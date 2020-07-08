import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber() {
        Assert.assertEquals("Метод getLocalNumber() не возвращает число 14", mainClass.getLocalNumber(), 14);
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("Метод getClassNumber() не возвращает число > 45", mainClass.getClassNumber() > 45);
    }

    @Test
    public void testGetClassString() {
        if (mainClass.getClassString().contains("Hello")) {
        } else {
            Assert.assertTrue("Метод getClassString не возвращает строку, в которой есть подстрока \"hello\" или \"Hello\"", mainClass.getClassString().contains("hello"));
        }
    }
}
