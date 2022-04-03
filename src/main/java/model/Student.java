package model;

public class Student {
   private int studentId;
   private String firstName;
   private String lastName;
   private String address;
   private int classId;

    public Student() {
    }

    public Student(String firstName, String lastName, String address, int classId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.classId = classId;
    }

    public Student(int studentId, String firstName, String lastName, String address, int classId) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}


