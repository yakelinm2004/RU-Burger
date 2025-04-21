package com.example.project4.model;

import java.util.Objects;

/**
 * Represents a combo meal, consisting of a sandwich, a beverage, and a side.
 * The combo also includes a quantity and calculates the total price based on the components and quantity.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class Combo extends MenuItem {

    private Sandwich sandwich;
    private Beverage drink;

    /**
     * The side part of the combo meal.
     */
    private Side side;

    /**
     * Constructs a Combo object with a sandwich, drink, side, and quantity.
     * @param sandwich The sandwich in the combo.
     * @param drink The beverage in the combo.
     * @param side The side item in the combo.
     * @param quantity The quantity of the combo meals.
     */
    public Combo(Sandwich sandwich, Beverage drink, Side side, int quantity) {
        this.sandwich = sandwich;
        this.drink = drink;
        this.side = side;
        this.quantity = quantity;
    }

    /**
     * Calculates the price of the combo meal. The price of the sandwich, beverage, and side is summed up,
     * and a $2.00 surcharge is added to the total. The result is then multiplied by the quantity.
     * @return The total price of the combo meal.
     */
    @Override
    public double price() {
        return (sandwich.price() + 2.00) * quantity; // Adds a surcharge and multiplies by quantity
    }

    /**
     * Returns a string representation of the combo meal, including the quantity, sandwich details,
     * side details, drink details, and total price.
     * @return A string representing the combo meal.
     */
    @Override
    public String toString() {
        return String.format("%dx Combo: %s + %s + %s = $%.2f",
                quantity,
                sandwich.toString(),
                side.toString(),
                drink.toString(),
                price());
    }

    /**
     * Compares this combo meal to another object for equality. Two combos are considered equal if they have
     * the same quantity, sandwich, drink, and side.
     * @param obj The object to compare this combo meal to.
     * @return true if the two combo meals are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Combo)) return false;
        Combo other = (Combo) obj;
        return quantity == other.quantity &&
                sandwich.equals(other.sandwich) &&
                drink.equals(other.drink) &&
                side.equals(other.side);
    }

    /**
     * Returns a hash code for this combo meal based on its sandwich, drink, side, and quantity.
     * @return The hash code of the combo meal.
     */
    @Override
    public int hashCode() {
        return Objects.hash(sandwich, drink, side, quantity);
    }
}
