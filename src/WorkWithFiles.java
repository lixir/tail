import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public ArrayList<String> reader() {
        if (files.size() == 0) return new ArrayList<String>();
        ArrayList<String> result = new ArrayList<>();
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


    public void writer(String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ofile))) {
            writer.write(text);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    public ArrayList<String> editor(ArrayList<String> fileText) {
        if (fileText.size() == 0) return new ArrayList<String>();
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
        ArrayList<String> list = reader();
        list = editor(list);
        StringBuilder sb = new StringBuilder();
        if (files.size() > 1) for (int i = 0; i < files.size(); i++){
            sb.append( "\n" + files.get(i) + "\n" + list.get(i) + "\n");
        } else if (files.size() == 1) {
            sb.append(list.get(0));
        } else {
            Scanner in = new Scanner(System.in);
            String temp = in.nextLine();
            sb.append(editor(new ArrayList<String>(){{add(temp);}}).get(0));
        }
        if (ofile != null) writer(sb.toString());
        return sb.toString();
    }
}
