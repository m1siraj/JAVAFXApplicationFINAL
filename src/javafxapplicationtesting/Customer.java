/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationtesting;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author ahmed
 */
public class Customer {
    private final SimpleStringProperty Username;
    private final SimpleStringProperty Password;
    private final SimpleStringProperty Points;
    
    public Customer(String UserName, String Password, String Points){
        this.Username = new SimpleStringProperty(UserName);
        this.Password = new SimpleStringProperty(Password);
        this.Points = new SimpleStringProperty(Points);
        
    }


    //Getters
    public String getUsername(){
        return Username.get();
    }
    public String getPassword(){
        return Password.get();
    }
    public String getPoints(){
        return Points.get();
    }
    
    
    
    //Setters
    public void setUsername(String username){
        Username.set(username);
    }
    public void setPassword(String password){
        Password.set(password);
    }
    public void setPoints(String points){
        Points.set(points);
    }
    
    

    @Override
    public String toString() {
        return ""+ getUsername()+" "+ getPassword()+" "+getPoints();
    }
    
    public String saveString() {
        return ""+ getUsername()+","+ getPassword()+","+getPoints();
    }
    
    public Boolean checkNull() {
        if ((this.getUsername()).isEmpty() || (this.getPassword()).isEmpty()){
            // System.out.println("InCheck Null and true");
            return true;
        }
        
        // System.out.println("this.getUsername is "+ this.getUsername());
        // System.out.println("this.getPassword is "+ this.getPassword());
        // System.out.println("InCheck Null and false");
        return false;
    }
    
    
}



