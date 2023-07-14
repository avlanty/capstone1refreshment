package caponerefreshment;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
                    bW.write(Double.toString(amt));
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

    public void addDepositMenu() {
        try {
            FileWriter fW = new FileWriter("transactions.csv", true);
            BufferedWriter bW = new BufferedWriter(fW);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void viewLedgerMenu() {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}