package com.example.project4.unittesting;

import com.example.project4.model.AddOns;
import com.example.project4.model.Bread;
import com.example.project4.model.Burger;
import com.example.project4.model.Protein;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BurgerTest {

    @Test
    public void testSinglePattyBurgerWithCheese() {
        ArrayList<AddOns> addOns = new ArrayList<>(List.of(AddOns.CHEESE));
        Burger burger = new Burger(Bread.BRIOCHE, Protein.ROAST_BEEF, addOns, 1, false);
        double expected = (6.99 + 1.00) * 1;
        assertEquals(expected, burger.price(), 0.01);
    }

    @Test
    public void testDoublePattyBurgerWithAvocadoAndTomato() {
        ArrayList<AddOns> addOns = new ArrayList<>(List.of(AddOns.AVOCADO, AddOns.TOMATOES)); // 1.00 + 0.50 = 1.50
        Burger burger = new Burger(Bread.WHEAT, Protein.ROAST_BEEF, addOns, 2, true);
        double expected = (6.99+2.50+0.50+0.30) * 2;
        assertEquals(expected, burger.price(), 0.01);
    }

}
