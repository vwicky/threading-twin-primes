package Solver;

import java.util.Arrays;

public class PrimeChecker {
    private static final int[] SMALL_PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37};

    public static boolean isPrime(long x) {
        if (x < 2) return false;
        if (x < 40) return Arrays.binarySearch(SMALL_PRIMES, (int) x) >= 0;
        if (x % 2 == 0 || x % 3 == 0 || x % 5 == 0) return false;

        // 6k ± 1 optimization: Checking divisibility up to √x
        for (long i = 7; i * i <= x; i += 6) {
            if (x % i == 0 || x % (i + 4) == 0) return false;
        }
        return true;
    }
}
