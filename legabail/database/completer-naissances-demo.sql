-- Mise a jour ciblee d'une base contenant deja les comptes de demonstration.
-- Ne modifie ni les contrats, ni les autres comptes, ni les dates deja renseignees.
BEGIN;
ALTER TABLE utilisateur ADD COLUMN IF NOT EXISTS date_naissance DATE;

-- Dates fictives : seuls les comptes de demonstration sans date sont completes.
UPDATE utilisateur AS u
SET date_naissance = v.date_naissance
FROM (VALUES
    ('aina.rabe@demo.legabail.mg', DATE '2001-05-14'),
    ('tahina.rasoanaivo@demo.legabail.mg', DATE '1996-11-03'),
    ('soa.andria@demo.legabail.mg', DATE '2004-02-20'),
    ('tiana.ramanana@demo.legabail.mg', DATE '1988-07-09')
) AS v(email, date_naissance)
WHERE u.email = v.email AND u.role = 'LOCATAIRE' AND u.date_naissance IS NULL;

COMMIT;

-- Ages actuels utilisables dans le filtre "Mes contrats".
SELECT prenom, nom, date_naissance,
       EXTRACT(YEAR FROM age(CURRENT_DATE, date_naissance)) AS age_actuel
FROM utilisateur
WHERE email IN ('aina.rabe@demo.legabail.mg', 'tahina.rasoanaivo@demo.legabail.mg',
                'soa.andria@demo.legabail.mg', 'tiana.ramanana@demo.legabail.mg');
