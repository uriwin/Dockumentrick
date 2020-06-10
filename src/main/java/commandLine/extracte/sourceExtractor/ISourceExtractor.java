package commandLine.extracte.sourceExtractor;

import org.apache.commons.cli.MissingArgumentException;

public interface ISourceExtractor {
    public SourceDTO getSource() throws MissingArgumentException;
}
