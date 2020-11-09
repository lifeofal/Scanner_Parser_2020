import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjScanner {
	private ArrayList<Token> listOfTokens;
	private char[] listOfLexemeCharacters = {'(',')','{','}', ';', ':', '.'};
	private int index;
	private int lineNumber;
    
	public ProjScanner (String filename)throws FileNotFoundException  {
		initializeLocalVariables();
		loadInADAFile(filename);
	}
	
	public void initializeLocalVariables() {
		listOfTokens = new ArrayList<Token>();
		index = 0;
		lineNumber = 1;
	}
	
	public void loadInADAFile(String filename) throws FileNotFoundException {
		File file = new File("test2.jl");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (line.length() != 0) {
                String[] currentLineSplit = line.split(" ");

                for (String currentString : currentLineSplit){
                	
                    if(!isStringEmptyLine(currentString)){
                        ArrayList<String> listOfData = checkStringForLexemes(currentString);
                    	
                        if (isListEmpty(listOfData.size())) {
                        	createToken(currentString);
                        } else {
                        	for (String item : listOfData) {
                        		createToken(item);
                        	}
                        }
                    }
                }
            }
            lineNumber++;
        }
        
        scanner.close();
	}
	
	public void printTokenList() {
		for (Token token : listOfTokens) {
			System.out.println(token.toString());
		}
	}
	
	public ArrayList<Token> getListOfTokens(){
		return listOfTokens;
	}
	
	
	
	public ArrayList<String> checkStringForLexemes(String test){
		ArrayList<String> brokenUpText = new ArrayList<String>();
		int first = 0;
        
        for(int i = 0; i < test.length(); i++) {
        	char currentChar = test.charAt(i);
        	
        	if (isALexemeCharacter(currentChar)) {
        		
        		if (i == test.length()- 1 && test.length() > 0) {
    				brokenUpText.add(test.substring(0,i));
    				brokenUpText.add(Character.toString(test.charAt(i)));
    			} else {
    				brokenUpText.add(test.substring(first,i));
    				brokenUpText.add(Character.toString(test.charAt(i)));
    			}
        		
    			first = i+1;
        	}
        }
		return brokenUpText;
	}
	
	public void createToken(String item) {
		Lexeme l = new Lexeme(item);
        Token token = new Token(index, l.getTokenType(), l.getLexemeName(), lineNumber, item);
        listOfTokens.add(token);
        index++;
	}
	
	//Boolean Statements
	public boolean isStringEmptyLine(String line) {
		if (line.length() == 0)
			return true;
		else
			return false;
	}
	
	public boolean isListEmpty(int count) {
		if(count == 0) 
			return true;
		else
			return false;
	}
	
	public boolean isALexemeCharacter(char currentCharacter) {
		boolean lexeme = false;
		
		for (char c : listOfLexemeCharacters) {
			if (currentCharacter == c) {
				lexeme = true;
				break;
			}
		}
		
		return lexeme;
	}
}
