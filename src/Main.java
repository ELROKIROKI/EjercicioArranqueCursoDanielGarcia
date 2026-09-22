import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String[] tareas = new String[10];
        boolean[] completadas = new boolean[10];
        String[] prioridades = new String[10];
        int contador = 0;

        int opcion;
        do {
            System.out.println("\n--- GESTOR DE TAREAS ---");
            System.out.println("1. Añadir tarea");
            System.out.println("2. Ver tareas");
            System.out.println("3. Completar tarea");
            System.out.println("4. Eliminar tarea");
            System.out.println("5. Filtrar tareas por prioridad");
            System.out.println("6. Guardar tareas en archivo");
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

                        String prioridad;
                        do {
                            System.out.print("Prioridad (alta, media o baja): ");
                            prioridad = teclado.nextLine().trim().toLowerCase();
                            if (!prioridad.equals("alta") && !prioridad.equals("media") && !prioridad.equals("baja")) {
                                System.out.println("Prioridad no válida. Escribe alta, media o baja.");
                            }
                        } while (!prioridad.equals("alta") && !prioridad.equals("media") && !prioridad.equals("baja"));

                        tareas[contador] = tarea;
                        completadas[contador] = false;
                        prioridades[contador] = prioridad;
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

                            System.out.println(i + 1 + ". " + estado + " " + tareas[i] + " (Prioridad: " + prioridades[i] + ")");
                        }
                    }
                    break;
                case 3:
                    if (contador == 0) {
                        System.out.println("no hay tareas para completar.");
                    } else {
                        System.out.print("número de tarea completada: ");
                        int numero = teclado.nextInt();
                        teclado.nextLine();
                        if (numero >= 1 && numero <= contador) {
                            completadas[numero - 1] = true;
                            System.out.println("tarea completada.");
                            continue;
                        }

                        System.out.println("ese número no existe.");
                    }
                    break;
                case 4:
                    if (contador == 0) {
                        System.out.println("no hay tareas para eliminar.");
                    } else {
                        System.out.print("número de tarea que quiere eliminar: ");
                        int numero = teclado.nextInt();
                        teclado.nextLine();
                        if (numero >= 1 && numero <= contador) {
                            for(int i = numero - 1; i < contador - 1; ++i) {
                                tareas[i] = tareas[i + 1];
                                completadas[i] = completadas[i + 1];
                                prioridades[i] = prioridades[i + 1];
                            }

                            tareas[contador - 1] = null;
                            completadas[contador - 1] = false;
                            prioridades[contador - 1] = null;
                            --contador;
                            System.out.println("Tarea eliminada.");
                            continue;
                        }

                        System.out.println("Ese número no existe.");
                    }
                    break;
                case 5:
                    if (contador == 0) {
                        System.out.println("No hay tareas.");
                    } else {
                        String filtro;
                        do {
                            System.out.print("¿Qué prioridad quieres ver (alta, media o baja)? ");
                            filtro = teclado.nextLine().trim().toLowerCase();
                            if (!filtro.equals("alta") && !filtro.equals("media") && !filtro.equals("baja")) {
                                System.out.println("Prioridad no válida. Escribe alta, media o baja.");
                            }
                        } while (!filtro.equals("alta") && !filtro.equals("media") && !filtro.equals("baja"));

                        boolean hayResultados = false;
                        System.out.println("\n--- TAREAS CON PRIORIDAD " + filtro.toUpperCase() + " ---");
                        for(int i = 0; i < contador; ++i) {
                            if (prioridades[i].equals(filtro)) {
                                String estado;
                                if (completadas[i]) {
                                    estado = "[X]";
                                } else {
                                    estado = "[ ]";
                                }

                                System.out.println(i + 1 + ". " + estado + " " + tareas[i]);
                                hayResultados = true;
                            }
                        }

                        if (!hayResultados) {
                            System.out.println("No hay tareas con esa prioridad.");
                        }
                    }
                    break;
                case 6:
                    if (contador == 0) {
                        System.out.println("No hay tareas que guardar.");
                    } else {
                        System.out.print("Nombre del archivo (por ejemplo tareas.txt): ");
                        String nombreArchivo = teclado.nextLine();

                        try {
                            FileWriter fw = new FileWriter(nombreArchivo);
                            PrintWriter escritor = new PrintWriter(fw);

                            for(int i = 0; i < contador; ++i) {
                                String estado;
                                if (completadas[i]) {
                                    estado = "[X]";
                                } else {
                                    estado = "[ ]";
                                }

                                escritor.println(i + 1 + ". " + estado + " " + tareas[i] + " (Prioridad: " + prioridades[i] + ")");
                            }

                            escritor.close();
                            System.out.println("Tareas guardadas en " + nombreArchivo);
                        } catch (IOException e) {
                            System.out.println("No se pudo guardar el archivo.");
                        }
                    }
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while(opcion != 0);

        teclado.close();
    }
}