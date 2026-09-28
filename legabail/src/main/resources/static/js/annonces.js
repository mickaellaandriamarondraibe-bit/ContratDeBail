document.addEventListener("DOMContentLoaded", () => {
    const grille = document.getElementById("listingGrid");
    const bouton = document.getElementById("searchListings");
    if (!grille || !bouton) return;

    const cartes = [...grille.querySelectorAll(".listing-card")];
    const recherche = document.getElementById("searchLocation");
    const type = document.getElementById("searchType");
    const prix = document.getElementById("searchPrice");
    const compteur = document.getElementById("listingCount");
    const vide = document.getElementById("listingEmpty");

    const filtrer = () => {
        const lieu = recherche.value.trim().toLocaleLowerCase("fr");
        const typeChoisi = type.value;
        const prixMaximum = Number(prix.value || Number.POSITIVE_INFINITY);
        let visibles = 0;

        cartes.forEach(carte => {
            const correspond = (!lieu || carte.dataset.location.toLocaleLowerCase("fr").includes(lieu))
                && (!typeChoisi || carte.dataset.type === typeChoisi)
                && Number(carte.dataset.price) <= prixMaximum;
            carte.classList.toggle("hidden", !correspond);
            if (correspond) visibles++;
        });

        compteur.textContent = `${visibles} logement(s) trouvé(s)`;
        vide.classList.toggle("hidden", visibles !== 0);
    };

    bouton.addEventListener("click", filtrer);
    recherche.addEventListener("input", filtrer);
    type.addEventListener("change", filtrer);
    prix.addEventListener("change", filtrer);
});
