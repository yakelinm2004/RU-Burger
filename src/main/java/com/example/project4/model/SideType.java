package com.example.project4.model;

/**
 * Enum representing different types of sides available in the menu.
 * Each side type has a predefined price.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public enum SideType {
    CHIPS(1.99),
    FRIES(2.49),
    ONION_RINGS(3.29),
    APPLE_SLICES(1.29);

    private final double price;

    /**
     * Constructor for the SideType enum, which assigns the price to each side type.
     * @param price The price of the side type.
     */
    SideType(double price) {
        this.price = price;
    }

    /**
     * Gets the price of the side type.
     * @return The price of the side type.
     */
    public double getPrice() {
        return price;
    }
}
