/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu.algoritmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.grupo2.proyecto.cpu.util.ActorProc;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Luis
 */
public class RoundRobin implements AlgoCPU {

    public int cuanto;
    int cuantoActual;
    int conmut;
    int conmutActual;
    public ArrayList<Proceso> procesos;
    public ArrayList<Proceso> cola;
    int porTerminar;
    public Proceso activo;
    public int nextIndex;
    public int t;
    private ArrayList<ActorProc> aProcs;
    Skin skin;
    int cContexto;
    boolean conmutando;

    public RoundRobin(ArrayList<Proceso> procesos, int conmut, Skin skin, int cuanto) {
        this.t = 0;
        this.cuanto = cuanto;
        cuantoActual = cuanto;
        this.conmut = conmut;
        this.skin = skin;
        Collections.reverse(procesos);
        this.procesos = procesos;
        this.cola = new ArrayList<Proceso>(6);
        this.aProcs = new ArrayList<ActorProc>(6);
        porTerminar = procesos.size();
    }

    private boolean usar() {
        cuantoActual -= 1;
        if (activo == null) {
            return false;
        }
        return activo.usarCPU();
    }

    private void siguienteProceso() {
        if (cola.isEmpty()) {
            nextIndex = 0;
            return;
        }
        cContexto += 1;
        nextIndex += 1;
        activo = cola.get(nextIndex % cola.size());
        activo.esperando = false;
    }

    @Override
    public boolean simular() {
        if (porTerminar == 0) {
            for (ActorProc aProc : aProcs) {
                aProc.desactivar();
            }
            return true;
        }
        for (ActorProc aProc : aProcs) {
            aProc.desactivar();
            aProc.actualizar();
        }
        if (conmutActual != 0) {
            conmutando = true;
            for (Proceso proceso : cola) {
                proceso.addtRespuesta();
                proceso.addtEspera();
            }
            conmutActual -= 1;
            t++;
            return false;
        }
        conmutando = false;
        nuevoProcListo();
        if (activo != null) {
            aProcs.get(procesos.indexOf(activo)).activar();
        }
        if (cuantoActual == 0 || usar()) {
            verSiTermino(activo);
            cuantoActual = cuanto;
            conmutActual = conmut;
            siguienteProceso();
        }
        t++;
        for (Proceso proceso : cola) {
            proceso.addtRespuesta();
            if (!proceso.equals(activo)) {
                proceso.addtEspera();
            }
            proceso.addtRetorno();
        }
        for (ActorProc aProc : aProcs) {
            aProc.actualizar();
        }
        return false;
    }

    public void setAProcs(ArrayList<ActorProc> aProcs) {
        this.aProcs = aProcs;
    }

    private void verSiTermino(Proceso proceso) {
        if (proceso.terminado) {
            porTerminar -= 1;
            if (cola.indexOf(activo) <= (nextIndex % cola.size())) {
                nextIndex -= 1;
            }
            aProcs.get(procesos.indexOf(activo)).terminar();
            cola.remove(proceso);
        }
    }

    @Override
    public int getT() {
        return t;
    }

    @Override
    public ArrayList<ActorProc> getAProcs() {
        return aProcs;
    }

    private void nuevoProcListo() {
        for (Proceso proceso : procesos) {
            if (proceso.listoEn == t) {
                cola.add(proceso);
                aProcs.add(new ActorProc(skin, proceso, procesos.indexOf(proceso) + 1));
            }
            if (activo == null) {
                activo = cola.get(0);
                activo.esperando = false;
                aProcs.get(cola.indexOf(proceso)).activar();
            }
        }
    }

    @Override
    public float getPromedioRespuesta() {
        float total = 0;
        for (Proceso proceso : procesos) {
            total += proceso.tRespuesta;
        }
        return total / procesos.size();
    }

    @Override
    public float getPromedioEspera() {
        float total = 0;
        for (Proceso proceso : procesos) {
            total += proceso.tEspera;
        }
        return total / procesos.size();
    }

    @Override
    public float getPromedioRetorno() {
        float total = 0;
        for (Proceso proceso : procesos) {
            total += proceso.tRetorno;
        }
        return total / procesos.size();
    }

    @Override
    public int getCContexto() {
        return cContexto;
    }

    @Override
    public int[] getConfig() {
        return new int[0];
    }

    @Override
    public boolean isConmutando() {
        return conmutando;
    }

}
