/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationtesting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class BooksController implements Initializable {

    @FXML private TableView<Books> booktableview;
    @FXML private TableColumn<Books, String> BooknameColumn;
    @FXML private TableColumn<Books, String> BookpriceColumn;
    
    //These instance variables are used to create new Person objects
    @FXML private TextField BooknameTextField;
    @FXML private TextField BookpriceTextField;

    //When this method is called, it will change the scene to a Login view
    
    public void closeButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.setTitle("Bookstore App");
        window.show();
        
        
    }
    
    
    /**
     * This method will allow the owner to double click on a cell and update
     * the book name of the book
     */
    public void changeBookNameCellEvent(CellEditEvent edittedCell)
    {
        Books bookSelected =  booktableview.getSelectionModel().getSelectedItem();
        bookSelected.setBookname(edittedCell.getNewValue().toString());
    }
    
    /**
     * This method will allow the owner to double click on a cell and update
     * the book price of the book
     */
    public void changeBookpriceCellEvent(CellEditEvent edittedCell)
    {
        Books bookSelected =  booktableview.getSelectionModel().getSelectedItem();
        bookSelected.setBookprice(edittedCell.getNewValue().toString());
    }
    
    public void BackButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("interface.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
    }
//    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        BooknameColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("Bookname"));
        BookpriceColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("Bookprice"));
        
        //load dummy data
        
        booktableview.setItems(getBooks());
        
        //Update the table to allow for the first and last name fields
        //to be editable
        booktableview.setEditable(true);
        BooknameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        BookpriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }
    
    /**new 
     * This method will create a new Book object and add it to the table. It also save the book
 information in Book text file.
     */
    public void AddButtonPushed()
    {
        // check for null values
        // check for duplicate books
        // return eror for above checks

        Books newBook = new Books(BooknameTextField.getText(), BookpriceTextField.getText());
        if(newBook.checkNullBook()) {// && !newRegistration.duplicate()) {
           // display error 
            // System.out.println("checkNull is true");
           
        } else {
            //check for duplicate
                //  return error} else{
            // System.out.println("checkNull is false");
            ObservableList<Books> newbook = booktableview.getItems();
            newbook.add(newBook);   // Add new user to the grid


            

            // add book to the txt file for permanent storage        
            FileWriter file_writer;
            try {
                file_writer = new FileWriter("Books.txt", true);
                BufferedWriter bw1 = new BufferedWriter(file_writer);
                bw1.write(newBook.saveString());
                bw1.newLine();
                bw1.close();
            }catch (IOException E) {
                System.out.print("Error is ");
            }

            // clear user registation fields
            BooknameTextField.clear();
            BookpriceTextField.clear();
            
        }
    }
    
    
    
    /**
     * This method will remove the selected row(s) from the table 
     */
    public void deleteButtonPushed() throws IOException
    {
        
        
        //copy from bookscontroller.java
        ObservableList<Books> selectedbookrows, allBooks;
        allBooks = booktableview.getItems();
        
        //this gives us the rows that were selected
        selectedbookrows = booktableview.getSelectionModel().getSelectedItems();
        System.out.println("selectedRows is " + selectedbookrows);
        
        
        
        //select the row from the tableview and store it in a variable
        
        System.out.println("selectedRows " + selectedbookrows);
        //tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
        //read the file and each line and see if that variable matches with any of the line and remove it from file
 
        File bookFile = new File("Books.txt");
        
        File tempFile1 = new File("tempbooks.txt");
         if (tempFile1.exists()) {
             System.out.println("tempbooks.txt already exists");
             tempFile1.delete(); //you might want to check if delete was successfull
         }
         System.out.println("Fiel does not exist");
         tempFile1.createNewFile();
 
        try {
            
            Scanner s2 = new Scanner(new File("Books.txt"));
            System.out.println("scanner new file");
            FileWriter file_writer = new FileWriter(tempFile1);
            BufferedWriter bw2 = new BufferedWriter(file_writer);
            
            
            while (s2.hasNext())
            {
                String tempLine = s2.nextLine();
                String [] fileLine = tempLine.split(",");
                if (fileLine[0].equals(selectedbookrows.get(0).getBookname()) && fileLine[1].equals(selectedbookrows.get(0).getBookprice())) {
                    System.out.println(" skip this line because we dont need this customer anymore.");
                    
                    
                } else {
                    // write to temporary file
                    bw2.write(tempLine);
                    bw2.newLine();
                    
                }
            }
            bw2.close();
            s2.close();
            file_writer.close();
            
            // delete customer file
            bookFile.delete();
            System.out.println("deletedFile " + bookFile.getName() + " exists is " + bookFile.exists());
            tempFile1.renameTo(bookFile);
            // rename temporary file to customer.txt
            selectedbookrows.forEach(allBooks::remove);     //
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("allBooks is " + allBooks);
    }
    
    
    
    public ObservableList<Books>  getBooks()
    {
        ObservableList<Books> book = FXCollections.observableArrayList();
            
        try {
            
            File f2 = new File("Books.txt");
            if(f2.exists()){
                Scanner s2 = new Scanner(new File("Books.txt"));
                ArrayList<String> bookList = new ArrayList<String>();
                while (s2.hasNext())
                {
                    bookList.add(s2.nextLine());
                    //parse each line and create an instance of customer
                    String[] bookDetails;
                    bookDetails = (bookList.get(bookList.size() - 1)).split(",");
                    System.out.println("bookDetails is " + bookDetails[0] + bookDetails[1]);
                    Books temp1 = new Books (bookDetails[0], bookDetails[1]);
                    System.out.println("temp1 is " + temp1);
                    book.add(temp1);
                    //add the instance to book
                }
                s2.close();
                System.out.println(book);
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, e);
        }
        return book;
    }
}
    


