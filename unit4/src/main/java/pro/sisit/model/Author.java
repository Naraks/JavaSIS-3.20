package pro.sisit.model;

import lombok.Getter;
import pro.sisit.CSVBehavior;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Author implements CSVBehavior {

    private String name;
    private String birthPlace;

    public Author(){}

    public Author(String name, String birthPlace) {
        this.name = name;
        this.birthPlace = birthPlace;
    }

    @Override
    public List<String> getFieldsForCSV() {
        return Arrays.asList(name, birthPlace);
    }

    @Override
    public void fillObjectFromCSVFields(List<String> fields) {
        this.name = fields.get(0);
        this.birthPlace = fields.get(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Author author = (Author) o;
        return getName().equals(author.getName()) &&
            getBirthPlace().equals(author.getBirthPlace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBirthPlace());
    }
}
