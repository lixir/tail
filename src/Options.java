
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lixir on 24.03.17.
 */
public class Options {
    @Option(name = "-o", usage = "Output file name.")
    public String ofile;

    @Option(name = "-c", usage = "The number of characters to output.")
    public int numChar = -1;

    @Option(name = "-n", usage = "The number of lines to output.")
    public int numString = -1;

    @Argument(handler = org.kohsuke.args4j.spi.OptionHandler.class)
    public List<String> files = new ArrayList();
}