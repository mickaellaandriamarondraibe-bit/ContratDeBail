-- LegaBail - Insertion complète des 35 articles et 133 règles simplifiées
-- Source : document de travail fourni sur l'ordonnance n°62-100.
-- Le document fourni ne reproduit pas le texte légal original mot pour mot.
-- Les marqueurs TEXTE_ORIGINAL_A_COMPLETER doivent être remplacés après validation.

BEGIN;

CREATE TABLE IF NOT EXISTS article_juridique (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    numero VARCHAR(20) NOT NULL UNIQUE,
    titre VARCHAR(255) NOT NULL,
    texte_original TEXT NOT NULL,
    explication_simple TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS regle (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    article_id BIGINT NOT NULL REFERENCES article_juridique(id),
    code VARCHAR(100) NOT NULL UNIQUE,
    titre VARCHAR(255) NOT NULL,
    explication_simple TEXT NOT NULL,
    condition_json JSONB,
    controle VARCHAR(100),
    consequence TEXT,
    bloquante BOOLEAN NOT NULL DEFAULT FALSE
);

-- Article 1 : Quels locaux sont concernés ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('1', 'Quels locaux sont concernés ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_1',
        'Il faut connaître le type de local et son usage avant de choisir les règles applicables.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '1'),
    'ART1_R1_COMPLEMENT_DU_CODE_CIVIL',
    'Complément du Code civil',
    'L’ordonnance n’est pas le seul texte applicable. Les règles du Code civil sur la location continuent de s’appliquer lorsqu’elles ne sont pas contraires à cette ordonnance.',
    '{"article":1,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’ordonnance n’est pas le seul texte applicable. Les règles du Code civil sur la location continuent de s’appliquer lorsqu’elles ne sont pas contraires à cette ordonnance.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '1'),
    'ART1_R2_LOGEMENTS_D_HABITATION',
    'Logements d’habitation',
    'Les maisons, appartements et autres locaux utilisés pour habiter sont concernés par l’ordonnance.',
    '{"article":1,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les maisons, appartements et autres locaux utilisés pour habiter sont concernés par l’ordonnance.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '1'),
    'ART1_R3_HOTELS_EXCLUS',
    'Hôtels exclus',
    'Les hôtels et les pensions de famille ne sont pas considérés ici comme de simples locations d’habitation.',
    '{"article":1,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les hôtels et les pensions de famille ne sont pas considérés ici comme de simples locations d’habitation.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '1'),
    'ART1_R4_CERTAINS_LOCAUX_PROFESSIONNELS',
    'Certains locaux professionnels',
    'Certains locaux utilisés pour une activité professionnelle sans caractère commercial ou industriel peuvent être concernés. Il faut néanmoins vérifier qu’ils ne dépendent pas d’un autre régime juridique.',
    '{"article":1,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Certains locaux utilisés pour une activité professionnelle sans caractère commercial ou industriel peuvent être concernés. Il faut néanmoins vérifier qu’ils ne dépendent pas d’un autre régime juridique.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '1'),
    'ART1_R5_ASSOCIATIONS_ET_SYNDICATS',
    'Associations et syndicats',
    'Les locaux loués par des organismes sans but lucratif peuvent être concernés. Cela comprend notamment certaines associations déclarées et certains syndicats.',
    '{"article":1,"regle":5,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les locaux loués par des organismes sans but lucratif peuvent être concernés. Cela comprend notamment certaines associations déclarées et certains syndicats.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '1'),
    'ART1_R6_LOGEMENTS_DESTINES_AU_PERSONNEL',
    'Logements destinés au personnel',
    'Une entreprise commerciale ou industrielle peut louer un logement exclusivement pour y loger son personnel.',
    '{"article":1,"regle":6,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Une entreprise commerciale ou industrielle peut louer un logement exclusivement pour y loger son personnel.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 2 : Le logement fourni avec un emploi
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('2', 'Le logement fourni avec un emploi',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_2',
        'Un logement directement lié à un emploi peut suivre un autre régime.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '2'),
    'ART2_R1_LOGEMENT_DIRECTEMENT_LIE_A_L_EMPLOI',
    'Logement directement lié à l’emploi',
    'Un logement attribué à une personne parce qu’elle exerce une fonction ou occupe un emploi est exclu de cette ordonnance lorsqu’il constitue un avantage lié à son salaire.',
    '{"article":2,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Un logement attribué à une personne parce qu’elle exerce une fonction ou occupe un emploi est exclu de cette ordonnance lorsqu’il constitue un avantage lié à son salaire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '2'),
    'ART2_R2_QUALIFICATION_NECESSAIRE',
    'Qualification nécessaire',
    'Il faut vérifier si la personne loue réellement le logement ou si elle l’occupe uniquement grâce à son emploi.',
    '{"article":2,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Il faut vérifier si la personne loue réellement le logement ou si elle l’occupe uniquement grâce à son emploi.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 3 : Comment fixer et modifier le loyer ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('3', 'Comment fixer et modifier le loyer ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_3',
        'L’ancienneté du bâtiment, son état, les charges et la dernière révision influencent le loyer.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '3'),
    'ART3_R1_LIBERTE_PENDANT_LES_CINQ_PREMIERES_ANNEES',
    'Liberté pendant les cinq premières années',
    'Pendant les cinq premières années suivant la délivrance du permis d’habiter d’un bâtiment neuf ou reconstruit, le bailleur et le locataire peuvent fixer ensemble le loyer.',
    '{"article":3,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Pendant les cinq premières années suivant la délivrance du permis d’habiter d’un bâtiment neuf ou reconstruit, le bailleur et le locataire peuvent fixer ensemble le loyer.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '3'),
    'ART3_R2_PLAFOND_APRES_CINQ_ANS',
    'Plafond après cinq ans',
    'Après cinq ans, le loyer ne doit pas dépasser un plafond calculé à partir de la valeur de l’immeuble et de règles fixées par décret.',
    '{"article":3,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Après cinq ans, le loyer ne doit pas dépasser un plafond calculé à partir de la valeur de l’immeuble et de règles fixées par décret.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '3'),
    'ART3_R3_REDUCTION_POUR_MAUVAIS_ETAT',
    'Réduction pour mauvais état',
    'Le loyer peut être réduit, jusqu’à une certaine limite, lorsque le logement est mal entretenu ou manque de confort. Cette réduction ne doit pas être appliquée automatiquement par le locataire sans accord ou décision exécutoire.',
    '{"article":3,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le loyer peut être réduit, jusqu’à une certaine limite, lorsque le logement est mal entretenu ou manque de confort. Cette réduction ne doit pas être appliquée automatiquement par le locataire sans accord ou décision exécutoire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '3'),
    'ART3_R4_DELAI_ENTRE_DEUX_REVISIONS',
    'Délai entre deux révisions',
    'Le loyer ne peut normalement pas être révisé avant un an depuis sa fixation initiale ou sa dernière révision. Une clause prévoyant un prix fixe pendant une durée supérieure doit être respectée.',
    '{"article":3,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le loyer ne peut normalement pas être révisé avant un an depuis sa fixation initiale ou sa dernière révision. Une clause prévoyant un prix fixe pendant une durée supérieure doit être respectée.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '3'),
    'ART3_R5_CHARGES_AJOUTEES_AU_LOYER',
    'Charges ajoutées au loyer',
    'Certaines charges réellement payées par le propriétaire peuvent être ajoutées au loyer. Les impôts ne font pas partie de ces charges selon cet article.',
    '{"article":3,"regle":5,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Certaines charges réellement payées par le propriétaire peuvent être ajoutées au loyer. Les impôts ne font pas partie de ces charges selon cet article.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 4 : Comment connaître la valeur du bâtiment ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('4', 'Comment connaître la valeur du bâtiment ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_4',
        'L’application ne doit pas inventer elle-même la valeur légale du bâtiment.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '4'),
    'ART4_R1_METHODE_FIXEE_PAR_DECRET',
    'Méthode fixée par décret',
    'La méthode utilisée pour calculer la valeur légale d’un bâtiment doit être définie par des décrets.',
    '{"article":4,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'La méthode utilisée pour calculer la valeur légale d’un bâtiment doit être définie par des décrets.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '4'),
    'ART4_R2_INTERVENTION_DE_COMMISSIONS',
    'Intervention de commissions',
    'Des commissions doivent proposer les méthodes de calcul de cette valeur.',
    '{"article":4,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Des commissions doivent proposer les méthodes de calcul de cette valeur.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '4'),
    'ART4_R3_CRITERES_D_EVALUATION',
    'Critères d’évaluation',
    'Le calcul doit notamment tenir compte du type de construction et de l’ancienneté du bâtiment.',
    '{"article":4,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le calcul doit notamment tenir compte du type de construction et de l’ancienneté du bâtiment.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 5 : Quel est le plafond du loyer mensuel ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('5', 'Quel est le plafond du loyer mensuel ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_5',
        'La durée de la location modifie le plafond du loyer mensuel.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '5'),
    'ART5_R1_LOCATION_DE_TROIS_MOIS_MAXIMUM',
    'Location de trois mois maximum',
    'Pour une location faite au mois et ne dépassant pas trois mois, le loyer mensuel ne doit pas dépasser un dixième du loyer annuel légal.',
    '{"article":5,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Pour une location faite au mois et ne dépassant pas trois mois, le loyer mensuel ne doit pas dépasser un dixième du loyer annuel légal.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '5'),
    'ART5_R2_LOCATION_DE_PLUS_DE_TROIS_MOIS',
    'Location de plus de trois mois',
    'Pour une location faite au mois pendant plus de trois mois, le loyer mensuel ne doit pas dépasser un douzième du loyer annuel légal.',
    '{"article":5,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Pour une location faite au mois pendant plus de trois mois, le loyer mensuel ne doit pas dépasser un douzième du loyer annuel légal.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 6 : La caution et l’avance d’un logement vide
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('6', 'La caution et l’avance d’un logement vide',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_6',
        'Pour un logement vide loué au mois : caution + avance ≤ deux mois de loyer.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '6'),
    'ART6_R1_LOGEMENT_CONCERNE',
    'Logement concerné',
    'Les plafonds de cet article concernent les logements loués sans mobilier.',
    '{"article":6,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les plafonds de cet article concernent les logements loués sans mobilier.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '6'),
    'ART6_R2_LOCATION_FAITE_AU_MOIS',
    'Location faite au mois',
    'Pour une location faite au mois, la caution et l’avance réunies ne doivent pas dépasser deux mois de loyer.',
    '{"article":6,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Pour une location faite au mois, la caution et l’avance réunies ne doivent pas dépasser deux mois de loyer.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '6'),
    'ART6_R3_AUTRES_TYPES_DE_LOCATION',
    'Autres types de location',
    'Dans les autres cas visés par l’article, la caution et l’avance réunies ne doivent pas dépasser le quart du loyer annuel.',
    '{"article":6,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Dans les autres cas visés par l’article, la caution et l’avance réunies ne doivent pas dépasser le quart du loyer annuel.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 7 : Le prix d’un logement meublé
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('7', 'Le prix d’un logement meublé',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_7',
        'La mention « logement meublé » ne permet pas automatiquement d’ajouter 50 %.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '7'),
    'ART7_R1_MAJORATION_MAXIMALE',
    'Majoration maximale',
    'Le mobilier ne peut pas augmenter le loyer de plus de 50 % par rapport au prix du logement vide.',
    '{"article":7,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le mobilier ne peut pas augmenter le loyer de plus de 50 % par rapport au prix du logement vide.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '7'),
    'ART7_R2_MAJORATION_PROPORTIONNELLE',
    'Majoration proportionnelle',
    'L’augmentation doit correspondre à la quantité, à la qualité et à l’utilité du mobilier fourni.',
    '{"article":7,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’augmentation doit correspondre à la quantité, à la qualité et à l’utilité du mobilier fourni.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '7'),
    'ART7_R3_CONDITION_POUR_APPLIQUER_LE_MAXIMUM',
    'Condition pour appliquer le maximum',
    'La majoration maximale suppose que le mobilier soit en parfait état et adapté au logement.',
    '{"article":7,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La majoration maximale suppose que le mobilier soit en parfait état et adapté au logement.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 8 : Le remboursement d’un loyer payé en trop
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('8', 'Le remboursement d’un loyer payé en trop',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_8',
        'Le trop-perçu doit être remboursé, mais la période réclamée est limitée.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '8'),
    'ART8_R1_OBLIGATION_DE_REMBOURSEMENT',
    'Obligation de remboursement',
    'Le propriétaire doit rembourser les sommes perçues au-dessus du taux légal.',
    '{"article":8,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le propriétaire doit rembourser les sommes perçues au-dessus du taux légal.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '8'),
    'ART8_R2_PERIODE_POUVANT_ETRE_RECLAMEE',
    'Période pouvant être réclamée',
    'Selon cet article, le locataire ne peut réclamer que les sommes concernant l’année précédant sa demande expresse.',
    '{"article":8,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Selon cet article, le locataire ne peut réclamer que les sommes concernant l’année précédant sa demande expresse.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 9 : Le paiement pendant une contestation
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('9', 'Le paiement pendant une contestation',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_9',
        'Contester le loyer ne permet pas d’arrêter automatiquement de payer.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '9'),
    'ART9_R1_CONTINUER_A_PAYER',
    'Continuer à payer',
    'Le locataire qui conteste le montant du loyer doit continuer à payer l’ancien montant.',
    '{"article":9,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le locataire qui conteste le montant du loyer doit continuer à payer l’ancien montant.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '9'),
    'ART9_R2_ACCORD_OU_DECISION_EXECUTOIRE',
    'Accord ou décision exécutoire',
    'Cette situation continue jusqu’à l’obtention : - d’un accord entre les parties ; - ou d’une décision de justice exécutoire.',
    '{"article":9,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Cette situation continue jusqu’à l’obtention : - d’un accord entre les parties ; - ou d’une décision de justice exécutoire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '9'),
    'ART9_R3_REFUS_DE_RESPECTER_LA_DECISION',
    'Refus de respecter la décision',
    'Si le locataire refuse la décision et quitte le logement, il peut supporter les frais du procès et les conséquences du préavis.',
    '{"article":9,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Si le locataire refuse la décision et quitte le logement, il peut supporter les frais du procès et les conséquences du préavis.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 10 : Quand faut-il l’autorisation du propriétaire ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('10', 'Quand faut-il l’autorisation du propriétaire ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_10',
        'Certaines cessions et sous-locations nécessitent l’accord du propriétaire.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '10'),
    'ART10_R1_CESSION_DU_BAIL',
    'Cession du bail',
    'Le locataire doit obtenir l’autorisation expresse du propriétaire pour céder son bail.',
    '{"article":10,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le locataire doit obtenir l’autorisation expresse du propriétaire pour céder son bail.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '10'),
    'ART10_R2_SOUS_LOCATION_DE_PLUS_DES_DEUX_TIERS',
    'Sous-location de plus des deux tiers',
    'L’autorisation est nécessaire lorsque la sous-location concerne plus des deux tiers des pièces d’habitation.',
    '{"article":10,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’autorisation est nécessaire lorsque la sous-location concerne plus des deux tiers des pièces d’habitation.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '10'),
    'ART10_R3_LOGEMENT_MEUBLE_PAR_LE_PROPRIETAIRE',
    'Logement meublé par le propriétaire',
    'L’autorisation est nécessaire pour sous-louer ou mettre à disposition une partie d’un logement meublé par le propriétaire.',
    '{"article":10,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’autorisation est nécessaire pour sous-louer ou mettre à disposition une partie d’un logement meublé par le propriétaire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '10'),
    'ART10_R4_EXCEPTION_FAMILIALE',
    'Exception familiale',
    'Ces restrictions ne s’appliquent pas de la même manière aux ascendants et descendants directs du locataire. Cela ne signifie pas que tous les membres de la famille sont concernés.',
    '{"article":10,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Ces restrictions ne s’appliquent pas de la même manière aux ascendants et descendants directs du locataire. Cela ne signifie pas que tous les membres de la famille sont concernés.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '10'),
    'ART10_R5_CONSEQUENCE_D_UNE_ABSENCE_D_AUTORISATION',
    'Conséquence d’une absence d’autorisation',
    'Le contrat réalisé sans l’autorisation nécessaire peut être considéré comme nul. L’application ne peut cependant pas prononcer elle-même cette nullité.',
    '{"article":10,"regle":5,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le contrat réalisé sans l’autorisation nécessaire peut être considéré comme nul. L’application ne peut cependant pas prononcer elle-même cette nullité.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 11 : Quel prix demander au sous-locataire ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('11', 'Quel prix demander au sous-locataire ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_11',
        'Le locataire principal ne peut pas utiliser la sous-location pour demander un prix excessif.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '11'),
    'ART11_R1_PLAFOND_DU_PRIX',
    'Plafond du prix',
    'Le prix demandé au sous-locataire ne doit pas dépasser le montant légal correspondant à la superficie qu’il occupe.',
    '{"article":11,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le prix demandé au sous-locataire ne doit pas dépasser le montant légal correspondant à la superficie qu’il occupe.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 12 : Les droits du sous-locataire
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('12', 'Les droits du sous-locataire',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_12',
        'Le sous-locataire possède également certaines protections concernant le prix.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '12'),
    'ART12_R1_REMBOURSEMENT_DU_TROP_PERCU',
    'Remboursement du trop-perçu',
    'Les règles de remboursement de l’article 8 s’appliquent également entre le locataire principal et le sous-locataire.',
    '{"article":12,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les règles de remboursement de l’article 8 s’appliquent également entre le locataire principal et le sous-locataire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '12'),
    'ART12_R2_PAIEMENT_PENDANT_LA_CONTESTATION',
    'Paiement pendant la contestation',
    'Les règles de l’article 9 s’appliquent aussi pendant une contestation entre le locataire principal et le sous-locataire.',
    '{"article":12,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les règles de l’article 9 s’appliquent aussi pendant une contestation entre le locataire principal et le sous-locataire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 13 : L’occupant de bonne foi
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('13', 'L’occupant de bonne foi',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_13',
        'La bonne foi dépend du comportement réel de l’occupant, pas d’une simple déclaration.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '13'),
    'ART13_R1_DROIT_AU_MAINTIEN',
    'Droit au maintien',
    'Certains locataires et occupants qui étaient déjà présents lors de la publication de l’ordonnance peuvent bénéficier d’un droit au maintien dans les lieux. La portée actuelle de cette ancienne condition doit être vérifiée.',
    '{"article":13,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Certains locataires et occupants qui étaient déjà présents lors de la publication de l’ordonnance peuvent bénéficier d’un droit au maintien dans les lieux. La portée actuelle de cette ancienne condition doit être vérifiée.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '13'),
    'ART13_R2_UTILISATION_NORMALE_DU_LOGEMENT',
    'Utilisation normale du logement',
    'L’occupant de bonne foi doit utiliser normalement les lieux loués.',
    '{"article":13,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’occupant de bonne foi doit utiliser normalement les lieux loués.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '13'),
    'ART13_R3_RESPECT_DES_OBLIGATIONS',
    'Respect des obligations',
    'Il doit respecter régulièrement ses obligations, notamment payer le loyer.',
    '{"article":13,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Il doit respecter régulièrement ses obligations, notamment payer le loyer.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 14 : Que se passe-t-il après un décès ou un départ ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('14', 'Que se passe-t-il après un décès ou un départ ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_14',
        'La famille ne récupère pas automatiquement le bail : plusieurs conditions doivent être vérifiées.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '14'),
    'ART14_R1_EVENEMENT_CONCERNE',
    'Événement concerné',
    'L’article s’applique en cas de décès du locataire ou d’abandon de son domicile.',
    '{"article":14,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’article s’applique en cas de décès du locataire ou d’abandon de son domicile.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '14'),
    'ART14_R2_PERSONNES_POUVANT_BENEFICIER_DU_BAIL',
    'Personnes pouvant bénéficier du bail',
    'Certains membres de la famille et certaines personnes à la charge du locataire peuvent bénéficier du maintien.',
    '{"article":14,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Certains membres de la famille et certaines personnes à la charge du locataire peuvent bénéficier du maintien.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '14'),
    'ART14_R3_PRESENCE_DE_PLUS_DE_SIX_MOIS',
    'Présence de plus de six mois',
    'Ces personnes doivent déjà occuper le logement depuis plus de six mois.',
    '{"article":14,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Ces personnes doivent déjà occuper le logement depuis plus de six mois.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '14'),
    'ART14_R4_LOCAL_EXCLUSIVEMENT_PROFESSIONNEL',
    'Local exclusivement professionnel',
    'Pour un local exclusivement professionnel, le bénéfice dépend notamment de la continuation de la profession exercée dans les lieux.',
    '{"article":14,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Pour un local exclusivement professionnel, le bénéfice dépend notamment de la continuation de la profession exercée dans les lieux.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 15 : Quand peut-on perdre le droit de rester ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('15', 'Quand peut-on perdre le droit de rester ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_15',
        'Il faut vérifier les conditions et les exceptions avant de conclure qu’une personne perd son droit.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R1_ABSENCE_D_OCCUPATION_REELLE',
    'Absence d’occupation réelle',
    'L’occupant peut perdre le maintien lorsqu’il n’habite pas réellement le logement.',
    '{"article":15,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’occupant peut perdre le maintien lorsqu’il n’habite pas réellement le logement.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R2_DEPART_DEFINITIF_DE_LA_LOCALITE',
    'Départ définitif de la localité',
    'Un départ définitif peut entraîner la perte du maintien. Une exception peut exister si sa famille ou des personnes à sa charge doivent rester dans le logement.',
    '{"article":15,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Un départ définitif peut entraîner la perte du maintien. Une exception peut exister si sa famille ou des personnes à sa charge doivent rester dans le logement.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R3_RESIDENCE_SECONDAIRE',
    'Résidence secondaire',
    'Le maintien peut être refusé lorsque le logement n’est pas la résidence principale. Une exception peut exister si une activité professionnelle exige cette résidence secondaire.',
    '{"article":15,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le maintien peut être refusé lorsque le logement n’est pas la résidence principale. Une exception peut exister si une activité professionnelle exige cette résidence secondaire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R4_EXISTENCE_D_UN_AUTRE_LOGEMENT',
    'Existence d’un autre logement',
    'L’occupant peut perdre le maintien s’il possède ou peut récupérer un autre logement adapté à ses besoins familiaux.',
    '{"article":15,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’occupant peut perdre le maintien s’il possède ou peut récupérer un autre logement adapté à ses besoins familiaux.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R5_PROPOSITION_D_UN_RELOGEMENT',
    'Proposition d’un relogement',
    'Le maintien peut être refusé lorsqu’un relogement dans des conditions sensiblement identiques est réellement possible.',
    '{"article":15,"regle":5,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le maintien peut être refusé lorsqu’un relogement dans des conditions sensiblement identiques est réellement possible.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R6_LOCATION_LIMITEE_A_UNE_ABSENCE',
    'Location limitée à une absence',
    'Le maintien peut être refusé si la location avait été expressément limitée à la durée d’une absence temporaire.',
    '{"article":15,"regle":6,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le maintien peut être refusé si la location avait été expressément limitée à la durée d’une absence temporaire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R7_FIN_DE_LA_FONCTION',
    'Fin de la fonction',
    'Le maintien peut prendre fin lorsque le logement dépendait d’une fonction ou d’un emploi qui a cessé.',
    '{"article":15,"regle":7,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le maintien peut prendre fin lorsque le logement dépendait d’une fonction ou d’un emploi qui a cessé.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R8_LOGEMENT_INHABITABLE',
    'Logement inhabitable',
    'Le maintien peut être refusé lorsque le logement est reconnu définitivement inhabitable ou que son évacuation est ordonnée pour utilité publique.',
    '{"article":15,"regle":8,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le maintien peut être refusé lorsque le logement est reconnu définitivement inhabitable ou que son évacuation est ordonnée pour utilité publique.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '15'),
    'ART15_R9_SITUATION_DU_SOUS_LOCATAIRE',
    'Situation du sous-locataire',
    'Le droit du sous-locataire dépend du droit au maintien du locataire principal.',
    '{"article":15,"regle":9,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le droit du sous-locataire dépend du droit au maintien du locataire principal.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 16 : La fin d’un bail à durée déterminée
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('16', 'La fin d’un bail à durée déterminée',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_16',
        'Rester après la date de fin sans opposition du bailleur peut transformer le bail.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '16'),
    'ART16_R1_FIN_AUTOMATIQUE',
    'Fin automatique',
    'Le bail à durée déterminée prend fin automatiquement à la date prévue. Un congé préalable n’est normalement pas nécessaire.',
    '{"article":16,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le bail à durée déterminée prend fin automatiquement à la date prévue. Un congé préalable n’est normalement pas nécessaire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '16'),
    'ART16_R2_EXPULSION_EVENTUELLE',
    'Expulsion éventuelle',
    'La fin du contrat et le départ matériel du locataire sont deux événements différents. Une éventuelle expulsion doit respecter la procédure légale.',
    '{"article":16,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La fin du contrat et le départ matériel du locataire sont deux événements différents. Une éventuelle expulsion doit respecter la procédure légale.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '16'),
    'ART16_R3_TRANSFORMATION_DU_BAIL',
    'Transformation du bail',
    'Si le locataire reste après la date de fin et que le bailleur ne s’y oppose pas, le bail devient à durée indéterminée.',
    '{"article":16,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Si le locataire reste après la date de fin et que le bailleur ne s’y oppose pas, le bail devient à durée indéterminée.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 17 : Le départ du locataire
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('17', 'Le départ du locataire',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_17',
        'Il faut vérifier le contrat avant de calculer la date limite du préavis.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '17'),
    'ART17_R1_BAIL_CONCERNE',
    'Bail concerné',
    'L’article concerne le départ du locataire dans un bail à durée indéterminée.',
    '{"article":17,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’article concerne le départ du locataire dans un bail à durée indéterminée.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '17'),
    'ART17_R2_PREAVIS_MINIMUM',
    'Préavis minimum',
    'Le locataire doit prévenir le bailleur au moins un mois avant son départ.',
    '{"article":17,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le locataire doit prévenir le bailleur au moins un mois avant son départ.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '17'),
    'ART17_R3_DELAI_CONTRACTUEL_PLUS_LONG',
    'Délai contractuel plus long',
    'Si le contrat prévoit un délai de préavis plus long, ce délai doit être pris en compte.',
    '{"article":17,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Si le contrat prévoit un délai de préavis plus long, ce délai doit être pris en compte.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '17'),
    'ART17_R4_PREAVIS_NON_RESPECTE',
    'Préavis non respecté',
    'Si le locataire ne respecte pas le délai, il doit payer les loyers correspondant à la période de préavis manquante.',
    '{"article":17,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Si le locataire ne respecte pas le délai, il doit payer les loyers correspondant à la période de préavis manquante.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 18 : Le congé donné par le bailleur
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('18', 'Le congé donné par le bailleur',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_18',
        'Le bailleur doit respecter le délai et la forme du congé.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '18'),
    'ART18_R1_SITUATION_CONCERNEE',
    'Situation concernée',
    'L’article vise le congé donné dans un bail à durée indéterminée à un locataire de bonne foi qui ne bénéficie pas du maintien dans les lieux.',
    '{"article":18,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’article vise le congé donné dans un bail à durée indéterminée à un locataire de bonne foi qui ne bénéficie pas du maintien dans les lieux.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '18'),
    'ART18_R2_PREAVIS_DE_TROIS_MOIS',
    'Préavis de trois mois',
    'Le bailleur doit prévenir le locataire trois mois à l’avance.',
    '{"article":18,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le bailleur doit prévenir le locataire trois mois à l’avance.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '18'),
    'ART18_R3_FORME_OFFICIELLE',
    'Forme officielle',
    'Le congé doit être envoyé : - par lettre recommandée avec accusé de réception ; - ou par exploit d’huissier.',
    '{"article":18,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Le congé doit être envoyé : - par lettre recommandée avec accusé de réception ; - ou par exploit d’huissier.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '18'),
    'ART18_R4_DOMICILE_DU_LOCATAIRE',
    'Domicile du locataire',
    'Le locataire est normalement considéré comme domicilié dans le logement loué, sauf accord contraire clairement prévu.',
    '{"article":18,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le locataire est normalement considéré comme domicilié dans le logement loué, sauf accord contraire clairement prévu.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 19 : Qui peut décider une expulsion ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('19', 'Qui peut décider une expulsion ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_19',
        'L’application peut signaler une situation, mais elle ne décide jamais une expulsion.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '19'),
    'ART19_R1_DECISION_JUDICIAIRE',
    'Décision judiciaire',
    'Une expulsion doit être décidée par un juge.',
    '{"article":19,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Une expulsion doit être décidée par un juge.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '19'),
    'ART19_R2_PERSONNES_CONCERNEES',
    'Personnes concernées',
    'La procédure peut notamment concerner : - un locataire de mauvaise foi ; - un locataire ayant reçu un congé régulier dont le délai est terminé ; - un occupant sans titre.',
    '{"article":19,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La procédure peut notamment concerner : - un locataire de mauvaise foi ; - un locataire ayant reçu un congé régulier dont le délai est terminé ; - un occupant sans titre.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '19'),
    'ART19_R3_CONTESTATION_SERIEUSE',
    'Contestation sérieuse',
    'Une contestation sérieuse peut empêcher l’utilisation immédiate de la procédure de référé.',
    '{"article":19,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Une contestation sérieuse peut empêcher l’utilisation immédiate de la procédure de référé.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '19'),
    'ART19_R4_DELAI_DE_GRACE',
    'Délai de grâce',
    'Le juge peut accorder un délai supplémentaire pour respecter les obligations ou quitter les lieux.',
    '{"article":19,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Le juge peut accorder un délai supplémentaire pour respecter les obligations ou quitter les lieux.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 20 : La procédure spéciale de certains établissements
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('20', 'La procédure spéciale de certains établissements',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_20',
        'Cette procédure ne s’applique pas à tous les bailleurs.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '20'),
    'ART20_R1_ETABLISSEMENT_DESIGNE',
    'Établissement désigné',
    'Seuls les établissements désignés par décret peuvent utiliser cette procédure spéciale.',
    '{"article":20,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Seuls les établissements désignés par décret peuvent utiliser cette procédure spéciale.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '20'),
    'ART20_R2_RETARD_D_AU_MOINS_UN_MOIS',
    'Retard d’au moins un mois',
    'Le locataire doit avoir au moins un mois de retard dans le paiement du loyer.',
    '{"article":20,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le locataire doit avoir au moins un mois de retard dans le paiement du loyer.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '20'),
    'ART20_R3_DEMANDE_AU_TRIBUNAL',
    'Demande au tribunal',
    'L’établissement peut demander au tribunal le paiement de l’arriéré et l’expulsion.',
    '{"article":20,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'L’établissement peut demander au tribunal le paiement de l’arriéré et l’expulsion.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '20'),
    'ART20_R4_SIGNIFICATION_PAR_HUISSIER',
    'Signification par huissier',
    'La décision doit être officiellement signifiée par un huissier.',
    '{"article":20,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'La décision doit être officiellement signifiée par un huissier.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '20'),
    'ART20_R5_OPPOSITION_DANS_LES_HUIT_JOURS',
    'Opposition dans les huit jours',
    'Le locataire peut faire opposition dans les huit jours suivant cette signification.',
    '{"article":20,"regle":5,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le locataire peut faire opposition dans les huit jours suivant cette signification.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '20'),
    'ART20_R6_INFORMATIONS_OBLIGATOIRES',
    'Informations obligatoires',
    'L’acte doit préciser la possibilité d’opposition, sa forme et son délai.',
    '{"article":20,"regle":6,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’acte doit préciser la possibilité d’opposition, sa forme et son délai.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '20'),
    'ART20_R7_DECISION_SUR_L_OPPOSITION',
    'Décision sur l’opposition',
    'Le juge statue sur l’opposition selon la procédure spéciale prévue par l’article.',
    '{"article":20,"regle":7,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Le juge statue sur l’opposition selon la procédure spéciale prévue par l’article.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '20'),
    'ART20_R8_ABSENCE_DE_DELAI_DE_GRACE',
    'Absence de délai de grâce',
    'Si les torts du locataire sont reconnus dans ce régime spécial, le juge ne peut pas lui accorder de délai de grâce.',
    '{"article":20,"regle":8,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Si les torts du locataire sont reconnus dans ce régime spécial, le juge ne peut pas lui accorder de délai de grâce.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 21 : La reprise pour réaliser des travaux
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('21', 'La reprise pour réaliser des travaux',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_21',
        'Les travaux doivent être autorisés et nécessiter réellement l’évacuation.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '21'),
    'ART21_R1_AUTORISATION_OFFICIELLE',
    'Autorisation officielle',
    'Le propriétaire doit avoir obtenu une autorisation de l’autorité compétente.',
    '{"article":21,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le propriétaire doit avoir obtenu une autorisation de l’autorité compétente.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '21'),
    'ART21_R2_TRAVAUX_CONCERNES',
    'Travaux concernés',
    'La reprise peut concerner : - une reconstruction ; - une surélévation ; - une modification importante.',
    '{"article":21,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La reprise peut concerner : - une reconstruction ; - une surélévation ; - une modification importante.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '21'),
    'ART21_R3_EVACUATION_NECESSAIRE',
    'Évacuation nécessaire',
    'Les travaux doivent normalement nécessiter l’évacuation du logement.',
    '{"article":21,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les travaux doivent normalement nécessiter l’évacuation du logement.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 22 : Les conditions de la reprise pour travaux
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('22', 'Les conditions de la reprise pour travaux',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_22',
        'Le délai, la notification, le motif, l’autorisation et le début réel des travaux doivent être contrôlés.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '22'),
    'ART22_R1_PREAVIS_DE_SIX_MOIS',
    'Préavis de six mois',
    'Le propriétaire doit prévenir le locataire six mois à l’avance.',
    '{"article":22,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le propriétaire doit prévenir le locataire six mois à l’avance.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '22'),
    'ART22_R2_NOTIFICATION_PAR_HUISSIER',
    'Notification par huissier',
    'Le préavis doit être transmis par un exploit d’huissier.',
    '{"article":22,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Le préavis doit être transmis par un exploit d’huissier.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '22'),
    'ART22_R3_MOTIFS_PRECIS',
    'Motifs précis',
    'Le document doit expliquer précisément les travaux envisagés.',
    '{"article":22,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le document doit expliquer précisément les travaux envisagés.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '22'),
    'ART22_R4_REFERENCE_DE_L_AUTORISATION',
    'Référence de l’autorisation',
    'Le préavis doit mentionner la décision administrative qui autorise les travaux.',
    '{"article":22,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le préavis doit mentionner la décision administrative qui autorise les travaux.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '22'),
    'ART22_R5_COMMUNICATION_DU_PLAN',
    'Communication du plan',
    'Le locataire peut demander à consulter le plan des travaux autorisés.',
    '{"article":22,"regle":5,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le locataire peut demander à consulter le plan des travaux autorisés.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '22'),
    'ART22_R6_DEBUT_DES_TRAVAUX',
    'Début des travaux',
    'Les travaux doivent commencer au plus tard trois mois après l’évacuation du logement.',
    '{"article":22,"regle":6,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les travaux doivent commencer au plus tard trois mois après l’évacuation du logement.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 23 : Les travaux ne commencent pas
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('23', 'Les travaux ne commencent pas',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_23',
        'Une fausse reprise pour travaux peut entraîner une indemnisation.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '23'),
    'ART23_R1_ABSENCE_DE_COMMENCEMENT',
    'Absence de commencement',
    'Si le propriétaire ne commence pas les travaux dans le délai, il peut devoir indemniser l’ancien locataire.',
    '{"article":23,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Si le propriétaire ne commence pas les travaux dans le délai, il peut devoir indemniser l’ancien locataire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '23'),
    'ART23_R2_MOTIF_VALABLE_ET_IMPREVU',
    'Motif valable et imprévu',
    'Le propriétaire peut présenter une raison valable et imprévue expliquant le retard.',
    '{"article":23,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le propriétaire peut présenter une raison valable et imprévue expliquant le retard.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '23'),
    'ART23_R3_PRESENCE_D_UN_NOUVEL_OCCUPANT',
    'Présence d’un nouvel occupant',
    'Si une nouvelle personne est installée dans le logement, l’indemnité ne peut pas être inférieure à une année de loyer.',
    '{"article":23,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Si une nouvelle personne est installée dans le logement, l’indemnité ne peut pas être inférieure à une année de loyer.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 24 : La reprise pour habiter le logement
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('24', 'La reprise pour habiter le logement',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_24',
        'La reprise doit être réelle et respecter le bénéficiaire, les formalités et les délais.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '24'),
    'ART24_R1_DROIT_DE_REPRENDRE_LE_LOGEMENT',
    'Droit de reprendre le logement',
    'Le propriétaire peut demander à récupérer le logement pour qu’il soit réellement habité par lui-même ou une personne autorisée.',
    '{"article":24,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le propriétaire peut demander à récupérer le logement pour qu’il soit réellement habité par lui-même ou une personne autorisée.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '24'),
    'ART24_R2_PERSONNES_AUTORISEES',
    'Personnes autorisées',
    'La reprise peut être effectuée pour : - le propriétaire ; - son conjoint ; - ses parents ou grands-parents directs ; - ses enfants ou petits-enfants directs ; - les ascendants ou descendants directs de son conjoint. Les frères, sœurs, cousins et amis ne sont pas mentionnés.',
    '{"article":24,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La reprise peut être effectuée pour : - le propriétaire ; - son conjoint ; - ses parents ou grands-parents directs ; - ses enfants ou petits-enfants directs ; - les ascendants ou descendants directs de son conjoint. Les frères, sœurs, cousins et amis ne sont pas mentionnés.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '24'),
    'ART24_R3_PREAVIS_DE_TROIS_MOIS',
    'Préavis de trois mois',
    'Le propriétaire doit prévenir le locataire au moins trois mois avant la reprise.',
    '{"article":24,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le propriétaire doit prévenir le locataire au moins trois mois avant la reprise.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '24'),
    'ART24_R4_NOTIFICATION_PAR_HUISSIER',
    'Notification par huissier',
    'Le préavis doit être transmis par exploit d’huissier.',
    '{"article":24,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Le préavis doit être transmis par exploit d’huissier.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '24'),
    'ART24_R5_MOTIF_OBLIGATOIRE',
    'Motif obligatoire',
    'La notification doit préciser le motif et la personne qui habitera le logement.',
    '{"article":24,"regle":5,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La notification doit préciser le motif et la personne qui habitera le logement.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '24'),
    'ART24_R6_INSTALLATION_DANS_LES_TROIS_MOIS',
    'Installation dans les trois mois',
    'La personne annoncée doit s’installer au plus tard trois mois après le départ du locataire.',
    '{"article":24,"regle":6,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La personne annoncée doit s’installer au plus tard trois mois après le départ du locataire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '24'),
    'ART24_R7_OCCUPATION_PENDANT_AU_MOINS_UN_AN',
    'Occupation pendant au moins un an',
    'La personne annoncée doit normalement habiter le logement pendant au moins une année.',
    '{"article":24,"regle":7,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La personne annoncée doit normalement habiter le logement pendant au moins une année.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '24'),
    'ART24_R8_EVENEMENT_GRAVE_ET_IMPREVU',
    'Événement grave et imprévu',
    'Une occupation plus courte peut être examinée lorsqu’un événement grave et imprévu empêche la continuation de l’occupation.',
    '{"article":24,"regle":8,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Une occupation plus courte peut être examinée lorsqu’un événement grave et imprévu empêche la continuation de l’occupation.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 25 : Les conditions de reprise ne sont pas respectées
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('25', 'Les conditions de reprise ne sont pas respectées',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_25',
        'Une fausse reprise pour habitation peut coûter au moins une année de loyer.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '25'),
    'ART25_R1_INDEMNISATION_DU_LOCATAIRE',
    'Indemnisation du locataire',
    'Si les conditions de l’article 24 ne sont pas respectées, le propriétaire peut devoir indemniser l’ancien locataire.',
    '{"article":25,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Si les conditions de l’article 24 ne sont pas respectées, le propriétaire peut devoir indemniser l’ancien locataire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '25'),
    'ART25_R2_MONTANT_MINIMAL',
    'Montant minimal',
    'L’indemnité ne peut normalement pas être inférieure à une année de loyer.',
    '{"article":25,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’indemnité ne peut normalement pas être inférieure à une année de loyer.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '25'),
    'ART25_R3_EVENEMENT_GRAVE_ET_IMPREVU',
    'Événement grave et imprévu',
    'Le propriétaire peut présenter une raison grave et imprévue, comme le décès du bénéficiaire.',
    '{"article":25,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le propriétaire peut présenter une raison grave et imprévue, comme le décès du bénéficiaire.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 26 : Où et comment payer le loyer ?
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('26', 'Où et comment payer le loyer ?',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_26',
        'Le contrat doit préciser clairement le mode et le lieu de paiement.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '26'),
    'ART26_R1_PAIEMENT_PAR_DEFAUT',
    'Paiement par défaut',
    'Par défaut, le loyer est quérable : le bailleur vient normalement le percevoir au lieu prévu.',
    '{"article":26,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Par défaut, le loyer est quérable : le bailleur vient normalement le percevoir au lieu prévu.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '26'),
    'ART26_R2_ACCORD_DIFFERENT',
    'Accord différent',
    'Le contrat peut prévoir un autre mode de paiement et une adresse déterminée.',
    '{"article":26,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le contrat peut prévoir un autre mode de paiement et une adresse déterminée.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 27 : Le refus de louer
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('27', 'Le refus de louer',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_27',
        'Cet article ne doit pas être automatisé avant vérification d’une source fiable.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '27'),
    'ART27_R1_REFUS_CONCERNE',
    'Refus concerné',
    'Le texte prévoit des conséquences pour un certain refus de louer un logement vacant.',
    '{"article":27,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le texte prévoit des conséquences pour un certain refus de louer un logement vacant.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '27'),
    'ART27_R2_LOCATION_SI_ELLE_RESTE_POSSIBLE',
    'Location si elle reste possible',
    'Le bailleur reconnu responsable peut devoir consentir la location si cela reste possible.',
    '{"article":27,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le bailleur reconnu responsable peut devoir consentir la location si cela reste possible.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '27'),
    'ART27_R3_DOMMAGES_INTERETS',
    'Dommages-intérêts',
    'Le candidat peut éventuellement recevoir des dommages-intérêts.',
    '{"article":27,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Le candidat peut éventuellement recevoir des dommages-intérêts.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '27'),
    'ART27_R4_TEXTE_AMBIGU',
    'Texte ambigu',
    'Une partie de l’article est mal reproduite dans le PDF. Sa signification exacte doit être vérifiée avant toute programmation.',
    '{"article":27,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Une partie de l’article est mal reproduite dans le PDF. Sa signification exacte doit être vérifiée avant toute programmation.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 28 : Les règles obligatoires
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('28', 'Les règles obligatoires',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_28',
        'L’accord des deux parties ne rend pas légale une clause interdite.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '28'),
    'ART28_R1_REGLES_D_ORDRE_PUBLIC',
    'Règles d’ordre public',
    'Les protections de l’ordonnance sont obligatoires.',
    '{"article":28,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les protections de l’ordonnance sont obligatoires.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '28'),
    'ART28_R2_CLAUSE_CONTRAIRE',
    'Clause contraire',
    'Une clause qui supprime une protection obligatoire peut être considérée comme nulle.',
    '{"article":28,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Une clause qui supprime une protection obligatoire peut être considérée comme nulle.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '28'),
    'ART28_R3_ACCORD_INSUFFISANT',
    'Accord insuffisant',
    'Le bailleur et le locataire ne peuvent pas contourner une règle obligatoire simplement parce qu’ils sont d’accord.',
    '{"article":28,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Le bailleur et le locataire ne peuvent pas contourner une règle obligatoire simplement parce qu’ils sont d’accord.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 29 : Les violations punies
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('29', 'Les violations punies',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_29',
        'Le système informe et signale ; seul le juge prononce une sanction pénale.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '29'),
    'ART29_R1_AUGMENTATION_ILLEGALE',
    'Augmentation illégale',
    'Une augmentation illégale du loyer pratiquée volontairement peut être sanctionnée.',
    '{"article":29,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Une augmentation illégale du loyer pratiquée volontairement peut être sanctionnée.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '29'),
    'ART29_R2_CHARGES_INDUES',
    'Charges indues',
    'Demander des charges injustifiées peut être sanctionné.',
    '{"article":29,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Demander des charges injustifiées peut être sanctionné.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '29'),
    'ART29_R3_REPRISE_ILLEGALE',
    'Reprise illégale',
    'Utiliser illégalement le droit de reprise peut être sanctionné.',
    '{"article":29,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Utiliser illégalement le droit de reprise peut être sanctionné.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '29'),
    'ART29_R4_REFUS_DE_LOUER',
    'Refus de louer',
    'Le refus prévu à l’article 27 peut être sanctionné, mais sa portée doit être clarifiée.',
    '{"article":29,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Le refus prévu à l’article 27 peut être sanctionné, mais sa portée doit être clarifiée.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '29'),
    'ART29_R5_PEINES_PREVUES',
    'Peines prévues',
    'Le texte prévoit une peine d’emprisonnement et une amende, ou l’une de ces deux peines. Les montants et leur actualité doivent être vérifiés.',
    '{"article":29,"regle":5,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Le texte prévoit une peine d’emprisonnement et une amende, ou l’une de ces deux peines. Les montants et leur actualité doivent être vérifiés.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '29'),
    'ART29_R6_DECISION_JUDICIAIRE',
    'Décision judiciaire',
    'Seul un juge peut prononcer une sanction pénale.',
    '{"article":29,"regle":6,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Seul un juge peut prononcer une sanction pénale.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 30 : La rémunération d’un intermédiaire
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('30', 'La rémunération d’un intermédiaire',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_30',
        'La commission de l’intermédiaire possède un plafond légal.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '30'),
    'ART30_R1_PLAFOND_DE_LA_REMUNERATION',
    'Plafond de la rémunération',
    'Un intermédiaire ne doit pas demander une rémunération supérieure à quinze jours de loyer au taux légal.',
    '{"article":30,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Un intermédiaire ne doit pas demander une rémunération supérieure à quinze jours de loyer au taux légal.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '30'),
    'ART30_R2_SANCTION',
    'Sanction',
    'L’intermédiaire peut être exposé aux sanctions prévues par l’article 29.',
    '{"article":30,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'L’intermédiaire peut être exposé aux sanctions prévues par l’article 29.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '30'),
    'ART30_R3_CALCUL_A_PRECISER',
    'Calcul à préciser',
    'La méthode exacte utilisée pour calculer quinze jours de loyer doit être définie avant la programmation.',
    '{"article":30,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'La méthode exacte utilisée pour calculer quinze jours de loyer doit être définie avant la programmation.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 31 : La première augmentation lors de l’application
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('31', 'La première augmentation lors de l’application',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_31',
        'Cette règle organisait l’entrée en application de l’ordonnance en 1962.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '31'),
    'ART31_R1_LIMITE_HISTORIQUE_DE_20',
    'Limite historique de 20 %',
    'Pendant la première année d’application de l’ordonnance, certaines augmentations étaient limitées à 20 %.',
    '{"article":31,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Pendant la première année d’application de l’ordonnance, certaines augmentations étaient limitées à 20 %.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '31'),
    'ART31_R2_ATTENTE_D_UN_DECRET',
    'Attente d’un décret',
    'Certaines premières augmentations devaient attendre la publication du décret prévu par l’article 3.',
    '{"article":31,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Certaines premières augmentations devaient attendre la publication du décret prévu par l’article 3.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '31'),
    'ART31_R3_AUGMENTATION_IMMEDIATE_EXCEPTIONNELLE',
    'Augmentation immédiate exceptionnelle',
    'Après la publication, une augmentation immédiate pouvait dépendre de la capacité du locataire à payer sans difficulté majeure.',
    '{"article":31,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Après la publication, une augmentation immédiate pouvait dépendre de la capacité du locataire à payer sans difficulté majeure.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '31'),
    'ART31_R4_PAS_DE_PLAFOND_ANNUEL_PERMANENT',
    'Pas de plafond annuel permanent',
    'Cette règle historique ne signifie pas que chaque bail actuel possède automatiquement une augmentation annuelle maximale de 20 %.',
    '{"article":31,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Cette règle historique ne signifie pas que chaque bail actuel possède automatiquement une augmentation annuelle maximale de 20 %.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 32 : Les contrats déjà existants
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('32', 'Les contrats déjà existants',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_32',
        'Cet article organisait le passage entre l’ancien et le nouveau régime.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '32'),
    'ART32_R1_BAUX_DEJA_EN_COURS',
    'Baux déjà en cours',
    'L’ordonnance s’est appliquée aux baux qui existaient déjà lors de son entrée en vigueur.',
    '{"article":32,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'L’ordonnance s’est appliquée aux baux qui existaient déjà lors de son entrée en vigueur.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '32'),
    'ART32_R2_PROCEDURES_DEJA_ENGAGEES',
    'Procédures déjà engagées',
    'Elle s’est également appliquée aux affaires judiciaires déjà commencées.',
    '{"article":32,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Elle s’est également appliquée aux affaires judiciaires déjà commencées.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '32'),
    'ART32_R3_VALIDITE_DES_ANCIENS_ACTES',
    'Validité des anciens actes',
    'Les actes régulièrement réalisés selon les anciennes formes sont restés valables.',
    '{"article":32,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les actes régulièrement réalisés selon les anciennes formes sont restés valables.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 33 : La procédure judiciaire temporaire
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('33', 'La procédure judiciaire temporaire',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_33',
        'Cette règle avait une fonction temporaire.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '33'),
    'ART33_R1_REGIME_TEMPORAIRE',
    'Régime temporaire',
    'L’article indiquait les procédures judiciaires utilisables en attendant l’entrée en vigueur d’un nouveau Code de procédure civile.',
    '{"article":33,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'L’article indiquait les procédures judiciaires utilisables en attendant l’entrée en vigueur d’un nouveau Code de procédure civile.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '33'),
    'ART33_R2_VERIFICATION_ACTUELLE',
    'Vérification actuelle',
    'Ces anciennes possibilités ne doivent pas être proposées aujourd’hui sans vérifier le droit actuel.',
    '{"article":33,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Ces anciennes possibilités ne doivent pas être proposées aujourd’hui sans vérifier le droit actuel.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 34 : La suppression des anciens textes
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('34', 'La suppression des anciens textes',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_34',
        'Les anciens textes incompatibles ne devaient plus être appliqués.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '34'),
    'ART34_R1_ABROGATION_GENERALE',
    'Abrogation générale',
    'Les dispositions contraires à l’ordonnance ont été supprimées.',
    '{"article":34,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'INFORMATION',
    'Les dispositions contraires à l’ordonnance ont été supprimées.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '34'),
    'ART34_R2_TEXTES_EXPRESSEMENT_SUPPRIMES',
    'Textes expressément supprimés',
    'Plusieurs décrets et arrêtés anciens sont directement mentionnés comme abrogés.',
    '{"article":34,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Plusieurs décrets et arrêtés anciens sont directement mentionnés comme abrogés.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '34'),
    'ART34_R3_TEXTES_LIES',
    'Textes liés',
    'Les textes qui modifiaient ou mettaient en application les anciens textes sont également concernés.',
    '{"article":34,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'A_PROGRAMMER',
    'Les textes qui modifiaient ou mettaient en application les anciens textes sont également concernés.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '34'),
    'ART34_R4_UTILITE_HISTORIQUE',
    'Utilité historique',
    'La liste de ces textes sert principalement à comprendre l’évolution du droit.',
    '{"article":34,"regle":4,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'La liste de ces textes sert principalement à comprendre l’évolution du droit.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

-- Article 35 : La publication des décrets
INSERT INTO article_juridique (numero, titre, texte_original, explication_simple)
VALUES ('35', 'La publication des décrets',
        'TEXTE_ORIGINAL_A_COMPLETER_DEPUIS_ORDONNANCE_62_100_ARTICLE_35',
        'L’ordonnance seule ne permet pas de programmer tous les calculs.')
ON CONFLICT (numero) DO UPDATE SET
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '35'),
    'ART35_R1_DATE_HISTORIQUE',
    'Date historique',
    'Les décrets nécessaires à l’application de l’ordonnance devaient être publiés avant le 1er mars 1963.',
    '{"article":35,"regle":1,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Les décrets nécessaires à l’application de l’ordonnance devaient être publiés avant le 1er mars 1963.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '35'),
    'ART35_R2_RECHERCHE_COMPLEMENTAIRE',
    'Recherche complémentaire',
    'Il faut retrouver les décrets qui ont réellement été publiés.',
    '{"article":35,"regle":2,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'Il faut retrouver les décrets qui ont réellement été publiés.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

INSERT INTO regle
    (article_id, code, titre, explication_simple, condition_json, controle, consequence, bloquante)
VALUES (
    (SELECT id FROM article_juridique WHERE numero = '35'),
    'ART35_R3_LIMITE_DU_MOTEUR',
    'Limite du moteur',
    'L’application ne doit pas inventer les taux et méthodes de calcul qui dépendent de ces décrets.',
    '{"article":35,"regle":3,"statut":"A_STRUCTURER"}'::jsonb,
    'CONTROLE_HUMAIN',
    'L’application ne doit pas inventer les taux et méthodes de calcul qui dépendent de ces décrets.',
    FALSE
)
ON CONFLICT (code) DO UPDATE SET
    article_id = EXCLUDED.article_id,
    titre = EXCLUDED.titre,
    explication_simple = EXCLUDED.explication_simple,
    condition_json = EXCLUDED.condition_json,
    controle = EXCLUDED.controle,
    consequence = EXCLUDED.consequence;

COMMIT;

-- Vérification après exécution :
SELECT COUNT(*) AS nombre_articles FROM article_juridique;
SELECT COUNT(*) AS nombre_regles FROM regle;

-- Résultat attendu : 35 articles et 133 règles.
