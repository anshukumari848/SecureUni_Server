package classes;

import requestclasses.QuizByStudent;
import requestclasses.QuizListFetchRequest;
import requestclasses.QuizListFetchStudent;
import requestclasses.QuizListFetchTeacher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizListFetch {
    public List<Quiz> fetchByStudent(QuizListFetchStudent quizListFetchStudent) {
        String query = "SELECT * FROM exam LEFT JOIN (SELECT * from studentexam where studentexam.StudentID=?) temp ON temp.examID=exam.ExamID WHERE temp.StudentId IS NULL && SubjectID=?;";
        List<Quiz> quizList = new ArrayList<>();
        try {
            Quiz quiz;
            PreparedStatement preparedStatement = Main.connection.prepareStatement(query);
            preparedStatement.setString(2, quizListFetchStudent.getSubjectID());
            preparedStatement.setString(1, quizListFetchStudent.getStudentID());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                quiz = new Quiz();
                quiz.setExamName(resultSet.getString(4));
                quiz.setExamID(resultSet.getString(1));
                quiz.setTeacherID(resultSet.getString(3));
                quiz.setSubject(new SubjectFetch().fetch(resultSet.getString(2)));
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizList;
    }
    private boolean fetch(QuizListFetchRequest quizListFetchRequest, String query, List<Quiz> quizList) {
//        try {
//            PreparedStatement preparedStatement= Main.connection.prepareStatement(query);
//            preparedStatement.setString(2,"%"+ quizListFetchRequest.getTeacherID()+"%");
//            preparedStatement.setString(1,"%"+ quizListFetchRequest.getSubjectID()+"%");
//            ResultSet resultSet=preparedStatement.executeQuery();
//            while (resultSet.next()){
//                Quiz quiz =new Quiz();
//                quiz.setExamID(resultSet.getString(1));
//                quiz.setSubject(new SubjectFetch().fetch(resultSet.getString(2)));
//                quiz.setTeacherID(resultSet.getString(3));
//                quiz.setExamName(resultSet.getString(4));
//                quizList.add(quiz);
//            }
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
      return false;
    }

    public List<Quiz> fetchBySubject(QuizListFetchRequest quizListFetchRequest){
        System.out.println("hi");
        String query="Select * from exam where SubjectID Like ? && TeacherID Like ? && Access=1;";
        List<Quiz> quizList =new ArrayList<>();
        try {
            PreparedStatement preparedStatement= Main.connection.prepareStatement(query);
            preparedStatement.setString(2,"%"+ quizListFetchRequest.getTeacherID()+"%");
            preparedStatement.setString(1,"%"+ quizListFetchRequest.getSubjectID()+"%");
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Quiz quiz =new Quiz();
                quiz.setExamID(resultSet.getString(1));
                quiz.setSubject(new SubjectFetch().fetch(resultSet.getString(2)));
                quiz.setTeacherID(resultSet.getString(3));
                quiz.setExamName(resultSet.getString(4));
                quizList.add(quiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizList;
    }

    public List<Quiz> fetch(QuizListFetchTeacher quizScoreFetchTeacherRequest){
        List<Quiz> quizList=new ArrayList<>();
        String query="Select ExamID from exam where TeacherID=?;";
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,quizScoreFetchTeacherRequest.getTeacherID());
            ResultSet resultSet=preparedStatement.executeQuery();
            Quiz quiz;
            while (resultSet.next()){
                quiz=new QuizFetch().fetch(resultSet.getString(1));
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizList;
    }
    public List<Quiz> fetch(QuizByStudent quizByStudentRequest){
        String query="SELECT ExamID FROM studentexam WHERE StudentID=?;";
        List<Quiz> quizList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=Main.connection.prepareStatement(query);
            preparedStatement.setString(1,quizByStudentRequest.getStudentID());
            ResultSet resultSet=preparedStatement.executeQuery();
            Quiz quiz;
            while (resultSet.next()){
                quiz=new Quiz(new QuizFetch().fetch(resultSet.getString(1)));
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizList;
    }
}
