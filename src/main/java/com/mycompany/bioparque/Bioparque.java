/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bioparque;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Bioparque {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        GestionarAnimal inventario = new GestionarAnimal(lector);

        int opcion;

        do {
            System.out.println("\n===== MENU DE OPCIONES =====");
            System.out.println("1. Registrar animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar animal");
            System.out.println("4. Actualizar animal");
            System.out.println("5. Eliminar animal");
            System.out.println("0. Salir");
            System.out.println("============================");
            System.out.print("Seleccione una opcion: ");

            String entrada = lector.nextLine();
            try {
                opcion = Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida: debe ingresar un numero. Intente de nuevo.");
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    inventario.registrarDesdeConsola();
                    break;

                case 2:
                    inventario.listar();
                    break;

                case 3:
                    inventario.buscarDesdeConsola();
                    break;

                case 4:
                    inventario.actualizarDesdeConsola();
                    break;

                case 5:
                    inventario.eliminarDesdeConsola();
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                case -1:
                    break;

                default:
                    System.out.println("Opcion fuera de rango. Intente de nuevo.");
            }

        } while (opcion != 0);

    }
}
