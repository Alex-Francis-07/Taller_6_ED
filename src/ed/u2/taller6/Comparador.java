package ed.u2.taller6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Comparador {
    private static final int R = 10; // Repeticiones
    private static final int WARMUP = 3; // Descarte inicial
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static void main(String[] args) {
        // 1. Asegurar que existen los datasets
        GeneradorDataset.main(null);

        System.out.println("\n=== TALLER 6: COMPARACIÓN DE ORDENACIÓN ===");
        System.out.printf("%-12s %-22s %-5s %-12s %-12s %-10s%n",
                "ALGORITMO", "DATASET", "N", "TIEMPO(ns)", "COMPS", "SWAPS");
        System.out.println("-".repeat(80));

        try {
            // Cargar Datasets (Aislamiento de I/O)
            Cita[] citas = loadCitas("citas_100.csv");
            Cita[] citasCasi = loadCitas("citas_100_casi_ordenadas.csv");
            Paciente[] pacientes = loadPacientes("pacientes_500.csv");
            Items[] inventario = loadInventario(("inventario_500_inverso.csv"));

            // Ejecutar Experimentos
            runFullExperiment("Citas(Rand)", citas);
            runFullExperiment("Citas(Casi)", citasCasi);
            runFullExperiment("Pacientes(Dup)", pacientes);
            runFullExperiment("Inv(Inverso)", inventario);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static <T extends Comparable<T>> void runFullExperiment(String datasetName, T[] originalData) {
        String[] algos = {"ed.u2.taller6.Insercion", "ed.u2.taller6.Seleccion", "ed.u2.taller6.Burbuja"};

        for (String algo : algos) {
            List<Long> times = new ArrayList<>();
            Metrica finalMetrica = new Metrica();

            for (int i = 0; i < R; i++) {
                // CLONAR para no ordenar un array ya ordenado en la siguiente vuelta
                T[] copiaData = Arrays.copyOf(originalData, originalData.length);
                Metrica m = new Metrica();

                switch (algo) {
                    case "ed.u2.taller6.Insercion": Insercion.sort(copiaData, m); break;
                    case "ed.u2.taller6.Seleccion": Seleccion.sort(copiaData, m); break;
                    case "ed.u2.taller6.Burbuja": Burbuja.sort(copiaData, m); break;
                }
                if (i >= WARMUP) {
                    times.add(m.timeNs);
                }
                finalMetrica = m;
            }

            // Calcular Mediana
            Collections.sort(times);
            long medianTime = times.get(times.size() / 2);

            System.out.printf("%-12s %-22s %-5d %-12d %-12d %-10d%n",
                    algo, datasetName, originalData.length, medianTime, finalMetrica.comparisons, finalMetrica.swaps);
        }
        System.out.println("-".repeat(80));
    }

    // --- Cargadores CSV ---
    private static Cita[] loadCitas(String file) throws IOException {
        List<Cita> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // Header
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                list.add(new Cita(p[0], p[1], LocalDateTime.parse(p[2], FMT)));
            }
        }
        return list.toArray(new Cita[0]);
    }

    private static Paciente[] loadPacientes(String file) throws IOException {
        List<Paciente> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                list.add(new Paciente(p[0], p[1], Integer.parseInt(p[2])));
            }
        }
        return list.toArray(new Paciente[0]);
    }

    private static Items[] loadInventario(String file) throws IOException {
        List<Items> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                list.add(new Items(p[0], p[1], Integer.parseInt(p[2])));
            }
        }
        return list.toArray(new Items[0]);
    }
}
