/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu.Algoritmos;

/**
 *
 * @author Luis
 */
public class Proceso {
    int id;
    int[] tRafagas;
    int tTranscurrido;
    int listoEn;
    int tRespuesta;
    int tEspera;
    int tRetorno;

    public Proceso(int id, int[] tRafagas, int rafagas, int listoEn) {
        this.id = id;
        this.tRafagas = new int[rafagas];
        for (int i = 0; i < rafagas; i++) {
            this.tRafagas[i] = tRafagas[i];
        }
        this.listoEn = listoEn;
    }
    
    
}
