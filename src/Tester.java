import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Simple Test Class for the Program
public class Tester {

    public static void main(String[] args) throws FileNotFoundException {
        ProjScanner scan = new ProjScanner("test3.jl");
        ProjParser parser = new ProjParser(scan.getListOfLines());
        ProjInterpreter interpreter = new ProjInterpreter(scan.getListOfLines());
        int errors = parser.checkProgramSyntax();
        
        if (errors != 0) {
        	System.out.println("When checking your syntax, we found " + errors + " errors.");
        } else {
            scan.printProgram();
            scan.printTokenList();
            parser.parse();
            interpreter.runInterpreter();
        }

    }
}
