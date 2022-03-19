package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentFetch {
    private String studentID;
    public StudentFetch(String studentID) {
        this.studentID = studentID;
    }
    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Student fetch() {
        String query = "Select * from students,users where studentID=? && students.UserID=users.UserID;";
        Student student = null;
        try {
            PreparedStatement preparedStatement = Main.connection.prepareStatement(query);
            preparedStatement.setString(1, this.studentID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                student = new Student(resultSet.getString(2), this.studentID);
                student.setFirstName(resultSet.getString(4));
                student.setLastName(resultSet.getString(5));
                student.setEmail(resultSet.getString(6));
                student.setJob(resultSet.getString(7));
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
