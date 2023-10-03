package bullscows;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        theGame();
    }
    private static void theGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Okay, let's start a game!");
        System.out.println("Please, enter the secret code's length: ");
        int userLength = scanner.nextInt();
        int secretCode = generateSecretCode(userLength);

        while (secretCode == -1) {
            System.out.println("Please enter valid length: ");
            userLength = scanner.nextInt();
            secretCode = generateSecretCode(userLength);
        }
        boolean correctGuess = false;
        int currentTurn = 1;

        while(!correctGuess) {
            System.out.println("===================================================");
            System.out.println("Turn " + currentTurn + ":" );
            correctGuess = grader(String.valueOf(secretCode), userLength);

            if (!correctGuess) {
                System.out.println("Don't worry. Keep trying!");
                currentTurn++;
            }

        }
        System.out.println("Congratulations you guessed the secret code!");

    }
    private static boolean grader(String correctCode, int length) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your " + length + " digit guess: ");
        String userAttempt = scanner.nextLine();

        if (userAttempt.length() != length) {
            System.out.println("Your number has " + length + " digits. Please try again!");
            return false;
        }

        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < length; i++) {
            if (userAttempt.charAt(i) == correctCode.charAt(i)) {
                bulls++;
            } else if (correctCode.contains(String.valueOf(userAttempt.charAt(i)))) {
                cows++;
            }
        }
        if (bulls > 0 && cows > 0) {
            System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s).");
        } else if (bulls > 0) {
            System.out.println("Grade: " + bulls + " bull(s).");

        } else if (cows > 0) {
            System.out.println("Grade: " + cows + " cow(s). ");
        } else {
            System.out.println("Grade: None.");
        }

        return bulls == length;
    }
    private static int generateSecretCode(int numberLength) {

        if (numberLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of " + numberLength + " because there aren't enough unique digits.");
            return -1;
        }

        String pseudoRandomNumber = String.valueOf(System.nanoTime());
        StringBuilder randomNumber = new StringBuilder();

        while(randomNumber.length() != numberLength) {
            randomNumber = new StringBuilder();

            for (int i = pseudoRandomNumber.length() -1; i >= 0 && randomNumber.length() < numberLength; i--) {
                if (!randomNumber.toString().contains(String.valueOf(pseudoRandomNumber.charAt(i)))) {
                    randomNumber.append(pseudoRandomNumber.charAt(i));

                    if (randomNumber.charAt(0) == '0') {
                        randomNumber.deleteCharAt(0);
                    }

                }
            }
        }
        return Integer.parseInt(randomNumber.toString());
    }
}
