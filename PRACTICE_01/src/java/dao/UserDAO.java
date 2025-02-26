/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class UserDAO implements IDAO<UserDTO, String> {

    @Override
    public boolean creat(UserDTO object) {
        String sql = "INSERT INTO [dbo].[Users] (UserID, Name, UserName, Role, Password)"
                + "VALUES (?, ?, ?, ?, ?);";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, object.getUserID());
            ps.setString(2, object.getName());
            ps.setString(3, object.getUserName());
            ps.setString(4, object.getRole());
            ps.setString(5, object.getPassword());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<UserDTO> readAll() {
        List<UserDTO> li = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Users]";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserDTO us = new UserDTO(rs.getString("UserID"),
                        rs.getString("Name"),
                        rs.getString("UserName"),
                        rs.getString("Role"),
                        rs.getString("Password"));
                li.add(us);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return li;
    }

    @Override
    public UserDTO readById(String id) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE UserID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserDTO us = new UserDTO(rs.getString("UserID"),
                        rs.getString("Name"),
                        rs.getString("UserName"),
                        rs.getString("Role"),
                        rs.getString("Password"));
                return us;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public UserDTO readByUserName(String usName) {
        String sql = "SELECT * FROM [dbo].[Users] WHERE UserName = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UserDTO us = new UserDTO(rs.getString("UserID"),
                        rs.getString("Name"),
                        rs.getString("UserName"),
                        rs.getString("Role"),
                        rs.getString("Password"));
                return us;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(UserDTO object) {
        String sql = "UPDATE [dbo].[Users] SET Name = ?, UserName = ?, Role = ?, Password = ? WHERE UserID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, object.getName());
            ps.setString(2, object.getUserName());
            ps.setString(3, object.getRole());
            ps.setString(4, object.getPassword());
            ps.setString(5, object.getUserID());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM [dbo].[Users] WHERE UserID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
