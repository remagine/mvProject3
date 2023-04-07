import command.Command;
import command.CommandSet;
import command.CommandType;
import command.cat.Cat;
import command.cp.Cp;
import command.mv.Mv;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, Command.BYTE_BUFFER_SIZE);
             InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader, Command.CHAR_BUFFER_SIZE);) {
            while (true) {
                String inputString = bufferedReader.readLine();
                List<CommandSet> commandSets = new ArrayList<>();
                String[] andandSplitArray = inputString.split(CommandType.ANDAND.name());
                Arrays.stream(andandSplitArray).forEach(str ->{
                    String[] strArray = str.split(" ");
                    String commandString = strArray[0];
                    commandSets.add(new CommandSet(CommandType.fromString(commandString), Arrays.asList(strArray).subList(1, strArray.length)));
                });
                commandSets.forEach(commandSet -> {
                    try {
                        executeCommand(commandSet);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(CommandSet commandSet) throws IOException {
        Command command;
        CommandType commandType = commandSet.getCommandType();
        List<String> arguments = commandSet.getArguments();
        String origin;
        String destination;
        switch (commandType) {
            case MV:
                if (arguments.size() != 2) {
                    throw new IllegalArgumentException("Mv command needs 2 arguments");
                }
                origin = arguments.get(0);
                destination = arguments.get(1);
                command = Mv.fromString(origin, destination);
                break;
            case CP:
                if (arguments.size() != 2) {
                    throw new IllegalArgumentException("Cp command needs 2 arguments");
                }
                origin = arguments.get(0);
                destination = arguments.get(1);
                command = Cp.fromString(origin, destination);
                break;
            case CAT:
                command = Cat.fromStringList(arguments);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + commandType);
        }

        command.execute();

    }
}