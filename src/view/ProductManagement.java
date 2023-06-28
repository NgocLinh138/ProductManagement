package view;

import controllers.Menu;
import controllers.ProductList;
import dtos.Product;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author NgocLinh
 */
public class ProductManagement extends ArrayList<Product> {

    public static void main(String[] args) throws IOException {

        ProductList list = new ProductList();
        Menu menu = new Menu();
        int choice;

        list.loadFromFile();

        do {
            System.out.println("\n\n----------------------------------------------");
            menu.showMenu();
            System.out.println("----------------------------------------------\n");

            choice = menu.getChoice();

            System.out.println();

            switch (choice) {

                case 1:
                    do {
                        list.createProduct();
                    } while (utils.Validation.inputYN("Go back to the main menu?").equals("2"));
                    break;

                case 2:
                    do {
                        list.checkExistProduct();
                    } while (utils.Validation.inputYN("Go back to the main menu?").equals("2"));
                    break;

                case 3:
                    do {
                        list.searchProductByName();
                    } while (utils.Validation.inputYN("Go back to the main menu?").equals("2"));
                    break;

                case 4:
                    do {
                        list.updateProduct();
                    } while (utils.Validation.inputYN("Go back to the main menu?").equals("2"));
                    break;

                case 5:
                    do {
                        list.saveToFile();
                    } while (utils.Validation.inputYN("Go back to the main menu?").equals("2"));
                    break;

                case 6:
                    do {
                        list.printList();
                    } while (utils.Validation.inputYN("Go back to the main menu?").equals("2"));
                    break;

                case 7:
                    do {
                        list.printLastItem();
                    } while (utils.Validation.inputYN("Go back to the main menu?").equals("2"));
                    break;

                default:
                    System.out.println("Good bye!");
                    break;
            }
        } while (choice > 0 && choice < 8);

    }
}
