/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.dis.algoritmos;

import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public interface AlgoDis {

    public boolean simular();

    public void addAcceso(int a);

    public void setAccesos(ArrayList accesos, int inicial);

    public int getDesp();

    public int getPos();

    public int getTiempo();

    public ArrayList<Integer> getAccesos();

}
