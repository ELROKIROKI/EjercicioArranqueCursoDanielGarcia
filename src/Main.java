import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String[] tareas = new String[10];
        boolean[] completadas = new boolean[10];
        int contador = 0;

        int opcion;
        do {
            System.out.println("\n--- GESTOR DE TAREAS ---");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Ver tareas");
            System.out.println("3. Completar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                case 1:
                    if (contador < tareas.length) {
                        System.out.print("Escribe la tarea: ");
                        String tarea = teclado.nextLine();
                        tareas[contador] = tarea;
                        completadas[contador] = false;
                        ++contador;
                        System.out.println("Tarea añadida.");
                    } else {
                        System.out.println("No puedes añadir más tareas.");
                    }
                    break;
                case 2:
                    if (contador == 0) {
                        System.out.println("No hay tareas.");
                    } else {
                        System.out.println("\n--- TAREAS ---");

                        for(int i = 0; i < contador; ++i) {
                            String estado;
                            if (completadas[i]) {
                                estado = "[X]";
                            } else {
                                estado = "[ ]";
                            }

                            System.out.println(i + 1 + ". " + estado + " " + tareas[i]);
                        }
                    }
                    break;
                case 3:
                    if (contador == 0) {
                        System.out.println("No hay tareas para completar.");
                    } else {
                        System.out.print("Número de tarea completada: ");
                        int numero = teclado.nextInt();
                        teclado.nextLine();
                        if (numero >= 1 && numero <= contador) {
                            completadas[numero - 1] = true;
                            System.out.println("Tarea completada.");
                            continue;
                        }

                        System.out.println("Ese número no existe.");
                    }
                    break;
                case 4:
                    if (contador == 0) {
                        System.out.println("No hay tareas para eliminar.");
                    } else {
                        System.out.print("Número de tarea que quieres eliminar: ");
                        int numero = teclado.nextInt();
                        teclado.nextLine();
                        if (numero >= 1 && numero <= contador) {
                            for(int i = numero - 1; i < contador - 1; ++i) {
                                tareas[i] = tareas[i + 1];
                                completadas[i] = completadas[i + 1];
                            }

                            tareas[contador - 1] = null;
                            completadas[contador - 1] = false;
                            --contador;
                            System.out.println("Tarea eliminada.");
                            continue;
                        }

                        System.out.println("Ese número no existe.");
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while(opcion != 0);

        teclado.close();
    }
}
