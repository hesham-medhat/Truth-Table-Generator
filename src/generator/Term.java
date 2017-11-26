/**
 * Package for generating truth tables.
 */
package generator;

/**
 * @author H
 *
 */
public class Term {

	public String termString;
	public int variables;
	private final boolean[] varRep;
	private final boolean[] varComp;
	public boolean contradiction;

	public Term(final String expression, final int variables) {
		contradiction = false;
		this.termString = expression.trim();
		this.variables = variables;
		varRep = new boolean[variables];
		varComp = new boolean[variables];
		analyzeTerm();
	}

	/**
	 * Analyzes the term. This method builds the varRep and varComp.
	 */
	private void analyzeTerm() {
		for (int i = 0; i < termString.length(); i++) {
			final char c = termString.charAt(i);
			if (c != '\'') {
				if (varRep[c - 'A']) {// Variable already represented.
					if (nextIsComp(i) && !varComp[c - 'A']) {
						contradiction = true;
						break;
					} else if (varComp[c - 'A'] && !nextIsComp(i)) {
						contradiction = true;
						break;
					}
				}
				varRep[c - 'A'] = true;// Variable denoted by this character is
										// represented.
				if (nextIsComp(i)) {
					varComp[c - 'A'] = true;// Variable denoted by this
											// character is complemented.
				}
			}
		}
	}

	/**
	 * Evaluates an expression as true or false for given input sequence.
	 * 
	 * @param sequence
	 * @return
	 */
	public boolean evaluate(final int sequence) {
		final boolean[] input = makeinputRepresentation(sequence);
		if (contradiction) {
			return false;
		} else {
			for (int i = 0; i < variables; i++) {
				if (varRep[i]) {
					if (input[i] != !varComp[i]) {
						return false;
					}
				}
			}
			return true;
		}
	}

	/**
	 * Represents the input sequence as boolean array.
	 * 
	 * @param sequence
	 *            as decimal integer.
	 * @return boolean array representation of presence of variables as 0's
	 *         (false) or 1's (true).
	 */
	private boolean[] makeinputRepresentation(final int sequence) {
		String binaryString = Integer.toBinaryString(sequence);
		final StringBuilder sb = new StringBuilder();
		for (int j = 0; j < variables - binaryString.length(); j++) {
			sb.append('0');// Add missing leading zeroes.
		}
		sb.append(binaryString);
		binaryString = sb.toString(); // Reversed here to match indexing of Rep
										// and Comp.
		final boolean[] input = new boolean[variables];
		for (int i = 0; i < variables; i++) {
			if (binaryString.charAt(i) == '1') {
				input[i] = true;
			}
		}
		return input;
	}

	/**
	 * Determines if the next character in termString is a complement '.
	 * 
	 * @param iterator
	 *            current position.
	 * @return true if next character in sequence is complement.
	 */
	private boolean nextIsComp(final int iterator) {
		return iterator < termString.length() - 1 && termString.charAt(iterator + 1) == '\'';
	}

}
