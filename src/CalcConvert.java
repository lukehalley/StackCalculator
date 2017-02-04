import java.util.Stack;

public class CalcConvert {

	public static String convertToPostfix(String infix) {
		Stack<String> postfixStack = new Stack<String>();
		String postfix = "";

		// Found On Stack Overflow:
		// http://stackoverflow.com/questions/21408570/tokenizing-an-infix-string-in-java
		String[] tokens = infix.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");

		for (String token : tokens) {
			switch (token) {
			case "(":
				postfixStack.push(token);
				break;
			case ")":
				String t = postfixStack.pop();

				do {
					postfix += t;
					postfix += " ";
					t = postfixStack.pop();
				} while (!t.equals("("));
				break;

			case "+":
			case "-":
			case "/":
			case "*":
				if (postfixStack.empty()) {
					postfixStack.push(token);
				} else {
					while (!postfixStack.empty() && precendance(token) < precendance(postfixStack.peek())) {
						postfix += postfixStack.pop();
						postfix += " ";
					}
					postfixStack.push(token);
				}
				break;
			default:
				postfix += token;
				postfix += " ";
			}
		}
		while (!postfixStack.empty()) {
			postfix += postfixStack.pop();
			postfix += " ";
		}
		return postfix;
	}

	public static int precendance(String op) {
		switch (op) {
		case "+":
		case "-":
			return 0;
		case "*":
		case "/":
			return 1;
		case "^":
			return 2;
		case "(":
			return -1;
		default:
			throw new IllegalArgumentException("Operator unknown: " + op);
		}
	}

	public static String evaluatePostfix(String postfix) {
		System.out.println(postfix);
		Stack<String> evaluateStack = new Stack<String>();

		String[] tokens = postfix.split("\\s+");

		for (String token : tokens) {
			switch (token) {
			case "+":
				String plusOne = evaluateStack.pop();
				String plusTwo = evaluateStack.pop();
				double plusAnswer = Double.parseDouble(plusOne) + Double.parseDouble(plusTwo);
				evaluateStack.push(Double.toString(plusAnswer));
				break;
			case "-":
				String minusOne = evaluateStack.pop();
				String minusTwo = evaluateStack.pop();
				double minusAnswer = Double.parseDouble(minusTwo) - Double.parseDouble(minusOne);
				evaluateStack.push(Double.toString(minusAnswer));
				break;
			case "/":
				String divideOne = evaluateStack.pop();
				String divideTwo = evaluateStack.pop();
				double divideAnswer = Double.parseDouble(divideTwo) / Double.parseDouble(divideOne);
				evaluateStack.push(Double.toString(divideAnswer));
				break;
			case "*":
				String multiplyOne = evaluateStack.pop();
				String multiplyTwo = evaluateStack.pop();
				double multiplyAnswer = Double.parseDouble(multiplyOne) * Double.parseDouble(multiplyTwo);
				evaluateStack.push(Double.toString(multiplyAnswer));
				break;
			default:
				evaluateStack.push(token);
			}

		}

		return evaluateStack.pop();

	}

}
