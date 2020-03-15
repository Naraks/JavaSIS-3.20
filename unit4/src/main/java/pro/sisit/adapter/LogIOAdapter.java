package pro.sisit.adapter;

import pro.sisit.CSVBehavior;

public class LogIOAdapter<T extends CSVBehavior> implements IOAdapter<T> {

    private IOAdapter<T> adapter;

    public LogIOAdapter(IOAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T read(int index) {
        System.out.printf("Read data from index %d%n", index);
        return adapter.read(index);
    }

    @Override
    public int append(T entity) {
        System.out.printf("Append entity: %s%n", entity.getClass().getSimpleName());
        return adapter.append(entity);
    }
}
