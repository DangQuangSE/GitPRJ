package dao;

import dto.StartUpProjectDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;

public class ProjectDAO implements IDAO<StartUpProjectDTO, Integer> {

    public int autoCreatID() {
        String sql = "SELECT * FROM [dbo].[StartupProjects]";
        int count = 1;
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public boolean create(StartUpProjectDTO object) {
        String sql = "INSERT INTO [dbo].[StartupProjects] ([project_id], project_name, Description, Status, estimated_launch) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, autoCreatID());
            ps.setString(2, object.getProjectName());
            ps.setString(3, object.getDescription());
            ps.setString(4, object.getStatus());
            ps.setDate(5, java.sql.Date.valueOf(object.getEstimated_launch()));
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<StartUpProjectDTO> readAll() {
        return null;
    }

    @Override
    public StartUpProjectDTO readById(Integer id) {
        String sql = "SELECT * FROM [dbo].[StartupProjects] WHERE  [project_id] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               StartUpProjectDTO pj = new StartUpProjectDTO(rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch").toLocalDate());
                return pj;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE [dbo].[StartupProjects] SET [Status] = ? WHERE [project_id] = ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
            int n = ps.executeUpdate();
            return n > 0;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public List<StartUpProjectDTO> searchByName(String searchTerm) {
        String sql = "SELECT * FROM [dbo].[StartupProjects] WHERE project_name LIKE ?";
        List<StartUpProjectDTO> li = new ArrayList<>();
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchTerm + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StartUpProjectDTO pj = new StartUpProjectDTO(rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getDate("estimated_launch").toLocalDate());
                li.add(pj);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return li;
    }

    @Override
    public boolean update(StartUpProjectDTO object) {
        return false;
    }
}
