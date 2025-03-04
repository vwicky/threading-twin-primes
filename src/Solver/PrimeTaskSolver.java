package Solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrimeTaskSolver {
    private int nInput;
    private int k;
    private int numThreads;
    private int step;

    /**
     * @param userN - n that user provided
     * @return whether it is valid or not
     */
    @Deprecated
    private boolean checkInput(int userN) {
        return true;
    }
    private void extractInput(Scanner scanner) {
        int tempN = scanner.nextInt();
        if (checkInput(tempN)) {
            this.nInput = tempN;
        }
        else {
            System.out.println("> wrong input data...");
        }
    }

    /**
     * @return a fully input-filled 'this' object
     */
    public PrimeTaskSolver initiate() {
        System.out.print("> enter n:");
        Scanner scanner = new Scanner(System.in);
        this.extractInput(scanner);
        return this;
    }
    public PrimeTaskSolver initiate(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            this.extractInput(scanner);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("> could not find the file " + filePath);
        }
        return this;
    }
    public PrimeTaskSolver showSettings() {
        System.out.println("\n================= Solver Settings =================");
        System.out.printf("| %-15s : %d |\n", "n", this.nInput);

        this.k = Math.floorDiv(this.nInput - 1, 6);
        System.out.printf("| %-15s : %d (%d possible pairs) |\n", "k", k, (k - 1));

        System.out.print("| Enter num of threads: ");
        try (Scanner scanner = new Scanner(System.in)) {
            this.numThreads = scanner.nextInt();
        }

        this.step = k / numThreads;
        System.out.printf("| %-15s : %d |\n", "step", step);
        System.out.println("===================================================");

        return this;
    }

    public AnswerWrapper solve() {
        Map<Integer, ThreadedHelper> threadMap = new HashMap<>();
        int begin, end = 1;
        for (int i = 0; i < numThreads; i++) {
            begin = end;
            end = begin + step + 1;
            //System.out.println(begin + " " + end);
            threadMap.put(i, new ThreadedHelper("name " + i, begin, end));
        }

        ExecutorService executor = Executors.newCachedThreadPool();
        for (var thread : threadMap.values()) {
            executor.execute(thread);
        }
        executor.close();

        System.out.println("THREADS:");
        long maxTimeSpentMillis = 0;
        int count = 1; // considering (3, 5) pair
        for (var thread : threadMap.values()) {
            count += thread.localCount;
            maxTimeSpentMillis = Math.max(maxTimeSpentMillis, thread.timeSpent.toMillis());

            System.out.println(">\t" + thread.threadName + " :: k[" + thread.leftLimit + " - " + thread.rightLimit + "] :: " + thread.timeSpent.toMillis() + "ms");
        }

        return new AnswerWrapper(
                nInput, count, maxTimeSpentMillis, numThreads, new Date()
        );
    }

    public int getNInput() {
        return nInput;
    }
}
