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
public class Book implements CSVBehavior {

    private String name;
    private String author;
    private String genre;
    private String isbn;

    @Override
    public List<String> getFieldsForCSV() {
        return Arrays.asList(name, author, genre, isbn);
    }

    @Override
    public void fillObjectFromCSVFields(List<String> fields) {
        if (fields == null) {
            throw new RuntimeException(String.format("Не удалось создать объект %s из csv файла", this.getClass().getSimpleName()));
        }
        this.name = Optional.ofNullable(fields.get(0)).orElseThrow(() -> new RuntimeException("Поле \"Name\" не должно быть пустым"));
        this.author = Optional.ofNullable(fields.get(1)).orElseThrow(() -> new RuntimeException("Поле \"Author\" не должно быть пустым"));
        this.genre = Optional.ofNullable(fields.get(2)).orElseThrow(() -> new RuntimeException("Поле \"Genre\" не должно быть пустым"));
        this.isbn = Optional.ofNullable(fields.get(3)).orElseThrow(() -> new RuntimeException("Поле \"ISBN\" не должно быть пустым"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return getName().equals(book.getName()) &&
            getAuthor().equals(book.getAuthor()) &&
            getGenre().equals(book.getGenre()) &&
            getIsbn().equals(book.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAuthor(), getGenre(), getIsbn());
    }
}
