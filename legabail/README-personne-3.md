# Personne 3 : locataire, accord et contrat

Parcours Spring MVC / Thymeleaf, sans API REST. Les candidatures utilisent les
annonces, biens et utilisateurs existants, sans annonce fictive dans le code.
Les quatre nouvelles entites correspondent aux tables du schema PostgreSQL fourni.

## Demarrage

Java 21 minimum et PostgreSQL avec une base `legabail` sont requis.
Les variables `DB_URL`, `DB_USERNAME` et `DB_PASSWORD` configurent la connexion.
Depuis ce dossier :

```sh
./mvnw spring-boot:run
```

Ouvrir http://localhost:8080/inscription/locataire puis `/annonces`.
Les pages d'annonces, initialement vides, donnent acces au formulaire de candidature.
Un bailleur deja inscrit accede aux dossiers dans `/bailleur/candidatures`.
Utiliser deux sessions de navigateur distinctes pour tester les deux parties.
Comme le parcours bailleur existant, l'inscription ouvre la session ; la connexion
des comptes existants reste a integrer au module utilisateur de l'equipe.

## Routes

| Methode | Route | Fonction |
| --- | --- | --- |
| GET, POST | `/inscription/locataire` | Inscription, validation et ouverture de session |
| GET, POST | `/annonces/{id}/candidater` | Formulaire et envoi de candidature |
| GET | `/locataire/candidatures` | Dossiers du locataire |
| GET | `/espace-locataire` | Alias de l'espace locataire |
| GET | `/bailleur/candidatures` | Dossiers recus par le bailleur |
| GET | `/candidatures/{id}` | Conditions, statut, historique et contrat |
| GET | `/candidatures/{id}/negociation` | Formulaire de nouvelle version |
| POST | `/propositions/{id}/modifier` | Nouvelle version sans accords herites |
| POST | `/propositions/{id}/accepter-locataire` | Accord du locataire |
| POST | `/propositions/{id}/accepter-bailleur` | Accord du bailleur |
| GET | `/contrats/{id}` | Contrat et signatures |
| POST | `/contrats/{id}/signer` | Signature de l'utilisateur en session |
| GET | `/contrats/{id}/imprimer` | Mise en page A4 et impression navigateur |

## Comportement

- Une candidature par annonce et locataire ; seules les annonces publiees acceptent une candidature.
- La proposition initiale copie les conditions de l'annonce. Chaque modification
  cree une nouvelle version et remet les deux accords a faux.
- Seules les parties concernees peuvent consulter, negocier, accepter ou signer.
  Les identites sont lues dans la session, jamais depuis un champ du formulaire.
- Les decisions verrouillent la candidature en base avant de lire la derniere
  version. Une version obsolete ou une candidature finalisee est refusee.
- Le second accord genere automatiquement un contrat unique. Son contenu est
  enregistre en texte pour conserver les identites, le logement et les conditions
  au moment de l'accord.
- La signature exige une confirmation explicite. Deux signatures distinctes font
  passer le contrat de `A_SIGNER` a `SIGNE`. Un nouvel envoi de la meme signature
  ne cree pas de doublon. Il s'agit d'un enregistrement applicatif de consentement,
  sans prestataire de signature electronique certifiee.
- L'impression ouvre la boite de dialogue du navigateur : choisir l'imprimante
  PDF pour exporter. Aucun fichier PDF n'est genere ou conserve sur le serveur.
- Les POST du parcours sont proteges par un jeton de session anti-CSRF.
- Les mots de passe locataire sont haches avec PBKDF2-HMAC-SHA256 et un sel aleatoire.
- Les validations portent sur les champs et les dates. Le moteur juridique de la
  personne 1 reste a brancher pendant l'integration d'equipe.

## Tests

```sh
./mvnw test
```

Les tests utilisent H2 en mode PostgreSQL, sans modifier une base PostgreSQL locale.
Ils couvrent l'inscription, les validations, les candidatures, les propositions
versionnees, les accords separes et conjoints, la generation, les signatures,
les autorisations, la protection CSRF et le rendu des pages Thymeleaf.
La suite a aussi ete executee sur PostgreSQL 16 avec le fichier
`database/schema-legabail.sql` et `spring.jpa.hibernate.ddl-auto=validate` :
13 tests passes, dont les accords concurrents et le rendu MVC.
Le parcours a egalement ete verifie dans Chrome avec deux sessions distinctes :
inscription, candidature, proposition version 2, double accord, deux signatures,
impression PDF et vues ordinateur/mobile (1366 px et 390 px).

Pour reproduire cette verification sur une base de test dediee, charger le schema
fourni, puis remplacer les valeurs de connexion dans la commande suivante.
Les tests ajoutent des donnees : ne pas utiliser une base de production.

```sh
./mvnw test \
  -Dspring.datasource.url=jdbc:postgresql://localhost:5432/legabail_test \
  -Dspring.datasource.username=postgres \
  -Dspring.datasource.password=postgres \
  -Dspring.datasource.driver-class-name=org.postgresql.Driver \
  -Dspring.jpa.hibernate.ddl-auto=validate
```
