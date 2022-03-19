package classes;

import java.io.Serializable;

public class User implements Serializable {
    private String userid;         //Unique ID of a User
    private String fName;      //First Name of a User
    private String lName;        //Last Name of a User
    private String email;            //Email of a user
    private String job;              //Role of the user

    public String getUserID(){
        return userid;
    }
    public void setUserID(String userid)
    {
        this.userid = userid;
    }
    public String getFirstName(){
        return fName;
    }
    public void setFirstName(String fName)
    {
        this.fName = fName;
    }
    public String getLastName(){
        return lName;
    }
    public void setLastName(String lName)
    {
        this.lName = lName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getJob(){
        return job;
    }
    public void setJob(String job)
    {
        this.job = job;
    }
}
