/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

/**
 *
 * @author Bat
 */
public class Vaccine extends Data  {
  
    public Vaccine( String Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    public String getIdVaccine() {
        return Id;
    }

    public void setIdVaccine(String Id) {
        this.Id = Id;
    }

    public String getNameVaccine() {
        return Name;
    }

    public void setNameVaccine(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return  Id + ", " + Name ;
    }
    
}
