package com.example.project4.controller;

import com.example.project4.model.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Controller for the Past Orders window.
 * Responsible for displaying all previously placed orders
 * and allowing the user to export them to a text file.
 * @author Yakelin Melendez-Gonzalez, Nivedha Sundar
 */
public class OrdersController {

    @FXML
    private ListView<String> ordersListView;

    private List<Order> pastOrders;

    /**
     * Sets the list of past orders and displays them in the ListView.
     * @param pastOrders A list of previously placed orders.
     */
    public void setPastOrders(List<Order> pastOrders) {
        this.pastOrders = pastOrders;

        for (Order order : pastOrders) {
            StringBuilder orderSummary = new StringBuilder();
            orderSummary.append(String.format("Order #" + order.getNumber() + "\n"));
            order.getItems().forEach(item -> orderSummary.append(item.toString()).append("\n"));
            orderSummary.append(String.format("Subtotal: $%.2f \n", order.getSubtotal()));
            orderSummary.append(String.format("Tax: $%.2f \n", order.getTax()));
            orderSummary.append(String.format("Total: $%.2f", order.getTotal()));
            orderSummary.append("\n------------------");
            ordersListView.getItems().add(orderSummary.toString());
        }
    }

    /**
     * Cancels the currently selected order from the ListView and removes it from the list of past orders.
     * If no order is selected, an error alert is shown to the user.
     * After cancellation, the ListView is refreshed to reflect the updated list.
     * Alerts the user upon successful cancellation.
     */
    @FXML
    private void cancelSelectedOrder() {
        int selectedIndex = ordersListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            new Alert(Alert.AlertType.WARNING, "Please select an order to cancel.").showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this order?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                pastOrders.remove(selectedIndex); // Remove from data list
                ordersListView.getItems().remove(selectedIndex); // Remove from view
                new Alert(Alert.AlertType.INFORMATION, "Order canceled.").showAndWait();
            }
        });
    }


    /**
     * Exports all past orders to a text file named "past_orders.txt".
     * Each order is written with its number, item list, and total.
     * Alerts the user upon success or failure.
     */
    @FXML
    private void exportToFile() {
        try (FileWriter writer = new FileWriter("orders.txt")) {
            for (Order order : pastOrders) {
                writer.write(String.format("Order #: " + order.getNumber() + "\n"));
                for (var item : order.getItems()) {
                    writer.write(item.toString() + "\n");
                }
                writer.write(String.format("Subtotal: $%.2f \n", order.getSubtotal()));
                writer.write(String.format("Tax: $%.2f \n", order.getTax()));
                writer.write(String.format("Total: $%.2f", order.getTotal()));
                writer.write("\n---------------------\n");
            }
            new Alert(Alert.AlertType.INFORMATION, "Orders exported to orders.txt").showAndWait();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to export: " + e.getMessage()).showAndWait();
        }
    }
}
