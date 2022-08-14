/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Bat
 */
public class StudentList extends ArrayList<Student>{
    public StudentList(){
        try {
            File f = new File("D:/Ki3SE/LAB211/student.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] split = data.split(";");
            String ID = split[0];
            String name = split[1];     
            this.add(new Student(ID, name));
            }
        } catch (FileNotFoundException e) {
                System.out.println(e);
        }         
    }
    public boolean isIDExist(String ID){
        return search(ID) != null;
    }
    public Student search(String ID) {
        for (Student x : this) {
            if (x.getId().equals(ID)) {
                return x;
            }
        }
        return null;
    } 
    public String returnName(String ID){
        String name = null;
        for(Student x: this){
           if (x.getId().equals(ID))
               name=x.getName();
        }
        return name;
    }
    public void printList(){
        this.forEach((x) -> {
            System.out.println(x.toString());
        });
                   
    }
}
