package edu.postech.csed332.homework2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;

/**
 * The Collection class represents a single collection. It contains a name of the collection and
 * also has a list of references to every book and every sub-collection in that particular
 * collection.
 * 
 * A collection can be exported to and imported from a string representation. The format of the
 * string is of your choice, but the string representation should have the name of this collection
 * and all elements (books and sub-collections) contained in this collection.
 */
public final class Collection extends Element {

	List<Element> elements;
	private String name;

	/**
	 * Creates a new collection with the given name.
	 *
	 * @param name the name of the collection
	 */
	public Collection(String name) {

		// NOTE Implemented

		this.name = (name == null) ? "" : name;
		this.elements = new ArrayList<>();

	}

	/**
	 * Restores a collection from its given string representation.
	 *
	 * @param stringRepresentation the string representation
	 */
	public static Collection restoreCollection(String stringRepresentation) {

		// NOTE Implemented
		
		return (new Gson()).fromJson(stringRepresentation, Collection.class);

	}

	/**
	 * Returns the string representation of a collection. The string representation should have the
	 * name of this collection, and all elements (books/subcollections) contained in this
	 * collection.
	 *
	 * @return string representation of this collection
	 */
	public String getStringRepresentation() {
		
		// NOTE Implemented

		return (new Gson()).toJson(this);

	}

	/**
	 * Returns the name of the collection.
	 *
	 * @return the name
	 */
	public String getName() {

		// NOTE Implemented

		return this.name;

	}

	/**
	 * Adds an element to the collection. If parentCollection of given element is not null, do not
	 * actually add, but just return false. (explanation: if given element is already a part of
	 * another collection, you should have deleted the element from that collection before adding to
	 * another collection)
	 *
	 * @param element the element to add
	 * @return true on success, false on fail
	 */
	public boolean addElement(Element element) {

		// NOTE Implemented

		if (element != null && element.getParentCollection() == null) {

			try {

				element.setParentCollection(this);
				return this.elements.add(element);

			} catch (Exception e) {

				System.err.println(e.toString());

			}

		} else {

			System.err.println("Element is null or already have parent collection.");

		}

		return false;

	}

	/**
	 * Deletes an element from the collection. Returns false if the collection does not have this
	 * element. Hint: Do not forget to clear parentCollection of given element
	 *
	 * @param element the element to remove
	 * @return true on success, false on fail
	 */
	public boolean deleteElement(Element element) {

		// NOTE Implemented

		if (element != null && elements.contains(element)) {

			element.setParentCollection(null);
			elements.remove(element);

			return true;

		}

		return false;

	}

	/**
	 * Returns the list of elements.
	 * 
	 * @return the list of elements
	 */
	public List<Element> getElements() {

		// NOTE Implemented

		return elements;

	}

	/**
	 * Return the books that the given author wrote, by him/herself or as co-author, in this
	 * Collection. Return null if the given author didn't write anything. If the author wrote
	 * several books, return all the books.
	 *
	 * @param author the author that want to find
	 * @return Return the book titles that the given author wrote
	 */
	public Set<Book> findTitle(String author) {

		// NOTE Implemented

		HashSet<Book> result = new HashSet<>();

		if (author != null) {

			Iterator<Element> iter = this.elements.iterator();

			while (iter.hasNext()) {

				Element one = iter.next();

				if (one instanceof Book) {

					Book book = (Book) one;

					if (book.getAuthor().contains(author)) {

						result.add(book);
						System.out.println("Book <" + book.getTitle() + "> found.");

					}

				} else if (one instanceof Collection) {

					Collection cl = (Collection) one;
					result.addAll(cl.findTitle(author));

				} else {

					System.err.println("Failed to analyze type of an element.");

				}

			}

		}

		return result;

	}

}
