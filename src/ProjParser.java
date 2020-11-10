import java.util.ArrayList;

public class ProjParser {
	ArrayList<Line> lines;
	
	public ProjParser(ArrayList<Line> lines) {
		this.lines = lines;
	}
	
	public void parse() {		
		for (Line line : lines) {
			if (!isLineEmpty(line)) {
				System.out.println("<Block> -> <Statement>");
			
				for(Token tokens : line.getTokens()) {
					System.out.print(tokens.getVar() + " ");
				}
				
				System.out.println("");
				
				ArrayList<Token> tokens = line.getTokens();
				
				if (isAssignmentStatement(tokens)) {
					System.out.println("<Statement> -> <Assignment_Statement>");
					
					System.out.print("<Assignment_Statement> -> " );
					for(Token token : tokens) {
						System.out.print(getLexemeType(token.getVar(), false) + "  " );
					}
					
					System.out.println();
				}
				
				for(Token token : tokens) {
					String tokenVariable = token.getVar();

					getLexemeType(tokenVariable, true);
				}
					
				for(Token token : tokens) {
					String tokenVariable = token.getVar();
					
					System.out.println(tokenVariable + " -> " + getLexemeType(tokenVariable, false));
				}
				
				System.out.println();
			}
			
			
		}
	}
	
	public String isLexeme(String token) {
		
		String lexemeType = "";
		
		boolean isAKnownLexeme = false;
		switch (token) {
			case ":=":
				lexemeType = "<Equal_Operator>"; 
				isAKnownLexeme = true;
				break;
			case "+":
				lexemeType = "<Addition_Operator>"; 
				isAKnownLexeme = true;
				break;
			case "-":
				lexemeType = "<Subtraction_Operator>"; 
				isAKnownLexeme = true;
				break;	
			case "*":
				lexemeType = "<Multiplication_Operator>"; 
				isAKnownLexeme = true;
				break;
			case "/":
				lexemeType = "<Division_Operator>"; 
				isAKnownLexeme = true;
				break;
		}
	
		if (isAKnownLexeme == false) {
			
			if (isStringNumeric(token)) {
				lexemeType = "<Literal_Integer>";
			} else {
				lexemeType = "<Literal_String>";
			}
			
		}
		
		return lexemeType;
	}
	
	public String getLexemeType(String tokenVariable, boolean printOut) {
		String lexemeType = "";
		String printStatement = "";
		
		if (isArithemeticExpression(tokenVariable)){
			lexemeType =  getArithmeticExpressionType(tokenVariable);
			printStatement += "<Assignment_Operator> -> " + lexemeType;
		} else if (isStringNumeric(tokenVariable)) {
			lexemeType = "<literal_integer>";
			printStatement += "<Arithmetic_Expression> -> " + lexemeType;

		} else if (isAssignmentOperator(tokenVariable)) {
			lexemeType = "<Equal_Operator>";
			printStatement += "<Assignment_Operator> -> " + lexemeType;

		} else if (tokenVariable.equals(";")) {
			lexemeType = "<End_Operator>";
			printStatement += "<Assignment_Operator> -> " + lexemeType;

		} else {
			lexemeType = "id";
			printStatement += "<Arithmetic_Expression> -> " + lexemeType;
		}
		
		if (printOut == true) {
			System.out.println(printStatement);
		}
		
		return lexemeType;
	}
	
	public String getArithmeticExpressionType(String token) {
		String lexemeType = "";
		
		switch (token) {
			case "+":
				lexemeType = "<Addition_Operator>"; 
				break;
			case "-":
				lexemeType = "<Subtraction_Operator>"; 
				break;	
			case "*":
				lexemeType = "<Multiplication_Operator>"; 
				break;
			case "/":
				lexemeType = "<Division_Operator>"; 
				break;
		}
		
		return lexemeType;
	}
	
	public boolean isArithemeticExpression(String token) {
		if (token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-"))
			return true;
		return false;
	}
	
	
	public boolean isAssignmentStatement(ArrayList<Token> tokens) {
		boolean foundAssignmentOperator = false;
		
		for (Token token : tokens) {
			if (token.getVar().equals(":=")) {
				foundAssignmentOperator = true;
				break;
			}
		}
		
		return foundAssignmentOperator;
	}
	
	public boolean isEndLineOperator(String line) {
		if(line.equals(";")) {
			return true;
		}
		return false;
	}
	
	public boolean isAssignmentOperator(String line) {
		if(line.equals(":=")) {
			return true;
		}
		return false;
	}
	
	public boolean isLineEmpty(Line line) {
		if (line.tokens.size() == 0)
			return true;
		return false;
	}
	
	public boolean isStringNumeric(String token) {
		try {
			Integer.parseInt(token);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
}
