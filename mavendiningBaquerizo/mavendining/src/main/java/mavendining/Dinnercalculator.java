package mavendining;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Dinnercalculator {

	private static final List<MealOrder> MENU = new ArrayList<>();
	static {
		MENU.add(new MealOrder("Kimbap", 15.0));
		MENU.add(new MealOrder("Paella", 12.0));
		MENU.add(new MealOrder("Lechona", 18.0));
		MENU.add(new MealOrder("Sunday Roast", 11.5));
		MENU.add(new MealOrder("Ají de Gallina", 9.0));
		MENU.add(new MealOrder("Hojaldra", 5.0));
		MENU.add(new MealOrder("Encebollado", 30.0));
		
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome to the 'Rinconcito del Sabor'");
		System.out.println("Today we have the following menu:");

		// Muestra el menú al usuario
		for (MealOrder meal : MENU) {
			System.out.println(meal.getMealName() + " - $" + meal.getPrice());
		}

		List<MealOrder> selectedMeals = new ArrayList<>();

		while (true) {
			System.out.print("Enter the meal name (or 'done' to finish the order): ");
			String mealName = scanner.nextLine().trim();

			if (mealName.equalsIgnoreCase("done")) {
				break;
			}

			MealOrder selectedMeal = findMealInMenu(mealName);

			if (selectedMeal == null) {
				System.out.println("Error: Meal not available on the menu. Please re-select.");
				continue;
			}

			System.out.print("Please enter the quantity for " + selectedMeal.getMealName() + ": ");
			int quantity = Integer.parseInt(scanner.nextLine());

			if (quantity <= 0 || quantity > 100) {
				System.out.println("Error: Quantity must be a positive integer between 1 and 100. Please re-enter.");
				continue;
			}


			selectedMeal.setQuantity(quantity);
			selectedMeals.add(selectedMeal);
		}

		// Calculate the count
		double totalCost = calculateTotalCost(selectedMeals);

		
		// User' confirmation: quantities and meals
		System.out.println("Selected Meals and Quantities:");
		for (MealOrder selectedMeal : selectedMeals) {
			System.out.println(selectedMeal.getMealName() + " x " + selectedMeal.getQuantity());
		}
		System.out.println("Total Cost: $" + totalCost);


		System.out.print("Is correct your order? (yes/no): ");
		String confirmation = scanner.nextLine().trim();

		//The total cost 
		if (confirmation.equalsIgnoreCase("yes")) {
			System.out.println("Order confirmed. Total cost: $" + totalCost);
		} else {
			System.out.println("Your order was canceled.");
		}
	}


	public static MealOrder findMealInMenu(String mealName) {
		for (MealOrder meal : MENU) {
			if (meal.getMealName().equalsIgnoreCase(mealName)) {
				return meal;
			}
		}
		return null;
	}

	public static double calculateTotalCost(List<MealOrder> selectedMeals) {
		double totalCost = 0.0;
		int totalQuantity = 0;
		
		for (MealOrder selectedMeal : selectedMeals) {
			totalCost += selectedMeal.getPrice() * selectedMeal.getQuantity();
            totalQuantity += selectedMeal.getQuantity();
		}

		//Descounts
		 if (totalQuantity > 5 && totalQuantity <= 10) {
	            totalCost *= 0.9; // Aplica un 10% de descuento
	        } else if (totalQuantity > 10) {
	            totalCost *= 0.8; // Aplica un 20% de descuento
	        }

	        // Special Descounts
	        if (totalCost > 100) {
	            totalCost -= 25.0;
	        } else if (totalCost > 50) {
	            totalCost -= 10.0;
	        }

	        // 5% of descount
	        boolean hasSpecialCategory = selectedMeals.stream().anyMatch(meal -> meal.isSpecialCategory());
	        if (hasSpecialCategory) {
	            totalCost *= 1.05;
	        }

		return totalCost;
	}
}

class MealOrder {
	private String mealName;
	private double price;
	private int quantity;

	public MealOrder(String mealName, double price) {
		this.mealName = mealName;
		this.price = price;
		this.quantity = 0;
	}

	public String getMealName() {
		return mealName;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	 public boolean isSpecialCategory() {
		 return mealName.toLowerCase().contains("special");
	    }
}
