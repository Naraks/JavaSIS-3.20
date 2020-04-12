package pro.sisit.unit9.data;

import org.springframework.data.repository.CrudRepository;
import pro.sisit.unit9.entity.Buyer;

import java.util.List;

public interface BuyerRepository extends CrudRepository<Buyer, Long> {
    List<Buyer> findByName(String name);
}
