-- Comptes existants : la date sera demandée lors de la prochaine candidature.
ALTER TABLE utilisateur ADD COLUMN IF NOT EXISTS date_naissance DATE;

-- Bloquantes vraiment fortes
UPDATE regle
SET bloquante = TRUE
WHERE id IN (20, 22, 31);

-- Alertes / informations, pas blocage dur
UPDATE regle
SET bloquante = true
WHERE id IN (24, 54, 56);

-- \i /home/carlos/Documents/roby/S5/DDAE/project/ContratDeBail/legabail/database/ajout-date-naissance.sql;
-- \i /home/carlos/Documents/roby/S5/DDAE/project/ContratDeBail/legabail/database/completer-naissances-demo.sql;
-- \i /home/carlos/Documents/roby/S5/DDAE/project/ContratDeBail/legabail/database/donnees-demonstration-completes.sql;
-- \i /home/carlos/Documents/roby/S5/DDAE/project/ContratDeBail/legabail/database/legabail-articles-regles-complet.sql;
-- \i /home/carlos/Documents/roby/S5/DDAE/project/ContratDeBail/legabail/database/schema-legabail.sql;
-- \i /home/carlos/Documents/roby/S5/DDAE/project/ContratDeBail/legabail/database/ajout-details-bien.sql