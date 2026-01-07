package util;

public class passwordValidator {

    public static void validate(String password) {

        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password cannot be empty");

        if (password.length() < 8)
            throw new IllegalArgumentException("Password must be at least 8 characters");

        if (!password.matches(".*[A-Z].*"))
            throw new IllegalArgumentException("Password must contain an uppercase letter");

        if (!password.matches(".*[a-z].*"))
            throw new IllegalArgumentException("Password must contain a lowercase letter");

        if (!password.matches(".*\\d.*"))
            throw new IllegalArgumentException("Password must contain a digit");
    }
}
