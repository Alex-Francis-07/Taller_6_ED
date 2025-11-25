package ed.u2.taller6;

public class Metrica {
    public long comparisons = 0;
    public long swaps = 0;
    public long timeNs = 0;

    public void reset() {
        comparisons = 0;
        swaps = 0;
        timeNs = 0;
    }
}
