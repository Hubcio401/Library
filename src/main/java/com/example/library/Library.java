package com.example.library;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Library extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Library");


        TableView<Books> books_table = new TableView<>();

        TableColumn<Books,String> column1 = new TableColumn<>("Title");

        column1.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Books,String> column2 = new TableColumn<>("Author");

        column2.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Books,String> column3 = new TableColumn<>("Genre");

        column3.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Books,Integer> column4 = new TableColumn<>("Height");

        column4.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Books,String> column5 = new TableColumn<>("Publisher");

        column5.setCellValueFactory(new PropertyValueFactory<>("publisher"));


        books_table.getColumns().addAll(column1,column2,column3,column4,column5);

        column1.setPrefWidth(280);
        column2.setPrefWidth(170);
        column3.setPrefWidth(130);
        column4.setPrefWidth(80);
        column5.setPrefWidth(140);


        TableView<Books> added_books_table = new TableView<>();

        TableColumn<Books,String> added_column1 = new TableColumn<>("Title");

        added_column1.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Books,String> added_column2 = new TableColumn<>("Author");

        added_column2.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Books,String> added_column3 = new TableColumn<>("Genre");

        added_column3.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Books,Integer> added_column4 = new TableColumn<>("Height");

        added_column4.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Books,String> added_column5 = new TableColumn<>("Publisher");

        added_column5.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        added_books_table.getColumns().addAll(added_column1,added_column2,added_column3,added_column4,added_column5);


        added_column1.setPrefWidth(280);
        added_column2.setPrefWidth(170);
        added_column3.setPrefWidth(130);
        added_column4.setPrefWidth(80);
        added_column5.setPrefWidth(140);


        added_books_table.setPlaceholder(
                new Label("Add some books to borrow"));

        DB_add_to_table.add();

        books_table.getItems().addAll(DB_add_to_table.books_list);

        TableView.TableViewSelectionModel<Books> selectionModel =
                books_table.getSelectionModel();
//
        // set selection mode to only 1 row
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);


        Button save = new Button("Save");
        Button add = new Button("Add");
        Button remove = new Button("Remove");

        add.setOnAction(add_value ->  {

            String tit = books_table.getSelectionModel().getSelectedItem().getTitle();
            String auth= books_table.getSelectionModel().getSelectedItem().getAuthor();
            String gen= books_table.getSelectionModel().getSelectedItem().getGenre();
            int hei= books_table.getSelectionModel().getSelectedItem().getHeight();
            String pub = books_table.getSelectionModel().getSelectedItem().getPublisher();
            Books obj = new Books(tit,auth,gen,hei,pub);


            added_books_table.getItems().add(obj);

        });

        remove.setOnAction(remove_value -> {

            Books selectedItem = added_books_table.getSelectionModel().getSelectedItem();
            added_books_table.getItems().remove(selectedItem);

        });

        save.setOnAction(save_value ->{

            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            try {
                PrintWriter writer;
                writer = new PrintWriter(file);
                String s = String.valueOf(added_books_table.getItems());
                writer.println(s);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
            }

        });


        add.setPrefHeight(150);
        remove.setPrefHeight(150);
        save.setPrefHeight(150);
        add.setPrefWidth(150);
        remove.setPrefWidth(150);
        save.setPrefWidth(150);

        HBox button_hbox = new HBox(20,add,remove,save);

        button_hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(books_table,button_hbox,added_books_table);

        Scene main_scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(main_scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
