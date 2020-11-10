import java.util.ArrayList;

public class Line {
	public int lineNumber;
	public ArrayList<Token> tokens;
	
	public Line (int line) {
		tokens = new ArrayList<Token>();
		lineNumber = line;
	}
	
	public void addToken(Token token) {
		tokens.add(token);
	}
	
	public ArrayList<Token> getTokens(){
		return tokens;
	}
}
