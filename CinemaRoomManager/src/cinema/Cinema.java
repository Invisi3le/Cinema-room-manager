package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = 0;
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = 0;
        seats = scanner.nextInt();


        String[][] seatsArray = new String[rows + 1][seats + 1];
        seatsArray[0][0] = " ";
        for (int i = 1; i < rows + 1; i++) {

            for (int j = 1; j < seats + 1; j++) {
                seatsArray[0][0] = " ";
                seatsArray[0][j] = String.valueOf(j);
                seatsArray[i][0] = String.valueOf(i);
                seatsArray[i][j] = "S";

            }
        }
        printMenu(seatsArray, rows, seats);
    }


    public static void showSeats(String[][] seatsArray, int rows, int seats) {
        System.out.println("Cinema:");
        for (String[] strings : seatsArray) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }

        printMenu(seatsArray, rows, seats);

    }

    public static void setSeat(String[][] seatsArray, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seat = scanner.nextInt();
        if (row > rows || seat > seats) {
            System.out.println("Wrong input!");
            setSeat(seatsArray, rows, seats);
        } else {
            if (seatsArray[row][seat].equals("B")) {
                System.out.println("That ticket has already been purchased!");
                setSeat(seatsArray, rows, seats);
            } else {
                seatsArray[row][seat] = "B";
                System.out.println("Ticket price: $" + calculateSeatPrice(rows, seats, row, seat));
                printMenu(seatsArray, rows, seats);
            }
        }
    }

    public static int calculateSeatPrice(int rows, int seats, int row, int seat) {
        int price = 0;
        if (rows * seats <= 60) {
            price = 10;
        } else {
            if (row <= rows / 2) {
                price = 10;
            } else {
                price = 8;
            }
        }
        return price;
    }

    public static void statistics(String[][] seatsArray, int rows, int seats) {
        int currentIncome = 0;
        int totalIncome = 0;
        int purchasedTickets = 0;
        int totalSeats = rows * seats;


        totalIncome = calculateTotalIncome(rows, seats);
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < seats + 1; j++) {
                if (seatsArray[i][j].equals("B")) {
                    purchasedTickets++;
                    currentIncome += calculateSeatPrice(rows, seats, i, j);
                }
            }
        }
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f", (double) purchasedTickets / totalSeats * 100);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
        printMenu(seatsArray, rows, seats);
    }

    public static int calculateTotalIncome(int rows, int seats) {
        int totalIncome = 0;
        int totalSeats = rows * seats;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            totalIncome = (rows / 2) * seats * 10 + (rows - rows / 2) * seats * 8;
        }
        return totalIncome;
    }

    public static void printMenu(String[][] seatsArray, int rows, int seats) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.print("0. Exit");

        int choice;
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showSeats(seatsArray, rows, seats);
                break;
            case 2:
                setSeat(seatsArray, rows, seats);
                break;
            case 3:
                statistics(seatsArray, rows, seats);
                break;
            case 0:
                return;

        }
    }


}

