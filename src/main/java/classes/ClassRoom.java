package classes;

public class ClassRoom {
    private String classroomID;//UniqueID of the Classroom
    private String className;//Name of the class


    public ClassRoom(String classroomID) {
        this.classroomID = classroomID;
    }

    /**
     * Method returning ID of classroom
     * @return String returing ID of classroom
     */
    public String getClassroomID() {
        return classroomID;
    }

    /**
     * Method for setting the Classroom ID
     * @param classroomID String containing ID of Classroom
     */
    public void setClassroomID(String classroomID) {
        this.classroomID = classroomID;
    }

    /**
     * Method for returning Name of Classroom
     * @return String returning name of Classroom
     */
    public String getClassName() {
        return className;
    }

    /**
     * Method for setting Name of Classroom
     * @param className String containing name of Classroom
     */
    public void setClassName(String className) {
        this.className = className;
    }
}
