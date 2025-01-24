/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab1progra2;

/**
 *
 * @author danilos
 */
public class Ticket {
    String nombre_pasajero ;
    double totalpaga_ticket;
    
    public Ticket(String nombre_pasajero, double totalpaga_ticket){
        this.nombre_pasajero= nombre_pasajero;
        this.totalpaga_ticket= totalpaga_ticket;
    }

    public String getNombre_pasajero() {
        return nombre_pasajero;
    }

    public double getTotalpaga_ticket() {
        return totalpaga_ticket;
    }

    
    
}