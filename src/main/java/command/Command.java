package command;

import java.io.IOException;

public interface Command {
    int BYTE_BUFFER_SIZE = 8192;
    int CHAR_BUFFER_SIZE = BYTE_BUFFER_SIZE /2;

    public void execute() throws IOException;
}
