package com.risjavafx.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class InfoTable<T, K> {

    public TableView<T> tableView;
    public ArrayList<TableColumn<T, K>> columnsArrayList = new ArrayList<>();

    public InfoTable() {
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(resizeFeatures -> false);
    }

    public void setColumns(ArrayList<TableColumn<T, K>> columnsArrayList) {
        this.columnsArrayList = columnsArrayList;
    }

    public void addColumnsToTable() {
        for (TableColumn<T, K> tableColumn : columnsArrayList) {
            tableView.getColumns().add(tableColumn);
            tableColumn.setReorderable(false);
            tableColumn.setSortable(true);
        }
    }

    // Allows you to specify what column width percent you want for each column
    public void setCustomColumnWidth(TableColumn<T, K> column, double width) {
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(width));
    }
}
