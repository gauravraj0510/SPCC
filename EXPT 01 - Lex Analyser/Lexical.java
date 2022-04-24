import java.io.*;
import java.util.*;
public class Lexical {
    String text;
    int i;
    public Lexical init(String text) {
        i = 0;
        this.text = text;
        return this;
    }
    //function to check if all chars are read
    private boolean isEOT() {
        return text.length() <= i;
    }
    //function to get char at index i
    private char c() throws Exception {
        if (isEOT())
            throw new Exception("No more characters");
        return text.charAt(i);
    }
    //function to get next char by incr the i
    private char next() throws Exception {
        char c = c();
        ++i;
        return c;
    }

    //function to skip whiteSpaces
    private void skipSpace() throws Exception {
        while (!isEOT() && Character.isWhitespace(c()))
            next();
    }

    //functions to look for start char of word,number,keyword,identifier
    private boolean isOperatorStart(char c) {
        String operators = "+-*/%!~&^=|";
        int idx = operators.indexOf(c);
        if (idx == -1)
            return false;
        return true;
    }
    private boolean isDelimiterStart(char c) throws Exception {
        String delimiters = ";(){}";
        int idx = delimiters.indexOf(c);
        if (idx == -1)
            return false;
        return true;
    }
    private boolean isSeperatorStart(char c) throws Exception {
        String seperator = ",(){}";
        int idx = seperator.indexOf(c);
        if (idx == -1)
            return false;
        return true;
    }
    private boolean isDigitStart(char c) throws Exception {
        return Character.isDigit(c);
    }
    //looking if char starts from alphabet or underscore or dollar
    private boolean isIdentifier_keyWordStart(char c) throws Exception {
        int ascii_underscore = 95;
        int ascii_dollar = 36;
        return (Character.isAlphabetic(c) || (int) c == ascii_underscore ||
            (int) c == ascii_dollar);
    }
    class Token {
        public String kind;
        public String value;
        public Token left;
        public Token right;
        @Override
        public String toString() {
            return kind + " \"" + value + "\"";
        }
        public String paren() {
            if (left == null && right == null) {
                return value;
            } else {
                StringBuilder b = new StringBuilder();
                b.append("(");
                if (left != null) {
                    b.append(left.paren()).append(" ");
                }
                b.append(value);
                if (right != null) {
                    b.append(" ").append(right.paren());
                }
                b.append(")");
                return b.toString();
            }
        }
    }

    //functions to look for tokens
    private Token operator() throws Exception {
        Token t = new Token();
        t.kind = "operator";
        t.value = Character.toString(next());
        return t;
    }
    private Token delimiter() throws Exception {
        Token t = new Token();
        t.kind = "delimiter";
        t.value = Character.toString(next());
        return t;
    }
    private Token seperator() throws Exception {
        Token t = new Token();
        t.kind = "seperator";
        t.value = Character.toString(next());
        return t;
    }
    private Token digit() throws Exception {
        StringBuilder token = new StringBuilder();
        token.append(next());
        //getting whole number as token and appending
        while (!isEOT() && Character.isDigit(c()))
            token.append(next());
        Token t = new Token();
        t.kind = "digit";
        t.value = token.toString();
        return t;
    }
    private Token identifier_keyword() throws Exception {
        List < String >

            keywords = Arrays.asList("int", "float", "char", "double", "long", "abstract", "assert", "boolean", "break", "byte", "case", "catch", "class", "const", "continue", "default", "do", "else", "extends", "false", "final", "finally", "for", "goto", "if", "implements", "import", "instanceof", "interface", "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try", "void", "volatile", "while");
        StringBuilder token = new StringBuilder();
        token.append(next());
        int ascii_underscore = 95;
        int ascii_dollar = 36;
        //append char-by-char the keyword or identifer even if contains _ or $ sign
        while (!isEOT() && (Character.isAlphabetic(c()) || Character.isDigit(c()) || (int) c() == ascii_underscore || (int) c() == ascii_dollar))
            token.append(next());
        Token t = new Token();
        if (keywords.contains(token.toString()))
            t.kind = "keyword";
        else
            t.kind = "identifer";
        t.value = token.toString();
        return t;
    }
    //looking for input string token-by-token
    public Token nextToken() throws Exception {
        skipSpace();
        if (isEOT())
            return null;
        else if (isOperatorStart(c()))
            return operator();
        else if (isDelimiterStart(c()))
            return delimiter();
        else if (isSeperatorStart(c()))
            return seperator();
        else if (isDigitStart(c()))
            return digit();
        else if (isIdentifier_keyWordStart(c()))
            return identifier_keyword();
        else
            throw new Exception("Not a character for tokens");
    }
    //get list all valid tokens
    public List < Token > getAllTokens() throws Exception {
        List < Token > tokens = new ArrayList < > ();
        Token t = nextToken();
        while (t != null) {
            tokens.add(t);
            t = nextToken();
        }
        return tokens;
    }
    //main function
    public static void main(String[] args) throws Exception {
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        System.out.println();
        //reading input
        System.out.print("Enter input: ");
        String text = bi.readLine();
        //list of all tokens with identification
        List < Token > tokens = new Lexical().init(text).getAllTokens();
        List < String > operatorList = new ArrayList < > ();
        List < String > delimiterList = new ArrayList < > ();
        List < String > seperatorList = new ArrayList < > ();
        List < String > keywordList = new ArrayList < > ();
        List < String > identiferList = new ArrayList < > ();
        List < String > digitList = new ArrayList < > ();
        //grouping all tokens of same category
        for (Token token: tokens)
            switch (token.kind) {
                case "operator":
                    operatorList.add(token.value);
                    break;
                case "delimiter":
                    delimiterList.add(token.value);
                    break;
                case "seperator":
                    seperatorList.add(token.value);
                    break;
                case "keyword":
                    keywordList.add(token.value);
                    break;
                case "identifer":
                    identiferList.add(token.value);
                    break;
                case "digit":
                    digitList.add(token.value);
                    break;
            }
        LinkedHashSet < String > hashSet = new LinkedHashSet < String > (operatorList);
        operatorList.clear();
        operatorList.addAll(hashSet);
        LinkedHashSet < String > hashSet1 = new LinkedHashSet < String > (delimiterList);
        delimiterList.clear();
        delimiterList.addAll(hashSet1);
        LinkedHashSet < String > hashSet2 = new LinkedHashSet < String > (identiferList);
        identiferList.clear();
        identiferList.addAll(hashSet2);
        LinkedHashSet < String > hashSet3 = new LinkedHashSet < String > (digitList);
        digitList.clear();
        digitList.addAll(hashSet3);
        LinkedHashSet < String > hashSet4 = new LinkedHashSet < String > (seperatorList);
        seperatorList.clear();
        seperatorList.addAll(hashSet4);
        System.out.println("\n");
        System.out.println("Operators : " + Arrays.toString(operatorList.toArray()) + "\n");
        System.out.println("Delimiters: " + Arrays.toString(delimiterList.toArray()) + "\n");
        System.out.println("Seperator: " + Arrays.toString(seperatorList.toArray()) + "\n");
        System.out.println("Keywords : " + Arrays.toString(keywordList.toArray()) + "\n");
        System.out.println("Identifer : " + Arrays.toString(identiferList.toArray()) + "\n");
        System.out.println("Constants : " + Arrays.toString(digitList.toArray()) + "\n\n");
    }
}