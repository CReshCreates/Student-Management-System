package Model.Normal;

public class Subjects {
    String code;
    String subName;
    int sem;

    public Subjects(String code, String subName, int sem){
        this.code = code;
        this.subName = subName;
        this.sem = sem;
    }

    public String getCode(){
        return code;
    }

    public String getSubName(){
        return subName;
    }

    public int getSem(){
        return sem;
    }
}
