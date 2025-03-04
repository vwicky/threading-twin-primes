package SaveHandler;

import Solver.AnswerWrapper;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class SaveHandler {

    public static void saveOutputTo(AnswerWrapper answer, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) { // Append mode
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Write header if file is empty
            boolean isNewFile = new java.io.File(filePath).length() == 0;
            if (isNewFile) {
                writer.write("N,Count,Time(ms),Threads,Date Run\n");
            }

            // Write data row
            writer.write(String.format("%d,%d,%d,%d,%s\n",
                    answer.getN(), answer.getCount(), answer.getTimeSpent(), answer.getNumOfThreads(),
                    dateFormat.format(answer.getDateRun())));

            System.out.println("Data successfully saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }
}
