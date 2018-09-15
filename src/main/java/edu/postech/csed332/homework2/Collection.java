package edu.postech.csed332.homework2;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

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

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Element.class, new JsonDeserializer<Element>() {

					@Override
					public Element deserialize(final JsonElement json, final Type type,
							JsonDeserializationContext cxt) throws JsonParseException {

						JsonObject o = json.getAsJsonObject();
						String oType = o.get("type").getAsString();

						if (oType.equals("cl")) {

							Collection cl = new Collection(o.get("name").getAsString());

							Iterator<JsonElement> iter = o.get("els").getAsJsonArray().iterator();

							while (iter.hasNext()) {
								cl.addElement(cxt.deserialize(iter.next(), Element.class));
							}

							return cl;

						} else if (oType.equals("book")) {
							return new Book(o.get("data").getAsString());
						} else {
							throw new JsonParseException("Unknown object type.");
						}

					}

				}).create();

		try {

			return (Collection) gson.fromJson(
					(stringRepresentation == null) ? "" : stringRepresentation, Element.class);

		} catch (Exception e) {

			return new Collection("");

		}

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

		Gson gson =
				new GsonBuilder().registerTypeAdapter(Element.class, new JsonSerializer<Element>() {

					@Override
					public JsonElement serialize(final Element el, final Type type,
							final JsonSerializationContext cxt) {

						final JsonObject rs = new JsonObject();

						if (el instanceof Collection) {

							Collection cl = (Collection) el;
							JsonArray els = new JsonArray();

							Iterator<Element> iter = cl.elements.iterator();

							while (iter.hasNext()) {
								els.add(cxt.serialize(iter.next(), Element.class));
							}

							rs.addProperty("type", "cl");
							rs.addProperty("name", cl.getName());
							rs.add("els", els);

						} else if (el instanceof Book) {

							Book book = (Book) el;

							rs.addProperty("type", "book");
							rs.addProperty("data", book.getStringRepresentation());

						}

						return rs;

					}

				}).create();

		return gson.toJson(this, Element.class);

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
