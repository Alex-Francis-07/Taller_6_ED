***Taller 6: Comparación Experimental de Ordenación***
  ->Asignatura: Estructura de Datos
  ->Integrantes:
Alex Sigcho
Ivett Zaragocin 

  1. Contexto del Proyecto

Este proyecto mide y compara el rendimiento real de tres algoritmos de ordenación: Bubble Sort, Insertion Sort y Selection Sort
A diferencia de ejercicios anteriores, aquí no buscamos simplemente ordenar un arreglo de números. El objetivo es aplicar el método científico para analizar el comportamiento de los algoritmos bajo condiciones controladas, utilizando:

- Objetos complejos: Ordenamos Citas (por fecha), Pacientes (por apellido) e Items de inventario (por stock), utilizando Generics en Java.
- Reproducibilidad: Usamos semillas aleatorias
- Métricas precisas: Medimos tiempo en nanosegundos, comparaciones e intercambios 

  2. Arquitectura y Funcionamiento de Clases
El proyecto está modularizado en 4 componentes principales que interactúan entre sí:

  A.GeneradorDataset

Es la responsable de crear los insumos del experimento
Función: Genera automáticamente 4 archivos CSV con datos específicos (Citas aleatorias, Citas casi ordenadas, Pacientes con duplicados, Inventario inverso).
Modelos: Define las clases internas Cita, Paciente e Item, implementando la interfaz Comparable para definir sus criterios de ordenación.

  B. Metrica

Actúa como marcador
Función: Es un objeto contenedor que viaja con el algoritmo.
Objetivo: Acumula contadores (comparisons, swaps, timeNs)

C. Bubble, Insertion, Selection

Genéricos: Usan <T extends Comparable<T>> para ordenar cualquier tipo de objeto.
Instrumentados: Reciben el objeto Metrica y registran cada operación

D. Generador

Es la clase principal (main). Su flujo es:
Verificación: Asegura que los CSV existan (si no, llama al Generador).
Ciclo de Pruebas (R=10): Ejecuta cada algoritmo 10 veces sobre copias limpias de los datos.

