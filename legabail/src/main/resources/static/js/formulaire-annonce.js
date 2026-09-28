document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("annonceForm");
    if (!form) return;

    const panels = [...form.querySelectorAll("[data-panel]")];
    const tabs = [...document.querySelectorAll("[data-step]")];
    const previous = document.getElementById("previousStep");
    const next = document.getElementById("nextStep");
    const submit = document.getElementById("submitProposal");
    const selectBien = document.getElementById("bienId");
    let step = 0;
    let maximumAtteint = 0;

    function afficherEtape(index) {
        step = Math.max(0, Math.min(panels.length - 1, index));
        maximumAtteint = Math.max(maximumAtteint, step);
        panels.forEach((panel, position) => panel.classList.toggle("active", position === step));
        tabs.forEach((tab, position) => {
            tab.classList.toggle("active", position === step);
            tab.disabled = position > maximumAtteint;
        });
        const pourcentage = (step + 1) * 25;
        document.getElementById("stepLabel").textContent = `Étape ${step + 1} sur 4`;
        document.getElementById("stepPercent").textContent = `${pourcentage} %`;
        document.getElementById("stepBar").style.width = `${pourcentage}%`;
        previous.disabled = step === 0;
        next.classList.toggle("hidden", step === panels.length - 1);
        submit.classList.toggle("hidden", step !== panels.length - 1);
        mettreAJourResume();
        window.scrollTo({ top: 0, behavior: "smooth" });
    }

    function etapeValide() {
        const invalides = [...panels[step].querySelectorAll("input, select, textarea")]
            .filter(champ => !champ.checkValidity());
        if (!invalides.length) return true;
        invalides[0].reportValidity();
        return false;
    }

    function texteChamp(nom, defaut = "Non renseigné") {
        const champ = form.elements.namedItem(nom);
        if (!champ?.value) return defaut;
        if (champ instanceof HTMLSelectElement) {
            return champ.selectedOptions[0]?.textContent || defaut;
        }
        return champ.value;
    }

    function mettreAJourBien() {
        const option = selectBien.selectedOptions[0];
        const preview = document.getElementById("bienPreview");
        const selectionne = Boolean(option?.value);
        preview.classList.toggle("hidden", !selectionne);
        if (!selectionne) return;
        document.getElementById("previewType").textContent = option.dataset.typeBien || "Non renseigné";
        document.getElementById("previewUsage").textContent = option.dataset.usage || "Non renseigné";
        document.getElementById("previewLogement").textContent = option.dataset.typeLogement === "MEUBLE" ? "Meublé" : "Nu";
        document.getElementById("previewPermis").textContent = option.dataset.datePermisHabiter || "Non renseigné";
    }

    function ajouterResume(conteneur, titre, valeur) {
        const bloc = document.createElement("div");
        const libelle = document.createElement("b");
        const contenu = document.createElement("span");
        libelle.textContent = titre;
        contenu.textContent = valeur;
        bloc.append(libelle, contenu);
        conteneur.append(bloc);
    }

    function mettreAJourResume() {
        const resume = document.getElementById("proposalSummary");
        resume.replaceChildren();
        ajouterResume(resume, "Bien", texteChamp("bienId"));
        ajouterResume(resume, "Loyer", `${texteChamp("loyer", "0")} Ar`);
        ajouterResume(resume, "Charges", `${texteChamp("charges", "0")} Ar`);
        ajouterResume(resume, "Caution + avance", `${Number(form.elements.caution.value || 0) + Number(form.elements.avance.value || 0)} Ar`);
        ajouterResume(resume, "Durée", form.elements.dateFin.value
            ? `${texteChamp("dateDebut")} au ${texteChamp("dateFin")}`
            : `À partir du ${texteChamp("dateDebut")}, durée indéterminée`);
        ajouterResume(resume, "Sous-location", texteChamp("sousLocation"));
        ajouterResume(resume, "Paiement", texteChamp("modePaiement"));
        ajouterResume(resume, "Clauses", texteChamp("clausesSpeciales", "Aucune"));
    }

    previous.addEventListener("click", () => afficherEtape(step - 1));
    next.addEventListener("click", () => {
        if (etapeValide()) afficherEtape(step + 1);
    });
    tabs.forEach(tab => tab.addEventListener("click", () => {
        const destination = Number(tab.dataset.step);
        if (destination <= maximumAtteint && (destination < step || etapeValide())) {
            afficherEtape(destination);
        }
    }));
    selectBien.addEventListener("change", mettreAJourBien);
    form.addEventListener("input", mettreAJourResume);
    form.addEventListener("change", mettreAJourResume);
    form.addEventListener("submit", evenement => {
        if (!form.checkValidity()) {
            evenement.preventDefault();
            const invalide = form.querySelector(":invalid");
            const panel = invalide?.closest("[data-panel]");
            if (panel) afficherEtape(Number(panel.dataset.panel));
            invalide?.reportValidity();
        }
    });

    mettreAJourBien();
    afficherEtape(0);
});
