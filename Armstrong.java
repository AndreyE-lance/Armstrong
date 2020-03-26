
/* This little code is looking for Armstrong numbers. "Only" 6014 ms for Long.MAX_VALUE with i7-3770.*/
import java.util.ArrayList;

import java.util.TreeSet;

public class Armstrong {
    public static ArrayList<Integer> iList = new ArrayList<>();
    public static long[][] lArray = new long[20][10];
    public static TreeSet<Long> lSet = new TreeSet<Long>();
    static long glN = 0;

    static {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 20; j++) {
                long sig = i;
                for (int p = 0; p < j - 1; p++) {
                    sig = sig * i;
                }
                lArray[j][i] = sig;
            }
        }
    }

    private static long getArmstrong(long numb) {
        long sum = 0;
        int digNumb = toDigit(numb);
        do {
            sum += lArray[digNumb][(int) (numb % 10)];
        } while ((numb /= 10) > 0);
        if (digNumb != toDigit(sum)) {
            return -1;
        }
        long sum1 = 0;
        for (long i = sum; i > 0; i = i / 10) {
            sum1 += lArray[digNumb][(int) (i % 10)];
        }
        if (sum1 != sum) return -1;
        if (sum == 0) return -1;
        if (sum >= glN) return -1;
        return sum;
    }

    private static int toDigit(long numb) {
        long temp = 0L;
        int digNumb = 1;
        while ((temp = (temp << 3) + (temp << 1) + 9L) < numb && temp > 0)
            digNumb++;
        return digNumb;
    }

    public static long isNext(long l) {
        if (l < 10) {
            l++;
            return l;
        }
        if ((l % 10 != 0)) {
            l++;
            return l;
        } else {
            l++;
            if (check(l)) return l;
            else {
                l = buildSuit();
                return l;
            }
        }

    }

    public static boolean check(long l) {
        iList.clear();
        parseInt(l);
        for (int i = 0; i < iList.size() - 1; i++) {
            if (iList.get(i) > iList.get(i + 1)) return false;
        }
        return true;
    }

    public static long buildSuit() {
        int last = iList.get(iList.size() - 1);
        for (int i = iList.size() - 1; i > 0; i--) {
            if (iList.get(i - 1) == 0) {
                iList.set(i - 1, last);
                iList.set(i, 0);
            } else {
                if (iList.get(i - 1) <= iList.get(i)) {
                    break;
                } else {
                    iList.set(i, iList.get(i - 1));
                }
            }
        }
        return toLong(iList);
    }

    public static void parseInt(long lInput) {
        if (lInput == 0L) return;
        int lDiv = (int) (lInput % 10L);
        parseInt(lInput / 10);
        iList.add(lDiv);

    }

    public static long toLong(ArrayList<Integer> inpList) {
        StringBuilder out = new StringBuilder();
        for (int i : inpList) {
            out.append(i);
        }
        try {
            return Long.parseLong(out.toString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static long[] getNumbers(long N) {
        lSet.clear();
        glN = N;

        long[] result = null;
        if (N <= 0){
            result = new long[0];
            return result;
        }
        for (long i = 0; i < N ; ) {
            i = isNext(i);
            if (i < 0)
                break;
            long f = getArmstrong(i);
            if (f != -1) {
                lSet.add(f);
            }
        }
        if (lSet.size() > 0) {
            int t = 0;
            result = new long[lSet.size()];
            for (long l : lSet) {
                result[t] = l;
                t++;
            }

        } else {
            result = new long[0];
        }
        return result;
    }

    public static void main(String[] args) {
        lSet.clear();
        iList.clear();
        glN=0;
        Long timeS = System.currentTimeMillis();
        long outArray[] = getNumbers(Long.MAX_VALUE);
        Long timeF = System.currentTimeMillis();
        for (long l : outArray) {
            System.out.println(l);
        }
        System.out.println();
        System.out.println(lSet.size());
        System.out.println("Array: " + (timeF - timeS) + " ms");

    }
}
