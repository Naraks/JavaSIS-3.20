package pro.sisit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pro.sisit.CSVBehavior;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class Buyer implements CSVBehavior {

    private String name;
    private String cardNumber;
    private List<Book> purchasedBooks;

    public Buyer(String name, String cardNumber){
        this.name = name;
        this.cardNumber = cardNumber;
    }

    public void addPurchasedBook(Book book){
        purchasedBooks.add(book);
    }

    @Override
    public List<String> getFieldsForCSV() {
        return Arrays.asList(name, cardNumber);
    }

    @Override
    public void fillObjectFromCSVFields(List<String> fields) {
        if (fields == null) {
            throw new RuntimeException(String.format("Не удалось создать объект %s из csv файла", this.getClass().getSimpleName()));
        }
        this.name = Optional.ofNullable(fields.get(0)).orElseThrow(() -> new RuntimeException("Поле \"Name\" не должно быть пустым"));
        this.cardNumber = Optional.ofNullable(fields.get(1)).orElseThrow(() -> new RuntimeException("Поле \"CardNumber\" не должно быть пустым"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Buyer buyer = (Buyer) o;
        return getName().equals(buyer.getName()) &&
                getCardNumber().equals(buyer.getCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCardNumber());
    }
}
