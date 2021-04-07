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
public class CustomerController implements Initializable {

    @FXML private TableView<Customer> tableView;
    @FXML private TableColumn<Customer, String> UsernameColumn;
    @FXML private TableColumn<Customer, String> PasswordColumn;
    @FXML private TableColumn<Customer, String> PointsColumn;
    
    //These instance variables are used to create new Person objects
    @FXML private TextField UsernameTextField;
    @FXML private TextField PasswordTextField;
//    @FXML private TextField PointsTextField;
    
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
     * the username of the customer
     */
    public void changeUserNameCellEvent(CellEditEvent edittedCell)
    {
        Customer customerSelected =  tableView.getSelectionModel().getSelectedItem();
        customerSelected.setPassword(edittedCell.getNewValue().toString());
    }
    
    /**
     * This method will allow the owner to double click on a cell and update
     * the password of the customer
     */
    public void changePasswordCellEvent(CellEditEvent edittedCell)
    {
        Customer customerSelected =  tableView.getSelectionModel().getSelectedItem();
        customerSelected.setPassword(edittedCell.getNewValue().toString());
    }
    
    /**
     * This method will allow the owner to double click on a cell and update
     * the points of the customer
     */
    public void changePointsCellEvent(CellEditEvent edittedCell)
    {
        Customer customerSelected =  tableView.getSelectionModel().getSelectedItem();
        customerSelected.setPoints(edittedCell.getNewValue().toString());
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
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Username"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Password"));
        PointsColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("Points"));
        
        //load dummy data
        tableView.setItems(getCustomer());
        
        //Update the table to allow for the first and last name fields
        //to be editable
        tableView.setEditable(true);
        UsernameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        PasswordColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        PointsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }
    
    /**
     * This method will create a new Book object and add it to the table. It also save the customer
 information in Customer text file.
     */
    public void AddButtonPushed()
    {
        // check for null values
        // check for duplicate username
        // return eror for above checks

        Customer newRegistration = new Customer(UsernameTextField.getText(), PasswordTextField.getText(), "0");
        if(newRegistration.checkNull()) {// && !newRegistration.duplicate()) {
           // display error 
            // System.out.println("checkNull is true");
           
        } else {
            //check for duplicate
                //  return error} else{BookB
            // System.out.println("checkNull is false");
            ObservableList<Customer> newCustomer = tableView.getItems();
            newCustomer.add(newRegistration);   // Add new user to the grid


            

            // add user to the txt file for permanent storage        
            FileWriter file_writer;
            try {
                file_writer = new FileWriter("Customers.txt", true);
                BufferedWriter bw = new BufferedWriter(file_writer);
                bw.write(newRegistration.saveString());
                bw.newLine();
                bw.close();
                file_writer.close();
            }catch (IOException E) {
                System.out.print("Error is ");
            }

            // clear user registation fields
            UsernameTextField.clear();
            PasswordTextField.clear();
//            PointsTextField.clear();
        }
    }
    
    
    
    /**
     * This method will remove the selected row(s) from the table 
     */
    public void deleteButtonPushed() throws FileNotFoundException, IOException
    {
        
//        tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
            
        ObservableList<Customer> selectedRows, allCustomers;
        allCustomers = tableView.getItems();
        
        //this gives us the rows that were selected
        selectedRows = tableView.getSelectionModel().getSelectedItems();
        System.out.println("selectedRows is " + selectedRows);
        
        //select the row from the tableview and store it in a variable
        
        System.out.println("selectedRows " + selectedRows);
        //tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItem());
        //read the file and each line and see if that variable matches with any of the line and remove it from file
 
        File customerFile = new File("Customers.txt");
        
        File tempFile = new File("temp.txt");
         if (tempFile.exists()) {
             System.out.println("temp.txt already exists");
             tempFile.delete(); //you might want to check if delete was successfull
         }
         System.out.println("File does not exist");
         tempFile.createNewFile();
 
        try {
            
            Scanner s = new Scanner(new File("Customers.txt"));
            System.out.println("scanner new file");
            FileWriter file_writer = new FileWriter(tempFile);
            BufferedWriter bw = new BufferedWriter(file_writer);
            
            
            while (s.hasNext())
            {
                String tempLine = s.nextLine();
                String [] fileLine = tempLine.split(",");
                if (fileLine[0].equals(selectedRows.get(0).getUsername()) && fileLine[1].equals(selectedRows.get(0).getPassword())) {
                    System.out.println(" skip this line because we dont need this customer anymore.");
                    
                    
                } else {
                    // write to temporary file
                    bw.write(tempLine);
                    bw.newLine();
                    
                }
            }
            bw.close();
            s.close();
            file_writer.close();
            
            
            // delete customer file
            customerFile.delete();
            System.out.println("deletedFile " + customerFile.getName() + " exists is " + customerFile.exists());
            tempFile.renameTo(customerFile);
            // rename temporary file to customer.txt
            selectedRows.forEach(allCustomers::remove);     
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("allCustomer is " + allCustomers);
    }
    
    
    
    public ObservableList<Customer>  getCustomer()
    {
        ObservableList<Customer> customer = FXCollections.observableArrayList();
            
        try {
            
            File f1 = new File("Customers.txt");
            if(f1.exists()){
                Scanner s = new Scanner(new File("Customers.txt"));
                ArrayList<String> customerList = new ArrayList<String>();
                while (s.hasNext())
                {
                    customerList.add(s.nextLine());
                    //parse each line and create an instance of customer
                    String[] customerDetails;
                    customerDetails = (customerList.get(customerList.size() - 1)).split(",");
                    System.out.println("customerDetails is " + customerDetails[0] + customerDetails[1] + customerDetails[2]);
                    Customer temp = new Customer (customerDetails[0], customerDetails[1], customerDetails[2]);
                    customer.add(temp);
                    //add the instance to customer
                }
                s.close();
                System.out.println(customerList);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }
    
//    public String  customerLevel() throws FileNotFoundException{
//        // test if customer has points
//        // if yes then rate it as follows:
//        // < 100  - bronze
//        // >100 and < 200 - silver
//        // > 200 - Gold
//        
//        
//        // getCustomer.points
//            // first get selected customer from the tableview
//            ObservableList<Customer> selectedRows, allCustomers;
//            allCustomers = tableView.getItems();
//            //this gives us the rows that were selected
//            selectedRows = tableView.getSelectionModel().getSelectedItems();
//            System.out.println("selectedRows is " + selectedRows);  // debug code
//            
//            // open file and find the matching line by using customer name and password
//            Scanner s = new Scanner(new File("Customer.txt"));
//            while (s.hasNext())
//            {
//                String[] line = s.nextLine().split(",");
//
//                
//                if(selectedRows.get(0).getUsername().equals(line[0]) && selectedRows.get(0).getPassword().equals(line[1])) {
//                    System.out.println("username: "+line[0]+" password: "+line[1]);
//
//                    Parent tableViewParent = FXMLLoader.load(getClass().getResource("interface.fxml"));
//                    Scene tableViewScene = new Scene(tableViewParent);
//
//                    //Stage info
//                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
//                    window.setTitle("Bookstore App");
//                    window.setScene(tableViewScene);
//                    window.show();
//                    break;
//                }
//            }
//            // if found then get points from line which should be the third element in the line
//            // else return error - exception
//        // use if conditions to check for rating
//        // return string [bronze, silver, gold]
//        // return int 0 -> bronze, 1 ->silver, 2 -> gold
//        return "level";
//    }
}
    


//public double buyBook (customerBooks book) {
//     
//        double cost;
//        cost = book.Bookprice;
//        this.points = cost * 10;
//        customerStatus.checkLevel();
//   
//        return cost;
//   
//    }
//   
//    public double redeemBuyBook (customerBooks book) {
//   
//        double cost;
//        cost = book.Bookprice - (points * 0.01);
//        this.points = cost * 10;
//        customerStatus.checkLevel();
//   
//        return cost;
//
//    }