(() => {
    const header = document.querySelector('.site-header');
    if (!header) return;
    const back = header.querySelector('.site-back');
    back?.addEventListener('click', event => {
        if (event.button !== 0 || event.ctrlKey || event.metaKey || event.shiftKey || event.altKey) return;
        if (document.referrer && new URL(document.referrer).origin === window.location.origin
                && window.history.length > 1) {
            event.preventDefault();
            window.history.back();
        }
    });
    const path = window.location.pathname.replace(/\/$/, '') || '/';
    const links = [...header.querySelectorAll('nav a')];
    const active = links
        .filter(link => {
            const href = new URL(link.href).pathname.replace(/\/$/, '') || '/';
            return path === href || (href !== '/' && path.startsWith(href + '/'));
        })
        .sort((a, b) => b.pathname.length - a.pathname.length)[0];
    if (active) active.setAttribute('aria-current', 'page');
    const account = header.querySelector('.account-menu');
    if (!account) return;
    document.addEventListener('click', event => {
        if (!account.contains(event.target)) account.open = false;
    });
    header.addEventListener('keydown', event => {
        if (event.key === 'Escape' && account.open) {
            account.open = false;
            account.querySelector('summary').focus();
        }
    });
})();
