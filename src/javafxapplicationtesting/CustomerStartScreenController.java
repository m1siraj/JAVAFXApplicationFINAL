/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationtesting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ahmed
 */
public class CustomerStartScreenController implements Initializable {
    
    @FXML Label welcomelabel;
    @FXML Label Points;
    @FXML TableView <Books> customerbooktableview;
    @FXML TableColumn <Books, String> booknameColumn;
    @FXML TableColumn <Books, String> bookpriceColumn;
    @FXML TableColumn actionColumn;
    
    @FXML private CheckBox selectAll;
   
    
    public void welcomelabel (String name){
        welcomelabel.setText("Welcome "+name);
    }
    
    //This method will populate the customer start screen tableview from the text file
    public ObservableList<Books>  getBooksInCustomerStartScreen()
    {
        ObservableList<Books> customerbook = FXCollections.observableArrayList();
            
        try {
            
            File f3 = new File("Books.txt");
            if(f3.exists()){
                Scanner s3 = new Scanner(new File("Books.txt"));
                ArrayList<String> bookList = new ArrayList<String>();
                while (s3.hasNext())
                {
                    bookList.add(s3.nextLine());
                    //parse each line and create an instance of book
                    String[] CustomerbookDetails;
                    CustomerbookDetails = (bookList.get(bookList.size() - 1)).split(",");
                    System.out.println("bookDetails is " + CustomerbookDetails[0] + CustomerbookDetails[1]);
                    Books temp1 = new Books (CustomerbookDetails[0], CustomerbookDetails[1]);
                    System.out.println("temp1 is " + temp1);
                    customerbook.add(temp1);
                   
                }
                s3.close();
                System.out.println(customerbook);
            }
        } catch (FileNotFoundException e) {
            Logger.getLogger(BooksController.class.getName()).log(Level.SEVERE, null, e);
        }
        return customerbook;
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //set up the columns in the table
        booknameColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("Bookname"));
        bookpriceColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("Bookprice"));
 
        //load dummy data
        
        customerbooktableview.setItems(getBooksInCustomerStartScreen());
        
        //Update the table to allow for the first and last name fields
        //to be editable
        customerbooktableview.setEditable(true);
        booknameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        bookpriceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
        
        //Checkbox Column
        actionColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("Select"));
        
        
//        selectAll.selectedProperty().addListener(new ChangeListener<Boolean>() {
//        @Override
//        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//            System.out.println("Select All Selected");
//            
//            items = customerbooktableview.getItems();
//            
//            for(Books item : items)
//            {
//                
//                if(selectAll.isSelected())
//                    item.getRemark().setSelected(true);
//                
//                else 
//                    item.getRemark().setSelected(false);
//            }
//        }
//        });
    }
    
    //When this method is called, it will change the scene to a Login view
    
    public void CustomerStartScreenLogoutButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
    }
    
    public void CustomerStartScreenBuyButtonPushed(ActionEvent event) throws IOException {
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CustomerCostScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.setTitle("Bookstore App");
        window.show();
        
    }
    
    public void CustomerStartScreenRedeemPointsandBuyButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("CustomerCostScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //Stage info
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.setTitle("Bookstore App");
        window.show();
        
    }
    
    
}
