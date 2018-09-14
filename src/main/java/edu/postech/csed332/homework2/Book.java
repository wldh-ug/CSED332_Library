package edu.postech.csed332.homework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A book will contain only the title and the author(s).
 * 
 * A book can be exported to and imported from a string representation. The format of the string is
 * of your choice, but by using the format, you should be able to construct a book object.
 * 
 * Do not reinvent the wheel. We strongly recommend that you use some library for XML, JSON, YML, or
 * similar format instead of writing your own parsing code.
 * 
 * While using the library requires adding it to pom.xml, it will be overall faster for you, likely
 * result in less buggy code, and you will get to learn more about Maven and Java.
 */
public final class Book extends Element {

	private String title;
	private Set<String> authors;

	/**
	 * Builds a book with the given title and authors.
	 *
	 * @param title   the title of the book
	 * @param authors the authors of the book
	 */
	public Book(String title, Set<String> authors) {

		// NOTE Implemented

		this.title = (title == null) ? "" : title;
		this.authors = (authors == null) ? new HashSet<>()
				: authors.stream().map(au -> (au == null) ? "" : au).collect(Collectors.toSet());

	}

	/**
	 * Builds a book from the string representation, which contains the title and authors of the
	 * book.
	 *
	 * @param stringRepresentation the string representation
	 */
	public Book(String stringRepresentation) {

		// NOTE Implemented

		if (stringRepresentation != null) {

			String[] splited = stringRepresentation.split("(?<!\\\\);");

			if (splited.length == 2) {

				this.title = unescape(splited[0]);
				this.authors = Arrays.asList(unescape(splited[1]).split("(?<!\\\\);")).stream()
						.map(au -> unescape(au)).collect(Collectors.toSet());

				return;

			}

		}

		this.title = "";
		this.authors = new HashSet<>();

	}

	/**
	 * Returns the string representation of the given book. The representation contains the title
	 * and authors of the book.
	 *
	 * @return the string representation
	 */
	public String getStringRepresentation() {
		
		// NOTE Implemented

		return escape(this.title) + ";" + escape(String.join(";",
				this.authors.stream().map(au -> escape(au)).collect(Collectors.toSet())));

	}

	/**
	 * Returns all the collections that this book belongs to directly and indirectly. Consider the
	 * following. (1) Computer Science is a collection. (2) Operating Systems is a collection under
	 * Computer Science. (3) The Linux Kernel is a book under Operating System collection. Then,
	 * getContainingCollections method for The Linux Kernel should return a list of these two
	 * collections (Computer Science, Operating System), starting from the top-level collection.
	 *
	 * @return the list of collections
	 */
	public List<Collection> getContainingCollections() {
		
		// NOTE Implemented

		ArrayList<Collection> result = new ArrayList<>();

		Collection tClctn = this.getParentCollection();

		while(tClctn != null) {

			result.add(tClctn);

			tClctn = tClctn.getParentCollection();

		}

		return result;

	}

	/**
	 * Returns the title of the book.
	 *
	 * @return the title
	 */
	public String getTitle() {
		
		// NOTE Implemented

		return this.title;

	}

	/**
	 * Returns the authors of the book.
	 *
	 * @return the authors
	 */
	public Set<String> getAuthor() {
		
		// NOTE Implemented

		return this.authors;

	}

	/**
	 * Escapes semicolons
	 * 
	 * @param original unescaped, original string
	 * @return escaped string
	 */
	private String escape(String original) {

		// NOTE Additional function

		return original.replaceAll(";", "\\\\;");

	}

	/**
	 * Unescapes semicolons
	 * 
	 * @param original escaped, original string
	 * @return unescaped string
	 */
	private String unescape(String original) {

		// NOTE Additional function

		return original.replaceAll("\\\\;", ";");

	}

}
