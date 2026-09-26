# Phase 2 — Règles juridiques structurées

Version de travail à faire valider par un juriste.

## Objectif

Ce document transforme chaque règle de la phase 1 en fiche structurée utilisable pour concevoir les formulaires, la base de données et le moteur de règles.

> Une fiche n’est pas encore du code exécutable. Une décision complexe reste soumise à un examen humain.

---
a
# Article 1 — Quels locaux sont concernés ?

## ART01_R01_COMPLEMENT_DU_CODE_CIVIL

- **Article :** 1
- **Numéro de règle :** 1
- **Titre :** Complément du Code civil
- **Explication simple :** L’ordonnance n’est pas le seul texte à examiner. Les règles du Code civil relatives à la location continuent de s’appliquer lorsqu’elles ne sont pas contraires à cette ordonnance. Cela signifie que l’application devra parfois consulter une règle extérieure au PDF avant de donner une réponse complète.
- **Conditions d’application :** Lorsque le dossier présente la situation « complément du code civil ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’ordonnance n’est pas le seul texte à examiner. Les règles du Code civil relatives à la location continuent de s’appliquer lorsqu’elles ne sont pas contraires à cette ordonnance. Cela signifie que l’application devra parfois consulter une règle extérieure au PDF avant de donner une réponse complète.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART01_R02_LOGEMENTS_DHABITATION

- **Article :** 1
- **Numéro de règle :** 2
- **Titre :** Logements d’habitation
- **Explication simple :** Les maisons, appartements et autres locaux utilisés pour habiter sont concernés par cette ordonnance. Avant d’appliquer les règles, le système doit donc demander quel est l’usage réel du local.
- **Conditions d’application :** Lorsque le dossier présente la situation « logements d’habitation ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les maisons, appartements et autres locaux utilisés pour habiter sont concernés par cette ordonnance. Avant d’appliquer les règles, le système doit donc demander quel est l’usage réel du local.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART01_R03_HOTELS_EXCLUS

- **Article :** 1
- **Numéro de règle :** 3
- **Titre :** Hôtels exclus
- **Explication simple :** Les hôtels et les pensions de famille ne sont pas considérés ici comme de simples locations d’habitation. L’application ne doit donc pas appliquer automatiquement les règles de cette ordonnance à une chambre d’hôtel ou à une pension de famille.
- **Conditions d’application :** Lorsque le dossier présente la situation « hôtels exclus ». 
- **Données nécessaires :** type_local, usage_local, identite_personne, lien_avec_partie
- **Type de contrôle :** QUALIFICATION_CHAMP
- **Contrôle à réaliser :** Les hôtels et les pensions de famille ne sont pas considérés ici comme de simples locations d’habitation. L’application ne doit donc pas appliquer automatiquement les règles de cette ordonnance à une chambre d’hôtel ou à une pension de famille.
- **Si conforme :** CONTINUER
- **Si non conforme :** ORIENTER_AUTRE_REGIME
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART01_R04_CERTAINS_LOCAUX_PROFESSIONNELS

- **Article :** 1
- **Numéro de règle :** 4
- **Titre :** Certains locaux professionnels
- **Explication simple :** Certains locaux utilisés pour une activité professionnelle sans caractère commercial ou industriel peuvent être concernés. Les locaux pris en location par une personne morale de droit public pour installer ses services ou ses agents peuvent également être concernés. Il faut toutefois vérifier qu’ils ne relèvent pas de l’ordonnance n° 60-050 sur certains baux professionnels ou commerciaux.
- **Conditions d’application :** Lorsque le dossier présente la situation « certains locaux professionnels ». 
- **Données nécessaires :** type_occupant, nature_activite
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Certains locaux utilisés pour une activité professionnelle sans caractère commercial ou industriel peuvent être concernés. Les locaux pris en location par une personne morale de droit public pour installer ses services ou ses agents peuvent également être concernés. Il faut toutefois vérifier qu’ils ne relèvent pas de l’ordonnance n° 60-050 sur certains baux professionnels ou commerciaux.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART01_R05_ASSOCIATIONS_ET_SYNDICATS

- **Article :** 1
- **Numéro de règle :** 5
- **Titre :** Associations et syndicats
- **Explication simple :** Les locaux loués par des organismes qui exercent une activité sans but lucratif peuvent être concernés. C’est notamment le cas de certaines associations déclarées et de certains syndicats.
- **Conditions d’application :** Lorsque le dossier présente la situation « associations et syndicats ». 
- **Données nécessaires :** type_occupant, nature_activite
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les locaux loués par des organismes qui exercent une activité sans but lucratif peuvent être concernés. C’est notamment le cas de certaines associations déclarées et de certains syndicats.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART01_R06_LOGEMENT_DU_PERSONNEL

- **Article :** 1
- **Numéro de règle :** 6
- **Titre :** Logement du personnel
- **Explication simple :** Une entreprise commerciale ou industrielle peut louer un logement uniquement pour y loger son personnel. Cette relation entre l’entreprise et le propriétaire peut entrer dans le champ de l’ordonnance. Il faut la distinguer du logement directement attribué à un salarié à cause de son emploi, qui est examiné dans l’article 2.
- **Conditions d’application :** Lorsque le dossier présente la situation « logement du personnel ». 
- **Données nécessaires :** type_local, usage_local, type_occupant, nature_activite
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Une entreprise commerciale ou industrielle peut louer un logement uniquement pour y loger son personnel. Cette relation entre l’entreprise et le propriétaire peut entrer dans le champ de l’ordonnance. Il faut la distinguer du logement directement attribué à un salarié à cause de son emploi, qui est examiné dans l’article 2.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 2 — Le logement fourni avec un emploi

## ART02_R01_LOGEMENT_LIE_A_LA_FONCTION

- **Article :** 2
- **Numéro de règle :** 1
- **Titre :** Logement lié à la fonction
- **Explication simple :** Le logement attribué comme avantage lié à un emploi est exclu du champ de cette ordonnance.
- **Conditions d’application :** Lorsque le dossier présente la situation « logement lié à la fonction ». 
- **Données nécessaires :** type_local, usage_local, type_occupant, nature_activite
- **Type de contrôle :** QUALIFICATION_CHAMP
- **Contrôle à réaliser :** Le logement attribué comme avantage lié à un emploi est exclu du champ de cette ordonnance.
- **Si conforme :** CONTINUER
- **Si non conforme :** ORIENTER_AUTRE_REGIME
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART02_R02_QUALIFICATION_NECESSAIRE

- **Article :** 2
- **Numéro de règle :** 2
- **Titre :** Qualification nécessaire
- **Explication simple :** Il faut vérifier la véritable relation entre l’emploi et l’occupation du logement.
- **Conditions d’application :** Lorsque le dossier présente la situation « qualification nécessaire ». 
- **Données nécessaires :** type_local, usage_local, type_occupant, nature_activite
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Il faut vérifier la véritable relation entre l’emploi et l’occupation du logement.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 3 — Comment fixer et modifier le loyer ?

## ART03_R01_LIBERTE_PENDANT_CINQ_ANS

- **Article :** 3
- **Numéro de règle :** 1
- **Titre :** Liberté pendant cinq ans
- **Explication simple :** Le loyer est librement fixé pendant les cinq premières années suivant le permis d’habiter.
- **Conditions d’application :** Lorsque le dossier présente la situation « liberté pendant cinq ans ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le loyer est librement fixé pendant les cinq premières années suivant le permis d’habiter.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART03_R02_PLAFOND_APRES_CINQ_ANS

- **Article :** 3
- **Numéro de règle :** 2
- **Titre :** Plafond après cinq ans
- **Explication simple :** Après cette période, le loyer est soumis à un plafond réglementaire.
- **Conditions d’application :** Lorsque le dossier présente la situation « plafond après cinq ans ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Après cette période, le loyer est soumis à un plafond réglementaire.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART03_R03_REDUCTION_POSSIBLE

- **Article :** 3
- **Numéro de règle :** 3
- **Titre :** Réduction possible
- **Explication simple :** Le loyer peut être réduit pour mauvais entretien ou manque de confort.
- **Conditions d’application :** Lorsque le dossier présente la situation « réduction possible ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le loyer peut être réduit pour mauvais entretien ou manque de confort.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART03_R04_DELAI_DE_REVISION

- **Article :** 3
- **Numéro de règle :** 4
- **Titre :** Délai de révision
- **Explication simple :** Une révision ne peut normalement pas intervenir avant un an.
- **Conditions d’application :** Lorsque le dossier présente la situation « délai de révision ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Une révision ne peut normalement pas intervenir avant un an.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART03_R05_CHARGES

- **Article :** 3
- **Numéro de règle :** 5
- **Titre :** Charges
- **Explication simple :** Certaines charges réellement payées peuvent être ajoutées, mais pas les impôts.
- **Conditions d’application :** Lorsque le dossier présente la situation « charges ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Certaines charges réellement payées peuvent être ajoutées, mais pas les impôts.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 4 — Comment connaître la valeur du bâtiment ?

## ART04_R01_METHODE_FIXEE_PAR_DECRET

- **Article :** 4
- **Numéro de règle :** 1
- **Titre :** Méthode fixée par décret
- **Explication simple :** L’évaluation ne doit pas être inventée par l’application.
- **Conditions d’application :** Lorsque le dossier présente la situation « méthode fixée par décret ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** L’évaluation ne doit pas être inventée par l’application.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART04_R02_INTERVENTION_DE_COMMISSIONS

- **Article :** 4
- **Numéro de règle :** 2
- **Titre :** Intervention de commissions
- **Explication simple :** Des commissions doivent proposer le mode de calcul.
- **Conditions d’application :** Lorsque le dossier présente la situation « intervention de commissions ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Des commissions doivent proposer le mode de calcul.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART04_R03_CRITERES_DEVALUATION

- **Article :** 4
- **Numéro de règle :** 3
- **Titre :** Critères d’évaluation
- **Explication simple :** Le type et l’ancienneté de la construction doivent notamment être pris en compte.
- **Conditions d’application :** Lorsque le dossier présente la situation « critères d’évaluation ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le type et l’ancienneté de la construction doivent notamment être pris en compte.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 5 — Quel est le plafond du loyer mensuel ?

## ART05_R01_LOCATION_DE_TROIS_MOIS_MAXIMUM

- **Article :** 5
- **Numéro de règle :** 1
- **Titre :** Location de trois mois maximum
- **Explication simple :** Le plafond mensuel correspond à un dixième du loyer annuel légal.
- **Conditions d’application :** Lorsque le dossier présente la situation « location de trois mois maximum ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Le plafond mensuel correspond à un dixième du loyer annuel légal.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART05_R02_LOCATION_DE_PLUS_DE_TROIS_MOIS

- **Article :** 5
- **Numéro de règle :** 2
- **Titre :** Location de plus de trois mois
- **Explication simple :** Le plafond mensuel correspond à un douzième du loyer annuel légal.
- **Conditions d’application :** Lorsque le dossier présente la situation « location de plus de trois mois ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Le plafond mensuel correspond à un douzième du loyer annuel légal.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 6 — La caution et l’avance d’un logement vide

## ART06_R01_LOGEMENT_NU

- **Article :** 6
- **Numéro de règle :** 1
- **Titre :** Logement nu
- **Explication simple :** Les plafonds de cet article concernent les lieux loués sans mobilier.
- **Conditions d’application :** Lorsque le dossier présente la situation « logement nu ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Les plafonds de cet article concernent les lieux loués sans mobilier.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART06_R02_LOCATION_AU_MOIS

- **Article :** 6
- **Numéro de règle :** 2
- **Titre :** Location au mois
- **Explication simple :** Caution et avance réunies ne dépassent pas deux mois de loyer.
- **Conditions d’application :** Lorsque le dossier présente la situation « location au mois ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Caution et avance réunies ne dépassent pas deux mois de loyer.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART06_R03_AUTRES_LOCATIONS

- **Article :** 6
- **Numéro de règle :** 3
- **Titre :** Autres locations
- **Explication simple :** Leur total ne dépasse pas le quart du loyer annuel.
- **Conditions d’application :** Lorsque le dossier présente la situation « autres locations ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Leur total ne dépasse pas le quart du loyer annuel.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 7 — Le prix d’un logement meublé

## ART07_R01_MAJORATION_MAXIMALE

- **Article :** 7
- **Numéro de règle :** 1
- **Titre :** Majoration maximale
- **Explication simple :** Le mobilier ne peut pas augmenter le prix de plus de 50 %.
- **Conditions d’application :** Lorsque le dossier présente la situation « majoration maximale ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le mobilier ne peut pas augmenter le prix de plus de 50 %.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART07_R02_PROPORTIONNALITE

- **Article :** 7
- **Numéro de règle :** 2
- **Titre :** Proportionnalité
- **Explication simple :** La hausse doit correspondre au mobilier réellement fourni.
- **Conditions d’application :** Lorsque le dossier présente la situation « proportionnalité ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** La hausse doit correspondre au mobilier réellement fourni.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART07_R03_CONDITION_DU_MAXIMUM

- **Article :** 7
- **Numéro de règle :** 3
- **Titre :** Condition du maximum
- **Explication simple :** La majoration maximale suppose un mobilier en parfait état et adapté au logement.
- **Conditions d’application :** Lorsque le dossier présente la situation « condition du maximum ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** La majoration maximale suppose un mobilier en parfait état et adapté au logement.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 8 — Le remboursement d’un loyer payé en trop

## ART08_R01_REMBOURSEMENT

- **Article :** 8
- **Numéro de règle :** 1
- **Titre :** Remboursement
- **Explication simple :** Le propriétaire doit restituer les sommes reçues au-dessus du taux légal.
- **Conditions d’application :** Lorsque le dossier présente la situation « remboursement ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le propriétaire doit restituer les sommes reçues au-dessus du taux légal.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART08_R02_PERIODE_RECUPERABLE

- **Article :** 8
- **Numéro de règle :** 2
- **Titre :** Période récupérable
- **Explication simple :** La demande du locataire est limitée à l’année précédant sa réclamation expresse.
- **Conditions d’application :** Lorsque le dossier présente la situation « période récupérable ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** La demande du locataire est limitée à l’année précédant sa réclamation expresse.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 9 — Faut-il continuer à payer pendant une contestation ?

## ART09_R01_PAIEMENT_MAINTENU

- **Article :** 9
- **Numéro de règle :** 1
- **Titre :** Paiement maintenu
- **Explication simple :** Le locataire continue à payer l’ancien montant pendant la contestation.
- **Conditions d’application :** Lorsque le dossier présente la situation « paiement maintenu ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le locataire continue à payer l’ancien montant pendant la contestation.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART09_R02_FIN_DE_LATTENTE

- **Article :** 9
- **Numéro de règle :** 2
- **Titre :** Fin de l’attente
- **Explication simple :** Le nouveau montant s’applique après un accord ou une décision exécutoire.
- **Conditions d’application :** Lorsque le dossier présente la situation « fin de l’attente ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le nouveau montant s’applique après un accord ou une décision exécutoire.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART09_R03_REFUS_DE_LA_DECISION

- **Article :** 9
- **Numéro de règle :** 3
- **Titre :** Refus de la décision
- **Explication simple :** Le locataire qui refuse la décision et part peut supporter les frais du procès et le préavis.
- **Conditions d’application :** Lorsque le dossier présente la situation « refus de la décision ». 
- **Données nécessaires :** date_evenement, date_echeance, type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le locataire qui refuse la décision et part peut supporter les frais du procès et le préavis.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 10 — Quand faut-il l’autorisation du propriétaire ?

## ART10_R01_CESSION_DU_BAIL

- **Article :** 10
- **Numéro de règle :** 1
- **Titre :** Cession du bail
- **Explication simple :** Elle nécessite l’autorisation expresse du propriétaire.
- **Conditions d’application :** Lorsque le dossier présente la situation « cession du bail ». 
- **Données nécessaires :** type_document, preuve_documentaire, type_operation, autorisation_bailleur
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** Elle nécessite l’autorisation expresse du propriétaire.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART10_R02_PLUS_DES_DEUX_TIERS

- **Article :** 10
- **Numéro de règle :** 2
- **Titre :** Plus des deux tiers
- **Explication simple :** La sous-location de plus des deux tiers des pièces nécessite cette autorisation.
- **Conditions d’application :** Lorsque le dossier présente la situation « plus des deux tiers ». 
- **Données nécessaires :** type_document, preuve_documentaire, type_operation, autorisation_bailleur
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** La sous-location de plus des deux tiers des pièces nécessite cette autorisation.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART10_R03_LOGEMENT_MEUBLE

- **Article :** 10
- **Numéro de règle :** 3
- **Titre :** Logement meublé
- **Explication simple :** Mettre à disposition une partie d’un local meublé par le propriétaire nécessite une autorisation.
- **Conditions d’application :** Lorsque le dossier présente la situation « logement meublé ». 
- **Données nécessaires :** type_local, usage_local, type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** Mettre à disposition une partie d’un local meublé par le propriétaire nécessite une autorisation.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART10_R04_EXCEPTION_FAMILIALE

- **Article :** 10
- **Numéro de règle :** 4
- **Titre :** Exception familiale
- **Explication simple :** Les ascendants et descendants directs du locataire bénéficient de l’exception prévue.
- **Conditions d’application :** Lorsque le dossier présente la situation « exception familiale ». 
- **Données nécessaires :** identite_personne, lien_avec_partie
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les ascendants et descendants directs du locataire bénéficient de l’exception prévue.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART10_R05_CONSEQUENCE

- **Article :** 10
- **Numéro de règle :** 5
- **Titre :** Conséquence
- **Explication simple :** Le contrat réalisé sans l’autorisation requise peut être nul.
- **Conditions d’application :** Lorsque le dossier présente la situation « conséquence ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** Le contrat réalisé sans l’autorisation requise peut être nul.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 11 — Quel prix demander au sous-locataire ?

## ART11_R01_PLAFOND_DE_SOUS_LOCATION

- **Article :** 11
- **Numéro de règle :** 1
- **Titre :** Plafond de sous-location
- **Explication simple :** Le prix demandé ne dépasse pas le montant légal applicable à la superficie occupée.
- **Conditions d’application :** Lorsque le dossier présente la situation « plafond de sous-location ». 
- **Données nécessaires :** montant_loyer, montants_concernes, type_operation, autorisation_bailleur
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Le prix demandé ne dépasse pas le montant légal applicable à la superficie occupée.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 12 — Les droits du sous-locataire

## ART12_R01_TROP_PERCU

- **Article :** 12
- **Numéro de règle :** 1
- **Titre :** Trop-perçu
- **Explication simple :** Les règles de remboursement de l’article 8 s’appliquent au sous-locataire.
- **Conditions d’application :** Lorsque le dossier présente la situation « trop-perçu ». 
- **Données nécessaires :** type_operation, autorisation_bailleur
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les règles de remboursement de l’article 8 s’appliquent au sous-locataire.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART12_R02_CONTESTATION

- **Article :** 12
- **Numéro de règle :** 2
- **Titre :** Contestation
- **Explication simple :** Les règles de paiement de l’article 9 s’appliquent pendant son litige avec le locataire principal.
- **Conditions d’application :** Lorsque le dossier présente la situation « contestation ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les règles de paiement de l’article 9 s’appliquent pendant son litige avec le locataire principal.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 13 — L’occupant de bonne foi

## ART13_R01_MAINTIEN_DANS_LES_LIEUX

- **Article :** 13
- **Numéro de règle :** 1
- **Titre :** Maintien dans les lieux
- **Explication simple :** Certains occupants présents lors de la publication bénéficient d’un maintien de plein droit.
- **Conditions d’application :** Lorsque le dossier présente la situation « maintien dans les lieux ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Certains occupants présents lors de la publication bénéficient d’un maintien de plein droit.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART13_R02_BONNE_FOI

- **Article :** 13
- **Numéro de règle :** 2
- **Titre :** Bonne foi
- **Explication simple :** L’occupant doit utiliser normalement les lieux et respecter régulièrement ses obligations.
- **Conditions d’application :** Lorsque le dossier présente la situation « bonne foi ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’occupant doit utiliser normalement les lieux et respecter régulièrement ses obligations.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART13_R03_PAIEMENT

- **Article :** 13
- **Numéro de règle :** 3
- **Titre :** Paiement
- **Explication simple :** Le paiement régulier du loyer fait notamment partie des obligations examinées.
- **Conditions d’application :** Lorsque le dossier présente la situation « paiement ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le paiement régulier du loyer fait notamment partie des obligations examinées.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 14 — Que se passe-t-il après un décès ou un départ ?

## ART14_R01_EVENEMENT_DECLENCHEUR

- **Article :** 14
- **Numéro de règle :** 1
- **Titre :** Événement déclencheur
- **Explication simple :** Le décès ou l’abandon du domicile par le locataire ouvre l’examen du maintien.
- **Conditions d’application :** Lorsque le dossier présente la situation « événement déclencheur ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le décès ou l’abandon du domicile par le locataire ouvre l’examen du maintien.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART14_R02_BENEFICIAIRES

- **Article :** 14
- **Numéro de règle :** 2
- **Titre :** Bénéficiaires
- **Explication simple :** Certains membres de la famille et personnes à charge peuvent reprendre le bail.
- **Conditions d’application :** Lorsque le dossier présente la situation « bénéficiaires ». 
- **Données nécessaires :** montant_loyer, montants_concernes, identite_personne, lien_avec_partie
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Certains membres de la famille et personnes à charge peuvent reprendre le bail.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART14_R03_DUREE_DE_PRESENCE

- **Article :** 14
- **Numéro de règle :** 3
- **Titre :** Durée de présence
- **Explication simple :** Ils doivent occuper le logement depuis plus de six mois.
- **Conditions d’application :** Lorsque le dossier présente la situation « durée de présence ». 
- **Données nécessaires :** type_local, usage_local, date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Ils doivent occuper le logement depuis plus de six mois.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART14_R04_LOCAL_PROFESSIONNEL

- **Article :** 14
- **Numéro de règle :** 4
- **Titre :** Local professionnel
- **Explication simple :** Une exception particulière s’applique si une personne poursuit la profession exercée dans le local.
- **Conditions d’application :** Lorsque le dossier présente la situation « local professionnel ». 
- **Données nécessaires :** type_local, usage_local, type_occupant, nature_activite
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Une exception particulière s’applique si une personne poursuit la profession exercée dans le local.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 15 — Quand perd-on le droit de rester ?

## ART15_R01_OCCUPATION_PERSONNELLE

- **Article :** 15
- **Numéro de règle :** 1
- **Titre :** Occupation personnelle
- **Explication simple :** L’occupant qui n’habite pas réellement les lieux peut perdre le maintien.
- **Conditions d’application :** Lorsque le dossier présente la situation « occupation personnelle ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’occupant qui n’habite pas réellement les lieux peut perdre le maintien.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART15_R02_DEPART_DE_LA_LOCALITE

- **Article :** 15
- **Numéro de règle :** 2
- **Titre :** Départ de la localité
- **Explication simple :** Un départ définitif peut exclure le maintien, avec une exception familiale.
- **Conditions d’application :** Lorsque le dossier présente la situation « départ de la localité ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** QUALIFICATION_CHAMP
- **Contrôle à réaliser :** Un départ définitif peut exclure le maintien, avec une exception familiale.
- **Si conforme :** CONTINUER
- **Si non conforme :** ORIENTER_AUTRE_REGIME
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART15_R03_RESIDENCE_PRINCIPALE

- **Article :** 15
- **Numéro de règle :** 3
- **Titre :** Résidence principale
- **Explication simple :** Une résidence secondaire peut être exclue, sauf nécessité professionnelle.
- **Conditions d’application :** Lorsque le dossier présente la situation « résidence principale ». 
- **Données nécessaires :** type_occupant, nature_activite
- **Type de contrôle :** QUALIFICATION_CHAMP
- **Contrôle à réaliser :** Une résidence secondaire peut être exclue, sauf nécessité professionnelle.
- **Si conforme :** CONTINUER
- **Si non conforme :** ORIENTER_AUTRE_REGIME
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART15_R04_AUTRE_LOGEMENT

- **Article :** 15
- **Numéro de règle :** 4
- **Titre :** Autre logement
- **Explication simple :** Disposer d’un logement adapté peut exclure le maintien.
- **Conditions d’application :** Lorsque le dossier présente la situation « autre logement ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** QUALIFICATION_CHAMP
- **Contrôle à réaliser :** Disposer d’un logement adapté peut exclure le maintien.
- **Si conforme :** CONTINUER
- **Si non conforme :** ORIENTER_AUTRE_REGIME
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART15_R05_RELOGEMENT

- **Article :** 15
- **Numéro de règle :** 5
- **Titre :** Relogement
- **Explication simple :** Un relogement sensiblement identique peut exclure le maintien.
- **Conditions d’application :** Lorsque le dossier présente la situation « relogement ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** QUALIFICATION_CHAMP
- **Contrôle à réaliser :** Un relogement sensiblement identique peut exclure le maintien.
- **Si conforme :** CONTINUER
- **Si non conforme :** ORIENTER_AUTRE_REGIME
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART15_R06_ABSENCE_TEMPORAIRE

- **Article :** 15
- **Numéro de règle :** 6
- **Titre :** Absence temporaire
- **Explication simple :** Une location expressément limitée à une absence prend fin avec cette absence.
- **Conditions d’application :** Lorsque le dossier présente la situation « absence temporaire ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Une location expressément limitée à une absence prend fin avec cette absence.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART15_R07_FIN_DE_FONCTION

- **Article :** 15
- **Numéro de règle :** 7
- **Titre :** Fin de fonction
- **Explication simple :** Le logement accessoire à un emploi peut prendre fin avec la fonction.
- **Conditions d’application :** Lorsque le dossier présente la situation « fin de fonction ». 
- **Données nécessaires :** type_local, usage_local, type_occupant, nature_activite
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le logement accessoire à un emploi peut prendre fin avec la fonction.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART15_R08_LOGEMENT_INHABITABLE

- **Article :** 15
- **Numéro de règle :** 8
- **Titre :** Logement inhabitable
- **Explication simple :** La vétusté, l’insalubrité ou l’utilité publique peuvent entraîner l’évacuation.
- **Conditions d’application :** Lorsque le dossier présente la situation « logement inhabitable ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** La vétusté, l’insalubrité ou l’utilité publique peuvent entraîner l’évacuation.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART15_R09_SOUS_LOCATAIRE

- **Article :** 15
- **Numéro de règle :** 9
- **Titre :** Sous-locataire
- **Explication simple :** Son maintien dépend de celui du locataire principal.
- **Conditions d’application :** Lorsque le dossier présente la situation « sous-locataire ». 
- **Données nécessaires :** type_operation, autorisation_bailleur
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Son maintien dépend de celui du locataire principal.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 16 — La fin d’un bail à durée déterminée

## ART16_R01_FIN_AUTOMATIQUE

- **Article :** 16
- **Numéro de règle :** 1
- **Titre :** Fin automatique
- **Explication simple :** Le bail déterminé prend fin au terme fixé sans congé préalable.
- **Conditions d’application :** Lorsque le dossier présente la situation « fin automatique ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le bail déterminé prend fin au terme fixé sans congé préalable.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART16_R02_DEPART_MATERIEL

- **Article :** 16
- **Numéro de règle :** 2
- **Titre :** Départ matériel
- **Explication simple :** La fin du bail et l’expulsion éventuelle sont deux événements différents.
- **Conditions d’application :** Lorsque le dossier présente la situation « départ matériel ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** La fin du bail et l’expulsion éventuelle sont deux événements différents.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART16_R03_TRANSFORMATION

- **Article :** 16
- **Numéro de règle :** 3
- **Titre :** Transformation
- **Explication simple :** Si le locataire reste sans opposition du bailleur, le bail devient indéterminé.
- **Conditions d’application :** Lorsque le dossier présente la situation « transformation ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Si le locataire reste sans opposition du bailleur, le bail devient indéterminé.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 17 — Le départ du locataire

## ART17_R01_BAIL_CONCERNE

- **Article :** 17
- **Numéro de règle :** 1
- **Titre :** Bail concerné
- **Explication simple :** L’article vise le départ d’un bail à durée indéterminée.
- **Conditions d’application :** Lorsque le dossier présente la situation « bail concerné ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’article vise le départ d’un bail à durée indéterminée.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART17_R02_PREAVIS_MINIMAL

- **Article :** 17
- **Numéro de règle :** 2
- **Titre :** Préavis minimal
- **Explication simple :** Le locataire avertit le bailleur au moins un mois à l’avance.
- **Conditions d’application :** Lorsque le dossier présente la situation « préavis minimal ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le locataire avertit le bailleur au moins un mois à l’avance.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART17_R03_CLAUSE_PLUS_LONGUE

- **Article :** 17
- **Numéro de règle :** 3
- **Titre :** Clause plus longue
- **Explication simple :** Le délai supérieur prévu par le contrat doit être pris en compte.
- **Conditions d’application :** Lorsque le dossier présente la situation « clause plus longue ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le délai supérieur prévu par le contrat doit être pris en compte.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART17_R04_PREAVIS_NON_RESPECTE

- **Article :** 17
- **Numéro de règle :** 4
- **Titre :** Préavis non respecté
- **Explication simple :** Les loyers correspondant au délai manquant restent dus.
- **Conditions d’application :** Lorsque le dossier présente la situation « préavis non respecté ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Les loyers correspondant au délai manquant restent dus.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 18 — Le congé donné par le propriétaire

## ART18_R01_SITUATION_CONCERNEE

- **Article :** 18
- **Numéro de règle :** 1
- **Titre :** Situation concernée
- **Explication simple :** Le bail doit être indéterminé et le locataire de bonne foi sans droit au maintien.
- **Conditions d’application :** Lorsque le dossier présente la situation « situation concernée ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le bail doit être indéterminé et le locataire de bonne foi sans droit au maintien.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART18_R02_PREAVIS

- **Article :** 18
- **Numéro de règle :** 2
- **Titre :** Préavis
- **Explication simple :** Le bailleur doit prévenir trois mois à l’avance.
- **Conditions d’application :** Lorsque le dossier présente la situation « préavis ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le bailleur doit prévenir trois mois à l’avance.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART18_R03_FORME

- **Article :** 18
- **Numéro de règle :** 3
- **Titre :** Forme
- **Explication simple :** Le congé est transmis par lettre recommandée avec accusé de réception ou par huissier.
- **Conditions d’application :** Lorsque le dossier présente la situation « forme ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** Le congé est transmis par lettre recommandée avec accusé de réception ou par huissier.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART18_R04_DOMICILE

- **Article :** 18
- **Numéro de règle :** 4
- **Titre :** Domicile
- **Explication simple :** Le locataire est présumé domicilié dans les lieux, sauf accord contraire explicite.
- **Conditions d’application :** Lorsque le dossier présente la situation « domicile ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le locataire est présumé domicilié dans les lieux, sauf accord contraire explicite.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 19 — Qui peut décider une expulsion ?

## ART19_R01_DECISION_JUDICIAIRE

- **Article :** 19
- **Numéro de règle :** 1
- **Titre :** Décision judiciaire
- **Explication simple :** Une expulsion nécessite une décision du juge.
- **Conditions d’application :** Lorsque le dossier présente la situation « décision judiciaire ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Une expulsion nécessite une décision du juge.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** justice
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART19_R02_PERSONNES_CONCERNEES

- **Article :** 19
- **Numéro de règle :** 2
- **Titre :** Personnes concernées
- **Explication simple :** La procédure peut viser un locataire de mauvaise foi, un congé régulier arrivé à terme ou un occupant sans titre.
- **Conditions d’application :** Lorsque le dossier présente la situation « personnes concernées ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** La procédure peut viser un locataire de mauvaise foi, un congé régulier arrivé à terme ou un occupant sans titre.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART19_R03_CONTESTATION_SERIEUSE

- **Article :** 19
- **Numéro de règle :** 3
- **Titre :** Contestation sérieuse
- **Explication simple :** Elle empêche l’utilisation automatique de la procédure de référé.
- **Conditions d’application :** Lorsque le dossier présente la situation « contestation sérieuse ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Elle empêche l’utilisation automatique de la procédure de référé.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART19_R04_DELAI_DE_GRACE

- **Article :** 19
- **Numéro de règle :** 4
- **Titre :** Délai de grâce
- **Explication simple :** Le juge peut accorder un délai supplémentaire.
- **Conditions d’application :** Lorsque le dossier présente la situation « délai de grâce ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le juge peut accorder un délai supplémentaire.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** justice
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 20 — Une procédure spéciale pour certains établissements

## ART20_R01_ETABLISSEMENT_DESIGNE

- **Article :** 20
- **Numéro de règle :** 1
- **Titre :** Établissement désigné
- **Explication simple :** Seuls les établissements désignés par décret sont concernés.
- **Conditions d’application :** Lorsque le dossier présente la situation « établissement désigné ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Seuls les établissements désignés par décret sont concernés.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART20_R02_RETARD_MINIMAL

- **Article :** 20
- **Numéro de règle :** 2
- **Titre :** Retard minimal
- **Explication simple :** Le retard doit atteindre au moins un mois.
- **Conditions d’application :** Lorsque le dossier présente la situation « retard minimal ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le retard doit atteindre au moins un mois.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART20_R03_DEMANDE_AU_TRIBUNAL

- **Article :** 20
- **Numéro de règle :** 3
- **Titre :** Demande au tribunal
- **Explication simple :** L’établissement peut demander le paiement et l’expulsion.
- **Conditions d’application :** Lorsque le dossier présente la situation « demande au tribunal ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’établissement peut demander le paiement et l’expulsion.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** justice
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART20_R04_SIGNIFICATION

- **Article :** 20
- **Numéro de règle :** 4
- **Titre :** Signification
- **Explication simple :** La décision doit être signifiée par huissier.
- **Conditions d’application :** Lorsque le dossier présente la situation « signification ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** La décision doit être signifiée par huissier.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART20_R05_OPPOSITION

- **Article :** 20
- **Numéro de règle :** 5
- **Titre :** Opposition
- **Explication simple :** Le locataire dispose de huit jours pour faire opposition.
- **Conditions d’application :** Lorsque le dossier présente la situation « opposition ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le locataire dispose de huit jours pour faire opposition.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART20_R06_MENTIONS_OBLIGATOIRES

- **Article :** 20
- **Numéro de règle :** 6
- **Titre :** Mentions obligatoires
- **Explication simple :** L’acte doit expliquer la possibilité, la forme et le délai d’opposition.
- **Conditions d’application :** Lorsque le dossier présente la situation « mentions obligatoires ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** L’acte doit expliquer la possibilité, la forme et le délai d’opposition.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART20_R07_DECISION_SUR_OPPOSITION

- **Article :** 20
- **Numéro de règle :** 7
- **Titre :** Décision sur opposition
- **Explication simple :** Le juge statue selon la procédure spéciale décrite.
- **Conditions d’application :** Lorsque le dossier présente la situation « décision sur opposition ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Le juge statue selon la procédure spéciale décrite.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** justice
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART20_R08_ABSENCE_DE_GRACE

- **Article :** 20
- **Numéro de règle :** 8
- **Titre :** Absence de grâce
- **Explication simple :** Si les torts sont reconnus, aucun délai de grâce n’est accordé dans ce régime spécial.
- **Conditions d’application :** Lorsque le dossier présente la situation « absence de grâce ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Si les torts sont reconnus, aucun délai de grâce n’est accordé dans ce régime spécial.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 21 — La reprise pour réaliser des travaux

## ART21_R01_TRAVAUX_AUTORISES

- **Article :** 21
- **Numéro de règle :** 1
- **Titre :** Travaux autorisés
- **Explication simple :** Le propriétaire doit disposer d’une autorisation officielle.
- **Conditions d’application :** Lorsque le dossier présente la situation « travaux autorisés ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** Le propriétaire doit disposer d’une autorisation officielle.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART21_R02_NATURE_DES_TRAVAUX

- **Article :** 21
- **Numéro de règle :** 2
- **Titre :** Nature des travaux
- **Explication simple :** Reconstruction, surélévation ou modification peuvent être concernées.
- **Conditions d’application :** Lorsque le dossier présente la situation « nature des travaux ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Reconstruction, surélévation ou modification peuvent être concernées.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART21_R03_EVACUATION_NECESSAIRE

- **Article :** 21
- **Numéro de règle :** 3
- **Titre :** Évacuation nécessaire
- **Explication simple :** Les travaux doivent normalement exiger le départ des occupants.
- **Conditions d’application :** Lorsque le dossier présente la situation « évacuation nécessaire ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les travaux doivent normalement exiger le départ des occupants.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 22 — Les conditions de la reprise pour travaux

## ART22_R01_PREAVIS

- **Article :** 22
- **Numéro de règle :** 1
- **Titre :** Préavis
- **Explication simple :** Le propriétaire prévient six mois à l’avance.
- **Conditions d’application :** Lorsque le dossier présente la situation « préavis ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le propriétaire prévient six mois à l’avance.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART22_R02_FORME

- **Article :** 22
- **Numéro de règle :** 2
- **Titre :** Forme
- **Explication simple :** Le préavis est transmis par exploit d’huissier.
- **Conditions d’application :** Lorsque le dossier présente la situation « forme ». 
- **Données nécessaires :** date_evenement, date_echeance, type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le préavis est transmis par exploit d’huissier.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART22_R03_MOTIF

- **Article :** 22
- **Numéro de règle :** 3
- **Titre :** Motif
- **Explication simple :** Les travaux sont expliqués avec précision.
- **Conditions d’application :** Lorsque le dossier présente la situation « motif ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les travaux sont expliqués avec précision.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART22_R04_AUTORISATION

- **Article :** 22
- **Numéro de règle :** 4
- **Titre :** Autorisation
- **Explication simple :** La décision administrative est mentionnée.
- **Conditions d’application :** Lorsque le dossier présente la situation « autorisation ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** La décision administrative est mentionnée.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART22_R05_PLAN

- **Article :** 22
- **Numéro de règle :** 5
- **Titre :** Plan
- **Explication simple :** Le locataire peut demander le plan des travaux autorisés.
- **Conditions d’application :** Lorsque le dossier présente la situation « plan ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_PREUVE_OU_FORME
- **Contrôle à réaliser :** Le locataire peut demander le plan des travaux autorisés.
- **Si conforme :** CONTINUER
- **Si non conforme :** DEMANDER_DOCUMENT_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART22_R06_DEBUT_DES_TRAVAUX

- **Article :** 22
- **Numéro de règle :** 6
- **Titre :** Début des travaux
- **Explication simple :** Ils commencent au plus tard trois mois après l’évacuation.
- **Conditions d’application :** Lorsque le dossier présente la situation « début des travaux ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Ils commencent au plus tard trois mois après l’évacuation.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 23 — Les travaux ne commencent pas

## ART23_R01_TRAVAUX_NON_COMMENCES

- **Article :** 23
- **Numéro de règle :** 1
- **Titre :** Travaux non commencés
- **Explication simple :** Le dépassement du délai peut entraîner une indemnisation.
- **Conditions d’application :** Lorsque le dossier présente la situation « travaux non commencés ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le dépassement du délai peut entraîner une indemnisation.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART23_R02_EXCEPTION

- **Article :** 23
- **Numéro de règle :** 2
- **Titre :** Exception
- **Explication simple :** Un motif valable et imprévu peut être examiné.
- **Conditions d’application :** Lorsque le dossier présente la situation « exception ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Un motif valable et imprévu peut être examiné.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART23_R03_NOUVEL_OCCUPANT

- **Article :** 23
- **Numéro de règle :** 3
- **Titre :** Nouvel occupant
- **Explication simple :** Dans ce cas, l’indemnité minimale correspond à une année de loyer.
- **Conditions d’application :** Lorsque le dossier présente la situation « nouvel occupant ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Dans ce cas, l’indemnité minimale correspond à une année de loyer.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 24 — La reprise pour habiter le logement

## ART24_R01_LE_PROPRIETAIRE_PEUT_REPRENDRE_LE_LOGEMENT

- **Article :** 24
- **Numéro de règle :** 1
- **Titre :** Le propriétaire peut reprendre le logement
- **Explication simple :** Le propriétaire peut demander à récupérer le logement pour qu’il soit réellement habité par lui-même ou par une personne autorisée.
- **Conditions d’application :** Lorsque le dossier présente la situation « le propriétaire peut reprendre le logement ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le propriétaire peut demander à récupérer le logement pour qu’il soit réellement habité par lui-même ou par une personne autorisée.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART24_R02_LES_PERSONNES_AUTORISEES

- **Article :** 24
- **Numéro de règle :** 2
- **Titre :** Les personnes autorisées
- **Explication simple :** Le propriétaire peut reprendre le logement pour : - lui-même ; - son conjoint ; - ses parents ou grands-parents directs ; - ses enfants ou petits-enfants directs ; - les ascendants ou descendants directs de son conjoint. Les frères, les sœurs, les cousins et les amis ne sont pas mentionnés dans cette liste.
- **Conditions d’application :** Lorsque le dossier présente la situation « les personnes autorisées ». 
- **Données nécessaires :** type_local, usage_local, identite_personne, lien_avec_partie
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le propriétaire peut reprendre le logement pour : - lui-même ; - son conjoint ; - ses parents ou grands-parents directs ; - ses enfants ou petits-enfants directs ; - les ascendants ou descendants directs de son conjoint. Les frères, les sœurs, les cousins et les amis ne sont pas mentionnés dans cette liste.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART24_R03_LE_PREAVIS_DE_TROIS_MOIS

- **Article :** 24
- **Numéro de règle :** 3
- **Titre :** Le préavis de trois mois
- **Explication simple :** Le propriétaire doit prévenir le locataire au moins trois mois avant la date de reprise demandée.
- **Conditions d’application :** Lorsque le dossier présente la situation « le préavis de trois mois ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le propriétaire doit prévenir le locataire au moins trois mois avant la date de reprise demandée.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART24_R04_LA_NOTIFICATION_PAR_HUISSIER

- **Article :** 24
- **Numéro de règle :** 4
- **Titre :** La notification par huissier
- **Explication simple :** Le préavis doit être transmis par un exploit d’huissier. Un SMS, un appel ou un simple message dans l’application ne remplace pas cette notification officielle.
- **Conditions d’application :** Lorsque le dossier présente la situation « la notification par huissier ». 
- **Données nécessaires :** date_evenement, date_echeance, type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Le préavis doit être transmis par un exploit d’huissier. Un SMS, un appel ou un simple message dans l’application ne remplace pas cette notification officielle.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART24_R05_LE_MOTIF_DOIT_ETRE_INDIQUE

- **Article :** 24
- **Numéro de règle :** 5
- **Titre :** Le motif doit être indiqué
- **Explication simple :** La notification doit préciser le motif de la reprise et la personne qui va habiter le logement.
- **Conditions d’application :** Lorsque le dossier présente la situation « le motif doit être indiqué ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** La notification doit préciser le motif de la reprise et la personne qui va habiter le logement.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART24_R06_LINSTALLATION_DANS_LES_TROIS_MOIS

- **Article :** 24
- **Numéro de règle :** 6
- **Titre :** L’installation dans les trois mois
- **Explication simple :** La personne annoncée doit s’installer dans le logement au plus tard trois mois après le départ du locataire.
- **Conditions d’application :** Lorsque le dossier présente la situation « l’installation dans les trois mois ». 
- **Données nécessaires :** type_local, usage_local, date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** La personne annoncée doit s’installer dans le logement au plus tard trois mois après le départ du locataire.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART24_R07_LOCCUPATION_PENDANT_AU_MOINS_UN_AN

- **Article :** 24
- **Numéro de règle :** 7
- **Titre :** L’occupation pendant au moins un an
- **Explication simple :** La personne annoncée doit normalement habiter le logement pendant au moins une année.
- **Conditions d’application :** Lorsque le dossier présente la situation « l’occupation pendant au moins un an ». 
- **Données nécessaires :** type_local, usage_local, date_evenement, date_echeance
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** La personne annoncée doit normalement habiter le logement pendant au moins une année.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART24_R08_LEXCEPTION_POUR_EVENEMENT_GRAVE_ET_IMPREVU

- **Article :** 24
- **Numéro de règle :** 8
- **Titre :** L’exception pour événement grave et imprévu
- **Explication simple :** Une occupation de moins d’un an peut être examinée lorsqu’un événement grave et imprévu empêche la personne de continuer à habiter le logement. Le décès du bénéficiaire est un exemple mentionné par le texte.
- **Conditions d’application :** Lorsque le dossier présente la situation « l’exception pour événement grave et imprévu ». 
- **Données nécessaires :** type_local, usage_local, identite_personne, lien_avec_partie
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Une occupation de moins d’un an peut être examinée lorsqu’un événement grave et imprévu empêche la personne de continuer à habiter le logement. Le décès du bénéficiaire est un exemple mentionné par le texte.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 25 — La personne annoncée n’habite pas le logement

## ART25_R01_CONDITIONS_NON_RESPECTEES

- **Article :** 25
- **Numéro de règle :** 1
- **Titre :** Conditions non respectées
- **Explication simple :** Le propriétaire peut devoir indemniser l’ancien locataire.
- **Conditions d’application :** Lorsque le dossier présente la situation « conditions non respectées ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le propriétaire peut devoir indemniser l’ancien locataire.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART25_R02_MONTANT_MINIMAL

- **Article :** 25
- **Numéro de règle :** 2
- **Titre :** Montant minimal
- **Explication simple :** L’indemnité ne peut normalement pas être inférieure à une année de loyer.
- **Conditions d’application :** Lorsque le dossier présente la situation « montant minimal ». 
- **Données nécessaires :** montant_loyer, montants_concernes, date_evenement, date_echeance
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’indemnité ne peut normalement pas être inférieure à une année de loyer.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART25_R03_EXCEPTION

- **Article :** 25
- **Numéro de règle :** 3
- **Titre :** Exception
- **Explication simple :** Un événement grave et imprévu peut être examiné.
- **Conditions d’application :** Lorsque le dossier présente la situation « exception ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Un événement grave et imprévu peut être examiné.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 26 — Où et comment payer le loyer ?

## ART26_R01_PAIEMENT_PAR_DEFAUT

- **Article :** 26
- **Numéro de règle :** 1
- **Titre :** Paiement par défaut
- **Explication simple :** Le loyer est quérable, donc le bailleur vient normalement le percevoir.
- **Conditions d’application :** Lorsque le dossier présente la situation « paiement par défaut ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le loyer est quérable, donc le bailleur vient normalement le percevoir.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART26_R02_ACCORD_DIFFERENT

- **Article :** 26
- **Numéro de règle :** 2
- **Titre :** Accord différent
- **Explication simple :** Le contrat peut prévoir un autre mode et une adresse déterminée.
- **Conditions d’application :** Lorsque le dossier présente la situation « accord différent ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le contrat peut prévoir un autre mode et une adresse déterminée.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 27 — Le refus de louer

## ART27_R01_REFUS_VISE

- **Article :** 27
- **Numéro de règle :** 1
- **Titre :** Refus visé
- **Explication simple :** Le texte prévoit une conséquence pour un certain refus de louer un local vacant.
- **Conditions d’application :** Lorsque le dossier présente la situation « refus visé ». 
- **Données nécessaires :** type_local, usage_local
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le texte prévoit une conséquence pour un certain refus de louer un local vacant.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART27_R02_LOCATION_SI_POSSIBLE

- **Article :** 27
- **Numéro de règle :** 2
- **Titre :** Location si possible
- **Explication simple :** Le bailleur reconnu responsable devrait consentir la location si cela reste possible.
- **Conditions d’application :** Lorsque le dossier présente la situation « location si possible ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le bailleur reconnu responsable devrait consentir la location si cela reste possible.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART27_R03_REPARATION

- **Article :** 27
- **Numéro de règle :** 3
- **Titre :** Réparation
- **Explication simple :** Des dommages-intérêts peuvent être dus au candidat.
- **Conditions d’application :** Lorsque le dossier présente la situation « réparation ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Des dommages-intérêts peuvent être dus au candidat.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART27_R04_TEXTE_AMBIGU

- **Article :** 27
- **Numéro de règle :** 4
- **Titre :** Texte ambigu
- **Explication simple :** Aucune de ces règles ne doit être automatisée avant vérification de la phrase défectueuse.
- **Conditions d’application :** Lorsque le dossier présente la situation « texte ambigu ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Aucune de ces règles ne doit être automatisée avant vérification de la phrase défectueuse.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 28 — Les règles obligatoires

## ART28_R01_ORDRE_PUBLIC

- **Article :** 28
- **Numéro de règle :** 1
- **Titre :** Ordre public
- **Explication simple :** Les protections de l’ordonnance sont obligatoires.
- **Conditions d’application :** Lorsque le dossier présente la situation « ordre public ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les protections de l’ordonnance sont obligatoires.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART28_R02_CLAUSE_CONTRAIRE

- **Article :** 28
- **Numéro de règle :** 2
- **Titre :** Clause contraire
- **Explication simple :** Une clause qui écarte ces protections peut être nulle.
- **Conditions d’application :** Lorsque le dossier présente la situation « clause contraire ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Une clause qui écarte ces protections peut être nulle.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART28_R03_ACCORD_INSUFFISANT

- **Article :** 28
- **Numéro de règle :** 3
- **Titre :** Accord insuffisant
- **Explication simple :** L’accord des deux parties ne permet pas de contourner une règle obligatoire.
- **Conditions d’application :** Lorsque le dossier présente la situation « accord insuffisant ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’accord des deux parties ne permet pas de contourner une règle obligatoire.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 29 — Les violations punies

## ART29_R01_MAJORATION_ILLICITE

- **Article :** 29
- **Numéro de règle :** 1
- **Titre :** Majoration illicite
- **Explication simple :** Une augmentation illégale pratiquée volontairement est visée.
- **Conditions d’application :** Lorsque le dossier présente la situation « majoration illicite ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Une augmentation illégale pratiquée volontairement est visée.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART29_R02_CHARGES_INDUES

- **Article :** 29
- **Numéro de règle :** 2
- **Titre :** Charges indues
- **Explication simple :** Demander des charges injustifiées est visé.
- **Conditions d’application :** Lorsque le dossier présente la situation « charges indues ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Demander des charges injustifiées est visé.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART29_R03_REPRISE_ILLEGALE

- **Article :** 29
- **Numéro de règle :** 3
- **Titre :** Reprise illégale
- **Explication simple :** L’usage illégal du droit de reprise est visé.
- **Conditions d’application :** Lorsque le dossier présente la situation « reprise illégale ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’usage illégal du droit de reprise est visé.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART29_R04_REFUS_DE_LOUER

- **Article :** 29
- **Numéro de règle :** 4
- **Titre :** Refus de louer
- **Explication simple :** Le refus mentionné à l’article 27 est visé, sous réserve de clarification.
- **Conditions d’application :** Lorsque le dossier présente la situation « refus de louer ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Le refus mentionné à l’article 27 est visé, sous réserve de clarification.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART29_R05_PEINES

- **Article :** 29
- **Numéro de règle :** 5
- **Titre :** Peines
- **Explication simple :** Emprisonnement et amende sont mentionnés, mais leur actualité doit être vérifiée.
- **Conditions d’application :** Lorsque le dossier présente la situation « peines ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Emprisonnement et amende sont mentionnés, mais leur actualité doit être vérifiée.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART29_R06_DECISION_HUMAINE

- **Article :** 29
- **Numéro de règle :** 6
- **Titre :** Décision humaine
- **Explication simple :** Seul le juge peut prononcer la sanction.
- **Conditions d’application :** Lorsque le dossier présente la situation « décision humaine ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Seul le juge peut prononcer la sanction.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** justice
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 30 — La rémunération d’un intermédiaire

## ART30_R01_PLAFOND

- **Article :** 30
- **Numéro de règle :** 1
- **Titre :** Plafond
- **Explication simple :** La rémunération de l’intermédiaire ne dépasse pas quinze jours de loyer légal.
- **Conditions d’application :** Lorsque le dossier présente la situation « plafond ». 
- **Données nécessaires :** montant_loyer, montants_concernes
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** La rémunération de l’intermédiaire ne dépasse pas quinze jours de loyer légal.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** intermediaire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART30_R02_SANCTION

- **Article :** 30
- **Numéro de règle :** 2
- **Titre :** Sanction
- **Explication simple :** L’intermédiaire peut être exposé aux peines prévues par l’article 29.
- **Conditions d’application :** Lorsque le dossier présente la situation « sanction ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** L’intermédiaire peut être exposé aux peines prévues par l’article 29.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** intermediaire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

## ART30_R03_CALCUL_A_PRECISER

- **Article :** 30
- **Numéro de règle :** 3
- **Titre :** Calcul à préciser
- **Explication simple :** La méthode exacte de conversion en jours doit être validée avant programmation.
- **Conditions d’application :** Lorsque le dossier présente la situation « calcul à préciser ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** La méthode exacte de conversion en jours doit être validée avant programmation.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** A_VALIDER_PAR_JURISTE

---

# Article 31 — La première augmentation lors de l’application de l’ordonnance

## ART31_R01_LIMITE_HISTORIQUE

- **Article :** 31
- **Numéro de règle :** 1
- **Titre :** Limite historique
- **Explication simple :** La hausse était limitée à 20 % pendant la première année d’application.
- **Conditions d’application :** Lorsque le dossier présente la situation « limite historique ». 
- **Données nécessaires :** date_evenement, date_echeance
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** La hausse était limitée à 20 % pendant la première année d’application.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART31_R02_ATTENTE_DU_DECRET

- **Article :** 31
- **Numéro de règle :** 2
- **Titre :** Attente du décret
- **Explication simple :** Certaines premières hausses attendaient la publication du décret prévu.
- **Conditions d’application :** Lorsque le dossier présente la situation « attente du décret ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Certaines premières hausses attendaient la publication du décret prévu.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART31_R03_EXCEPTION

- **Article :** 31
- **Numéro de règle :** 3
- **Titre :** Exception
- **Explication simple :** Après publication, une hausse immédiate pouvait dépendre de la capacité de paiement du locataire.
- **Conditions d’application :** Lorsque le dossier présente la situation « exception ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Après publication, une hausse immédiate pouvait dépendre de la capacité de paiement du locataire.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART31_R04_USAGE_ACTUEL

- **Article :** 31
- **Numéro de règle :** 4
- **Titre :** Usage actuel
- **Explication simple :** Cette disposition transitoire ne doit pas devenir un plafond annuel permanent.
- **Conditions d’application :** Lorsque le dossier présente la situation « usage actuel ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** CALCUL_OU_COMPARAISON
- **Contrôle à réaliser :** Cette disposition transitoire ne doit pas devenir un plafond annuel permanent.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** BLOQUANT
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

# Article 32 — Les anciens contrats lors de l’entrée en vigueur

## ART32_R01_BAUX_EXISTANTS

- **Article :** 32
- **Numéro de règle :** 1
- **Titre :** Baux existants
- **Explication simple :** L’ordonnance s’appliquait aux baux déjà en cours.
- **Conditions d’application :** Lorsque le dossier présente la situation « baux existants ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** L’ordonnance s’appliquait aux baux déjà en cours.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART32_R02_PROCEDURES_EXISTANTES

- **Article :** 32
- **Numéro de règle :** 2
- **Titre :** Procédures existantes
- **Explication simple :** Elle s’appliquait aussi aux affaires judiciaires déjà engagées.
- **Conditions d’application :** Lorsque le dossier présente la situation « procédures existantes ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Elle s’appliquait aussi aux affaires judiciaires déjà engagées.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART32_R03_ACTES_ANTERIEURS

- **Article :** 32
- **Numéro de règle :** 3
- **Titre :** Actes antérieurs
- **Explication simple :** Les actes régulièrement accomplis selon les anciennes formes restaient valables.
- **Conditions d’application :** Lorsque le dossier présente la situation « actes antérieurs ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les actes régulièrement accomplis selon les anciennes formes restaient valables.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

# Article 33 — La procédure judiciaire temporaire

## ART33_R01_REGIME_TEMPORAIRE

- **Article :** 33
- **Numéro de règle :** 1
- **Titre :** Régime temporaire
- **Explication simple :** Certaines procédures étaient autorisées pendant la transition.
- **Conditions d’application :** Lorsque le dossier présente la situation « régime temporaire ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Certaines procédures étaient autorisées pendant la transition.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART33_R02_VERIFICATION_ACTUELLE

- **Article :** 33
- **Numéro de règle :** 2
- **Titre :** Vérification actuelle
- **Explication simple :** Ce choix ancien ne doit pas être proposé sans contrôler le droit actuel.
- **Conditions d’application :** Lorsque le dossier présente la situation « vérification actuelle ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Ce choix ancien ne doit pas être proposé sans contrôler le droit actuel.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

# Article 34 — La suppression des anciens textes

## ART34_R01_ABROGATION_GENERALE

- **Article :** 34
- **Numéro de règle :** 1
- **Titre :** Abrogation générale
- **Explication simple :** Toutes les dispositions contraires ont été supprimées.
- **Conditions d’application :** Lorsque le dossier présente la situation « abrogation générale ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Toutes les dispositions contraires ont été supprimées.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART34_R02_TEXTES_DESIGNES

- **Article :** 34
- **Numéro de règle :** 2
- **Titre :** Textes désignés
- **Explication simple :** Plusieurs décrets et arrêtés sont expressément mentionnés comme abrogés.
- **Conditions d’application :** Lorsque le dossier présente la situation « textes désignés ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Plusieurs décrets et arrêtés sont expressément mentionnés comme abrogés.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART34_R03_MODIFICATIONS_LIEES

- **Article :** 34
- **Numéro de règle :** 3
- **Titre :** Modifications liées
- **Explication simple :** Les textes qui modifiaient ou exécutaient les anciens textes sont également visés.
- **Conditions d’application :** Lorsque le dossier présente la situation « modifications liées ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** VERIFICATION_LOGIQUE
- **Contrôle à réaliser :** Les textes qui modifiaient ou exécutaient les anciens textes sont également visés.
- **Si conforme :** CONTINUER
- **Si non conforme :** AVERTIR_OU_BLOQUER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART34_R04_CONSERVATION_HISTORIQUE

- **Article :** 34
- **Numéro de règle :** 4
- **Titre :** Conservation historique
- **Explication simple :** Ces références servent à comprendre l’évolution du droit, pas aux formulaires actuels.
- **Conditions d’application :** Lorsque le dossier présente la situation « conservation historique ». 
- **Données nécessaires :** informations_du_dossier, justificatifs_eventuels
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Ces références servent à comprendre l’évolution du droit, pas aux formulaires actuels.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

# Article 35 — La publication des décrets

## ART35_R01_DELAI_HISTORIQUE

- **Article :** 35
- **Numéro de règle :** 1
- **Titre :** Délai historique
- **Explication simple :** Les décrets devaient être publiés avant le 1er mars 1963.
- **Conditions d’application :** Lorsque le dossier présente la situation « délai historique ». 
- **Données nécessaires :** date_evenement, date_echeance, type_document, preuve_documentaire
- **Type de contrôle :** CONTROLE_DELAI
- **Contrôle à réaliser :** Les décrets devaient être publiés avant le 1er mars 1963.
- **Si conforme :** CONTINUER
- **Si non conforme :** BLOQUER_OU_EXAMINER
- **Niveau :** OBLIGATOIRE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART35_R02_RECHERCHE_COMPLEMENTAIRE

- **Article :** 35
- **Numéro de règle :** 2
- **Titre :** Recherche complémentaire
- **Explication simple :** Il faut retrouver les décrets effectivement publiés.
- **Conditions d’application :** Lorsque le dossier présente la situation « recherche complémentaire ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Il faut retrouver les décrets effectivement publiés.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

## ART35_R03_LIMITE_DU_MOTEUR

- **Article :** 35
- **Numéro de règle :** 3
- **Titre :** Limite du moteur
- **Explication simple :** Les calculs dépendant de ces décrets ne doivent pas être inventés.
- **Conditions d’application :** Lorsque le dossier présente la situation « limite du moteur ». 
- **Données nécessaires :** type_document, preuve_documentaire
- **Type de contrôle :** EXAMEN_JURIDIQUE
- **Contrôle à réaliser :** Les calculs dépendant de ces décrets ne doivent pas être inventés.
- **Si conforme :** CONTINUER
- **Si non conforme :** TRANSMETTRE_POUR_EXAMEN
- **Niveau :** EXAMEN_JURIDIQUE
- **Public concerné :** bailleur, locataire
- **Statut juridique :** HISTORIQUE_A_NE_PAS_ACTIVER

---

# Résumé

- Articles : **35**
- Règles structurées : **133**
- Étape suivante : relire et préciser chaque condition, donnée et contrôle avant le développement.


