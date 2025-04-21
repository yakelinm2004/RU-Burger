package com.example.project4.model;

import java.util.Objects;

/**
 * Represents a beverage item on the menu with a size, flavor, and quantity.
 * This class calculates the price based on the size and quantity of the beverage.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class Beverage extends MenuItem {

    private Size size;
    private Flavor flavor;

    /**
     * Constructor to create a Beverage object with a specific size, flavor, and quantity.
     * @param size     The size of the beverage.
     * @param flavor   The flavor of the beverage.
     * @param quantity The quantity of beverages ordered.
     */
    public Beverage(Size size, Flavor flavor, int quantity) {
        this.size = size;
        this.flavor = flavor;
        this.quantity = quantity;
    }

    /**
     * Calculates the price of the beverage based on its size and quantity.
     * The price is determined by the size of the beverage, and then multiplied by the quantity.
     * @return The total price of the beverage.
     */
    @Override
    public double price() {
        double base = switch (size) {
            case SMALL -> 1.99;
            case MEDIUM -> 2.49;
            case LARGE -> 2.99;
        };
        return base * quantity;
    }

    /**
     * Returns the flavor of the beverage.
     * @return The flavor of the beverage.
     */
    public Flavor getFlavor() {
        return flavor;
    }

    /**
     * Returns the size of the beverage.
     * @return The size of the beverage.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Returns a string representation of the beverage including the quantity, size, flavor, and price.
     * @return A string representation of the beverage.
     */
    @Override
    public String toString() {
        return String.format("%dx %s %s - $%.2f",
                quantity,
                size.name().charAt(0) + size.name().substring(1).toLowerCase(),
                flavor.name().replace("_", " ").toLowerCase() + " drink",
                price());
    }

    /**
     * Compares this beverage to another object for equality. Two beverages are considered equal if
     * they have the same quantity, size, and flavor.
     * @param obj The object to compare this beverage to.
     * @return true if the two beverages are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Beverage)) return false;
        Beverage other = (Beverage) obj;
        return quantity == other.quantity &&
                size == other.size &&
                flavor == other.flavor;
    }

    /**
     * Returns a hash code for this beverage based on its size, flavor, and quantity.
     * @return The hash code of the beverage.
     */
    @Override
    public int hashCode() {
        return Objects.hash(size, flavor, quantity);
    }
}
