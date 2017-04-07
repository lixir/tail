
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lixir on 24.03.17.
 */
public class Options {
    @Option(name = "-o", usage = "Флаг задаёт имя выходного файла. Если флаг не указан, результат " +
            "выводится в консоль.")
    public String ofile;

    @Option(name = "-c", usage = "Флаг задаёт количество символов, которые будут выведены.")
    public int numChar;

    @Option(name = "-n", usage = "Флаг задаёт количество строк, которые будут выведены.")
    public int numString;

    @Argument(multiValued = true)
    public String files;
}