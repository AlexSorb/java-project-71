package hexlet.code;

import hexlet.code.source.FileManager;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;

import hexlet.code.source.Differ;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
    description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format = "stylish";


    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file"
//            defaultValue = "/home/alex/java-project-71/app/File1.json"
    )
    private String filepath1;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file"
//    defaultValue = "/home/alex/java-project-71/app/File2.json"
      )
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
            Map<String, Object> readfile1 = null;
            Map<String, Object> readfile2 = null;
            String comparison = "";
            try {
                readfile1 = Differ.readJson(FileManager.normaolizePath(this.filepath1));
                readfile2 = Differ.readJson(FileManager.normaolizePath(this.filepath2));
                comparison = Differ.generate(readfile1, readfile2);
            } catch (IOException e) {
                System.out.println("Ошибка чтения");
                e.printStackTrace();
            }
            System.out.println(comparison);
        }
        return 1;
    }
}
