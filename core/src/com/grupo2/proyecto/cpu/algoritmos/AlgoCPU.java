/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu.algoritmos;

import com.grupo2.proyecto.cpu.util.ActorProc;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public interface AlgoCPU {
    public boolean simular();

    public ArrayList<ActorProc> getAProcs();
    
    public float getPromedioRespuesta();
    
    public float getPromedioEspera();
    
    public float getPromedioRetorno();
    
    public int getCContexto();
    
    public int[] getConfig();
    
    public int getT();
    
    public boolean isConmutando();
}
