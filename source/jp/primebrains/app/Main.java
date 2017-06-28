package jp.primebrains.app;

import jp.primebrains.book.ArrayBookShelf;
import jp.primebrains.book.Book;
import jp.primebrains.book.BookShelf;
import jp.primebrains.utils.Iterator;

public class Main {
    public static void main(String[] args) {
        // BookShelf shelf = new ListBookShelf(4);

        BookShelf shelf = new ArrayBookShelf(4);

        shelf.append(new Book("Book A"));
        shelf.append(new Book("Book B"));
        shelf.append(new Book("Book C"));
        shelf.append(new Book("Book D"));

        Iterator iterator = shelf.iterator();
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(book.getName());
        }

    }
}