import java.util.*;
import java.io.*;

public class leftRecursion {
    public static void main (String args[]) throws IOException {
        File file = new File("leftRecursion.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String s = "";
        while ((s = br.readLine()) != null) {
            char non_terminal = s.charAt(0);
            String production = s.substring(3);
            String[] rules = production.split("\\|");
            ArrayList<String> beta = new ArrayList<>();
            ArrayList<String> alpha = new ArrayList<>();

            for (int i = 0; i < rules.length; i++) {
                String rule = rules[i];
                char start = rule.charAt(0);
                if (start == non_terminal) {
                    alpha.add(rule.substring(1));
                } else {
                    beta.add(rule + non_terminal + "'");
                }
            }
            
            if (alpha.size() > 0) {
                // Generating the rules
                System.out.print(non_terminal + "->");
                for (int i = 0; i < beta.size(); i++) {
                    System.out.print(beta.get(i));
                    if (i + 1 != beta.size()) {
                        System.out.print("|");
                    }
                }
                System.out.println("");

                System.out.print(non_terminal + "'->");
                for (int i = 0; i < alpha.size(); i++) {
                    System.out.print(alpha.get(i) + non_terminal + "'|");
                }
                System.out.print("E");
                System.out.println("\n");
            } else {
                System.out.println(s);
            }
        }
    }
}

// A->ABd|Aa|a
// B->Be|b

// S->(L)|a
// L->L,S|S

// S->S0S1S|01
