package com.example.project4.model;

/**
 * Enum representing the different add-ons available for an item.
 * Each add-on has a specific price associated with it.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public enum AddOns {
    LETTUCE(0.30),
    TOMATOES(0.30),
    ONIONS(0.30),
    AVOCADO(0.50),
    CHEESE(1.00);

    private final double price;

    /**
     * Constructor for the enum AddOns. Initializes the price for each add-on.
     * @param price The price of the add-on.
     */
    AddOns(double price) {
        this.price = price;
    }

    /**
     * Returns the price of the add-on.
     * @return the price of the add-on as a double.
     */
    public double getPrice() {
        return price;
    }
}
