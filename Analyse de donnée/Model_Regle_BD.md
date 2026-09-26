

# Exemple complet : mettre l’article 6 dans une base de données

## 1. La règle juridique

> Pour un logement vide loué au mois, la caution et l’avance réunies ne peuvent pas dépasser deux mois de loyer.

## 2. Les informations nécessaires

| Information | Exemple | Fournie par |
|---|---:|---|
| Type du logement | `NU` | Bailleur |
| Type de location | `AU_MOIS` | Bailleur |
| Loyer mensuel | `300000` | Bailleur |
| Caution | `600000` | Bailleur |
| Avance | `300000` | Bailleur |
| Accord du locataire | `NON_ENCORE` | Locataire |

## 3. La règle enregistrée

Une table `regles_juridiques` peut contenir :

| Colonne | Valeur de l’article 6 |
|---|---|
| `code` | `A06_02` |
| `article` | `6` |
| `titre` | `Plafond de la caution et de l’avance` |
| `explication_simple` | `La caution et l’avance réunies ne dépassent pas deux mois de loyer.` |
| `niveau` | `BLOQUANT` |
| `statut_validation` | `A_VALIDER_PAR_JURISTE` |
| `source` | `Ordonnance 62-100, article 6` |

Exemple SQL :

```sql
CREATE TABLE regles_juridiques (
    id INTEGER PRIMARY KEY,
    code VARCHAR(30) UNIQUE NOT NULL,
    article INTEGER NOT NULL,
    titre VARCHAR(200) NOT NULL,
    explication_simple TEXT NOT NULL,
    niveau VARCHAR(30) NOT NULL,
    statut_validation VARCHAR(50) NOT NULL,
    source VARCHAR(255) NOT NULL
);
```

Puis la règle :

```sql
INSERT INTO regles_juridiques (
    code,
    article,
    titre,
    explication_simple,
    niveau,
    statut_validation,
    source
)
VALUES (
    'A06_02',
    6,
    'Plafond de la caution et de l''avance',
    'La caution et l''avance réunies ne dépassent pas deux mois de loyer.',
    'BLOQUANT',
    'A_VALIDER_PAR_JURISTE',
    'Ordonnance 62-100, article 6'
);
```

## 4. Les conditions d’application

La règle possède deux conditions :

```json
{
  "toutes_les_conditions": [
    {
      "champ": "type_logement",
      "operateur": "EGAL",
      "valeur": "NU"
    },
    {
      "champ": "type_location",
      "operateur": "EGAL",
      "valeur": "AU_MOIS"
    }
  ]
}
```

Cela veut dire :

```text
SI le logement est NU
ET SI la location est faite AU MOIS
ALORS la règle A06_02 doit être vérifiée.
```

## 5. Le calcul à effectuer

```text
maximum autorisé = loyer mensuel × 2
total demandé = caution + avance
```

Avec les réponses de l’exemple :

```text
maximum = 300 000 × 2
maximum = 600 000 Ar

total = 600 000 + 300 000
total = 900 000 Ar
```

Le total dépasse le maximum de 300 000 Ar.

## 6. Le résultat enregistré

Une table `resultats_verification` peut conserver le résultat :

```sql
CREATE TABLE resultats_verification (
    id INTEGER PRIMARY KEY,
    dossier_id INTEGER NOT NULL,
    regle_id INTEGER NOT NULL,
    statut VARCHAR(30) NOT NULL,
    valeur_calculee DECIMAL(15, 2),
    valeur_maximale DECIMAL(15, 2),
    message TEXT,
    date_verification TIMESTAMP NOT NULL
);
```

Exemple de résultat :

```json
{
  "dossier_id": 25,
  "regle": "A06_02",
  "statut": "BLOQUE",
  "valeur_calculee": 900000,
  "valeur_maximale": 600000,
  "message": "La caution et l’avance dépassent la limite de deux mois de loyer."
}
```

## 7. La logique du programme

```text
Quand le bailleur modifie le loyer, la caution ou l’avance :

1. Charger les réponses du dossier.
2. Vérifier si le logement est nu.
3. Vérifier si la location est faite au mois.
4. Calculer caution + avance.
5. Calculer loyer × 2.
6. Comparer les deux résultats.
7. Accepter ou bloquer la réponse.
8. Afficher une explication simple.
```

Pseudo-code :

```python
def verifier_article_6(dossier):
    if dossier.type_logement != "NU":
        return {
            "statut": "NON_APPLICABLE"
        }

    if dossier.type_location != "AU_MOIS":
        return {
            "statut": "AUTRE_REGLE_A_VERIFIER"
        }

    total_demande = dossier.caution + dossier.avance
    maximum = dossier.loyer_mensuel * 2

    if total_demande > maximum:
        return {
            "statut": "BLOQUE",
            "regle": "A06_02",
            "total_demande": total_demande,
            "maximum": maximum,
            "message": (
                "Pour ce logement vide loué au mois, "
                "la caution et l’avance réunies ne peuvent "
                "pas dépasser deux mois de loyer."
            )
        }

    return {
        "statut": "VALIDE",
        "regle": "A06_02",
        "total_demande": total_demande,
        "maximum": maximum
    }
```

## 8. Effet sur le formulaire

Le bailleur saisit :

```text
Loyer : 300 000 Ar
Caution : 600 000 Ar
Avance : 300 000 Ar
```

L’application affiche :

> Montant refusé  
> La caution et l’avance atteignent 900 000 Ar.  
> Pour ce logement, le maximum est de 600 000 Ar.  
> Référence : article 6.

Le bailleur doit corriger les montants avant de continuer.

Le locataire ne reçoit donc jamais une proposition contenant ce montant bloqué.
D’après le `.md`, il ne faut pas séparer les articles de manière totalement stricte : certains concernent uniquement le bailleur, certains principalement le locataire, et plusieurs concernent les deux.
