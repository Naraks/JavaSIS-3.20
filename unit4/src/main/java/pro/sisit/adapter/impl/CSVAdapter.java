package pro.sisit.adapter.impl;

import java.io.*;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import pro.sisit.CSVBehavior;
import pro.sisit.adapter.IOAdapter;

@RequiredArgsConstructor
public class CSVAdapter<T extends CSVBehavior> implements IOAdapter<T> {

    private final Class<T> entityType;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    private final int charLimit = 100_000;
    private final String delimiter = ";";

    @Override
    public T read(int index) {
        T object = null;
        try {
            reader.mark(charLimit);
            checkIndex(index);
            setCursorToNeededLine(index);
            object = entityType.newInstance();
            object.fillObjectFromCSVFields(Arrays.asList(reader.readLine().split(delimiter)));
            reader.reset();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Не удалось прочитать строку по номеру %d", index));
        }
        return object;
    }

    @Override
    public int append(CSVBehavior entity) {
        int index = -1;
        try {
            index = countLinesInFile();
            if (index != 0) {
                writer.newLine();
            }
            for(String s : entity.getFieldsForCSV()) {
                writer.write(s);
                if (entity.getFieldsForCSV().indexOf(s) != entity.getFieldsForCSV().size() - 1) {
                    writer.write(delimiter);
                }
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Не удалось записать объект %s", entity.toString()));
        }
        return ++index;
    }

    private void setCursorToNeededLine(int index) throws IOException {
        for (int i = 0; i < index - 1; i++){
            reader.readLine();
        }
    }

    private void checkIndex(int index) throws IOException{
        if (index < 1 || index > countLinesInFile()) {
            throw new RuntimeException("В csv-файле нет записей по указанному индексу");
        }
    }

    private int countLinesInFile() throws IOException {
        reader.mark(charLimit);
        int count = 0;
        while (true) {
            String s = reader.readLine();
            if (s == null) {
                break;
            }
            count++;
        }
        reader.reset();
        return count;
    }
}
