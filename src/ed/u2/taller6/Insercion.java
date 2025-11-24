package ed.u2.taller6;

public class Insercion {
    public static <T extends Comparable<T>> void sort(T[] a, Metrica metrica) {
        long start = System.nanoTime();
        int n = a.length;
        for (int i = 1; i < n; i++) {
            T key = a[i];
            int j = i - 1;

            /* Nota sobre Instrumentación:
             * La comparación a[j] > key se evalúa en cada iteración del while.
             * Incluso cuando falla (termina el while), se hizo una comparación.
             */
            while (j >= 0) {
                metrica.comparisons++;
                if (a[j].compareTo(key) > 0) {
                    metrica.swaps++; // Contamos el desplazamiento como un swap/escritura
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
        long end = System.nanoTime();
        metrica.timeNs = end - start;
    }
}
