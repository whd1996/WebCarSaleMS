package test;

import java.util.Arrays;
import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) {
        int i, j;
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        int a[] = {12, 23, 26, 45, 58, 60};
        a = Arrays.copyOf(a, a.length+1);

        for (i = 0; i < 6; i++) {
            if (x < a[i]) {
                for (j = 5; j >= i; j--) {
                    a[j + 1] = a[j];
                }
                a[i] = x;
                break;
            }
        }
        if (i == 6) {
            a[i] = x;
        }
        for (int k = 0; k < 7; k++) {
            System.out.print(a[k] + " ");
        }
    }
}
