package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;
import ru.netology.exception.NotFoundException;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ProductManagerTest {

    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book item1 = new Book(1, "Война и Мир", 1000, "Толстой");
    private Book item2 = new Book(2, "Идиот", 1200, "Достоевский");
    private Book item3 = new Book(3, "Капитанская дочка", 707, "Пушкин");
    private Smartphone item4 = new Smartphone(4, "Samsung", 12500, "Корея");
    private Smartphone item5 = new Smartphone(5, "Huawei", 15000, "Китай");
    private Smartphone item6 = new Smartphone(6, "Apple", 55000, "США");



    @BeforeEach
    public void setUp() {
        manager.add(item1);
        manager.add(item2);
        manager.add(item3);
        manager.add(item4);
        manager.add(item5);
        manager.add(item6);

    }

    @Test
    void shouldNotSearchBy() {
        Product[] expected = new Product[0];
        Product[] actual = manager.searchBy("Nokia");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchBy() {
        Product[] expected = new Product[]{item4};
        Product[] actual = manager.searchBy("Samsung");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveById() {
        repository.removeById(1);
        Product[] expected = new Product[]{item2, item3, item4, item5, item6};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> repository.removeById(10));
    }
}