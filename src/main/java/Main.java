import command.Command;
import command.CommandType;
import command.cat.Cat;
import command.cp.Cp;
import command.mv.Mv;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
                String[] inputArray = inputString.split(" ");
                String commandString = inputArray[0];
                List<String> fileList = Arrays.asList(inputArray).subList(1, inputArray.length);

                CommandType commandType = CommandType.fromString(commandString);
                Command command;
                String origin;
                String destination;
                switch (commandType) {
                    case MV:
                        if (fileList.size() != 2) {
                            throw new IllegalArgumentException("Mv command needs 2 arguments");
                        }
                        origin = fileList.get(0);
                        destination = fileList.get(1);
                        command = Mv.fromString(origin, destination);
                        break;
                    case CP:
                        if (fileList.size() != 2) {
                            throw new IllegalArgumentException("Cp command needs 2 arguments");
                        }
                        origin = fileList.get(0);
                        destination = fileList.get(1);
                        command = Cp.fromString(origin, destination);
                        break;
                    case CAT:
                        command = Cat.fromStringList(fileList);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + commandType);
                }

                command.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}