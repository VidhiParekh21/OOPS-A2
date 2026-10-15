import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Files.write(Paths.get("log1.txt"),
            Arrays.asList("Java is easy", "Learn Python", "Java is useful"));

        Files.write(Paths.get("log2.txt"),
            Arrays.asList("Learn Java", "Learn C++"));

        String[] files = {"log1.txt", "log2.txt"};
        int totalLines = 0, keywordCount = 0;
        String keyword = "Java";

        for (String file : files) {
            Path p = Paths.get(file);
            List<String> lines = Files.readAllLines(p);

            int count = 0;
            for (String line : lines) {
                totalLines++;
                count++;

                if (line.contains(keyword)) {
                    keywordCount++;
                }
            }

            BasicFileAttributes a = Files.readAttributes(
                p, BasicFileAttributes.class);

            System.out.println(file + " lines: " + count);
            System.out.println("Size: " + a.size() + " bytes");
        }

        System.out.println("Total lines: " + totalLines);
        System.out.println("Lines containing Java: " + keywordCount);
    }
}