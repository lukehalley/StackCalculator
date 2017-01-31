
public class CalcEngine {

	String expr;

	/**
	 * Create a CalcEngine instance. Initialise its state so that it is ready
	 * for use.
	 */
	public CalcEngine() {
		expr = "";
	}

	/**
	 * Return the value that should currently be displayed on the calculator
	 * display.
	 */
	public String getExpr() {
		return expr;
	}

	/**
	 * A number button was pressed. Do whatever you have to do to handle it. The
	 * number value of the button is given as a parameter.
	 */
	public void charPressed(String c) {

		expr += c;
	}

	/**
	 * The '=' button was pressed.
	 */
	public void equals() {
		
		String postfix = CalcConvert.convertToPostfix(expr);
		String answer = CalcConvert.evaluatePostfix(postfix);
		
		expr = answer;
		
	}

	/**
	 * The 'C' (clear) button was pressed.
	 */
	public void clear() {
		expr = "";

	}

	/**
	 * Return the title of this calculation engine.
	 */
	public String getTitle() {
		return ("Lukes Calculator");
	}

	/**
	 * Return the author of this engine. This string is displayed as it is, so
	 * it should say something like "Written by H. Simpson".
	 */
	public String getAuthor() {
		return ("Luke Halley");
	}

	/**
	 * Return the version number of this engine. This string is displayed as it
	 * is, so it should say something like "Version 1.1".
	 */
	public String getVersion() {
		return ("Ver. 1.1");
	}

}
