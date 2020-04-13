package pro.sisit.unit9.service;

import org.springframework.stereotype.Service;
import pro.sisit.unit9.data.PurchasedBookRepository;
import pro.sisit.unit9.entity.Book;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.PurchasedBook;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class PurchaseServiceImpl implements PurchaseService{

    private final PurchasedBookRepository purchasedBookRepository;

    public PurchaseServiceImpl(PurchasedBookRepository purchasedBookRepository) {
        this.purchasedBookRepository = purchasedBookRepository;
    }

    @Override
    public void makePurchase(Buyer buyer, BigDecimal cost, Book book) {
        checkPurchase(buyer, cost, book);
        PurchasedBook purchasedBook = new PurchasedBook();
        purchasedBook.setBuyer(buyer);
        purchasedBook.setCost(cost);
        purchasedBook.setBook(book);
        purchasedBookRepository.save(purchasedBook);
    }

    private void checkPurchase(Buyer buyer, BigDecimal cost, Book book){
        Objects.requireNonNull(buyer, "Buyer required");
        Objects.requireNonNull(cost, "Cost required");
        Objects.requireNonNull(book, "Book required");
    }
}
