(() => {
    const root = document.body;
    const targetValue = root.dataset.eventDate;

    if (targetValue) {
        const target = new Date(targetValue).getTime();
        const ids = {
            days: document.getElementById('days'),
            hours: document.getElementById('hours'),
            minutes: document.getElementById('minutes'),
            seconds: document.getElementById('seconds')
        };

        const updateCountdown = () => {
            const remaining = Math.max(0, target - Date.now());
            const day = 86_400_000;
            const hour = 3_600_000;
            const minute = 60_000;

            ids.days.textContent = Math.floor(remaining / day);
            ids.hours.textContent = Math.floor((remaining % day) / hour).toString().padStart(2, '0');
            ids.minutes.textContent = Math.floor((remaining % hour) / minute).toString().padStart(2, '0');
            ids.seconds.textContent = Math.floor((remaining % minute) / 1000).toString().padStart(2, '0');
        };

        updateCountdown();
        setInterval(updateCountdown, 1000);
    }

    const items = document.querySelectorAll('.reveal');
    if ('IntersectionObserver' in window) {
        const observer = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    entry.target.classList.add('visible');
                    observer.unobserve(entry.target);
                }
            });
        }, { threshold: 0.12 });
        items.forEach(item => observer.observe(item));
    } else {
        items.forEach(item => item.classList.add('visible'));
    }
})();
