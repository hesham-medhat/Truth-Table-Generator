package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import generator.Expression;

public class GUI {

	private JFrame frmLogicExpressions;
	private JTextField firstExpression;
	private JTextField firstNumOfVariable;
	private JTextField secondExpression;
	private JTextField secondNumOfVariable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmLogicExpressions.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmLogicExpressions = new JFrame();
		frmLogicExpressions.setTitle("Logic expressions");
		frmLogicExpressions.getContentPane().setForeground(SystemColor.text);
		frmLogicExpressions.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frmLogicExpressions.setBounds(100, 100, 803, 857);
		frmLogicExpressions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setEditable(false);
		
		firstExpression = new JTextField();
		firstExpression.setFont(new Font("Tahoma", Font.PLAIN, 15));
		firstExpression.setForeground(Color.BLACK);
		firstExpression.setColumns(10);
		
		firstNumOfVariable = new JTextField();
		firstNumOfVariable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		firstNumOfVariable.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("First expression");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNumberOfIp = new JLabel("Number of I/P of the first expression");
		lblNumberOfIp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		secondExpression = new JTextField();
		secondExpression.setFont(new Font("Tahoma", Font.PLAIN, 15));
		secondExpression.setColumns(10);
		
		secondNumOfVariable = new JTextField();
		secondNumOfVariable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		secondNumOfVariable.setColumns(10);
		
		JLabel lblSexongExpression = new JLabel("Second expression");
		lblSexongExpression.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblNumberOfIp_1 = new JLabel("Number of I/P of the second expression");
		lblNumberOfIp_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnTestFirstExpression = new JButton("Test first expression");
		btnTestFirstExpression.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTestFirstExpression.setForeground(Color.BLUE);
		btnTestFirstExpression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textArea.setText("");
				
				final String expression1 = firstExpression.getText();
				final int variables1 = Integer.valueOf(firstNumOfVariable.getText());
				
				Expression x = new Expression(expression1,variables1);
			
				final boolean[] arrX = x.generateTruthTable();
				
				textArea.setText(" The expression : " + expression1 + "\n\n");
				textArea.append(" Minterms\t Output\n");
				
				for(int i = 0; i < arrX.length ; i++){
					textArea.append(" m" + i + "\t\t " + arrX[i] + "\n");
				}
				
				if (x.isContradiction()){
					textArea.append("\n Result of testing is : Contradiction");
				}
				else if (x.isTautology()){
					textArea.append("\n Result of testing is : Tautology");
				}
				
			}
		});
		
		JButton btnTestSexondExpression = new JButton("Test second expression");
		btnTestSexondExpression.setForeground(Color.BLUE);
		btnTestSexondExpression.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTestSexondExpression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textArea.setText("");
				
				final String expression2 = secondExpression.getText();
				final int variables2 = Integer.valueOf(secondNumOfVariable.getText());
				
				Expression y = new Expression(expression2,variables2);
				
				final boolean[] arrY = y.generateTruthTable();
				
				textArea.setText(" The expression : " + expression2 + "\n\n");
				textArea.append(" Minterms\t Output\n");
				
				for(int i = 0; i < arrY.length ; i++){
					textArea.append(" m" + i + "\t\t " + arrY[i] + "\n");
				}
				
				if (y.isContradiction()){
					textArea.append("\n Result of testing is : Contradiction");
				}
				else if (y.isTautology()){
					textArea.append("\n Result of testing is : Tautology");
				}
			}
		});
		
		JButton btnCompareBoth = new JButton("Compare both");
		btnCompareBoth.setForeground(Color.BLUE);
		btnCompareBoth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCompareBoth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textArea.setText("");
				
				final String expression1 = firstExpression.getText();
				final String expression2 = secondExpression.getText();
				final int variables1 = Integer.valueOf(firstNumOfVariable.getText());
				final int variables2 = Integer.valueOf(secondNumOfVariable.getText());
				
				Expression x = new Expression(expression1,variables1);
				Expression y = new Expression(expression2,variables2);
				
				final boolean[] arrX = x.generateTruthTable();
				final boolean[] arrY = y.generateTruthTable();
				
				textArea.setText(" Comparing : " + expression1 +" and " + expression2 + "\n\n");
				textArea.append(" Minterms\tFirst output\tSecond output\n");
				
				for(int i = 0; i < arrY.length ; i++){
					textArea.append(" m" + i + "\t\t" + arrX[i] + "\t\t" + arrY[i] + "\n");
				}
				if (x.equals(y)){
					textArea.append("\n The expressions are equivalent");
				}
				else {
					textArea.append("\n The expressions aren't equivalent");
				}
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(frmLogicExpressions.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel)
					.addGap(231)
					.addComponent(firstExpression, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNumberOfIp)
					.addGap(81)
					.addComponent(firstNumOfVariable, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblSexongExpression)
					.addGap(210)
					.addComponent(secondExpression, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNumberOfIp_1)
					.addGap(59)
					.addComponent(secondNumOfVariable, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(btnTestFirstExpression)
					.addGap(37)
					.addComponent(btnTestSexondExpression)
					.addGap(35)
					.addComponent(btnCompareBoth))
				.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNewLabel))
						.addComponent(firstExpression, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNumberOfIp))
						.addComponent(firstNumOfVariable, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(lblSexongExpression))
						.addComponent(secondExpression, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNumberOfIp_1))
						.addComponent(secondNumOfVariable, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTestFirstExpression, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTestSexondExpression, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCompareBoth, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
		);
		frmLogicExpressions.getContentPane().setLayout(groupLayout);
	}
}
