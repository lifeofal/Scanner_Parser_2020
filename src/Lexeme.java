
public class Lexeme {
    private String lexemeName;
    private String tokenType;

    private String[] intLiterals = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    private String[] variables = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};



    public Lexeme(String var) {
        this.lexemeName = var;
        LexemeType(lexemeName);

    }


    public void LexemeType(String var) {
        boolean flag = true;


        if(!var.equals("")) {
            for (int i = 0; i < variables.length; i++) {
                if (var.charAt(0) == variables[i].charAt(0)) {
                    tokenType = "NonLexeme";
                    lexemeName = "VAR_ID";
                    flag = false;
                    break;
                }
            }
        }

        if (flag && var.length() > 0) {
            for (int i = 0; i < intLiterals.length; i++) {
                if (var.charAt(0) == intLiterals[i].charAt(0)) {
                    tokenType = "NonLexeme";
                    lexemeName = "INT_LITERALS";
                    flag = false;
                    break;
                }
            }
        }
        
        if(flag && var.length() > 0){
            tokenType = "Lexeme";
                switch (var.charAt(0)) {
                    case '+':
                        lexemeName = "PLUS";
                        break;
                    case '=':
                        lexemeName = "UPDATE";
                        break;
                    case '/':
                        lexemeName = "DIVIDE";
                        break;
                    case '*':
                        lexemeName = "TIMES";
                        break;
                    case '-':
                        if(var.length() >1){
                            lexemeName = "COMMENT";
                        }else
                        lexemeName = "MINUS";
                        break;
                    case '%':
                        lexemeName = "REMAINDER";
                        break;
                    case '^':
                        lexemeName = "POWER";
                        break;
                    case '<':
                        lexemeName = "GREATER";
                        break;
                    case'>':
                        lexemeName = "LESS";
                        break;
                    case '(':
                    	lexemeName = "OPEN_PAREN";
                    	break;
                    case ')':
                    	lexemeName = "CLOSE_PAREN";
                    case ';':
                    	lexemeName = "END_OF_LINE";
                    default:
                        lexemeName = "SYMBOL";
                        break;
            }
        }

    }


    public String getLexemeName() {
        return lexemeName;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String toString() {
        return tokenType + "." + lexemeName;
    }
}
