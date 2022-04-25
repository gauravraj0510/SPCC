import java.util.*;

public class ll1 {
    public static void main(String[] args) {
        String[] nonTerm = new String[] {"P", "S", "A", "B"};
        String[] term = new String[] {"a", "b", "c", "d", "$"};

        Map<String, String[]> map = new HashMap<>();
        map.put("P", new String [] {"Sd"});
        map.put("S", new String [] {"aAS", "c"});
        map.put("A", new String [] {"ba", "Sb"});
        map.put("B", new String [] {"bA", "S"});
        
        Map<String, Map<String, String>> firstMap = new HashMap<>();
        firstMap.put("P", new HashMap<>() {{
            put("a", "Sd");
            put("c", "Sd");
        }});
        firstMap.put("S", new HashMap<>() {{
            put("a", "aAS");
            put("c", "c");
        }});
        firstMap.put("A", new HashMap<>() {{
            put("b", "ba");
            put("a", "Sb");
            put("c", "Sb");
        }});
        firstMap.put("B", new HashMap<>() {{
            put("b", "bA");
            put("a", "S");
            put("c", "S");
        }});

        for (int i = 0; i < term.length; i++) {
            System.out.print("\t"+ term[i]);
        }
        System.out.println();
        for (int i = 0; i < nonTerm.length; i++) {
            System.out.print(nonTerm[i]);
            Map<String, String> prods = firstMap.get(nonTerm[i]);
            for (int j = 0; j < term.length; j++) {
                System.out.print("\t" + prods.get(term[j]));
            }
            System.out.println();
        }
    }
}

// OUTPUT
//         a       b       c       d       $
// P       Sd      null    Sd      null    null
// S       aAS     null    c       null    null
// A       Sb      ba      Sb      null    null
// B       S       bA      S       null    null