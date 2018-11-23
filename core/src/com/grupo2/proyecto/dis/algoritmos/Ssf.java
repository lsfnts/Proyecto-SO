/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.dis.algoritmos;

import com.badlogic.gdx.Gdx;
import com.grupo2.proyecto.dis.util.DisComparator;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Luis
 */
public class Ssf implements AlgoDis {

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
    boolean izquierda;

    DisComparator comparator;

    public Ssf(int pos, int tDes) {
        this.inicial = pos;
        this.tDes = tDes;
        completados = new ArrayList<>();
    }

    @Override
    public boolean simular() {
        if (ordenados.isEmpty()) {
            return true;
        }
        oldPos = pos;
        pos = ordenados.remove(0);
        desp += Math.abs(pos - oldPos);
        completados.add(pos+":"+Math.abs(pos - oldPos) * tDes);
        tiempo += Math.abs(pos - oldPos) * tDes;
        return false;
    }

    private int nextPos() {
        if (order != 0) {
            int aux;
            aux = Integer.compare((Math.abs(pos - accesos.get(order - 1))), (Math.abs(pos - accesos.get(order))));
            if (aux < 0 || (aux == 0 && izquierda)) {
                order -= 1;
            }
        }
        pos = accesos.get(order);
        ordenados.add(accesos.remove(order));
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
    public void setAccesos(ArrayList accesos, int inicial) {
        accesos.add(inicial);
        accesos.sort(comparator);
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
    public ArrayList<Integer> getAccesosRestantes() {
        return ordenados;
    }

    @Override
    public ArrayList<String> getAccesosPasados() {
        return completados;
    }

}
