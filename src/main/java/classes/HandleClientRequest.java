package classes;



import requestclasses.*;
import Utilities.ServerRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.Socket;

public class HandleClientRequest implements Runnable {

    private Socket socket;
    private ObjectInputStream oistream;
    private ObjectOutputStream oostream;

    public HandleClientRequest(Socket socket) {
        this.socket = socket;
        try {
            oistream = new ObjectInputStream(socket.getInputStream());
            oostream = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println(socket.getInetAddress().getHostAddress());
        while (true) {


            Object obj = null;
            try {
                try {
                    obj = oistream.readObject();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert obj != null;
                String request = (String) obj.toString();

                if (request.equals(String.valueOf(ServerRequest.SIGNUP_REQUEST))) {
                    System.out.println("Received signup request from client");
                    SignUpRequest signUpRequest = (SignUpRequest) obj;

                    oostream.writeObject(new SignUp().signup(signUpRequest));
                    oostream.flush();
                }

                if (request.equals(String.valueOf(ServerRequest.STUDENT_LOGIN_REQUEST))) {
                    StudentLoginRequest studentLoginRequest = (StudentLoginRequest) obj;
                    oostream.writeObject(new Login().studentLogin(studentLoginRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.TEACHER_LOGIN_REQUEST))) {
                    TeacherLoginRequest teacherLoginRequest = (TeacherLoginRequest) obj;
                    oostream.writeObject(new Login().teacherLogin(teacherLoginRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.SUBJECT_ADD_REQUEST))) {
                    SubjectAddRequest subjectAddRequest = (SubjectAddRequest) obj;
                    oostream.writeObject(new SubjectAdd().add(subjectAddRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.SUBJECT_LIST_FETCH_REQUEST))) {
                    SubjectListFetchRequest subjectListFetchRequest = (SubjectListFetchRequest) obj;
                    oostream.writeObject(new SubjectListFetch().fetch(subjectListFetchRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.EXAM_ADD_REQUEST))) {
                    QuizNameAddRequest quizNameAddRequest = (QuizNameAddRequest) obj;
                    oostream.writeObject(new QuizNameAdd().add(quizNameAddRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.EXAM_LIST_FETCH_REQUEST))) {
                    QuizListFetchRequest quizListFetchRequest = (QuizListFetchRequest) obj;
                    System.out.println("hi");
                    oostream.writeObject(new QuizListFetch().fetchBySubject(quizListFetchRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.SECTION_ADD_REQUEST))) {
                    SectionsAddRequest sectionsAddRequest = (SectionsAddRequest) obj;
                    oostream.writeObject(new SectionAdd().add(sectionsAddRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.SECTION_FETCH_REQUEST))) {
                    SectionFetchRequest sectionFetchRequest = (SectionFetchRequest) obj;
                    System.out.println("succesSectionfetch");
                    oostream.writeObject(new SectionFetch().sectionsListFetch(sectionFetchRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.QUIZ_ADD_REQUEST))) {
                    QuizAddRequest quizAddRequest = (QuizAddRequest) obj;
                    System.out.println("hiquizadd");
                    oostream.writeObject(new QuizAdd().add(quizAddRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.QUESTION_FETCH_REQUEST))) {
                    QuestionFetchRequest questionFetchRequest = (QuestionFetchRequest) obj;
                    oostream.writeObject(new QuestionFetch().fetch(questionFetchRequest, socket.getInetAddress().getHostAddress()));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.QUESTION_LIST_FETCH_STUDENT_REQUEST))) {
                    QuizListFetchStudent quizListFetchStudentRequest = (QuizListFetchStudent) obj;
                    oostream.writeObject(new QuizListFetch().fetchByStudent(quizListFetchStudentRequest));
                    oostream.flush();
                }


                if (request.equals(String.valueOf(ServerRequest.SCORE_FETCH_REQUEST))) {
                    ScoreFetchRequest scoreFetchRequest = (ScoreFetchRequest) obj;
                    oostream.writeObject(new Score().fetch(scoreFetchRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.SCORE_ADD_REQUEST))) {
                    ScoreAddRequest scoreAddRequest = (ScoreAddRequest) obj;
                    oostream.writeObject(new Score().add(scoreAddRequest));
                    oostream.flush();
                }

                if (request.equals(String.valueOf(ServerRequest.QUIZ_BY_STUDENT_REQUEST))) {
                    QuizByStudent quizByStudentRequest = (QuizByStudent) obj;
                    oostream.writeObject(new QuizListFetch().fetch(quizByStudentRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.QUIZ_LIST_FETCH_TEACHER_REQUEST))) {
                    QuizListFetchTeacher quizListFetchTeacherRequest = (QuizListFetchTeacher) obj;
                    oostream.writeObject(new QuizListFetch().fetch(quizListFetchTeacherRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.COMMENT_ADD_REQUEST))) {
                    CommentAddRequest commentAddRequest = (CommentAddRequest) obj;
                    oostream.writeObject(new CommentFetch().add(commentAddRequest));
                    oostream.flush();
                }
                if (request.equals(String.valueOf(ServerRequest.COMMENT_FETCH_REQUEST))) {
                    CommentFetchRequest commentFetchRequest = (CommentFetchRequest) obj;
                    oostream.writeObject(new CommentFetch().fetch(commentFetchRequest));
                    oostream.flush();
                }

            } catch (StreamCorruptedException e) {
                try {
                    oistream = new ObjectInputStream(socket.getInputStream());
                    oostream = new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



