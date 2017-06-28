package jp.primebrains.book;

import jp.primebrains.utils.Iterator;

public class BookShelfIterator implements Iterator {

    private BookShelf bookShelf;

    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        if (index < bookShelf.getLength()) {
            return true;
        }
        return false;
    }

    @Override
    public Book next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }

}
