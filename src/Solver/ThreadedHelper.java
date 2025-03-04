package Solver;

import java.time.Duration;
import java.time.Instant;

public class ThreadedHelper extends Thread {
    public String threadName;
    public int localCount = 0;
    public Duration timeSpent;
    public int leftLimit, rightLimit;

    ThreadedHelper(String name, int leftLimit, int rightLimit) {
        this.threadName = name;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
    }

    @Override
    public void run() {
        // we trace through all the possible k's and form probable twin primes
        // and then check they're prime

        //System.out.println("\t> " + this.threadName + " started, {" + leftLimit + ", " + rightLimit + "} ");
        Instant start = Instant.now();
        this.localCount = 0;
        for (int i = this.leftLimit; i < this.rightLimit; i++) {
            long x1 = 6L * i - 1;
            long x2 = 6L * i + 1;

            //System.out.print(">\t" + x1 + " & " + x2);
            if (PrimeChecker.isPrime(x1) && PrimeChecker.isPrime(x2)){
                //System.out.println(">\t" + x1 + " & " + x2);
                //System.out.print("  -- YES");
                this.localCount++;
            }
            //System.out.println();
        }
        Instant end = Instant.now();
        this.timeSpent = Duration.between(start, end);
        //System.out.println("\t> " + this.threadName + " finished in " + timeSpent.toMillis() + " milliseconds");
    }
}
