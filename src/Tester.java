import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Simple Test Class for the Program
public class Tester {

    public static void main(String[] args) throws FileNotFoundException {
        ProjScanner scan = new ProjScanner("test2.jl");
        //scan.printTokenList();
    	
        ProjParser parser = new ProjParser();
        
        
        String test = "value := evaluate(expr.expr1) * evaluate (expr.expr2);";
        
        char[] listOfLexemeCharacters = {'(',')','{','}', ';', ':', '.'};
        int start = 0;
        int charLocation = 0;
        ArrayList<String> data = new ArrayList<String>();
        
        for (int i = 0; i < test.length();i++) {
        	for (char c : listOfLexemeCharacters) {
        		if (c == test.charAt(i)) {
        			charLocation = i;
        			data.add("Substring Process : " + test.substring(start, charLocation));
        			data.add("CharAt Process : " + Character.toString(test.charAt(i)));
        			start = i;
        			break;
        		}
        	}
        }
        
        for(String s : data) {
        	System.out.println(s);
        }
    }
    
}
