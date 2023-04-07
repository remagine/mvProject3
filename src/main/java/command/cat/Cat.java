package command.cat;

import command.Command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cat implements Command {
    private final List<Path> filePaths;

    public Cat(List<Path> filePaths) {
        if (filePaths == null) {
            throw new NullPointerException(this.getClass().getName() + " filePaths is null");
        }
        if (filePaths.isEmpty()) {
            throw new NullPointerException(this.getClass().getName() + " filePaths is empty");
        }
        List<Path> immutablePaths = Collections.unmodifiableList(filePaths);
        if (immutablePaths.stream().anyMatch(Objects::isNull)) {
            throw new NullPointerException(this.getClass().getName() + " list of path is null");
        }
        if (immutablePaths.stream().anyMatch(i -> !Files.exists(i))) {
            throw new NullPointerException(this.getClass().getName() + " path of file doesn't exist");
        }

        this.filePaths = immutablePaths;
    }

    public static Cat fromStringList(List<String> stringList) {
        if (stringList == null) {
            throw new NullPointerException(" stringList is null");
        }
        if (stringList.isEmpty()) {
            throw new NullPointerException(" stringList is empty");
        }
        List<String> immutableStringList = Collections.unmodifiableList(stringList);
        if (immutableStringList.stream().anyMatch(Objects::isNull)) {
            throw new NullPointerException(" list of item is null");
        }
        if (immutableStringList.stream().anyMatch(String::isEmpty)) {
            throw new NullPointerException(" list of item is empty");
        }
        List<Path> immutablePathList = immutableStringList.stream().map(Paths::get).collect(Collectors.toUnmodifiableList());;

        return new Cat(immutablePathList);
    }

    @Override
    public void execute() {

    }
}
