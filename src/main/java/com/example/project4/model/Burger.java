package com.example.project4.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a burger, which is a type of sandwich with additional functionality
 * for a double patty option and specific pricing logic based on patty count and add-ons.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class Burger extends Sandwich {

    private boolean doublePatty;

    /**
     * Constructs a Burger object with specified bread, protein, add-ons, quantity, and double patty option.
     * @param bread      The type of bread for the burger.
     * @param protein    The type of protein for the burger (e.g., beef, chicken).
     * @param addOns     A list of add-ons for the burger (e.g., lettuce, tomato).
     * @param quantity   The number of burgers.
     * @param doublePatty Whether the burger has a double patty or not.
     */
    public Burger(Bread bread, Protein protein, ArrayList<AddOns> addOns, int quantity, boolean doublePatty) {
        super(bread, protein, addOns, quantity);
        this.doublePatty = doublePatty;
    }

    /**
     * Calculates the price of the burger based on the base patty price, the double patty option,
     * and the total cost of add-ons. The price is then multiplied by the quantity.
     * @return The total price of the burger.
     */
    @Override
    public double price() {
        double pattyPrice = 6.99; // Base price for a single patty burger
        if (doublePatty) {
            pattyPrice += 2.50; // Additional cost for double patty
        }

        double addOnTotal = 0.0;
        for (AddOns addOn : addOns) {
            addOnTotal += addOn.getPrice(); // Adding the price of each add-on
        }

        return (pattyPrice + addOnTotal) * quantity; // Total price for all burgers
    }

    /**
     * Sets whether the burger has a double patty.
     * @param doublePatty The double patty option.
     */
    public void setDoublePatty(boolean doublePatty) {
        this.doublePatty = doublePatty;
    }

    /**
     * Compares this burger to another object for equality. Two burgers are considered equal if they have
     * the same quantity, bread, protein, add-ons, and double patty status.
     * @param obj The object to compare this burger to.
     * @return true if the two burgers are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Burger)) return false;
        Burger other = (Burger) obj;
        return doublePatty == other.doublePatty &&
                quantity == other.quantity &&
                bread == other.bread &&
                protein == other.protein &&
                addOns.equals(other.addOns);
    }

    /**
     * Returns a hash code for this burger based on its bread, protein, add-ons, quantity, and double patty status.
     * @return The hash code of the burger.
     */
    @Override
    public int hashCode() {
        return Objects.hash(bread, protein, addOns, quantity, doublePatty);
    }

    /**
     * Returns whether the burger has a double patty.
     * @return true if the burger has a double patty, false otherwise.
     */
    public boolean isDoublePatty() {
        return doublePatty;
    }

    /**
     * Returns a string representation of the burger, including the quantity, protein, bread type,
     * add-ons (if any), and price.
     * @return A string representation of the burger.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(quantity).append("x ");
        sb.append(protein).append(" Burger on ").append(bread);

        if (!addOns.isEmpty()) {
            sb.append(" with ");
            for (int i = 0; i < addOns.size(); i++) {
                sb.append(addOns.get(i).name().toLowerCase().replace("_", " "));
                if (i < addOns.size() - 1) sb.append(", ");
            }
        }

        sb.append(String.format(" - $%.2f", price()));
        return sb.toString();
    }
}
