package pro.sisit.adapter;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pro.sisit.adapter.impl.CSVAdapter;
import pro.sisit.model.Author;
import pro.sisit.model.Book;
import pro.sisit.model.BookShop;

public class CSVAdapterTest {

    @Before
    public void createFile() {
        // Создать и заполнить csv-файл для сущности Author
        Author a0 = new Author("Пушкин А.С.", "Российская империя, Москва");
        Author a1 = new Author("Лермонтов М.Ю.", "Российская империя, Москва");
        Author a2 = new Author("Тютчев Ф.И.", "Российская империя, Овстуг");
        Author a3 = new Author("Бунин И.А.", "Российская империя, Воронеж");

        File authorsFile = Paths.get("test-author-file.csv").toFile();
        try {
            authorsFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader r = new BufferedReader(new FileReader(authorsFile));
             BufferedWriter w = new BufferedWriter(new FileWriter(authorsFile, true))) {
            IOAdapter<Author> adapter = new LogIOAdapter<>(new CSVAdapter<>(Author.class, r, w));
            adapter.append(a0);
            adapter.append(a1);
            adapter.append(a2);
            adapter.append(a3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Создать и заполнить csv-файл для сущности Book
        Book b0 = new Book("Снятся ли андроидам электроовцы", "Филип Дик", "научная фантастика", "5-85256-001-4");
        Book b1 = new Book("Цветы для Элджернона", "Daniel Keyes", "научная фантастика", "5-699-04661-5");
        Book b2 = new Book("451 градус по Фаренгейту", "Рэй Бредбери", "антиутопия", "");
        Book b3 = new Book("Хроники Амбера", "Роджер Желязны", "фэнтези", "978-5-699-17665-6");

        File booksFile = Paths.get("test-book-file.csv").toFile();
        try {
            booksFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader r = new BufferedReader(new FileReader(booksFile));
             BufferedWriter w = new BufferedWriter(new FileWriter(booksFile, true))) {
            IOAdapter<Book> adapter = new LogIOAdapter<>(new CSVAdapter<>(Book.class, r, w));
            adapter.append(b0);
            adapter.append(b1);
            adapter.append(b2);
            adapter.append(b3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Создать и заполнить csv-файл для сущности BookShop
        BookShop bs0 = new BookShop("ул. Котяткина, 42", "Бумажная радость", "09:00 - 18:00");
        BookShop bs1 = new BookShop("ул. Мопсяткина, 12", "Чтун", "09:30 - 18:30");
        BookShop bs2 = new BookShop("ул. Ленина, 122", "Золотая обложка", "10:00 - 20:00");
        BookShop bs3 = new BookShop("пер. Дорожный, 14б", "Литература для ценителей", "12:00 - 01:00");

        File bookShopsFile = Paths.get("test-bookshop-file.csv").toFile();
        try {
            bookShopsFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader r = new BufferedReader(new FileReader(bookShopsFile));
             BufferedWriter w = new BufferedWriter(new FileWriter(bookShopsFile, true))) {
            IOAdapter<BookShop> adapter = new LogIOAdapter<>(new CSVAdapter<>(BookShop.class, r, w));
            adapter.append(bs0);
            adapter.append(bs1);
            adapter.append(bs2);
            adapter.append(bs3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void deleteFile() {
        Paths.get("test-author-file.csv").toFile().delete();
        Paths.get("test-book-file.csv").toFile().delete();
        Paths.get("test-bookshop-file.csv").toFile().delete();
    }

    @Test
    public void testReadBook() throws IOException {

        Path bookFilePath = Paths.get("test-book-file.csv");

        BufferedReader bookReader = new BufferedReader(
                new FileReader(bookFilePath.toFile()));

        BufferedWriter bookWriter = new BufferedWriter(
                new FileWriter(bookFilePath.toFile(), true));

        IOAdapter<Book> bookCsvAdapter =
                new LogIOAdapter<>(
                        new CSVAdapter<>(Book.class, bookReader, bookWriter));

        Book book1 = bookCsvAdapter.read(1);
        assertEquals("Филип Дик", book1.getAuthor());
        assertEquals("Снятся ли андроидам электроовцы", book1.getName());
        assertEquals("5-85256-001-4", book1.getIsbn());
        assertEquals("научная фантастика", book1.getGenre());

        Book expectedBook0 = new Book(
                "Цветы для Элджернона",
                "Daniel Keyes",
                "научная фантастика",
                "5-699-04661-5");
        Book actualBook0 = bookCsvAdapter.read(2);
        assertEquals(expectedBook0, actualBook0);

        bookReader.close();
        bookWriter.close();
    }

    @Test
    public void testReadAuthor() throws IOException {
        Path authorFilePath = Paths.get("test-author-file.csv");

        BufferedReader authorReader = new BufferedReader(
                new FileReader(authorFilePath.toFile()));

        BufferedWriter authorWriter = new BufferedWriter(
                new FileWriter(authorFilePath.toFile(), true));

        IOAdapter<Author> authorCsvAdapter =
                new LogIOAdapter<>(
                        new CSVAdapter<>(Author.class, authorReader, authorWriter));

        Author author1 = authorCsvAdapter.read(1);
        assertEquals("Пушкин А.С.", author1.getName());
        assertEquals("Российская империя, Москва", author1.getBirthPlace());

        Author expectedAuthor0 = new Author(
                "Лермонтов М.Ю.",
                "Российская империя, Москва");
        Author actualAuthor0 = authorCsvAdapter.read(2);
        assertEquals(expectedAuthor0, actualAuthor0);

        authorReader.close();
        authorWriter.close();
    }

    @Test
    public void testReadBookShop() throws IOException {
        Path bookshopFilePath = Paths.get("test-bookshop-file.csv");

        BufferedReader bookshopReader = new BufferedReader(
                new FileReader(bookshopFilePath.toFile()));

        BufferedWriter bookshopWriter = new BufferedWriter(
                new FileWriter(bookshopFilePath.toFile(), true));

        IOAdapter<BookShop> bookshopCsvAdapter =
                new LogIOAdapter<>(
                        new CSVAdapter<>(BookShop.class, bookshopReader, bookshopWriter));

        BookShop bookShop1 = bookshopCsvAdapter.read(1);
        assertEquals("ул. Котяткина, 42", bookShop1.getAddress());
        assertEquals("Бумажная радость", bookShop1.getName());
        assertEquals("09:00 - 18:00", bookShop1.getWorkingHours());

        BookShop expectedBookShop0 = new BookShop(
                "ул. Мопсяткина, 12",
                "Чтун",
                "09:30 - 18:30");
        BookShop actualBookShop0 = bookshopCsvAdapter.read(2);
        assertEquals(expectedBookShop0, actualBookShop0);

        bookshopReader.close();
        bookshopWriter.close();
    }

    @Test
    public void testAppendBook() throws IOException {
        Path bookFilePath = Paths.get("test-book-file.csv");

        BufferedReader bookReader = new BufferedReader(
                new FileReader(bookFilePath.toFile()));

        BufferedWriter bookWriter = new BufferedWriter(
                new FileWriter(bookFilePath.toFile(), true));

        IOAdapter<Book> bookCsvAdapter =
                new LogIOAdapter<>(
                        new CSVAdapter<>(Book.class, bookReader, bookWriter));

        Book newBook = new Book(
                "Чертоги разума. Убей в себе идиота!",
                "Андрей Курпатов",
                "Психология",
                "978-5-906902-91-7");

        int bookIndex = bookCsvAdapter.append(newBook);
        Book bookAtIndex = bookCsvAdapter.read(bookIndex);
        assertEquals(newBook, bookAtIndex);

        bookReader.close();
        bookWriter.close();
    }

    @Test
    public void testAppendBookShop() throws IOException {
        Path bookShopFilePath = Paths.get("test-bookshop-file.csv");

        BufferedReader bookShopReader = new BufferedReader(
                new FileReader(bookShopFilePath.toFile()));

        BufferedWriter bookShopWriter = new BufferedWriter(
                new FileWriter(bookShopFilePath.toFile(), true));

        IOAdapter<BookShop> bookShopCsvAdapter =
                new LogIOAdapter<>(
                        new CSVAdapter<>(BookShop.class, bookShopReader, bookShopWriter));

        BookShop newBookShop = new BookShop(
                "ул. Кочерыжкинв",
                "Мир сказок",
                "10:00 - 18:00");

        int bookShopIndex = bookShopCsvAdapter.append(newBookShop);
        BookShop bookShopAtIndex = bookShopCsvAdapter.read(bookShopIndex);
        assertEquals(newBookShop, bookShopAtIndex);

        bookShopReader.close();
        bookShopWriter.close();
    }

    @Test
    public void testAppendAuthor() throws IOException {
        Path authorFilePath = Paths.get("test-author-file.csv");

        BufferedReader authorReader = new BufferedReader(
                new FileReader(authorFilePath.toFile()));

        BufferedWriter authorWriter = new BufferedWriter(
                new FileWriter(authorFilePath.toFile(), true));

        IOAdapter<Author> authorCsvAdapter =
                new LogIOAdapter<>(
                        new CSVAdapter<>(Author.class, authorReader, authorWriter));

        Author newAuthor = new Author(
                "Гиппиус З.Н.",
                "Белёв");

        int authorIndex = authorCsvAdapter.append(newAuthor);
        Author authorAtIndex = authorCsvAdapter.read(authorIndex);
        assertEquals(newAuthor, authorAtIndex);

        authorReader.close();
        authorWriter.close();
    }
}
