package util;

import java.util.Scanner;

public class ReadInteger {
    public int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a valid number:");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
