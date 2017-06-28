package jp.primebrains.book;

import java.util.ArrayList;
import java.util.List;

import jp.primebrains.utils.Iterator;

public class ListBookShelf implements BookShelf {

    private List<Book> books;

    public ListBookShelf(int maxSize) {
        books = new ArrayList<Book>();
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookShelfIterator(this);
    }

    @Override
    public Book getBookAt(int index) {
        return books.get(index);
    }

    @Override
    public void append(Book book) {
        this.books.add(book);
    }

    @Override
    public int getLength() {
        return books.size();
    }
}
