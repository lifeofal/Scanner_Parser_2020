import java.util.ArrayList;

public class ProjInterpreter {
	/*
	 * Idea : 
	 * If ProjParser shows no syntax errors, then it can procceed. \
	 	Once here we will need to to re run through all of the lines. 
	 	If there is an assignment statement map the variable name to the int literal or 
	 */
	private ArrayList<Line> lines;
	private ArrayList<Memory> memoryStore;

	public ProjInterpreter (ArrayList<Line> lines) {
		this.lines = lines;
		memoryStore = new ArrayList<Memory>();
	}
	
	public void run() {
		
	}
}
