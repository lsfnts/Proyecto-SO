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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.cpu.Algoritmos.AlgoCPU;
import com.grupo2.proyecto.cpu.Algoritmos.Proceso;
import com.grupo2.proyecto.cpu.Algoritmos.RoundRobin;
import com.grupo2.proyecto.cpu.util.ActorProc;

/**
 *
 * @author Luis
 */
public class CPUView {
    Skin skin;
    Stage stage;
    final Main main;
    AlgoCPU algoCPU;
    Table tProcesos;
    boolean termino;

    public CPUView(Main main, Skin skin, AlgoCPU algoCPU) {
        this.main = main;
        this.skin = skin;
        this.algoCPU = algoCPU;
        stage = new Stage(new FitViewport(1120, 900));
        Gdx.input.setInputProcessor(stage);
        
        final HorizontalGroup hg = new HorizontalGroup();
        hg.setFillParent(true);
        hg.expand().space(20);
        stage.addActor(hg);
        
        tProcesos = new Table();
        //llenarProcesos();
        hg.addActor(tProcesos.top());
        
        VerticalGroup vgPanel = new VerticalGroup().space(20);
        vgPanel.addActor(new Label("Round Robin", skin));
        vgPanel.addActor(new Label("Cuanto: 3 ms", skin));
        hg.addActor(vgPanel);
        
    }
    
    float acumulador = 0.0f;
    
    public void render(float delta){
        acumulador += delta;
        if(acumulador >= 1f && !termino){
            acumulador -= 1f;
            termino= algoCPU.simular();
            tProcesos.clear();
            llenarProcesos();
        }
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(1/30f);
        stage.draw();
    }
    
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    public void llenarProcesos() {
        for (int i = 0; i < algoCPU.getAProcs().size(); i++) {
            tProcesos.add(algoCPU.getAProcs().get(i));
            if(i%2 != 0) tProcesos.row();
        }
    }
}
