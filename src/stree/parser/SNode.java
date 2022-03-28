package stree.parser;

import java.util.List;

public interface SNode {

	Boolean isLeaf();

	int quote();

	void quote(int q);

	String contents();

	void setContents(String contents);

	void addToContents(Character c);

	List<SNode> children();

	void setParent(SNode parent);

	void addChild(SNode child);

	SNode parent();

	default Boolean isNode() {
		return !this.isLeaf();
	}

	default Boolean hasContents() {
		return this.contents() != null;
	}

	default Boolean hasChildren() {
		return this.children() != null && this.children().size() > 0;
	}

	default SNode get(int pos) {
		return this.children().get(pos);
	}

	default int size() {
		return this.children().size();
	}

	default void accept(SVisitor visitor) {
		if (this.isLeaf())
			visitor.visitLeaf(this);
		else
			visitor.visitNode(this);
	}
	
	public void setAlien(Object alien);
	public Object alien();

}
