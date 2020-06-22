package commandLine.extract.sourceExtractor;

import org.apache.commons.cli.MissingArgumentException;

public interface ISourceDTOExtractor {
    public SourceDTO getSourceDTO() throws MissingArgumentException;
}
