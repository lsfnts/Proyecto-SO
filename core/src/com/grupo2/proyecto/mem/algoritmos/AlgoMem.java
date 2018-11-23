/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.mem.algoritmos;

import com.grupo2.proyecto.mem.util.ActorPag;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public interface AlgoMem {
    
    public boolean simular();
    
    public void setMarcos(int size);
    
    public void setAccesos(ArrayList<Integer> al);
    
    public ActorPag getPagInMarco(int pos);
    
    public int getMarcosMax();
    
    public int getTAciertos();
    
    public int getTFallos();
    
    public String getRondaText();
    
    public ArrayList<String> getRondaAnterior();
    
    public boolean isR();
}
