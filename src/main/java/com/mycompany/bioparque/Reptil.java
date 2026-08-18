/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 *
 * @author Usuario
 */
public class Reptil extends Animal {

    private String tipoEscamas;

    public Reptil(String codigo, String nombre, int edad, double peso,
                  String sexo, EstadoSalud estadoSalud,
                  EstadoInventario estadoInventario, String habitat,
                  String tipoEscamas) {

        super(codigo, nombre, edad, peso, sexo,
              estadoSalud, estadoInventario, habitat);

        if (tipoEscamas == null || tipoEscamas.isBlank()) {
            throw new IllegalArgumentException("El tipo de escamas es obligatorio.");
        }
        this.tipoEscamas = tipoEscamas;
    }

    public String getTipoEscamas() {
        return tipoEscamas;
    }

    public void actualizarTipoEscamas(String tipoEscamas) {
        if (tipoEscamas == null || tipoEscamas.isBlank()) {
            throw new IllegalArgumentException("El tipo de escamas es obligatorio.");
        }
        this.tipoEscamas = tipoEscamas;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo de escamas: " + tipoEscamas);
    }
}
