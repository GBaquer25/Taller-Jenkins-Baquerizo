package mavendining;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DinnercalculatorTest {
	
    @Test
    void testCalculateTotalCost_SinDiscount() {
        List<MealOrder> selectedMeals = new ArrayList<>();
        selectedMeals.add(new MealOrder("Kimbap", 15.0, 3));
        selectedMeals.add(new MealOrder("Paella", 12.0, 2));
        selectedMeals.add(new MealOrder("Lechona", 18.0, 4));

        double totalCost = Dinnercalculator.calculateTotalCost(selectedMeals);

        double expectedTotalCost = (15.0 * 3) + (12.0 * 2) + (18.0 * 4);
        Assertions.assertEquals(expectedTotalCost, totalCost, 0.001);
    }

    @Test
    void testCalculateTotalCost_WithSpecialDiscount() {
        List<MealOrder> selectedMeals = new ArrayList<>();
        selectedMeals.add(new MealOrder("Kimbap", 15.0, 10));
        selectedMeals.add(new MealOrder("Paella", 12.0, 5));
        selectedMeals.add(new MealOrder("Lechona", 18.0, 7));

        double totalCost = Dinnercalculator.calculateTotalCost(selectedMeals);

        double expectedTotalCost = (15.0 * 10) + (12.0 * 5) + (18.0 * 7);
        expectedTotalCost *= 0.8; 
        Assertions.assertEquals(expectedTotalCost, totalCost, 0.001);
    }

    @Test
    void testCalculateTotalCost_WithDiscount() {
        List<MealOrder> selectedMeals = new ArrayList<>();
        selectedMeals.add(new MealOrder("Kimbap", 15.0, 6));
        selectedMeals.add(new MealOrder("Paella", 12.0, 8));
        selectedMeals.add(new MealOrder("Lechona", 18.0, 3));

        double totalCost = Dinnercalculator.calculateTotalCost(selectedMeals);

        double expectedTotalCost = (15.0 * 6) + (12.0 * 8) + (18.0 * 3);
        expectedTotalCost *= 0.9;
        Assertions.assertEquals(expectedTotalCost, totalCost, 0.001);
    }
}
