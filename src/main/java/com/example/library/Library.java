package com.example.library;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Library extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Library");


        TableView<Books> books_table = new TableView<>();

//        TableColumn<Books,Integer> column_ID = new TableColumn<>("ID");
//
//        column_ID.setCellValueFactory(new PropertyValueFactory<>("id"));

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

//        books_table.setLayoutX(50);

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

        added_books_table.setPlaceholder(
                new Label("Add some books to borrow"));

        DB_add_to_table.add();

        books_table.getItems().addAll(DB_add_to_table.books_list);

        TableView.TableViewSelectionModel<Books> selectionModel =
                books_table.getSelectionModel();

        //sprawdz se selection model dla dwoch tabel bo cosi nie dziala i zobacz hello-view.fxml
//
        // set selection mode to only 1 row
        selectionModel.setSelectionMode(
                SelectionMode.SINGLE);


//        added_books_table.setLayoutX(200);


        Button save = new Button("Save");
        Button add = new Button("Add");
        Button remove = new Button("Remove");

//        ArrayList<Books> added_list = new ArrayList<>();


        add.setOnAction(add_value ->  {

            String tit = books_table.getSelectionModel().getSelectedItem().getTitle();
            String auth= books_table.getSelectionModel().getSelectedItem().getAuthor();
            String gen= books_table.getSelectionModel().getSelectedItem().getGenre();
            int hei= books_table.getSelectionModel().getSelectedItem().getHeight();
            String pub = books_table.getSelectionModel().getSelectedItem().getPublisher();
            Books obj = new Books(tit,auth,gen,hei,pub);
//            System.out.println(obj);

//            added_list.add(obj);

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

            //

//            DirectoryChooser directoryChooser = new DirectoryChooser();
//            File selectedDirectory = directoryChooser.showDialog(primaryStage);
//
//            if(selectedDirectory == null){
//                //No Directory selected
//            }else{
//
//                String path = selectedDirectory.getAbsolutePath()+"\\books.txt";
//                FileWriter fstream = null;
//                try {
//                    fstream = new FileWriter(path);
//                    BufferedWriter books_file = new BufferedWriter(fstream);
//
//                    String s = String.valueOf(added_books_table.getItems());
//                    fstream.write(s);
//                    System.out.println(s);
////                    books_file.write(s);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(selectedDirectory.getAbsolutePath());
//            }
//            System.out.println(added_books_table.getItems());

        });


//        add.setLayoutY(300);


        HBox button_hbox = new HBox(20,add,remove,save);
//        HBox.setMargin(add, new Insets(10, 10, 10, 10));

        button_hbox.setAlignment(Pos.CENTER);



//        Group group = new Group();
//        group.getChildren().addAll(save,add,remove);
//        group.setStyle("-fx-alignment: CENTER;");
//        add.setLayoutX(300);
//        remove.setLayoutX(350);
//        save.setAlignment(Pos.CENTER);
//        save.setLayoutX(250);


//        group.setLayoutX(500);


//        group.setLayoutX(100);
//        group.setLayoutY(100);



        VBox vbox = new VBox(books_table,button_hbox,added_books_table);


//        GridPane gridPane = new GridPane(vbox);
//
//        gridPane.setMinSize(400,200);
//
//        gridPane.setPadding(new Insets(10,10,10,10));
//
//        gridPane.setHgap(5);
//        gridPane.setVgap(5);
//
//        gridPane.setAlignment(Pos.CENTER);
//
//        gridPane.add(borrow,0,0);
//        gridPane.add(remove,1,0);
//        gridPane.add(add,2,0);


//        GridPane grid = new GridPane();
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        grid.setPadding(new Insets(25, 25, 25, 25));
//
//        add.setStyle("-fx-background-color: yellow;");

        Scene main_scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(main_scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
