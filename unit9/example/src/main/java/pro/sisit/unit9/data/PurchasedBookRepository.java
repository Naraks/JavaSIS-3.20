package pro.sisit.unit9.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import pro.sisit.unit9.entity.Book;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.PurchasedBook;

import java.util.List;

public interface PurchasedBookRepository extends CrudRepository<PurchasedBook, Long>,
                                        JpaRepository<PurchasedBook, Long>, JpaSpecificationExecutor<PurchasedBook> {
    List<PurchasedBook> findByBook(Book book);
    List<PurchasedBook> findByBuyer(Buyer buyer);
}
