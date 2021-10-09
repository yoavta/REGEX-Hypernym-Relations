import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author yoav tamir
 * @version 1.0
 */
public class PatternsCollection {

    private List<Pattern> patternsList;
    private List<Pattern> specialPatternList;

    /**
     * constructor for PatternsCollection.
     */
    public PatternsCollection() {
        patternsList = new ArrayList<>();
        specialPatternList = new ArrayList<>();
        addPatterns();
    }

    /**
     * addPatterns - adding the pattern to this object.
     */
    private void addPatterns() {

        Pattern suchAsX1 = Pattern.compile("<np>([^>]*)</np>[ ]*,? *such as *?([ ]*<np>([^>]*)</np>)"
                + "( *,? *[ ]*<np>([^>]*)</np>)*(?:(?: *,? <np>([^>]*)</np>)* *,? *(?:and|or)+ *<np>([^>]*)</np>)?");
        Pattern suchAsX2 = Pattern.compile("<np>([^>]*)</np>");
        Pattern suchXAs1 = Pattern.compile("such <np>([^>]*)</np> * as *?([ ]*<np>([^>]*)</np>)"
                + "(?:(?: *,? <np>([^>]*)</np>)* *(?:and|or)+ *<np>([^>]*)</np>)?");
        Pattern suchXAs2 = Pattern.compile("<np>([^>]*)</np>");
        Pattern includedX1 = Pattern.compile("<np>([^<]*)</np>[ ]*,?\\s*including  *?([ ]*<np>([^>]*)</np>)"
                + "( *,? *[ ]*<np>([^>]*)</np>)*(?:(?: *,? <np>([^>]*)</np>)* *,? *(?:and|or)+ *<np>([^>]*)</np>)?");
        Pattern includedX2 = Pattern.compile("<np>([^>]*)</np>");
        Pattern especiallyX1 = Pattern.compile("<np>([^<]*)</np>[ ]*,?\\s*especially * <np>([^<]*)</np>[ ]"
                + "(?:(?: *,? <np>([^>]*)</np>)* *(?:and|or)+ *<np>([^>]*)</np>)?");
        Pattern especiallyX2 = Pattern.compile("<np>([^>]*)</np>");
        Pattern xWhicIs1 = Pattern.compile("<np>([^<]*)</np>[ ]*,? *which is *(?:"
                + "(?: *an example| *a kind| * a class) *of)? *<np>([^<]*)</np>");
        Pattern xWhicIs2 = Pattern.compile("<np>([^>]*)</np>");

        patternsList.add(includedX1);
        patternsList.add(includedX2);
        patternsList.add(suchAsX1);
        patternsList.add(suchAsX2);
        patternsList.add(suchXAs1);
        patternsList.add(suchXAs2);
        patternsList.add(especiallyX1);
        patternsList.add(especiallyX2);

//
        specialPatternList.add(xWhicIs1);
        specialPatternList.add(xWhicIs2);
    }

    /**
     * getLength- getter for patternsList.size.
     * @return patternsList.size() int.
     */
    public int getLength() {
        return patternsList.size();
    }
    /**
     * getSpecialLength- getter for specialPatternList.size.
     * @return specialPatternList.size() int.
     */
    public int getSpecialLength() {
        return specialPatternList.size();
    }

    /**
     * getPatterns- getter for patternsList.
     * @return List<Pattern> patternsList
     */
    public List<Pattern> getPatterns() {
        return patternsList;
    }
    /**
     * getSpecialPatternList- getter for specialPatternList.
     * @return List<Pattern> specialPatternList
     */
    public List<Pattern> getSpecialPatternList() {
        return specialPatternList;
    }
}
