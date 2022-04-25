import java.util.*;
import java.io.*;

public class follow {
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
        for(i=0;i<ntlen;i++){
        System.out.println("Enter First for"+ntermnl[i]+":");
            fst[i]=br.readLine();
        }
        
        flw = new String[ntlen];
        for (i = 0; i < ntlen; i++)
            flw[i] = follow(i);
        System.out.println("\nFollow Set");
        for (i = 0; i < ntlen; i++)
            System.out.println("Follow of " + ntermnl[i] + ": " +
                    removeDuplicates(flw[i]));
    }

    static String follow(int i) {
        char pro[], chr[];
        String temp = "";
        int j, k, l, m, n, found = 0;
        if (i == 0)
            temp = "$";
        for (j = 0; j < ntlen; j++) {
            for (k = 0; k < grmr[j].length; k++) // entering grammar matrix
            {
                pro = new char[grmr[j][k].length()];
                pro = grmr[j][k].toCharArray();
                for (l = 0; l < pro.length; l++) // entering each production
                {
                    if (pro[l] == ntermnl[i]) // finding the nonterminal whose follow set is to be found
                    {
                        if (l == pro.length - 1) // if it is the last terminal/non terminal then follow of current
                                                 // non-terminal
                        {
                            if (j < i)
                                temp = temp + flw[j];
                        } else {
                            for (m = 0; m < ntlen; m++) {
                                if (pro[l + 1] == ntermnl[m]) // first of next non terminal otherwise (else later…)
                                {
                                    chr = new char[fst[m].length()];
                                    chr = fst[m].toCharArray();
                                    for (n = 0; n < chr.length; n++) {
                                        if (chr[n] == '9') // if first includes epsilon
                                        {
                                            if (l + 1 == pro.length - 1)
                                                temp = temp + follow(j); // when nonterminal is second last
                                            else
                                                temp = temp + follow(m);
                                        } else
                                            temp = temp + chr[n]; // include whole first set except epsilon
                                    }
                                    found = 1;
                                }
                            }
                            if (found != 1)
                                temp = temp + pro[l + 1]; // follow set will include terminal(else is here)
                        }
                    }
                }
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
// Enter the non-terminals: 
// PSAB
// Enter the terminals: 
// abcd
// Specify the grammar(Enter 9 for epsilon production)
// Enter the number of productions for P: 
// 1
// Enter the productions: 
// Sd
// Enter the number of productions for S: 
// 2
// Enter the productions: 
// aAS
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
// Enter First forP:
// ac
// Enter First forS:
// ac
// Enter First forA:
// bac
// Enter First forB:
// bac

// Follow Set
// Follow of P: [$]
// Follow of S: [d, b, a, c]
// Follow of A: [a, c]
// Follow of B: [a, c]