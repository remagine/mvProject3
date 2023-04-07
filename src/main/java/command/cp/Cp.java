package command.cp;

import command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cp implements Command {
    private final Path originPath;
    private final Path destinationPath;

    public Cp(Path originPath, Path destinationPath) throws FileAlreadyExistsException, FileNotFoundException {
        if (!Files.exists(originPath)) {
            throw new FileNotFoundException(this.getClass().getName() + " originPath has none file");
        }
        if (Files.exists(destinationPath)) {
            throw new FileAlreadyExistsException(this.getClass().getName() + " destinationPath has already exist");
        }
        if (originPath.equals(destinationPath)) {
            throw new IllegalArgumentException(this.getClass().getName() + " Both Paths are same");
        }

        this.originPath = originPath;
        this.destinationPath = destinationPath;
    }

    public static Cp fromString(String originPathStr, String destinationPathStr) throws FileAlreadyExistsException, FileNotFoundException {
        Path originPath = Paths.get(originPathStr);
        Path destinationPath = Paths.get(destinationPathStr);

        return new Cp(originPath, destinationPath);
    }

    @Override
    public void execute() {
        try {
            Files.copy(originPath, destinationPath);
            System.out.println("Copy files success");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
