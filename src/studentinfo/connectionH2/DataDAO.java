package studentinfo.connectionH2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import studentinfo.model.Group;
import studentinfo.model.Student;

public class DataDAO {
    private Connection connection;
    
    public DataDAO() {
        try {
            connection = H2DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void createStudent(Student student) {
        try {
            String query = "INSERT INTO Students (name, group_id, address, city, result, image) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getGroup());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getCity());
            statement.setInt(5, student.getResult());
            statement.setString(6, student.getImage());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateStudent(Student student) {
        try {
            String query = "UPDATE Students SET name=?, group_id=?, address=?, city=?, result=?, image=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getGroup());
            statement.setString(3, student.getAddress());
            statement.setString(4, student.getCity());
            statement.setInt(5, student.getResult());
            statement.setString(6, student.getImage());
            statement.setInt(7, student.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteStudent(int studentId) {
        try {
            String query = "DELETE FROM Students WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void createGroup(Group group) {
        try {
            String query = "INSERT INTO Groups (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateGroup(Group group) {
        try {
            String query = "UPDATE Groups SET name=? WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteGroup(int groupId) {
        try {
            String query = "DELETE FROM Groups WHERE id=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, groupId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
