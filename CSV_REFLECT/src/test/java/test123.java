import org.example.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class test123 {
Main main;
    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @Test
    public void test0() throws Exception {
        Assert.assertEquals(main.listOfBooks.size(), 5);
    }
}
