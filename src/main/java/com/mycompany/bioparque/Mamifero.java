/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 *
 * @author Usuario
 */
public class Mamifero extends Animal {
    private String tipoPelaje;

    public Mamifero(String codigo, String nombre, int edad, double peso, String sexo,
                     EstadoSalud estadoSalud, EstadoInventario estadoInventario,
                     String habitat, String tipoPelaje) {

        super(codigo, nombre, edad, peso, sexo, estadoSalud, estadoInventario, habitat);

        if (tipoPelaje == null || tipoPelaje.isBlank()) {
            throw new IllegalArgumentException("El tipo de pelaje es obligatorio.");
        }
        this.tipoPelaje = tipoPelaje;
    }

    public String getTipoPelaje() {
        return tipoPelaje;
    }

    public void actualizarTipoPelaje(String tipoPelaje) {
        if (tipoPelaje == null || tipoPelaje.isBlank()) {
            throw new IllegalArgumentException("El tipo de pelaje es obligatorio.");
        }
        this.tipoPelaje = tipoPelaje;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo de pelaje: " + tipoPelaje);
    }
}
