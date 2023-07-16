package caponerefreshment;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeScreen {

    Scanner scanner = new Scanner(System.in);
    User user1 = new User("Anthony", 24, true);

    public HomeScreen() {}

    public void welcomeMenu() {
        System.out.println("\nWelcome to the bank " + user1.getName() + "!\n");
        System.out.print("1. Add Deposit\n");
        System.out.print("2. Make Payment\n");
        System.out.print("3. Ledger\n");
        System.out.print("4. Exit\n");
        System.out.print("\nEnter your choice: ");
        String userAns = scanner.next();
        switch (userAns) {
            case "1":
                addDepositMenu();
                break;
            case "2":
                makePaymentMenu();
                break;
            case "3":
                viewLedgerMenu();
                break;
            case "4":
                System.exit(0);
        }
    }

    public void addDepositMenu() {
        try {
            FileWriter fW = new FileWriter("transactions.csv", true);
            BufferedWriter bW = new BufferedWriter(fW);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            int numOfFields = 5;
            for (int i = 0; i < numOfFields; i++) {
                if (i == 0) {
                    String curDate = LocalDate.now().toString();
                    bW.write(curDate);
                } else if (i == 1) {
                    LocalTime curTime = LocalTime.now();
                    String formatTime = curTime.format(formatter);
                    bW.write(formatTime);
                } else if (i == 2) {
                    scanner.nextLine();
                    System.out.print("\nEnter description: ");
                    String desc = scanner.nextLine();
                    bW.write(desc);
                } else if (i == 3) {
                    System.out.print("Enter vendor: ");
                    String vend = scanner.nextLine();
                    bW.write(vend);
                } else {
                    System.out.print("Enter amount: $ ");
                    double amt = scanner.nextDouble();
                    bW.write(Double.toString(amt));
                }
                if (i != numOfFields - 1) {
                    bW.write(" | ");
                }
            }
            bW.write("\n");
            bW.flush();
            System.out.println("\nYour transaction has been saved.");
            System.out.print("\nWould you like to make another deposit? (Y/N): ");
            String ansChoice = scanner.next();
            switch(ansChoice.toUpperCase()){
                case "Y":
                    addDepositMenu();
                    break;
                case "N":
                    System.out.println("\nReturning to the Main Menu.");
                    welcomeMenu();
                    break;
            }
            fW.close();
            bW.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void makePaymentMenu() {
        try {
            FileWriter fW = new FileWriter("transactions.csv", true);
            BufferedWriter bW = new BufferedWriter(fW);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            int numOfFields = 5;
            for (int i = 0; i < numOfFields; i++) {
                if (i == 0) {
                    String curDate = LocalDate.now().toString();
                    bW.write(curDate);
                } else if (i == 1) {
                    LocalTime curTime = LocalTime.now();
                    String formatTime = curTime.format(formatter);
                    bW.write(formatTime);
                } else if (i == 2) {
                    scanner.nextLine();
                    System.out.print("\nEnter description: ");
                    String desc = scanner.nextLine();
                    bW.write(desc);
                } else if (i == 3) {
                    System.out.print("Enter vendor: ");
                    String vend = scanner.nextLine();
                    bW.write(vend);
                } else {
                    System.out.print("Enter amount: $ ");
                    double amt = scanner.nextDouble();
                    double withdrawal = amt * -1;
                    bW.write(Double.toString(withdrawal));
                }
                if (i != numOfFields - 1) {
                    bW.write(" | ");
                }
            }
            bW.write("\n");
            bW.flush();
            System.out.println("\nYour transaction has been saved.");
            System.out.print("\nWould you like to make another payment? (Y/N): ");
            String ansChoice = scanner.next();
            switch(ansChoice.toUpperCase()){
                case "Y":
                    makePaymentMenu();
                    break;
                case "N":
                    System.out.println("\nReturning to the Main Menu.");
                    welcomeMenu();
                    break;
            }
            fW.close();
            bW.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewAllTransactions(){
        try {
            FileReader fW = new FileReader("transactions.csv");
            BufferedReader bW = new BufferedReader(fW);
            String line = null;
            System.out.println();
            while ((line = bW.readLine()) != null) {
                System.out.println(line);
            }
            System.out.print("\nWould you like to return to the Main Menu? (Y/N): ");
            String userAns = scanner.next();
            switch (userAns.toUpperCase()) {
                case "Y":
                    welcomeMenu();
                case "N":
                    System.out.println("Too bad!");
                    welcomeMenu();
            }
            bW.close();
            fW.close();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void viewByMonth(){
        try {
            FileReader fW = new FileReader("transactions.csv");
            BufferedReader bW = new BufferedReader(fW);
            System.out.print("\nEnter a month (MM): ");
            String month = scanner.next();
            String line = null;
            System.out.println("\nTransactions for " + month + ":\n");
            while ((line = bW.readLine()) != null) {
                Pattern pattern = Pattern.compile("(\\d{4}-" + month + "-\\d{2})\\s*\\|\\s*(\\d{2}:\\d{2}:\\d{2})\\s*\\|\\s*([^|]+)\\s*\\|\\s*([^|]+)\\s*\\|\\s*([-]?\\d+\\.?\\d*)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String date = matcher.group(1);
                    String time = matcher.group(2);
                    String description = matcher.group(3);
                    String vendor = matcher.group(4);
                    String amount = matcher.group(5);
                    System.out.println("Date: " + date + " | Time: " + time + " | Description: " + description + " | Vendor: " + vendor + "| Amount: " + amount);
                }
            }
            bW.close();
            fW.close();
            welcomeMenu();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void viewByYear(){
        try {
            FileReader fW = new FileReader("transactions.csv");
            BufferedReader bW = new BufferedReader(fW);
            System.out.print("\nEnter a year (YYYY): ");
            String year = scanner.next();
            String line = null;
            System.out.println("\nTransactions for " + year + ":\n");
            while ((line = bW.readLine()) != null) {
                Pattern pattern = Pattern.compile("(" + year + "-\\d{2}-\\d{2})\\s*\\|\\s*(\\d{2}:\\d{2}:\\d{2})\\s*\\|\\s*([^|]+)\\s*\\|\\s*([^|]+)\\s*\\|\\s*([-]?\\d+\\.?\\d*)");
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String date = matcher.group(1);
                    String time = matcher.group(2);
                    String description = matcher.group(3);
                    String vendor = matcher.group(4);
                    String amount = matcher.group(5);
                    System.out.println("Date: " + date + " | Time: " + time + " | Description: " + description + " | Vendor: " + vendor + "| Amount: " + amount);
                }
            }
            bW.close();
            fW.close();
            welcomeMenu();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void viewLedgerMenu() {
            System.out.println("\n1. View All Transactions");
            System.out.println("2. View By Month");
            System.out.println("3. View By Year");
            System.out.print("\nEnter your choice: ");
            String choice = scanner.next();
            switch(choice) {
                case "1":
                    viewAllTransactions();
                    break;
                case "2":
                    viewByMonth();
                    break;
                case "3":
                    viewByYear();
                    break;
            }
    }

}