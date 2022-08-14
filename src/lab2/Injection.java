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
public class Injection{
    private String IdInjection;
    private String IdStudent;
    private String IdVaccine;
    private String date1;
    private String place1;
    private String date2;
    private String place2;
    private boolean mui1,mui2; 

    public Injection(String IdInjection, String IdStudent, String IdVaccine, String date1, String place1, String date2, String place2, boolean mui1, boolean mui2) {
        this.IdInjection = IdInjection;
        this.IdStudent = IdStudent;
        this.IdVaccine = IdVaccine;
        this.date1 = date1;
        this.place1 = place1;
        this.date2 = date2;
        this.place2 = place2;
        this.mui1 = mui1;
        this.mui2 = mui2;
    }
    public static String StringidInj ="I\\d{2}$";
    public static String StringidStu ="SE\\d{6}$|SA\\d{6}$|SS\\d{6}$|"; 
    
    public String getIdInjection() {
        return IdInjection;
    }

    public void setIdInjection(String IdInjection) {
        this.IdInjection = IdInjection;
    }

    public String getIdStudent() {
        return IdStudent;
    }

    public void setIdStudent(String IdStudent) {
        this.IdStudent = IdStudent;
    }

    public String getIdVaccine() {
        return IdVaccine;
    }

    public void setIdVaccine(String IdVaccine) {
        this.IdVaccine = IdVaccine;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public boolean isMui1() {
        return mui1;
    }

    public void setMui1(boolean mui1) {
        this.mui1 = mui1;
    }

    public boolean isMui2() {
        return mui2;
    }

    public void setMui2(boolean mui2) {
        this.mui2 = mui2;
    }
    public void show(){
        System.out.println("*******************");
        System.out.println("Injection id  : " + IdInjection + " - Student id: " + IdStudent + " - Vaccine id: " + IdVaccine);
        System.out.println("The first time : "  + "place: " + place1 + " - Day: " + date1);
        if(date2 == null)
        System.out.println("The second time: "  + "place: null"  + " - Day: " + date2);
        else
        System.out.println("The second time: "  + "place: " +place2 + " - Day: " + date2);
    }
    
    
    
    
   

}
