package org.example.task1;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        int number;

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number: ");
        number = sc.nextInt();

        if (isPrizeTicket(number)) {
            System.out.println("Congratulations! You won the prize.");
        } else {
            System.out.println("Unfortunately, you didn't win.");
        }
    }

    private static boolean isPrizeTicket(int numberOfTicket) {
        int leftHalf, rightHalf;

        int length = (int) (Math.log10(numberOfTicket) + 1);
        int temp = 1;

        for (int i = 0; i < length / 2; i++) {
            temp *= 10;
        }

        leftHalf = numberOfTicket / temp;
        rightHalf = numberOfTicket % temp;

        return countDigits(leftHalf) == countDigits(rightHalf);
    }

    private static int countDigits(int number) {
        int summary = 0;

        while (number > 0) {
            summary += number % 10;
            number /= 10;
        }

        return summary;
    }
}
