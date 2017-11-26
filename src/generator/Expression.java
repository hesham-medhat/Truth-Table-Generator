/**
 * Package for generating truth tables.
 */
package generator;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a full expression.
 * 
 * @author H
 *
 */
public class Expression implements Comparable<Expression> {

	private final List<Term> terms;
	public int variables;
	public String expressionString;
	private boolean[] truthTable;

	/**
	 * Constructor for expression taking input String and number of variables.
	 * 
	 * @param expressionString
	 *            as string.
	 * @param variables
	 *            number of variables.
	 */
	public Expression(final String expressionString, final int variables) {
		this.expressionString = expressionString.trim();
		this.variables = variables; // Must be before terms generation.
		this.terms = termsGenerator();
		this.truthTable = generateTruthTable();
	}

	@Override
	public int compareTo(final Expression other) {
		final boolean[] otherOutput = other.generateTruthTable();
		for (int i = 0; i < otherOutput.length; i++) {
			if (otherOutput[i] != truthTable[i]) {
				return other.hashCode() - this.hashCode();
			}
		}
		return 0;
	}

	@Override
	public boolean equals(final Object other) {
		final boolean[] otherOutput = ((Expression) other).generateTruthTable();
		for (int i = 0; i < otherOutput.length; i++) {
			if (otherOutput[i] != truthTable[i]) {
				return false;
			}
		}
		return true;
	}

	public boolean[] generateTruthTable() {
		if (truthTable == null) {
			truthTable = new boolean[(int) Math.round(Math.pow(2.0, variables))];
			for (int i = 0; i < (int) Math.round(Math.pow(2.0, variables)); i++) { // Try
																					// all
																					// possible
																					// input.
				for (final Term t : terms) {
					if (t.evaluate(i)) {// If evaluates to for any product term.
						truthTable[i] = true;
						break;
					}
				}
			}
		}
		return truthTable;
	}

	/**
	 * @return true if the expression is a contradiction.
	 */
	public boolean isContradiction() {
		for (final boolean output : this.generateTruthTable()) {
			if (output) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return true if the expression is a tautology.
	 */
	public boolean isTautology() {
		for (final boolean output : this.generateTruthTable()) {
			if (!output) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Generates all product terms in the expression.
	 * 
	 * @return list of terms generated.
	 */
	private List<Term> termsGenerator() {
		final String[] termsArr = expressionString.split("\\+");
		final List<Term> termsList = new LinkedList<>();
		for (final String i : termsArr) {
			termsList.add(new Term(i, variables));
		}
		return termsList;
	}

}
