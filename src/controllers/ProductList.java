package controllers;

import dtos.I_List;
import dtos.Product;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import utils.Color;

/**
 *
 * @author NgocLinh
 */
public class ProductList extends ArrayList<Product> implements I_List {

    Scanner sc = new Scanner(System.in);
    String productID, productName, status;
    double unitPrice;
    int quantity;

    public ProductList() {
    }

    // 1. Create Product
    @Override
    public void createProduct() {
        int pos;

        productName = utils.Validation.getName("product name (at least 5 characters and no spaces)", true);

        do {
            productID = utils.Validation.getID("product ID (can not duplicate) ", true);
            pos = searchID(productID);
            if (pos != -1) {
                System.err.println("Product ID is not allowed to duplicate!");
            }
        } while (pos != -1);

        unitPrice = utils.Validation.getUnitPrice("unit price (in range [0;10000]) ", 0, 10000);
        quantity = utils.Validation.getQuantity("quantity (in range [0;1000]) ", 0, 1000);
        status = utils.Validation.getStatus("status (available / not available) ", true);

        this.add(new Product(productID, productName, unitPrice, quantity, status));

        utils.Color.showMessage("\nAdding successfully!", Color.GREEN);
    }

    // Search ID, if duplicate return i, else return -1
    public int searchID(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    // 2. Check exist Product
    @Override
    public void checkExistProduct() {
        String id;

        id = utils.Validation.getID("Product's ID to check ", true);

        if (searchID(id) < 0) {
            System.err.println("No Product Found!\n");
        } else {
            utils.Color.showMessage("\nExist Product!", Color.GREEN);
        }
    }

    // 3. Search Product by Name
    @Override
    public void searchProductByName() {
        int pos;
        String text;

        System.out.println("==== SEARCH PRODUCT INFORMATION BY NAME ====");

        do {
            System.out.println("\nEnter Product's name you want to search: ");
            text = sc.nextLine();

            pos = searchName(text);

            if (pos < 0 || text.isEmpty()) {
                System.err.println("Have no any Product!");
                System.out.println();
            } else {
                this.sort(Comparator.comparing(Product::getProductName));
                System.out.println("\n    id    |        name        |     unit price     |       quantity       |     status     ".toUpperCase());
                System.out.println("----------|--------------------|--------------------|----------------------|----------------");
                for (Product i : this) {
                    if (i.getProductName().contains(text)) {
                        i.print();
                    }
                }
            }

        } while (text.isEmpty());
    }

    // Search name, if duplicate return i, else return -1
    public int searchName(String name) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductName().contains(name)) {
                return i;
            }
        }
        return -1;
    }

    // 4. Update Product
    @Override
    public void updateProduct() {
        String str = utils.Validation.inputUD();
        int pos;

        switch (str) {
            // 4.1. Update Product information
            case "1":
                productID = utils.Validation.getID("ID to be updated", true);

                pos = searchID(productID);

                if (pos < 0) {
                    System.err.println("Product does not exist! \nFailed!");
                } else {
                    productName = utils.Validation.getName("product name to be updated", false);
                    unitPrice = utils.Validation.getUnitPrice("unit price to be updated", 0, 10000, this.get(pos).getUnitPrice());
                    quantity = utils.Validation.getQuantity("quantity to be updated", 0, 1000, this.get(pos).getQuantity());
                    status = utils.Validation.getStatus("status to be updated", false);

                    this.get(pos).setProductName(productName);
                    this.get(pos).setUnitPrice(unitPrice);
                    this.get(pos).setQuantity(quantity);
                    this.get(pos).setStatus(status);

                    saveToFile();

                    utils.Color.showMessage("\nUpdating successfully!", Color.GREEN);
                }
                break;

            // 4.2. Delete Product
            case "2":
                productID = utils.Validation.getID("ID to be deleted", true);

                pos = searchID(productID);

                if (pos < 0) {
                    System.err.println("Product does not exist! \nFailed!");
                } else {
                    String text = utils.Validation.inputYN("Do you want to delete this Product?");

                    if (text.equals("1")) {
                        this.remove(pos);
                        utils.Color.showMessage("\nDeleting successfully!", Color.GREEN);
                    } else {
                        System.err.println("Failed!");
                    }

                    saveToFile();
                }
                break;
        }
    }

    // 5. Save to file
    @Override
    public void saveToFile() {
        try {
            File f = new File("Product.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Product i : this) {
                String str = "";
                str += i + "\n";
                bw.write(str);
            }

            bw.close();
            fw.close();

        } catch (IOException e) {
            System.err.println(e);
        }

        utils.Color.showMessage("\nSaving successfully!", Color.GREEN);
    }

    // 6. Print list from file
    @Override
    public void printList() {
        ArrayList<Product> tmp = new ArrayList<>();

        try {
            FileReader fr = new FileReader("Product.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] item = line.split(",");
                productID = item[0].trim();
                productName = item[1].trim();
                unitPrice = Double.parseDouble(item[2].trim());
                quantity = Integer.parseInt(item[3].trim());
                status = item[4].trim();

                tmp.add(new Product(productID, productName, unitPrice, quantity, status));
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
            System.err.println(e);
        }

        System.out.println("\nAll Product list from file: ".toUpperCase());
        System.out.println("\n    id    |        name        |     unit price     |       quantity       |     status     ".toUpperCase());
        System.out.println("----------|--------------------|--------------------|----------------------|----------------");

        Collections.sort(tmp, (Product o1, Product o2) -> {
            int result;
            if (o1.getQuantity() == o2.getQuantity()) {
                result = (int) (o1.getUnitPrice() - o2.getUnitPrice());
            } else {
                result = o2.getQuantity() - o1.getQuantity();
            }
            return result;
        });

        tmp.forEach((i) -> {
            i.print();
        });

        System.out.println("----------|--------------------|--------------------|----------------------|----------------");
        utils.Color.showMessage("Total: ".toUpperCase() + this.size() + " Product(s).", Color.GREEN);

    }
    
    // print the last item in the list 
    public void printLastItem() {
        System.out.println(this.get(size() - 1));
    }

    // Load from file
    public void loadFromFile() throws FileNotFoundException, IOException {
        File f = new File("Product.txt");

        if (!f.exists()) {
            System.err.println("File does not exist!");
        }

        try {
            try (FileReader fr = new FileReader("Product.txt")) {
                BufferedReader br = new BufferedReader(fr);
                String line;
                
                while ((line = br.readLine()) != null) {
                    String[] item = line.split(",");
                    productID = item[0].trim();
                    productName = item[1].trim();
                    unitPrice = Double.parseDouble(item[2].trim());
                    quantity = Integer.parseInt(item[3].trim());
                    status = item[4].trim();
                    
                    int pos = searchID(productID);
                    if (pos == -1) {
                        this.add(new Product(productID, productName, unitPrice, quantity, status));
                    }
                }
                
                br.close();
            }
        } catch (FileNotFoundException | NumberFormatException e) {
        }
    }

}
