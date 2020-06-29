package arguments.commandLine;

import arguments.IArgumentsRequired;
import arguments.extract.destinationExtractor.DestinationType;
import arguments.extract.sourceExtractor.SourceType;
import fileFilter.FilterType;
import manipulateActions.ManipulateActionsType;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CommandLineArguments implements IArgumentsRequired {
    private Options commandLineArguments;

    public CommandLineArguments() {
        this.commandLineArguments = new Options();

        addActionsArguments();

        addFilterArguments();

        addInputSourceArguments();

        addOutputDestinationArguments();
    }

    private void addFilterArguments(){
        for (FilterType filterType: FilterType.values()){
            addArgument(filterType.name(), filterType.getArgumentsRequiredNumber(),
                    filterType.getDescription(), filterType.isRequired());
        }
    }

    private void addActionsArguments(){
        for (ManipulateActionsType manipulateActionsType: ManipulateActionsType.values()){
            addArgument(manipulateActionsType.name(), manipulateActionsType.getArgumentsRequiredNumber(),
                    manipulateActionsType.getDescription(), manipulateActionsType.isRequired());
        }
    }

    private void addInputSourceArguments(){
        for (SourceType sourceType: SourceType.values()){
            addArgument(sourceType.name(), sourceType.getArgumentsRequiredNumber(),
                    sourceType.getDescription(), sourceType.isRequired());
        }
    }

    private void addOutputDestinationArguments(){
        for (DestinationType destinationType: DestinationType.values()){
            addArgument(destinationType.name(), destinationType.getArgumentsRequiredNumber(),
                    destinationType.getDescription(), destinationType.isRequired());
        }
    }

    private void addArgument(String argumentName, Integer requiredArgumentsNumber, String description, boolean isRequired){
        if (isRequired){
            this.commandLineArguments.addOption(Option.builder().longOpt(argumentName).desc(description)
                    .numberOfArgs(requiredArgumentsNumber).required().build());
        }
        else{
            this.commandLineArguments.addOption(Option.builder().longOpt(argumentName).desc(description)
                    .numberOfArgs(requiredArgumentsNumber).build());
        }
    }

    @Override
    public Options getArguments() {
        return commandLineArguments;
    }
}
