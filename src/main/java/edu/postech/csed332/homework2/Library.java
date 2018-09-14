package edu.postech.csed332.homework2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;

/**
 * Container class for all the collections (that eventually contain books). The library object is
 * just a container for all collections. A library can be exported to or imported from a file.
 * 
 * The format of the file is of your choice. Again, we strongly encourage using some library to
 * convert between Library objects and string representation.
 */
public final class Library {

	private List<Collection> collections;

	/**
	 * Builds a new, empty library.
	 */
	public Library() {
		// TODO implement this
	}

	/**
	 * Builds a new library and restores its contents from the given file.
	 *
	 * @param fileName the file from where to restore the library.
	 */
	public Library(String fileName) {
		// TODO implement this
	}

	/**
	 * Saved the contents of the library to the given file.
	 *
	 * @param fileName the file where to save the library
	 */
	public void saveLibraryToFile(String fileName) {
		// TODO implement this
	}

	/**
	 * Returns the collections contained in the library.
	 *
	 * @return library contained elements
	 */
	public List<Collection> getCollections() {
		// TODO implement this
	}

	/**
	 * Return the set of all the books that belong to the given collection in the Library. Return
	 * null if the given collection doesn't exist. Note that the name of the collection is unique
	 * and the findBooks should go through all the collections in the hierarchy of the given
	 * collection. Consider the following. (1) Computer Science is a collection. (2) The
	 * "Introduction of Computer Science" is a book under Computer Science. (3) Software Engineering
	 * is a collection under Computer Science. (4) The "Software design method" is a book under
	 * Software Engineering collection. (5) Development Methodology is a collection under Software
	 * Engineering. (6) The "Agile Programming" is a book under Development Methodology.
	 * 
	 * Then, findBooks method for the Computer Science should return a set of these three Book
	 * objects "Introduction of Computer Science", "Software design method", and "Agile
	 * Programming".
	 * 
	 * @param collection the collection that want to know the belonging books
	 * @return Return the set of the books that belong to the given collection
	 */
	public Set<Book> findBooks(String collection) {
		// TODO implement this
	}

}
