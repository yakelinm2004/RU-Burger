package com.example.project4.model;

/**
 * Abstract class representing a menu item in the system.
 * All menu items (e.g., sandwiches, beverages, combos) will inherit from this class
 * and implement the price calculation.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public abstract class MenuItem {
    protected int quantity;

    /**
     * Sets the quantity for this menu item.
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the quantity of this menu item.
     * @return The quantity of the menu item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Abstract method to calculate the price of the menu item.
     * Each subclass must provide its own implementation of this method.
     * @return The price of the menu item.
     */
    public abstract double price();
}
