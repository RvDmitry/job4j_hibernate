package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersStoreTest {
    private static BasicDataSource pool = new BasicDataSource();

    @BeforeClass
    public static void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);
        Order original = store.save(Order.of("name1", "description1"));
        List<Order> all = (List<Order>) store.findAll();
        var received = all.stream().filter(order -> order.getId() == original.getId()).findFirst();
        assertTrue(received.isPresent());
        assertEquals(received.get().getId(), original.getId());
        assertEquals(received.get().getName(), original.getName());
        assertEquals(received.get().getDescription(), original.getDescription());
    }

    @Test
    public void whenSaveOrderAndFindById() {
        OrdersStore store = new OrdersStore(pool);
        Order original = store.save(Order.of("name2", "description2"));
        Order received = store.findById(original.getId());
        assertEquals(received.getId(), original.getId());
        assertEquals(received.getName(), original.getName());
        assertEquals(received.getDescription(), original.getDescription());
    }

    @Test
    public void whenSaveOrderAndFindByName() {
        OrdersStore store = new OrdersStore(pool);
        Order original = store.save(Order.of("name3", "description3"));
        Order received = store.findByName(original.getName());
        assertEquals(received.getId(), original.getId());
        assertEquals(received.getName(), original.getName());
        assertEquals(received.getDescription(), original.getDescription());
    }

    @Test
    public void whenUpdateOrder() {
        OrdersStore store = new OrdersStore(pool);
        Order first = store.save(Order.of("name4", "description4"));
        Order second = new Order(first.getId(), "name5", "description5", first.getCreated());
        assertTrue(store.update(second));
        assertNull(store.findByName(first.getName()));
        assertEquals(store.findById(first.getId()).getName(), second.getName());
        assertEquals(store.findById(first.getId()).getDescription(), second.getDescription());
    }
}