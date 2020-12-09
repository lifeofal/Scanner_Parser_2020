import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjScanner {
	private ArrayList<Token> listOfTokens;
	private ArrayList<Line> listOfLines;
	private char[] listOfLexemeCharacters = {'(',')','{','}', ';', ':', '.', '*'};
	private int index;
	private int lineNumber;
    
	public ProjScanner (String filename)throws FileNotFoundException  {
		initializeLocalVariables();
		loadInADAFile(filename);
	}
	
	public void initializeLocalVariables() {
		listOfTokens = new ArrayList<Token>();
		listOfLines = new ArrayList<Line>();
		index = 0;
		lineNumber = 1;
	}
	
	public void loadInADAFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            Line currentLine = new Line(lineNumber);
            listOfLines.add(currentLine);
            if (line.contains("--")) {
            	
            } else if (line.length() != 0) {
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
	
	public ArrayList<Line> getListOfLines(){
		return listOfLines;
	}

	public ArrayList<String> checkStringForLexemes(String test){
		ArrayList<String> data = new ArrayList<String>();
        int start = 0;
        int charLocation = 0;
        
        for (int i = 0; i < test.length();i++) {
        	for (char c : listOfLexemeCharacters) {
        		if (c == test.charAt(i) && i > 0) {
        			charLocation = i;
        			data.add(test.substring(start, charLocation));
        			data.add(Character.toString(test.charAt(i)));
        			start = i;
        			break;
        		}
        	}
        }
        
		return data;
	}
	
	public void createToken(String item) {
		Lexeme l = new Lexeme(item);
        Token token = new Token(index, l.getTokenType(), l.getLexemeName(), lineNumber, item);
        listOfLines.get(lineNumber-1).addToken(token);
        listOfTokens.add(token);
        index++;
	}
	
	public void printProgram() {
		System.out.println("Source Code Start");
		System.out.println();
		for(Line line : listOfLines) {
			ArrayList<Token> tokens = line.getTokens();
			
			for(Token token : tokens) {
				System.out.print(token.getVar() + " ");
			}
			
			System.out.println();
		}
		System.out.println();
		System.out.println("Source Code End");

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
	
	public boolean isAComment(String line) {
		if (line.contains("--"))
			return true;
		else
			return false;
	}
}
