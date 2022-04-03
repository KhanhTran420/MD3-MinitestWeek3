package model;

public class Classes {
    private int classesID;
    private String className;
    private String classDescription;

    public Classes(){}

    public Classes(String className, String classDescription) {
        this.className = className;
        this.classDescription = classDescription;
    }

    public Classes(int classesID, String className, String classDescription) {
        this.classesID = classesID;
        this.className = className;
        this.classDescription = classDescription;
    }

    public int getClassesID() {
        return classesID;
    }

    public void setClassesID(int classesID) {
        this.classesID = classesID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }
}
