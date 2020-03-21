package pro.sisit.adapter;

import pro.sisit.CSVBehavior;

public interface IOAdapter<T extends CSVBehavior> {

    T read(int index);

    int append(T entity);
}
