package classes;

import Utilities.Status;
import requestclasses.QuizNameAddRequest;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuizNameAdd {
    //    quiz name add
    public String add(QuizNameAddRequest quizNameAddRequest){
        String query="Insert into exam( ExamID,SubjectID,TeacherID, ExamName) values(?,?,?,?);";
        System.out.println(String.valueOf(Status.SUCCESS));
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1, quizNameAddRequest.getExamID());
            preparedStatement.setString(2, quizNameAddRequest.getSubjectID());
            preparedStatement.setString(3, quizNameAddRequest.getTeacherID());
            preparedStatement.setString(4, quizNameAddRequest.getExamName());
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }
}
