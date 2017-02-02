import java.util.Iterator;

public class PersonenLagerIterator implements Iterator<Person> {

    private PersonenLager lager;
    private int i;

    public PersonenLagerIterator(PersonenLager l) {
        lager = l;
        i = 0;
    }

    public boolean hasNext() {
        return lager.getCurrent() < i;
    }

    public Person next() {
        return lager.getByIndex(i++);
    }

    public void remove() {
        throw new UnsupportedOperationException("nicht implementiert");
    }
}

