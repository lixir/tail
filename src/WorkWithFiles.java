import java.io.*;
import java.util.*;

/**
 * Created by lixir on 08.04.2017.
 */

public class WorkWithFiles {
    private final List<String> files;
    private final String ofile;
    private final boolean flag;
    private final int num;

    public WorkWithFiles(List<String> files, String ofile, boolean flag, int num){
        this.files = files;
        this.ofile = ofile;
        this.flag = flag;
        this.num = num;
    }

    private StringBuilder fileRead(BufferedReader reader)  throws Exception{
        String line = reader.readLine();
        StringBuilder text = new StringBuilder();
        while (line != null) {
            text.append(line);
            line = reader.readLine();
            if (line != null) text.append("\n");
        }
        return text;
    }

    private List<String> reader() {
        StringBuilder text = new StringBuilder();
        List<String> result = new ArrayList<>();
        if (files.size() == 0) {
            try {
                InputStreamReader reader = new InputStreamReader(System.in);
                result.add(fileRead(new BufferedReader(reader)).toString());
            }catch (Exception e){
                System.err.println(e.toString());
                System.exit(1);
            }
        }
        for (String fileName : files) {
            try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                text = fileRead(reader);
            } catch (Exception e) {
                System.err.println(e.toString());
                System.exit(1);
            }
            result.add(text.toString());
        }
        return result;
    }


    private void writer(String text) {
        if (ofile != null) try (BufferedWriter writer = new BufferedWriter(new FileWriter(ofile))) {
            writer.write(text);
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }
        if (ofile == null) System.out.println(text);
    }


    private List<String> editor(List<String> fileText) {
        for (int i = 0; i < fileText.size(); i++) {
            StringBuilder text = new StringBuilder();
            String element = fileText.get(i);
            if (flag) {
                String[] substrings = fileText.get(i).split("\n");
                for (int j = num; j >= 1; j--) {
                    text.append(substrings[substrings.length - j]);
                    if (j != 1) text.append("\n");
                }
            } else {
                text.append(element.substring(element.length() - num));
            }
            fileText.set(i,text.toString());
        }
        return fileText;
    }

    public void workWithFiles(){
        List<String> list = reader();
        StringBuilder sb = new StringBuilder();
        list = editor(list);
        if (files.size() > 1) for (int i = 0; i < files.size(); i++){
            sb.append( "\n" + files.get(i) + "\n" + list.get(i) + "\n");
        } else sb.append(list.get(0));

        writer(sb.toString());
    }
}
