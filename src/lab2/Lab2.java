/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author Bat
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException, Exception {
        ManagerList in=new ManagerList();
        Scanner sc = new Scanner(System.in);
        String userChoice;
        in.readStudentAndVaccine();
        in.loadfromFile();
        in.readProvincesFile();
        System.out.println("WELCOME TO APP MANAGER INJECTION ");
        do {
            System.out.println("╔════════════════════════════*MENU*══════════════════════════╗");
            System.out.println(" ║ 1. Show information all students                   ║");
            System.out.println(" ║ 2. Add student                                     ║");
            System.out.println(" ║ 3. Updating information of students                ║");
            System.out.println(" ║ 4. Delete student vaccine injection information    ║");
            System.out.println(" ║ 5. Search by studentID                             ║");
            System.out.println(" ║ 6. Quit                                            ║");
            System.out.println("╚═════════════════════════════════════════════════════════════╝");
            System.out.print(" Your choose: ");
            userChoice = sc.nextLine();
            switch (userChoice) {
                case "1" ->    in.showList();
                case "2" ->    in.Add();
                case "3" ->    in.update();
                case "4" ->    in.remove();
                case "5" ->    in.find();
                case "6" ->{   in.Write();
                               System.out.println("Bye ");}
                default ->     System.err.println("Please choose 1..6!");}
        } while (!userChoice.equals("6"));
    }
}



