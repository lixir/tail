import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    private List<String> reader() {
        if (files.size() == 0) return new ArrayList<String>();
        List<String> result = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            StringBuilder text = new StringBuilder();
            String fileName = files.get(i);
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line = reader.readLine();

                while (line != null) {
                    text.append(line);
                    line = reader.readLine();
                    if (line != null) text.append("\n");
                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            result.add(i, text.toString());
        }
        return result;
    }


    private void writer(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ofile))) {
            writer.write(text);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
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

    public String workWithFiles(){
        List<String> list = reader();
        StringBuilder sb = new StringBuilder();
        if (files.size() == 0){
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            sb.append(line);
            line = in.nextLine();
            while(!line.isEmpty()) {
                sb.append("\n" + line);
                line = in.nextLine();
            }
            list.add(sb.toString());
            sb.delete(0, sb.length());
        }
        list = editor(list);

        if (files.size() > 1) for (int i = 0; i < files.size(); i++){
            sb.append( "\n" + files.get(i) + "\n" + list.get(i) + "\n");
        } else sb.append(list.get(0));

        if (ofile != null) {
            writer(sb.toString());
            return "";
        }
        return sb.toString();
    }
}
