/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Gallery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class ImageDAO {

    public String get1stImageByGalleryID(int id) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement preSta = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT top 1 [image] "
                    + "FROM [Gallery_images] "
                    + "WHERE gallery_id = ?";
            connection = dbContext.getConnection();
            preSta = connection.prepareStatement(sql);
            preSta.setInt(1, id);
            rs = preSta.executeQuery();

            if (rs.next()) {
                return (dbContext.getImagePath() + rs.getString("image"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, preSta, connection);
        }
        return null;
    }

    public List<String> getAllImageByGalleryID(int id) throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement preSta = null;
        ResultSet rs = null;
        String imgPath = dbContext.getImagePath();
        List<String> images = new ArrayList<>();
        try {
            String sql = "SELECT [image] "
                    + "FROM [Gallery_images] "
                    + "WHERE gallery_id=?";
            connection = dbContext.getConnection();
            preSta = connection.prepareStatement(sql);
            preSta.setInt(1, id);
            rs = preSta.executeQuery();

            while (rs.next()) {
                images.add(imgPath + rs.getString("image").trim());
            }
            return images;
        } catch (Exception e) {
            throw e;
        } finally {
            dbContext.closeConnection(rs, preSta, connection);
        }
    }

    
    public List<String> pagingForImg(int galleryID, int pageIndex, int pageSize) throws Exception {
        DBContext dBContext = new DBContext();
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        String imgPath = dBContext.getImagePath();

        try {
            String sql = "SELECT * FROM  "
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY [gallery_id] asc) AS [NO.],"
                    + "[gallery_id], [image] "
                    + "FROM Gallery_images "
                    + "WHERE [gallery_id] = ?) as x "
                    + "WHERE [NO.] BETWEEN ?*?-? and ?*?";
            connection = dBContext.getConnection();
            prestm = connection.prepareStatement(sql);
            prestm.setInt(1, galleryID);
            prestm.setInt(2, pageIndex);
            prestm.setInt(3, pageSize);
            prestm.setInt(4, pageSize - 1);
            prestm.setInt(5, pageIndex);
            prestm.setInt(6, pageSize);
            rs = prestm.executeQuery();
            List<String> img = new ArrayList<>();

            while (rs.next()) {
                img.add(imgPath + rs.getString("image").trim());
            }
            return img;
        } catch (Exception ex) {
            throw ex;
        } finally {
            dBContext.closeConnection(rs, prestm, connection);
        }
    }
}
