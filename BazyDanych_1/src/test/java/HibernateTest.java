import org.example.HibernateUtil;
import org.example.tables.Trial;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HibernateTest {

    static Session session;

    @BeforeAll
    static void setUp() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @BeforeEach
    void beginTransaction() {
        session.beginTransaction();
    }

    @Test
    void connectionTest() {
        Assertions.assertTrue(session.isConnected());
        Assertions.assertTrue(session.isOpen());
        Assertions.assertFalse(session.isDirty());
        Assertions.assertTrue(session.getTransaction().isActive());
    }

    @Test
    void insertTest() {
        Trial trial = new Trial();
        trial.setId(1);
        trial.setName("test");
        session.save(trial);

    }

    @Test
    void modifyTest() {
        Trial trial = session.find(Trial.class, 1);
        trial.setName("test2");
        session.update(trial);

        Trial trial1 = session.find(Trial.class, 1);
        Assertions.assertEquals("test2", trial1.getName());
    }

    @Test
    void detachAndMegeTest() {
        Trial trial = session.find(Trial.class, 1);
        session.detach(trial);
        trial.setName("test3");
        session.merge(trial);

        Trial trial1 = session.find(Trial.class, 1);
        Assertions.assertEquals("test3", trial1.getName());
    }

    @Test
    void deleteTest() {
        Trial trial = session.find(Trial.class, 1);
        session.delete(trial);

        Trial trial1 = session.find(Trial.class, 1);
        Assertions.assertNull(trial1);
    }

    @Test
    void queryTest() {
        session.createNamedQuery("Trial.findByName")
                .setParameter("name", "test2")
                .getResultList()
                .forEach(System.out::println);
    }

    @AfterEach
    void commit() {
        session.getTransaction().commit();
    }

    @AfterAll
    static void tearDown() {
        session.close();
    }
}
