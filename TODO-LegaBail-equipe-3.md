# TODO — Développement de LegaBail

## Technologies retenues

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- HTML, CSS et JavaScript

L'application utilise des contrôleurs Spring MVC et des pages Thymeleaf. Elle n'utilise pas d'API REST ni d'échanges JSON entre les pages et Java.

```text
Page Thymeleaf
      ↓
Controller MVC
      ↓
Service
      ↓
Repository
      ↓
PostgreSQL
```

---

# Préparation commune

À faire ensemble avant la séparation :

- [ ] Générer le projet Spring Boot
- [ ] Ajouter Spring Web
- [ ] Ajouter Thymeleaf
- [ ] Ajouter Spring Data JPA
- [ ] Ajouter Validation
- [ ] Ajouter le pilote PostgreSQL
- [ ] Créer la base de données `legabail`
- [ ] Exécuter `schema-legabail-minimal.sql`
- [ ] Configurer `application.properties`
- [ ] Créer le dépôt Git
- [ ] Créer la branche `feature/regles`
- [ ] Créer la branche `feature/bailleur`
- [ ] Créer la branche `feature/locataire-contrat`
- [ ] Placer les fichiers HTML dans `src/main/resources/templates`
- [ ] Placer les fichiers CSS et JavaScript dans `src/main/resources/static`

Structure commune :

```text
src/main/java/com/legatech/legabail/
├── controller/
├── entity/
├── form/
├── repository/
└── service/

src/main/resources/
├── templates/
└── static/
    ├── css/
    └── js/
```

Après cette préparation, chaque personne travaille sur sa branche sans attendre les autres.

---

# Personne 1 — Mickaella : articles et moteur de règles

## Objectif

Créer la partie qui vérifie juridiquement les réponses du bailleur et du locataire.

## Tables prises en charge

- `article_juridique`
- `regle`

## Entités et repositories

- [ ] Créer `ArticleJuridique`
- [ ] Créer `Regle`
- [ ] Créer `ArticleJuridiqueRepository`
- [ ] Créer `RegleRepository`

## Insertion des articles et règles

- [ ] Insérer tous les articles juridiques
- [ ] Insérer le texte original de chaque article
- [ ] Insérer l'explication simple de chaque article
- [ ] Découper les articles en règles unitaires
- [ ] Insérer les règles unitaires
- [ ] Définir les conditions d'application
- [ ] Définir le contrôle de chaque règle
- [ ] Définir la conséquence de chaque règle
- [ ] Indiquer si la règle est bloquante

## Moteur de règles

- [ ] Créer `MoteurReglesService`
- [ ] Créer `ResultatControle`
- [ ] Vérifier le type de logement
- [ ] Vérifier l'usage du logement
- [ ] Vérifier l'ancienneté du bâtiment
- [ ] Vérifier le montant du loyer
- [ ] Vérifier la caution et l'avance
- [ ] Vérifier la majoration du logement meublé
- [ ] Vérifier les dates du contrat
- [ ] Vérifier la sous-location
- [ ] Retourner une explication simple
- [ ] Retourner l'article concerné
- [ ] Indiquer si le résultat est conforme
- [ ] Indiquer si une erreur bloque l'enregistrement

Utilisation attendue :

```java
List<ResultatControle> resultats =
        moteurReglesService.verifierAnnonce(annonceForm);
```

## Pages Thymeleaf

- [ ] Créer `articles.html`
- [ ] Afficher la liste des articles
- [ ] Créer `article-detail.html`
- [ ] Afficher les règles de chaque article

## Contrôleur MVC

- [ ] Créer `ArticleController`
- [ ] Afficher `/articles`
- [ ] Afficher `/articles/{id}`

Exemple :

```java
@GetMapping("/articles")
public String afficherArticles(Model model) {
    model.addAttribute("articles", articleService.findAll());
    return "articles";
}
```

## Tests

- [ ] Tester une caution valide
- [ ] Tester une caution invalide
- [ ] Tester un logement nu
- [ ] Tester un logement meublé
- [ ] Tester des dates incorrectes
- [ ] Tester une sous-location interdite
- [ ] Tester une règle non applicable

## Livrable

Un moteur Java capable de recevoir un formulaire et de retourner les règles applicables, les avertissements et les erreurs bloquantes.

---

# Personne 2 — Parcours bailleur et annonces

## Objectif

Permettre au bailleur de créer un compte, enregistrer un bien et publier une annonce.

## Tables prises en charge

- `utilisateur`, pour les bailleurs
- `bien`
- `annonce`

## Entités et repositories

- [ ] Créer `Utilisateur`
- [ ] Créer `Bien`
- [ ] Créer `Annonce`
- [ ] Créer `UtilisateurRepository`
- [ ] Créer `BienRepository`
- [ ] Créer `AnnonceRepository`

## Formulaires Java

- [ ] Créer `BailleurForm`
- [ ] Créer `BienForm`
- [ ] Créer `AnnonceForm`
- [ ] Ajouter les validations `@NotNull`
- [ ] Ajouter les validations `@NotBlank`
- [ ] Ajouter les validations `@Positive`

## Services

- [ ] Créer `UtilisateurService`
- [ ] Créer `BienService`
- [ ] Créer `AnnonceService`
- [ ] Enregistrer un bailleur
- [ ] Enregistrer un bien
- [ ] Enregistrer une annonce en brouillon
- [ ] Modifier une annonce
- [ ] Publier une annonce
- [ ] Archiver une annonce
- [ ] Lister les annonces du bailleur
- [ ] Lister les annonces publiées

## Contrôleurs MVC

- [ ] Créer `BailleurController`
- [ ] Créer `BienController`
- [ ] Créer `AnnonceController`

Routes MVC :

```text
GET  /inscription/bailleur
POST /inscription/bailleur

GET  /bailleur/biens/nouveau
POST /bailleur/biens

GET  /bailleur/annonces/nouvelle
POST /bailleur/annonces
POST /bailleur/annonces/{id}/publier

GET  /espace-bailleur
GET  /annonces
GET  /annonces/{id}
```

## Pages Thymeleaf

- [ ] Transformer le formulaire bailleur en `formulaire-bailleur.html`
- [ ] Créer `espace-bailleur.html`
- [ ] Créer `annonces.html`
- [ ] Créer `annonce-detail.html`
- [ ] Afficher les erreurs Java dans le formulaire
- [ ] Garder les avertissements immédiats en JavaScript
- [ ] Afficher les annonces provenant de PostgreSQL

Exemple :

```html
<form th:action="@{/bailleur/annonces}"
      th:object="${annonceForm}"
      method="post">

    <input th:field="*{loyer}" type="number">
    <button type="submit">Enregistrer</button>
</form>
```

## Travail indépendant

En attendant le moteur de règles, utiliser un contrôle temporaire :

```java
public List<ResultatControle> verifierAnnonce(AnnonceForm form) {
    return List.of();
}
```

Cette méthode sera remplacée par le moteur de Mickaella pendant l'intégration.

## Tests

- [ ] Créer un bailleur
- [ ] Ajouter un bien
- [ ] Créer un logement nu
- [ ] Créer un logement meublé
- [ ] Enregistrer une annonce en brouillon
- [ ] Modifier une annonce
- [ ] Publier une annonce
- [ ] Afficher les annonces publiées
- [ ] Archiver une annonce

## Livrable

Le bailleur peut remplir son formulaire, enregistrer son bien et publier son annonce.

---

# Personne 3 — Locataire, accord et contrat

## Objectif

Permettre au locataire de candidater, négocier, accepter les conditions et signer un contrat.

## Tables prises en charge

- `candidature`
- `proposition`
- `contrat`
- `signature`

La table `utilisateur` est utilisée pour identifier le locataire.

## Entités et repositories

- [x] Créer `Candidature`
- [x] Créer `Proposition`
- [x] Créer `Contrat`
- [x] Créer `Signature`
- [x] Créer `CandidatureRepository`
- [x] Créer `PropositionRepository`
- [x] Créer `ContratRepository`
- [x] Créer `SignatureRepository`

## Formulaires Java

- [x] Créer `LocataireForm`
- [x] Créer `CandidatureForm`
- [x] Créer `PropositionForm`
- [x] Créer `SignatureForm`

## Services

- [x] Créer `CandidatureService`
- [x] Créer `PropositionService`
- [x] Créer `ContratService`
- [x] Créer `SignatureService`

## Parcours locataire

- [x] Enregistrer le locataire
- [x] Enregistrer une candidature
- [x] Accepter les conditions
- [x] Demander une modification
- [x] Créer une nouvelle version de la proposition
- [x] Afficher le statut de la candidature

## Accord des parties

- [x] Enregistrer l'acceptation du bailleur
- [x] Enregistrer l'acceptation du locataire
- [x] Vérifier que les deux acceptent la même proposition
- [x] Bloquer le contrat si une acceptation manque
- [x] Déclencher la génération après le double accord

## Contrat

- [x] Générer le numéro du contrat
- [x] Récupérer les identités
- [x] Récupérer les informations du logement
- [x] Récupérer les conditions acceptées
- [x] Construire le contenu traditionnel du contrat
- [x] Afficher le contrat avec Thymeleaf
- [x] Ajouter l'impression en PDF
- [x] Enregistrer la signature du bailleur
- [x] Enregistrer la signature du locataire
- [x] Passer le contrat à `SIGNE` après les deux signatures

## Contrôleurs MVC

- [x] Créer `LocataireController`
- [x] Créer `CandidatureController`
- [x] Créer `PropositionController`
- [x] Créer `ContratController`
- [x] Créer `SignatureController`

Routes MVC :

```text
GET  /inscription/locataire
POST /inscription/locataire

GET  /annonces/{id}/candidater
POST /annonces/{id}/candidater

GET  /locataire/candidatures
GET  /candidatures/{id}

POST /propositions/{id}/accepter-locataire
POST /propositions/{id}/accepter-bailleur
POST /propositions/{id}/modifier

GET  /contrats/{id}
POST /contrats/{id}/signer
GET  /contrats/{id}/imprimer
```

## Pages Thymeleaf

- [x] Créer `formulaire-locataire.html`
- [x] Créer `espace-locataire.html`
- [x] Créer `candidature-detail.html`
- [x] Créer `negociation.html`
- [x] Transformer le contrat du prototype en `contrat.html`
- [x] Afficher les signatures

## Travail indépendant

En attendant les annonces réelles, utiliser une annonce fictive :

```java
Annonce annonce = new Annonce();
annonce.setId(1L);
annonce.setLoyer(new BigDecimal("750000"));
annonce.setCharges(new BigDecimal("50000"));
```

Cette annonce sera remplacée par une donnée de PostgreSQL pendant l'intégration.

## Tests

- [x] Envoyer une candidature
- [x] Accepter une proposition
- [x] Demander une modification
- [x] Créer une deuxième version
- [x] Tester l'accord du bailleur seulement
- [x] Tester l'accord du locataire seulement
- [x] Tester l'accord des deux parties
- [x] Vérifier que le contrat n'est pas généré sans double accord
- [x] Générer le contrat
- [x] Signer comme bailleur
- [x] Signer comme locataire

## Livrable

Le locataire peut candidater, négocier, accepter et signer un contrat.

---

# Règles pour travailler indépendamment

Chaque personne fournit :

- [ ] Ses entités Java
- [ ] Ses repositories
- [ ] Ses services
- [ ] Ses contrôleurs MVC
- [ ] Ses classes de formulaire
- [ ] Ses pages Thymeleaf
- [ ] Ses tests
- [ ] Un petit fichier `README.md` expliquant ses routes

Répartition des packages :

```text
Mickaella  → article, regle, controle
Personne 2 → utilisateur, bien, annonce
Personne 3 → candidature, proposition, contrat, signature
```

La personne 3 peut temporairement utiliser un simple `Long locataireId` pour ne pas attendre la classe `Utilisateur` de la personne 2.

---

# Intégration finale — Toute l'équipe

- [ ] Fusionner les trois branches
- [ ] Retirer les données fictives
- [ ] Brancher le formulaire bailleur au moteur de règles
- [ ] Relier les candidatures aux véritables annonces
- [ ] Vérifier les relations JPA
- [ ] Vérifier les validations des formulaires
- [ ] Tester une annonce conforme
- [ ] Tester une annonce non conforme
- [ ] Tester une candidature
- [ ] Tester une négociation
- [ ] Tester le double accord
- [ ] Tester la génération du contrat
- [ ] Tester les deux signatures
- [ ] Tester le parcours complet
- [ ] Corriger les conflits Git
- [ ] Préparer les données de démonstration

Parcours final :

```text
Bailleur remplit le formulaire
            ↓
Java vérifie les règles
            ↓
Annonce publiée
            ↓
Locataire envoie sa candidature
            ↓
Négociation éventuelle
            ↓
Accord des deux parties
            ↓
Contrat généré
            ↓
Signatures
```

UPDATE bien
SET image_url = '/images/biens/image copy 2.png'
WHERE id = 13;

