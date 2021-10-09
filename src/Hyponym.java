/**
 * @author yoav tamir
 * @version 1.0
 */
public class Hyponym implements Comparable<Hyponym> {

    private String name;
    private int appearNumber;

    /**
     * constructor for Hyponym.
     *
     * @param name String
     */
    public Hyponym(String name) {
        this.name = name;
        this.appearNumber = 1;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    /**
     * getName- getter for name.
     * @return name String.
     */
    public String getName() {
        return name;
    }

    /**
     * AddAppear- add one connection to the same Hypernym-Hyponym connection.
     */
    public void addAppear() {
        appearNumber++;
    }

    /**
     * getAppearNumber- getter for appearNumber.
     * @return appearNumber int
     */
    public int getAppearNumber() {
        return appearNumber;
    }

    @Override
    public int compareTo(Hyponym other) {
        if (appearNumber > other.getAppearNumber()) {
            return -1;
        } else if (appearNumber < other.getAppearNumber()) {
            return 1;
        } else {
            return (getName().compareTo(other.getName()));
        }
    }
}
