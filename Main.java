import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner leer = new Scanner(System.in);

        Cuenta cuentas[] = new Cuenta[10];
        String historialAct[][] = new String[10][20];
        String historialCerr[][] = new String[10][10];

        int contCuenta = 0;
        int op;
        int i;
        int j;
        int idBuscar;
        int posEncontrada;
        int posOrigen;
        int posDestino;
        int monto;
        String nombre;

        for (i = 0; i < 10; i++) {
            cuentas[i] = new Cuenta();

            cuentas[i].setId(0);
            cuentas[i].setNombre("");
            cuentas[i].setSaldo(0);
            cuentas[i].activa = false;

            for (j = 0; j < 20; j++) {
                historialAct[i][j] = "";
            }

            for (j = 0; j < 10; j++) {
                historialCerr[i][j] = "";
            }
        }

        do {

            System.out.println("1. Crear cuenta");
            System.out.println("2. Cerrar cuenta");
            System.out.println("3. Modificar nombre");
            System.out.println("4. Depositar");
            System.out.println("5. Retirar");
            System.out.println("6. Transferir");
            System.out.println("7. Buscar cuenta por ID");
            System.out.println("8. Consultar cuentas");
            System.out.println("9. Historial activo");
            System.out.println("10. Historial cerrado");
            System.out.println("11. Salir");
            System.out.print("\nOpcion a realizar: ");

            op = leer.nextInt();
            leer.nextLine();

            if (op == 1) {

                if (contCuenta < 10) {

                    System.out.println("Ingrese ID:");
                    idBuscar = leerNumero(leer);

                    posEncontrada = -1;

                    for (i = 0; i < 10; i++) {
                        if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                            posEncontrada = i;
                        }
                    }

                    if (posEncontrada == -1) {

                        cuentas[contCuenta].setId(idBuscar);

                        System.out.println("Ingrese nombre:");
                        nombre = leer.nextLine();
                        cuentas[contCuenta].setNombre(nombre);

                        System.out.println("Ingrese saldo inicial:");
                        monto = leerNumero(leer);
                        cuentas[contCuenta].setSaldo(monto);

                        cuentas[contCuenta].activa = true;

                        agregarHistorialActivo(historialAct, contCuenta, "Cuenta creada con saldo " + monto);

                        contCuenta = contCuenta + 1;

                    } else {
                        System.out.println("ID repetido");
                    }

                } else {
                    System.out.println("Limite de cuentas alcanzado");
                }

            }

            if (op == 2) {

                System.out.println("Ingrese ID a cerrar:");
                idBuscar = leerNumero(leer);

                posEncontrada = -1;

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                        posEncontrada = i;
                    }
                }   
                    System.out.println(" ");
                   System.out.println("ID: " + cuentas[posEncontrada].getId());
                    System.out.println("Nombre: " + cuentas[posEncontrada].getNombre());
                    System.out.println("Saldo: " + cuentas[posEncontrada].getSaldo());
                    System.out.println(" ");
                char opcion;
                    System.out.println("¿Eliminar cuenta?\n Ingrese S para si, cualquier otra tecla para salir");
                    String input = leer.next("[a-zA-Z]");
                    opcion = input.charAt(0);

                if (opcion == 'S' || opcion == 's') {
                if (posEncontrada != -1) {

                    if (cuentas[posEncontrada].getSaldo() == 0) {

                        cuentas[posEncontrada].activa = false;

                        pasarHistorial(historialAct, historialCerr, posEncontrada);

                        System.out.println("Cuenta cerrada");

                    } else {
                        System.out.println("La cuenta debe tener saldo 0 para ser eliminada");
                    }

                } else {
                    System.out.println("No existe");
                }
            }
            
            }

            if (op == 3) {

                System.out.println("Ingrese ID:");
                idBuscar = leerNumero(leer);

                posEncontrada = -1;

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                        posEncontrada = i;
                    }
                }

                if (posEncontrada != -1) {

                    System.out.println("Nuevo nombre:");
                    nombre = leer.nextLine();
                    cuentas[posEncontrada].setNombre(nombre);

                    agregarHistorialActivo(historialAct, posEncontrada, "Nombre cambiado a " + nombre);

                } else {
                    System.out.println("No existe");
                }

            }

            if (op == 4) {

                System.out.println("ID:");
                idBuscar = leerNumero(leer);

                posEncontrada = -1;

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                        posEncontrada = i;
                    }
                }

                if (posEncontrada != -1) {

                    System.out.println("Cantidad:");
                    monto = leerNumero(leer);

                    cuentas[posEncontrada].setSaldo(cuentas[posEncontrada].getSaldo() + monto);

                    agregarHistorialActivo(historialAct, posEncontrada, "Deposito de " + monto);

                } else {
                    System.out.println("No existe");
                }

            }

            if (op == 5) {

                System.out.println("ID:");
                idBuscar = leerNumero(leer);

                posEncontrada = -1;

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                        posEncontrada = i;
                    }
                }

                if (posEncontrada != -1) {

                    System.out.println("Cantidad:");
                    monto = leerNumero(leer);

                    if (monto <= cuentas[posEncontrada].getSaldo()) {

                        cuentas[posEncontrada].setSaldo(cuentas[posEncontrada].getSaldo() - monto);

                        agregarHistorialActivo(historialAct, posEncontrada, "Retiro de " + monto);

                    } else {
                        System.out.println("Saldo insuficiente");
                    }

                } else {
                    System.out.println("No existe");
                }

            }

            if (op == 6) {

                System.out.println("Cuenta origen:");
                idBuscar = leerNumero(leer);

                posOrigen = -1;

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                        posOrigen = i;
                    }
                }

                if (posOrigen != -1) {

                    System.out.println("Cuenta destino:");
                    idBuscar = leerNumero(leer);

                    posDestino = -1;

                    for (i = 0; i < 10; i++) {
                        if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                            posDestino = i;
                        }
                    }

                    if (posDestino != -1) {

                        System.out.println("Cantidad:");
                        monto = leerNumero(leer);

                        if (monto <= cuentas[posOrigen].getSaldo()) {

                            cuentas[posOrigen].setSaldo(cuentas[posOrigen].getSaldo() - monto);
                            cuentas[posDestino].setSaldo(cuentas[posDestino].getSaldo() + monto);

                            agregarHistorialActivo(historialAct, posOrigen, "Transferencia salida " + monto);
                            agregarHistorialActivo(historialAct, posDestino, "Transferencia entrada " + monto);

                        } else {
                            System.out.println("Saldo insuficiente");
                        }

                    } else {
                        System.out.println("Cuenta destino no existe");
                    }

                } else {
                    System.out.println("Cuenta origen no existe");
                }

            }

            if (op == 7) {

                System.out.println("ID:");
                idBuscar = leerNumero(leer);

                posEncontrada = -1;

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                        posEncontrada = i;
                    }
                }

                if (posEncontrada != -1) {

                    System.out.println("ID: " + cuentas[posEncontrada].getId());
                    System.out.println("Nombre: " + cuentas[posEncontrada].getNombre());
                    System.out.println("Saldo: " + cuentas[posEncontrada].getSaldo());

                } else {
                    System.out.println("No existe");
                }

            }

            if (op == 8) {

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == true) {
                        System.out.println(cuentas[i].getId() + " " + cuentas[i].getNombre() + " " + cuentas[i].getSaldo());
                    }
                }

            }

            if (op == 9) {

                System.out.println("ID:");
                idBuscar = leerNumero(leer);

                posEncontrada = -1;

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == true && cuentas[i].getId() == idBuscar) {
                        posEncontrada = i;
                    }
                }

                if (posEncontrada != -1) {

                    for (i = 0; i < 20; i++) {
                        if (!historialAct[posEncontrada][i].equals("")) {
                            System.out.println(historialAct[posEncontrada][i]);
                        }
                    }

                } else {
                    System.out.println("No existe");
                }

            }

            if (op == 10) {

                System.out.println("ID:");
                idBuscar = leerNumero(leer);

                posEncontrada = -1;

                for (i = 0; i < 10; i++) {
                    if (cuentas[i].activa == false && cuentas[i].getId() == idBuscar) {
                        posEncontrada = i;
                    }
                }

                if (posEncontrada != -1) {

                    for (i = 0; i < 10; i++) {
                        if (!historialCerr[posEncontrada][i].equals("")) {
                            System.out.println(historialCerr[posEncontrada][i]);
                        }
                    }

                } else {
                    System.out.println("No existe");
                }

            }

        } while (op != 11);

    }


    public static int leerNumero(Scanner leer) {

        int num = 0;
        boolean ok = false;

        while (ok == false) {
            try {
                num = Integer.parseInt(leer.nextLine());
                ok = true;
            } catch (Exception e) {
                System.out.println("Parametro no valido, ingrese un numero:");
            }
        }

        return num;
    }


    public static void agregarHistorialActivo(String h[][], int pos, String texto) {

        int i;

        for (i = 0; i < 20; i++) {

            if (h[pos][i].equals("")) {
                h[pos][i] = texto;
                i = 20;
            }

        }

    }


    public static void pasarHistorial(String act[][], String cerr[][], int pos) {

        int i;

        for (i = 0; i < 10; i++) {
            if (act[pos][i].equals("") == false) {
                cerr[pos][i] = act[pos][i];
            }
        }

    }

}
