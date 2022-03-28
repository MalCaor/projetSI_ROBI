package stree.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

public class SParser<T> {

	private Reader input;
	private SHandler<T> handler;
	private String pending;
	private final Character[] whites = { ' ', '\r', '\n', '\t' };
	private int line;
	private int pos;
	private int level;

	public interface SHandler<T> {
		default List<T> result() {
			return null;
		}

		default void reset() {
		}

		default void atom(String a) {
		}

		default void startNode() {
		}

		default void endNode() {
		}

		default void comment(String c) {
		}

		default void quote() {
		}
	}

	protected SParser.SHandler<T> defaulHandler() {
		return new STreeBuilder<T>();
	};

	protected int read() throws IOException {
		int n = this.input.read();
		if ((char) n == '\n') {
			this.line++;
			this.pos = 0;
		} else {
			this.pos++;
		}
		return n;
	}

	public List<T> parse(SHandler<T> handler, String src) throws IOException {
		return this.parse(handler, new StringReader(src));
	}

	public List<T> parse(String src) throws IOException {
		return this.parse(new StringReader(src));
	}

	public List<T> parse(Reader input) throws IOException {
		return this.parse(this.defaulHandler(), input);
	}
	
	public List<T> parse(SHandler<T> handler, Reader input) throws IOException {
		this.input = input;
		this.handler = handler;
		this.pending = "";
		this.line = 1;
		this.pos = 0;
		this.level = 0;
		this.handler.reset();
		this.parse();
		return handler.result();
	}

	protected int parse() throws IOException {
		do {
			int n = this.read();
			switch (n) {
			case -1:
				if (this.level > 0) {
					throw new SSyntaxError("Missing closing", this.line, this.pos);
				}
				this.flushPending();
				return n;

			case ')':
				this.flushPending();
				this.level--;
				if (this.level < 0)
					throw new SSyntaxError("Too much closing", this.line, this.pos);
				this.handler.endNode();
				break;

			case ';':
				this.flushPending();
				this.readComment();
				break;

			case '(':
				this.flushPending();
				this.level++;
				this.handler.startNode();
				break;

			case '"':
				this.readString();
				break;

			case '\'':
				this.handler.quote();
				break;

			default:
				if (this.isWhite((char) n)) {
					this.flushPending();
				} else {
					this.pending += ((char) n);
				}
			}

		} while (true);
	}

	protected void readComment() throws IOException {
		int n;
		String comment = "";
		do {
			n = this.read();
			if (n == -1) {
				return;
			}
			comment += n;
		} while (n != '\n');
		this.handler.comment(comment);
	}

	protected void readString() throws IOException {
		this.pending += ('"');
		int l = this.line;
		int p = this.pos;
		do {
			int n = this.read();
			if ((char) n == '"') {
				this.pending += ((char) n);
				return;
			}
			if (n == -1) {
				if (n == -1) {
					throw new SSyntaxError("Unclosed string", l, p);
				}
			}
			if ((char) n == '\\') {
				this.readEscape();
			} else {
				this.pending += ((char) n);
			}
		} while (true);
	}

	protected void readEscape() throws IOException {
		int n = this.read();
		if (n == -1) {
			throw new SSyntaxError(this.line, this.pos);
		}
		this.pending += '\\';
		this.pending += ((char) n);
	}

	protected Boolean flushPending() {
		if (!this.pending.isEmpty()) {
			this.handler.atom(this.pending);
			this.pending = "";
			return true;
		}
		return false;
	}

	protected Boolean isWhite(char n) {
		return (Arrays.asList(this.whites).contains(n));
	}

}
