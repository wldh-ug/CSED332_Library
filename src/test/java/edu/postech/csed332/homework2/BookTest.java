package edu.postech.csed332.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Cover following methods,
 * Book.java
 * Book(String stringRepresentation) // regular constructor
 * String getStringRepresentation()
 * List<Collection> getContainingCollections()
 */
public class BookTest {

	@DisplayName("Test for creating book from String and Set")
	@Test
	public void testBookConstructor1() {
		
		// NOTE Implemented

		// Create book from string (Non ASCII Characters)
		Book bookA = new Book("아, 보람 따위 됐으니 야근수당이나 주세요",
				new HashSet<>(Arrays.asList(new String[] {"이소담", "히노 에이타로"})));

		// Create book from string with semicolons
		Book bookB = new Book(
				"Robin Hood; His Deeds and Adventures as Recounted in the Old English Ballads;",
				new HashSet<>(Arrays.asList(new String[] {"Lucy Fitch", "Comp Perkins"})));

		// Test for titles
		assertEquals("아, 보람 따위 됐으니 야근수당이나 주세요", bookA.getTitle());
		assertEquals(
				"Robin Hood; His Deeds and Adventures as Recounted in the Old English Ballads;",
				bookB.getTitle());

		// Test for authors
		assertTrue(bookA.getAuthor().contains("이소담"));
		assertTrue(bookB.getAuthor().contains("Comp Perkins"));
		assertFalse(bookB.getAuthor().contains("이소담"));

	}

	@DisplayName("Test for creating book from String representation")
	@Test
	public void testBookConstructor2() {

		// NOTE Additional test suite

		String[] params = {
			"돌이킬 수 없는 약속;야쿠마루 가쿠",
			"(시나공) 정보처리기사 실기(산업기사 포함)(2018);길벗R&D\\;한기준\\;김기윤\\;김정준\\;강윤석",
			"Robin Hood\\; His Deeds and Adventures as Recounted in the Old English Ballads\\;;Lucy Fitch\\;Comp Perkins",
			"Null Book;AuthorA\\;A\\\\;u\\\\;t\\\\;h\\\\;o\\\\;r\\\\;B"
		};

		String emptyString = "";

		for (int i = 0; i < params.length; i++) {

			Book book = new Book(params[i]);
			String authors = String.join(", ", book.getAuthor());

			System.out.println("Load book: <" + book.getTitle() + "> by " + authors);

			// Test title
			assertNotNull(book.getTitle());
			assertNotEquals(book.getTitle(), emptyString);

			// Test Authors
			assertNotNull(book.getAuthor());
			assertNotEquals(authors, emptyString);

		}

	}

	@DisplayName("Test for exceptional situations on creating book")
	@Test
	public void testBookConstructor3() {

		// NOTE Additional test suite
		// ! There's no duty for taking care of string representation of strance cases

		/* Test type A */

		List<String> titles = new ArrayList<String>() {{

			add(null);
			add("");
			add("\\;");
			add("\\\\");
			add("\\\\;");

		}};
		List<Set<String>> authors = new ArrayList<Set<String>>() {{

			add(null);
			add(new HashSet<>());
			add(new HashSet<String>() {{ add(""); add(" "); }});
			add(new HashSet<String>() {{ add(null); add(" "); }});

		}};

		Iterator<String> iterT = titles.iterator();

		while (iterT.hasNext()) {

			String tStr = iterT.next();
			Iterator<Set<String>> iterA = authors.iterator();

			while (iterA.hasNext()) {

				Book book = new Book(tStr, iterA.next());
				System.out.println("Load book: <" + book.getTitle() + "> by "
						+ String.join(", ", book.getAuthor()));

			}

		}

		/* Test type B */

		String[] params = {null, "", ";", "\\;", "\\\\;"};

		for (int i = 0; i < params.length; i++) {

			Book book = new Book(params[i]);
			System.out.println("Load book: <" + book.getTitle() + "> by "
					+ String.join(", ", book.getAuthor()));

		}

	}
	
	@DisplayName("Testing SR from string and set")
	@Test
	public void testGetStringRepresentation1() {
		
		// NOTE Implemented

		HashMap<String, Book> books = new HashMap<String, Book>() {{

			put("아, 보람 따위 됐으니 야근수당이나 주세요;히노 에이타로\\;이소담",
				new Book("아, 보람 따위 됐으니 야근수당이나 주세요",
						new HashSet<>(Arrays.asList(new String[] {"이소담", "히노 에이타로"}))));
			put("Robin Hood\\; His Deeds and Adventures as Recounted in the Old English Ballads\\;;Lucy Fitch\\;Comp Perkins",
				new Book(
						"Robin Hood; His Deeds and Adventures as Recounted in the Old English Ballads;",
						new HashSet<>(Arrays.asList(new String[] {"Lucy Fitch", "Comp Perkins"}))));

		}};

		Iterator<String> iter = books.keySet().iterator();

		while (iter.hasNext()) {

			String expected = iter.next();
			assertEquals(expected, books.get(expected).getStringRepresentation());

		}

	}

	@DisplayName("Testing SR from SR")
	@Test
	public void testGetStringRepresentation2() {

		// NOTE Additional test suite

		HashMap<String, Book> books = new HashMap<String, Book>() {
			{

				put("돌이킬 수 없는 약속;야쿠마루 가쿠", new Book("돌이킬 수 없는 약속;야쿠마루 가쿠"));
				put("(시나공) 정보처리기사 실기(산업기사 포함)(2018);강윤석\\;김기윤\\;한기준\\;길벗R&D\\;김정준",
						new Book("(시나공) 정보처리기사 실기(산업기사 포함)(2018);길벗R&D\\;한기준\\;김기윤\\;김정준\\;강윤석"));
				put("Robin Hood\\; His Deeds and Adventures as Recounted in the Old English Ballads\\;;Lucy Fitch\\;Comp Perkins",
						new Book(
								"Robin Hood\\; His Deeds and Adventures as Recounted in the Old English Ballads\\;;Lucy Fitch\\;Comp Perkins"));
				put("Null Book;AuthorA\\;A\\\\;u\\\\;t\\\\;h\\\\;o\\\\;r\\\\;B",
						new Book("Null Book;AuthorA\\;A\\\\;u\\\\;t\\\\;h\\\\;o\\\\;r\\\\;B"));

			}
		};

		Iterator<String> iter = books.keySet().iterator();

		while (iter.hasNext()) {

			String expected = iter.next();
			assertEquals(expected, books.get(expected).getStringRepresentation());

		}

	}

	@DisplayName("Testing collections: the simplest case")
	@Test
	public void testGetContainingCollections1() {
		//TODO implement this
	}

}
