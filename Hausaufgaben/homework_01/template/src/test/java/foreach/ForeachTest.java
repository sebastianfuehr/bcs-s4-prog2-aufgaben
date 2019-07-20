package foreach;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static foreach.ForEach.*;
import static org.junit.Assert.assertEquals;

public class ForeachTest {
    final String[] names = { "andrea", "beate", "charlotte", "dieter", "emil", "fridolin" };

    private ByteArrayOutputStream acquireOut() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(byteArrayOutputStream));
        return byteArrayOutputStream;
    }

    @After
    public void releaseOut() {
        System.setOut(System.out);
    }

    @Test
    public void testForLoop1() {
        ByteArrayOutputStream out = acquireOut();
        forLoop1A(names);
        String expected = out.toString();
        out = acquireOut();
        forLoop1B(names);
        String actual = out.toString();
        assertEquals("ForLoop1", expected, actual);
    }

    @Test
    public void testForLoop2() {
        List<String> list = Arrays.asList(this.names);
        ByteArrayOutputStream out = acquireOut();
        forLoop2A(list);
        String expected = out.toString();
        out = acquireOut();
        forLoop2B(list);
        String actual = out.toString();
        assertEquals("ForLoop2", expected, actual);
    }

    @Test
    public void testForLoop3() {
        ByteArrayOutputStream out = acquireOut();
        forLoop3A(5);
        String expected = out.toString();
        out = acquireOut();
        forLoop3B(5);
        String actual = out.toString();
        assertEquals("ForLoop3", expected, actual);
    }
}
