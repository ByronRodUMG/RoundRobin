package main.java.umg.edu;

import java.util.*;

public class PlanificadorRoundRobin {
    private List<Proceso> procesos;
    private int quantum;

    public PlanificadorRoundRobin(List<Proceso> procesos, int quantum) {
        this.procesos = procesos;
        this.quantum = quantum;
    }

    public void planificar() {
        Queue<Proceso> cola = new LinkedList<>();
        int tiempoActual = 0;
        int tiempoEsperaTotal = 0;
        int tiempoRetornoTotal = 0;
        List<Proceso> ordenEjecucion = new ArrayList<>();

        // Ordenar procesos por tiempo de llegada
        procesos.sort(Comparator.comparingInt(Proceso::getTiempoLlegada));

        // Agregar procesos a la cola según el tiempo de llegada
        int indice = 0;
        while (indice < procesos.size() && procesos.get(indice).getTiempoLlegada() <= tiempoActual) {
            cola.add(procesos.get(indice));
            indice++;
        }

        while (!cola.isEmpty()) {
            Proceso procesoActual = cola.poll();
            ordenEjecucion.add(procesoActual);

            if (procesoActual.getTiempoRestante() > quantum) {
                tiempoActual += quantum;
                procesoActual.setTiempoRestante(procesoActual.getTiempoRestante() - quantum);
            } else {
                tiempoActual += procesoActual.getTiempoRestante();
                procesoActual.setTiempoRestante(0);
                procesoActual.setTiempoRetorno(tiempoActual - procesoActual.getTiempoLlegada());
                procesoActual.setTiempoEspera(procesoActual.getTiempoRetorno() - procesoActual.getTiempoRafaga());
                tiempoEsperaTotal += procesoActual.getTiempoEspera();
                tiempoRetornoTotal += procesoActual.getTiempoRetorno();
            }

            // Agregar nuevos procesos a la cola según el tiempo de llegada
            while (indice < procesos.size() && procesos.get(indice).getTiempoLlegada() <= tiempoActual) {
                cola.add(procesos.get(indice));
                indice++;
            }

            // Re-agregar el proceso actual a la cola si no ha terminado
            if (procesoActual.getTiempoRestante() > 0) {
                cola.add(procesoActual);
            }
        }

        // Imprimir resultados
        System.out.print("\nOrden de ejecución:\n\n\t");
        for (int i = 0; i < ordenEjecucion.size(); i++) {
            System.out.print((char) ('A' + ordenEjecucion.get(i).getId() - 1));
            if (i < ordenEjecucion.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();

        // Imprimir tabla de tiempos de espera y retorno
        System.out.println("\nTiempos de espera y retorno:\n");
        System.out.println("Proceso   T. Espera   T. Retorno");
        for (Proceso proceso : procesos) {
            System.out.println("   " + (char) ('A' + proceso.getId() - 1) + "         " + proceso.getTiempoEspera() + "\t\t " + proceso.getTiempoRetorno());
        }

        System.out.println(String.format("\nTiempo de espera promedio:  %.2f", (double) tiempoEsperaTotal / procesos.size()));
        System.out.println(String.format("Tiempo de retorno promedio: %.2f", (double) tiempoRetornoTotal / procesos.size()));
    }
}