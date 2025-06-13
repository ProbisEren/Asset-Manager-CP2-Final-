/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp2_lab_final;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class userManager {
    static DatabaseManager db = new DatabaseManager();
    private static int current_user_id=-1;
    
    //returns current users username
    public static String getUsername(){
     String sql = "SELECT * FROM users WHERE idusers="+current_user_id;
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            rs.next();
            return rs.getString(2);
             

            
            
        } catch (SQLException e) {
            System.err.println(" Kullanıcı adı hatası: " + e.getMessage());
        }
        return null;
    }
    
    //returns current users password
    public static String getPassword(){
     String sql = "SELECT * FROM users WHERE idusers="+current_user_id;
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            rs.next();
            return rs.getString(4);
             

            
            
        } catch (SQLException e) {
            System.err.println(" Kullanıcı şifre alma hatası: " + e.getMessage());
        }
        return null;
    }
    
    public static int getBalance(){
     String sql = "SELECT * FROM users WHERE idusers="+current_user_id;
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            rs.next();
            
            return rs.getInt(7);
             

            
            
        } catch (SQLException e) {
            System.err.println(" Kullanıcı adı hatası: " + e.getMessage());
        }
        return 0;
    }
    
    public static void setBalance(double newBalance){
        String sql = "UPDATE users SET balance = ? WHERE idusers = "+current_user_id;
     
        try (Connection conn = db.connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                )
        {
            
             ps.setDouble(1, newBalance);
             ps.executeUpdate();
             

            
            
        } catch (SQLException e) {
            System.err.println(" set balance hatası: " + e.getMessage());
        }
        
    }
    
    

    // adds new user to database
    public static void addUser(String name, String surname, String password, String cardInfos, int age, double balance) {
        String query = "INSERT INTO users(username, surname, password, card_infos, age, balance) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(query)) 
        {

            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, password);
            ps.setString(4, cardInfos);
            ps.setInt(5, age);
            ps.setDouble(6, balance);

            ps.executeUpdate();
            System.out.println(" Kullanıcı başarıyla eklendi.");
        } catch (SQLException e) {
            System.err.println(" Kullanıcı ekleme hatası: " + e.getMessage());
        }
        
    }
    
    // returns current_user_id
    public static int getCurrent_UserID(){
        return current_user_id;
    }
    
    //verilen bilgili kullanıcının giriş yapıp yapamayacağını kontrol eder, girebilirse current_user_id'yi düzenler
    public static boolean can_login(String username, String password) throws IOException{
        String sql = "SELECT * FROM users";
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            
            while(rs.next()){
                if(rs.getString(2).compareTo(username)==0&&rs.getString(4).compareTo(password)==0){ // 0 değil 1 olabilir kontrol et yine
                    
                    current_user_id = rs.getInt(1);
                    System.out.println("girebilir ve ID:"+current_user_id);
                    
                    PrintWriter fw = new PrintWriter(new FileWriter("dosya.txt", true));
                    fw.println(userManager.getCurrent_UserID() + " " + userManager.getUsername() + " logged in.");
                    fw.close();
                    return true;
                }
                
            }
            System.out.println("giremez");
            return false;
             

            
            
        } catch (SQLException e) {
            System.err.println(" Kullanıcı giriş hatası: " + e.getMessage());
        }
                    System.out.println("giremez");

        return false;
        
    }
    
    
    
    public static int does_have(int val_ID){
        String sql = "SELECT * FROM user_asset WHERE user_id="+current_user_id;
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            while(rs.next()){
                if(rs.getInt(2)==val_ID){
                    System.out.println("he has that");
                    return rs.getInt(3);
                }
            
            
            }
            
            
             

            
            
        } catch (SQLException e) {
            System.err.println(" does_have hatası: " + e.getMessage());
        }
        System.out.println("sahip değil");
        return -1;
    }
    
    public static int id_of(String name){
        String sql = "SELECT idusers FROM users WHERE username='" + name + "'";
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
             if (rs.next()) {  // sadece veri varsa getir
            return rs.getInt(1);
            }
            
             

            
            
        } catch (SQLException e) {
            System.err.println(" id yok " + e.getMessage());
        }
        return -1;
    
    }
    
    
    
    
    
}

