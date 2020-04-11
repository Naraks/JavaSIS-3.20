package pro.sisit.unit9.service;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pro.sisit.unit9.SpringDataApplication;
import pro.sisit.unit9.data.*;
import pro.sisit.unit9.entity.Book;
import pro.sisit.unit9.entity.Buyer;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataApplication.class)
public class PurchaseServiceImplTest {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private PurchasedBookRepository purchasedBookRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void init(){
        Book book1 = new Book();
        book1.setTitle("Анжелика/Маркиза ангелов");
        book1.setDescription("Анжелика — дочь обедневшего дворянина из французской провинции Пуату...");
        book1.setYear(1956);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Путь в Версаль");
        book2.setDescription("Анжелика попадает в одну из парижских банд...");
        book2.setYear(1958);
        bookRepository.save(book2);

        Buyer buyer1 = new Buyer();
        buyer1.setName("Мария Кюри");
        buyer1.setAddress("г. Варшава, ул. Неизвестная, д.1");
        buyerRepository.save(buyer1);
    }

    @After
    public void clean(){
        purchasedBookRepository.deleteAll();
        bookRepository.deleteAll();
        buyerRepository.deleteAll();
    }

    @Test
    public void makePurchaseTest() {
        Buyer buyer = buyerRepository.findAll().stream().findFirst().orElse(null);
        BigDecimal cost = new BigDecimal(1);
        Book book1 = bookRepository.findAll().stream().findFirst().orElse(null);
        Book book2 = bookRepository.findAll().stream().findFirst().orElse(null);

        purchaseService.makePurchase(buyer, cost, book1);
        purchaseService.makePurchase(buyer, cost, book2);

        long count = purchasedBookRepository.count();
        Assert.assertEquals(2, count);
    }

    @Test
    public void makePurchaseBuyerNullTest(){
        Buyer buyer = null;
        BigDecimal cost = new BigDecimal(1);
        Book book = bookRepository.findAll().stream().findFirst().orElse(null);

        exception.expect(RuntimeException.class);
        exception.expectMessage("Null buyer in 'makePurchase' method");

        purchaseService.makePurchase(buyer, cost, book);
    }

    @Test
    public void makePurchaseCostNullTest(){
        Buyer buyer = buyerRepository.findAll().stream().findFirst().orElse(null);
        BigDecimal cost = null;
        Book book = bookRepository.findAll().stream().findFirst().orElse(null);

        exception.expect(RuntimeException.class);
        exception.expectMessage("Null cost in 'makePurchase' method");

        purchaseService.makePurchase(buyer, cost, book);
    }

    @Test
    public void makePurchaseBookNullTest(){
        Buyer buyer = buyerRepository.findAll().stream().findFirst().orElse(null);
        BigDecimal cost = new BigDecimal(1);
        Book book = null;

        exception.expect(RuntimeException.class);
        exception.expectMessage("Null book in 'makePurchase' method");

        purchaseService.makePurchase(buyer, cost, book);
    }
}