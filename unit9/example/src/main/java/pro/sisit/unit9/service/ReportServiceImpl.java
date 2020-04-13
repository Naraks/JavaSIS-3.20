package pro.sisit.unit9.service;

import org.springframework.stereotype.Service;
import pro.sisit.unit9.data.PurchasedBookRepository;
import pro.sisit.unit9.entity.Book;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.PurchasedBook;

import java.math.BigDecimal;

@Service
public class ReportServiceImpl implements ReportService{

    private final PurchasedBookRepository purchasedBookRepository;

    public ReportServiceImpl(PurchasedBookRepository purchasedBookRepository) {
        this.purchasedBookRepository = purchasedBookRepository;
    }

    @Override
    public BigDecimal countTotalCostOfPurchasesByBook(Book book) {
        return purchasedBookRepository.findByBook(book).stream()
                .map(PurchasedBook::getCost)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal countTotalCostOfPurchasedBookByBuyer(Buyer buyer) {
        return purchasedBookRepository.findByBuyer(buyer).stream()
                .map(PurchasedBook::getCost)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
