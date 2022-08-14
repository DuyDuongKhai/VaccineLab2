/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bat
 */
public class ManagerList {

    ArrayList<Injection> list = new ArrayList();
    ArrayList<Student> studentList = new ArrayList();
    ArrayList<Vaccine> vaccineList = new ArrayList();
    Scanner sc = new Scanner(System.in);

    public ManagerList() {
    }

    public void Add() throws ParseException, Exception {
        String IdInjection = null;
        String date1 = null;
        String place1 = null;
        String date2 = null;
        String place2 = null;
        String IdStudent = null;
        String IdVaccine = null;
        boolean mui1, mui2 = false;
        boolean flag;

        while (true) {
            do {
                flag = false;
                System.out.print("Input the ID Injection: ");
                IdInjection = sc.nextLine().toUpperCase().trim();
                if (IdInjection.isEmpty() || IdInjection.matches("I\\d{2}$") == false) {
                    System.err.println("Input again. ID invalid!");
                    flag = true;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIdInjection().equals(IdInjection)) {
                        System.err.println("Input again. Same Id!");
                        flag = true;
                    }
                }
            } while (flag);
            StudentList stulist = new StudentList();
            do {
                flag=false;
                System.out.print("Input Student ID : ");
                IdStudent = sc.nextLine().trim().toUpperCase();
                if (!stulist.isIDExist(IdStudent)) {
                    flag=true;
                    System.err.println("Student not exist");
                } else if (stulist.search(IdStudent).getNum() >= 1) {
                    System.err.println("This student has injected the maximum number of injection or waiting for second dose ");
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getIdStudent().equals(IdStudent)) {
                        System.err.println("Input again. Same Id!");
                        flag = true;
                    }
                }
            } while(flag);
            VaccineList vaclist = new VaccineList();
            do {
                System.out.print("Input the Vaccine ID: ");
                IdVaccine = sc.nextLine().trim();
                if (!vaclist.isIDExist(IdVaccine)) {
                    System.err.println("Input again. Vaccine not exist!");
                }
            } while (!vaclist.isIDExist(IdVaccine));
           
            do { System.out.print("Input the first place: ");
                flag = false;               
                place1 = sc.nextLine().toUpperCase().trim();
                if (place1.isEmpty()||searchProvice(place1)==true) {
                    System.err.println("Input again. The place invalid!");
                    flag = true;
                }
            } while (flag);
            mui1 = true;
            long todaycheck;
            do {
                try {

                    System.out.print("Input first date (dd/MM/yyyy): ");
                    date1 = sc.nextLine();
                    flag = false;
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    df.setLenient(false);
                    Date day = df.parse(date1);
                    Date today = df.parse("08/03/2021");
                    todaycheck = (today.getTime() - day.getTime()) / (1000 * 60 * 60 * 24);;
                    if (day.before(today)) {
                        System.err.println("This date must be after 08/03/2021!");
                        flag = true;
                    }
                } catch (ParseException e) {
                    System.err.println("Input again. Date invalid!");
                    flag = true;
                }
            } while (flag);
            /*if (todaycheck < 4 * 7) {
                date2 = null;
                Injection in = new Injection(IdInjection, IdStudent, IdVaccine, place1, place2, date1, date2, mui1, mui2);
                list.add(in);
            } else {*/
            do {
                flag = false;
                System.out.print("Input the second place (Enter if don't have second injection): ");
                place2 = sc.nextLine().toUpperCase().trim();
                if(place2.isEmpty()) flag=false;
                else if (searchProvice(place2)==true) {
                    System.err.println("Input again. Place invalid! ");
                    flag = true;
                }
            } while (flag);
            if (!place2.isEmpty()) {
                mui2 = true;
                while (true) {
                    try {
                        System.out.print("Input second date (dd/MM/yyyy): ");
                        date2 = sc.nextLine();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        df.setLenient(false);
                        Date day2 = df.parse(date2);
                        if (Validate.checkDay2(date1, date2) >= 4 * 7 && Validate.checkDay2(date1, date2) <= 12 * 7) {
                            break;
                        } else {
                            System.err.println("The second day must be after 4 to 12 weeks of the first day!!");
                        }
                    } catch (ParseException e) {
                        System.err.println("Input again. Date invalid!");
                        flag = true;
                    }
                }

            } else {
                place2 = null;
                date2 = null;
            }
            Injection in = new Injection(IdInjection, IdStudent, IdVaccine, date1, place1, date2, place2, mui1, mui2);
            list.add(in);
            
            System.out.println("Do you want to insert more? Y/N");
            String c = sc.nextLine().toUpperCase();
            if (c.contains("N")) {
                break;
            }
        }
        sort();
    }
    public boolean searchProvice(String provinceName) {
        ArrayList<String> listProvince = readProvincesFile();
        for (String string : listProvince) {
            if (string.equalsIgnoreCase(provinceName)) {
                return false;
            }
        }
        return true;
    }
   
    public ArrayList<String> readProvincesFile() {
        File file = new File("D:/Ki3SE/LAB211/ProvincesList.txt");
        FileReader fR = null;
        BufferedReader bR = null;
        ArrayList<String> listProvinces = new ArrayList<>();
        try {
            if (file.exists() == false) {
                file.createNewFile();
            } else {
                fR = new FileReader(file);
                bR = new BufferedReader(fR);
                while (bR.ready()) {
                    listProvinces.add((bR.readLine()).trim());
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (fR != null) {
                    fR.close();
                }
                if (bR != null) {
                    bR.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return listProvinces;
    }

    String a=null;
    public void showList() {
        if (list.isEmpty()) {
            System.err.println("Don't have information.");
        } else {
            sort();
            StudentList s=new StudentList();
            VaccineList v=new VaccineList();
            System.out.println(String.format("|%-11s | %-10s| %-20s | %-10s | %-11s | %-15s | %-10s | %-15s | %-11s|",
                    "IdInjection", "IdStudent","NameStudent", "IdVaccine","VaccineName" ,"First Place", "Fisrt Date", "Second Place", "Second date"));
            list.forEach(x -> {    
                System.out.println(String.format("|%-11s | %-10s| %-20s | %-10s | %-11s | %-15s | %-10s | %-15s | %-11s|",
                        x.getIdInjection(), x.getIdStudent(),s.returnName(x.getIdStudent()), x.getIdVaccine(),v.returnName(x.getIdVaccine()), x.getPlace1(), x.getDate1(), x.getPlace2(), x.getDate2()));
            });
        }
    }

    public void update() throws Exception {
        if (list.isEmpty()) {
            System.err.println("Don't have information.");
        } else {
            String stuID;
            System.out.print("Input the Injection Id wanna search: ");
            stuID = sc.nextLine().toUpperCase().trim();
            int flag = 0;
            for (Injection i : list) {
                if (stuID.equalsIgnoreCase(i.getIdInjection())) {
                    flag = 1;
                    if (i.isMui2()) {
                        System.err.println("Student has completed 2 injections!");
                        return;
                    } else {
                        boolean flag2 = true;
                        do {
                            String p2;
                            flag2 = false;
                            System.out.print("Input the second place: ");
                            p2 = sc.nextLine().toUpperCase().trim();
                            if (p2.isEmpty() || p2.equals(" ")||searchProvice(p2)) {
                                System.err.println("Input again. The place can't be empty");
                                flag2 = true;
                            }
                            i.setPlace2(p2);
                        } while (flag2);
                        i.setMui2(true);
                        boolean flagup = false;
                        while (true) {
                            try {
                                System.out.print("Input second date(dd/MM/yyyy): ");
                                String date22 = sc.nextLine().toUpperCase();
                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                df.setLenient(false);
                                Date day2 = df.parse(date22);
                                if (Validate.checkDay2(i.getDate1(), date22) >= 4 * 7 && Validate.checkDay2(i.getDate1(), date22) <= 12 * 7) {
                                    i.setDate2(date22);
                                    break;
                                } else System.err.println("The second day must be after 4 to 12 weeks of the first day!!");                                
                            } catch (ParseException e) {
                                System.err.println("Input again. Date invalid!");
                                flagup = true;
                            }
                        }
                        System.err.println("Student has completed 2 injections!");
                        return;
                    }
                }
            }
            if (flag == 0) System.err.println("Injection does not exist");            
        }
    }

    public void find() {
        if (list.isEmpty()) {
            System.err.println("Don't have information.");
        } else {
            StudentList s=new StudentList();
            VaccineList v=new VaccineList();
            String stuID;
            System.out.print("Input the student Id wanna search: ");
            stuID = sc.nextLine().toUpperCase().trim();
            int flag = 0;
            for (Injection i : list) {
                if (i.getIdStudent().toUpperCase().equals(stuID)) {
                    flag = 1;
                    System.out.println(String.format("|%-11s | %-10s| %-20s | %-10s | %-11s | %-15s | %-10s | %-15s | %-11s|",
                    "IdInjection", "IdStudent","NameStudent", "IdVaccine","VaccineName" ,"First Place", "Fisrt Date", "Second Place", "Second date"));
            }
            }
            for (Injection x : list) {
                if (x.getIdStudent().toUpperCase().equals(stuID)) 
                    System.out.println(String.format("|%-11s | %-10s| %-20s | %-10s | %-11s | %-15s | %-10s | %-15s | %-11s|",
                        x.getIdInjection(), x.getIdStudent(),s.returnName(x.getIdStudent()), x.getIdVaccine(),v.returnName(x.getIdVaccine()), x.getPlace1(), x.getDate1(), x.getPlace2(), x.getDate2()));
            
                
            }
            if (flag == 0) System.err.println("The student ID not existed!!");
        }
    }

    public void Write() throws IOException {
        File file1 = new File("D:/Ki3SE/LAB211/injection.txt");
        FileWriter fw;
        fw = new FileWriter(file1);
        StudentList s=new StudentList();
        VaccineList v=new VaccineList();
        try (PrintWriter pw = new PrintWriter(fw)) {
            if (list.isEmpty()) {
                pw.println("Don't have information.");
            } else {
                list.forEach(i -> {
                    pw.println(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s",
                            i.getIdInjection(), i.getIdStudent(),s.returnName(i.getIdStudent()), i.getIdVaccine(),v.returnName(i.getIdVaccine()), i.getDate1(), i.getPlace1(), i.getDate2(), i.getPlace2()));
                    ;
                });

            }
        }
    }

    public void remove() {
        if (list.isEmpty()) {
            System.err.println("Don't have information.");
        } else {
            StudentList s=new StudentList();
            VaccineList v=new VaccineList();
            int flag = 0;
            String stuID;
            System.out.print("Input the injection Id wanna search: ");
            stuID = sc.nextLine().toUpperCase().trim();
            for (Injection x : list) {
                if (stuID.equals(x.getIdInjection().toUpperCase())) {
                    flag = 1;String b=null;
                    System.out.println("Are you sure about remove injection(Y/N): " + stuID + " ?");
                    System.out.println(String.format("|%-3s | %-10s| %-20s | %-11s | %-10s | %-15s | %-10s | %-15s | %-11s|",
                            x.getIdInjection(), x.getIdStudent(),s.returnName(x.getIdStudent()),v.returnName(x.getIdVaccine()), x.getIdVaccine(), x.getPlace1(), x.getDate1(), x.getPlace2(), x.getDate2()));
                    b=sc.nextLine().toUpperCase().trim();
                    if (b.contains("Y")) {
                        list.remove(x);
                        System.err.println("Remove success!!");
                        return;
                    } else {
                        System.err.println("Remove fail!!");
                    }
                }
            }
            if (flag == 0) {
                System.err.println("Injection does not exist");
            }
        }
    }

    public void loadfromFile() {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            File f = new File("D:/Ki3SE/LAB211/injection.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] s = data.split(";");
                String IdInjection = s[0];
                String IdStudent = s[1];
                String IdVaccine = s[2];
                String Place1 = s[3];
                String Date1 = s[4];
                String Place2 = s[5].equals("null") ? null : s[5];
                String Date2 = s[6];
                boolean mui2 = false, mui1 = false;
                if (!Place1.isEmpty())mui1 = true;
                if (Place2 != null)mui2 = true;
                list.add(new Injection(IdInjection, IdStudent, IdVaccine, Place1, Date1, Place2, Date2, mui1, mui2));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void readStudentAndVaccine() {
        StudentList stulist = new StudentList();
        VaccineList vaclist = new VaccineList();
        stulist.printList();
        System.out.println("-------------------------");
        vaclist.printList();
    }
    public void sort() {
        Comparator<Injection> byId = (o1, o2) -> {
            return (o1.getIdInjection()).compareTo(o2.getIdInjection());
        };
        Collections.sort(list, byId);

    }
}
