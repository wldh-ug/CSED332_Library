package edu.postech.csed332.homework2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A book will contain only the title and the author(s).
 * 
 * A book can be exported to and imported from a string representation. 
 * The format of the string is of your choice, but by using the format, 
 * you should be able to construct a book object.
 * 
 * Do not reinvent the wheel. We strongly recommend that 
 * you use some library for XML, JSON, YML, or similar format 
 * instead of writing your own parsing code.
 * 
 * While using the library requires adding it to pom.xml, 
 * it will be overall faster for you, likely result in less buggy code, 
 * and you will get to learn more about Maven and Java.
 */
public final class Book extends Element {
    private String title;
    private Set<String> authors;

    /**
     * Builds a book with the given title and authors.
     *
     * @param title the title of the book
     * @param authors the authors of the book
     */
    public Book(String title, Set<String> authors) {
		// TODO implement this
    }

    /**
     * Builds a book from the string representation, 
     * which contains the title and authors of the book. 
     *
     * @param stringRepresentation the string representation
     */
    public Book(String stringRepresentation) {
		// TODO implement this
    }

    /**
     * Returns the string representation of the given book.
     * The representation contains the title and authors of the book.
     *
     * @return the string representation
     */
    public String getStringRepresentation() {
		// TODO implement this
    }

    /**
     * Returns all the collections that this book belongs to directly and indirectly.
     * Consider the following. 
     * (1) Computer Science is a collection. 
     * (2) Operating Systems is a collection under Computer Science. 
     * (3) The Linux Kernel is a book under Operating System collection. 
     * Then, getContainingCollections method for The Linux Kernel should return a list 
     * of these two collections (Computer Science, Operating System), starting from 
     * the top-level collection.
     *
     * @return the list of collections
     */
    public List<Collection> getContainingCollections() {
		// TODO implement this
    }

    /**
     * Returns the title of the book.
     *
     * @return the title
     */
    public String getTitle() {
		// TODO implement this
    }

    /**
     * Returns the authors of the book.
     *
     * @return the authors
     */
    public Set<String> getAuthor() {
		// TODO implement this
    }
    
}