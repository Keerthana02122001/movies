/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.myproject.moviesjdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author keerthana
 */
public class MoviesJDBC {

    public static void main(String[] args) throws IOException {
     
       createTable();
       post();
       get();
    }
    
    public static ArrayList<String> get() throws IOException{
        
        try{
            Scanner sc=new Scanner(System.in);
            Connection con=getConnection();
            System.out.println("Enter actor name for movies to be displayed");
            String actname=sc.nextLine();
            System.out.println("Actor movies are:");
            PreparedStatement pst=con.prepareStatement("Select Name FROM Movies where Actor='"+actname+"' ");
            ResultSet rst=pst.executeQuery();
            ArrayList<String> arr=new ArrayList<String>();
            while(rst.next()){
                System.out.println(rst.getString("Name"));
                System.out.println("");
               // System.out.println(rst.getString("Name"));
                arr.add(rst.getString("Name"));
                
            }
             System.out.println("Displayed successfully");
             return arr;
        }
        catch(Exception e){
            
            System.out.println(e);
            return null;
        }
    }
    
    public static void post()throws IOException{
        
        try{
           Connection con=getConnection();
           Scanner sc=new Scanner(System.in);
           while(true){
               
           System.out.println("Enter Movie name");
           String name=sc.nextLine();
           
           System.out.println("Enter Actor name");
           String actor=sc.nextLine();
           
           System.out.println("Enter Actress name");
           String actress=sc.nextLine();
           
           System.out.println("Enter Director name");
           String director=sc.nextLine();
           
           System.out.println("Enter release date");
           String date=sc.nextLine();
           
           
           //PreparedStatement pst=con.prepareStatement("insert into Movies(Name,Actor,Actress,Director,Year_of_release) values (\"Baahubali\",\"Prabhas\",\"Anushka\",\"Rajamouli\",\"2015-07-10\")");
           PreparedStatement pst=con.prepareStatement("insert into Movies values('"+name+"','"+actor+"','"+actress+"','"+director+"','"+date+"')");
           pst.executeUpdate();
           System.out.println("Insertion completed");
           System.out.println("Enter yes/no to insert more records");
           String rsp=sc.nextLine();
           if(rsp.equalsIgnoreCase("no")){
               break;
           }
               
           }
           
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void createTable() throws IOException{
        
        try{
        Connection con=getConnection();
        String query="CREATE TABLE Movies(Name varchar(40),Actor varchar(40),Actress varchar(40),Director varchar(40),Year_of_release varchar(40))";
        Statement st=con.createStatement();
            
         st.execute(query);
         System.out.println("Created table");
        }
        
        catch(Exception e){
            System.out.println(e);
        }
        
    }
        
    public static Connection getConnection()throws IOException{
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Movies","root","keerthana#1234");
            System.out.println("Connection successfull");
           
            
            return con;
            
        }
        
        catch(Exception e){
            System.out.println(e);
            
        }
        return null;
    }     
    
}
