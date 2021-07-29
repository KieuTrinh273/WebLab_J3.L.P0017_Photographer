/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class ViewDAO {
    public int getViews() throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement preSta = null;
        ResultSet rs = null;
        int count =0;
        try {
            String sql = "SELECT [Viewed] FROM [View]";
            connection = dbContext.getConnection();
            preSta = connection.prepareStatement(sql);
            rs = preSta.executeQuery();
            if(rs.next()){
                count = rs.getInt("Viewed");
            }
            return count;
        } catch (Exception e) {
            throw e;
        }finally {
            dbContext.closeConnection(rs, preSta, connection);
        }
    }
    
    public void updateView() throws Exception {
        DBContext dbContext = new DBContext();
        Connection connection = null;
        PreparedStatement preSta = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE [dbo].[View]SET [Viewed] = [Viewed]+1;";
            connection = dbContext.getConnection();
            preSta = connection.prepareStatement(sql);
            preSta.executeUpdate();
        } catch (Exception e) {
            throw e;
        }finally {
            dbContext.closeConnection(rs, preSta, connection);
        }
    }
}
