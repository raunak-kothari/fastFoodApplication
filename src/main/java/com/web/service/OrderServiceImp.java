package com.web.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.web.entity.Dishes;
import com.web.entity.OrderDetails;
import com.web.userRepo.DishesRepo;
import com.web.userRepo.ResturantRepo;

@Service
public class OrderServiceImp {

	private List<Dishes> dishesInCart = new ArrayList<>();
	private List<Dishes> dishesOrdered = new ArrayList<>();
	private OrderDetails savedDetails;
	boolean newOrder = false;

	long temp_orederid;

	@Autowired
	private ResturantRepo repo;

	@Autowired
	private DishesRepo dishRepo;

	public List<Dishes> getDishesInCart() {
		return new ArrayList<>(dishesInCart);
	}

	// to add items into the cart
	public String addToCart(Integer dishId) {
		Optional<Dishes> optionalDish = dishRepo.findById(dishId);

		if (optionalDish.isPresent()) {
			Dishes d1 = optionalDish.get();

			boolean found = false;

			for (Dishes dish : dishesInCart) {
				if (dish.getDishId() == dishId) {

					if (dish.getDishQuantity() == null) {
						dish.setDishQuantity(1);
					} else {

						dish.setDishQuantity(dish.getDishQuantity() + 1);
					}
					// d1.setDishQuantity(newQuantity);
					dishRepo.save(d1);

					found = true;
					break;
				}
			}

			if (!found) {
				// Dish not found in the cart, add a new entry
				d1.setDishQuantity(1);
				dishesInCart.add(d1);
			}

			return "ok";
		} else {
			// Handle the case where the dish with the given ID is not found
			System.out.println("Dish not found with ID: " + dishId);
			return "not-found";
		}
	}

	public String decreaseQuantity(Integer dishId) {
		Optional<Dishes> optionalDish = dishRepo.findById(dishId);

		if (optionalDish.isPresent()) {
			Dishes d1 = optionalDish.get();

			boolean found = false;

			for (Dishes dish : dishesInCart) {
				if (dish.getDishId() == dishId) {
					if (dish.getDishQuantity() == null || dish.getDishQuantity() <= 1) {
						// If the quantity is null or already 1, remove the item from the cart
						dishesInCart.remove(dish);
					} else {
						dish.setDishQuantity(dish.getDishQuantity() - 1);
					}

					dishRepo.save(d1);

					found = true;
					break;
				}
			}

			if (!found) {
				// Handle the case where the dish with the given ID is not found in the cart
				System.out.println("Dish not found in the cart with ID: " + dishId);
				return "not-found";
			}

			return "ok";
		} else {
			// Handle the case where the dish with the given ID is not found
			System.out.println("Dish not found with ID: " + dishId);
			return "not-found";
		}
	}

	public String saveOrder(String customerName, String phoneNumber, String paymentMethod, String address,
			Double subtotalAmount, ModelMap modelMap) {

// Assuming you have an OrderDetails entity
		OrderDetails orderDetails = new OrderDetails();

		orderDetails.setCustomerName(customerName);
		orderDetails.setPhoneNumber(phoneNumber);
		orderDetails.setPaymentMethod(paymentMethod);
		orderDetails.setAddress(address);
		orderDetails.setSubtotalAmount(subtotalAmount);

		LocalDateTime myDateTime = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy h:mm a");
		String formattedDate = myDateTime.format(formatter);
		orderDetails.setMyDateTime(formattedDate);

		String finalDetails = listDishesToString(dishesInCart);

		orderDetails.setDishes(finalDetails);

		if (newOrder == false)

		{
			newOrder = true;

			// Save the order details to get the generated order ID
			orderDetails = repo.save(orderDetails);

			temp_orederid = orderDetails.getOrderId();

			modelMap.put("command", orderDetails);
			modelMap.addAttribute("dishesList", dishesInCart);
			return "ok";
		} else {
			orderDetails.setOrderId(temp_orederid);
			modelMap.put("command", orderDetails);
			modelMap.addAttribute("dishesList", dishesInCart);
			return "ok";
		}

	}

	public void clearCart() {

		dishesInCart.clear();
	}

	private String listDishesToString(List<Dishes> dishesList) {
		StringBuilder result = new StringBuilder("[");
		for (int i = 0; i < dishesList.size(); i++) {
			result.append(dishesList.get(i).toString());
			if (i < dishesList.size() - 1) {
				result.append(", ");
			}
		}
		result.append("]");
		return result.toString();
	}

	public void removeFromCart(Integer dishId) {
		for (Dishes dish : dishesInCart) {
			if (dish.getDishId() == dishId) {

				dishesInCart.remove(dish);
				break;
			}

		}

	}

	public String search(Long orderId, ModelMap modelMap) {
		// Add logic to process the search data, such as querying the database

		Optional<OrderDetails> optionalDetails = repo.findById(orderId);

		if (optionalDetails.isPresent()) {

			OrderDetails details = optionalDetails.get();

			modelMap.put("command", details);

			String s = details.getDishes();

			// Define the pattern for extracting dishId and dishQuantity
			Pattern pattern = Pattern.compile("dishId=(\\d+).*dishQuantity=(\\d+)");

			// Create a matcher with the input string
			Matcher matcher = pattern.matcher(s);

			// Find the first occurrence
			if (matcher.find()) {
				// Extract the values of dishId and dishQuantity
				String dishIdValue = matcher.group(1);
				String dishQuantityValue = matcher.group(2);

				// Convert the Strings to integers
				int dishId = Integer.parseInt(dishIdValue);
				int dishQuantity = Integer.parseInt(dishQuantityValue);

				dishesOrdered.clear();

				Dishes dish = dishRepo.findById(dishId).get();

				dish.setDishQuantity(dishQuantity);
				dishesOrdered.add(dish);

				modelMap.addAttribute("dishesList", dishesOrdered);

			}

			return "ok";

		} else

		{
			return "notOk";
		}

	}

	public String viewCart(ModelMap model) {

		if (dishesInCart.isEmpty()) {
			return "empty";
		} else {
			model.addAttribute("dishesList", dishesInCart);
			return "ok";
		}

	}

	public void clearDishes() {
		dishesInCart.clear();
	}

}