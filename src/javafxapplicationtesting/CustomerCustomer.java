/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplicationtesting;


/**
 *
 * @author ahmed
 */
public class CustomerCustomer {
    private String Username;
    private String Password;
    private String Points;
    private String Status;
    
    
    public CustomerCustomer(String Username, String Password, String Points, String Status){
        this.Username = Username;
        this.Password = Password;
        this.Points = Points;
        this.Status = Status;
        
    }


    //Getters
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }
    
    //Setters
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String Points) {
        this.Points = Points;
    }

    public String getStatus() {
        return Status;
    }

    //This setStatus method will set the status of the customer depending on the points.
    //Points are passed as a String.
    
    public void setStatus(String Points) {
        //If points are less than 1000, status is silver, else gold
        
        if ((Integer.parseInt(Points)) < 1000) {
            this.Status =  "Silver";
        }
        else {          
             this.Status =  "Gold";
        }
        
    }
    public double buyBook (Books book) {
    
        double cost=0;
        //cost = book.Bookprice;
        //this.Points = cost * 10;
        //setStatus.checkLevel();
   
        return cost;
    
    }
   
    public double redeemBuyBook (Books book) {
   
        int cost=0;
        String cost2 = String.valueOf(cost);
        //cost = (Integer.parseInt(book.Bookprice) - (Points * 0.01));
        //this.Points = cost * 10;
        //setStatus.checkLevel();
   
        return cost;

    }
    
}