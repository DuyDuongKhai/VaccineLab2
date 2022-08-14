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
public class VaccineList extends ArrayList<Vaccine>{
    public VaccineList(){
        try {
            File f = new File("D:/Ki3SE/LAB211/vaccine.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] s = data.split(";");
            String ID = s[0];
            String name = s[1]; 
            this.add(new Vaccine(ID, name));
            }
        } catch (FileNotFoundException e) {
                System.out.println(e);
        }         
    }
    public void printList(){
        this.forEach((x) -> {
            System.out.println(x.toString());
        });
            
    }
    public boolean isIDExist(String ID){
        return search(ID) != null;
    }
    public boolean isNameinList(String name){
        return searchName(name) != null;
    }    
    public Vaccine search(String ID) {
        for (Vaccine x : this) {
            if (x.getIdVaccine().equals(ID)) {
                return x;
            }
        }
        return null;
    } 
    public String returnName(String ID){
        String name = null;
        for(Vaccine x: this){
           if (x.getIdVaccine().equals(ID))
               name=x.getNameVaccine();
        }
        return name;
    }    
    public Vaccine searchName(String name) {
        for (Vaccine x : this) {
            if (x.getNameVaccine().equals(name)) {
                return x;
            }
        }
        return null;
    }              
}
