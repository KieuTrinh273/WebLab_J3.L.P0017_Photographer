/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import entity.Contact;
import entity.Gallery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class GalleryDAO {

    public List<Gallery> getTop3Galleries() throws Exception {
        DBContext dBContext = new DBContext();
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT top 3 [id],[title],[gallery_content],[galleryName] "
                    + "FROM [Galleries]";
            connection = dBContext.getConnection();
            prestm = connection.prepareStatement(sql);
            rs = prestm.executeQuery();
            List<Gallery> galleries = new ArrayList<>();

            while (rs.next()) {
                Gallery gallery = new Gallery();
                gallery.setGalleryContent(rs.getString("gallery_content"));
                gallery.setTitle(rs.getString("title"));
                gallery.setId(rs.getInt("id"));
                gallery.setGalleryName(rs.getString("galleryName"));
                galleries.add(gallery);
            }
            return galleries;
        } catch (Exception ex) {
            throw ex;
        } finally {
            dBContext.closeConnection(rs, prestm, connection);
        }
    }
    
    public List<Gallery> getAllGalleries() throws Exception {
        DBContext dBContext = new DBContext();
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT [id],[title],[gallery_content],[galleryName] "
                    + "FROM [Galleries]";
            connection = dBContext.getConnection();
            prestm = connection.prepareStatement(sql);
            rs = prestm.executeQuery();
            List<Gallery> galleries = new ArrayList<>();

            while (rs.next()) {
                Gallery gallery = new Gallery();
                gallery.setGalleryContent(rs.getString("gallery_content"));
                gallery.setTitle(rs.getString("title"));
                gallery.setId(rs.getInt("id"));
                gallery.setGalleryName(rs.getString("galleryName"));
                galleries.add(gallery);
            }
            return galleries;
        } catch (Exception ex) {
            throw ex;
        } finally {
            dBContext.closeConnection(rs, prestm, connection);
        }
    }

    public Gallery getGalleryByID(int id) throws Exception {
        DBContext dBContext = new DBContext();
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT [id],[title],[gallery_content],[galleryName] "
                    + "FROM [Galleries]"
                    + "WHERE id = ?";
            connection = dBContext.getConnection();
            prestm = connection.prepareStatement(sql);
            prestm.setInt(1, id);
            rs = prestm.executeQuery();
            
            if(rs.next()){
                Gallery gallery = new Gallery();
                gallery.setGalleryContent(rs.getString("gallery_content"));
                gallery.setGalleryName(rs.getString("galleryName"));
                gallery.setId(rs.getInt("id"));
                gallery.setTitle(rs.getString("title"));
                return gallery;
            }
        } catch (Exception ex) {
            throw ex;
        } finally{
            dBContext.closeConnection(rs, prestm, connection);
        }
        return null;
    }
    
    public List<Gallery> pagingForGalleries(int pageIndex, int pageSize) throws Exception {
        DBContext dBContext = new DBContext();
        Connection connection = null;
        PreparedStatement prestm = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM "
                    + "(SELECT ROW_NUMBER() OVER (ORDER BY id asc) AS [NO.],"
                    + "[id],[title],[gallery_content],[galleryName] "
                    + "FROM Galleries) as x "
                    + "WHERE [NO.] BETWEEN ?*?-? AND ?*?";
            connection = dBContext.getConnection();
            prestm = connection.prepareStatement(sql);
            prestm.setInt(1, pageIndex);
            prestm.setInt(2, pageSize);
            prestm.setInt(3, pageSize - 1);
            prestm.setInt(4, pageIndex);
            prestm.setInt(5, pageSize);
            rs = prestm.executeQuery();
            List<Gallery> galleries = new ArrayList<>();

            while (rs.next()) {
                Gallery gallery = new Gallery();
                gallery.setGalleryContent(rs.getString("gallery_content"));
                gallery.setTitle(rs.getString("title"));
                gallery.setId(rs.getInt("id"));
                gallery.setGalleryName(rs.getString("galleryName"));
                galleries.add(gallery);
            }
            return galleries;
        } catch (Exception ex) {
            throw ex;
        } finally {
            dBContext.closeConnection(rs, prestm, connection);
        }
    }
//
//    public List<Gallery> getGalleriesByPage(List<Gallery> galleries, int start, int end) {
//        List<Gallery> galleriesOnPage = new ArrayList<>();
//        for (int i = start; i < end; i++) {
//            galleriesOnPage.add(galleries.get(i));
//        }
//        return galleriesOnPage;
//    }
    
}
