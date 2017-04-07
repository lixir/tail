import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.IllegalAnnotationError;

import java.io.*;
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
            System.out.println("qqqqqqq" + substrings);
            for (int i = 1; i <= num; i++) {
                text.append(substrings[substrings.length - i]);
            }
        } else {
            String[] substrings = fileText.split("");
            for (int i = 1; i <= num; i++) {
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

        boolean flag = !argsList.contains("-s");

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("CmdLineException");
            parser.printUsage(System.out);
        } catch (IllegalAnnotationError e) {
            System.err.println(e.toString());
        }

        String fileText;
        if (options.ofile != null) {
            fileText = reader(options.ofile);
            System.out.println(fileText);
            if (flag) {
                int numString = argsList.contains("-n") ? options.numString : 10;
                fileText = editor(fileText, true, numString);
            } else {
                fileText = editor(fileText, false, options.numChar);
            }

        } else {
            System.out.println("Введите текст:");
            Scanner in = new Scanner(System.in);
            fileText = in.nextLine();
        }

//        List files = options.files;
//        for (Object element: files){
//            writer(element.toString(), fileText);
//        }
//        writer(options.files, fileText);
    }
}

