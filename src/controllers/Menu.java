package controllers;

import dtos.I_Menu;
import java.util.Scanner;

/**
 *
 * @author NgocLinh
 */
public class Menu implements I_Menu {

    Scanner sc = new Scanner(System.in);

    @Override
    public int getChoice() {
        int choice = 0;
        do {
            try {
                System.out.println("\nYour choice: ");
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Please input numbers!!!");
            }
        } while (choice < 0);
        return choice;
    }

    @Override
    public void showMenu() {
        System.out.println("\n===== WELCOME TO THE PRODUCT MANAGEMENT =====");
        System.out.println("1. Create a Product.");
        System.out.println("2. Check exist Product.");
        System.out.println("3. Search Products' information by Name.");
        System.out.println("4. Update Product.");
        System.out.println("5. Save Products to file.");
        System.out.println("6. Print list Products from the file.");
        System.out.println("7. Print the last item from the file.");
        System.out.println("Others. Quit.");
    }

}
