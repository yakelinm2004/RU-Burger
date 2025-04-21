package com.example.project4.controller;

import com.example.project4.model.Side;
import com.example.project4.model.SideType;
import com.example.project4.model.Size;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.util.function.Consumer;

/**
 * Controller for handling the customization of side items in the UI.
 * Allows users to select the size and quantity of a specific side type.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class SidesController {

    @FXML
    private Button addOrderButton;

    @FXML
    private Button decreaseButton;

    @FXML
    private Button increaseButton;

    @FXML
    private Label itemName;

    @FXML
    private RadioButton smallButton, mediumButton, largeButton;

    @FXML
    private Label quantityLabel;

    private int quantity = 0;

    private SideType sideType;

    private Consumer<Side> onSideAdded;

    /**
     * Sets the type of side (e.g. Fries, Chips) this controller will customize.
     * This allows reuse of the same controller for different side options.
     * @param sideType the type of side being customized
     */
    public void setSideType(SideType sideType) {
        this.sideType = sideType;
    }

    /**
     * Sets a callback to be executed when the side is added to the order.
     * @param callback the action to perform with the created Side object
     */
    public void setOnSideAdded(Consumer<Side> callback) {
        this.onSideAdded = callback;
    }

    /**
     * Handles the "Add to Order" action.
     * Creates a Side object based on user selection and sends it through the callback.
     */
    @FXML
    void addToOrder() {
        Size size = Size.SMALL;
        if (mediumButton.isSelected()) {
            size = Size.MEDIUM;
        } else if (largeButton.isSelected()) {
            size = Size.LARGE;
        }

        if (quantity <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Quantity must be at least 1.");
            alert.showAndWait();
            return;
        }

        Side side = new Side(size, sideType, quantity);
        if (onSideAdded != null) {
            onSideAdded.accept(side);
        }

        ((Stage) addOrderButton.getScene().getWindow()).close();
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
