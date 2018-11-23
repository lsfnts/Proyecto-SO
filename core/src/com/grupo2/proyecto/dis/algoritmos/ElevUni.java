/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.dis.algoritmos;

import com.badlogic.gdx.Gdx;
import com.grupo2.proyecto.dis.util.DisComparator;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Luis
 */
public class ElevUni implements AlgoDis {

    ArrayList<Integer> accesos;
    ArrayList<Integer> ordenados;
    ArrayList<String> completados;
    int tDes;
    int tiempo;
    int order;
    int oldPos;
    int pos;
    int desp;
    int inicial;
    int max;
    boolean izquierda;

    DisComparator comparator;

    public ElevUni(int ini, int tDes, int sel) {
        this.inicial = ini;
        this.oldPos = ini;
        this.tDes = tDes;
        this.izquierda = (sel == 0);
        completados = new ArrayList<>();
    }

    @Override
    public boolean simular() {
        if (ordenados.isEmpty()) {
            return true;
        }
        oldPos = pos;
        pos = ordenados.remove(0);
        int despActual = 0;
        if (!izquierda && pos < oldPos) {
            //despActual += -oldPos + pos + 38;
            despActual += pos;
            tiempo += despActual * tDes;
        } else if (izquierda && pos > oldPos) {
            //despActual += oldPos + 38 - pos;
            despActual += 19 - pos;
            tiempo += despActual * tDes;
        } else {
            despActual = Math.abs(pos - oldPos);
            tiempo += despActual * tDes;
        }
        completados.add(pos + ":" + despActual * tDes);
        desp += despActual;
        return false;
    }

    private int ordenar() {
        int i = 0;
        ordenados.add(100);
        if (izquierda) {
            for (int j = 0; j < accesos.size(); j++) {
                Integer get = accesos.get(j);
                if (get > pos) {
                    ordenados.add(get);
                } else if (get == pos) {
                    ordenados.add(0, get);
                    i++;
                } else {
                    ordenados.add(ordenados.indexOf(pos) + i++, get);
                }
            }
        } else {
            for (int j = 0; j < accesos.size(); j++) {
                Integer get = accesos.get(j);
                if (get < pos) {
                    ordenados.add(get);
                } else if (get == pos) {
                    ordenados.add(0, get);
                    i++;
                } else {
                    ordenados.add(ordenados.indexOf(pos) + i++, get);
                }
            }
        }
        ordenados.remove(Integer.valueOf(100));
        return pos;
    }

    @Override
    public void addAcceso(int a
    ) {
        ordenados.add(a);
        setAccesos(ordenados, pos);
    }

    @Override
    public void setAccesos(ArrayList<Integer> accesos, int inicial
    ) {
        accesos.sort(comparator);
        if (izquierda) {
            Collections.reverse(accesos);
        }
        pos = inicial;
        this.accesos = accesos;
        ordenados = new ArrayList<>();
        ordenar();
    }

    @Override
    public int getPos() {
        return pos;
    }

    @Override
    public int getTiempo() {
        return tiempo;
    }

    @Override
    public int getDesp() {
        return desp;
    }

    @Override
    public ArrayList<Integer> getAccesosRestantes() {
        return ordenados;
    }

    @Override
    public ArrayList<String> getAccesosPasados() {
        return completados;
    }

}
