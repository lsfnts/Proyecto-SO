/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.mem;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.mem.algoritmos.AlgoMem;

/**
 *
 * @author Luis
 */
public class MemScreen extends ScreenAdapter{
    
    private MemView view;

    public MemScreen(Main main, Skin skin, AlgoMem algoMem) {
        view = new MemView(main, skin, algoMem);
        
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
