import java.util.*;
import java.io.*;

public class leftFactoring {
    public static void main(String args[]) throws IOException {
        File file = new File("leftFactoring.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String s = "";
        while ((s = br.readLine()) != null) {
            char non_terminal = s.charAt(0);
            String productions = s.substring(3);
            String[] rules = productions.split("\\|");

            ArrayList<String> left = new ArrayList<>();
            ArrayList<String> right = new ArrayList<>();
            ArrayList<String> completed = new ArrayList<>();
            ArrayList<String> updated1 = new ArrayList<>();
            ArrayList<String> updated2 = new ArrayList<>();

            for (int i = 0; i < rules.length; i++) {
                String rule = rules[i];
                left.add(Character.toString(rule.charAt(0)));
                if (rule.length() == 1) {
                    right.add("E");
                } else {
                    right.add(rule.substring(1));
                }
            }

            for (int i = 0; i < left.size(); i++) { // a, f, a, k, a, f
                int count = 0;
                String apostrophes = "'";
                for (int j = 0; j < updated1.size(); j++) {
                    apostrophes = apostrophes + "'";
                }

                if (!completed.contains(left.get(i))) { // { a, f }
                    for (int ii = i + 1; ii < left.size(); ii++) {
                        if (left.get(i).equals(left.get(ii))) { // k and f
                            if (!updated1.contains(left.get(i) + non_terminal + apostrophes)) {
                                updated1.add(left.get(i) + non_terminal + apostrophes); // { aA' }
                            }
                            if (count == 0) {
                                // should happen only once
                                updated2.add(right.get(i)); // { }
                                count++;
                            }
                            updated2.add(right.get(ii)); // { }
                        }
                    }
                    if (updated2.size() == 0) {
                        // nothing matched
                        updated1.add(left.get(i) + right.get(i));
                    } else {
                        System.out.print(non_terminal + apostrophes + "->");
                        for (int iii = 0; iii < updated2.size(); iii++) {
                            System.out.print(updated2.get(iii));
                            if (iii + 1 != updated2.size()) {
                                System.out.print("|");
                            }
                        }
                        System.out.println();
                    }
                    updated2.clear();
                }
                completed.add(left.get(i));
            }
            System.out.print(non_terminal + "->");
            for (int i = 0; i < updated1.size(); i++) {
                System.out.print(updated1.get(i));
                if (i + 1 != updated1.size()) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }
}

// A->a|fg|abc|kj|abcd|fh|jh

// A->aA'|fA''|kj|jh
// A'->E|bc|bcd
// A''->g|h
