package stree.parser;

public class SSyntaxError extends Error {
	private static final long serialVersionUID = -6580502390570165179L;

	public SSyntaxError(int line, int pos) {
		super("S-exp syntax error: line " + line + ": position " + pos);
	}
	public SSyntaxError(String msg, int line, int pos) {
		super("S-exp syntax error: " + msg + " : line " + line + ": position " + pos);
	}

}
