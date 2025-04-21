package com.example.project4.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a sandwich menu item with a specific bread, protein, and optional add-ons.
 * The sandwich's price is determined based on the protein type, the selected add-ons, and the quantity.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class Sandwich extends MenuItem {
    protected Bread bread;
    protected Protein protein;
    protected ArrayList<AddOns> addOns;

    /**
     * Constructs a Sandwich object with a specific bread, protein, add-ons, and quantity.
     * @param bread The type of bread for the sandwich.
     * @param protein The type of protein for the sandwich.
     * @param addOns The list of optional add-ons for the sandwich.
     * @param quantity The quantity of sandwiches ordered.
     */
    public Sandwich(Bread bread, Protein protein, ArrayList<AddOns> addOns, int quantity) {
        this.bread = bread;
        this.protein = protein;
        this.quantity = quantity;
        this.addOns = addOns;
    }

    /**
     * Calculates the price of the sandwich based on the protein type, the selected add-ons, and the quantity.
     * The price is the base price for the protein, plus the price of the add-ons, multiplied by the quantity.
     * @return The price of the sandwich.
     */
    @Override
    public double price() {
        double basePrice = switch(protein) {
            case ROAST_BEEF -> 10.99;
            case SALMON -> 9.99;
            case CHICKEN -> 8.99;
        };

        double addOnTotal = 0.0;
        for (AddOns addOn : addOns) {
            addOnTotal += addOn.getPrice();
        }

        return (basePrice + addOnTotal) * quantity;
    }

    /**
     * Returns a string representation of the sandwich, including the quantity, protein type,
     * bread type, add-ons (if any), and total price.
     * @return A string representation of the sandwich.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(quantity).append("x ");
        sb.append(protein).append(" Sandwich on ").append(bread);

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

    /**
     * Compares this sandwich to another object for equality. Two sandwiches are considered equal
     * if they have the same quantity, bread type, protein type, and add-ons.
     * @param obj The object to compare this sandwich to.
     * @return true if the sandwiches are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Sandwich)) return false;
        Sandwich other = (Sandwich) obj;
        return quantity == other.quantity &&
                bread == other.bread &&
                protein == other.protein &&
                addOns.equals(other.addOns);
    }

    /**
     * Returns a hash code for this sandwich based on its bread, protein, add-ons, and quantity.
     * @return The hash code of the sandwich.
     */
    @Override
    public int hashCode() {
        return Objects.hash(bread, protein, addOns, quantity);
    }
}
