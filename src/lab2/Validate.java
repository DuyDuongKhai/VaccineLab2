/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Bat
 */
public class Validate {
   
    public static int Inputnum() throws Exception {
        int a = -1;
        Scanner p = new Scanner(System.in);
        int f = 0;
        do {
            try {
                a = p.nextInt();
                if(a<=0)
                    throw new Exception("Invalid!!");
                f = 1;
            } 
            catch (Exception e) {
                System.out.println("Invalid! Please enter again");
                p.nextLine();
            }

        } while (f != 1);
        return a;
    }

    public static String inputString() throws Exception {
        String s = "";
        int f = 0;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                s = sc.nextLine();
                if (s.equals("")) {
                    throw new Exception("Information must not empty");
                }
                f = 1;

            } catch (Exception e) {
                System.out.println("Invalid! Please enter again.");
                sc.nextLine();

            }
        } while (f != 1);
        return s;
    }
    
    public static String Input() throws Exception {
        String b = "";
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false); 
        try {
            b = sc.nextLine();
            df.parse(b); 
        } catch (ParseException e) {
            System.out.println("Invalid! Please enter again. ");
            b = sc.nextLine();
        }
        return b;
    }
    public static String getString1(String abc, String xyz) {
        String a;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(abc);
                a = sc.nextLine();
                if (!a.isEmpty()) {
                    break;
                } else {
                    System.err.println(xyz);
                }
            } catch (Exception e) {
                System.err.println("Input again!!!");
            }
        } while (true);
        return a;
    }
    public static String getString2(String abc) {
        String a;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print(abc);
                a = sc.nextLine();
                break;
            } catch (Exception e) {
                System.err.println("Input again!!!");
            }
        } while (true);
        return a;
    }
    public static Date getDate(String getDate) {
         Scanner sc = new Scanner(System.in);
        Date tmpDate = null;
        int flag = 0;
        while (flag == 0) {
            try {
                while (true) {
                    System.out.print(getDate);
                    Date today = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    formatter.setLenient(false);
                    tmpDate = formatter.parse(sc.nextLine());
                    if (today.compareTo(tmpDate) > -1) {
                        flag = 1;
                        return tmpDate;
                    } else {
                        System.err.println("This date must be before today!!!");
                    }
                }
            } catch (ParseException e) {
                System.err.println("Please input the date allow the format(dd/mm/yyyy)");
            }
        }
        return tmpDate;
    }
    public static long checkDay2(String date1, String date2) {
        long days = 0;
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date day = df.parse(date1);
            Date day2 = df.parse(date2);
            long getTime = day2.getTime() - day.getTime();
            days = getTime / (1000 * 60 * 60 * 24);

        } catch (ParseException e) {
            System.out.println(e);
        }
        return days;
    }
     public static boolean yesOrNo(String abc) {
         Scanner sc=new Scanner(System.in);
        String choice;
        System.out.println(abc);
        while (true) {
            choice = sc.nextLine().toUpperCase();
            if (choice.equals("Y")) {
                return true;
            }
            if (choice.equals("N")) {
                return false;
            }
            System.err.println("Please input yes or no!!!");
        }
    }
}
