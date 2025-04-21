package com.example.project4.model;

import java.util.Objects;

/**
 * Represents a side menu item, which includes a specific size and side type.
 * The price is determined by the size of the side and the side type.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class Side extends MenuItem {
    private Size size;
    private SideType sideType;

    /**
     * Constructs a Side object with a specific size, side type, and quantity.
     * @param size The size of the side (Small, Medium, or Large).
     * @param sideType The type of side (e.g., Fries, Salad).
     * @param quantity The quantity of sides ordered.
     */
    public Side(Size size, SideType sideType, int quantity) {
        this.size = size;
        this.sideType = sideType;
        this.quantity = quantity;
    }

    /**
     * Calculates the price of the side based on its size, side type, and quantity.
     * The price depends on the size of the side and is adjusted based on the side type.
     * @return The price of the side.
     */
    @Override
    public double price() {
        double sidePrice = switch (size) {
            case SMALL -> sideType.getPrice();
            case MEDIUM -> sideType.getPrice() + 0.50;
            case LARGE -> sideType.getPrice() + 1.00;
        };
        return sidePrice * quantity; // Multiply by quantity
    }

    /**
     * Gets the type of the side.
     * @return The side type.
     */
    public SideType getSideType() {
        return sideType;
    }

    /**
     * Gets the size of the side.
     * @return The size of the side.
     */
    public Size getSize() {
        return size;
    }

    /**
     * Returns a string representation of the side, including the quantity, size, side type,
     * and total price.
     * @return A string representation of the side.
     */
    @Override
    public String toString() {
        return String.format("%dx %s %s - $%.2f",
                quantity,
                size.name().charAt(0) + size.name().substring(1).toLowerCase(), // Capitalize size
                sideType.name().replace("_", " ").toLowerCase(), // Format side type (replace underscores)
                price());
    }

    /**
     * Compares this side to another object for equality. Two sides are considered equal
     * if they have the same quantity, size, and side type.
     * @param obj The object to compare this side to.
     * @return true if the sides are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Side)) return false;
        Side other = (Side) obj;
        return quantity == other.quantity &&
                sideType == other.sideType &&
                size == other.size;
    }

    /**
     * Returns a hash code for this side based on its size, side type, and quantity.
     * @return The hash code of the side.
     */
    @Override
    public int hashCode() {
        return Objects.hash(sideType, size, quantity);
    }
}
