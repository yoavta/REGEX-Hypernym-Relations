import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.TreeMap;
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
public class RelationsCollection {
    private Map<String, Hypernym> collection;
    private Map<String, Integer> listFor2;

    /**
     * constructor for RelationsCollection.
     */
    public RelationsCollection() {
        collection = new TreeMap<String, Hypernym>();
        listFor2 = new TreeMap<String, Integer>();
    }

    /**
     * addRelation - adding new relation to the map.
     *
     * @param hypernymStr     String
     * @param hyponymTempList List<Hyponym>
     */
    public void addRelation(String hypernymStr, List<Hyponym> hyponymTempList) {
        Hypernym hypernym;
        if (collection.containsKey(hypernymStr)) {
            hypernym = collection.get(hypernymStr);
        } else {
            hypernym = new Hypernym(hypernymStr);
            collection.put(hypernymStr, hypernym);
        }

        for (Hyponym hyponym : hyponymTempList) {
            Map<String, Hyponym> hyponymList = hypernym.getHyponymList();

            if (hyponymList.containsKey(hyponym.getName())) {
                hyponymList.get(hyponym.getName()).addAppear();
            } else {
                hyponymList.put(hyponym.getName(), hyponym);
            }
        }
    }

    /**
     * printToFile - print to file in a given path.
     *
     * @param path String
     * @throws IOException e
     */
    public void printToFile(String path) throws IOException {
        sortCollectionByName();
//        int hypernymCounter = 0; //debug
        File outputFile = new File(path);
        FileOutputStream fos = new FileOutputStream(outputFile);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (Map.Entry<String, Hypernym> hypernymEntry : collection.entrySet()) {
            if (hypernymEntry.getValue().isItImportant()) {
                String temp = hypernymEntry.getKey() + ": " + hypernymEntry.getValue().listToString();
                bw.write(temp);
                bw.newLine();
//                hypernymCounter++; //debug
            }
        }


        bw.close();
//        System.out.println("total of " + hypernymCounter + "hypernyms."); //debug
    }


//    public int howManyConnections() {
//        int sum = 0;
//        for (Map.Entry<String, Hypernym>
//                hypernymEntry : collection.entrySet()) {
//            if (hypernymEntry.getValue().isItImportant()) {
//                sum += hypernymEntry.getValue().sumElements();
//            }
//        }
//        ;
//        return sum;
//    }

    /**
     * sortCollectionByName - sort the main collection by name.
     */
    public void sortCollectionByName() {
        List<Map.Entry<String, Hypernym>> list = new LinkedList<Map.Entry<String, Hypernym>>(collection.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Hypernym>>() {
            public int compare(Map.Entry<String, Hypernym> o1,
                               Map.Entry<String, Hypernym> o2) {
                return (o1.getValue().toString()).toLowerCase().compareTo((o2.getValue().toString().toLowerCase()));
            }
        });

        HashMap<String, Hypernym> tempMap = new LinkedHashMap<>();
        for (Map.Entry<String, Hypernym> aa : list) {
            tempMap.put(aa.getKey(), aa.getValue());
        }
        collection = tempMap;
    }

    /**
     * main method for finding lemma relations.
     *
     * @param lemma String
     */
    public void findByLemma(String lemma) {

        for (Map.Entry<String, Hypernym>
                hypernymEntry : collection.entrySet()) {
            if (hypernymEntry.getValue().getHyponymList().containsKey(lemma)
                    && hypernymEntry.getValue().isItImportant()) {
                listFor2.put(hypernymEntry.getValue().getName(),
                        hypernymEntry.getValue().getHyponymList().get(lemma).getAppearNumber());
            }
        }

    }

    /**
     * printToConsole - print the relations of the given lemma to the console.
     *
     * @param path String
     * @throws IOException e
     */
    public void printToConsole(String path) throws IOException {
        sortCollectionByName();
        sortCollectionByValue();

        for (Map.Entry<String, Integer>
                integerEntry : listFor2.entrySet()) {
            String temp = integerEntry.getKey() + ": (" + integerEntry.getValue() + ")";
            System.out.println(temp);

        }

        //        System.out.println("total of " + hypernymCounter + "hypernyms.");
    }

    /**
     * sortCollectionByValue - sort the main collection by number of appearances.
     */
    private void sortCollectionByValue() {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(listFor2.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue().compareTo((o1.getValue())));
            }
        });

        HashMap<String, Integer> tempMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            tempMap.put(aa.getKey(), aa.getValue());
        }
        listFor2 = tempMap;
    }

}
