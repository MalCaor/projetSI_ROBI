package stree.parser;

public interface SVisitor {
	public void visitNode(SNode node);
	public void visitLeaf(SNode node);
}
