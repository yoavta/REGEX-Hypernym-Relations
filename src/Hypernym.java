
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;


/**
 * @author yoav tamir
 * @version 1.0
 */
public class Hypernym {

    private LinkedHashMap<String, Hyponym> hyponymList;
    private String name;

    /**
     * constructor for Hypernym.
     *
     * @param name String
     */
    public Hypernym(String name) {
        hyponymList = new LinkedHashMap<String, Hyponym>();
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "";
    }

    /**
     * getHyponymList - getter for hyponymList.
     *
     * @return Map<String, Hyponym> hyponymList
     */
    public Map<String, Hyponym> getHyponymList() {
        return hyponymList;
    }

    /**
     * isItImportant - check if this Hypernym holds more than 2 values.
     *
     * @return boolean value
     */
    public boolean isItImportant() {
        if (hyponymList.size() < 3) {
            return false;
        }
        return true;
    }

    /**
     * listToString- converting a list to a string function.
     *
     * @return String temp
     */
    public String listToString() {
        String temp = "";
        boolean first = true;
        List<Map.Entry<String, Hyponym>> list = new LinkedList<Map.Entry<String, Hyponym>>(hyponymList.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Hyponym>>() {
            public int compare(Map.Entry<String, Hyponym> o1,
                               Map.Entry<String, Hyponym> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        HashMap<String, Hyponym> tempMap = new LinkedHashMap<String, Hyponym>();
        for (Map.Entry<String, Hyponym> aa : list) {
            tempMap.put(aa.getKey(), aa.getValue());
        }

        for (Map.Entry<String, Hyponym>
                hypernymEntry : tempMap.entrySet()) {

            if (first) {
                temp = temp + hypernymEntry.getValue().getName()
                        + " (" + hypernymEntry.getValue().getAppearNumber() + ")";
                first = false;
            } else {
                temp = temp + ", " + hypernymEntry.getKey() + " (" + hypernymEntry.getValue().getAppearNumber() + ")";
            }
        }
        return temp;
    }


//    public int sumElements() {
//        int sum = 0;
//        for (Map.Entry<String, Hyponym>
//                hypernymEntry : hyponymList.entrySet()) {
//            sum += hypernymEntry.getValue().getAppearNumber();
//        }
//        return sum;
//    }

    /**
     * getName - getter for name.
     * @return name String
     */
    public String getName() {
        return name;
    }

}
