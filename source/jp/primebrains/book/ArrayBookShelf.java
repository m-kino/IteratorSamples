package jp.primebrains.book;

import jp.primebrains.utils.Iterator;

public class ArrayBookShelf implements BookShelf {

    private Book[] books;

    private int last;

    public ArrayBookShelf(int maxSize) {
        books = new Book[maxSize];
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookShelfIterator(this);
    }

    @Override
    public Book getBookAt(int index) {
        return books[index];
    }

    @Override
    public void append(Book book) {
        this.books[last] = book;
        last++;
    }

    @Override
    public int getLength() {
        return books.length;
    }
}
