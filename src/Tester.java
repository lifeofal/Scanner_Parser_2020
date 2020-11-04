import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Simple Test Class for the Program
public class Tester {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("test2.jl");
        Scanner scanner = new Scanner(file);

        //Read in file
        int index = 0;
        int lineNumber = 1;
        ArrayList<String> listOfTokens = new ArrayList<String>();
        ArrayList<Token> TokensClass = new ArrayList<Token>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.length() != 0) {
                //Separate the line with spaces...
                String[] bar = line.split(" ");

                ArrayList<String> lines = new ArrayList<String>();
                for (String s : bar){
                    if(s.length() != 0){

                        lines.add(s);
                    }

                }

                for (String s : lines) {

                    Lexeme l = new Lexeme(s);

                    Token token = new Token(index, l.getTokenType(), l.getLexemeName(), lineNumber, s);
                    TokensClass.add(token);
                    //listOfTokens.add("Token " + tokens + ": " + s + " Line : " + lineNumber);
                    //	tokens++;
                    index++;
                }

                lineNumber++;

            }


        }

        for (Token token : TokensClass) {
            System.out.println(token.toString());
        }
    }
}
