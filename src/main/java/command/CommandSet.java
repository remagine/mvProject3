package command;

import java.util.Collections;
import java.util.List;

public class CommandSet {
    private final CommandType commandType;
    private final List<String> arguments;

    public CommandSet(CommandType commandType, List<String> arguments) {
        this.commandType = commandType;
        this.arguments = arguments;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public List<String> getArguments() {
        return arguments;
    }
}
