package main.java.umg.edu;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("===================================================================\n" +
                    "\n" +
                    "       ___   _                      _  _                     \n" +
                    "      / _ \\ | |                    (_)| |                    \n" +
                    "     / /_\\ \\| |  __ _   ___   _ __  _ | |_  _ __ ___    ___  \n" +
                    "     |  _  || | / _` | / _ \\ | '__|| || __|| '_ ` _ \\  / _ \\ \n" +
                    "     | | | || || (_| || (_) || |   | || |_ | | | | | || (_) |\n" +
                    "     \\_| |_/|_| \\__, | \\___/ |_|   |_| \\__||_| |_| |_| \\___/ \n" +
                    "                 __/ |                                       \n" +
                    "                |___/                                        \n" +
                    "______                           _  ______         _      _        \n" +
                    "| ___ \\                         | | | ___ \\       | |    (_)       \n" +
                    "| |_/ /  ___   _   _  _ __    __| | | |_/ /  ___  | |__   _  _ __  \n" +
                    "|    /  / _ \\ | | | || '_ \\  / _` | |    /  / _ \\ | '_ \\ | || '_ \\ \n" +
                    "| |\\ \\ | (_) || |_| || | | || (_| | | |\\ \\ | (_) || |_) || || | | |\n" +
                    "\\_| \\_| \\___/  \\__,_||_| |_| \\__,_| \\_| \\_| \\___/ |_.__/ |_||_| |_|\n" +
                    "                                                                   \n" +
                    "                                                                   \n" +
                    "===================================================================\n" +
                    "                  Proyecto de Sistemas Operativos    \n" +
                    "               Simulador de Planificador de Procesos       \n" +
                    "===================================================================\n" +
                    " [1]  Simular utilizando el Algoritmo Round Robin\n" +
                    " [0]  Salir del programa\n" +
                    "===================================================================\n" +
                    "Ingrese una opción: ");
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                opcion = -1;
                scanner.next();
            }

            switch (opcion) {
                case 1:
                    limpiarConsola();
                    ejecutarRoundRobin(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    limpiarConsola();
                    System.out.println("\u001B[31mOpción no válida. Por favor, intente de nuevo.\u001B[0m");
            }
        } while (opcion != 0);
    }

    private static void ejecutarRoundRobin(Scanner scanner) {
        int numProcesos = solicitarNumero(scanner, "Ingrese el número de procesos: ");

        List<Proceso> procesos = new ArrayList<>();
        for (int i = 0; i < numProcesos; i++) {
            int tiempoRafaga = solicitarNumero(scanner, "Ingrese el tiempo de ráfaga para el proceso " + (char) ('A' + i) + ":  ");
            int tiempoLlegada = solicitarNumero(scanner, "Ingrese el tiempo de llegada para el proceso " + (char) ('A' + i) + ": ");
            procesos.add(new Proceso(i + 1, tiempoRafaga, tiempoLlegada));
        }

        int quantum = solicitarNumero(scanner, "Ingrese el tiempo de quantum: ");

        PlanificadorRoundRobin planificador = new PlanificadorRoundRobin(procesos, quantum);
        planificador.planificar();
        System.out.println("\nPresione Enter para regresar...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        limpiarConsola();
    }

    private static int solicitarNumero(Scanner scanner, String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                break;
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.next();
            }
        }
        return numero;
    }

    private static void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la consola.");
        }
    }
}