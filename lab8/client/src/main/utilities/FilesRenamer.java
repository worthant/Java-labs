package main.utilities;

import java.io.IOException;
import java.nio.file.*;

/**
 * Renames .svg files
 * If you want to rename to any other format - just change .svg to .png or anything
 */
public class FilesRenamer {
    public static void main(String[] args) {
        String format = ".svg";

        String directoryPath = "lab8/client/src/main/resources/icons/svg";
        Path pathToDirectory = Paths.get(directoryPath);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(pathToDirectory, "*" + format)) {
            int i = 0;
            for (Path path : stream) {
                String newFileName = "icon_" + i + format;
                Path newPath = Paths.get(pathToDirectory.toString(), newFileName);
                Files.move(path, newPath, StandardCopyOption.REPLACE_EXISTING);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
