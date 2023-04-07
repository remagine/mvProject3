package command.mv;

import command.Command;
import command.cp.Cp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Mv implements Command {
    private final Path originPath;
    private final Path destinationPath;

    public Mv(Path originPath, Path destinationPath) {
        if (!Files.exists(originPath)) {
            throw new IllegalArgumentException(this.getClass().getName() + " originPath has none file");
        }
        if (!Files.exists(destinationPath)) {
            throw new IllegalArgumentException(this.getClass().getName() + " destinationPath has none file");
        }
        if (originPath.equals(destinationPath)) {
            throw new IllegalArgumentException(this.getClass().getName() + " Both Paths are same");
        }
        this.originPath = originPath;
        this.destinationPath = destinationPath;
    }

    public static Mv fromString(String originPathStr, String destinationPathStr) {
        Path originPath = Paths.get(originPathStr);
        Path destinationPath = Paths.get(destinationPathStr);

        return new Mv(originPath, destinationPath);
    }

    @Override
    public void execute() throws IOException {
        Cp cp  = new Cp(originPath, destinationPath);
        cp.execute();
        Files.delete(originPath);
    }
}
