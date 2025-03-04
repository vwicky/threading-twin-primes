package Solver;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class AnswerWrapper {
    private int n;
    private int count;
    private long timeSpent;
    private int numOfThreads;
    private Date dateRun;

    public AnswerWrapper(
            int n,
            int count,
            long timeSpent,
            int numOfThreads,
            Date dateRun) {
        this.n = n;
        this.count = count;
        this.timeSpent = timeSpent;
        this.numOfThreads = numOfThreads;
        this.dateRun = dateRun;
    }

    public void printBeautiful() {
        String separator = "+------------+------------+------------+------------+---------------------+";
        String header = String.format("| %10s | %10s | %10s | %10s | %19s |",
                "N", "Count", "Time (ms)", "Threads", "Date Run");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String row = String.format("| %10d | %10d | %10d | %10d | %19s |",
                n, count, timeSpent, numOfThreads, dateFormat.format(dateRun));

        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);
        System.out.println(row);
        System.out.println(separator);
    }

    public int getN() {
        return n;
    }

    public int getCount() {
        return count;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public int getNumOfThreads() {
        return numOfThreads;
    }

    public Date getDateRun() {
        return dateRun;
    }
}
