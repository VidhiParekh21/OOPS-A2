import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class FolderReport {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("testfolder");

        try (PrintWriter out = new PrintWriter("report.txt")) {
            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(
                    Path file, BasicFileAttributes attr) throws IOException {

                    String name = file.getFileName().toString();
                    long size = attr.size();
                    String time = DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm").format(
                        LocalDateTime.ofInstant(
                            attr.lastModifiedTime().toInstant(),
                            ZoneId.systemDefault()));

                    String report = name + " | " + size
                        + " bytes | " + time;

                    System.out.println(report);
                    out.println(report);

                    return FileVisitResult.CONTINUE;
                }
            });
        }

        System.out.println("Report saved to report.txt");
    }
}