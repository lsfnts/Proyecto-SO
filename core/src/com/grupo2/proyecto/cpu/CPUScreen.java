/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.cpu.Algoritmos.AlgoCPU;
import com.grupo2.proyecto.cpu.Algoritmos.RoundRobin;

/**
 *
 * @author Luis
 */
public class CPUScreen extends ScreenAdapter{
    
    private CPUView view;

    public CPUScreen(Main main, Skin skin, AlgoCPU algoCPU) {
        view = new CPUView(main, skin, algoCPU);
        
    }

    @Override
    public void render(float delta) {
        view.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }
    
    
}
