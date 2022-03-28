package stree.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.function.Consumer;

interface Doer {
	public void execute();
}

public class SPrinter implements SVisitor {
	protected OutputStream stream;
	Boolean withIndentation;
	int level;
	int indentSize;

	public SPrinter() {
		this(new ByteArrayOutputStream());
	}

	public SPrinter(OutputStream stream) {
		this.stream = stream;
		this.level = 0;
		this.withIndentation = false;
		this.indentSize = 2;
	}

	public void withIndentation(Boolean withIndentation) {
		this.withIndentation = withIndentation;
	}

	public OutputStream result() {
		return this.stream;
	}
	
	protected void forEachSepBy(List<? extends SNode> l, Consumer<SNode> cons, Doer d) {
		int count = 0;
		for (SNode e : l) {
			cons.accept(e);
			count++;
			if (count < l.size()) {
				d.execute();
			}
		}
	}

	protected void indent() {
		try {
			for (int i = 0; i < this.indentSize; i++) {
				this.stream.write((int) ' ');
			}
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	protected void space() {
		try {
			this.stream.write((int) ' ');
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	@Override
	public void visitNode(SNode node) {
		try {
			if (this.withIndentation && this.level > 0) {
				this.stream.write((int) '\n');
				for (int i = 0; i < level; i++) {
					this.indent();
				}
			}
			for (int i = 0; i < node.quote(); i++)
				this.stream.write((int) '\'');
			this.stream.write((int) '(');
			this.stream.write((int) ' ');
			if (node.hasChildren()) {
				this.level++;
				this.forEachSepBy(node.children(), s -> s.accept(this), this::space);
				this.level--;
			}
			this.stream.write((int) ' ');
			this.stream.write((int) ')');
		} catch (IOException e) {
			throw new Error(e);
		}
	}

	@Override
	public void visitLeaf(SNode node) {
		try {
			for (int i = 0; i < node.quote(); i++)
				this.stream.write((int) '\'');
			this.stream.write(node.contents().getBytes());
		} catch (IOException e) {
			throw new Error(e);
		}
	}

}
