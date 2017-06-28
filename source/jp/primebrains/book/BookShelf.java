package jp.primebrains.book;

import jp.primebrains.utils.Iterable;

public interface BookShelf extends Iterable {

    Book getBookAt(int index);

    void append(Book book);

    int getLength();

}
