package dao;

import dto.ShoesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class ShoesDAO implements IDAO<ShoesDTO, String> {

    @Override
    public boolean creat(ShoesDTO object) {
        String sql = "INSERT INTO (ShoesID, ShoesName, Trademark, Publish_year, Quantity)"
                + "VALUES(?, ?, ?, ?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, object.getShoesID());
            ps.setString(2, object.getShoesName());
            ps.setString(3, object.getTrademark());
            ps.setInt(4, object.getPublishYear());
            ps.setInt(5, object.getQuantity());
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<ShoesDTO> readAll() {
        String sql = "SELECT * FROM [dbo].[shoes]";
        List<ShoesDTO> li = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShoesDTO shoes = new ShoesDTO(rs.getString("ShoesID"),
                        rs.getString("ShoesName"),
                        rs.getString("Trademark"),
                        rs.getInt("Publish_year"),
                        rs.getInt("Quantity"));
                li.add(shoes);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return li;
    }

    @Override
    public ShoesDTO readById(String id) {
        String sql = "SELECT * FROM [dbo].[shoes] WHERE ShoesID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShoesDTO sDto = new ShoesDTO(rs.getString("ShoesID"),
                        rs.getString("ShoesName"),
                        rs.getString("Trademark"),
                        rs.getInt("Publish_year"),
                        rs.getInt("Quantity"));
                return sDto;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ShoesDTO> readByName(String name) {
        String sql = "SELECT * FROM [dbo].[shoes] WHERE ShoesName LIKE ?";
        List<ShoesDTO> li = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShoesDTO shoes = new ShoesDTO(rs.getString("ShoesID"),
                        rs.getString("ShoesName"),
                        rs.getString("Trademark"),
                        rs.getInt("Publish_year"),
                        rs.getInt("Quantity"));
                li.add(shoes);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return li;
    }

    public List<ShoesDTO> readByName2(String name) {
        String sql = "SELECT * FROM [dbo].[shoes] WHERE ShoesName LIKE ? AND Quantity > 0";
        List<ShoesDTO> li = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShoesDTO shoes = new ShoesDTO(rs.getString("ShoesID"),
                        rs.getString("ShoesName"),
                        rs.getString("Trademark"),
                        rs.getInt("Publish_year"),
                        rs.getInt("Quantity"));
                li.add(shoes);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return li;
    }

    @Override
    public boolean update(ShoesDTO object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean updateQuantityToZero(String id) {
        String sql = "UPDATE [dbo].[shoes] SET Quantity = 0 WHERE ShoesID = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ShoesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
