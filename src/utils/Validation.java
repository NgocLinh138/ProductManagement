package utils;

import dtos.Product;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author NgocLinh
 */
public class Validation extends ArrayList<Product> {

    static Scanner sc = new Scanner(System.in);

    // get name
    public static String getName(String msg, boolean check) {
        String name;
        do {
            System.out.print("\nEnter " + msg + ": ");
            name = sc.nextLine();

            if (name.isEmpty()) {
                System.err.println("Not allow empty!");
            } else {
                check = true;
            }

            if (!name.matches("^\\S{5,}$")) {
                System.err.println("Product name must be at least 5 characters and no spaces!");
            }
        } while (check && (!name.matches("^\\S{5,}$")));
        return name;
    }

    // get ID
    public static String getID(String msg, boolean check) {
        String id;
        do {
            System.out.print("\nEnter " + msg + ": ");
            id = sc.nextLine();

            if (id.isEmpty()) {
                System.err.println("Not allow empty!");
            } else {
                check = true;
            }
        } while (check && id.isEmpty());
        return id;
    }

    // get unit price
    public static double getUnitPrice(String msg, double min, double max) {
        double price = 0;
        boolean check = true;
        do {
            try {
                System.out.print("\nEnter " + msg + ": ");
                price = Double.parseDouble(sc.nextLine());
                check = false;

                if (price < min || price > max) {
                    System.err.println("Please input a real number in range [" + min + " -> " + max + "]");
                }

            } catch (NumberFormatException e) {
                System.err.println("Unit price must be a real number!");
            }
        } while (check || price < min || price > max);
        return price;
    }

    // get unit price (for update)
    public static double getUnitPrice(String msg, double min, double max, double oldData) {
        double n = oldData;
        boolean check = true;
        do {
            try {
                System.out.print("\nEnter " + msg + ": ");
                String temp = sc.nextLine();

                if (temp.isEmpty()) {
                    check = false;
                    n = oldData;
                } else {
                    n = Double.parseDouble(temp);
                    check = false;
                }

                if (n < min || n > max) {
                    System.err.println("Please input a real number in range [" + min + " -> " + max + "]");
                }
            } catch (NumberFormatException e) {
                System.err.println("Unit price must be a real number!");
            }
        } while (check || n < min || n > max);
        return n;
    }

    // get quantity
    public static int getQuantity(String msg, int min, int max) {
        int quantity = 0;
        boolean check = true;
        do {
            try {
                System.out.print("\nEnter " + msg + ": ");
                quantity = Integer.parseInt(sc.nextLine());
                check = false;

                if (quantity < min || quantity > max) {
                    System.err.println("Please input an integer number in range [" + min + " -> " + max + "]");
                }

            } catch (NumberFormatException e) {
                System.err.println("Quantity must be an integer number!");
            }
        } while (quantity < min || quantity > max || check);
        return quantity;
    }

    // get quantity (for update)
    public static int getQuantity(String msg, int min, int max, int oldData) {
        int n = oldData;
        boolean check = true;
        do {
            try {
                System.out.print("\nEnter " + msg + ": ");
                String temp = sc.nextLine();

                if (temp.isEmpty()) {
                    check = false;
                    n = oldData;
                } else {
                    n = Integer.parseInt(temp);
                    check = false;
                }

                if (n < min || n > max) {
                    System.err.println("Please input an integer number in range [" + min + " -> " + max + "]");
                }
            } catch (NumberFormatException e) {
                System.err.println("Quantity must be an integer number!");
            }
        } while (n < min || n > max || check);
        return n;
    }

    // get status
    public static String getStatus(String msg, boolean check) {
        String status;
        do {
            System.out.print("\nEnter " + msg + ": ");
            status = sc.nextLine();

            if (status.isEmpty()) {
                System.err.println("Not allow empty!");
            }

            if (!status.equalsIgnoreCase("available") && !status.equalsIgnoreCase("not available")) {
                System.err.println("Please enter valid status ( Available / Not available )!");
            }
        } while (check && (!status.equalsIgnoreCase("available") && !status.equalsIgnoreCase("not available")));
        return status;
    }

    // input Yes or No
    public static String inputYN(String msg) {
        String text;

        do {
            System.out.println();
            utils.Color.showMessage(msg, Color.PURPLE_UNDERLINED);
            utils.Color.showMessage("1. Yes \n2. No", Color.PURPLE_UNDERLINED);
            text = sc.nextLine();
        } while (!text.equals("1") && !text.equals("2"));

        return text;
    }

    // input Update or Delete
    public static String inputUD() {
        String text;

        do {
            System.out.println("\n\n==== UPDATE CD INFORMATION ====");

            utils.Color.showMessage("Do you want to Update or Delete?", Color.PURPLE_UNDERLINED);
            utils.Color.showMessage("    1. Update" + "\n    2. Delete", Color.PURPLE_UNDERLINED);
            text = sc.nextLine();
        } while (!text.equals("1") && !text.equals("2"));

        return text;
    }

}
