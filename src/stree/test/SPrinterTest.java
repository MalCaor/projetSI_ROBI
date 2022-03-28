package stree.test;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import stree.parser.SNode;
import stree.parser.SParser;
import stree.parser.SPrinter;
import stree.parser.STreeBuilder;

class SPrinterTest {

	@Test
	void test1() throws IOException {
		SParser<SNode> reader = new SParser<>();
		STreeBuilder<SNode> builder = new STreeBuilder<>();
		SPrinter printer;
		List<SNode> nodes;
		SNode n;

		nodes = reader.parse(builder, "()");
		n = nodes.get(0);
		printer = new SPrinter();
		n.accept(printer);
		assertTrue(printer.result().toString().equals("(  )"));

		nodes = reader.parse(builder, "(X)");
		n = nodes.get(0);
		printer = new SPrinter();
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( X )"));

		nodes = reader.parse(builder, "('X)");
		n = nodes.get(0);
		printer = new SPrinter();
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( 'X )"));

		nodes = reader.parse(builder, "( 	X  )");
		n = nodes.get(0);
		printer = new SPrinter();
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( X )"));

		nodes = reader.parse(builder, "(X Y)");
		n = nodes.get(0);
		printer = new SPrinter();
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( X Y )"));

		nodes = reader.parse(builder, "( X ( Y ) Z )");
		n = nodes.get(0);
		printer = new SPrinter();
		n.accept(printer);
		System.out.println(((ByteArrayOutputStream) printer.result()).toString());
		assertTrue(printer.result().toString().equals("( X ( Y ) Z )"));

		nodes = reader.parse(builder, "( X ''( 'Y ) Z )");
		n = nodes.get(0);
		printer = new SPrinter();
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( X ''( 'Y ) Z )"));

		nodes = reader.parse(builder, "( X = 1 ( Y  = X ) Z )");
		n = nodes.get(0);
		printer = new SPrinter();
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( X = 1 ( Y = X ) Z )"));

		nodes = reader.parse(builder, "(\n" + "	(set c (Console new))\n" + "	(c print \"Hello World\")\n"
				+ "	((Console new) print \"il était une fois:\\n\\\"bla bla\\n\\\"\") \n" + ")");
		n = nodes.get(0);
		printer = new SPrinter();
		printer.withIndentation(true);
		n.accept(printer);
		System.out.println(((ByteArrayOutputStream) printer.result()).toString());
		assertTrue(printer.result().toString()
				.equals("( \n" + "  ( set c \n" + "    ( Console new ) ) \n" + "  ( c print \"Hello World\" ) \n"
						+ "  ( \n" + "    ( Console new ) print \"il était une fois:\\n\\\"bla bla\\n\\\"\" ) )"));

		File f = new File("resources/sample4.sexp");
		nodes = reader.parse(builder, new FileReader(f));
		
		for (SNode sn : nodes) {
			printer = new SPrinter();
			printer.withIndentation(true);
			sn.accept(printer);
			System.out.println(printer.result().toString());
		}
	}

	@Test
	void test2() throws IOException {
		SParser<SNode> reader = new SParser<>();
		STreeBuilder<SNode> builder = new STreeBuilder<>();
		SPrinter printer;
		List<SNode> nodes;
		SNode n;
		
		nodes = reader.parse(builder, "(X) (Y)");
		assertTrue(nodes.size() == 2);
		printer = new SPrinter();
		n = nodes.get(0);
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( X )"));
		printer = new SPrinter();
		n = nodes.get(1);
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( Y )"));
		
	}
	@Test
	void testUtf8() throws IOException {
		SParser<SNode> reader = new SParser<>();
		STreeBuilder<SNode> builder = new STreeBuilder<>();
		SPrinter printer;
		List<SNode> nodes;
		SNode n;
		
		nodes = reader.parse(builder, "(éàèî) (€æ‡ÒÂê∂ê∂†ê®†Ì)");
		assertTrue(nodes.size() == 2);
		printer = new SPrinter();
		n = nodes.get(0);
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( éàèî )"));
		printer = new SPrinter();
		n = nodes.get(1);
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( €æ‡ÒÂê∂ê∂†ê®†Ì )"));
		
	}
	@Test
	void test3() throws IOException {
		SParser<SNode> reader = new SParser<>();
		STreeBuilder<SNode> builder = new STreeBuilder<>();
		SPrinter printer;
		List<SNode> nodes;
		SNode n;
				
		nodes = reader.parse(builder, "( space := ( Space new ) ) ( robi := ( Rect new ) ) ");
		assertTrue(nodes.size() == 2);
		printer = new SPrinter();
		n = nodes.get(0);
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( space := ( Space new ) )"));
		n = nodes.get(1);
		printer = new SPrinter();
		n.accept(printer);
		assertTrue(printer.result().toString().equals("( robi := ( Rect new ) )"));
	}

}
