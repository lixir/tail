import java.io.BufferedWriter;
import java.io.FileWriter;

import static org.junit.Assert.*;


/**
 * Created by lixir on 07.04.2017.
 */
public class TailTest {
    @org.junit.Test
    public void readerTest(){
        assertEquals("00000000000000000000000000000000\n11111111111111111111111111111111"+
                "\n22222222222222222222222222222222\n33333333333333333333333333333333" +
                "\n44444444444444444444444444444444\n55555555555555555555555555555555" +
                "\n66666666666666666666666666666666\n77777777777777777777777777777777" +
                "\n88888888888888888888888888888888\n99999999999999999999999999999999"
                ,Tail.reader("files\\input.txt"));
    }

    @org.junit.Test
    public void writerTest() throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("files\\output.txt"))) {
            writer.write("");
        }
        Tail.writer("files\\output.txt", "test");
        assertEquals("test", Tail.reader("files\\output.txt"));
    }

    @org.junit.Test
    public void editorTest(){
        assertEquals("22222222222222222222222222222222\n33333333333333333333333333333333" +
                "\n44444444444444444444444444444444\n55555555555555555555555555555555" +
                "\n66666666666666666666666666666666\n77777777777777777777777777777777" +
                "\n88888888888888888888888888888888\n99999999999999999999999999999999"
                ,Tail.editor(Tail.reader("files\\input.txt"), true, 8));
        assertEquals("99999999", Tail.editor(Tail.reader("files\\input.txt"), false, 8));
    }
}