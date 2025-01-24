package com.mycompany.lab1progra2;

import javax.swing.*;
import java.awt.*;

public class PalindromoAir {
    Ticket[] asientos = new Ticket[30];
    JFrame frame;
    JTextArea textArea;

    public void menu() {
        if (frame == null) {

            frame = new JFrame("PalindromoAir");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 1));

           
            JButton btnVender = new JButton("Vender Ticket");
            JButton btnCancelar = new JButton("Cancelar Ticket");
            JButton btnDespachar = new JButton("Despachar Avión");
            JButton btnImprimir = new JButton("Imprimir Pasajeros");
            JButton btnSalir = new JButton("Salir");

          
            textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

          
            panel.add(btnVender);
            panel.add(btnCancelar);
            panel.add(btnDespachar);
            panel.add(btnImprimir);
            panel.add(btnSalir);

            frame.add(panel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

           
            btnVender.addActionListener(e -> {
                String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del pasajero:");
                if (nombre != null && !nombre.isEmpty()) {
                    sellTicket(nombre);
                }
            });

           
            btnCancelar.addActionListener(e -> {
                String nombre = JOptionPane.showInputDialog(frame, "Ingrese el nombre del pasajero a cancelar:");
                if (nombre != null && !nombre.isEmpty()) {
                    if (cancelticket(nombre)) {
                        textArea.append("Ticket cancelado con éxito.\n");
                    } else {
                        textArea.append("No se encontró un ticket para el pasajero indicado.\n");
                    }
                }
            });

          
            btnDespachar.addActionListener(e -> {
                double totalIngreso = income();
                textArea.append("Ingresos totales: $" + totalIngreso + "\n");
                reset();
                textArea.append("El avión ha sido despachado y los asientos han sido liberados.\n");
            });

          
            btnImprimir.addActionListener(e -> printPassengers());

         
            btnSalir.addActionListener(e -> System.exit(0));

      
            frame.setVisible(true);
        }
    }
   public int searchPassenger(String nombre) {
    return searchPassenger(nombre, 0); 
}

private int searchPassenger(String nombre, int index) {
    if (index >= asientos.length) {
        return -1;
    }
    if (asientos[index] != null && asientos[index].getNombre_pasajero().equalsIgnoreCase(nombre)) {
        return index; 
    }
    return searchPassenger(nombre, index + 1);
}
    
    public boolean cancelticket(String nombre) {
        return cancelticket(nombre, 0);
    }

    private boolean cancelticket(String nombre, int index) {
        if (index >= asientos.length) {
            return false; 
        }
        if (asientos[index] != null && asientos[index].getNombre_pasajero().equals(nombre)) {
            asientos[index] = null; 
            return true;
        }
        return cancelticket(nombre, index + 1); 
    }

    public int firstAvailable() {
        return firstAvailable(0);
    }

    private int firstAvailable(int index) {
        if (index >= asientos.length) {
            return -1; 
        }
        if (asientos[index] == null) {
            return index;
        }
        return firstAvailable(index + 1);
    }

    public static boolean isPalindromo(String name) {
        if (name.length() == 1 || name.length() == 0) {
            return true; 
        }
        if (name.charAt(0) != name.charAt(name.length() - 1)) {
            return false;
        }
        return isPalindromo(name.substring(1, name.length() - 1));
    }

   public void sellTicket(String nombre) {
  
    if (searchPassenger(nombre) != -1) { 
        textArea.append("El nombre " + nombre + " ya está registrado. Por favor, intente con otro nombre.\n");
        return;
    }

    
    int posicion = firstAvailable();
    if (posicion == -1) {
        textArea.append("No hay asientos disponibles.\n");
        return;
    }

   
    double precio = 800;
    if (isPalindromo(nombre)) {
        precio *= 0.8; 
    }

  
    asientos[posicion] = new Ticket(nombre, precio);
    textArea.append("Ticket vendido a " + nombre + " por $" + precio + ". Asiento: " + posicion + "\n");
}

    public void printPassengers() {
        printPassengers(0);
    }

    private void printPassengers(int index) {
        if (index >= asientos.length) {
            return;
        }
        if (asientos[index] != null) {
            textArea.append(asientos[index].getNombre_pasajero() + " - $" + asientos[index].getTotalpaga_ticket() + "\n");
        }
        printPassengers(index + 1);
    }

    public double income() {
        return income(0);
    }

    private double income(int index) {
        if (index >= asientos.length) {
            return 0; 
        }
        if (asientos[index] != null) {
            return asientos[index].getTotalpaga_ticket() + income(index + 1); 
        }
        return income(index + 1); 
    }

    public void reset() {
        reset(0);
    }

    private void reset(int index) {
        if (index >= asientos.length) {
            return;
        }
        asientos[index] = null; 
        reset(index + 1); 
    }
}
