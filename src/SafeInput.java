import java.util.Scanner;

public class SafeInput {

    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt for the user
     * @return a String response that is not zero length
     */

    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.print("\n"+prompt+": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;

    }


    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt for the user
     * @param low for lower limit of integer
     * @param high for upper limit of integer
     * @return a String response that is not zero length
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int rangedInt = 0;
        boolean validInput = false;

        do {
            System.out.print(prompt);
            if (pipe.hasNextInt()) {
                rangedInt = pipe.nextInt();
                pipe.nextLine();

                if (rangedInt >= low && rangedInt <= high) {
                    validInput = true;
                } else {
                    System.out.println("You input the value: " + rangedInt);
                    System.out.println("Input out of range. Please try again.");
                }
            } else {
                String trash = pipe.nextLine();
                System.out.println("You have input value of: " + trash);
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        } while (!validInput);

        return rangedInt;
    }



    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt for the user
     * @return a String response that is not zero length
     */
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean validInput = false;
        boolean userConfirmation = false;

        do {
            System.out.print(prompt);
            String userInput = pipe.nextLine().trim().toLowerCase(); // changes uppercase to lowercase to allow for both cases

            if (userInput.equals("y")) {
                validInput = true;
                userConfirmation = true;
            } else if (userInput.equals("n")) {
                validInput = true;
            } else {

                System.out.println("You input a response of: " + userInput);
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        } while (!validInput);

        return userConfirmation;
    }



    /**
     *
     * @param pipe a Scanner opened to read from System.in
     * @param prompt for the user
     * @param regex for the pattern of string the string must follow
     * @return a String response that is not zero length
     */
    public static String getRegExString(Scanner pipe, String prompt, String regex) {
        String userInput = "";
        boolean validInput = false;

        do {
            System.out.print(prompt);
            if (pipe.hasNextLine()) {
                userInput = pipe.nextLine();

                if (userInput.matches(regex)) {
                    validInput = true;
                } else {

                    System.out.println("Input does not match the specified pattern. Please try again by entering in following pattern: " + regex);
                }
            } else {
                String invalidInput = pipe.nextLine();
                System.out.println("Your input was " + invalidInput);
                System.out.println("Invalid input. Please enter a valid string.");
            }
        } while (!validInput);

        return userInput;
    }


    /**
     *
     * @param msg a string that will be centered within the header
     */
    public static void prettyHeader(String msg) {
        int totalWidth = 60;
        int msgWidth = msg.length();
        int padding = (totalWidth - msgWidth - 6) / 2;


        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();


        System.out.print("***");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }

        if (msgWidth % 2 != 0) {
            System.out.print(" ");
        }
        System.out.println("***");


        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}