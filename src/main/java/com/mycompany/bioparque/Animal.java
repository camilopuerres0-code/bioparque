/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**

 *
 * @author Usuario
 */
public abstract class Animal {

    private final String codigo;
    private String nombre;
    private int edad;
    private double peso;
    private String sexo;
    private EstadoSalud estadoSalud;
    private EstadoInventario estadoInventario;
    private String habitat;

    public Animal(String codigo, String nombre, int edad, double peso,
                  String sexo, EstadoSalud estadoSalud,
                  EstadoInventario estadoInventario, String habitat) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a cero.");
        }
        if (sexo == null || sexo.isBlank()) {
            throw new IllegalArgumentException("El sexo es obligatorio.");
        }
        if (estadoSalud == null) {
            throw new IllegalArgumentException("El estado de salud es obligatorio.");
        }
        if (estadoInventario == null) {
            throw new IllegalArgumentException("El estado de inventario es obligatorio.");
        }
        if (habitat == null || habitat.isBlank()) {
            throw new IllegalArgumentException("El habitat es obligatorio.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.sexo = sexo;
        this.estadoSalud = estadoSalud;
        this.estadoInventario = estadoInventario;
        this.habitat = habitat;
    }


    public void actualizarPeso(double nuevoPeso) {
        if (nuevoPeso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor a cero.");
        }
        this.peso = nuevoPeso;
    }

  
    public void trasladarHabitat(String nuevoHabitat) {
        if (nuevoHabitat == null || nuevoHabitat.isBlank()) {
            throw new IllegalArgumentException("El nuevo habitat es obligatorio.");
        }
        if (estadoInventario == EstadoInventario.NO_DISPONIBLE) {
            throw new IllegalArgumentException("No se puede trasladar un animal retirado del inventario.");
        }
        this.habitat = nuevoHabitat;
    }

    /**
     * Pone al animal en observación (cuarentena) por motivos de salud.
     */
    public void ponerEnObservacion() {
        if (estadoInventario == EstadoInventario.NO_DISPONIBLE) {
            throw new IllegalArgumentException("No se puede poner en observacion un animal retirado.");
        }
        this.estadoInventario = EstadoInventario.EN_CUARENTENA;
        this.estadoSalud = EstadoSalud.EN_TRATAMIENTO;
    }

    /**
     */
    public void darAltaMedica() {
        if (estadoInventario != EstadoInventario.EN_CUARENTENA) {
            throw new IllegalArgumentException("El animal no se encuentra en observacion.");
        }
        this.estadoInventario = EstadoInventario.DISPONIBLE;
        this.estadoSalud = EstadoSalud.SANO;
    }

    /**
     */
    public void retirar() {
        if (estadoInventario == EstadoInventario.NO_DISPONIBLE) {
            throw new IllegalArgumentException("El animal ya se encuentra retirado.");
        }
        this.estadoInventario = EstadoInventario.NO_DISPONIBLE;
    }


    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public String getSexo() {
        return sexo;
    }

    public EstadoSalud getEstadoSalud() {
        return estadoSalud;
    }

    public EstadoInventario getEstadoInventario() {
        return estadoInventario;
    }

    public String getHabitat() {
        return habitat;
    }

    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Sexo: " + sexo);
        System.out.println("Estado de salud: " + estadoSalud);
        System.out.println("Estado de inventario: " + estadoInventario);
        System.out.println("Habitat: " + habitat);
    }
}
