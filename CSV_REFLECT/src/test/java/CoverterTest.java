import org.example.generator.helpers.FileFieldConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CoverterTest {
    FileFieldConverter converter;

    @Before
    public void setUp() {
        converter = new FileFieldConverter();
    }

    @Test
    public void test0() {
        Assert.assertEquals(converter.stringToType("true", boolean.class), true);
        Assert.assertEquals(converter.stringToType("1", Integer.class), 1);
        Assert.assertEquals(converter.stringToType("Ala", String.class), "Ala");
        Assert.assertEquals(converter.stringToType("1.0", Double.class), 1.0);
    }

    @Test
    public void test1() {
        List convList = (List) converter.stringToType("1,2,3,4", List.class);
        List compList = List.of("1", "2", "3", "4");

        Assert.assertEquals(convList, compList);
    }
}
