
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lixir on 24.03.17.
 */
public class Parser {
    @Option(name = "-o", usage = "Output file name.")
    public String ofile;

    @Option(name = "-c", usage = "The number of characters to output.", forbids = "-n")
    public int numChar = -1;

    @Option(name = "-n", usage = "The number of lines to output.")
    public int numString = -1;

    @Argument(handler = org.kohsuke.args4j.spi.OptionHandler.class)
    public List<String> files = new ArrayList();

    CmdLineParser parser = new CmdLineParser(this);

    public Parser(String[] args){
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("CmdLineException");
            parser.printUsage(System.out);
        }
    }
}