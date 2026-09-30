(() => {
    const input = document.getElementById('image');
    const preview = document.getElementById('photosPreview');
    let urls = [];
    function actualiser() {
        urls.forEach(url => URL.revokeObjectURL(url));
        urls = [];
        preview.replaceChildren();
        const fichiers = [...input.files];
        const conservees = [...document.querySelectorAll('input[name="photosSupprimees"]')].filter(c => !c.checked).length;
        const erreur = fichiers.length + conservees > 10 ? 'Maximum 10 photos par bien.'
            : fichiers.some(f => f.size > 5 * 1024 * 1024) ? 'Chaque photo doit faire au maximum 5 Mo.' : '';
        input.setCustomValidity(erreur);
        if (erreur) { preview.textContent = erreur; return; }
        fichiers.forEach(fichier => {
            const figure = document.createElement('figure');
            figure.className = 'photo-thumbnail';
            const img = document.createElement('img');
            img.src = URL.createObjectURL(fichier);
            urls.push(img.src);
            img.alt = fichier.name;
            const caption = document.createElement('figcaption');
            caption.textContent = fichier.name;
            figure.append(img, caption);
            preview.append(figure);
        });
    }
    input.addEventListener('change', actualiser);
    document.querySelectorAll('input[name="photosSupprimees"]').forEach(c => c.addEventListener('change', actualiser));
})();
