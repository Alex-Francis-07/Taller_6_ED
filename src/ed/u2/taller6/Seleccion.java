package ed.u2.taller6;

public class Seleccion {
    public static <T extends Comparable<T>> void sort(T[] a, Metrica metrica) {
        long start = System.nanoTime();
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                metrica.comparisons++;
                if (a[j].compareTo(a[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                metrica.swaps++;
                T temp = a[minIndex];
                a[minIndex] = a[i];
                a[i] = temp;
            }
        }
        long end = System.nanoTime();
        metrica.timeNs = end - start;
    }
}
