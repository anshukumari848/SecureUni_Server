package classes;

import requestclasses.ScoreAddRequest;
import requestclasses.ScoreFetchRequest;
import Utilities.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Score {
    public String add(ScoreAddRequest scoreAddRequest){
        String query="UPDATE studentexam set Score=? where ExamID=?;";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(2,scoreAddRequest.getQuizID());
            preparedStatement.setDouble(1,scoreAddRequest.getScore());
            System.out.println(scoreAddRequest.getScore());
            preparedStatement.executeUpdate();
            return String.valueOf(Status.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.valueOf(Status.FAILED);
    }
    public double fetch(ScoreFetchRequest scoreFetchRequest){
        String query="SELECT Score from studentexam where StudentID=? && ExamID=?;";
        Double d=0.0;
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,scoreFetchRequest.getStudentID());
            preparedStatement.setString(2,scoreFetchRequest.getQuizID());
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                d=resultSet.getDouble(1);
            }
            return d;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }
}
