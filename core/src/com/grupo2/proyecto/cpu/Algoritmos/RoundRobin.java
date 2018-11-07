/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu.Algoritmos;

import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class RoundRobin {
    int cuanto;
    public ArrayList<Proceso> cola;

    public RoundRobin(int cuanto, int colaSize) {
        this.cuanto = cuanto;
        cola = new ArrayList<Proceso>(4);
    }

}
