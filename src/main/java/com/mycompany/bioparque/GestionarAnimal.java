/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Centraliza el inventario de animales: comprobación de código único y las
 * operaciones CRUD (Crear, Listar/Buscar, Actualizar, Eliminar). Los errores
 * de negocio se lanzan como IllegalArgumentException desde el modelo y se
 * capturan aquí, en la capa de interacción con el usuario.
 *
 * @author Usuario
 */
public class GestionarAnimal {

    // Se recibe un unico Scanner desde Bioparque (main). Tener dos
    // Scanner distintos leyendo System.in provoca perdida de datos,
    // por eso este NO crea su propia instancia.
    Scanner lector;
    ArrayList<Animal> animales = new ArrayList<>();

    public GestionarAnimal(Scanner lector) {
        this.lector = lector;
    }

    // ===================== Utilidades de lectura con validación =====================

    private int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = lector.nextLine();
            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida: debe ingresar un numero entero. Intente de nuevo.");
            }
        }
    }

    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = lector.nextLine();
            try {
                return Double.parseDouble(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida: debe ingresar un numero. Intente de nuevo.");
            }
        }
    }

    private String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = lector.nextLine();
            if (entrada != null && !entrada.isBlank()) {
                return entrada;
            }
            System.out.println("Entrada invalida: el dato no puede estar vacio. Intente de nuevo.");
        }
    }

    // ===================== Comprobación de código único =====================

    private boolean existeCodigo(String codigo) {
        for (Animal animal : animales) {
            if (animal.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    // ===================== CREATE =====================

    public void registrarDesdeConsola() {

        int categoria = leerEntero("Ingrese la categoria (1-Ave 2-Mamifero 3-Reptil): ");
        if (categoria != 1 && categoria != 2 && categoria != 3) {
            System.out.println("Categoria no valida.");
            return;
        }

        String codigo;
        do {
            codigo = leerTexto("Ingrese el codigo: ");
            if (existeCodigo(codigo)) {
                System.out.println("Ese codigo ya existe. Debe ser unico.");
            }
        } while (existeCodigo(codigo));

        String nombre = leerTexto("Ingrese el nombre: ");
        int edad = leerEntero("Ingrese la edad: ");
        double peso = leerDouble("Ingrese el peso: ");
        String sexo = leerTexto("Ingrese el sexo: ");
        String habitat = leerTexto("Ingrese el habitat: ");

        try {
            Animal nuevo;

            if (categoria == 2) {
                String tipoPelaje = leerTexto("Ingrese el tipo de pelaje: ");
                nuevo = new Mamifero(codigo, nombre, edad, peso, sexo,
                        EstadoSalud.SANO, EstadoInventario.DISPONIBLE, habitat, tipoPelaje);

            } else if (categoria == 1) {
                double envergadura = leerDouble("Ingrese la envergadura: ");
                System.out.print("Puede volar? (si/no): ");
                boolean puedeVolar = lector.nextLine().trim().equalsIgnoreCase("si");
                nuevo = new Ave(codigo, nombre, edad, peso, sexo,
                        EstadoSalud.SANO, EstadoInventario.DISPONIBLE, habitat, envergadura, puedeVolar);

            } else {
                String tipoEscamas = leerTexto("Ingrese el tipo de escamas: ");
                nuevo = new Reptil(codigo, nombre, edad, peso, sexo,
                        EstadoSalud.SANO, EstadoInventario.DISPONIBLE, habitat, tipoEscamas);
            }

            animales.add(nuevo);
            System.out.println("Animal registrado correctamente.");

        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo registrar el animal: " + e.getMessage());
        }
    }

    public void listar() {

        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }

        for (Animal animal : animales) {
            animal.mostrarInformacion();
            System.out.println("------------------------");
        }
    }

    private Animal buscarPorCodigo(String codigo) {
        for (Animal animal : animales) {
            if (animal.getCodigo().equalsIgnoreCase(codigo)) {
                return animal;
            }
        }
        return null;
    }

    public void buscarDesdeConsola() {
        String codigo = leerTexto("Ingrese el codigo del animal: ");
        Animal animal = buscarPorCodigo(codigo);

        if (animal == null) {
            System.out.println("No se encontro el animal.");
            return;
        }
        animal.mostrarInformacion();
    }

    // ===================== UPDATE (opcion 4) =====================

    public void actualizarDesdeConsola() {

        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }

        String codigo = leerTexto("Ingrese el codigo del animal a actualizar: ");
        Animal animal = buscarPorCodigo(codigo);

        if (animal == null) {
            System.out.println("No se encontro el animal.");
            return;
        }

        System.out.println("\n--- Actualizar: " + animal.getNombre() + " (" + animal.getCodigo() + ") ---");
        System.out.println("1. Actualizar peso");
        System.out.println("2. Trasladar de habitat");
        System.out.println("3. Poner en observacion");
        System.out.println("4. Dar de alta medica");
        System.out.println("5. Retirar del inventario");
        System.out.println("0. Cancelar");

        int opcion = leerEntero("Seleccione una opcion: ");

        try {
            switch (opcion) {
                case 1:
                    double nuevoPeso = leerDouble("Ingrese el nuevo peso: ");
                    animal.actualizarPeso(nuevoPeso);
                    System.out.println("Peso actualizado correctamente.");
                    break;

                case 2:
                    String nuevoHabitat = leerTexto("Ingrese el nuevo habitat: ");
                    animal.trasladarHabitat(nuevoHabitat);
                    System.out.println("Habitat actualizado correctamente.");
                    break;

                case 3:
                    animal.ponerEnObservacion();
                    System.out.println("El animal quedo en observacion.");
                    break;

                case 4:
                    animal.darAltaMedica();
                    System.out.println("El animal fue dado de alta medica.");
                    break;

                case 5:
                    animal.retirar();
                    System.out.println("El animal fue retirado del inventario.");
                    break;

                case 0:
                    System.out.println("Actualizacion cancelada.");
                    break;

                default:
                    System.out.println("Opcion fuera de rango.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo actualizar el animal: " + e.getMessage());
        }
    }

    
    public void filtrarDesdeConsola() {

        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }

        System.out.println("\n--- Filtrar animales ---");
        System.out.println("1. Por categoria (Ave/Mamifero/Reptil)");
        System.out.println("2. Por estado de inventario");
        System.out.println("3. Por tipo de alimentacion (texto contenido)");
        System.out.println("0. Cancelar");

        int opcion = leerEntero("Seleccione una opcion: ");
        ArrayList<Animal> resultado = new ArrayList<>();

        switch (opcion) {
            case 1: {
                int categoria = leerEntero("Categoria (1-Ave 2-Mamifero 3-Reptil): ");
                for (Animal animal : animales) {
                    if ((categoria == 1 && animal instanceof Ave)
                            || (categoria == 2 && animal instanceof Mamifero)
                            || (categoria == 3 && animal instanceof Reptil)) {
                        resultado.add(animal);
                    }
                }
                break;
            }
            case 2: {
                System.out.println("1. DISPONIBLE  2. NO_DISPONIBLE  3. EN_CUARENTENA");
                int estadoOpcion = leerEntero("Seleccione el estado: ");
                EstadoInventario estado = switch (estadoOpcion) {
                    case 1 -> EstadoInventario.DISPONIBLE;
                    case 2 -> EstadoInventario.NO_DISPONIBLE;
                    case 3 -> EstadoInventario.EN_CUARENTENA;
                    default -> null;
                };
                if (estado == null) {
                    System.out.println("Opcion de estado no valida.");
                    return;
                }
                for (Animal animal : animales) {
                    if (animal.getEstadoInventario() == estado) {
                        resultado.add(animal);
                    }
                }
                break;
            }
            case 3: {
                String texto = leerTexto("Ingrese un texto a buscar en el tipo de alimentacion: ");
                for (Animal animal : animales) {
                    if (animal.obtenerTipoAlimentacion().toLowerCase()
                            .contains(texto.toLowerCase())) {
                        resultado.add(animal);
                    }
                }
                break;
            }
            case 0:
                System.out.println("Filtro cancelado.");
                return;
            default:
                System.out.println("Opcion fuera de rango.");
                return;
        }

        mostrarResultadosFiltro(resultado);
    }

    private void mostrarResultadosFiltro(ArrayList<Animal> resultado) {
        if (resultado.isEmpty()) {
            System.out.println("Ningun animal cumple el filtro.");
            return;
        }

        System.out.println("\n--- Resultado del filtro (" + resultado.size() + ") ---");
        for (Animal animal : resultado) {
            System.out.println(animal.resumenBasico());
            System.out.println("  Tipo de alimentacion: " + animal.obtenerTipoAlimentacion());
            if (animal instanceof Alimentable alimentable) {
                System.out.printf("  Racion diaria estimada: %.2f kg%n",
                        alimentable.calcularRacionDiaria());
            }
            System.out.println("------------------------");
        }
    }


    public void eliminarDesdeConsola() {

        if (animales.isEmpty()) {
            System.out.println("No hay animales registrados.");
            return;
        }

        String codigo = leerTexto("Ingrese el codigo del animal a eliminar: ");
        Animal animal = buscarPorCodigo(codigo);

        if (animal == null) {
            System.out.println("No se encontro el animal.");
            return;
        }

        System.out.print("Seguro que desea eliminar definitivamente a " + animal.getNombre()
                + " del inventario? (si/no): ");
        String confirmacion = lector.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("si")) {
            animales.remove(animal);
            System.out.println("Animal eliminado correctamente.");
        } else {
            System.out.println("Eliminacion cancelada.");
        }
    }
}
