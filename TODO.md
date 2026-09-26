# TODO 1 — Analyse et conception

## Personne 1 — Mickaella

- [x] Phase 1 — Extraire et classer les règles
- [ ] Phase 2 — Transformer les lois en règles structurées
- [ ] Phase 3 — Déterminer les informations nécessaires
- [ ] Phase 9 — Préparer les clauses du contrat

## Personne 2

- [ ] Phase 4 — Répartir les informations entre les acteurs
- [ ] Phase 5 — Construire le formulaire du bailleur
- [ ] Phase 6 — Construire le formulaire du locataire

## Personne 3

- [ ] Phase 7 — Créer la matrice de décision
- [ ] Phase 8 — Définir les conditions une par une
- [ ] Créer les scénarios de test

## Toute l’équipe

- [ ] Vérifier la cohérence entre les règles, les formulaires et les conditions
- [ ] Valider le résultat final
- [ ] Préparer séparément le TODO du développement complet




Oui, **ton ordre est globalement correct**. Tu viens de définir la vraie méthode de construction du projet. Mais je vais légèrement l’améliorer pour éviter de mélanger les textes juridiques, les formulaires et le code.

## Ordre recommandé

### Phase 1 — Extraire et classer toutes les règles

À partir du PDF, nous relevons chaque article, puis nous séparons :

- les règles applicables au contrat ;
- les règles de calcul ;
- les obligations du bailleur ;
- les obligations du locataire ;
- les autorisations ;
- les interdictions ;
- les délais et préavis ;
- les règles nécessitant un accord ;
- les règles historiques ou transitoires ;
- les règles nécessitant un décret complémentaire ;
- les règles concernant seulement les litiges ou sanctions.

Attention : un article peut contenir plusieurs règles. Par exemple, l’article 24 contient au moins trois conditions distinctes :

1. préavis de trois mois ;
2. installation du bénéficiaire dans les trois mois ;
3. occupation pendant au moins un an.

Nous aurons donc probablement **plus de 35 règles**, même si le document ne contient que 35 articles.

### Phase 2 — Transformer les lois en règles structurées

Chaque règle devra avoir une structure précise :

```json
{
  "id": "ART24_REPRISE_OCCUPATION",
  "article": 24,
  "titre": "Reprise pour occupation personnelle",
  "explication_simple": "Le propriétaire peut reprendre le logement pour lui-même ou certains membres de sa famille.",
  "conditions_application": [],
  "donnees_necessaires": [],
  "controle": null,
  "consequence": null,
  "niveau": "OBLIGATOIRE",
  "public_concerne": ["bailleur", "locataire"],
  "statut_juridique": "A_VALIDER_PAR_JURISTE"
}
```

À cette étape, nous ne créons pas encore les écrans. Nous créons le **catalogue juridique central**.

### Phase 3 — Déterminer toutes les informations nécessaires

Pour chaque règle, nous posons la question :

> Quelles informations l’application doit-elle connaître pour savoir si cette règle s’applique ?

Exemple, pour vérifier l’article 6 :

- logement nu ou meublé ;
- paiement mensuel ou autre ;
- montant du loyer ;
- montant de la caution ;
- montant de l’avance.

Cela produit une liste complète de données à collecter.

### Phase 4 — Répartir les questions entre les acteurs

Nous décidons ensuite qui peut fournir chaque information.

| Information | Bailleur | Locataire | Système |
|---|---:|---:|---:|
| Type de logement | Oui | Vérifie | Non |
| Montant du loyer | Propose | Accepte/négocie | Contrôle |
| Identité du locataire | Non | Oui | Vérifie |
| Usage du logement | Propose | Confirme | Compare |
| Montant maximal autorisé | Non | Non | Calcule |
| Sous-location | Propose | Accepte/négocie | Vérifie |
| Date de fin | Propose | Accepte | Contrôle |

Ainsi, nous construisons deux formulaires différents, mais liés au même dossier.

### Phase 5 — Construire le formulaire du bailleur

Les questions seront ordonnées selon les dépendances.

Exemple :

```text
Le logement est-il nu ou meublé ?
        ↓
Si meublé : afficher l’inventaire et les questions sur le mobilier
        ↓
Si nu : activer le contrôle de la caution prévu par l’article 6
```

Le formulaire ne doit pas tout afficher immédiatement. Il doit ouvrir les bonnes sections selon les réponses précédentes.

### Phase 6 — Construire le formulaire du locataire

Le formulaire du locataire est généré à partir :

- des réponses du bailleur ;
- des règles applicables ;
- des informations manquantes ;
- des éléments nécessitant son accord ;
- des éventuelles incompatibilités.

Il contiendra trois types d’éléments :

- **champs modifiables** : identité, profession, documents ;
- **conditions en lecture seule** : logement, montant proposé, durée ;
- **décisions** : accepter, refuser ou demander une modification.

### Phase 7 — Créer une matrice de décision

Pour chaque réponse, nous décrivons ses conséquences.

| Événement | Condition | Règle | Résultat |
|---|---|---|---|
| Type = nu | Paiement mensuel | Article 6 | Activer plafond caution + avance |
| Type = meublé | Mobilier fourni | Article 7 | Contrôler majoration |
| Bail = durée déterminée | Date de fin valide | Article 16 | Générer clause d’échéance |
| Sous-location demandée | Autorisation nécessaire | Article 10 | Demander décision au bailleur |
| Reprise pour travaux | Autorisation administrative | Articles 21–22 | Préavis et justificatif requis |

Cette matrice deviendra ensuite la base du moteur de règles.

### Phase 8 — Définir les conditions une par une

Pour chaque champ juridiquement important :

```text
QUAND une réponse change
SI les conditions d’application sont réunies
ALORS exécuter les contrôles
PUIS produire les conséquences
```

Exemple :

```text
QUAND caution ou avance change
SI logement = nu ET paiement = mensuel
ALORS vérifier caution + avance ≤ 2 × loyer
SI dépassement
ALORS bloquer et expliquer l’article 6
```

Toutes les actions de l’utilisateur ne déclencheront pas forcément une règle juridique. Cliquer sur « suivant » ou modifier son numéro de téléphone ne demande pas de contrôle juridique. En revanche, toute modification d’une **donnée juridique dépendante** doit relancer les règles concernées.

### Phase 9 — Préparer les clauses du contrat

Chaque combinaison validée doit être liée à une clause :

```text
Réponse + règle validée
        ↓
Clause correspondante
        ↓
Contrat provisoire
```

Il faudra créer :

- des clauses obligatoires ;
- des clauses conditionnelles ;
- des clauses alternatives ;
- des annexes ;
- des explications simples affichées à l’écran.

### Phase 10 — Développer le backend

Seulement après cette modélisation, nous choisirons la technologie et développerons :

- utilisateurs et rôles ;
- biens immobiliers ;
- dossiers de location ;
- réponses aux formulaires ;
- moteur de règles ;
- négociations ;
- versions du contrat ;
- génération PDF ;
- signature ;
- historique et audit.

## Les cinq ensembles de données à ne pas mélanger

Il faudra probablement conserver séparément :

```text
1. Textes juridiques originaux
2. Règles exécutables
3. Questions des formulaires
4. Explications simplifiées
5. Clauses du contrat
```

Une même règle pourra être liée à plusieurs questions et à plusieurs clauses.

## Ton TODO principal

Ton plan final peut donc être résumé ainsi :

```text
[ ] Extraire les articles
[ ] Découper les articles en règles unitaires
[ ] Classer et vérifier juridiquement les règles
[ ] Identifier les données nécessaires à chaque règle
[ ] Répartir ces données entre bailleur, locataire et système
[ ] Concevoir le formulaire dynamique du bailleur
[ ] Concevoir le formulaire dynamique du locataire
[ ] Construire la matrice événements → règles → conséquences
[ ] Définir les clauses associées
[ ] Créer les scénarios de test
[ ] Choisir l’architecture technique
[ ] Développer le backend et le frontend
```

