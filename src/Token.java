import java.util.*;


public class Token {

    private String type;
    private String tokenID;
    private int line;
    private String var;
    private int num;


    public Token(int num, String type, String tokenID, int line, String var){

        this.type = type;
        this.tokenID = tokenID;
        this.line = line;
        this.var = var;
        this.num = num;

    }

    public String getType() {

        return type;

    }

    public void setType(String type) {

        this.type = type;

    }

    public String getTokenID() {

        return tokenID;

    }

    public void setTokenID(String tokenID) {

        this.tokenID = tokenID;

    }

    public int getLine() {

        return line;

    }

    public void setLine(int line) {

        this.line = line;

    }

    public String getVar() {

        return var;

    }

    public void setVar(String var) {

        this.var = var;

    }

    public String toString(){

        return formatText();

    }
    
    public String formatText() {
    	int max_length = 30;
    	
    	String stringBuilder = "";
    	
    	String tokenNumberLabel = "Token " + num + ": ";
    	
    	stringBuilder += tokenNumberLabel;
    	
    	for(int i = 0; i < 15 - tokenNumberLabel.length(); i++) {
    		stringBuilder += " ";
    	}
    	
    	String typeNameLabel = type + "." + tokenID;
    	
    	stringBuilder += typeNameLabel;
    	
    	for(int i = 0; i < max_length - typeNameLabel.length(); i++) {
    		stringBuilder += " ";
    	}
    	
    	String lineNumberLabel = "Line : " + line;
    	stringBuilder += lineNumberLabel;
    	
    	return stringBuilder;
    }
}
