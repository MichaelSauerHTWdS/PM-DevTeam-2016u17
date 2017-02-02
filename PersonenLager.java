public class PersonenLager implements Iterable<Person> {

    private Person personen[];
    private int size;
    private int current;

    public PersonenLager(int s) {
        size = s;
        current = 0;
        personen = new Person[s];
    }

    public Person get(String name) {
        if(current == 0)
            return null;
        for(Person p : personen)
            if(p.getName() == name)
                return p;
        return null;
    }

    public Person add(Person p) {
        if(p == null)
            throw new RuntimeException("Person ist null");
        if(size == current)
            throw new RuntimeException("PersonenLager voll");
        personen[current++] = p;
        return p;
    }

    public Person remove(String name) {
        Person t = null;
        for(int i = 0; i < current; ++i) {
            if(personen[i].getName() == name) {
                t = personen[i];
                for(int j = i; j < size - 1; ++j) {
                    personen[j] = personen[j + 1];
                }
                personen[size - 1] = null;
                current = current - 1;
                return t;
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public int getCurrent() {
        return current;
    }

    public Person getByIndex(int i) {
        return personen[i];
    }

    public PersonenLagerIterator iterator() {
        return new PersonenLagerIterator(this);
    }

}

