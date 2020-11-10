import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Simple Test Class for the Program
public class Tester {

    public static void main(String[] args) throws FileNotFoundException {
        ProjScanner scan = new ProjScanner("test3.jl");
//        scan.printTokenList();
    	
        ProjParser parser = new ProjParser(scan.getListOfLines());
        parser.parse();
    }
}
