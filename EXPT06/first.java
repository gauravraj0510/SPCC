import java.util.*;
import java.io.*;

public class first {
    static char ntermnl[], termnl[];
    static int ntlen, tlen;
    static String grmr[][], fst[], flw[];

    public static void main(String args[]) throws IOException {
        String nt, t;
        int i, j, n;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the non-terminals: ");
        nt = br.readLine();
        ntlen = nt.length();
        ntermnl = new char[ntlen];
        ntermnl = nt.toCharArray();
        System.out.println("Enter the terminals: ");
        t = br.readLine();
        tlen = t.length();
        termnl = new char[tlen];
        termnl = t.toCharArray();
        System.out.println("Specify the grammar(Enter 9 for epsilon production)");
        grmr = new String[ntlen][];
        for (i = 0; i < ntlen; i++) {
            System.out.println("Enter the number of productions for " + ntermnl[i] + ": ");
            n = Integer.parseInt(br.readLine());
            grmr[i] = new String[n];
            System.out.println("Enter the productions: ");
            for (j = 0; j < n; j++)
                grmr[i][j] = br.readLine();
        }
        fst = new String[ntlen];
        for (i = 0; i < ntlen; i++)
            fst[i] = first(i);
        System.out.println("\nFirst Set");
        for (i = 0; i < ntlen; i++)
            System.out.println("First of " + ntermnl[i] + ": " + removeDuplicates(fst[i]));
        
    }

    static String first(int i) {
        int j, k, l = 0, found = 0;
        String temp = "", str = "";
        for (j = 0; j < grmr[i].length; j++) // number of productions
        {
            for (k = 0; k < grmr[i][j].length(); k++, found = 0) // when nonterminal has epsilon production
            {
                for (l = 0; l < ntlen; l++) // finding nonterminal
                {
                    if (grmr[i][j].charAt(k) == ntermnl[l]) // for nonterminal in first set
                    {
                        str = first(l);
                        if (!(str.length() == 1 && str.charAt(0) == '9')) // when epsilon production is the only
                                                                          // nonterminal production
                            temp = temp + str;
                        found = 1;
                        break;
                    }
                }
                if (found == 1) {
                    if (str.contains("9")) // here epsilon will lead to next nonterminal’s first set
                        continue;
                } else // if first set includes terminal
                    temp = temp + grmr[i][j].charAt(k);
                break;
            }
        }
        return temp;
    }
    static String removeDuplicates(String str) {
        int i;
        char ch;
        boolean seen[] = new boolean[256];
        ArrayList<Character> arr = new ArrayList<>();
        for (i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (!seen[ch]) {
                seen[ch] = true;
                arr.add(ch);
            }
        }
        return arr.toString();
    }
}

// OUTPUT
// Enter the non-terminals: PSAB
// Enter the terminals: 
// abcd
// Specify the grammar(Enter 9 for epsilon production)
// Enter the number of productions for P: 
// 1
// Enter the productions: Sd
// Enter the number of productions for S: 
// 2
// Enter the productions: 
// aAs
// c
// Enter the number of productions for A: 
// 2
// Enter the productions: 
// ba
// SB
// Enter the number of productions for B: 
// 2
// Enter the productions: 
// bA
// S
// First SetFirst of P: [a, c]
// First of S: [a, c]
// First of A: [b, a, c]F
// irst of B: [b, a, c]