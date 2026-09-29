-- Comptes existants : la date sera demandée lors de la prochaine candidature.
ALTER TABLE utilisateur ADD COLUMN IF NOT EXISTS date_naissance DATE;
