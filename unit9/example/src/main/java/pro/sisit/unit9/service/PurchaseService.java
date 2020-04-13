package pro.sisit.unit9.service;

import pro.sisit.unit9.entity.Book;
import pro.sisit.unit9.entity.Buyer;

import java.math.BigDecimal;

public interface PurchaseService {
    void makePurchase(Buyer buyer, BigDecimal cost, Book book);
}
