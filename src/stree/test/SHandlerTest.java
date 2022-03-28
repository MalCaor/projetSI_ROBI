package stree.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import stree.parser.SDefaultNode;
import stree.parser.SNode;
import stree.parser.SParser;
import stree.parser.STreeBuilder;

class SHandlerTest {

	class MySNode extends SDefaultNode implements SNode {

	}

	class MySNodeBuilder implements STreeBuilder.SNodeBuilder<SNode> {
		@Override
		public MySNode newNode() {
			return new MySNode();
		}
	}

	@Test
	void testNode() throws IOException {
		SParser<SNode> reader = new SParser<>();
		STreeBuilder<SNode> builder = new STreeBuilder<>();
		List<SNode> nodes;
		SNode n;

		builder.setNodeBuilder(new MySNodeBuilder());

		nodes = reader.parse(builder, "( X )");
		n = nodes.get(0);
		assertTrue(n != null);
		assertTrue(n instanceof MySNode);
		assertFalse(n.isLeaf());
		assertTrue(n.hasChildren());
		assertTrue(n.get(0) instanceof MySNode);

		builder.setNodeBuilder(new STreeBuilder.SNodeBuilder<>() {
			public MySNode newNode() {
				return new MySNode();
			}
		});

		nodes = reader.parse(builder, "( X )");
		n = nodes.get(0);
		assertTrue(n != null);
		assertTrue(n instanceof MySNode);
		assertFalse(n.isLeaf());
		assertTrue(n.hasChildren());
		assertTrue(n.get(0) instanceof MySNode);

		builder.setNodeBuilder(() -> {
			return new MySNode();
		});

		nodes = reader.parse(builder, "( X )");
		n = nodes.get(0);
		assertTrue(n != null);
		assertTrue(n instanceof MySNode);
		assertFalse(n.isLeaf());
		assertTrue(n.hasChildren());
		assertTrue(n.get(0) instanceof MySNode);
	}

}
