/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.BookDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

/**
 *
 * @author Dell
 */
public class BookDAO implements IDAO<String, BookDTO> {

    @Override
    public boolean create(String entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readById(BookDTO id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(String entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(BookDTO id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> search(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<BookDTO> searchByTitle(String searchTerm) {

        String sql = "SELECT + FROM tblBooks WHERE title LIKE ?";
        List<BookDTO> list = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookDTO book = new BookDTO(
                        rs.getString("BookID"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getInt("PublishYear"),
                        rs.getDouble("Price"),
                        rs.getInt("Quantity"));
                list.add(book);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
