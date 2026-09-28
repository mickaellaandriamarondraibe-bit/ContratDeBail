-- LegaBail - jeu de donnees de demonstration complet
-- PostgreSQL 15+
--
-- Ordre conseille sur une base vide :
--   1. schema-legabail.sql
--   2. legabail-articles-regles-complet.sql
--   3. donnees-demonstration-completes.sql
--
-- Ce script n'efface aucune donnee. Il peut etre rejoue sans dupliquer
-- les utilisateurs, candidatures, propositions, contrats ou signatures.

BEGIN;

-- Colonne reservee aux futures photos televersees des biens.
ALTER TABLE bien ADD COLUMN IF NOT EXISTS image_url TEXT;

-- ---------------------------------------------------------------------------
-- 1. Utilisateurs : 3 bailleurs et 4 locataires
-- Mot de passe commun pour ces comptes : Demo2026!
-- ---------------------------------------------------------------------------

INSERT INTO utilisateur
    (role, nom, prenom, email, mot_de_passe_hash, telephone, numero_piece)
VALUES
    ('BAILLEUR', 'Rakoto', 'Hery', 'hery.rakoto@demo.legabail.mg',
     'pbkdf2-sha256$600000$TGVnYUJhaWxEZW1vMjAyNg==$rtTBBrTuI4S9J5qVXAo/FWvpVzh4juCjTgNjW2miWd8=', '034 12 345 67', 'CIN 101 221 345 678'),
    ('BAILLEUR', 'Ravelo', 'Fara', 'fara.ravelo@demo.legabail.mg',
     'pbkdf2-sha256$600000$TGVnYUJhaWxEZW1vMjAyNg==$rtTBBrTuI4S9J5qVXAo/FWvpVzh4juCjTgNjW2miWd8=', '032 45 678 90', 'CIN 102 332 456 789'),
    ('BAILLEUR', 'Randria', 'Mamy', 'mamy.randria@demo.legabail.mg',
     'pbkdf2-sha256$600000$TGVnYUJhaWxEZW1vMjAyNg==$rtTBBrTuI4S9J5qVXAo/FWvpVzh4juCjTgNjW2miWd8=', '033 98 765 43', 'CIN 103 443 567 890'),
    ('LOCATAIRE', 'Rabe', 'Aina', 'aina.rabe@demo.legabail.mg',
     'pbkdf2-sha256$600000$TGVnYUJhaWxEZW1vMjAyNg==$rtTBBrTuI4S9J5qVXAo/FWvpVzh4juCjTgNjW2miWd8=', '034 22 111 44', 'CIN 201 111 222 333'),
    ('LOCATAIRE', 'Rasoanaivo', 'Tahina', 'tahina.rasoanaivo@demo.legabail.mg',
     'pbkdf2-sha256$600000$TGVnYUJhaWxEZW1vMjAyNg==$rtTBBrTuI4S9J5qVXAo/FWvpVzh4juCjTgNjW2miWd8=', '032 33 222 55', 'CIN 202 222 333 444'),
    ('LOCATAIRE', 'Andria', 'Soa', 'soa.andria@demo.legabail.mg',
     'pbkdf2-sha256$600000$TGVnYUJhaWxEZW1vMjAyNg==$rtTBBrTuI4S9J5qVXAo/FWvpVzh4juCjTgNjW2miWd8=', '033 44 333 66', 'CIN 203 333 444 555'),
    ('LOCATAIRE', 'Ramanana', 'Tiana', 'tiana.ramanana@demo.legabail.mg',
     'pbkdf2-sha256$600000$TGVnYUJhaWxEZW1vMjAyNg==$rtTBBrTuI4S9J5qVXAo/FWvpVzh4juCjTgNjW2miWd8=', '038 55 444 77', 'CIN 204 444 555 666')
ON CONFLICT (email) DO UPDATE SET
    role = EXCLUDED.role,
    nom = EXCLUDED.nom,
    prenom = EXCLUDED.prenom,
    mot_de_passe_hash = EXCLUDED.mot_de_passe_hash,
    telephone = EXCLUDED.telephone,
    numero_piece = EXCLUDED.numero_piece;

-- ---------------------------------------------------------------------------
-- 2. Biens : appartements, maisons, studio et local professionnel
-- ---------------------------------------------------------------------------

INSERT INTO bien
    (bailleur_id, adresse, type_bien, usage, type_logement,
     date_permis_habiter, inventaire)
SELECT u.id, v.adresse, v.type_bien, v.usage, v.type_logement,
       v.date_permis_habiter, v.inventaire
FROM utilisateur u
JOIN (VALUES
    ('hery.rakoto@demo.legabail.mg',
     'Lot II J 45, Ivandry, Antananarivo', 'Appartement', 'HABITATION',
     'MEUBLE', DATE '2023-04-18',
     '1 lit double, 1 armoire, 1 canape, 1 table, 4 chaises, 1 refrigerateur'),
    ('hery.rakoto@demo.legabail.mg',
     'Lot VA 12, Ambatobe, Antananarivo', 'Maison', 'HABITATION',
     'NU', DATE '2018-09-10', NULL),
    ('hery.rakoto@demo.legabail.mg',
     'Rue Rainandriamampandry, Tsaralalana, Antananarivo', 'Appartement',
     'HABITATION', 'NU', DATE '2024-02-02', NULL),
    ('fara.ravelo@demo.legabail.mg',
     'Immeuble Kintana, Analakely, Antananarivo', 'Studio', 'HABITATION',
     'MEUBLE', DATE '2022-11-21',
     '1 lit, 1 bureau, 1 chaise, 1 placard, kitchenette equipee'),
    ('fara.ravelo@demo.legabail.mg',
     'Zone Forello, Tanjombato, Antananarivo', 'Local professionnel',
     'PROFESSIONNEL_NON_COMMERCIAL', 'NU', DATE '2016-06-15', NULL),
    ('fara.ravelo@demo.legabail.mg',
     'Rue Dr Villette, Isoraka, Antananarivo', 'Appartement', 'HABITATION',
     'MEUBLE', DATE '2025-01-12',
     '2 lits, 2 armoires, salon complet, table a manger, refrigerateur'),
    ('mamy.randria@demo.legabail.mg',
     'Lot 118, Ambohidratrimo', 'Maison', 'HABITATION',
     'NU', DATE '2020-07-08', NULL),
    ('mamy.randria@demo.legabail.mg',
     'Avenue de l''Independance, Mahamasina, Antananarivo', 'Maison',
     'HABITATION', 'NU', DATE '2014-03-27', NULL)
) AS v(email, adresse, type_bien, usage, type_logement,
       date_permis_habiter, inventaire)
    ON u.email = v.email
WHERE NOT EXISTS (
    SELECT 1
    FROM bien b
    WHERE b.bailleur_id = u.id AND b.adresse = v.adresse
);

-- ---------------------------------------------------------------------------
-- 3. Annonces : plusieurs etats pour tester les ecrans
-- ---------------------------------------------------------------------------

INSERT INTO annonce
    (bien_id, loyer, charges, caution, avance, prix_logement_nu,
     date_debut, date_fin, sous_location, mode_paiement,
     clauses_speciales, statut, date_creation)
SELECT b.id, v.loyer, v.charges, v.caution, v.avance, v.prix_logement_nu,
       v.date_debut, v.date_fin, v.sous_location, v.mode_paiement,
       v.clauses_speciales, v.statut, v.date_creation
FROM bien b
JOIN (VALUES
    ('Lot II J 45, Ivandry, Antananarivo',
     750000.00, 50000.00, 500000.00, 500000.00, 550000.00,
     DATE '2026-10-01', DATE '2027-09-30', 'AUTORISATION_ECRITE',
     'Mobile Money avant le 5 du mois',
     'Animaux acceptes sous reserve de ne pas causer de nuisance.',
     'LOUEE', TIMESTAMPTZ '2026-08-12 09:00:00+03'),
    ('Lot VA 12, Ambatobe, Antananarivo',
     1200000.00, 100000.00, 1200000.00, 1200000.00, NULL,
     DATE '2026-11-01', NULL, 'INTERDITE',
     'Virement bancaire avant le 5 du mois',
     'Entretien du jardin a la charge du locataire.',
     'PUBLIEE', TIMESTAMPTZ '2026-09-02 10:30:00+03'),
    ('Rue Rainandriamampandry, Tsaralalana, Antananarivo',
     480000.00, 30000.00, 480000.00, 480000.00, NULL,
     DATE '2026-10-15', DATE '2027-10-14', 'AUTORISATION_ECRITE',
     'Especes contre recu ou Mobile Money', NULL,
     'PUBLIEE', TIMESTAMPTZ '2026-09-08 14:00:00+03'),
    ('Immeuble Kintana, Analakely, Antananarivo',
     650000.00, 40000.00, 500000.00, 500000.00, 480000.00,
     DATE '2026-10-10', DATE '2027-10-09', 'INTERDITE',
     'Virement bancaire',
     'Le mobilier devra etre restitue dans son etat initial.',
     'PUBLIEE', TIMESTAMPTZ '2026-09-10 08:45:00+03'),
    ('Zone Forello, Tanjombato, Antananarivo',
     900000.00, 120000.00, 900000.00, 900000.00, NULL,
     DATE '2026-12-01', NULL, 'INTERDITE',
     'Virement bancaire avant le 10 du mois',
     'Usage professionnel non commercial uniquement.',
     'PUBLIEE', TIMESTAMPTZ '2026-09-11 11:15:00+03'),
    ('Rue Dr Villette, Isoraka, Antananarivo',
     1100000.00, 80000.00, 1000000.00, 1000000.00, 800000.00,
     DATE '2027-01-01', DATE '2027-12-31', 'AUTORISATION_ECRITE',
     'Mobile Money',
     'Annonce en preparation, informations a confirmer.',
     'BROUILLON', TIMESTAMPTZ '2026-09-18 16:20:00+03'),
    ('Lot 118, Ambohidratrimo',
     600000.00, 25000.00, 600000.00, 300000.00, NULL,
     DATE '2026-10-20', NULL, 'INTERDITE',
     'Collecte par le bailleur',
     'Le locataire entretient la cour privative.',
     'PUBLIEE', TIMESTAMPTZ '2026-09-20 09:35:00+03'),
    ('Avenue de l''Independance, Mahamasina, Antananarivo',
     850000.00, 50000.00, 850000.00, 850000.00, NULL,
     DATE '2026-09-01', DATE '2027-08-31', 'INTERDITE',
     'Virement bancaire', NULL,
     'ARCHIVEE', TIMESTAMPTZ '2026-07-01 13:10:00+03')
) AS v(adresse, loyer, charges, caution, avance, prix_logement_nu,
       date_debut, date_fin, sous_location, mode_paiement,
       clauses_speciales, statut, date_creation)
    ON b.adresse = v.adresse
WHERE NOT EXISTS (
    SELECT 1
    FROM annonce a
    WHERE a.bien_id = b.id AND a.date_debut = v.date_debut
);

-- ---------------------------------------------------------------------------
-- 4. Candidatures
-- ---------------------------------------------------------------------------

INSERT INTO candidature
    (annonce_id, locataire_id, nombre_occupants, usage_prevu,
     message, statut, date_candidature)
SELECT a.id, u.id, v.nombre_occupants, v.usage_prevu,
       v.message, v.statut, v.date_candidature
FROM annonce a
JOIN bien b ON b.id = a.bien_id
JOIN (VALUES
    ('Lot II J 45, Ivandry, Antananarivo', 'aina.rabe@demo.legabail.mg',
     2, 'HABITATION', 'Nous souhaitons nous installer des le debut du bail.',
     'ACCEPTEE', TIMESTAMPTZ '2026-08-15 10:00:00+03'),
    ('Lot VA 12, Ambatobe, Antananarivo', 'tahina.rasoanaivo@demo.legabail.mg',
     4, 'HABITATION', 'Famille de quatre personnes, revenus stables.',
     'ENVOYEE', TIMESTAMPTZ '2026-09-05 15:30:00+03'),
    ('Immeuble Kintana, Analakely, Antananarivo', 'soa.andria@demo.legabail.mg',
     1, 'HABITATION', 'Je propose une date de debut legerement plus tardive.',
     'NEGOCIATION', TIMESTAMPTZ '2026-09-12 12:15:00+03'),
    ('Zone Forello, Tanjombato, Antananarivo', 'aina.rabe@demo.legabail.mg',
     2, 'PROFESSIONNEL_NON_COMMERCIAL',
     'Le local accueillera un cabinet de conseil.',
     'ENVOYEE', TIMESTAMPTZ '2026-09-14 09:20:00+03'),
    ('Lot 118, Ambohidratrimo', 'tiana.ramanana@demo.legabail.mg',
     3, 'HABITATION', 'Le logement correspond a notre recherche.',
     'ACCEPTEE', TIMESTAMPTZ '2026-09-21 17:05:00+03'),
    ('Rue Rainandriamampandry, Tsaralalana, Antananarivo',
     'soa.andria@demo.legabail.mg', 1, 'HABITATION',
     'Disponible pour une visite cette semaine.',
     'REFUSEE', TIMESTAMPTZ '2026-09-22 11:40:00+03'),
    ('Lot VA 12, Ambatobe, Antananarivo', 'tiana.ramanana@demo.legabail.mg',
     3, 'HABITATION', 'Nous recherchons un bail a duree indeterminee.',
     'ENVOYEE', TIMESTAMPTZ '2026-09-24 08:10:00+03')
) AS v(adresse, email, nombre_occupants, usage_prevu,
       message, statut, date_candidature)
    ON b.adresse = v.adresse
JOIN utilisateur u ON u.email = v.email
ON CONFLICT (annonce_id, locataire_id) DO UPDATE SET
    nombre_occupants = EXCLUDED.nombre_occupants,
    usage_prevu = EXCLUDED.usage_prevu,
    message = EXCLUDED.message,
    statut = EXCLUDED.statut,
    date_candidature = EXCLUDED.date_candidature;

-- ---------------------------------------------------------------------------
-- 5. Propositions, y compris une negociation en deux versions
-- ---------------------------------------------------------------------------

INSERT INTO proposition
    (candidature_id, numero_version, loyer, charges, caution, avance,
     date_debut, date_fin, sous_location, clauses_speciales,
     bailleur_accepte, locataire_accepte, date_creation)
SELECT c.id, v.numero_version, v.loyer, v.charges, v.caution, v.avance,
       v.date_debut, v.date_fin, v.sous_location, v.clauses_speciales,
       v.bailleur_accepte, v.locataire_accepte, v.date_creation
FROM candidature c
JOIN annonce a ON a.id = c.annonce_id
JOIN bien b ON b.id = a.bien_id
JOIN utilisateur u ON u.id = c.locataire_id
JOIN (VALUES
    ('Lot II J 45, Ivandry, Antananarivo', 'aina.rabe@demo.legabail.mg', 1,
     750000.00, 50000.00, 500000.00, 500000.00,
     DATE '2026-10-01', DATE '2027-09-30', 'AUTORISATION_ECRITE',
     'Animaux acceptes sans nuisance.', TRUE, TRUE,
     TIMESTAMPTZ '2026-08-16 10:00:00+03'),
    ('Lot VA 12, Ambatobe, Antananarivo', 'tahina.rasoanaivo@demo.legabail.mg', 1,
     1200000.00, 100000.00, 1200000.00, 1200000.00,
     DATE '2026-11-01', NULL, 'INTERDITE',
     'Entretien du jardin a la charge du locataire.', FALSE, FALSE,
     TIMESTAMPTZ '2026-09-05 15:31:00+03'),
    ('Immeuble Kintana, Analakely, Antananarivo', 'soa.andria@demo.legabail.mg', 1,
     650000.00, 40000.00, 500000.00, 500000.00,
     DATE '2026-10-10', DATE '2027-10-09', 'INTERDITE',
     'Mobilier a restituer dans son etat initial.', TRUE, FALSE,
     TIMESTAMPTZ '2026-09-12 12:16:00+03'),
    ('Immeuble Kintana, Analakely, Antananarivo', 'soa.andria@demo.legabail.mg', 2,
     625000.00, 40000.00, 500000.00, 500000.00,
     DATE '2026-11-01', DATE '2027-10-31', 'INTERDITE',
     'Nouvelle date et nouveau loyer proposes par le locataire.', FALSE, TRUE,
     TIMESTAMPTZ '2026-09-13 09:00:00+03'),
    ('Zone Forello, Tanjombato, Antananarivo', 'aina.rabe@demo.legabail.mg', 1,
     900000.00, 120000.00, 900000.00, 900000.00,
     DATE '2026-12-01', NULL, 'INTERDITE',
     'Usage professionnel non commercial uniquement.', FALSE, FALSE,
     TIMESTAMPTZ '2026-09-14 09:21:00+03'),
    ('Lot 118, Ambohidratrimo', 'tiana.ramanana@demo.legabail.mg', 1,
     600000.00, 25000.00, 600000.00, 300000.00,
     DATE '2026-10-20', NULL, 'INTERDITE',
     'Entretien de la cour privative.', TRUE, TRUE,
     TIMESTAMPTZ '2026-09-22 08:00:00+03'),
    ('Rue Rainandriamampandry, Tsaralalana, Antananarivo',
     'soa.andria@demo.legabail.mg', 1,
     480000.00, 30000.00, 480000.00, 480000.00,
     DATE '2026-10-15', DATE '2027-10-14', 'AUTORISATION_ECRITE',
     NULL, FALSE, FALSE, TIMESTAMPTZ '2026-09-22 11:41:00+03'),
    ('Lot VA 12, Ambatobe, Antananarivo', 'tiana.ramanana@demo.legabail.mg', 1,
     1200000.00, 100000.00, 1200000.00, 1200000.00,
     DATE '2026-11-01', NULL, 'INTERDITE',
     'Entretien du jardin a la charge du locataire.', FALSE, FALSE,
     TIMESTAMPTZ '2026-09-24 08:11:00+03')
) AS v(adresse, email, numero_version, loyer, charges, caution, avance,
       date_debut, date_fin, sous_location, clauses_speciales,
       bailleur_accepte, locataire_accepte, date_creation)
    ON b.adresse = v.adresse AND u.email = v.email
ON CONFLICT (candidature_id, numero_version) DO UPDATE SET
    loyer = EXCLUDED.loyer,
    charges = EXCLUDED.charges,
    caution = EXCLUDED.caution,
    avance = EXCLUDED.avance,
    date_debut = EXCLUDED.date_debut,
    date_fin = EXCLUDED.date_fin,
    sous_location = EXCLUDED.sous_location,
    clauses_speciales = EXCLUDED.clauses_speciales,
    bailleur_accepte = EXCLUDED.bailleur_accepte,
    locataire_accepte = EXCLUDED.locataire_accepte,
    date_creation = EXCLUDED.date_creation;

-- ---------------------------------------------------------------------------
-- 6. Contrats generes apres double accord
-- ---------------------------------------------------------------------------

INSERT INTO contrat
    (proposition_id, numero, contenu, fichier_pdf, statut, date_generation)
SELECT p.id, v.numero, v.contenu, NULL, v.statut, v.date_generation
FROM proposition p
JOIN candidature c ON c.id = p.candidature_id
JOIN annonce a ON a.id = c.annonce_id
JOIN bien b ON b.id = a.bien_id
JOIN utilisateur u ON u.id = c.locataire_id
JOIN (VALUES
    ('Lot II J 45, Ivandry, Antananarivo', 'aina.rabe@demo.legabail.mg', 1,
     'LB-DEMO-2026-001',
     E'CONTRAT DE BAIL\n\nBailleur : Rakoto Hery\nLocataire : Rabe Aina\nBien : Lot II J 45, Ivandry, Antananarivo\nLoyer : 750000 Ar\nCharges : 50000 Ar\nDebut : 01/10/2026\nFin : 30/09/2027\n\nLes deux parties ont accepte les conditions de la proposition version 1.',
     'SIGNE', TIMESTAMPTZ '2026-08-18 14:00:00+03'),
    ('Lot 118, Ambohidratrimo', 'tiana.ramanana@demo.legabail.mg', 1,
     'LB-DEMO-2026-002',
     E'CONTRAT DE BAIL\n\nBailleur : Randria Mamy\nLocataire : Ramanana Tiana\nBien : Lot 118, Ambohidratrimo\nLoyer : 600000 Ar\nCharges : 25000 Ar\nDebut : 20/10/2026\nDuree : indeterminee\n\nLes deux parties ont accepte les conditions de la proposition version 1.',
     'A_SIGNER', TIMESTAMPTZ '2026-09-22 10:00:00+03')
) AS v(adresse, email, numero_version, numero, contenu, statut, date_generation)
    ON b.adresse = v.adresse
   AND u.email = v.email
   AND p.numero_version = v.numero_version
ON CONFLICT (proposition_id) DO UPDATE SET
    contenu = EXCLUDED.contenu,
    statut = EXCLUDED.statut,
    date_generation = EXCLUDED.date_generation;

-- ---------------------------------------------------------------------------
-- 7. Signatures : contrat signe et contrat encore en attente du locataire
-- ---------------------------------------------------------------------------

INSERT INTO signature
    (contrat_id, utilisateur_id, date_signature, statut)
SELECT ct.id, u.id, v.date_signature, v.statut
FROM contrat ct
JOIN (VALUES
    ('LB-DEMO-2026-001', 'hery.rakoto@demo.legabail.mg',
     TIMESTAMPTZ '2026-08-19 09:00:00+03', 'SIGNEE'),
    ('LB-DEMO-2026-001', 'aina.rabe@demo.legabail.mg',
     TIMESTAMPTZ '2026-08-19 10:30:00+03', 'SIGNEE'),
    ('LB-DEMO-2026-002', 'mamy.randria@demo.legabail.mg',
     TIMESTAMPTZ '2026-09-23 08:45:00+03', 'SIGNEE'),
    ('LB-DEMO-2026-002', 'tiana.ramanana@demo.legabail.mg',
     NULL::TIMESTAMPTZ, 'EN_ATTENTE')
) AS v(numero_contrat, email, date_signature, statut)
    ON ct.numero = v.numero_contrat
JOIN utilisateur u ON u.email = v.email
ON CONFLICT (contrat_id, utilisateur_id) DO UPDATE SET
    date_signature = EXCLUDED.date_signature,
    statut = EXCLUDED.statut;

COMMIT;

-- Controle rapide du volume charge par table.
SELECT 'utilisateur' AS table_name, COUNT(*) AS total FROM utilisateur
UNION ALL SELECT 'bien', COUNT(*) FROM bien
UNION ALL SELECT 'annonce', COUNT(*) FROM annonce
UNION ALL SELECT 'article_juridique', COUNT(*) FROM article_juridique
UNION ALL SELECT 'regle', COUNT(*) FROM regle
UNION ALL SELECT 'candidature', COUNT(*) FROM candidature
UNION ALL SELECT 'proposition', COUNT(*) FROM proposition
UNION ALL SELECT 'contrat', COUNT(*) FROM contrat
UNION ALL SELECT 'signature', COUNT(*) FROM signature
ORDER BY table_name;

