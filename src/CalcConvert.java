import java.util.Stack;

public class CalcConvert {

	public static String convertToPostfix(String infix) {
		Stack<String> st = new Stack<String>();
		String postfix = "";

		String[] tokens = infix.split("(?<=[^\\.a-zA-Z\\d])|(?=[^\\.a-zA-Z\\d])");
		for (String token : tokens) {
			switch (token) {
			case "+":
			case "-":
			case "/":
			case "*":
				if (st.empty()) {
					st.push(token);
				} else {
					while (!st.empty() && precendance(token) >= precendance(st.peek())) {
						postfix += st.pop();
					}
					st.push(token);
				} break;
			default:
				postfix += token;
			}

		}
		while (!st.empty()) {
			postfix += st.pop();
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
		default:
			throw new IllegalArgumentException("Operator unknown: " + op);
		}
	}
}

//
// Algorithm convertToPostfix (infix)
// // Converts an infix expression to an equivalent postfix expression.
// operatorStack = a new empty stack
// postfix = a new empty string
// while (infix has characters left to parse)
// {
// nextCharacter = next nonblank character of infix
// switch (nextCharacter)
// {
// case variable:
// Append nextCharacter to postfix
// break
// case '^':
// operatorStack.push (nextCharacter)
// break
// case '+':
// case '-':
// case '*':
// case '/':
// while (!operatorStack.isEmpty () and
// precedence of nextCharacter <= precedence of operatorStack.peek ())
// {
// Append operatorStack.peek () to postfix
// operatorStack.pop ()
// }
// operatorStack.push (nextCharacter)
// break
// case '( ':
// operatorStack.push (nextCharacter)
// break
// case ')': // stack is not empty if infix expression is valid
// topOperator = operatorStack.pop ()
// while (topOperator != '(')
// {
// Append topOperator to postfix
// topOperator = operatorStack.pop ()
// }
// break
// default:
// break
// }
// }
// while (!operatorStack.isEmpty ())
// {
// topOperator = operatorStack.pop ()
// Append topOperator to postfix
// }
// return postfix
//
// }
