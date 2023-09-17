package modernJava.iterator;

public interface FirstIterator<E> {
    boolean hasNext();
    void next();
    E currentItem();
}
