package il.ac.technion.cs.sd.book.test;

import static org.junit.Assert.*;

import org.junit.Test;

import il.ac.technion.cs.sd.book.app.BookScoreInitializer;
import il.ac.technion.cs.sd.book.app.BookScoreInitializerImpl;

public class StamExample {

	@Test
	public void test() {
		BookScoreInitializer b = new BookScoreInitializerImpl();
		b.setup("hello world");
	}

}
