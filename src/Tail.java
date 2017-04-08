import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;


/**
 * Created by lixir on 09.03.17.
 */

public class Tail {

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        CmdLineParser parser = new CmdLineParser(options);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("CmdLineException");
            parser.printUsage(System.out);
        }

        if (options.numString != -1 && options.numChar != -1) {
            parser.printUsage(System.out);
            return;
        }
        boolean flag = options.numChar == -1;
        int num = options.numChar + options.numString + 1;
        if (num == -1) num += 11;
        if (options.ofile != null){
            new WorkWithFiles(options.files,options.ofile, flag, num).workWithFiles();
        } else System.out.println( new WorkWithFiles(options.files, null, flag, num).workWithFiles() );
    }
}

