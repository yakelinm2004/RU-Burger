package com.example.project4.controller;

import com.example.project4.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Controller for the Combo Meal customization window.
 * Handles UI interaction for customizing a combo meal, which includes:
 * a burger or sandwich, a side, and a drink.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class ComboMealController {

    @FXML private RadioButton colaButton, juiceButton, teaButton, orangeButton, peachButton, mangoButton,
            raspberryButton, cherryButton, limeButton, pineappleButton, lemonadeButton, strawLemonButton,
            mLemonButton, peachTeaButton, raspTeaButton;

    @FXML private RadioButton friesButton, onionsButton, chipsButton, applesButton;

    @FXML private CheckBox avocadoOption, cheeseOption, lettuceOption, onionsOption, tomatoOption;

    @FXML private RadioButton bagelButton, briocheButton, pretzelButton, sourdoughButton, wheatButton;

    @FXML private RadioButton burgerButton, sandwichButton;

    @FXML private RadioButton chickenButton, rbButton, salmonButton, singleButton, doubleButton;

    @FXML private Button decreaseButton, increaseButton;
    @FXML private Label quantityLabel;
    private int quantity = 0;

    @FXML private Label itemName;

    @FXML private ToggleGroup breadChoice, proteinChoice, mealChoice;

    private Consumer<Combo> onComboAdded;

    /**
     * Sets the callback to handle when a Combo is added to the order.
     * @param callback the callback function accepting a Combo object
     */
    public void setOnComboAdded(Consumer<Combo> callback) {
        this.onComboAdded = callback;
    }

    /**
     * Initializes toggle group behavior for meal type selection.
     * Disables or enables controls depending on the selected meal type.
     */
    @FXML
    public void initialize(){
        mealChoice.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue == burgerButton) {
                    burgerSelected();
                } else if (newValue == sandwichButton) {
                    sandwichSelected();
                }
            }
        });
    }

    /**
     * Handles UI state when "Burger" is selected.
     * Disables sandwich-specific bread and protein options.
     */
    @FXML
    protected void burgerSelected() {
        bagelButton.setDisable(true);
        sourdoughButton.setDisable(true);
        chickenButton.setDisable(true);
        salmonButton.setDisable(true);
        rbButton.setDisable(true);
        singleButton.setDisable(false);
        doubleButton.setDisable(false);
    }

    /**
     * Handles UI state when "Sandwich" is selected.
     * Enables sandwich-specific options and disables burger patty toggles.
     */
    @FXML
    protected void sandwichSelected() {
        bagelButton.setDisable(false);
        sourdoughButton.setDisable(false);
        chickenButton.setDisable(false);
        salmonButton.setDisable(false);
        rbButton.setDisable(false);
        singleButton.setDisable(true);
        doubleButton.setDisable(true);
    }

    /**
     * Gathers user input from all UI components, builds a Combo object,
     * and invokes the callback to add it to the current order.
     * If quantity is invalid, it shows an alert and exits early.
     */
    @FXML
    protected void addToOrder() {
        if (quantity <= 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Quantity must be at least 1.");
            alert.showAndWait();
            return;
        }

        Bread bread = Bread.BRIOCHE;
        if (wheatButton.isSelected()) bread = Bread.WHEAT;
        else if (pretzelButton.isSelected()) bread = Bread.PRETZEL;
        else if (bagelButton.isSelected()) bread = Bread.BAGEL;
        else if (sourdoughButton.isSelected()) bread = Bread.SOURDOUGH;

        ArrayList<AddOns> addOns = new ArrayList<>();
        if (lettuceOption.isSelected()) addOns.add(AddOns.LETTUCE);
        if (tomatoOption.isSelected()) addOns.add(AddOns.TOMATOES);
        if (onionsOption.isSelected()) addOns.add(AddOns.ONIONS);
        if (avocadoOption.isSelected()) addOns.add(AddOns.AVOCADO);
        if (cheeseOption.isSelected()) addOns.add(AddOns.CHEESE);

        Sandwich sandwich;
        if (burgerButton.isSelected()) {
            boolean isDouble = doubleButton.isSelected();
            sandwich = new Burger(bread, Protein.ROAST_BEEF, addOns, quantity, isDouble);
        } else {
            Protein protein = Protein.CHICKEN;
            if (rbButton.isSelected()) protein = Protein.ROAST_BEEF;
            else if (salmonButton.isSelected()) protein = Protein.SALMON;
            sandwich = new Sandwich(bread, protein, addOns, quantity);
        }

        SideType sideType = SideType.CHIPS;
        if (chipsButton.isSelected()) sideType = SideType.CHIPS;
        else if (applesButton.isSelected()) sideType = SideType.APPLE_SLICES;
        Side side = new Side(Size.SMALL, sideType, quantity);

        Flavor flavor = Flavor.COLA;
        if (teaButton.isSelected()) flavor = Flavor.TEA;
        else if (juiceButton.isSelected()) flavor = Flavor.JUICE;

        Beverage drink = new Beverage(Size.MEDIUM, flavor, quantity);

        Combo combo = new Combo(sandwich, drink, side, quantity);

        if (onComboAdded != null) {
            onComboAdded.accept(combo);
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
