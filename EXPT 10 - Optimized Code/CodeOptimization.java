import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Elimination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int no_of_instructions;
        System.out.println("Enter number of instructions: ");
        no_of_instructions = sc.nextInt();
        System.out.println("Enter Instructions: ");
        Set < String > set1 = new HashSet < > ();
        for (int i = 0; i < no_of_instructions; i++) {
            String instruction = sc.next();
            String[] result = instruction.split("=");
            if (!set1.contains(result[1])) {
                set1.add(result[1]);
            }
        }
        System.out.println("\nOptimimized Code: ");
        int i = 1;
        for (String str: set1) {
            System.out.println("t" + i + " = " + str + "\n");
            i = i + 1;
        }
    }
}

// OUTPUT

// Enter number of instructions: 
// 8
// Enter Instructions: t1=-c
// t2=a+b
// t3=a+b
// t4=a+b
// t5=a+b
// t6=d+5
// t7=a+b
// t8=-c
// Optimimized Code: t1 = -c

// t2 = a+b

// t3 = d+5