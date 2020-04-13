package pro.sisit.unit9.service;

import pro.sisit.unit9.entity.Book;
import pro.sisit.unit9.entity.Buyer;

import java.math.BigDecimal;

public interface ReportService {
    BigDecimal countTotalCostOfPurchasesByBook(Book book);
    BigDecimal countTotalCostOfPurchasedBookByBuyer(Buyer buyer);
}
