/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.cpu.Algoritmos.RoundRobin;
import com.grupo2.proyecto.cpu.util.ActorCPU;

/**
 *
 * @author Luis
 */
public class CPUView {
    Skin skin;
    Stage stage;
    final Main main;
    RoundRobin rr;

    public CPUView(Main main, Skin skin, RoundRobin rr) {
        this.main = main;
        this.skin = skin;
        stage = new Stage(new FitViewport(1120, 900));
        Gdx.input.setInputProcessor(stage);
        
        final HorizontalGroup hg = new HorizontalGroup();
        hg.setFillParent(true);
        hg.expand().pad(20).top().left();
        stage.addActor(hg);
        
        Table tProcesos = new Table();
        for (int i = 0; i < rr.cola.size(); i++) {
            tProcesos.add(new ActorCPU(skin));
            if(i==3) tProcesos.row();
        }
        hg.addActor(tProcesos.top());
        
    }
    
    public void render(float delta){
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }
    
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
}
