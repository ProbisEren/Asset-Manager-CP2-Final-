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
import javax.swing.JOptionPane;

/**
 *
 * @author 1234
 */
public class walletManager {
    static DatabaseManager db = new DatabaseManager();
    
    
    // satın alım ve balance düzenleme işlemini yapar
    public static boolean buy(int val_ID,int how_many) throws IOException{
        double tutar=how_many*valuableManager.price_of(val_ID);
        if(tutar>userManager.getBalance()){
            
            JOptionPane.showMessageDialog(null, "paran yetmedi babba");
            return false;
        }
        
        if(userManager.does_have(val_ID) <= 0)
{
        
        
       String sql = "INSERT INTO user_asset(user_id,valuable_id,how_many) VALUES(?,?,?)";

        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                
PreparedStatement ps = conn.prepareStatement(sql);)
        {
            ps.setInt(1, userManager.getCurrent_UserID());
            ps.setInt(2, val_ID);
            ps.setInt(3, how_many);
            ps.executeUpdate();
            
            System.out.println("satın alım yapıuıldı");
            
            userManager.setBalance(userManager.getBalance()-tutar);
            
            PrintWriter fw = new PrintWriter(new FileWriter("dosya.txt",true));
            fw.println(userManager.getCurrent_UserID()+" "+userManager.getUsername()+" "+valuableManager.name_of(val_ID)+" "+how_many+" bought");
            fw.close();
            
            return true;
            
             

            
            
        } catch (SQLException e) {
            System.err.println(" Kullanıcı satın alma hatası: " + e.getMessage());
        }
                    

         }else{
         String sql = "UPDATE user_asset SET how_many = ? WHERE valuable_id = "+val_ID;
     
        try (Connection conn = db.connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                )
        {
            
             ps.setInt(1, userManager.does_have(val_ID)+how_many);
             ps.executeUpdate();
             
            System.out.println("satın alım yapıuıldı");
            userManager.setBalance(userManager.getBalance()-tutar);
            
            PrintWriter fw = new PrintWriter(new FileWriter("dosya.txt"));
            fw.println(userManager.getCurrent_UserID()+" "+userManager.getUsername()+" "+valuableManager.name_of(val_ID)+" "+how_many+" bought");
            fw.close();
            return true;
            
        } catch (SQLException e) {
            System.err.println(" satın alma hatası: " + e.getMessage());
        }
        }
        
        
        return false;
    
    }
    
    
    public static void TopUpBalance(int add){
        userManager.setBalance(add+userManager.getBalance());
        JOptionPane.showMessageDialog(null, "para hesabına eklendi");
        
    
    }
    
    public static int how_many_different(){
        String sql = "SELECT * FROM user_asset WHERE user_id="+userManager.getCurrent_UserID();
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            int counter =0;
            while(rs.next()){
                counter++;
            
            }
             return counter;

            
            
        } catch (SQLException e) {
            System.err.println(" different hatası: " + e.getMessage());
        }
        return  0;
    
    
    
    }
    
    public static int nth_val_of_user(int n){
        
        String sql = "SELECT * FROM user_asset WHERE user_id="+userManager.getCurrent_UserID();
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            int counter =1;
            while(rs.next()){
                if(counter ==n)
                    return rs.getInt(2); // userın n. sahip olduğu şeyin idsi
            counter++;
            }
             

            
            
        } catch (SQLException e) {
            System.err.println(" nth hatası: " + e.getMessage());
        }
        return 0;
        
    
    
    
    }
    
    public static int how_many(int n){
        
        String sql = "SELECT * FROM user_asset WHERE user_id="+userManager.getCurrent_UserID();
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            int counter =1;
            while(rs.next()){
                if(counter ==n)
                    return rs.getInt(3); // userın n. sahip olduğu şeyin adeti
            counter++;
            }
             

            
            
        } catch (SQLException e) {
            System.err.println(" howmany hatası: " + e.getMessage());
        }
        return  0;
    
    
    
    
    }
    
    public static int how_many_have_(int id){
    
    String sql = "SELECT how_many FROM user_asset WHERE valuable_id="+id;
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            if (rs.next()) {  // sadece veri varsa getir
            return rs.getInt(1);
        }
             

            
            
        } catch (SQLException e) {
            System.err.println(" howmany hatası: " + e.getMessage());
        }
        return  -1;
    
    
    
    }
    
    
    public static void sell(int val_ID, int how_many) throws IOException{
        
        if(how_many>how_many_have_(val_ID)){
           System.out.println("sahip olduğundan fazla satamazsın");
            JOptionPane.showMessageDialog(null, "sahip olduğundan fazla satamazsın");
           return;
        }else if (how_many<how_many_have_(val_ID)){
            
            String sql = "UPDATE user_asset SET how_many = ? WHERE valuable_id = "+val_ID;
     
        try (Connection conn = db.connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                )
        {
            
             ps.setInt(1, userManager.does_have(val_ID)-how_many);
             ps.executeUpdate();
             
            System.out.println("satış yapıldı");
           userManager.setBalance(userManager.getBalance()+valuableManager.price_of(val_ID)*how_many);
            
            PrintWriter fw = new PrintWriter(new FileWriter("dosya.txt",true));
            fw.println(userManager.getCurrent_UserID()+" "+userManager.getUsername()+" "+valuableManager.name_of(val_ID)+" "+how_many+" sold");
            fw.close();
            
            
        } catch (SQLException e) {
            System.err.println(" satın alma hatası: " + e.getMessage());
        }
        }else{
            String sql = "DELETE FROM user_asset WHERE valuable_id = " + val_ID;
            try (Connection conn = db.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.executeUpdate();

                System.out.println("tamamı satıldı, kayıt silindi");
                PrintWriter fw = new PrintWriter(new FileWriter("dosya.txt", true));
                fw.println(userManager.getCurrent_UserID() + " " + userManager.getUsername() + " " + valuableManager.name_of(val_ID) + " " + how_many + " sold");
                fw.close();
                userManager.setBalance(userManager.getBalance() + valuableManager.price_of(val_ID) * how_many);

            } catch (SQLException e) {
                System.err.println(" silme hatası: " + e.getMessage());
            }
        
        
        }
            
        
        
        
        
        }
    
    
    
    
    
    
    
    
    
    
    
    
}
