package service;

import java.util.ArrayList;
import model.Student;

public class StudentService{
    private ArrayList<Student> students=new ArrayList<>();

    public void addStudent(Student student){
        if(student==null){
            throw new IllegalArgumentException("Invalid student");
        }
        students.add(student);
    }

    public int getCount(){
        return students.size();
    }

    public void performAction(int action){
        if(action<1||action>2){
            throw new IllegalArgumentException("Invalid action");
        }
    }
}