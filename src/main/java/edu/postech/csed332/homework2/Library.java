package edu.postech.csed332.homework2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.*;

/**
 * Container class for all the collections (that eventually contain books). The library object is
 * just a container for all collections. A library can be exported to or imported from a file.
 * 
 * The format of the file is of your choice. Again, we strongly encourage using some library to
 * convert between Library objects and string representation.
 */
public final class Library {

	private List<Collection> collections;
	private Kryo bin;

	/**
	 * Builds a new, empty library.
	 */
	public Library() {

		// NOTE Implemented

		this.collections = new ArrayList<>();
		this.bin = new Kryo();
		
		bin.register(List.class);

		System.out.println("Empty library created.");

	}

	/**
	 * Builds a new library and restores its contents from the given file.
	 *
	 * @param fileName the file from where to restore the library.
	 */
	public Library(String fileName) {
		
		// NOTE Implemented

		this.bin = new Kryo();

		bin.register(List.class);

		try {

			Input save = new Input(new FileInputStream(fileName));
			this.collections = bin.readObject(save, List.class);
			save.close();

			System.out.println("Library loaded from file '" + fileName + "'.");

		} catch (Exception e) {

			System.err.println("File '" + fileName + "' does not exists.");
			this.collections = new ArrayList<>();

			System.out.println("Empty library created.");

		}

	}

	/**
	 * Saved the contents of the library to the given file.
	 *
	 * @param fileName the file where to save the library
	 */
	public void saveLibraryToFile(String fileName) {

		// NOTE Implemented
		
		try {

			Output save = new Output(new FileOutputStream(fileName));
			bin.writeObject(save, this.collections);
			save.close();

			System.out.println("Library saved to file '" + fileName + "'.");

		} catch (Exception e) {

			System.err.println("File '" + fileName + "' cannot be opened, failed to save.");

		}

	}

	/**
	 * Returns the collections contained in the library.
	 *
	 * @return library contained elements
	 */
	public List<Collection> getCollections() {
		
		// NOTE Implemented

		return collections;

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
		
		// NOTE Implemented

		HashSet<Book> result = new HashSet<>();
		if (collection == null)
			return result;

		List<Collection> clList = new ArrayList<>(collections);

		// Filter collections
		List<Collection> fltdClList = new ArrayList<>();
		Iterator<Collection> iterC = clList.iterator();

		while (iterC.hasNext()) {

			Collection tClctn = iterC.next();

			if (tClctn.getName().equals(collection)) {

				fltdClList.add(tClctn);

			} else {

				Iterator<Element> iterE = tClctn.getElements().iterator();

				while (iterE.hasNext()) {

					Element item = iterE.next();

					if (item instanceof Collection) {

						clList.add((Collection) item);

					}

				}

			}

		}

		// Collect books
		Iterator<Collection> iterFC = fltdClList.iterator();

		while (iterFC.hasNext()) {

			Iterator<Element> iterFE = iterFC.next().getElements().iterator();

			while (iterFE.hasNext()) {

				Element item = iterFE.next();

				if (item instanceof Book) {

					result.add((Book) item);

				} else if (item instanceof Collection) {

					fltdClList.add((Collection) item);

				}

			}

		}

		return result;

	}

}
