package edu.postech.csed332.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Cover following methods, Collection.java static Collection restoreCollection(String
 * stringRepresentation) // static method instead of constructor String getStringRepresentation()
 * boolean addElement(Element element) // see comments in code for some conditions boolean
 * deleteElement(Element element) // see comments in code for some conditions Set<Books>
 * findTitle(String author)
 */
public class CollectionTest {

	@DisplayName("Generating collections from SRs on normal case")
	@Test
	public void testRestoreCollection1() {

		// NOTE Implemented

		Collection clA = Collection.restoreCollection(
				"{\"type\":\"cl\",\"name\":\"Social\",\"els\":[{\"type\":\"book\",\"data\":\"아, 보람 따위 됐으니 야근수당이나 주세요;히노 에이타로\\\\;이소담\"}]}");
		Collection clB = Collection.restoreCollection(
				"{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]}");
		Collection clC = Collection.restoreCollection(
				"{\"type\":\"cl\",\"name\":\"History\",\"els\":[{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]},{\"type\":\"book\",\"data\":\"The Final Mission of Extortion 17: Special Ops, Helicopter Support, SEAL Team Six, and the Deadliest Day of the US War in Afghanista;Ed Darack\"}]}");



	}

	@DisplayName("Generating SRs from SRs")
	@Test
	public void testRestoreCollection2() {

		// NOTE Additional test suite

		assertEquals(
				"{\"type\":\"cl\",\"name\":\"Social\",\"els\":[{\"type\":\"book\",\"data\":\"아, 보람 따위 됐으니 야근수당이나 주세요;히노 에이타로\\\\;이소담\"}]}",
				Collection.restoreCollection(
						"{\"type\":\"cl\",\"name\":\"Social\",\"els\":[{\"type\":\"book\",\"data\":\"아, 보람 따위 됐으니 야근수당이나 주세요;히노 에이타로\\\\;이소담\"}]}")
						.getStringRepresentation());
		assertEquals(
				"{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]}",
				Collection.restoreCollection(
						"{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]}")
						.getStringRepresentation());
		assertEquals(
				"{\"type\":\"cl\",\"name\":\"History\",\"els\":[{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]},{\"type\":\"book\",\"data\":\"The Final Mission of Extortion 17: Special Ops, Helicopter Support, SEAL Team Six, and the Deadliest Day of the US War in Afghanista;Ed Darack\"}]}",
				Collection.restoreCollection(
						"{\"type\":\"cl\",\"name\":\"History\",\"els\":[{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]},{\"type\":\"book\",\"data\":\"The Final Mission of Extortion 17: Special Ops, Helicopter Support, SEAL Team Six, and the Deadliest Day of the US War in Afghanista;Ed Darack\"}]}")
						.getStringRepresentation());

	}

	@DisplayName("Generating collections from SRs on exceptional cases")
	@Test
	public void testRestoreCollection3() {

		// NOTE Additional test suite

		Collection.restoreCollection(null);
		Collection.restoreCollection("");
		Collection.restoreCollection("\n");
		Collection.restoreCollection("1290s");
		Collection.restoreCollection("][][[][][]{");
		Collection.restoreCollection("[null,null]");

	}

	@DisplayName("Generating SRs from ingredients on simple cases")
	@Test
	public void testGetStringRepresentation1() {

		// NOTE Implemented

		Book bookA = new Book("아, 보람 따위 됐으니 야근수당이나 주세요;히노 에이타로\\;이소담");

		Collection clA = new Collection("Social");
		clA.addElement(bookA);

		// ! Escaped must be \\\\ instead of \\ (Java String requires escape one more time)

		assertEquals(
				"{\"type\":\"cl\",\"name\":\"Social\",\"els\":[{\"type\":\"book\",\"data\":\"아, 보람 따위 됐으니 야근수당이나 주세요;히노 에이타로\\\\;이소담\"}]}",
				clA.getStringRepresentation());

	}

	@DisplayName("Generating SRs from ingredients on complex cases")
	@Test
	public void testGetStringRepresentation2() {

		// NOTE Implemented

		Book bookA = new Book(
				"The Final Mission of Extortion 17: Special Ops, Helicopter Support, SEAL Team Six, and the Deadliest Day of the US War in Afghanista;Ed Darack");
		Book bookB = new Book(
				"Robin Hood\\; His Deeds and Adventures as Recounted in the Old English Ballads\\;;Lucy Fitch\\;Comp Perkins");

		Collection clA = new Collection("History");
		Collection clB = new Collection("World");
		clA.addElement(clB);
		clA.addElement(bookA);
		clB.addElement(bookB);

		// ! Escaped must be \\\\ instead of \\ (Java String requires escape one more time)

		assertEquals(
				"{\"type\":\"cl\",\"name\":\"History\",\"els\":[{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]},{\"type\":\"book\",\"data\":\"The Final Mission of Extortion 17: Special Ops, Helicopter Support, SEAL Team Six, and the Deadliest Day of the US War in Afghanista;Ed Darack\"}]}",
				clA.getStringRepresentation());
		assertEquals(
				"{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]}",
				clB.getStringRepresentation());

	}

	@DisplayName("Appending elements on normal cases")
	@Test
	public void testAddElement1() {

		// NOTE Implemented

		// Book Collections
		Collection clAP = new Collection("Arts & Photography");
		Collection clAP_D = new Collection("Drawing");
		Collection clAP_D_PID = new Collection("Pen & Ink Drawing");
		Collection clBM = new Collection("Biographies & Memoirs");
		Collection clBM_TC = new Collection("True Crime");
		Collection clBM_ENB = new Collection("Ethnic & National Biographies");

		// Books
		Book bookAP_1 =
				new Book("Born a Crime: Stories from a South African Childhood;Trevor Noah");
		Book bookAP_2 = new Book(
				"Essential Elements for Strings: Book 1 with EEi (Violin);Michael Allen\\;Robert Gillespie\\;Pamela Tellejohn Hayes\\;John Higgins");
		Book bookAP_D_1 = new Book(
				"Adult Coloring Book : Stress Relieving Designs Animals, Mandalas, Flowers, Paisley Patterns And So Much More;Selah Works Prints");
		Book bookAP_D_PID_1 = new Book(
				"Lettering and Modern Calligraphy: A Beginner's Guide: Learn Hand Lettering and Brush Lettering;Paper Peony Press");
		Book bookBM_1 = new Book("Shade: A Tale of Two Presidents;Pete Souza");
		Book bookBM_2 =
				new Book("The 5 Love Languages: The Secret to Love that Lasts;Gary Chapman");
		Book bookBM_TC_1 = new Book(
				"Who Killed These Girls?: Cold Case: The Yogurt Shop Murders;Beverly Lowry");
		Book bookBM_TC_2 = new Book(
				"The Feather Thief: Beauty, Obsession, and the Natural History Heist of the Century;Kirk Wallace Johnson");
		Book bookBM_ENB_1 = new Book("Michelle Obama: A Photographic Journey;Antonia Felix");
		Book bookBM_ENB_2 = new Book(
				"The Reason I Jump: The Inner Voice of a Thirteen-Year-Old Boy with Autism;Naoki Higashida");

		// Structure collections
		assertTrue(clAP.addElement(clAP_D));
		assertTrue(clAP_D.addElement(clAP_D_PID));
		assertTrue(clBM.addElement(clBM_TC));
		assertTrue(clBM.addElement(clBM_ENB));

		// Register books to proper collection
		assertTrue(clAP.addElement(bookAP_1));
		assertTrue(clAP.addElement(bookAP_2));
		assertTrue(clAP_D.addElement(bookAP_D_1));
		assertTrue(clAP_D_PID.addElement(bookAP_D_PID_1));
		assertTrue(clBM.addElement(bookBM_1));
		assertTrue(clBM.addElement(bookBM_2));
		assertTrue(clBM_TC.addElement(bookBM_TC_1));
		assertTrue(clBM_TC.addElement(bookBM_TC_2));
		assertTrue(clBM_ENB.addElement(bookBM_ENB_1));
		assertTrue(clBM_ENB.addElement(bookBM_ENB_2));

		// Try to register book to unrelated collection
		assertFalse(clBM.addElement(bookAP_1));

		// Try to register non-root collection to unrelated collection
		assertFalse(clAP.addElement(clBM_TC));

		// Try to register book to uplevel collection (nonparent)
		assertFalse(clBM.addElement(bookBM_ENB_2));
		assertFalse(clBM_TC.addElement(bookBM_ENB_2));

		// Try to register book to uplevel collection (parent)
		assertTrue(clBM_ENB.addElement(bookBM_ENB_2));
		assertTrue(clAP.addElement(bookAP_1));

		// Try to register collection to uplevel collection (nonparent)
		assertFalse(clAP.addElement(clAP_D_PID));

		// Try to register collection to uplevel collection (parent)
		assertTrue(clAP.addElement(clAP_D));
		assertTrue(clBM.addElement(clBM_ENB));

		// Try to register book to downlevel collection
		assertFalse(clAP_D_PID.addElement(bookAP_1));

		// Try to register collection to downlevel collection
		assertFalse(clAP_D_PID.addElement(clAP));
		assertFalse(clAP_D.addElement(clAP));

		// Try to register book to samelevel collection
		assertFalse(clAP_D.addElement(bookAP_1));

		// Try to register collection to samelevel collection
		assertFalse(clBM_TC.addElement(clBM_ENB));

	}

	@DisplayName("Appending elements on unexpected cases")
	@Test
	public void testAddElement2() {

		// NOTE Additional test suite

		Collection cl = new Collection("Example");

		assertFalse(cl.addElement(null));
		assertFalse(cl.addElement(cl));

	}

	@DisplayName("Appending elements on duplicated cases")
	@Test
	public void testAddElement3() {

		// NOTE Additional test suite

		Collection cl = new Collection("Example");
		Collection clDP = new Collection("Example");
		Collection clA = new Collection("Example_A");
		Collection clADP = new Collection("Example_A");
		Book book = new Book("A;A");
		Book bookDP = new Book("A;A");

		// Duplicated self
		assertTrue(cl.addElement(clDP));

		// Duplicated collections
		cl.addElement(clA);
		assertTrue(cl.addElement(clADP));
		assertEquals(3, cl.getElements().size());

		// Duplicated books
		cl.addElement(book);
		assertTrue(cl.addElement(bookDP));
		assertEquals(5, cl.getElements().size());

	}

	@DisplayName("Removing elements on normal cases")
	@Test
	public void testDeleteElement1() {

		// NOTE Implemented

		// Book Collections
		Collection clAP = new Collection("Arts & Photography");

		// Books
		Book bookAP_1 =
				new Book("Born a Crime: Stories from a South African Childhood;Trevor Noah");
		Book bookAP_2 = new Book(
				"Essential Elements for Strings: Book 1 with EEi (Violin);Michael Allen\\;Robert Gillespie\\;Pamela Tellejohn Hayes\\;John Higgins");

		clAP.addElement(bookAP_1);
		clAP.addElement(bookAP_2);

		assertTrue(clAP.deleteElement(bookAP_1));
		assertFalse(clAP.deleteElement(bookAP_1));
		assertTrue(clAP.getElements().contains(bookAP_2));
		assertFalse(clAP.getElements().contains(bookAP_1));

	}

	@DisplayName("Removing elements on unexpected cases")
	@Test
	public void testDeleteElement2() {

		// NOTE Additional test suite

		Collection cl = new Collection("Example");

		assertFalse(cl.deleteElement(null));
		assertFalse(cl.deleteElement(cl));

	}

	@DisplayName("Finding title by author name")
	@Test
	public void testFindTitle() {

		// NOTE Implemented

		Collection clA = new Collection("ExampleA");
		Collection clB = new Collection("ExampleB");
		Collection clC = new Collection("ExampleC");
		Collection clD = new Collection("ExampleD");

		clA.addElement(clB);
		clA.addElement(clC);
		clC.addElement(clD);

		Book bookA = new Book("A;A");
		Book bookB = new Book("B;A");
		Book bookC = new Book("C;A");
		Book bookD = new Book("D;A");
		Book bookE = new Book("E;B");

		clA.addElement(bookA);
		clB.addElement(bookB);
		clC.addElement(bookC);
		clD.addElement(bookD);
		clD.addElement(bookE);

		Set<Book> books = clA.findTitle("A");

		assertTrue(books.contains(bookA));
		assertTrue(books.contains(bookB));
		assertTrue(books.contains(bookC));
		assertTrue(books.contains(bookD));
		assertFalse(books.contains(bookE));

	}

}
