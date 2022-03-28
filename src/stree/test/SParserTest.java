package stree.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import stree.parser.SParser;


class SParserTest {

	@Test
	void testSamples() throws IOException {
		SParser<Object> reader = new SParser<>();
		File f = new File("resources/sample1.sexp");
		reader.parse(new SParser.SHandler<>(){}, new FileReader(f));

		f = new File("resources/sample2.sexp");
		reader.parse(new SParser.SHandler<>(){}, new FileReader(f));
		
		f = new File("resources/sample3.sexp");
		reader.parse(new SParser.SHandler<>(){}, new FileReader(f));
		
		f = new File("resources/sample4.sexp");
		reader.parse(new SParser.SHandler<>(){}, new FileReader(f));
	}
	
}
