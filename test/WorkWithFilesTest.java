import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by lixir on 07.04.2017.
 */
public class WorkWithFilesTest {
    private final WorkWithFiles wwf = new WorkWithFiles(new ArrayList<String>(){{ add("files\\input.txt");}}, "files\\output.txt", true, 8);
    private final WorkWithFiles wwf1 = new WorkWithFiles(new ArrayList<String>(){{ add("files\\output.txt");}}, "files\\output.txt", false, 8);
    private final List<String> list = new ArrayList<String>(){
        { add("22222222222222222222222222222222\n33333333333333333333333333333333" +
            "\n44444444444444444444444444444444\n55555555555555555555555555555555" +
            "\n66666666666666666666666666666666\n77777777777777777777777777777777" +
            "\n88888888888888888888888888888888\n99999999999999999999999999999999");
        }
    };

    @org.junit.Test
    public void readerTest(){
        assertEquals(new ArrayList(){
            { add("00000000000000000000000000000000\n11111111111111111111111111111111\n"+
        list.get(0)); }}
        ,wwf.reader());
    }

    @org.junit.Test
    public void writerTest() throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("files\\output.txt"))) {
            writer.write("");
        }
        wwf1.writer("test");
        assertEquals(new ArrayList<String>(){{add("test");}}, wwf1.reader());
    }

    @org.junit.Test
    public void editorTest(){
        assertEquals(list, wwf.editor(wwf.reader()));
        wwf1.writer(list.get(0));
        assertEquals(new ArrayList<String>(){{add("99999999");}}, wwf1.editor(wwf1.reader()));
    }

    @org.junit.Test
    public void workWithFileTest(){
        assertEquals(list.get(0) ,wwf.workWithFiles());
    }
}