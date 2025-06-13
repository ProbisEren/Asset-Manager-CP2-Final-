package com.mycompany.cp2_lab_final;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class valuableManager {
    static DatabaseManager db = new DatabaseManager();
    
    public static void add_newValuable(String name,double price){
    
        if(is_addable(name)){
        // adds new user to database
   
        String query = "INSERT INTO valuables(name, price) VALUES (?, ?)";

        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(query)) 
        {
            

            ps.setString(1, name);
            ps.setDouble(2, price);

            ps.executeUpdate();
            System.out.println(" Valuable başarıyla eklendi.");
        } catch (SQLException e) {
            System.err.println(" Valuable ekleme hatası: " + e.getMessage());
        }
        }
        else
            System.out.println("already added");
    
    
    
    }
    
    public static boolean is_addable(String name){ // check if it is addable or not
    
    String sql = "SELECT name FROM valuables WHERE name='" + name + "'";
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            if (rs.next()) {

                return false;

            }
            
            
             

            
            
        } catch (SQLException e) {
            System.err.println(" is_addable hatası: " + e.getMessage());
        }
        
        return true;
    }
    
    public static double price_of(int val_ID){
        String sql = "SELECT price FROM valuables WHERE id="+val_ID;
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
            if (rs.next()) {
            
            return rs.getDouble(1);
             
            }
            
            
        } catch (SQLException e) {
            System.err.println(" price of hatası: " + e.getMessage());
        }
        return 0;
    
    }
    
    public static String name_of(int val_ID){
        String sql = "SELECT name FROM valuables WHERE id="+val_ID;
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs= st.executeQuery(sql))
        {
             if (rs.next()) {  // sadece veri varsa getir
            return rs.getString(1);
        }
            
             

            
            
        } catch (SQLException e) {
            System.err.println(" adı yok " + e.getMessage());
        }
        return null;
    
    }
    
    public static int id_of(String name){
        String sql = "SELECT id FROM valuables WHERE name='" + name + "'";
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
    
    public static void change_values(){
        String s="SELECT id, price FROM valuables";
        String sql = "UPDATE valuables SET price = ? WHERE id = ?";
     
        try (Connection conn = db.connect();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(s);
                PreparedStatement ps = conn.prepareStatement(sql);
                )
        {
            
            while (rs.next()) {
        int id = rs.getInt("id");
        double eskiFiyat = rs.getDouble("price");
        double rndm = ((Math.random()*20-10)/100);
        if(0<=eskiFiyat+eskiFiyat*rndm){
        ps.setDouble(1, Math.round((eskiFiyat+eskiFiyat*rndm) * 1000.0) / 1000.0);   // yeni fiyat
        ps.setInt(2, id);             
        ps.executeUpdate();
        }else{
         ps.setDouble(1, eskiFiyat*1.1);   // yeni fiyat 0dan küçük çıkarsa eski halini %10 artır
        ps.setInt(2, id);            
        ps.executeUpdate();
        }
            

        
    }

    ps.close();
            
            
           
            
            
        } catch (SQLException e) {
            System.err.println(" fiyat güncelleme hatası: " + e.getMessage());
        }
    
    
    
    }
    
    
    
    
}
