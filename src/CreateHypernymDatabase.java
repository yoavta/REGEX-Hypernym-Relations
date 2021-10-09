
import java.io.IOException;

/**
 * @author yoav tamir
 * @version 1.0
 */
public class CreateHypernymDatabase {

    /**
     * main function of the CreateHypernymDatabase method.
     * @param args String[]
     * @throws IOException e
     */
    public static void main(String[] args) throws IOException {

        DataFlow dataFlow = new DataFlow(args);
        dataFlow.process();
        dataFlow.printTo(1);

    }





}




