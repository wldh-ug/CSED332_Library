package edu.postech.csed332.homework2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

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
	private StandardPBEStringEncryptor enc;

	/**
	 * Builds a new, empty library.
	 */
	public Library() {

		// NOTE Implemented

		this.collections = new ArrayList<>();
		this.bin = new Kryo();
		this.enc = new StandardPBEStringEncryptor();

		bin.register(ArrayList.class);
		enc.setPassword(
				"jEAAACFBAAYAZsYmNozPE+QsIOSVyb9l+0wdD91frI6ucuodtBZnL9CN/66dRqJwBFRsbcKdMfvNkkMa5FRVRRWHBSjyKA7BXpn4ZlgxJ");

	}

	/**
	 * Builds a new library and restores its contents from the given file.
	 *
	 * @param fileName the file from where to restore the library.
	 */
	public Library(String fileName) {

		// NOTE Implemented

		this.bin = new Kryo();
		this.enc = new StandardPBEStringEncryptor();
		this.collections = new ArrayList<>();

		bin.register(ArrayList.class);
		enc.setPassword(
				"jEAAACFBAAYAZsYmNozPE+QsIOSVyb9l+0wdD91frI6ucuodtBZnL9CN/66dRqJwBFRsbcKdMfvNkkMa5FRVRRWHBSjyKA7BXpn4ZlgxJ");

		try {

			Input save = new Input(new FileInputStream(fileName));

			try {

				ArrayList<String> data = bin.readObject(save, ArrayList.class);

				Iterator<String> iter = data.iterator();

				while (iter.hasNext()) {
					this.collections
							.add(Collection.restoreCollection(this.enc.decrypt(iter.next())));
				}

			} catch (Exception e) {

				System.err.println("Libaray loading failed. Collections are empty.");
				System.err.println(e.toString());

			} finally {
				save.close();
			}

		} catch (Exception e) {

			System.err.println("File '" + fileName + "' does not exists. Libiary is empty.");
			this.collections = new ArrayList<>();

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

			try {

				// Prepare data
				Iterator<Collection> iter = this.collections.iterator();
				ArrayList<String> data = new ArrayList<>();

				while (iter.hasNext()) {
					data.add(this.enc.encrypt(iter.next().getStringRepresentation()));
				}

				// Save data
				bin.writeObject(save, data);

			} catch (Exception e) {

				System.err.println("Library save error occurred. Saving aborted.");
				System.err.println(e.toString());

			} finally {
				save.close();
			}

		} catch (Exception e) {

			System.err.println("File '" + fileName
					+ "' cannot be opened, failed to save. Check permissions and if target directory does not exists.");

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
	 * Adds the collection to the library.
	 *
	 * @param cl new collection
	 */
	public void addCollection(Collection cl) {

		// NOTE Additional function

		Iterator<Collection> iter = collections.iterator();

		while (iter.hasNext()) {
			if (iter.next().getName().equals(cl.getName())) {
				return;
			}
		}

		collections.add(cl);

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

		List<Collection> clList = new ArrayList<>(this.collections);
		List<Collection> fltdClList = new ArrayList<>();
		List<Collection> tempClList = new ArrayList<>();

		// Filter collections
		while (clList.size() > 0) {

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
							tempClList.add((Collection) item);
						}

					}

				}

			}

			clList = tempClList;
			tempClList = new ArrayList<>();

		}

		// Collect books
		while (fltdClList.size() > 0) {

			Iterator<Collection> iterFC = fltdClList.iterator();

			while (iterFC.hasNext()) {

				Iterator<Element> iterFE = iterFC.next().getElements().iterator();

				while (iterFE.hasNext()) {

					Element item = iterFE.next();

					if (item instanceof Book) {
						result.add((Book) item);
					} else {
						tempClList.add((Collection) item);
					}

				}

			}

			fltdClList = tempClList;
			tempClList = new ArrayList<>();

		}

		return result;

	}

}
