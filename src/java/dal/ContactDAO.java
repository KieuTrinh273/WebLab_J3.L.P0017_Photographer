/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class ContactDAO {
    public Contact getContact() throws Exception {
        DBContext db = new DBContext();
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        String imgPath = db.getImagePath();
        
        try {
            String sql = "SELECT [address],[city],[country],[phone_number],[email],"
                    + "[facebook],[twitter],[google],[mapUrl],[description],[image]\n"
                    + "FROM [Contact]";
            connection = db.getConnection();
            prestm = connection.prepareStatement(sql);
            rs = prestm.executeQuery();

            if (rs.next()) {
                Contact contact = new Contact();
                contact.setAddress(rs.getString("address"));
                contact.setCity(rs.getString("city"));
                contact.setCountry(rs.getString("country"));
                contact.setPhoneNumber(rs.getString("phone_number"));
                contact.setEmail(rs.getString("email"));
                contact.setFacebook(rs.getString("facebook"));
                contact.setTwitter(rs.getString("twitter"));
                contact.setGoogle(rs.getString("google"));
                contact.setMapUrl(rs.getString("mapUrl"));
                contact.setDescription(rs.getString("description"));
                contact.setImage(imgPath + rs.getString("image"));
                return contact;
            }
        } catch (Exception ex) {
            throw ex;
        } 
        finally {
            db.closeConnection(rs, prestm, connection);
        }
        return null;
    }
}
