import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yoav tamir
 * @version 1.0
 */
public class Line {
    private String line;
    private PatternsCollection patternsCollection;
    private RelationsCollection relationsCollection;

    /**
     * constructor for Line.
     * @param line String
     * @param relationsCollection RelationsCollection
     */
    public Line(String line, RelationsCollection relationsCollection) {
        this.line = line;
        this.patternsCollection = new PatternsCollection();
        this.relationsCollection = relationsCollection;
    }

    /**
     * analyze - analyze a single line according to the patterns.
     */
    public void analyze() {
        int numOfNormalPattern = (int) patternsCollection.getLength() / 2;
        for (int i = 0; i <= numOfNormalPattern; i += 2) {
            Pattern mainPattern = patternsCollection.getPatterns().get(i);
            Pattern subPattern = patternsCollection.getPatterns().get(i + 1);
            Matcher mainMatcher = mainPattern.matcher(line);

            while (mainMatcher.find()) {
                String subText = mainMatcher.group(0).substring(1);
                Matcher subMatcher = subPattern.matcher(subText);
//                System.out.println(" hypernm " + mainMatcher.group(1));//debug
                List<Hyponym> hyponymTempList = new ArrayList<>();

                while (subMatcher.find()) {
                    hyponymTempList.add(new Hyponym(subMatcher.group(1)));
//                    System.out.println(" hypenm " + subMatcher.group(1));//debug
                }
                relationsCollection.addRelation(mainMatcher.group(1), hyponymTempList);
            }


        }
        if (patternsCollection.getSpecialLength() == 0) {
            return;
        }
        int numOfSpecialPattern = (int) patternsCollection.getSpecialLength() / 2;
        for (int j = 0; j <= numOfSpecialPattern; j += 2) {
            Pattern mainPattern = patternsCollection.getSpecialPatternList().get(j);
            Matcher mainMatcher = mainPattern.matcher(line);

            List<Hyponym> hyponymTempList = new ArrayList<>();
            while (mainMatcher.find()) {
                hyponymTempList.add(new Hyponym(mainMatcher.group(1)));
                relationsCollection.addRelation(mainMatcher.group(2), hyponymTempList);
            }


        }
    }


}
