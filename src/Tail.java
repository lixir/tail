import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lixir on 09.03.17.
 */

public class Tail {


    public static String reader(String fileName) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            while (line != null) {
                result.append(line);
                line = reader.readLine();
                if (line != null) result.append("\n");
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return result.toString();
    }


    public static void writer(String fileName, String text) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(text);
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }


    public static String editor(String fileText, boolean flag, int num) {
        StringBuilder text = new StringBuilder();
        if (flag) {
            String[] substrings = fileText.split("\n");
            for (int i = num; i >= 1; i--) {
                text.append(substrings[substrings.length - i]);
                if (i != 1) text.append("\n");
            }
        } else {
            String[] substrings = fileText.split("");
            for (int i = num; i >= 1; i--) {
                text.append(substrings[substrings.length - i]);
            }
        }
        return text.toString();
    }


    public static void main(String[] args) {
        Options options = new Options();
        CmdLineParser parser = new CmdLineParser(options);


        List argsList = Arrays.asList(args);
        if (argsList.contains("-c") && argsList.contains("-n")) {
            parser.printUsage(System.out);
            return;
        }

        boolean flag = !argsList.contains("-c");

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("CmdLineException");
            parser.printUsage(System.out);
        }

        StringBuilder fileText = new StringBuilder();
        int sum = 0;
        if (argsList.contains("-c") || argsList.contains("-n")) sum += 2;
        if (argsList.contains("-o")) sum += 2;
        List<String> files = new ArrayList();
        for (int i = sum; i < args.length; i++) files.add(args[i]);
        int numString = argsList.contains("-n") ? options.numString : 10;

        if (files.size() >= 1) {
            for (String element : files) {
                fileText.append("\n" + element + "\n");
                fileText.append(editor(reader(element), flag, flag ? numString : options.numChar));
            }
        } else if (files.size() == 1){
            fileText.append(editor(reader(files.get(0)), flag, flag ? numString : options.numChar));
        } else {
            System.out.println("Введите количество строк для ввода:");
            Scanner in = new Scanner(System.in);
            int inSubstrings = in.nextInt();
            System.out.println("Введите текст:");
            StringBuilder temp = new StringBuilder();
            for (int i = 0; i <= inSubstrings; i++){
                temp.append(in.nextLine());
                if (i != inSubstrings) temp.append("\n");
            }
            fileText.append(editor(temp.toString(), flag, flag ? numString : options.numChar));
        }

        if (options.ofile != null){
            writer(options.ofile, fileText.toString());
        } else System.out.println(fileText);
    }
}

