package com.controller;

import com.model.Coin;
import com.services.CoinService;

import java.time.LocalDate;
import java.util.Scanner;

public class CoinController {
    private CoinService service;
    private Scanner sc = new Scanner(System.in);

    public CoinController(CoinService service) {
        this.service = service;
    }

    public void start() {
        while(true) {
            System.out.println("\n--- Coin Collection Menu ---");
            System.out.println("1. Add Coin");
            System.out.println("2. Show All Coins");
            System.out.println("3. Search by Country");
            System.out.println("4. Search by Year");
            System.out.println("5. Search by Value (Sorted)");
            System.out.println("6. Search by Country + Denomination");
            System.out.println("7. Load from Database");
            System.out.println("8. Delete Coin");
            System.out.println("9. Bulk Upload from CSV");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Country: ");
                    String country = sc.nextLine();
                    System.out.print("Denomination: ");
                    String denom = sc.nextLine();
                    System.out.print("Year of Minting: ");
                    int year = sc.nextInt();
                    System.out.print("Current Value: ");
                    double value = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Acquired Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());

                    Coin coin = new Coin(0, country, denom, year, value, date);
                    service.addCoin(coin);
                    System.out.println("Coin added!");
                    break;

                case 2:
                    service.getAllCoins().forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter country: ");
                    String c = sc.nextLine();
                    service.searchByCountry(c).forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Enter year: ");
                    int y = sc.nextInt();
                    service.searchByYear(y).forEach(System.out::println);
                    break;

                case 5:
                    service.searchByValueSorted().forEach(System.out::println);
                    break;

                case 6:
                    System.out.print("Enter country: ");
                    String cc = sc.nextLine();
                    System.out.print("Enter denomination: ");
                    String dd = sc.nextLine();
                    Coin found = service.searchByCountryAndDenomination(cc, dd);
                    System.out.println(found != null ? found : "Not found");
                    break;

                case 7:
                    service.loadFromDatabase();
                    System.out.println("Loaded coins from database.");
                    break;

                case 8:
                    System.out.print("Enter coin ID to delete: ");
                    int delId = sc.nextInt();
                    service.deleteCoin(delId);
                    System.out.println("Coin deleted if it existed.");
                    break;


                case 9:
                    System.out.print("Enter CSV file path: ");
                    String path = sc.nextLine();
                    service.bulkUpload(path);
                    System.out.println("Bulk upload complete.");
                    break;

                case 10:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
