package com.example.project4.controller;

import com.example.project4.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Controller for the burger customization popup.
 * Allows staff to select bread, patty style, add-ons, and quantity for a burger,
 * and sends the result back to the main controller when "Add to Order" is clicked.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class BurgerCustomController {

    @FXML private Button addOrderButton;
    @FXML private CheckBox avocadoOption, cheeseOption, lettuceOption, onionsOption, tomatoOption;
    @FXML private RadioButton briocheButton, wheatButton, pretzelButton;
    @FXML private RadioButton singleOption, doubleOption;
    @FXML private Button decreaseButton, increaseButton;
    @FXML private Label quantityLabel;

    @FXML private ToggleGroup breadChoice, proteinChoice;

    private int quantity = 0;

    private Consumer<Burger> onBurgerAdded;

    /**
     * Sets the callback that will be triggered when a burger is successfully added to the order.
     * @param callback a Consumer that accepts the Burger object
     */
    public void setOnBurgerAdded(Consumer<Burger> callback) {
        this.onBurgerAdded = callback;
    }

    /**
     * Increases the quantity selected by the user.
     * Updates the quantity label.
     */
    @FXML
    private void increaseQuantity() {
        quantity++;
        quantityLabel.setText(String.valueOf(quantity));
    }

    /**
     * Decreases the quantity, ensuring it doesn't go below zero.
     * Updates the quantity label.
     */
    @FXML
    private void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
            quantityLabel.setText(String.valueOf(quantity));
        }
    }

    /**
     * Gathers all the selected customization options,
     * creates a Burger object, and returns it via the provided callback.
     * If no quantity is selected, it alerts the user.
     */
    @FXML
    void addToOrder() {
        Bread bread = Bread.BRIOCHE;
        if (wheatButton.isSelected()) bread = Bread.WHEAT;
        else if (pretzelButton.isSelected()) bread = Bread.PRETZEL;

        Protein protein = Protein.ROAST_BEEF;

        ArrayList<AddOns> addOns = new ArrayList<>();
        if (lettuceOption.isSelected()) addOns.add(AddOns.LETTUCE);
        if (tomatoOption.isSelected()) addOns.add(AddOns.TOMATOES);
        if (onionsOption.isSelected()) addOns.add(AddOns.ONIONS);
        if (avocadoOption.isSelected()) addOns.add(AddOns.AVOCADO);
        if (cheeseOption.isSelected()) addOns.add(AddOns.CHEESE);

        if (quantity <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Quantity must be at least 1.");
            alert.showAndWait();
            return;
        }

        boolean isDouble = doubleOption.isSelected();

        Burger burger = new Burger(bread, protein, addOns, quantity, isDouble);
        if (onBurgerAdded != null) {
            onBurgerAdded.accept(burger);
        }

        ((Stage) addOrderButton.getScene().getWindow()).close();
    }
}
