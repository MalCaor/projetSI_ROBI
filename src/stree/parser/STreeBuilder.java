package stree.parser;

import java.util.ArrayList;
import java.util.List;

public class STreeBuilder<T> implements SParser.SHandler<T> {

	public interface SNodeBuilder<T> {
		T newNode();
	}
	@SuppressWarnings("unchecked")
	protected SNodeBuilder<T> defaultNodeBuilder() {
		return () -> {
			return (T) new SDefaultNode();
		};
	}

	private SNodeBuilder<T> nodeBuilder;	
	private ArrayList<T> nodes;
	private SNode top;
	private int quote = 0;
	
	
	public STreeBuilder() {
		this.nodes = new ArrayList<>();
		this.reset();
	}
	
	public void reset() {
		nodes.clear();
		this.top = null;
		this.quote = 0;
	}

	protected SNodeBuilder<T> nodeBuilder() {
		return this.nodeBuilder == null ? this.defaultNodeBuilder() : this.nodeBuilder;
	}

	public void setNodeBuilder(SNodeBuilder<T> builder) {
		this.nodeBuilder = builder;
	}

	@Override
	public List<T> result() {
		return nodes;
	}

	@SuppressWarnings("unchecked")
	protected void storeNode(SNode node) {
		if (this.top != null)  {
			this.top.addChild((SNode) node);
		} else  {
			nodes.add((T) node);
		}
	}
	
	protected SNode newNode(String contents) {
		SNode node = (SNode) this.nodeBuilder().newNode();
		node.quote(this.quote);
		this.quote = 0;
		node.setContents(contents);
		return node;
	}
	
	@Override
	public void atom(String contents) {
		this.storeNode(this.newNode(contents));
	}

	@Override
	public void startNode() {
		SNode node = this.newNode(null);
		this.storeNode(node);
		this.top = node;
	}

	@Override
	public void endNode() {
		this.top = this.top.parent();
	}

	@Override
	public void comment(String c) {
	}

	@Override
	public void quote() {
		this.quote++;
	}

}
