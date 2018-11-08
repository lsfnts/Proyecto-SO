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
    public int rafagaActual;
    public int t;
    int listoEn;
    public int tRespuesta;
    public int tEspera;
    public int tRetorno;
    boolean listo;
    boolean activo;
    boolean terminado;

    public Proceso(int id, int[] tRafagas, int rafagas, int listoEn) {
        this.id = id;
        this.tRafagas = new int[rafagas];
        System.arraycopy(tRafagas, 0, this.tRafagas, 0, rafagas);
        this.listoEn = listoEn;
        rafagaActual=0;
        t = this.tRafagas[rafagaActual];
    }
    
    /**
     *
     * @return true si termino la rafaga
     */
    public boolean usarCPU() {
        t -= 1;
        if(t == 0 ) {
            rafagaActual += 1;
            if(rafagaActual==tRafagas.length){
                terminado = true;
                rafagaActual = -1;
                return true;
            } else {
                t= tRafagas[rafagaActual];
            }
            return true;
        }
        return false;
    }
    
}
