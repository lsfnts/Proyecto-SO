/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.dis;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.dis.algoritmos.AlgoDis;

/**
 *
 * @author Luis
 */
public class DisScreen extends ScreenAdapter{
    private DisView view;

    public DisScreen(Main main, Skin skin, AlgoDis algoDis) {
        view = new DisView(main, skin, algoDis);
        
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
