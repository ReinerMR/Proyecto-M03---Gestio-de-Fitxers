package menumesas;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Reiner
 * @author Kevin
 */
public class MenuMesas {

    public static int menu() {
        Scanner T = new Scanner(System.in);
        int n = 0;
        while (n < 1 || n > 5) {

            System.out.println(">------------------------<");
            System.out.println("--------MENU MESAS--------");
            System.out.println(">------------------------<");
            System.out.println("1 - LISTAR MESAS");
            System.out.println("2 - DAR DE ALTA MESA");
            System.out.println("3 - MODIFICAR MESA");
            System.out.println("4 - BORRAR MESA");
            System.out.println("5 - SALIR");
            System.out.println(">------------------------<");
            System.out.print("INTRODUCE LA OPCION: ");
            n = T.nextInt();
        }
        return n;
    }

    public static void main(String[] args) {
        File fichero = new File("C:/Users/alumne/Desktop/Proyecto M03/Proyecto-M03---Gestio-de-Fitxers/taulas.txt");
        int opcio;
        opcio = MenuMesas.menu();
        do {
            switch (opcio) {
                case 1:
                    LeerTabla();
                    break;
                case 2:
                    EscribirTabla(fichero);
                    break;
                case 3:

                    editarMesa(fichero);

                    break;

                case 4:
                    borrarMesa(fichero);
                    break;
                case 5:
                    opcio = 5;
                    break;
            }
            opcio = MenuMesas.menu();
        } while (opcio != 5);
    }

    public static void LeerTabla() {

        File fichero = new File("C:/Users/alumne/Desktop/Proyecto M03/Proyecto-M03---Gestio-de-Fitxers/taulas.txt");

        try {
            // Codificación ISO-8859-1 (ANSI) o UTF-8 dependiendo de cómo esté creado el fichero de texto
            Scanner lectorFichero = new Scanner(fichero, "ISO-8859-1");

            while (lectorFichero.hasNext()) {
                System.out.println(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }
    }

    public static String nuevaMesa() {
        Scanner lector = new Scanner(System.in);
        System.out.println("Introduce la id: ");
        String id = lector.next();
        System.out.println("Descripcion mesa: ");
        String descripcion = lector.next();
        System.out.println("Numero de sillas: ");
        String numeroSillas = lector.next();
        System.out.println("¿Tiene silla para bebes? ");
        String bb = lector.next();
        System.out.println("Sillas de adultos: ");
        String sillasAdultos = lector.next();
        System.out.println("¿Tiene Ventilador? ");
        String ventilador = lector.next();
        System.out.println("¿Esta en el jardin? ");
        String jardin = lector.next();

        String nuevaMesa = id + ";" + descripcion + ";" + numeroSillas ";" + bb + ";" + sillasAdultos + ";" + ventilador + ";" + jardin;
        return nuevaMesa;
    }

    public static void editarMesa(File fichero) {
        Scanner lector = new Scanner(System.in);
        String lineacreada, lineanueva;
        System.out.println("Introduce que id quieres editar: ");
        lineacreada = lector.nextLine();
        //Array para guardar todas las li­neas leÃ­das del fichero
        ArrayList<String> lineas = new ArrayList<>();

        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);
            while (lectorFichero.hasNext()) {
                lineas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }

        // Abrimos el fichero de texto para sobreescribirlo
        try {
            FileWriter writer = new FileWriter(fichero);

            for (String linea : lineas) {
                if (lineacreada.equals(linea.substring(0, 2))) {
                    lineanueva = nuevaMesa();
                    writer.write(lineanueva + "\n");
                } else {
                    writer.write(linea + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }

    private static void borrarMesa(File fichero) {
        Scanner lector = new Scanner(System.in);
        String lineaeliminada;
        System.out.println("Introduce que id quieres eliminar: ");
        lineaeliminada = lector.nextLine();

        // Array para guardar todas las li­neas lei­das del fichero
        ArrayList<String> lineas = new ArrayList<>();

        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);

            while (lectorFichero.hasNext()) {
                lineas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/leer el fichero");
        }

        // Abrimos el fichero de texto para sobreescribirlo
        try {
            FileWriter writer = new FileWriter(fichero);

            for (String linea : lineas) {
                if (!lineaeliminada.equals(linea.substring(0, 2))) {
                    writer.write(linea + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }

    private static void EscribirTabla(File fichero) {
        String nuevaLinea = "\n" + nuevaMesa();

        try {
            FileWriter writer = new FileWriter(fichero, true);
            writer.write(nuevaLinea);
            writer.close();

        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear/escribir en el fichero");
        }
    }
}
