package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.entity.OrderDetails;
import com.web.service.OrderServiceImp;
import com.web.userRepo.ResturantRepo;

@Controller
public class ResturantController {

	// Autowiring the OrderServiceImp for handling order-related operations
	@Autowired
	OrderServiceImp service;

	// Autowiring the ResturantRepo for accessing restaurant-related data
	@Autowired
	ResturantRepo repo;

	// Instance variable to store new order details
	OrderDetails newOrderDetails;

	// Home page mapping
	@RequestMapping("/")
	public String homePage() {
		return "home";
	}

	// Order details page mapping
	@RequestMapping("/orderDetails")
	public String orderDetails() {
		return "orderDetails";
	}

	// Handling search form submission
	@PostMapping("/search")
	public String handleSearchForm(@RequestParam(name = "orderId", required = false) Long orderId, ModelMap modelMap) {

		// Invoking search operation from OrderServiceImp
		String status = service.search(orderId, modelMap);

		if (status.equalsIgnoreCase("ok")) {
			return "result"; // Redirect to the result page
		} else {
			return "invalid orderid"; // Redirect to an error page for invalid order ID
		}
	}

	// Returning to the home page and clearing dishes
	@RequestMapping("/returnHome")
	public String backToHomePage() {
		service.clearDishes();
		return "home";
	}

	// About us page mapping
	@RequestMapping("/aboutUs")
	public String aboutUsPage() {
		return "aboutUs";
	}

	// Displaying the cart contents
	@RequestMapping("/cart")
	public String displayCart(ModelMap model) {
		// Invoking viewCart operation from OrderServiceImp
		String status = service.viewCart(model);

		if (status.equalsIgnoreCase("ok")) {
			return "cart"; // Redirect to the cart page
		} else {
			return "emptyCart"; // Redirect to an error page for an empty cart
		}
	}

	// Adding a dish to the cart
	@RequestMapping("/addToCart/{dishId}")
	public String addDishToCart(@PathVariable Integer dishId) {
		// Invoking addToCart operation from OrderServiceImp
		service.addToCart(dishId);
		return "redirect:/"; // Redirect to the home page
	}

	// Removing a dish from the cart
	@RequestMapping("/remove/{dishId}")
	public String removeFromCart(@PathVariable Integer dishId) {
		// Invoking removeFromCart operation from OrderServiceImp
		service.removeFromCart(dishId);
		return "redirect:/cart"; // Redirect to the cart page
	}

	// Incrementing the quantity of a dish in the cart
	@RequestMapping("/increment/{dishId}")
	public String incrementQuantity(@PathVariable Integer dishId) {
		// Invoking addToCart operation from OrderServiceImp
		service.addToCart(dishId);
		return "redirect:/cart"; // Redirect to the cart page
	}

	// Decrementing the quantity of a dish in the cart
	@RequestMapping("/decrement/{dishId}")
	public String decrementQuantity(@PathVariable Integer dishId) {
		// Invoking decreaseQuantity operation from OrderServiceImp
		service.decreaseQuantity(dishId);
		return "redirect:/cart"; // Redirect to the cart page
	}

	// Submitting an order
	@RequestMapping(value = "/submitOrder", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
	public String submitOrder(@RequestParam("customerName") String customerName,
			@RequestParam("phoneNumber") String phoneNumber, @RequestParam("paymentMethod") String paymentMethod,
			@RequestParam("address") String address, @RequestParam("subtotalAmount") Double subtotalAmount,
			ModelMap modelMap) {

		// Invoking saveOrder operation from OrderServiceImp
		String status = service.saveOrder(customerName, phoneNumber, paymentMethod, address, subtotalAmount, modelMap);

		if ("ok".equals(status)) {
			return "orderConfirm"; // Redirect to the order confirmation page
		} else {
			return "error"; // Redirect to an error page for unsuccessful order submission
		}
	}
}
