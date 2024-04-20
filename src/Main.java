import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {


    static ArrayList<String> menuArrayList = new ArrayList<>();

    static Scanner in = new Scanner(System.in);

    static boolean saved = true;

    static String fileName = "";
    public static void main(String[] args) {


        boolean exitProgram = false;

        while(!exitProgram) {
            displayMenu();

            String choice = SafeInput.getRegExString(in, "Please enter your menu selection (A, C, D, O, S, V, Q): ","[AaDdVvQqOoSsCc]");

            switch (choice.toUpperCase()) {
                case "A":
                    addItem();
                    saved = false;
                    break;
                case "D":
                    deleteItem();
                    saved = false;
                    break;
                case "V":
                    viewList();
                    break;
                case "Q":
                    exitProgram = confirmExit();
                    break;
                case "O":
                    openListFile();
                    break;
                case "S":
                    saveListFile(menuArrayList, fileName);
                    saved = true;
                    break;
                case "C":
                    removeAllElements(menuArrayList);
                    saved = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {

        System.out.println("Menu:");
        System.out.println("A - Add an item to the array/list");
        System.out.println("C - Clear all elements from current list/array");
        System.out.println("D - Delete an item from the array/list");
        System.out.println("O - Open list/array from disk");
        System.out.println("S - Save list/array to disk");
        System.out.println("V - View the array/list");
        System.out.println("Q - Quit/Exit the program");

    }


    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter an item to add");
        menuArrayList.add(item);
        System.out.println("Item added: " + item);
    }


    private static void deleteItem() {
        if (menuArrayList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        System.out.println("Current list:");
        displayNumberedItems();
        int index = SafeInput.getRangedInt(in,"Enter the number of the item to delete: ", 1, menuArrayList.size());
        String item = menuArrayList.remove(index - 1);
        System.out.println("Item deleted: " + item);
    }



    private static void viewList() {
        System.out.println("Current list/array:");
        displayNumberedItems();
    }


    private static boolean confirmExit() {

        return SafeInput.getYNConfirm(in,"Are you sure you want to quit? Any unsaved lists will NOT save. Please save the data before proceeding if needed. (Y/N): ");
    }


    private static void displayNumberedItems() {

        if (menuArrayList.isEmpty()) {
            System.out.println("The list/array is empty.");
        } else {
            for (int i = 0; i < menuArrayList.size(); i++) {
                System.out.println((i + 1) + ". " + menuArrayList.get(i));
            }
        }
    }


    private static void openListFile() {
        if (saved) {
            String prompt = "All data was saved. Would you like to open a new list? (Y/N): ";
            boolean deleteListYN = SafeInput.getYNConfirm(in, prompt);
            if (!deleteListYN) {
                return;
            }
        } else {
            String prompt = "Your currently open list will not save. Please save the data before proceeding. Would you like to open a new list? (Y/N): "; // asks the user if they want to open new list
            boolean deleteListYN = SafeInput.getYNConfirm(in, prompt);
            if (!deleteListYN) {
                return;
            }
            removeAllElements(menuArrayList);
        }


        Scanner inFile;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        chooser.setFileFilter(filter);
        String line;


        Path target = Paths.get(System.getProperty("user.dir")).resolve("src");
        chooser.setCurrentDirectory(target.toFile());


        try {

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                inFile = new Scanner(target);
                System.out.println("Opened " + target.getFileName());
                fileName = String.valueOf(target.getFileName());


                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    menuArrayList.add(line);
                }
                inFile.close();
            } else {
                System.out.println("Please select a new file");
            }
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
    }


    public static void saveListFile(ArrayList<String> arrayList, String fileName)
    {

        PrintWriter outFile;
        Path target = Paths.get(System.getProperty("user.dir")).resolve("src");
        if (fileName.equals(""))
        {
            target = target.resolve("mop.txt");
        }else
        {
            target = target.resolve(fileName);
        }

        try
        {

            outFile = new PrintWriter(target.toString());
            for (String s : arrayList) {
                outFile.println(s);
            }
            outFile.close();
            System.out.printf("File \"%s\" saved!\n", target.getFileName());
        } catch (IOException e) {
            System.out.println("IOException Error");
        }
    }


    private static void removeAllElements(ArrayList<String> arrayList) {
        arrayList.clear();
        System.out.println("All elements erased from current list/array.");
}
}
