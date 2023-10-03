package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int codeLength = scanner.nextInt();
        if(codeLength > 10){
            System.out.println("Error: can't generate a secret number with a length of " +
                    "11 because there aren't enough unique digits.");
        }else {
            StringBuilder stringBuilder = new StringBuilder();
            while (stringBuilder.length() < codeLength){
                long pseudoRandomNumber = System.nanoTime();
                for (long i = pseudoRandomNumber; i >= 0; i--) {
                    if(stringBuilder.length() == codeLength){
                        break;
                    } else if(stringBuilder.length() == 0 && i % 10 == 0){
                        continue;
                    }
                    stringBuilder.append(i % 10);
                }
            }
            System.out.println("The random secret number is: " + stringBuilder.toString());
        }
    }
}
