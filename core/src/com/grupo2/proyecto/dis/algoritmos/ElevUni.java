/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.dis.algoritmos;

import com.badlogic.gdx.Gdx;
import com.grupo2.proyecto.dis.util.DisComparator;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class ElevUni implements AlgoDis {

    ArrayList<Integer> accesos;
    ArrayList<Integer> ordenados;
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
            despActual += 19-pos;
            tiempo += despActual * tDes;
        } else {
            despActual = Math.abs(pos - oldPos);
            tiempo += despActual * tDes;
        }
        desp += despActual;
        return false;
    }

    private int nextPos() {
        if (izquierda && order != 0) {
            order -= 1;
        }
        pos = accesos.get(order);
        ordenados.add(accesos.remove(order));
        if (!izquierda && pos == max) {
            order = 0;
        } else if (izquierda && order == 0) {
            order = accesos.size() - 1;
        }
        if (order == accesos.size()) {
            order -= 1;
        }
        return pos;
    }

    @Override
    public void addAcceso(int a) {
        ordenados.add(a);
        setAccesos(ordenados, pos);
    }

    @Override
    public void setAccesos(ArrayList<Integer> accesos, int inicial) {
        accesos.add(inicial);
        accesos.sort(comparator);
        max = accesos.get(accesos.size() - 1);
        order = accesos.indexOf(inicial);
        accesos.remove(new Integer(inicial));
        if (order == accesos.size()) {
            order -= 1;
        }
        pos = inicial;
        this.accesos = accesos;
        ordenados = new ArrayList<>();
        for (int i = accesos.size(); i > 0; i--) {
            nextPos();
        }
        pos = inicial;
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
    public ArrayList<Integer> getAccesos() {
        return ordenados;
    }

}
