package com.bookshop.Controllers;

import com.bookshop.Models.DBDriver;
import com.bookshop.Models.Item;
import com.bookshop.Models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class BookshopController {
    public TableView<Item> tableView;
    public TableColumn<Item, String> nameColumn;

    public TableColumn<Item, String> priceColumn;
    private TableColumn<Item, String> stockColumn;
    private TableColumn<Item, String> categoryColumn;
    private TableColumn<Item, String> imageColumn;

    public BookshopController() {
        this.tableView = new TableView<>();
        this.nameColumn = new TableColumn<>("Name");
        this.priceColumn = new TableColumn<>("Price");
        this.stockColumn = new TableColumn<>("Stock");
        this.categoryColumn = new TableColumn<>("Category");
        this.imageColumn = new TableColumn<>("Image");
    }

    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().itemPriceProperty());
        // Configure more column cell value factories

        // Retrieve data from the database
        List<Item> items = Model.getInstance().getDbDriver().itemsList();
        tableView.getItems().addAll(items);
        //this.tableView.getColumns().addAll(this.nameColumn, this.priceColumn, this.stockColumn, this.categoryColumn, this.imageColumn);
    }
}
