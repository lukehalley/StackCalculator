import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class UserInterface
	implements ActionListener
{
	private CalcEngine calc;
	private boolean showingAuthor;

    private JFrame frame;
	private JTextField display;
	private JLabel status;

	/**
	 * Create a user interface for a given calcEngine.
	 */
	public UserInterface(CalcEngine engine)
	{
		calc = engine;
		showingAuthor = true;
		makeFrame();
		frame.setVisible(true);
	}

	/**
	 * Make this interface visible again. (Has no effect if it is already
	 * visible.)
	 */
    public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

	/**
	 * Make the frame for the user interface.
	 */
	private void makeFrame()
	{
		frame = new JFrame(calc.getTitle());
		
		JPanel contentPane = (JPanel)frame.getContentPane();
		contentPane.setLayout(new BorderLayout(8, 8));
		contentPane.setBorder(new EmptyBorder( 10, 10, 10, 10));

		display = new JTextField();
		contentPane.add(display, BorderLayout.NORTH);

		JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
			addButton(buttonPanel, "7");
			addButton(buttonPanel, "8");
			addButton(buttonPanel, "9");
			addButton(buttonPanel, "C");
			addButton(buttonPanel, "4");
			addButton(buttonPanel, "5");
			addButton(buttonPanel, "6");
			addButton(buttonPanel, "*");
			addButton(buttonPanel, "1");
			addButton(buttonPanel, "2");
			addButton(buttonPanel, "3");
			addButton(buttonPanel, "/");
			addButton(buttonPanel, "0");
			addButton(buttonPanel, "+");
			addButton(buttonPanel, "-");
			addButton(buttonPanel, "=");
		contentPane.add(buttonPanel, BorderLayout.CENTER);

		status = new JLabel(calc.getAuthor());
		contentPane.add(status, BorderLayout.SOUTH);

		frame.pack();
	}

	/**
	 * Add a button to the button panel.
	 */
	private void addButton(Container panel, String buttonText)
	{
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		panel.add(button);
	}

	/**
	 * An interface action has been performed. Find out what it was and
	 * handle it.
	 */
	public void actionPerformed(ActionEvent event)
	{
		String command = event.getActionCommand();
		
		if (command.equals("=")) {
			calc.equals();
			
		} else {
			calc.charPressed(command);
		}

		redisplay();
	}

	/**
	 * Update the interface display to show the current value of the 
	 * calculator.
	 */
	private void redisplay()
	{
		display.setText("" + calc.getExpr());
	}

	/**
	 * Toggle the info display in the calculator's status area between the
	 * author and version information.
	 */
	@SuppressWarnings("unused")
	private void showInfo()
	{
		if(showingAuthor)
			status.setText(calc.getVersion());
		else
			status.setText(calc.getAuthor());

		showingAuthor = !showingAuthor;
	}
}

