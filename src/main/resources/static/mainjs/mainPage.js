let currentSlide = 0;
const slides = document.querySelectorAll('.slide');
const slideCount = slides.length;

function showSlide(n) {
    slides.forEach(slide => slide.style.display = 'none');
    slides[n].style.display = 'block';
}

function nextSlide() {
    currentSlide = (currentSlide + 1) % slideCount;
    showSlide(currentSlide);
}

function prevSlide() {
    currentSlide = (currentSlide - 1 + slideCount) % slideCount;
    showSlide(currentSlide);
}

document.addEventListener('DOMContentLoaded', () => {
    showSlide(currentSlide);
    setInterval(nextSlide, 5000); // 3초마다 자동 슬라이드
});

/* 인기 레시피 */
document.addEventListener("DOMContentLoaded", function() {
    function initializeSlider(section) {
        const prevBtn = section.querySelector(".prev_btn");
        const nextBtn = section.querySelector(".next_btn");
        const track = section.querySelector(".slider_track");
        const cards = Array.from(section.querySelectorAll(".recipe_card"));
        let cardWidth = cards[0].offsetWidth + parseInt(getComputedStyle(cards[0]).marginRight);
        const cardsPerPage = 6;

        // 마지막 슬라이드 복사하여 맨 앞으로 이동
        cards.slice(-cardsPerPage).forEach(card => {
            const clone = card.cloneNode(true);
            track.insertBefore(clone, track.firstChild);
        });

        // 첫 슬라이드 복사하여 맨 뒤로 이동
        cards.slice(0, cardsPerPage).forEach(card => {
            const clone = card.cloneNode(true);
            track.appendChild(clone);
        });

        let currentPage = 1;
        track.style.transform = `translateX(-${cardsPerPage * cardWidth}px)`;

        function showCards() {
            const totalCards = cards.length;
            const totalPages = Math.ceil(totalCards / cardsPerPage) + 2; // 앞뒤로 복사된 페이지 추가
            const targetPosition = -((currentPage) * cardWidth * cardsPerPage);

            track.style.transition = "transform 0.5s ease";
            track.style.transform = `translateX(${targetPosition}px)`;
        }

        prevBtn.addEventListener("click", function() {
            currentPage--;
            showCards();

            if (currentPage === 0) {
                setTimeout(() => {
                    track.style.transition = "none";
                    currentPage = Math.ceil(cards.length / cardsPerPage);
                    track.style.transform = `translateX(-${currentPage * cardWidth * cardsPerPage}px)`;
                }, 500);
            }
        });

        nextBtn.addEventListener("click", function() {
            currentPage++;
            showCards();

            if (currentPage === Math.ceil(cards.length / cardsPerPage) + 1) {
                setTimeout(() => {
                    track.style.transition = "none";
                    currentPage = 1;
                    track.style.transform = `translateX(-${currentPage * cardWidth * cardsPerPage}px)`;
                }, 500);
            }
        });

        window.addEventListener('resize', function() {
            cardWidth = cards[0].offsetWidth + parseInt(getComputedStyle(cards[0]).marginRight);
            showCards();
        });
    }

    const sections = document.querySelectorAll(".recipe_section");
    sections.forEach(section => initializeSlider(section));
});