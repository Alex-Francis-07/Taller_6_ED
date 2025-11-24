package ed.u2.taller6;

public class Burbuja {
    public static <T extends Comparable<T>> void sort(T[] a, Metrica metrica) {
        long start = System.nanoTime();
        int n = a.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                metrica.comparisons++;
                if (a[j].compareTo(a[j + 1]) > 0) {
                    metrica.swaps++;
                    T temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        long end = System.nanoTime();
        metrica.timeNs = end - start;
    }
}
