import java.util.*;
import java.io.*;
public class TAC {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String for TAC:\n");
        String input = sc.nextLine();
        String[] input_split = input.split("=");
        char[] input_char = input_split[1].toCharArray();
        char[][] t = new char[3][3];
        int count = 0;
        String t_val = new String();
        for (int i = 0; i < input_char.length; i++) {
            if (input_char[i] == '/' || input_char[i] == '*' || input_char[i] == '%') {
                t[count][0] = input_char[i - 1];
                t[count][1] = input_char[i];
                t[count][2] = input_char[i + 1];
                count++;
            }
            if (input_char[i] == '+' || input_char[i] == '-') {
                t[2][0] = '1';
                t[2][1] = input_char[i];
                t[2][2] = '2';
            }
        }
        System.out.println("\nThree Address Code for the above grammer is:");
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                t_val = "t1";
            } else if (i == 1) {
                t_val = "t2";
            } else {
                t_val = "t3";
            }
            System.out.print("\n" + t_val + "=");
            for (int j = 0; j < 3; j++) {
                if (i == 2) {
                    if (j == 0 || j == 2) {
                        System.out.print("t" + t[i][j]);
                    } else {
                        System.out.print(t[i][j]);
                    }
                } else {
                    System.out.print(t[i][j]);
                }
            }
        }
        System.out.println("\n");
        System.out.println("\n" + input_split[0] + "=" + t_val);
    }
}

// OUTPUT
// Enter the String for TAC:
// a=b*c+d*e

// Three Address Code for the above grammer is:
// t1=b*c
// t2=d*e
// t3=t1+t2
// a=t3