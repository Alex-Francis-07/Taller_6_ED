import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneradorDataset {
    private static final long SEED = 42;
    private static final Random rand = new Random(SEED);
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static void main(String[] args) {
        try {
            generateCitas();
            generateCitasCasiOrdenadas();
            generatePacientes();
            generateInventario();
            System.out.println("Datasets generados exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateCitas() throws IOException {
        String[] apellidos = {"Garcia", "Lopez", "Perez", "Gomez", "Sanchez", "Diaz", "Torres"};
        try (PrintWriter pw = new PrintWriter(new FileWriter("citas_100.csv"))) {
            pw.println("id;apellido;fechaHora");
            for (int i = 1; i <= 100; i++) {
                LocalDateTime dt = LocalDateTime.of(2025, 3, 1 + rand.nextInt(30), 8 + rand.nextInt(10), rand.nextInt(60));
                pw.printf("CITA-%03d;%s;%s%n", i, apellidos[rand.nextInt(apellidos.length)], dt.format(FMT));
            }
        }
    }

    private static void generateCitasCasiOrdenadas() throws IOException {
        List<Cita> lista = new ArrayList<>();
        String[] apellidos = {"Ruiz", "Mendoza", "Silva", "Rojas"};
        for (int i = 1; i <= 100; i++) {
            LocalDateTime dt = LocalDateTime.of(2025, 3, 1 + rand.nextInt(30), 8 + rand.nextInt(10), rand.nextInt(60));
            lista.add(new Cita("CITA-" + i, apellidos[rand.nextInt(apellidos.length)], dt));
        }
        Collections.sort(lista);
        for (int k = 0; k < 5; k++) {
            int i = rand.nextInt(100);
            int j = rand.nextInt(100);
            Collections.swap(lista, i, j);
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter("citas_100_casi_ordenadas.csv"))) {
            pw.println("id;apellido;fechaHora");
            for (Cita c : lista) pw.printf("%s;%s;%s%n", c.id, c.apellido, c.fecha.format(FMT));
        }
    }

    private static void generatePacientes() throws IOException {
        String[] apellidos = {"Ramirez", "Zambrano", "Vera", "Loor", "Mera"}; // Pocos para forzar duplicados
        try (PrintWriter pw = new PrintWriter(new FileWriter("pacientes_500.csv"))) {
            pw.println("id;apellido;prioridad");
            for (int i = 1; i <= 500; i++) {
                pw.printf("PAC-%04d;%s;%d%n", i, apellidos[rand.nextInt(apellidos.length)], 1 + rand.nextInt(5));
            }
        }
    }

    private static void generateInventario() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter("inventario_500_inverso.csv"))) {
            pw.println("id;insumo;stock");
            for (int i = 0; i < 500; i++) {
                // Stock inverso: 500, 499, ...
                pw.printf("ITEM-%04d;Generico;%d%n", i + 1, (500 - i));
            }
        }
    }
}
