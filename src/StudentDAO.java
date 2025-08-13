import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final Connection con;

    public StudentDAO() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/studentrecords?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "10052003";
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, username, password);
    }

    public void addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO students (name, age, grade) VALUES (?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, s.getName());
        stmt.setInt(2, s.getAge());
        stmt.setString(3, s.getGrade());
        stmt.executeUpdate();
        System.out.println("Student added successfully ");
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM students");
        while (rs.next()) {
            list.add(new Student(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("age"), rs.getString("grade")));
        }
        return list;
    }

    public List getStudentsById(int id) throws SQLException {
        List list = new ArrayList();
        String q="select * from students where id=?";
        PreparedStatement pstmt = con.prepareStatement(q);
        pstmt.setInt(1,id);     //why 1 because first ?=1,second ?=2....
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {

            list.add(rs.getInt("id"));
            list.add(rs.getString("name"));
            list.add(rs.getInt("age"));
            list.add(rs.getString("grade"));
        }
        return list;
    }


    public void deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, id);
        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Student deleted ");
        } else {
            System.out.println("Student not found ");
        }
    }

    public void updateStudent(int id, String newName, int newAge, String newGrade) throws SQLException {
        String sql = "UPDATE students SET name=?, age=?, grade=? WHERE id=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, newName);
        stmt.setInt(2, newAge);
        stmt.setString(3, newGrade);
        stmt.setInt(4, id);
        int rows = stmt.executeUpdate();
        if (rows > 0) {
            System.out.println("Student updated ");
        } else {
            System.out.println("Student not found ");
        }
    }
}
