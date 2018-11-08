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
    public float en;
    public int t;
    public int tAnte;
    int listoEn;
    public int tRespuesta;
    public int tEspera;
    public int tRetorno;
    boolean esperando;
    boolean terminado;

    public Proceso(int id, int[] tRafagas, int rafagas, int listoEn) {
        this.id = id;
        this.tRafagas = new int[rafagas];
        System.arraycopy(tRafagas, 0, this.tRafagas, 0, rafagas);
        this.listoEn = listoEn;
        rafagaActual = 0;
        t = this.tRafagas[rafagaActual];
        esperando = true;
    }

    /**
     *
     * @return true si termino la rafaga
     */
    public boolean usarCPU() {
        t -= 1;
        if (t == 0) {
            tAnte = tRafagas[rafagaActual];
            rafagaActual += 1;
            if (rafagaActual == tRafagas.length) {
                terminado = true;
                rafagaActual = -1;
                return true;
            } else {
                t = tRafagas[rafagaActual];
            }
            return true;
        }
        return false;
    }

    public void addtRespuesta() {
        if (esperando) {
            tRespuesta += 1;
        }
    }

    public void addtEspera() {
        tEspera += 1;
    }

    public void addtRetorno() {
        if(!terminado){
            tRetorno +=1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Proceso) {
            return this.id == ((Proceso) obj).id;
        }
        return false;
    }

}
