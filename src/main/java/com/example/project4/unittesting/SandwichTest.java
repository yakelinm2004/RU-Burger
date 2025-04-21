package com.example.project4.unittesting;

import com.example.project4.model.AddOns;
import com.example.project4.model.Bread;
import com.example.project4.model.Protein;
import com.example.project4.model.Sandwich;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class SandwichTest {

    @Test
    public void testRoastBeefSandwichWithCheeseAndLettuce() {
        ArrayList<AddOns> addOns = new ArrayList<>(List.of(AddOns.CHEESE, AddOns.LETTUCE));
        Sandwich sandwich = new Sandwich(Bread.BRIOCHE, Protein.ROAST_BEEF, addOns, 1);
        double expected = (10.99 + 1.00 + 0.30) * 1;
        assertEquals(expected, sandwich.price(), 0.01);
    }

    @Test
    public void testSalmonSandwichWithAvocadoOnly() {
        ArrayList<AddOns> addOns = new ArrayList<>(List.of(AddOns.AVOCADO));
        Sandwich sandwich = new Sandwich(Bread.WHEAT, Protein.SALMON, addOns, 2);
        double expected = (9.99 + 0.50) * 2;
        assertEquals(expected, sandwich.price(), 0.01);
    }

    @Test
    public void testChickenSandwichNoAddOns() {
        ArrayList<AddOns> addOns = new ArrayList<>();
        Sandwich sandwich = new Sandwich(Bread.PRETZEL, Protein.CHICKEN, addOns, 3);
        double expected = 8.99 * 3;
        assertEquals(expected, sandwich.price(), 0.01);
    }
}
