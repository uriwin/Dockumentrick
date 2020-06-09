package commandLine.clientArguments;

import commandLine.clientArguments.IClientArguments;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CommandLineArguments implements IClientArguments {
    private Options cliOptions;

    public CommandLineArguments(){
        this.cliOptions = new Options();
        this.cliOptions.addOption(getInputFilePathOption());
        this.cliOptions.addOption(getOutputFilePathOption());
        this.cliOptions.addOption(getBaseConverterOption());
        this.cliOptions.addOption(getEscapeCharacterAppenderOption());
        this.cliOptions.addOption(getEncloseOption());
        this.cliOptions.addOption(getLimitedLinesFilterOption());
        this.cliOptions.addOption(getXmlElementFilterOption());
        this.cliOptions.addOption(getCsvColumnFilter());
    }

    public Option getInputFilePathOption() {
        return Option.builder().required().longOpt("inputFilePath").hasArg().build();
    }
    public Option getOutputFilePathOption() {
        return Option.builder().required().longOpt("outputFilePath").hasArg().build();
    }
    public Option getBaseConverterOption() { return Option.builder().longOpt("changeNumBase").hasArg().desc("(h/o/b)").build();}
    public Option getEncloseOption() {
        return Option.builder().longOpt("enclose").hasArg().numberOfArgs(2).build();
    }
    public Option getEscapeCharacterAppenderOption() {
        return Option.builder().longOpt("escapeChar").numberOfArgs(Option.UNLIMITED_VALUES).hasArg().build();
    }
    public Option getLimitedLinesFilterOption(){
        return Option.builder().longOpt("limitedLines").hasArg().build();
    }
    public Option getXmlElementFilterOption(){
        return Option.builder().longOpt("Element").hasArg().build();
    }
    public Option getCsvColumnFilter(){return Option.builder().longOpt("Column").hasArg().build();}

    @Override
    public Options getClientOptions() {
        return cliOptions;
    }
}
