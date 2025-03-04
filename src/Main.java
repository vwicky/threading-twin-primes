import SaveHandler.SaveHandler;
import Solver.AnswerWrapper;
import Solver.PrimeTaskSolver;

import java.io.File;
import java.util.Date;

public class Main {
    private static final String homePath = new File("").getAbsolutePath();
    private static final String inputFilePath = homePath + "\\input-values.txt";
    private static final String outputFilePath = homePath + "\\output-values.csv";

    public static void main(String[] args) {
        /*
        (3, 5), (5, 7), (11, 13), (17, 19), (29, 31), (41, 43), (59, 61),
        (71, 73), (101, 103), (107, 109), (137, 139),
         */
        PrimeTaskSolver solver = new PrimeTaskSolver()
                .initiate(inputFilePath)
                .showSettings();

        AnswerWrapper answer = solver.solve();
        answer.printBeautiful();

        SaveHandler.saveOutputTo(answer, outputFilePath);
    }
}