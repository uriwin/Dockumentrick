package cli.dockumentrick;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class DockumentrickOptions {
    public Options cliOptions;
    public DockumentrickOptions(){
        this.cliOptions = new Options();
        this.cliOptions.addOption(getInputFilePathOption());
        this.cliOptions.addOption(getBaseConverterOption());
        this.cliOptions.addOption(getEscapeCharacterAppenderOption());
        this.cliOptions.addOption(getEncloseOption());
        this.cliOptions.addOption(getEncloseByOption());
        this.cliOptions.addOption(getLimitedLinesFilterOption());
        this.cliOptions.addOption(getXmlElementFilterOption());
        this.cliOptions.addOption(getCsvColumnFilter());
    }

    public Option getInputFilePathOption() {
        return Option.builder().required().longOpt("inputPath").hasArg().build();
    }
    public Option getBaseConverterOption() { return Option.builder().longOpt("changeNumBase").hasArg().desc("(h/o/b)").build();}
    public Option getEncloseOption() {
        return Option.builder().longOpt("enclose").hasArg().build();
    }
    public Option getEncloseByOption() {
        return Option.builder().longOpt("encloseBy").hasArg().build();
    }
    public Option getEscapeCharacterAppenderOption() {
        return Option.builder().longOpt("escapeChar").hasArg().build();
    }
    public Option getLimitedLinesFilterOption(){
        return Option.builder().longOpt("limitedLines").hasArg().build();
    }
    public Option getXmlElementFilterOption(){
        return Option.builder().longOpt("Element").hasArg().build();
    }
    public Option getCsvColumnFilter(){return Option.builder().longOpt("Column").hasArg().build();}

}
