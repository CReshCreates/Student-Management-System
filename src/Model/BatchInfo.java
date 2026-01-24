package Model;

public class BatchInfo {
    int batchId;
    int year;
    String program;

    public BatchInfo(int batchId, int year, String program){
        this.batchId = batchId;
        this.year = year;
        this.program = program;
    }

    public int getBatchId(){
        return batchId;
    }

    public int getYear(){
        return year;
    }

    public String getProgram(){
        return program;
    }
}
