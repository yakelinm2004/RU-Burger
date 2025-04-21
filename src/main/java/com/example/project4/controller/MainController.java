package com.example.project4.controller;

import com.example.project4.model.MenuItem;
import com.example.project4.model.Order;
import com.example.project4.model.SideType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the main UI. Manages user interaction with the menu,
 * order creation, and coordination of customization popups for different menu items.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class MainController {

    @FXML
    private ListView<MenuItem> orderList;

    @FXML
    private Label totalLabel, subTotalLabel, taxTotalLabel;

    @FXML
    private Label quantityLabel;

    private int orderCounter = 1;

    private Order currentOrder;
    private final List<Order> pastOrders = new ArrayList<>();

    /**
     * Initializes the controller and sets up the ListView with custom cells that include a remove button.
     */
    @FXML
    public void initialize() {
        currentOrder = new Order(orderCounter, new ArrayList<>());

        orderList.setCellFactory(list -> new ListCell<>() {
            private final Label itemLabel = new Label();
            private final Button removeButton = new Button("Remove");
            private final HBox cellBox = new HBox(10, itemLabel, removeButton);

            {
                removeButton.setOnAction(event -> {
                    MenuItem item = getItem();
                    if (item != null) {
                        getListView().getItems().remove(item);
                        currentOrder.removeItem(item);
                        updateSubTotal();
                        updateTax();
                        updateTotal();
                    }
                });

                cellBox.setStyle("-fx-alignment: center-left; -fx-padding: 5 10;");
                removeButton.setStyle("-fx-font-size: 11px;");
            }

            @Override
            protected void updateItem(MenuItem item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    itemLabel.setText(item.toString());
                    setGraphic(cellBox);
                }
            }
        });
    }

    /**
     * Opens the burger customization popup and handles item addition.
     */
    public void openBurgerCustomization() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/burgerCustom.fxml"));
        Parent popupRoot = loader.load();

        BurgerCustomController controller = loader.getController();
        controller.setOnBurgerAdded(burger -> {
            currentOrder.addItem(burger);
            orderList.getItems().add(burger);
            updateSubTotal();
            updateTax();
            updateTotal();
        });

        showPopup(popupRoot, "Customize Burger");
    }

    /**
     * Opens the sandwich customization popup and handles item addition.
     */
    public void openSandwichCustomization() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/sandwichCustom.fxml"));
        Parent root = loader.load();

        SandwichController controller = loader.getController();
        controller.setOnSandwichAdded(sandwich -> {
            currentOrder.addItem(sandwich);
            orderList.getItems().add(sandwich);
            updateSubTotal();
            updateTax();
            updateTotal();
        });

        showPopup(root, "Customize Sandwich");
    }

    /**
     * Opens the combo customization popup and handles item addition.
     */
    public void openComboCustomization() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/comboCustom.fxml"));
        Parent root = loader.load();

        ComboMealController controller = loader.getController();
        controller.setOnComboAdded(combo -> {
            currentOrder.addItem(combo);
            orderList.getItems().add(combo);
            updateSubTotal();
            updateTax();
            updateTotal();
        });

        showPopup(root, "Customize Combo");
    }

    /**
     * Opens a side customization popup for Fries.
     */
    public void openFriesCustomization() throws IOException {
        openSidePopup(SideType.FRIES, "Customize Fries");
    }

    /**
     * Opens a side customization popup for Chips.
     */
    public void openChipsCustomization() throws IOException {
        openSidePopup(SideType.CHIPS, "Customize Chips");
    }

    /**
     * Opens a side customization popup for Onion Rings.
     */
    public void openOnionRingsCustomization() throws IOException {
        openSidePopup(SideType.ONION_RINGS, "Customize Onion Rings");
    }

    /**
     * Opens a side customization popup for Apple Slices.
     */
    public void openAppleSlicesCustomization() throws IOException {
        openSidePopup(SideType.APPLE_SLICES, "Customize Apple Slices");
    }

    /**
     * Opens the generic sides customization popup, passing in a side type and title.
     */
    private void openSidePopup(SideType sideType, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/sidesCustom.fxml"));
        Parent root = loader.load();

        SidesController controller = loader.getController();
        controller.setSideType(sideType);
        controller.setOnSideAdded(side -> {
            currentOrder.addItem(side);
            orderList.getItems().add(side);
            updateSubTotal();
            updateTax();
            updateTotal();
        });

        showPopup(root, title);
    }

    /**
     * Opens the drink customization popup.
     */
    public void openDrinksSelection() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/drinksCustom.fxml"));
        Parent root = loader.load();

        DrinksController controller = loader.getController();
        controller.setOnDrinkAdded(drink -> {
            currentOrder.addItem(drink);
            orderList.getItems().add(drink);
            updateSubTotal();
            updateTax();
            updateTotal();
        });

        showPopup(root, "Customize Drink");
    }

    /**
     * Updates the UI with the total cost including tax.
     */
    @FXML
    private void updateTotal() {
        double total = currentOrder.getTotal();
        totalLabel.setText(String.format("$%.2f", total));
    }

    /**
     * Updates the UI with the subtotal.
     */
    @FXML
    private void updateSubTotal() {
        double subtotal = currentOrder.getSubtotal();
        subTotalLabel.setText(String.format("$%.2f", subtotal));
    }

    /**
     * Updates the UI with the tax amount.
     */
    @FXML
    private void updateTax() {
        double tax = currentOrder.getTax();
        taxTotalLabel.setText(String.format("$%.2f", tax));
    }

    /**
     * Clears the current order and resets UI labels.
     */
    @FXML
    private void clearOrder() {
        currentOrder.clearItems();
        orderList.getItems().clear();
        totalLabel.setText("$0.00");
        subTotalLabel.setText("$0.00");
        taxTotalLabel.setText("$0.00");
    }

    /**
     * Finalizes the current order, adds it to the list of past orders,
     * resets the order view, and displays a confirmation.
     */
    @FXML
    private void placeOrder() {
        if (currentOrder.getItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Order is empty!").showAndWait();
            return;
        }

        orderCounter++;
        Order finalizedOrder = new Order(orderCounter, new ArrayList<>(currentOrder.getItems()));
        pastOrders.add(finalizedOrder);

        Alert confirmation = new Alert(Alert.AlertType.INFORMATION,
                "Order #" + finalizedOrder.getNumber() + " placed!\nTotal: $" + String.format("%.2f", finalizedOrder.getTotal()));
        confirmation.showAndWait();

        currentOrder = new Order(orderCounter, new ArrayList<>());
        orderList.getItems().clear();
        updateTotal();
        updateSubTotal();
        updateTax();
    }

    /**
     * Opens the Past Orders window and loads all completed orders.
     */
    @FXML
    private void displayOrders() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/project4/orders.fxml"));
        Parent root = loader.load();

        OrdersController controller = loader.getController();
        controller.setPastOrders(pastOrders);

        showPopup(root, "Orders");
    }

    /**
     * Utility method to show a popup window.
     * @param root The root node of the popup
     * @param title The title of the popup window
     */
    private void showPopup(Parent root, String title) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
