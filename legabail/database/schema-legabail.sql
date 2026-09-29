-- LegaBail - Base PostgreSQL minimale pour le prototype

BEGIN;

-- 1. Tous les comptes : bailleur ou locataire
CREATE TABLE utilisateur (
    id                BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role              VARCHAR(15) NOT NULL CHECK (role IN ('BAILLEUR', 'LOCATAIRE')),
    nom               VARCHAR(100) NOT NULL,
    prenom            VARCHAR(100) NOT NULL,
    email             VARCHAR(180) NOT NULL UNIQUE,
    mot_de_passe_hash VARCHAR(255) NOT NULL,
    telephone         VARCHAR(30),
    date_naissance    DATE,
    numero_piece      VARCHAR(100)
);

-- 2. Logements enregistrés par les bailleurs
CREATE TABLE bien (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bailleur_id         BIGINT NOT NULL REFERENCES utilisateur(id),
    adresse             TEXT NOT NULL,
    type_bien           VARCHAR(40) NOT NULL,
    usage               VARCHAR(40) NOT NULL,
    type_logement       VARCHAR(15) NOT NULL CHECK (type_logement IN ('NU', 'MEUBLE')),
    date_permis_habiter DATE,
    inventaire          TEXT,
    image_url           TEXT
);

-- 3. Conditions proposées par le bailleur et visibles sur l'accueil
CREATE TABLE annonce (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    bien_id               BIGINT NOT NULL REFERENCES bien(id),
    loyer                 NUMERIC(15,2) NOT NULL CHECK (loyer > 0),
    charges               NUMERIC(15,2) NOT NULL DEFAULT 0 CHECK (charges >= 0),
    caution               NUMERIC(15,2) NOT NULL DEFAULT 0 CHECK (caution >= 0),
    avance                NUMERIC(15,2) NOT NULL DEFAULT 0 CHECK (avance >= 0),
    prix_logement_nu      NUMERIC(15,2),
    date_debut            DATE NOT NULL,
    date_fin              DATE,
    sous_location         VARCHAR(30),
    mode_paiement         VARCHAR(50),
    clauses_speciales     TEXT,
    statut                VARCHAR(20) NOT NULL DEFAULT 'BROUILLON'
                          CHECK (statut IN ('BROUILLON', 'PUBLIEE', 'LOUEE', 'ARCHIVEE')),
    date_creation         TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 4. Articles juridiques extraits du document
CREATE TABLE article_juridique (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    numero                VARCHAR(20) NOT NULL UNIQUE,
    titre                 VARCHAR(255) NOT NULL,
    texte_original        TEXT NOT NULL,
    explication_simple    TEXT NOT NULL
);

-- 5. Règles unitaires tirées des articles
CREATE TABLE regle (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    article_id            BIGINT NOT NULL REFERENCES article_juridique(id),
    code                  VARCHAR(100) NOT NULL UNIQUE,
    titre                 VARCHAR(255) NOT NULL,
    explication_simple    TEXT NOT NULL,
    condition_json        JSONB,
    controle              VARCHAR(100),
    consequence           TEXT,
    bloquante             BOOLEAN NOT NULL DEFAULT FALSE
);

-- 6. Demande d'un locataire pour une annonce
CREATE TABLE candidature (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    annonce_id            BIGINT NOT NULL REFERENCES annonce(id),
    locataire_id          BIGINT NOT NULL REFERENCES utilisateur(id),
    nombre_occupants      INTEGER NOT NULL CHECK (nombre_occupants > 0),
    usage_prevu           VARCHAR(50) NOT NULL,
    message               TEXT,
    statut                VARCHAR(20) NOT NULL DEFAULT 'ENVOYEE'
                          CHECK (statut IN ('ENVOYEE', 'NEGOCIATION', 'ACCEPTEE', 'REFUSEE')),
    date_candidature      TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (annonce_id, locataire_id)
);

-- 7. Conditions négociées et acceptées par les deux parties
CREATE TABLE proposition (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    candidature_id        BIGINT NOT NULL REFERENCES candidature(id),
    numero_version        INTEGER NOT NULL DEFAULT 1,
    loyer                 NUMERIC(15,2) NOT NULL,
    charges               NUMERIC(15,2) NOT NULL DEFAULT 0,
    caution               NUMERIC(15,2) NOT NULL DEFAULT 0,
    avance                NUMERIC(15,2) NOT NULL DEFAULT 0,
    date_debut            DATE NOT NULL,
    date_fin              DATE,
    sous_location         VARCHAR(30),
    clauses_speciales     TEXT,
    bailleur_accepte      BOOLEAN NOT NULL DEFAULT FALSE,
    locataire_accepte     BOOLEAN NOT NULL DEFAULT FALSE,
    date_creation         TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (candidature_id, numero_version)
);

-- 8. Contrat généré seulement après le double accord
CREATE TABLE contrat (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    proposition_id        BIGINT NOT NULL UNIQUE REFERENCES proposition(id),
    numero                VARCHAR(50) NOT NULL UNIQUE,
    contenu               TEXT NOT NULL,
    fichier_pdf           TEXT,
    statut                VARCHAR(20) NOT NULL DEFAULT 'A_SIGNER'
                          CHECK (statut IN ('A_SIGNER', 'SIGNE', 'TERMINE', 'RESILIE')),
    date_generation       TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 9. Une signature du bailleur et une signature du locataire
CREATE TABLE signature (
    id                    BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    contrat_id            BIGINT NOT NULL REFERENCES contrat(id),
    utilisateur_id        BIGINT NOT NULL REFERENCES utilisateur(id),
    date_signature        TIMESTAMPTZ,
    statut                VARCHAR(15) NOT NULL DEFAULT 'EN_ATTENTE'
                          CHECK (statut IN ('EN_ATTENTE', 'SIGNEE', 'REFUSEE')),
    UNIQUE (contrat_id, utilisateur_id)
);

CREATE INDEX idx_annonce_statut ON annonce(statut);
CREATE INDEX idx_candidature_annonce ON candidature(annonce_id);
CREATE INDEX idx_candidature_locataire ON candidature(locataire_id);

COMMIT;

