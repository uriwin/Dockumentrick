package arguments;

import commandLine.extract.destinationExtractor.DestinationType;
import commandLine.extract.sourceExtractor.SourceType;
import fileFilter.FilterType;
import manipulateActions.ManipulateActionsType;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CommandLineArguments implements IArgumentsRequired {
    private Options cliArguments;

    public CommandLineArguments() {
        this.cliArguments = new Options();
        this.cliArguments.addOption(getInputFilePathOption());
        this.cliArguments.addOption(getOutputFilePathOption());
        this.cliArguments.addOption(getBaseConverterOption());
        this.cliArguments.addOption(getEscapeCharacterAppenderOption());
        this.cliArguments.addOption(getEncloseOption());
        this.cliArguments.addOption(getLimitedLinesFilterOption());
        this.cliArguments.addOption(getXmlElementFilterOption());
        this.cliArguments.addOption(getCsvColumnFilter());
    }

    private Option getInputFilePathOption() {
        return Option.builder().required().longOpt(SourceType.INPUT_FILE.toString()).hasArg().build();
    }

    private Option getOutputFilePathOption() {
        return Option.builder().required().longOpt(DestinationType.OUTPUT_FILE.toString()).hasArg().build();
    }

    private Option getBaseConverterOption() {
        return Option.builder().longOpt(ManipulateActionsType.BASE_CONVERTER.toString()).hasArg().desc("(h/o/b)").build();
    }

    private Option getEncloseOption() {
        return Option.builder().longOpt(ManipulateActionsType.STRING_ENCLOSER.toString()).hasArg().numberOfArgs(2).build();
    }

    private Option getEscapeCharacterAppenderOption() {
        return Option.builder().longOpt(ManipulateActionsType.ESCAPE_CHARACTER_APPENDER.toString()).hasArg().build();
    }

    private Option getLimitedLinesFilterOption() {
        return Option.builder().longOpt(FilterType.FILTER_ON_SPECIFIC_FIRST_LINES.toString()).hasArg().build();
    }

    private Option getXmlElementFilterOption() {
        return Option.builder().longOpt(FilterType.FILTER_ON_SPECIFIC_ELEMENT.toString()).hasArg().build();
    }

    private Option getCsvColumnFilter() {
        return Option.builder().longOpt(FilterType.FILTER_ON_SPECIFIC_COLUMN.toString()).hasArg().build();
    }

    @Override
    public Options getArgumentsRequired() {
        return cliArguments;
    }
}
