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

//        creating two tableview

        TableView<Books> books_table = new TableView<>();

        TableColumn<Books,String> title_column = new TableColumn<>("Title");

        title_column.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Books,String> author_column = new TableColumn<>("Author");

        author_column.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Books,String> genre_column = new TableColumn<>("Genre");

        genre_column.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Books,Integer> height_column = new TableColumn<>("Height");

        height_column.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Books,String> publisher_column = new TableColumn<>("Publisher");

        publisher_column.setCellValueFactory(new PropertyValueFactory<>("publisher"));


        books_table.getColumns().addAll(title_column,author_column,genre_column,height_column,publisher_column);

        title_column.setPrefWidth(280);
        author_column.setPrefWidth(170);
        genre_column.setPrefWidth(130);
        height_column.setPrefWidth(80);
        publisher_column.setPrefWidth(140);


        TableView<Books> added_books_table = new TableView<>();

        TableColumn<Books,String> added_title_column = new TableColumn<>("Title");

        added_title_column.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Books,String> added_author_column = new TableColumn<>("Author");

        added_author_column.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Books,String> added_genre_column = new TableColumn<>("Genre");

        added_genre_column.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Books,Integer> added_height_column = new TableColumn<>("Height");

        added_height_column.setCellValueFactory(new PropertyValueFactory<>("height"));

        TableColumn<Books,String> added_publisher_column = new TableColumn<>("Publisher");

        added_publisher_column.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        added_books_table.getColumns().addAll(added_title_column,added_author_column,added_genre_column,added_height_column,added_publisher_column);


        added_title_column.setPrefWidth(280);
        added_author_column.setPrefWidth(170);
        added_genre_column.setPrefWidth(130);
        added_height_column.setPrefWidth(80);
        added_publisher_column.setPrefWidth(140);


        added_books_table.setPlaceholder(
                new Label("Add some books to borrow"));

//        creating connection to database and displaying records in first tableview

        DB_add_to_table.add();

        books_table.getItems().addAll(DB_add_to_table.books_list);

        TableView.TableViewSelectionModel<Books> selectionModel =
                books_table.getSelectionModel();

        // set selection mode to only 1 row
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);

//      creating buttons and their functionality

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

        VBox scene_vbox = new VBox(books_table,button_hbox,added_books_table);

        Scene main_scene = new Scene(scene_vbox, 800, 600);
        primaryStage.setScene(main_scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
