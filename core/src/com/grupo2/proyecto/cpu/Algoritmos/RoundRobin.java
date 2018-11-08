/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu.Algoritmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.grupo2.proyecto.cpu.util.ActorProc;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Luis
 */
public class RoundRobin implements AlgoCPU{
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

    @Override
    public boolean usar() {
        cuantoActual -= 1;
        if(activo == null) return false;
        return activo.usarCPU();
    }

    @Override
    public void siguienteProceso() {
        if(cola.isEmpty()) {
            nextIndex = 0;
            return;
        }
        Gdx.app.log("Cambiando a proceso", String.valueOf(nextIndex +1));
        nextIndex +=1;
        activo = cola.get(nextIndex % cola.size());
        conmutActual = conmut;
    }
    
    @Override
    public boolean simular() {
        if(conmutActual != 0) {
            conmutActual -= 1;
            return false;
        }
        if(activo != null) aProcs.get(nextIndex % cola.size()).activar();
        nuevoProcListo();
        if(cuantoActual == 0 || usar()) {
            aProcs.get(cola.indexOf(activo)).desactivar();
            verSiTermino(activo);
            cuantoActual = cuanto;
            siguienteProceso();
        }
        t++;
        for (ActorProc aProc : aProcs) {
            aProc.actualizar();
        }
        return porTerminar==0;
    }

    public void setAProcs(ArrayList<ActorProc> aProcs) {
        this.aProcs = aProcs;
    }

    public void verSiTermino(Proceso proceso) {
        if(proceso.terminado) {
            porTerminar -= 1;
            if(cola.indexOf(activo) <= (nextIndex % cola.size()) ) {
                nextIndex -= 1;
            }
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

    @Override
    public void nuevoProcListo() {
        for (Proceso proceso : procesos) {
            if(proceso.listoEn == t) {
                cola.add(proceso);
                aProcs.add(new ActorProc(skin, proceso));
            }
            if(activo == null){
                activo = cola.get(0);
                aProcs.get(cola.indexOf(proceso)).activar();
            }
        }
    }

}
