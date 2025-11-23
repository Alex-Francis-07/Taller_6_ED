package ed.u2.taller6;

class Items implements Comparable<Items> {
    String id, insumo;
    int stock;

    public Items(String id, String insumo, int stock) {
        this.id = id;
        this.insumo = insumo;
        this.stock = stock;
    }

    @Override
    public int compareTo(Items o) {
        return Integer.compare(this.stock, o.stock);
    }
}
