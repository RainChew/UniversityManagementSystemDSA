/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.util.Scanner;

/**
 *
 * @author Chew Lip Sin
 */
public class ReadValue {

    Scanner sc = new Scanner(System.in);

    public int readInteger() {
        int number = 0;
        boolean isInteger = false;
        do {
            isInteger = false;
            try {
                number = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                MessageUI.printFormattedText("Invalid format, please enter in integer: ", ConsoleColor.YELLOW);
                sc.next();
                isInteger = true;
            }

        } while (isInteger);
        return number;
    }

    public String readString() {
        String input = sc.nextLine();
        return input;
    }

    public String readCourseCode() {
        String input;
        boolean match = false;
        do {
            input = sc.nextLine();

            match = input.matches("^[ABFMabfm][A-Za-z]{3}\\d{4}$|^[Mm][Pp][Uu][-]\\d{4}$");
            if ("0".equals(input)) {
                match = true;
                return "0";
            }
            if (!match) {
                MessageUI.displayInvalidFormat();
            }
        } while (!match);
        return input;
    }

    public String readCharInt() {
        String input;
        boolean match = false;
        do {
            input = sc.nextLine();
            if (input == "0") {
                return input;
            }
            match = input.matches("^[a-zA-Z0-9\\s]$");
            if (!match) {
                MessageUI.displayInvalidFormat();
            }
        } while (!match);
        return input;
    }
}
