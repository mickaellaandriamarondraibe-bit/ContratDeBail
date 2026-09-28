document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll("[data-rule-form]").forEach(initialiserControle);
});

function initialiserControle(formulaire) {
    const sortie = document.querySelector(formulaire.dataset.rulesTarget || "#rules");
    if (!sortie) return;

    const champsModifies = new Set();
    const choixBien = formulaire.elements.namedItem("bienId");
    const estFormulaireAnnonce = choixBien instanceof HTMLSelectElement;
    let minuteur;
    let requete;

    const mettreAJour = () => {
        window.clearTimeout(minuteur);
        minuteur = window.setTimeout(async () => {
            const donnees = collecterDonnees(formulaire);
            appliquerChampsConditionnels(formulaire, donnees);

            if (estFormulaireAnnonce && !choixBien.value) {
                requete?.abort();
                afficherInvitation(sortie, "Selectionnez un bien pour commencer le controle.");
                return;
            }

            requete?.abort();
            requete = new AbortController();

            try {
                const reponse = await fetch(`/controle-regles?${donnees}`, {
                    headers: { Accept: "application/json" },
                    signal: requete.signal
                });
                if (!reponse.ok) throw new Error("Controle indisponible");
                let resultats = await reponse.json();

                if (formulaire.dataset.ruleScope === "bien") {
                    resultats = filtrerReglesBien(resultats);
                } else if (estFormulaireAnnonce) {
                    resultats = filtrerReglesAnnonce(resultats, champsModifies);
                }

                afficherResultats(sortie, resultats);
            } catch (erreur) {
                if (erreur.name !== "AbortError") afficherIndisponible(sortie);
            }
        }, 180);
    };

    const reagirAuChamp = evenement => {
        if (evenement.target.name) champsModifies.add(evenement.target.name);
        mettreAJour();
    };

    formulaire.addEventListener("input", reagirAuChamp);
    formulaire.addEventListener("change", reagirAuChamp);
    mettreAJour();
}

function filtrerReglesBien(resultats) {
    return resultats.filter(({ codeRegle }) =>
        codeRegle.startsWith("ART1_") ||
        codeRegle.startsWith("ART3_") ||
        codeRegle === "ART7_R3_CONDITION_POUR_APPLIQUER_LE_MAXIMUM");
}

function filtrerReglesAnnonce(resultats, champsModifies) {
    const autoriser = codeRegle => {
        if (codeRegle === "ART1_R2_LOGEMENTS_D_HABITATION" ||
            codeRegle === "ART1_R3_HOTELS_EXCLUS" ||
            codeRegle === "ART1_R4_CERTAINS_LOCAUX_PROFESSIONNELS" ||
            codeRegle === "ART3_R1_LIBERTE_PENDANT_LES_CINQ_PREMIERES_ANNEES" ||
            codeRegle === "ART3_R2_PLAFOND_APRES_CINQ_ANS" ||
            codeRegle === "ART7_R3_CONDITION_POUR_APPLIQUER_LE_MAXIMUM") {
            return champsModifies.has("bienId");
        }

        if (codeRegle === "ART6_R1_LOGEMENT_CONCERNE" ||
            codeRegle === "ART6_R2_LOCATION_FAITE_AU_MOIS") {
            return champsModifies.has("loyer");
        }

        if (codeRegle === "ART6_R3_AUTRES_TYPES_DE_LOCATION") {
            return champsModifies.has("charges");
        }

        if (codeRegle === "ART7_R1_MAJORATION_MAXIMALE") {
            return champsModifies.has("loyer") || champsModifies.has("prixLogementNu");
        }

        if (codeRegle === "ART10_R2_SOUS_LOCATION_DE_PLUS_DES_DEUX_TIERS") {
            return champsModifies.has("sousLocation");
        }

        if (codeRegle === "ART16_R1_FIN_AUTOMATIQUE" ||
            codeRegle === "ART16_R3_TRANSFORMATION_DU_BAIL") {
            return champsModifies.has("dateDebut") || champsModifies.has("dateFin");
        }

        return false;
    };

    return resultats.filter(({ codeRegle }) => autoriser(codeRegle));
}

function collecterDonnees(formulaire) {
    const champ = nom => formulaire.elements.namedItem(nom)?.value?.trim() || "";
    const parametres = new URLSearchParams();
    let typeLogement = champ("typeLogement");
    let usage = champ("usagePrevu") || champ("usage");
    let typeBien = champ("typeBien");
    let datePermisHabiter = champ("datePermisHabiter");
    let inventaire = champ("inventaire");

    const choixBien = formulaire.elements.namedItem("bienId")?.selectedOptions?.[0];
    if (choixBien?.value) {
        typeLogement = choixBien.dataset.typeLogement || typeLogement;
        usage = choixBien.dataset.usage || usage;
        typeBien = choixBien.dataset.typeBien || typeBien;
        datePermisHabiter = choixBien.dataset.datePermisHabiter || datePermisHabiter;
        inventaire = choixBien.dataset.inventaire || inventaire;
    }

    ajouter(parametres, "typeLogement", typeLogement);
    ajouter(parametres, "usage", usage);
    ajouter(parametres, "typeBien", typeBien);
    ajouter(parametres, "datePermisHabiter", datePermisHabiter);
    ["loyer", "charges", "caution", "avance", "prixLogementNu", "dateDebut", "dateFin", "sousLocation"]
        .forEach(nom => ajouter(parametres, nom, champ(nom)));
    parametres.set("inventaireFourni", String(Boolean(inventaire)));
    return parametres;
}

function ajouter(parametres, nom, valeur) {
    if (valeur !== "") parametres.set(nom, valeur);
}

function appliquerChampsConditionnels(formulaire, donnees) {
    const meuble = donnees.get("typeLogement")?.toUpperCase().includes("MEUBLE");
    formulaire.querySelector("[data-if-furnished]")?.classList.toggle("hidden", !meuble);
}

function afficherResultats(sortie, resultats) {
    sortie.replaceChildren();
    if (!resultats.length) {
        afficherInvitation(sortie, "Continuez a renseigner le formulaire pour voir les articles applicables.");
        return;
    }

    resultats.forEach(resultat => {
        const etat = resultat.conforme ? "ok" : resultat.bloquante ? "error" : "review";
        const libelle = resultat.conforme ? "Conforme" : resultat.bloquante ? "Bloquant" : "A verifier";
        const bloc = document.createElement("div");
        bloc.className = `rule ${etat}`;
        const titre = document.createElement("strong");
        titre.textContent = `${resultat.article} - ${libelle}`;
        const texte = document.createElement("p");
        texte.textContent = resultat.explicationSimple;
        bloc.append(titre, texte);
        sortie.append(bloc);
    });
}

function afficherInvitation(sortie, texte) {
    sortie.replaceChildren();
    const message = document.createElement("p");
    message.className = "rule-empty";
    message.textContent = texte;
    sortie.append(message);
}

function afficherIndisponible(sortie) {
    afficherInvitation(sortie, "Le controle juridique est momentanement indisponible.");
}
