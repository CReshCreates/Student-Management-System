package Model;

public class UserRegInfo {
    String role;
    String username;
    String fullName;
    String password;
    String phoneNumber;
    String address;
    Integer year;
    String program;
    String section;
    String department;

    public UserRegInfo(String role, String username, String fullName, String password, String phoneNumber, String address, Integer year, String program, String section, String department){
        this.role = role;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this. phoneNumber = phoneNumber;
        this.address = address;
        this.year = year;
        this.program = program;
        this.section = section;
        this.department = department;
    }

    public String getRole(){return role;}

    public String getUserName(){return username;}

    public String getFullName(){return fullName;}

    public String getPassword(){return password;}

    public String getPhoneNumber(){return phoneNumber;}

    public String getAddress(){return address;}

    public int getYear(){return year;}

    public String getProgram(){return program;}

    public String getSection(){ return section;}

    public String getDepartment(){return department;}
}
