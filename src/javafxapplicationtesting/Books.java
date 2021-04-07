/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationtesting;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 *
 * @author ahmed
 */
//Books file is common to owner and customer. Customer Start Screen tableview needs to be populated by the Books.txt file 
public class Books {
    private final SimpleStringProperty Bookname;
    private final SimpleStringProperty Bookprice;
    private CheckBox select;
    
    public Books(String Bookname, String Bookprice){
        this.Bookname = new SimpleStringProperty(Bookname);
        this.Bookprice = new SimpleStringProperty(Bookprice);
        this.select = new CheckBox();
    }


    //Getters
    public String getBookname(){
        return Bookname.get();
    }
    public String getBookprice(){
        return Bookprice.get();
    }
    public CheckBox getSelect() {
        return select;
    }
    
    
    
    
    //Setters
    public void setBookname(String bookname){
        Bookname.set(bookname);
    }
    public void setBookprice(String bookprice){
        Bookprice.set(bookprice);
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
    
    
    

    @Override
    public String toString() {
        return ""+ getBookname()+","+ getBookprice();
    }
    
    public String saveString() {
        return ""+ getBookname()+","+ getBookprice();
    }
    
    public Boolean checkNullBook() {
        if ((this.getBookname()).isEmpty() || (this.getBookprice()).isEmpty()){
            // System.out.println("InCheck Null and true");
            return true;
        }
        
        // System.out.println("this.getUsername is "+ this.getUsername());
        // System.out.println("this.getPassword is "+ this.getPassword());
        // System.out.println("InCheck Null and false");
        return false;
    }
    
    
    
}



