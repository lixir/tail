import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by lixir on 07.04.2017.
 */
public class WorkWithFilesTest {
    private final WorkWithFiles wwf = new WorkWithFiles(new ArrayList<String>(){{ add("files\\input.txt");}},
            "files\\output.txt", true, 8);
    private final WorkWithFiles wwf1 = new WorkWithFiles(new ArrayList<String>(){{ add("files\\input.txt");}},
            null, false, 8);
    private final WorkWithFiles wwf2 = new WorkWithFiles(new ArrayList<String>(){
        {
        add("files\\input.txt");
        add("files\\input1.txt");
        }
    }, null, false, 8);
    private final List<String> list = new ArrayList<String>(){
        { add("22222222222222222222222222222222\n33333333333333333333333333333333" +
            "\n44444444444444444444444444444444\n55555555555555555555555555555555" +
            "\n66666666666666666666666666666666\n77777777777777777777777777777777" +
            "\n88888888888888888888888888888888\n99999999999999999999999999999999");
        }
    };


    @org.junit.Test
    public void workWithFileTest() throws Exception{
        assertEquals("99999999" ,wwf1.workWithFiles());

        wwf.workWithFiles();
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("files\\output.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                text.append(line);
                line = reader.readLine();
                if (line != null) text.append("\n");
            }
        }
        assertEquals(list.get(0), text.toString());
        assertEquals("\nfiles\\input.txt\n99999999\n\nfiles\\input1.txt\nrrrrrrrr\n", wwf2.workWithFiles());
    }
}