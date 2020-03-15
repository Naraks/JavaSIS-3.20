package pro.sisit.model;

import lombok.Getter;
import pro.sisit.CSVBehavior;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Buyer implements CSVBehavior {

    private String name;
    private String cardNumber;
    private List<Book> purchasedBooks;

    public Buyer(){}

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
        this.name = fields.get(0);
        this.cardNumber = fields.get(1);
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
