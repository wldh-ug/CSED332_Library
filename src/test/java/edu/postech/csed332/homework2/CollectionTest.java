package edu.postech.csed332.homework2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Cover following methods,
 * Collection.java
 * static Collection restoreCollection(String stringRepresentation) // static method instead of constructor
 * String getStringRepresentation()
 * boolean addElement(Element element) // see comments in code for some conditions
 * boolean deleteElement(Element element) // see comments in code for some conditions
 * Set<Books> findTitle(String author)
 */
public class CollectionTest {

	@DisplayName("Testing of generating collections from SR (normal case)")
	@Test
	public void testRestoreCollection1() {
		//TODO implement this
	}

	@DisplayName("Testing of generating collections and SR of them from string and list")
	@Test
	public void testGetStringRepresentation1() {

		// NOTE Implemented
		
		Book bookA = new Book("아, 보람 따위 됐으니 야근수당이나 주세요;히노 에이타로\\;이소담");
		Book bookB = new Book(
				"The Final Mission of Extortion 17: Special Ops, Helicopter Support, SEAL Team Six, and the Deadliest Day of the US War in Afghanista;Ed Darack");
		Book bookC = new Book(
				"Robin Hood\\; His Deeds and Adventures as Recounted in the Old English Ballads\\;;Lucy Fitch\\;Comp Perkins");

		Collection clA = new Collection("Social");
		clA.addElement(bookA);

		Collection clB = new Collection("History");
		Collection clC = new Collection("World");
		clB.addElement(clC);
		clB.addElement(bookB);
		clC.addElement(bookC);

		// ! Escaped must be \\\\ instead of \\ (Java String requires escape one more time)

		assertEquals(
				"{\"type\":\"cl\",\"name\":\"Social\",\"els\":[{\"type\":\"book\",\"data\":\"아, 보람 따위 됐으니 야근수당이나 주세요;히노 에이타로\\\\;이소담\"}]}",
				clA.getStringRepresentation());
		assertEquals(
				"{\"type\":\"cl\",\"name\":\"History\",\"els\":[{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]},{\"type\":\"book\",\"data\":\"The Final Mission of Extortion 17: Special Ops, Helicopter Support, SEAL Team Six, and the Deadliest Day of the US War in Afghanista;Ed Darack\"}]}",
				clB.getStringRepresentation());
		assertEquals(
				"{\"type\":\"cl\",\"name\":\"World\",\"els\":[{\"type\":\"book\",\"data\":\"Robin Hood\\\\; His Deeds and Adventures as Recounted in the Old English Ballads\\\\;;Lucy Fitch\\\\;Comp Perkins\"}]}",
				clC.getStringRepresentation());

	}

	@Test
	public void testAddElement1() {
		//TODO implement this
	}

	@Test
	public void testDeleteElement1() {
		//TODO implement this
	}
	
	@Test
	public void testFindTitle() {
			//TODO implement this
	}

}
