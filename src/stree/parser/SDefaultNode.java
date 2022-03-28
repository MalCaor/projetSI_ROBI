package stree.parser;

import java.util.ArrayList;
import java.util.List;

public class SDefaultNode implements SNode {

	private SNode parent;
	private Integer quote;
	private String contents;
	private List<SNode> children;
	private Object alien;

	protected List<SNode> newChildrenList() {
		return new ArrayList<>();
	}

	public SDefaultNode() {
		this.contents = null;
		this.children = this.newChildrenList();
		this.parent = null;
		this.quote = 0;
	}

	public int quote() {
		return this.quote;
	}
	public void quote(int q) {
		this.quote = q;
	}
	
	public Boolean isLeaf() {
		return this.contents != null;
	}

	public String contents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void addToContents(Character c) {
		if (this.contents == null)
			this.contents = "";
		this.contents += c;
	}

	public List<SNode> children() {
		return this.children;
	}

	public void setParent(SNode parent) {
		this.parent = parent;
	}
	
	public SNode parent() {
		return this.parent;
	}

	public void addChild(SNode child) {
		this.children.add(child);
		child.setParent(this);
	}

	@Override
	public void setAlien(Object alien) {
		this.alien = alien;
		
	}

	@Override
	public Object alien() {
		return this.alien;
	}

}
