package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.nio.file.Path;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
description = "Compares two configuration files and shows a difference.")
public class App {

    @Option(names = {"-f", "--format"}, paramLabel = "format",
            description = "output format [default: stylish]" //,
            //showDefaultValue = CommandLine.Help.Visibility.ALWAYS
    )
    private String format = "stylish";


    @Parameters(paramLabel = "filepath1", description = "path to first file")
    private Path filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    private Path filepath2;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        //new CommandLine(this).parseArgs(args);
        System.out.println("Hello World!");
    }
}
