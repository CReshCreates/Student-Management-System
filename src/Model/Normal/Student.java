package Model.Normal;

public class Student {
    int s_id;
    String full_name;
    int phone_number;
    String address;
    int u_id;
    int batch_id;

    public Student(int s_id, String full_name, int phone_number, String address, int u_id, int batch_id){
        this.s_id = s_id;
        this.full_name = full_name;
        this.phone_number = phone_number;
        this.address = address;
        this.u_id = u_id;
        this.batch_id = batch_id;
    }

    public int get_s_id(){
        return s_id;
    }

    public String getFullName(){
        return full_name;
    }

    public int getPhoneNumber(){
        return phone_number;
    }

    public String getAddress(){
        return address;
    }

    public int getUId(){
        return u_id;
    }

    public int getBatch_id(){
        return batch_id;
    }
}
