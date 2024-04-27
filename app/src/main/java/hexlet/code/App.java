package hexlet.code;

import hexlet.code.source.FileManager;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

import hexlet.code.source.Differ;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: stylish]" //,
            //showDefaultValue = CommandLine.Help.Visibility.ALWAYS
    )
    private String format = "stylish";


    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String filepath1 = "/home/alex/java-project-71/app/File1.json";

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String filepath2 = "/home/alex/java-project-71/app/File2.json";

    public static void main(String[] args) {
        App app = new App();
        int exitCode = new CommandLine(app).execute(args);
       // new CommandLine(app).parseArgs(args);

        app.call();

        System.exit(exitCode);
    }

    public Integer call() {
            var readfile1 = Differ.readJson(FileManager.normaolizePath(this.filepath1));
            var readfile2 = Differ.readJson(FileManager.normaolizePath(this.filepath2));
            var comparison = Differ.generate(readfile1, readfile2);
            System.out.println(comparison);
        return null;
    }
}
