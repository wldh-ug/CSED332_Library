package edu.postech.csed332.homework2;

import org.junit.jupiter.api.Test;

/**
 * Cover following methods,
 * Library.java
 * Library(String fileName)
 * saveLibraryToFile(String fileName)
 * Set<Book> findBooks(String collection)
 */
public class LibraryTest {

    @Test
    public void testLibraryConstructorFromFile1() {
        //TODO implement this
    }

    @Test
    public void testSaveLibraryToFile1() {
        
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
        clAP.addElement(clAP_D);
        clAP_D.addElement(clAP_D_PID);
        clBM.addElement(clBM_TC);
        clBM.addElement(clBM_ENB);

        // Register books to proper collection
        clAP.addElement(bookAP_1);
        clAP.addElement(bookAP_2);
        clAP_D.addElement(bookAP_D_1);
        clAP_D_PID.addElement(bookAP_D_PID_1);
        clBM.addElement(bookBM_1);
        clBM.addElement(bookBM_2);
        clBM_TC.addElement(bookBM_TC_1);
        clBM_TC.addElement(bookBM_TC_2);
        clBM_ENB.addElement(bookBM_ENB_1);
        clBM_ENB.addElement(bookBM_ENB_2);

        Library lib = new Library();

        lib.addCollection(clAP);
        lib.addCollection(clBM);

        lib.saveLibraryToFile(".\\save\\test.lib");

    }
    
    @Test
    public void testFindBooks1() {
        //TODO implement this
    }

}
