package util;

import java.io.IOException;

public class ReturnToMenuUsingAnyKey {
    public static void pressAnyKeyToContinue() {
        System.out.println("\nPress any key to return to menu...");
        try {
            System.in.read();   // reads one byte (any key)
            System.in.skip(System.in.available()); // clears buffer
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
