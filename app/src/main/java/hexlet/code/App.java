package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;
import hexlet.code.source.Differ;

import java.io.IOException;
import java.util.concurrent.Callable;



@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.9",
    description = "Compares two configuration files and shows a difference.")
public final class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;


    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2;

    public static void main(String[] args)  {
        var main = new App();
        var mainCommandLine = new CommandLine(main);
        if (args.length > 0) {
            mainCommandLine.parseArgs(args);
        }
        int exitCode = mainCommandLine.execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        if (filepath1 != null && filepath2 != null) {
            String comparison = "";
            try {
                comparison = Differ.generate(filepath1, filepath2, format);
            } catch (IOException e) {
                System.out.println("Ошибка чтения");
                e.printStackTrace();
            }
            System.out.println(comparison);
        }
        return 0;
    }
}
