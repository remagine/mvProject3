package command;

import java.util.Arrays;

public enum CommandType {
    MV("mv"), CP("cp"), CAT("cat"), ANDAND("&&"), OROR("||");

    private final String commandName;

    CommandType(String commandName) {
        this.commandName = commandName;
    }

    public static CommandType fromString(String commandName) {
        return Arrays
                .stream(CommandType.values())
                .filter(commandType -> commandType.commandName.equals(commandName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 commandName 입니다."));
    }
}
