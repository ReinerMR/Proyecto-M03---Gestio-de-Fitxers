package menumesas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Reiner
 * @author Kevin
 *
 */
public class MenuMesas {

    //ARRAY DE LOS USUARIOS = Kevin TG
    public static Usuarios[] Usuarios = null;

    public static void main(String[] args) {
        GenerarUsuario();
        menuUsuarios();
    }

    //FUNCION QUE LEER TABLA = Kevin TG
    public static void LeerTabla() {

        File fichero = new File("C:\\Users\\alumne\\Desktop\\Proyecto M03\\Proyecto-M03---Gestio-de-Fitxers\\taulas.txt");

        try {
            // CodificaciÃ³n ISO-8859-1 (ANSI) o UTF-8 dependiendo de cÃ³mo estÃ© creado el fichero de texto
            Scanner lectorFichero = new Scanner(fichero, "ISO-8859-1");

            while (lectorFichero.hasNext()) {
                System.out.println(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("ERROR AL LEER EL FICHERO");
        }
    }

    //FUNCION DE PEDIR INFORMACION DE LAS MESAS = Reiner MR
    public static String nuevaMesa() {

        Scanner lector = new Scanner(System.in);

        System.out.print("Introduce la id: ");
        String id = lector.next();
        System.out.print("Descripcion mesa: ");
        String descripcion = lector.next();
        System.out.print("Numero de sillas: ");
        String numeroSillas = lector.next();
        System.out.print("Â¿Tiene silla para bebes? ");
        String bb = lector.next();
        System.out.print("Sillas de adultos: ");
        String sillasAdultos = lector.next();
        System.out.print("Â¿Tiene Ventilador? ");
        String ventilador = lector.next();
        System.out.print("Â¿Esta en el jardin? ");
        String jardin = lector.next();

        String nuevaMesa = id + ";" + descripcion + ";" + numeroSillas + ";" + bb + ";" + sillasAdultos + ";" + ventilador + ";" + jardin;
        return nuevaMesa;
    }

    //FUNCION DONDE EDITAMOS EL FICHERO LAS MESAS = Reiner MR
    public static void editarMesa(File fichero) {

        Scanner lector = new Scanner(System.in);

        String lineacreada, lineanueva;

        System.out.print("Introduce que id quieres editar: ");
        lineacreada = lector.nextLine();

        ArrayList<String> lineas = new ArrayList<>();

        // Abrimos el fichero de texto para leerlo en memoria
        try {
            Scanner lectorFichero = new Scanner(fichero);
            while (lectorFichero.hasNext()) {
                lineas.add(lectorFichero.nextLine());
            }

            lectorFichero.close();
        } catch (Exception e) {
            System.out.println("ERROR AL EDITAR EL FICHERO");
        }

        // Abrimos el fichero de texto para sobreescribirlo
        try {
            FileWriter writer = new FileWriter(fichero);

            for (String linea : lineas) {
                if (lineacreada.equals(linea.substring(0, 3))) {
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

    //FUNCION DONDE BORRAMOS LAS MESAS = Reiner MR , Kevin TG
    private static void borrarMesa(File fichero) {

        Scanner lector = new Scanner(System.in);

        String lineaeliminada;

        System.out.print("Introduce que id quieres eliminar: ");
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
            System.out.println("ERROR AL BORRAR MESA");
        }

        // Abrimos el fichero de texto para sobreescribirlo
        try {
            FileWriter writer = new FileWriter(fichero);

            for (String linea : lineas) {
                if (!lineaeliminada.equals(linea.substring(0, 3))) {
                    writer.write(linea + "\n");
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al abrir/sobreescribir el fichero");
        }
    }

    //FUNCION DONDE ESCRIBE LA TABLA = Reiner MR
    private static void EscribirTabla(File fichero) {
        String nuevaLinea = "\n" + nuevaMesa();

        try {
            FileWriter writer = new FileWriter(fichero, true);
            writer.write(nuevaLinea);
            writer.close();

        } catch (Exception e) {
            System.out.println("ERROR AL ESCRIBIR");
        }
    }

    //FUNCION DE MOSTRAR EL MENU USUARIOS = Reiner MR
    private static void menuUsuarios() {
        Scanner M = new Scanner(System.in);
        int u = 0;
        boolean salir = false;

        while (!salir) {

            System.out.println(">---------------------------<");
            System.out.println("---------MENU ADMIN----------");
            System.out.println(">---------------------------<");
            System.out.println("1 - ADMINISTRADOR ");
            System.out.println("2 - CAMAMARERO ");
            System.out.println("3 - SALIR");
            System.out.println(">---------------------------<");

            try {

                System.out.print("INTRODUCE LA OPCION: ");
                u = M.nextInt();

                //LAS FUNCIONES DE MENU ADMIN
                switch (u) {
                    //Case 1 Menu Admin
                    case 1:
                        menuAdmin();
                        break;
                    //Case 2 Menu Cambrer
                    case 2:
                        menuCambrer();
                        break;
                    //Case 3 Salir
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("INTRODUCE DEL 1 AL 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("INTRODUCE LA OPCION: ");
                M.nextInt();
            }
        }
    }

    //FUNCION MOSTRAR EL MENU CAMARERO POR PANTALLA =  Kevin TG, Reiner MR
    private static void menuCambrer() {
        Scanner M = new Scanner(System.in);
        int c = 0;
        boolean salir = false;

        while (!salir) {

            System.out.println(">---------------------------<");
            System.out.println("---------MENU MESAS----------");
            System.out.println(">---------------------------<");
            System.out.println("1 - LISTAR MESAS");
            System.out.println("2 - DAR DE ALTA MESA");
            System.out.println("3 - MODIFICAR MESA");
            System.out.println("4 - BORRAR MESA");
            System.out.println("5 - SALIR");
            System.out.println(">---------------------------<");

            try {

                System.out.print("INTRODUCE LA OPCION: ");
                c = M.nextInt();

                //Creamos el fichero para guardar la informacion de las mesas
                File fichero = new File("C:\\Users\\alumne\\Desktop\\Proyecto M03\\Proyecto-M03---Gestio-de-Fitxers\\taulas.txt");

                //Las funciones del menu mesas
                switch (c) {
                    //Case 1 LEER TABLA
                    case 1:
                        LeerTabla();
                        break;
                    //Case 2 DAR DE ALTA
                    case 2:
                        EscribirTabla(fichero);
                        break;
                    //Case 3 MODIFICAR LA MESA
                    case 3:
                        editarMesa(fichero);
                        break;
                    //Case 4 BORRAR LA MESA
                    case 4:
                        borrarMesa(fichero);
                        break;
                    //Case 5 VOLVER AL MENU USUARIOS
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("INTRODUCE DEL 1 AL 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("INTRODUCE LA OPCION: ");
                M.nextInt();
            }
        }
    }

    //FUNCION MOSTRAR EL MENU ADMIN POR PANTALLA = Reiner MR
    private static void menuAdmin() {
        Scanner M = new Scanner(System.in);
        int a = 0;
        boolean salir = false;

        while (!salir) {

            System.out.println(">---------------------------<");
            System.out.println("--------MENU USUARIOS--------");
            System.out.println(">---------------------------<");
            System.out.println("1 - DAR DE ALTA USUARIO");
            System.out.println("2 - LISTAR USUARIOS");
            System.out.println("3 - MODIFICAR USUARIOS");
            System.out.println("4 - BORRAR USUARIO");
            System.out.println("5 - SALIR");
            System.out.println(">---------------------------<");

            try {

                System.out.print("INTRODUCE LA OPCION: ");
                a = M.nextInt();

                //Creamos el fichero para guardar la informacion de las mesas
                File fichero = new File("C:\\Users\\alumne\\Desktop\\Proyecto M03\\Proyecto-M03---Gestio-de-Fitxers\\users.dat");

                //Las funciones del menu mesas
                switch (a) {
                    //Case 1 DAR DE ALTA AL USUARIO COMO CAMBRER
                    case 1:
                        CrearUsuario();
                        break;
                    //Case 2 LISTAR USUARIOS
                    case 2:
                        MostrarUsuario();
                        break;
                    //Case 3 MODIFICAR USUARIOS
                    case 3:
                        ModificarUsuario();
                        break;
                    //Case 4 BORRAR USUARIOS
                    case 4:
                        BorrarUsuario();
                        break;
                    //Case 5 VOLVER AL MENU USUARIOS
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("INTRODUCE DEL 1 AL 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("INTRODUCE LA OPCION: ");
                M.nextInt();
            }
        }
    }

    //FUNCION DONDE GENERA EL USUARIO PRINCIPAL = Kevin TG
    private static void GenerarUsuario() {

        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("C:\\Users\\alumne\\Desktop\\Proyecto M03\\Proyecto-M03---Gestio-de-Fitxers\\users.dat"));
            Usuarios[] Usuarios = new Usuarios[10];
            Usuarios[0] = new Usuarios();
            Usuarios[0].rol = "Administrador";
            Usuarios[0].usuario = "admin";
            Usuarios[0].contraseña = "admin";
            fichero.writeObject(Usuarios);
            fichero.close();
        } catch (Exception e) {
            System.out.println("ERROR AL GENERAR USUARIO");
        }
    }

    //FUNCION DONDE CREA EL USUARIO = Kevin TG
    private static void CrearUsuario() {

        Scanner CU = new Scanner(System.in);
        System.out.print("ROL: ");
        String rol = CU.nextLine();
        System.out.print("USUARIO: ");
        String usuario = CU.nextLine();
        System.out.print("CONTRASEÑA: ");
        String contraseña = CU.nextLine();
        int contadorUsuarios = 0;
        boolean comprobar = true;
        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("C:\\Users\\alumne\\Desktop\\Proyecto M03\\Proyecto-M03---Gestio-de-Fitxers\\users.dat"));

            Usuarios[] Usuarios = new Usuarios[10];

            while (comprobar != false) {
                contadorUsuarios++;
                if (Usuarios[contadorUsuarios] == null) {
                    Usuarios[contadorUsuarios] = new Usuarios();
                    Usuarios[contadorUsuarios].rol = rol;
                    Usuarios[contadorUsuarios].usuario = usuario;
                    Usuarios[contadorUsuarios].contraseña = contraseña;
                    comprobar = false;
                }

            }

            fichero.writeObject(Usuarios);
            fichero.close();
        } catch (Exception e) {
            System.out.println("ERROR AL CREAR USUARIO");
        }
    }

    //FUNCION MOSTRAR LOS USUARIOS POR PANTALLA = Kevin TG
    private static void MostrarUsuario() {

        try {
            ObjectInputStream fichero = new ObjectInputStream(new FileInputStream("C:\\Users\\alumne\\Desktop\\Proyecto M03\\Proyecto-M03---Gestio-de-Fitxers\\users.dat"));

            Usuarios[] Usuarios = (Usuarios[]) fichero.readObject();

            for (Usuarios usuario : Usuarios) {

                if (usuario != null) {
                    System.out.println(">---------------------------<");
                    System.out.println("Rol: " + usuario.rol);
                    System.out.println("Usuario: " + usuario.usuario);
                    System.out.println("Contraseña: " + usuario.contraseña);
                }
            }

            fichero.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR AL MOSTRAR EL FICHERO");
        }
    }

    //FUNCION DONDE MODIFICAMOS EL USUARIO = Kevin TG
    private static void ModificarUsuario() {

        Scanner MU = new Scanner(System.in);
        System.out.print("INTRODUCE EL NOMBRE DE USUARIO: ");
        String nombreActualizado = MU.nextLine();
        for (Usuarios usuario : Usuarios) {
            if (usuario != null && usuario.usuario.equals(nombreActualizado)) {
                System.out.print("INTRODUCE NUEVO ROL: ");
                usuario.rol = MU.nextLine();
                System.out.print("INTRODUCE NUEVO NOMBRE: ");
                usuario.usuario = MU.nextLine();
                System.out.print("INTRODUCE NUEVA CONTRASEÑA: ");
                usuario.contraseña = MU.nextLine();
                System.out.println("ACTUALIZACION CORRECTA!");
            }
        }

        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("C:\\Users\\alumne\\Desktop\\Proyecto M03\\Proyecto-M03---Gestio-de-Fitxers\\users.dat"));

            fichero.writeObject(Usuarios);

            fichero.close();

        } catch (Exception e) {
            System.out.println("ERROR A GUARDAR LA MODIFICACION DEL USUARIO");
        }

    }

    //FUNCION DONDE BORRAMOS EL USUARIO = Kevin TG
    private static void BorrarUsuario() {

        Scanner BU = new Scanner(System.in);
        System.out.print("Introduce el nombre del usuario a eliminar: ");
        String nombreBorrar = BU.nextLine();
        for (Usuarios usuario : Usuarios) {
            if (usuario != null && usuario.usuario.equals(nombreBorrar)) {
                usuario.usuario = "";
                usuario.contraseña = "";
                usuario.rol = "";
                usuario = null;
                System.out.println("SE HA BORRADO EL SIGUIENTE USUARIO: " + nombreBorrar);
            }
        }

        try {
            ObjectOutputStream fichero = new ObjectOutputStream(new FileOutputStream("C:\\Users\\alumne\\Desktop\\Proyecto M03\\Proyecto-M03---Gestio-de-Fitxers\\users.dat"));

            fichero.writeObject(Usuarios);

            fichero.close();

        } catch (Exception e) {
            System.out.println("ERROR A GUARDAR EL USUARIO BORRADO");
        }

    }

    

}
