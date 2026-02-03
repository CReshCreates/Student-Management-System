package Model.Normal;

public class Teacher {
    int teacherId;
    String fullName;

    public Teacher(int teacherId, String fullName){
        this.teacherId = teacherId;
        this.fullName = fullName;
    }

    public String getName(){
        return fullName;
    }

    public int getTeacherId(){
        return teacherId;
    }

    public String toString(){
        return " Teacher ID: " + teacherId + ", Teacher Name: " + fullName;
    }
}
