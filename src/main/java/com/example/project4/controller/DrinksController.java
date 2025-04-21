package com.example.project4.controller;

import com.example.project4.model.Beverage;
import com.example.project4.model.Flavor;
import com.example.project4.model.Size;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.function.Consumer;

/**
 * Controller for the Drink customization view.
 * Allows selection of drink size, flavor, and quantity.
 * On confirmation, passes the configured Beverage object back to the main controller.
 *  @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class DrinksController {

    @FXML private RadioButton cherryButton, colaButton, juiceButton, lemonadeButton, limeButton, mLemonButton;
    @FXML private RadioButton mangoButton, orangeButton, peachButton, peachTeaButton, pineappleButton;
    @FXML private RadioButton raspberryButton, raspTeaButton, strawLemonButton, teaButton;

    @FXML private RadioButton smallButton, mediumButton, largeButton;

    @FXML private Button decreaseButton, increaseButton;
    @FXML private Label quantityLabel;
    private int quantity = 0;

    @FXML private Label itemName;

    private Consumer<Beverage> onDrinkAdded;

    /**
     * Sets the callback to be triggered when a drink is added to the order.
     * @param callback A function that accepts the created Beverage
     */
    public void setOnDrinkAdded(Consumer<Beverage> callback) {
        this.onDrinkAdded = callback;
    }

    /**
     * Gathers the selected drink configuration, validates quantity,
     * and creates a Beverage object to be added to the current order.
     * If the quantity is zero or negative, alerts the user.
     */
    @FXML
    void addToOrder() {
        Size size = Size.SMALL;
        if (mediumButton.isSelected()) size = Size.MEDIUM;
        else if (largeButton.isSelected()) size = Size.LARGE;

        Flavor flavor = Flavor.COLA;
        if (juiceButton.isSelected()) flavor = Flavor.JUICE;
        else if (lemonadeButton.isSelected()) flavor = Flavor.LEMONADE;
        else if (peachButton.isSelected()) flavor = Flavor.PEACH;
        else if (mangoButton.isSelected()) flavor = Flavor.MANGO;
        else if (raspberryButton.isSelected()) flavor = Flavor.RASPBERRY;
        else if (orangeButton.isSelected()) flavor = Flavor.ORANGE;
        else if (pineappleButton.isSelected()) flavor = Flavor.PINEAPPLE;
        else if (limeButton.isSelected()) flavor = Flavor.LIME;
        else if (cherryButton.isSelected()) flavor = Flavor.CHERRY;
        else if (strawLemonButton.isSelected()) flavor = Flavor.STRAWBERRY_LEMONADE;
        else if (peachTeaButton.isSelected()) flavor = Flavor.PEACH_TEA;
        else if (raspTeaButton.isSelected()) flavor = Flavor.RASPBERRY_TEA;
        else if (teaButton.isSelected()) flavor = Flavor.TEA;
        else if (mLemonButton.isSelected()) flavor = Flavor.MANGO_LEMONADE;

        if (quantity <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Quantity must be at least 1.");
            alert.showAndWait();
            return;
        }

        Beverage drink = new Beverage(size, flavor, quantity);
        if (onDrinkAdded != null) {
            onDrinkAdded.accept(drink);
        }

        ((Stage) itemName.getScene().getWindow()).close();
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
}
