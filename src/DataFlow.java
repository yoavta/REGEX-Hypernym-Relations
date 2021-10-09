import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author yoav tamir
 * @version 1.0
 */
public class DataFlow {

    private String dir;
    private String outputPath;
    private RelationsCollection relationsCollection;

    /**
     * constructor for DataFlow.
     *
     * @param args String[]
     */
    public DataFlow(String[] args) {
        dir = args[0];
        outputPath = args[1];
        this.relationsCollection = new RelationsCollection();
    }

    /**
     * process - process the file given into a data base.
     *
     * @throws IOException e
     */
    public void process() throws IOException {
        List<Path> filesPaths = new ArrayList<Path>();
        Files.list(Paths.get(dir))
                .forEach(path -> {
                    fileHandle(path);
                });
    }

    /**
     * printTo - print to file or to console according the the input given.
     * 1-> to file
     * 2-> to console
     *
     * @param index int
     * @throws IOException e
     */
    public void printTo(int index) throws IOException {
        if (index == 1) {
            relationsCollection.printToFile(outputPath);
        } else {

            relationsCollection.printToConsole(outputPath);
        }
    }

    /**
     * fileHandle - process each file given.
     *
     * @param path Path
     */
    private void fileHandle(Path path) {

        try (Stream<String> stream = Files.lines(path)) {

            stream.forEach(line -> {
                if (line.length() > 0) {
                    Line temp = new Line(line, relationsCollection);
                    temp.analyze();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getRelationsCollection- getter for relationsCollection.
     * @return RelationsCollection relationsCollection
     */
    public RelationsCollection getRelationsCollection() {
        return relationsCollection;
    }
}
