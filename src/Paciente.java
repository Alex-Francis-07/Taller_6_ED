class Paciente implements Comparable<Paciente> {
    String id, apellido;
    int prioridad;

    public Paciente(String id, String apellido, int prioridad) {
        this.id = id;
        this.apellido = apellido;
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Paciente o) {
        return this.apellido.compareTo(o.apellido);
    }
}
