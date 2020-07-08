import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainClassTest {

    @Test
    public void testGetLocalNumber() {
        MainClass mainClass = new MainClass();
        int a = mainClass.getLocalNumber();
        assertThat("Метод getLocalNumber() не возвращает число 14", a == 14);
    }

    @Test
    public void testGetClassNumber() {
        MainClass mainClass = new MainClass();
        int a = mainClass.getClassNumber();
        assertThat("Метод getClassNumber() не возвращает число > 45", a > 45);
    }



}