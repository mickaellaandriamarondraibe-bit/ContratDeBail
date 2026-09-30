document.querySelectorAll('[data-gallery]').forEach(gallery => {
    const track = gallery.querySelector('.gallery-track');
    const photos = [...track.querySelectorAll('img')];
    const count = gallery.querySelector('.gallery-count');
    if (photos.length < 2) return;
    let index = 0;
    const aller = direction => {
        index = (index + direction + photos.length) % photos.length;
        track.scrollTo({left: index * track.clientWidth, behavior: 'auto'});
    };
    gallery.querySelector('.gallery-prev').addEventListener('click', () => aller(-1));
    gallery.querySelector('.gallery-next').addEventListener('click', () => aller(1));
    gallery.addEventListener('keydown', event => {
        if (event.key === 'ArrowLeft' || event.key === 'ArrowRight') {
            event.preventDefault();
            aller(event.key === 'ArrowLeft' ? -1 : 1);
        }
    });
    track.addEventListener('scroll', () => {
        if (!track.clientWidth) return;
        index = Math.max(0, Math.min(photos.length - 1, Math.round(track.scrollLeft / track.clientWidth)));
        count.textContent = `${index + 1} / ${photos.length}`;
    }, {passive: true});
    new ResizeObserver(() => {
        if (track.clientWidth) track.scrollLeft = index * track.clientWidth;
    }).observe(track);
});
