package pro.sisit.unit9.service;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pro.sisit.unit9.SpringDataApplication;
import pro.sisit.unit9.data.BookRepository;
import pro.sisit.unit9.data.BuyerRepository;
import pro.sisit.unit9.data.PurchasedBookRepository;
import pro.sisit.unit9.entity.Book;
import pro.sisit.unit9.entity.Buyer;
import pro.sisit.unit9.entity.PurchasedBook;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDataApplication.class)
public class ReportServiceImplTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private PurchasedBookRepository purchasedBookRepository;

    @Before
    public void init() {
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

        Book book3 = new Book();
        book3.setTitle("Анжелика и король");
        book3.setDescription("Выйдя замуж за маркиза Филиппа дю Плесси-Бельера, Анжелика стремится занять достойное ее положение в высшем свете...");
        book3.setYear(1959);
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setTitle("Неукротимая Анжелика");
        book4.setDescription("В поисках мужа Анжелика отправляется на Средиземное море, где затерялись его следы...");
        book4.setYear(1960);
        bookRepository.save(book4);

        Book book5 = new Book();
        book5.setTitle("Бунтующая Анжелика");
        book5.setDescription("Арестованную Анжелику привозят в замок дю Плесси, где под присмотром слуг живёт её младший сын от Филиппа — Шарль-Анри...");
        book5.setYear(1961);
        bookRepository.save(book5);

        Buyer buyer1 = new Buyer();
        buyer1.setName("Мария Кюри");
        buyer1.setAddress("г. Варшава");
        buyerRepository.save(buyer1);

        Buyer buyer2 = new Buyer();
        buyer2.setName("Станислав Лем");
        buyer2.setAddress("г. Львов");
        buyerRepository.save(buyer2);

        Buyer buyer3 = new Buyer();
        buyer3.setName("Александр Македонский");
        buyer3.setAddress("г. Пелла");
        buyerRepository.save(buyer3);

        Buyer buyer4 = new Buyer();
        buyer4.setName("Неизвестный фанат");
        buyer4.setAddress("г. Красноярск");
        buyerRepository.save(buyer4);

        PurchasedBook purchasedBook1 = new PurchasedBook();
        purchasedBook1.setBook(book1);
        purchasedBook1.setCost(new BigDecimal(100));
        purchasedBook1.setBuyer(buyer1);
        purchasedBookRepository.save(purchasedBook1);

        PurchasedBook purchasedBook2 = new PurchasedBook();
        purchasedBook2.setBook(book2);
        purchasedBook2.setCost(new BigDecimal(200));
        purchasedBook2.setBuyer(buyer2);
        purchasedBookRepository.save(purchasedBook2);

        PurchasedBook purchasedBook3 = new PurchasedBook();
        purchasedBook3.setBook(book3);
        purchasedBook3.setCost(new BigDecimal(500));
        purchasedBook3.setBuyer(buyer2);
        purchasedBookRepository.save(purchasedBook3);

        PurchasedBook purchasedBook4 = new PurchasedBook();
        purchasedBook4.setBook(book4);
        purchasedBook4.setCost(new BigDecimal(125));
        purchasedBook4.setBuyer(buyer4);
        purchasedBookRepository.save(purchasedBook4);

        PurchasedBook purchasedBook5 = new PurchasedBook();
        purchasedBook5.setBook(book4);
        purchasedBook5.setCost(new BigDecimal(130));
        purchasedBook5.setBuyer(buyer4);
        purchasedBookRepository.save(purchasedBook5);
    }

    @After
    public void clean() {
        purchasedBookRepository.deleteAll();
        bookRepository.deleteAll();
        buyerRepository.deleteAll();
    }

    @Test
    public void countTotalCostOfPurchasesByBookTest(){
        Assert.assertEquals(new BigDecimal(0),
                reportService.countTotalCostOfPurchasesByBook(
                        bookRepository.findByTitle("Бунтующая Анжелика")
                                .stream()
                                .findFirst()
                                .orElse(null)));

        Assert.assertEquals(new BigDecimal("255.00"),
                reportService.countTotalCostOfPurchasesByBook(
                        bookRepository.findByTitle("Неукротимая Анжелика")
                                .stream()
                                .findFirst()
                                .orElse(null)));
    }

    @Test
    public void countTotalCostOfPurchasedBookByBuyerTest() {
        Assert.assertEquals(new BigDecimal(0),
                reportService.countTotalCostOfPurchasedBookByBuyer(
                        buyerRepository.findByName("Александр Македонский")
                                .stream()
                                .findFirst()
                                .orElse(null)));

        Assert.assertEquals(new BigDecimal("700.00"),
                reportService.countTotalCostOfPurchasedBookByBuyer(
                        buyerRepository.findByName("Станислав Лем")
                                .stream()
                                .findFirst()
                                .orElse(null)));

        Assert.assertEquals(new BigDecimal("100.00"),
                reportService.countTotalCostOfPurchasedBookByBuyer(
                        buyerRepository.findByName("Мария Кюри")
                                .stream()
                                .findFirst()
                                .orElse(null)));
    }
}