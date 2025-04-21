package com.example.project4.controller;

import com.example.project4.model.AddOns;
import com.example.project4.model.Bread;
import com.example.project4.model.Protein;
import com.example.project4.model.Sandwich;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Controller for customizing and adding a Sandwich item to an order.
 * Allows the user to choose bread, protein, add-ons, and quantity.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class SandwichController {
    @FXML
    private CheckBox avocadoOption;

    @FXML
    private RadioButton bagelButton;

    @FXML
    private RadioButton briocheButton;

    @FXML
    private CheckBox cheeseOption;

    @FXML
    private RadioButton chickenButton;

    @FXML
    private Button decreaseButton;

    @FXML
    private Button increaseButton;

    @FXML
    private Label itemName;

    @FXML
    private CheckBox lettuceOption;

    @FXML
    private CheckBox onionsOption;

    @FXML
    private RadioButton pretzelButton;

    @FXML
    private Label quantityLabel;
    private int quantity = 0;

    @FXML
    private RadioButton rbButton;

    @FXML
    private RadioButton salmonButton;

    @FXML
    private RadioButton sourdoughButton;

    @FXML
    private CheckBox tomatoOption;

    @FXML
    private RadioButton wheatButton;

    private Consumer<Sandwich> onSandwichAdded;

    /**
     * Sets the callback to be triggered when the sandwich is added to the order.
     * @param callback A consumer that accepts the newly created Sandwich.
     */
    public void setOnSandwichAdded(Consumer<Sandwich> callback) {
        this.onSandwichAdded = callback;
    }

    /**
     * Called when the "Add to Order" button is clicked.
     * Gathers all selections from the UI, constructs a Sandwich object,
     * and passes it to the callback consumer.
     */
    @FXML
    void addToOrder() {
        Bread bread = Bread.BRIOCHE;
        if (wheatButton.isSelected()) bread = Bread.WHEAT;
        else if (pretzelButton.isSelected()) bread = Bread.PRETZEL;
        else if (bagelButton.isSelected()) bread = Bread.BAGEL;
        else if (sourdoughButton.isSelected()) bread = Bread.SOURDOUGH;

        Protein protein = Protein.CHICKEN;
        if (rbButton.isSelected()) protein = Protein.ROAST_BEEF;
        else if (salmonButton.isSelected()) protein = Protein.SALMON;

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

        Sandwich sandwich = new Sandwich(bread, protein, addOns, quantity);
        if (onSandwichAdded != null) {
            onSandwichAdded.accept(sandwich);
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
