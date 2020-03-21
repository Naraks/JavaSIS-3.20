package pro.sisit.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pro.sisit.CSVBehavior;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author implements CSVBehavior {

    private String name;
    private String birthPlace;

    @Override
    public List<String> getFieldsForCSV() {
        return Arrays.asList(name, birthPlace);
    }

    @Override
    public void fillObjectFromCSVFields(List<String> fields) {
        if (fields == null) {
            throw new RuntimeException(String.format("Не удалось создать объект %s из csv файла", this.getClass().getSimpleName()));
        }
        this.name = Optional.ofNullable(fields.get(0)).orElseThrow(() -> new RuntimeException("Поле \"Name\" не должно быть пустым"));
        this.birthPlace = Optional.ofNullable(fields.get(1)).orElseThrow(() -> new RuntimeException("Поле \"BirthPlace\" не должно быть пустым"));
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
