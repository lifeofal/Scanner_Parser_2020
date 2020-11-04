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

        return "Token " + num + ": " + type + "." + tokenID + "\t\t\t" + var + "\t\t\tLine: " + line;

    }
}
