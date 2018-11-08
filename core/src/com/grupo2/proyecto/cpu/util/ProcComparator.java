/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu.util;

import com.grupo2.proyecto.cpu.Algoritmos.Proceso;
import java.util.Comparator;

/**
 *
 * @author Luis
 */
public class ProcComparator implements Comparator<Proceso>{

    @Override
    public int compare(Proceso o1, Proceso o2) {
        if(o1.en < o2.en) return -1;
        else if (o1.en > o2.en) return 1;
        else return 0;
    }
    
}
