import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AllWords {

    private List<String> allWords;
    private Set<String> stringHashSet;

    public AllWords(String filePath) {
        // Read words from an external file
        try {
            allWords = Files.lines(Paths.get(filePath))
                            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        // Create a HashSet from the list
        stringHashSet = new HashSet<>(allWords);

        // Convert all words to lowercase
        stringHashSet = stringHashSet.stream()
                                     .map(String::toLowerCase)
                                     .collect(Collectors.toSet());
    }

    // Getter method for stringHashSet
    public Set<String> getStringHashSet() {
        return stringHashSet;
    }
}
