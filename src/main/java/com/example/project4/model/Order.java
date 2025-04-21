package com.example.project4.model;

import java.util.ArrayList;

/**
 * Represents an order containing a list of menu items, with methods for managing items, calculating totals,
 * and handling the order number.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class Order {
    private int number;
    private ArrayList<MenuItem> items;

    /**
     * Constructs an Order object with a unique order number and a list of menu items.
     * @param number The unique number identifying the order.
     * @param items The list of menu items in the order.
     */
    public Order(int number, ArrayList<MenuItem> items) {
        this.number = number;
        this.items = new ArrayList<>(items); // Copying items to avoid external modifications
    }

    /**
     * Gets the unique order number.
     * @return The order number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the list of items in the order.
     * @param items The new list of menu items for the order.
     */
    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    /**
     * Gets the list of items in the order.
     * @return The list of menu items in the order.
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Adds a menu item to the order.
     * @param item The menu item to add.
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Removes a menu item from the order.
     * @param item The menu item to remove.
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    /**
     * Clears all menu items from the order.
     */
    public void clearItems() {
        items.clear();
    }

    /**
     * Calculates the total price of the order, including tax.
     * The tax rate is 6.625% and is applied to the subtotal.
     * @return The total price of the order including tax.
     */
    public double getTotal() {
        double subTotal = 0.0;
        for (MenuItem item : items) {
            subTotal += item.price();
        }

        double tax = subTotal * 0.06625; // Tax rate of 6.625%
        return subTotal + tax; // Return total price including tax
    }

    /**
     * Calculates the subtotal of the order, which is the sum of all item prices without tax.
     * @return The subtotal of the order.
     */
    public double getSubtotal() {
        double subTotal = 0.0;
        for (MenuItem item : items) {
            subTotal += item.price();
        }
        return subTotal;
    }

    /**
     * Calculates the tax for the order, which is 6.625% of the subtotal.
     * @return The tax amount for the order.
     */
    public double getTax() {
        double subTotal = 0.0;
        for (MenuItem item : items) {
            subTotal += item.price();
        }
        return subTotal * 0.06625; // Tax rate of 6.625%
    }
}
