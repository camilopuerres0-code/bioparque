/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 *
 * @author Usuario
 */
public class Ave extends Animal {
    private double envergadura;
    private boolean puedeVolar;

    public Ave(String codigo, String nombre, int edad, double peso, String sexo,
               EstadoSalud estadoSalud, EstadoInventario estadoInventario,
               String habitat, double envergadura, boolean puedeVolar) {

        super(codigo, nombre, edad, peso, sexo, estadoSalud, estadoInventario, habitat);

        if (envergadura <= 0) {
            throw new IllegalArgumentException("La envergadura debe ser mayor a cero.");
        }
        this.envergadura = envergadura;
        this.puedeVolar = puedeVolar;
    }

    public double getEnvergadura() {
        return envergadura;
    }

    public boolean isPuedeVolar() {
        return puedeVolar;
    }

    public void actualizarEnvergadura(double envergadura) {
        if (envergadura <= 0) {
            throw new IllegalArgumentException("La envergadura debe ser mayor a cero.");
        }
        this.envergadura = envergadura;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Envergadura: " + envergadura + " m");
        System.out.println("Puede volar: " + (puedeVolar ? "Sí" : "No"));
    }
}
