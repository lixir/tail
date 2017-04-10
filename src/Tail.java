

/**
 * Created by lixir on 09.03.17.
 */

public class Tail {

    public static void main(String[] args) {
        Parser parser = new Parser(args);
        boolean flag = parser.numChar == -1;
        int num = parser.numChar + parser.numString + 1;
        if (num == -1) num = 10;
        try {
            if (parser.ofile != null) {
                new WorkWithFiles(parser.files, parser.ofile, flag, num).workWithFiles();
            } else System.out.println(new WorkWithFiles(parser.files, null, flag, num).workWithFiles());
        } catch (Exception e){
            System.err.println(e.toString());
            parser.parser.printUsage(System.out);
        }
    }
}

