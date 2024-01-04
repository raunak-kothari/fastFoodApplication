'use strict';


function scrollToFooter() {
	// Get the footer element by its id
	var footer = document.getElementById('footer');

	// Use smooth scrolling behavior to scroll to the footer
	footer.scrollIntoView({ behavior: 'smooth' });
}

/**
 * navbar toggle
 */

const navbar = document.querySelector("[data-navbar]");
const navbarLinks = document.querySelectorAll("[data-nav-link]");
const menuToggleBtn = document.querySelector("[data-menu-toggle-btn]");

menuToggleBtn.addEventListener("click", function() {
	navbar.classList.toggle("active");
	this.classList.toggle("active");
});

for (let i = 0; i < navbarLinks.length; i++) {
	navbarLinks[i].addEventListener("click", function() {
		navbar.classList.toggle("active");
		menuToggleBtn.classList.toggle("active");
	});
}

/**
 * header sticky & back to top
 */

const header = document.querySelector("[data-header]");
const backTopBtn = document.querySelector("[data-back-top-btn]");

window.addEventListener("scroll", function() {
	if (window.scrollY >= 100) {
		header.classList.add("active");
		backTopBtn.classList.add("active");
	} else {
		header.classList.remove("active");
		backTopBtn.classList.remove("active");
	}
});

/**
 * search box toggle
 */

const searchBtn = document.querySelector("[data-search-btn]");
const searchContainer = document.querySelector("[data-search-container]");
const searchSubmitBtn = document.querySelector("[data-search-submit-btn]");
const searchCloseBtn = document.querySelector("[data-search-close-btn]");

const searchBoxElems = [searchBtn, searchSubmitBtn, searchCloseBtn];

for (let i = 0; i < searchBoxElems.length; i++) {
	searchBoxElems[i].addEventListener("click", function() {
		searchContainer.classList.toggle("active");
		document.body.classList.toggle("active");
	});
}

/**
 * move cycle on scroll
 */

const deliveryBoy = document.querySelector("[data-delivery-boy]");

let deliveryBoyMove = -80;
let lastScrollPos = 0;

window.addEventListener("scroll", function() {
	let deliveryBoyTopPos = deliveryBoy.getBoundingClientRect().top;

	if (deliveryBoyTopPos < 500 && deliveryBoyTopPos > -250) {
		let activeScrollPos = window.scrollY;

		if (lastScrollPos < activeScrollPos) {
			deliveryBoyMove += 1;
		} else {
			deliveryBoyMove -= 1;
		}

		lastScrollPos = activeScrollPos;
		deliveryBoy.style.transform = `translateX(${deliveryBoyMove}px)`;
	}
});

// Assuming you're using plain JavaScript (you can adapt for jQuery or other libraries if needed)
document.addEventListener('DOMContentLoaded', function() {
	// Get all Add To Cart buttons
	var addToCartButtons = document.querySelectorAll('.add-to-cart-btn');

	// Attach click event listener to each button
	addToCartButtons.forEach(function(button) {
		button.addEventListener('click', function(event) {
			event.preventDefault();

			// Get the product ID from the data-product-id attribute
			var productId = button.getAttribute('data-product-id');

			// Send a POST request to the server to add the product to the cart
			fetch('/addToCart', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/x-www-form-urlencoded',
				},
				body: 'productId=' + encodeURIComponent(productId),
			})
				.then(function(response) {
					return response.text();
				})
				.then(function(responseText) {
					// Handle the response from the server (if needed)
					console.log(responseText);
				})
				.catch(function(error) {
					console.error('Error:', error);
				});
		});
	});
});