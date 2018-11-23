/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.mem.algoritmos;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.grupo2.proyecto.mem.util.ActorPag;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Luis
 */
public class Nfu implements AlgoMem {

    Skin skin;
    ArrayList<Integer> accesos;
    LinkedList<Integer> marcos;
    int marcosMax;
    ArrayList<ActorPag> actorPags;

    int ronda;
    int Taciertos;
    int Tfallos;

    int interval;
    int contador;
    boolean olvidar;
    boolean reestrablecer;

    boolean isR;
    ArrayList<String> anterior;

    public Nfu(Skin skin, int interval, boolean olvidar) {
        this.skin = skin;
        this.interval = interval;
        this.olvidar = olvidar;
        actorPags = new ArrayList<>();
    }

    private boolean solicitarPag(int i) {
        if (marcos.contains(i)) {
            Taciertos += 1;
            for (ActorPag actorPag : actorPags) {
                if (actorPag.equals(i)) {
                    actorPag.acierto();
                }
            }
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
            int menorC = Integer.MAX_VALUE;
            Integer i1 = null;
            for (Integer pag : marcos) {
                if (actorPags.get(pag).c < menorC) {
                    menorC = actorPags.get(pag).c;
                    i1 = pag;
                }

            }
            marcos.add(marcos.indexOf(i1), i);
            marcos.remove(i1);
        } else {
            marcos.add(i);
        }
    }

    private void sumarR() {
        for (Integer pag : marcos) {
            actorPags.get(pag).apagar();
            if (actorPags.get(pag).r) {
                actorPags.get(pag).sumarC(olvidar);
            }
        }
    }

    @Override
    public boolean simular() {
        clear();

        if (ronda == accesos.size()) {
            return true;
        }
        if (accesos.get(ronda) < 0) {
            reestrablecer = true;
            sumarR();
            isR = true;
            accesos.remove(ronda);
            anterior = new ArrayList<>();
            int i;
            for (i = 0; i < marcos.size(); i++) {
                if (actorPags.get(accesos.get(i)).r) {
                    anterior.add(String.valueOf(marcos.get(i)) + "\nR:1 C:" + actorPags.get(accesos.get(i)).c);
                } else {
                    anterior.add(String.valueOf(marcos.get(i)) + "\nR:0 C:" + actorPags.get(accesos.get(i)).c);
                }

            };
            for (; i < marcosMax; i++) {
                anterior.add("");
            }
            return false;
        }
        isR = false;
        reestrablecer = false;
        if (!solicitarPag(accesos.get(ronda))) {
            reemplazar(accesos.get(ronda));
        }
        anterior = new ArrayList<>();
        int i;
        for (i = 0; i < marcos.size(); i++) {
            if (actorPags.get(accesos.get(i)).r) {
                anterior.add(String.valueOf(marcos.get(i)) + "\nR:1 C:" + actorPags.get(accesos.get(i)).c);
            } else {
                anterior.add(String.valueOf(marcos.get(i)) + "\nR:0 C:" + actorPags.get(accesos.get(i)).c);
            }

        };
        for (; i < marcosMax; i++) {
            anterior.add("");
        }
        ronda += 1;
        return false;
    }

    private void clear() {
        for (ActorPag actorPag : actorPags) {
            actorPag.apagar();
        }
    }

    @Override
    public void setAccesos(ArrayList<Integer> al) {
        accesos = al;
        for (int i = 0; i < accesos.size(); i++) {
            if (!actorPags.contains(al.get(i))) {
                actorPags.add(new ActorPag(skin, al.get(i), olvidar));
            }
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
        if (reestrablecer) {
            return "R = 0";
        } else {
            return "ronda: " + ronda;
        }
    }

    @Override
    public ArrayList<String> getRondaAnterior() {
        return anterior;
    }

    @Override
    public boolean isR() {
        return isR;
    }

}
