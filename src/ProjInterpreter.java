import java.util.ArrayList;

public class ProjInterpreter {

	private ArrayList<Line> lines;
	private ArrayList<Memory> memoryStore;

	public ProjInterpreter (ArrayList<Line> lines) {
		this.lines = lines;
		memoryStore = new ArrayList<Memory>();
	}
	
	public void runInterpreter() {
		System.out.println("\nProgram Execution : " );
		for(Line line : lines) {
			boolean isValid = true;
			ArrayList<Token> currentLinesTokens = line.getTokens();
			String[] operators = {"PLUS", "MINUS", "DIVIDE", "TIMES"};
			
			if (!isLineEmpty(line)) {
				if (currentLinesTokens.get(0).getTokenID().equals("VAR_ID") && currentLinesTokens.get(1).getTokenID().equals("UPDATE") && !currentLinesTokens.get(0).getVar().toUpperCase().equals("PUT")) { // Assignment Statement
					
					if (currentLinesTokens.size() == 4) {
						String variableName = currentLinesTokens.get(0).getVar();
						String data = currentLinesTokens.get(2).getVar();
						memoryStore.add(new Memory(variableName, data));
					} else {
						int indexOfOperator = -1;
						
						for(int i = 2; i < currentLinesTokens.size(); i++){
							for(String s : operators) {
								
								if(s.equals(currentLinesTokens.get(i).getTokenID())) {
									indexOfOperator = i;
								}
							}
						}
						
						if (indexOfOperator != -1) {
							Token firstVariable = currentLinesTokens.get(indexOfOperator - 1);
							Token secondVariable = currentLinesTokens.get(indexOfOperator + 1 );

							String firstTokenID = firstVariable.getTokenID();
							String secondTokenID = secondVariable.getTokenID();
							
							if (isOperationLegal(firstTokenID, secondTokenID)) {
								String firstVar = firstVariable.getVar();
								String secondVar = secondVariable.getVar();
								String operator = currentLinesTokens.get(indexOfOperator).getVar();
								int total = performArithmeticOperation(firstVar, secondVar, operator);
								
								String variableName = currentLinesTokens.get(0).getVar();
								String data = Integer.toString(total);
								memoryStore.add(new Memory(variableName, data));
							}
						}
					}
					
				}
				
				if (currentLinesTokens.get(0).getVar().toUpperCase().equals("PUT")) {
					String variable = currentLinesTokens.get(2).getVar();
					
					if (isVariableInMemory(variable)) {
						System.out.println(getMemoryData(variable));
					} else {
						System.out.println("Runtime Error :: Line " + line.lineNumber + " :: " + variable + " has not been declared.");
						break;
					}
					
				}
			}			
		}
	}
	
	public int performArithmeticOperation(String firstVar, String secondVar, String operator) {
		int first = 0;
		int second = 0;
		
		if (isInteger(firstVar)) {
			first = Integer.parseInt(firstVar);
		} else {
			String number = "";
			for (Memory mem : memoryStore) {
				if (mem.variableName.equals(firstVar)) {
					number = mem.data;
					break;
				}
			}
			first = Integer.parseInt(number);
		}
		
		if (isInteger(secondVar)) {
			second = Integer.parseInt(secondVar);
		} else {
			String number = "";
			for (Memory mem : memoryStore) {
				if (mem.variableName.equals(secondVar)) {
					number = mem.data;
					break;
				}
			}
			second = Integer.parseInt(number);
		}
		
		int total = 0;
		switch(operator) {
			case "*": 
				total = first * second;
				break;
			case "/": 
				total = first / second;
				break;
			case "+":
				total = first + second;
				break;
			case "-":
				total = first - second;
		}
		
		return total;
	}
	
	public String getMemoryData(String variable) {
		String varData = "";
		
		for (Memory mem: memoryStore) {
			if (mem.variableName.equals(variable))
				varData = mem.data;
		}
		
		return varData;
	}
	
	public boolean isVariableInMemory(String variable) {
		boolean isInMemory = false;
		
		for (Memory mem: memoryStore) {
			if (mem.variableName.equals(variable))
				return true;
		}
		return false;
	}
	
	public boolean isInteger(String numberCheck) {
	    boolean isInteger = true;
	    try {
	    	Integer.parseInt(numberCheck);
	    }catch (NumberFormatException e) {
	    	isInteger = false;
	    }
	    return isInteger;
	}
	
	public boolean isOperationLegal(String firstVar, String secondVar) {
		boolean checksFirst = false;
		boolean checksSecond = false;
		
		if (firstVar.equals("VAR_ID") || firstVar.equals("INT_LITERALS")) {
			checksFirst = true;
		}
		if (secondVar.equals("VAR_ID") || secondVar.equals("INT_LITERALS")) {
			checksSecond = true;
		}
		
		if (checksFirst == true && checksSecond == true) {
			return true;
		}
		return false;
	}
	
	public boolean isArithmeticOperation(String operator) {
		String[] operators = {"PLUS", "MINUS", "DIVIDE", "TIMES"};
		
		boolean isOperator = false;
		for (String s : operators) {
			if(s.equals(operator)) {
				isOperator = true;
				break;
			}
		}
		
		return isOperator;
	}
	
	public boolean isLineEmpty(Line line) {
		if (line.tokens.size() == 0)
			return true;
		return false;
	}
}
