/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu.algoritmos;

/**
 *
 * @author Luis
 */
public class Proceso {

    public int id;
    public int[] tRafagas;
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
    public boolean terRaf;

    public Proceso(int id, int[] tRafagas, int rafagas, int listoEn) {
        this.id = id;
        this.tRafagas = new int[rafagas];
        System.arraycopy(tRafagas, 0, this.tRafagas, 0, rafagas);
        this.listoEn = listoEn;
        rafagaActual = 0;
        t = this.tRafagas[rafagaActual];
        esperando = true;
        en = -1;
    }

    /**
     *
     * @return true si termino la rafaga
     */
    public boolean usarCPU() {
        t -= 1;
        if (t == 0) {
            terRaf = true;
            tAnte = tRafagas[rafagaActual];
            rafagaActual +=1;
            if (rafagaActual== tRafagas.length) {
                terminado = true;
                return true;
            } else {
                t = tRafagas[rafagaActual];
            }
            return true;
        }
        return false;
    }
    
    public int[] getRafagas(){
        int[] is = new int[tRafagas.length-rafagaActual];
        int i = 0;
        for (int j = rafagaActual; j < tRafagas.length; j++) {
            is[i++] = tRafagas[j];
        }
        return is;
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
        if (!terminado) {
            tRetorno += 1;
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
