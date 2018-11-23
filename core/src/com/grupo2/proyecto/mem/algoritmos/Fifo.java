/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.mem.algoritmos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.grupo2.proyecto.mem.util.ActorPag;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Luis
 */
public class Fifo implements AlgoMem {

    Skin skin;
    int[] accesos;
    LinkedList<Integer> marcos;
    int marcosMax;
    ArrayList<ActorPag> actorPags;

    ArrayList<String> anterior;

    int ronda;
    int Taciertos;
    int Tfallos;

    public Fifo(Skin skin) {
        this.skin = skin;
        actorPags = new ArrayList<>();
    }

    private boolean solicitarPag(int i) {
        if (marcos.contains(i)) {
            Taciertos += 1;
            for (ActorPag actorPag : actorPags) {
                if (actorPag.equals(i)) {
                    actorPag.fallo();
                }
            }
            actorPags.get(accesos[i]).acierto();
            return true;
        } else {
            Tfallos += 1;
            for (ActorPag actorPag : actorPags) {
                if (actorPag.equals(i)) {
                    actorPag.fallo();
                }
            }
            return false;
        }
    }

    private void reemplazar(int i) {
        if (marcos.size() >= marcosMax) {
            marcos.removeFirst();
        }
        marcos.addLast(i);
    }

    @Override
    public boolean simular() {
        clear();
        anterior = new ArrayList<>();
        if (ronda == accesos.length) {
            return true;
        }
        if (!solicitarPag(accesos[ronda])) {
            reemplazar(accesos[ronda]);
        }
        int i;
        for (i = 0; i < marcos.size(); i++) {
            anterior.add(String.valueOf(marcos.get(i)));
        };
        for (; i < marcosMax; i++) {
            anterior.add("");
        }
        ronda += 1;
        return false;
    }

    @Override
    public void setAccesos(ArrayList<Integer> al) {
        accesos = al.stream().mapToInt(i -> i).toArray();
        for (int i = 0; i < accesos.length; i++) {
            if (!actorPags.contains(al.get(i))) {
                actorPags.add(new ActorPag(skin, al.get(i)));
            }
        }
    }

    private void clear() {
        for (ActorPag actorPag : actorPags) {
            actorPag.apagar();
        }
    }

    @Override
    public void setMarcos(int size) {
        marcos = new LinkedList<>();
        marcosMax = size;
    }

    @Override
    public ActorPag getPagInMarco(int pos) {
        if (marcos.size() <= pos) {
            return new ActorPag();
        }
        int i = marcos.get(pos);
        for (ActorPag actorPag : actorPags) {
            
            if (actorPag.equals(i)) {
                return actorPag;
            }
        }
        return new ActorPag();
    }

    @Override
    public int getMarcosMax() {
        return marcosMax;
    }

    @Override
    public int getTAciertos() {
        return Taciertos;
    }

    @Override
    public int getTFallos() {
        return Tfallos;
    }

    @Override
    public String getRondaText() {
        return "ronda: " + ronda;
    }

    @Override
    public ArrayList<String> getRondaAnterior() {
        return anterior;
    }

    @Override
    public boolean isR() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
