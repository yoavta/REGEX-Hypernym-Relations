import java.io.IOException;
/**
 * @author yoav tamir
 * @version 1.0
 */
public class DiscoverHypernym {
    /**
     * main function of the DiscoverHypernym method.
     * @param args String[]
     * @throws IOException e
     */
    public static void main(String[] args) throws IOException {
        String dir = args[0];
        String lemma = args[1];
        DataFlow dataFlow = new DataFlow(args);
        dataFlow.process();
        dataFlow.getRelationsCollection().findByLemma(lemma);
        dataFlow.printTo(2);
    }
}
