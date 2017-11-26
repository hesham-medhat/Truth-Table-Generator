/**
 * Package for generating truth tables.
 */
package generator;

import java.util.*;

/**
 * This class represents a full expression.
 * @author H
 *
 */
public class Expression implements Comparable<Expression> {

	private List<Term> terms;
	public int variables;
	public String expressionString;
	private boolean[] truthTable;

	/**
	 * Constructor for expression taking input String and number of variables.
	 * @param expressionString as string.
	 * @param variables number of variables.
	 */
	public Expression(final String expressionString, final int variables) {
		this.expressionString = expressionString.trim();
		this.variables = variables; //Must be before terms generation.
		this.terms = termsGenerator();
		this.truthTable = generateTruthTable();
	}

	/**
	 * Generates all product terms in the expression.
	 * @return list of terms generated.
	 */
	private List<Term> termsGenerator() {
		String[] termsArr = expressionString.split("\\+");
		List<Term> termsList = new LinkedList<Term>();
		for (String i : termsArr) {
			termsList.add(new Term(i, variables));
		}
		return termsList;
	}

	public boolean[] generateTruthTable() {
		if (truthTable == null) {
			truthTable = new boolean[(int) Math.round(Math.pow(2.0, variables))];
			for (int i = 0; i < (int) Math.round(Math.pow(2.0, variables)); i++) { // Try all possible input.
				for (Term t : terms) {
					if (t.evaluate(i)) {// If evaluates to for any product term.
						truthTable[i] = true;
						break;
					}
				}
			}
		}
		return truthTable;
	}

	@Override
	public int compareTo(Expression other) {
		boolean[] otherOutput = other.generateTruthTable();
		for (int i = 0; i < otherOutput.length; i++) {
			if (otherOutput[i] != truthTable[i]) {
				return -1;
			}
		}
		return 0;
	}

	/**
	 * @return true if the expression is a tautology.
	 */
	public boolean isTautology() {
		for (boolean output : this.generateTruthTable()) {
			if (!output) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return true if the expression is a contradiction.
	 */
	public boolean isContradiction() {
		for (boolean output : this.generateTruthTable()) {
			if (output) {
				return false;
			}
		}
		return true;
	}

}
