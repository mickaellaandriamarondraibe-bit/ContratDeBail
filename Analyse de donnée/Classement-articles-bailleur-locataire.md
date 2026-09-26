
## Classement article par article

| Article | Partie concernée | Sujet |
|---:|---|---|
| **1** | Les deux | Vérifier si le logement entre dans le champ de la loi |
| **2** | Les deux | Exclusion des logements liés à un emploi |
| **3** | Les deux | Fixation, révision et réduction du loyer |
| **4** | Administration | Évaluation officielle de la valeur du logement |
| **5** | Bailleur principalement | Plafond du loyer mensuel |
| **6** | Bailleur principalement | Plafond de la caution et du loyer d’avance |
| **7** | Bailleur principalement | Prix maximal d’un logement meublé |
| **8** | Les deux | Remboursement au locataire d’un loyer trop élevé |
| **9** | Locataire principalement | Continuer à payer pendant une contestation |
| **10** | Les deux | Autorisation de céder ou sous-louer |
| **11** | Locataire principal | Prix maximal demandé au sous-locataire |
| **12** | Locataire principal et sous-locataire | Contestation et remboursement en sous-location |
| **13** | Locataire | Droit au maintien dans les lieux et bonne foi |
| **14** | Locataire et famille | Maintien après le décès ou le départ du locataire |
| **15** | Locataire principalement | Situations où le maintien peut être refusé |
| **16** | Les deux | Fin d’un bail à durée déterminée |
| **17** | Locataire | Départ et préavis du locataire |
| **18** | Bailleur | Congé donné par le bailleur |
| **19** | Les deux + juge | Procédure d’expulsion |
| **20** | Établissement bailleur spécial + locataire | Impayé et procédure spéciale |
| **21** | Bailleur | Reprise du logement pour travaux |
| **22** | Les deux | Préavis pour travaux et droit de consulter le plan |
| **23** | Les deux | Indemnisation si les travaux ne commencent pas |
| **24** | Bailleur principalement | Reprise du logement pour y habiter |
| **25** | Les deux | Indemnisation si la reprise n’est pas respectée |
| **26** | Les deux | Lieu et méthode de paiement du loyer |
| **27** | Bailleur et candidat locataire | Refus de louer — article encore ambigu |
| **28** | Les deux | Interdiction de contourner la loi par le contrat |
| **29** | Bailleur principalement | Infractions concernant loyer, charges et reprise |
| **30** | Intermédiaire ou agence | Plafond de la commission |
| **31** | Les deux — historique | Première augmentation lors de l’application de la loi |
| **32** | Les deux — historique | Anciens baux et procédures déjà commencées |
| **33** | Justice — historique | Procédure applicable pendant la transition |
| **34** | Législateur — historique | Anciens textes supprimés |
| **35** | Gouvernement — historique | Décrets d’application à publier |

## Articles à utiliser dans le formulaire du bailleur

Le formulaire du bailleur doit surtout récupérer les informations nécessaires aux articles :

- **1 et 2** : type et usage du logement ;
- **3 à 7** : ancienneté du bâtiment, loyer, charges, caution, avance et mobilier ;
- **10** : sous-location autorisée ou non ;
- **16** : durée et date de fin du bail ;
- **18** : congé donné au locataire ;
- **21 à 25** : reprise pour travaux ou habitation ;
- **26** : moyen et lieu de paiement ;
- **27** : refus d’un candidat, après clarification juridique ;
- **29** : contrôle des pratiques interdites.

## Articles à utiliser dans le formulaire du locataire

Le formulaire du locataire doit surtout contrôler les articles :

- **1 et 2** : situation et usage du logement ;
- **3, 5, 6 et 7** : acceptation du loyer, de la caution, de l’avance et du mobilier ;
- **8 et 9** : contestation ou demande de remboursement ;
- **10 à 12** : sous-location ou cession ;
- **13 à 15** : occupation réelle, résidence principale et maintien dans les lieux ;
- **16 et 17** : fin du bail et départ volontaire ;
- **18 et 19** : réception d’un congé ou procédure d’expulsion ;
- **22 à 25** : départ causé par une reprise du bailleur ;
- **26** : paiement du loyer.

La logique importante est donc :

```text
Une réponse du bailleur
        ↓
crée ou modifie une condition du bail
        ↓
le système vérifie les articles concernés
        ↓
le locataire voit seulement les questions correspondant à cette situation
        ↓
ses réponses sont également vérifiées
```

Par exemple, si le bailleur sélectionne **« logement nu »**, **« location mensuelle »**, `loyer = 300 000 Ar`, `caution = 400 000 Ar` et `avance = 300 000 Ar`, le système applique l’**article 6**. Le total de 700 000 Ar dépasse le plafond de 600 000 Ar : le bailleur doit corriger avant que le formulaire soit transmis au locataire.