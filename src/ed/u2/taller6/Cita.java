package ed.u2.taller6;

import java.time.LocalDateTime;

class Cita implements Comparable<Cita> {
    String id, apellido;
    LocalDateTime fecha;

    public Cita(String id, String apellido, LocalDateTime fecha) {
        this.id = id;
        this.apellido = apellido;
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Cita o) {
        return this.fecha.compareTo(o.fecha);
    }

    @Override
    public String toString() {
        return id + ";" + apellido + ";" + fecha;
    }
}
