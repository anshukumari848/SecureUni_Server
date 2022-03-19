package classes;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherFetch {
    private String teacherID;


    public TeacherFetch(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public Teacher fetch() {
        String query = "Select * from teachers,users where teacherID=? && teachers.UserID=users.UserID;";
        Teacher teacher = null;
        try {
            PreparedStatement preparedStatement = Main.connection.prepareStatement(query);
            preparedStatement.setString(1, this.teacherID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teacher = new Teacher( resultSet.getString(2), teacherID);
                teacher.setFirstName(resultSet.getString(4));
                teacher.setLastName(resultSet.getString(5));
                teacher.setEmail(resultSet.getString(6));
                teacher.setJob(resultSet.getString(7));
            }
            return teacher;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
