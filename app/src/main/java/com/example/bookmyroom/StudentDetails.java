package com.example.bookmyroom;




public class StudentDetails {

    public String Name;
    public String Regno;
    public   Integer rollno;
    public   String EmailID;
    public   String password;
    public   Long phoneno;

    public StudentDetails(String name, String regno, Integer rollno, String emailID, String password, Long phoneno) {
        Name = name;
        Regno = regno;
        this.rollno = rollno;
        EmailID = emailID;
        this.password = password;
        this.phoneno = phoneno;
    }
}
