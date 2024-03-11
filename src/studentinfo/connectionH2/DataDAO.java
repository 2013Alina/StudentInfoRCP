package studentinfo.connectionH2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;

import studentinfo.model.Group;
import studentinfo.model.Student;
import studentinfo.model.StudentDataManager;

public class DataDAO {
    private Connection connection;

    public DataDAO() {
        try {
            connection = H2DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fillWithData() {
        List<Group> groups = StudentDataManager.addGroups();
        for (Group group : groups) {
            createGroup(group);
            for (Student student : group.getStudentslist()) {
                createStudent(student);
            }
        }
    }

    private int getGroupIdByName(String groupName) {
        int groupId = -1;
        try {
            String query = "SELECT id FROM Groups WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, groupName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                groupId = resultSet.getInt("id");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupId;
    }

    public void saveGroup(Group group, TreeViewer treeViewer) {
        try {
            String query = "INSERT INTO Groups (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int groupId = generatedKeys.getInt(1);
                group.setId(groupId); 
            }
            statement.close();

            List<Group> updatedGroups = getAllGroups();
            treeViewer.setInput(updatedGroups);
            treeViewer.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private String getGroupNameById(int groupId) {
        String groupName = null;
        try {
            String query = "SELECT name FROM Groups WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                groupName = resultSet.getString("name");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupName;
    }

    public void saveStudent(Student student) {
        try {
            int groupId = getGroupIdByName(student.getGroup());
            if (groupId != -1) {
                String query = "INSERT INTO Students (name, group_id, address, city, result, image) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, student.getName());
                statement.setInt(2, groupId);
                statement.setString(3, student.getAddress());
                statement.setString(4, student.getCity());
                statement.setInt(5, student.getResult());
                
                try {
                    String encodedImage = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get(student.getImage())));
                    statement.setString(6, encodedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                statement.executeUpdate();
                statement.close();
            } else {
                System.out.println("Group not found for student: " + student.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        try {
            String query = "SELECT * FROM Groups";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Group group = new Group(id, name);
                List<Student> students = getAllStudentsForGroup(id);
                group.setStudentslist(students);
                groups.add(group);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }
    
    private List<Student> getAllStudentsForGroup(int groupId) {
        List<Student> students = new ArrayList<>();
        try {
            String query = "SELECT * FROM Students WHERE group_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String groupName = resultSet.getString("group_id");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                int result = resultSet.getInt("result");
                String imageBase64 = resultSet.getString("image");

                File iconsDir = new File("photoStudents");
                if (!iconsDir.exists()) {
                    iconsDir.mkdir(); // Создать директорию, если она не существует
                    System.out.println("PhotoStudents = " + iconsDir.getAbsolutePath());
                }

                // Декодирую строки Base64 в байтовый массив
                byte[] decodedBytes = Base64.getDecoder().decode(imageBase64);
                String imagePath = "photoStudents/" + name + ".png";
                try (FileOutputStream fos = new FileOutputStream(imagePath)) {
                    fos.write(decodedBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Student student = new Student(id, name, groupName, address, city, result, imagePath);
                students.add(student);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            String query = "SELECT * FROM Students";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String groupName = resultSet.getString("group_id");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                int result = resultSet.getInt("result");
                String imageBase64 = resultSet.getString("image");
                
                File iconsDir = new File("photoStudents");
                if (!iconsDir.exists()) {
                    iconsDir.mkdir(); // Создать директорию, если она не существует
                    System.out.println("PhotoStudents = " + iconsDir.getAbsolutePath());
                }

                // Декодирую строки Base64 в байтовый массив
                byte[] decodedBytes = Base64.getDecoder().decode(imageBase64);
                String imagePath = "/StudentInfoRCP/icons/photoStudents/" + name + ".png";
                try (FileOutputStream fos = new FileOutputStream(imagePath)) {
                    fos.write(decodedBytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                Student student = new Student(id, name, groupName, address, city, result, imagePath);
                students.add(student);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
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
            String imputImagePath = student.getImage();
            statement.setString(6, imputImagePath);
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
            String query = "INSERT INTO Groups (id, name) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, group.getId());
            statement.setString(2, group.getName());
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
    
    public void closeConnectionH2() {
        H2DatabaseConnection.closeConnection(connection);
    }
}
