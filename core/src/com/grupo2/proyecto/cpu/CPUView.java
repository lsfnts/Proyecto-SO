/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.Menus.CPUMenu;
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
    final HorizontalGroup hg;
    
    Label lCContexto;
    Label lTRespuesta;
    Label lTEspera;
    Label lTRetorno;
    Label lTactual;
    
    int aux;
    
    public CPUView(final Main main, final Skin skin, AlgoCPU algoCPU) {
        this.main = main;
        this.skin = skin;
        this.algoCPU = algoCPU;
        stage = new Stage(new FitViewport(1300, 900));
        Gdx.input.setInputProcessor(stage);
        
        hg = new HorizontalGroup();
        hg.setFillParent(true);
        hg.expand().space(20).top();
        stage.addActor(hg);
        
        tProcesos = new Table();
        tProcesos.add(new Container<Actor>().size(360, 1));
        hg.addActor(tProcesos);
        
        VerticalGroup vgPanel = new VerticalGroup().space(20);
        if(algoCPU instanceof RoundRobin) {
            vgPanel.addActor(new Label("Round Robin", skin));
            vgPanel.addActor(new Label("Cuanto: 3 ms", skin));
        } else {
            vgPanel.addActor(new Label("Tiempo mas corto\na continuacion", skin));
            vgPanel.addActor(new Label("a: 3 ms", skin));
            vgPanel.addActor(new Label("estimacion inicial: 3 ms", skin));
        }
        
        
        vgPanel.addActor(new Container(new Image(skin.getDrawable("slider_back_hor"))).width(400).pad(10));
        lCContexto = new Label("cambios de contexto:  0", skin);
        vgPanel.addActor(lCContexto);
        vgPanel.addActor(new Container().size(1, 20));
        lTRespuesta = new Label("Tiempo de respuesta\npromedio:  0 ms", skin);
        vgPanel.addActor(lTRespuesta);
        lTEspera = new Label("Tiempo de espera\npromedio:  0 ms", skin);
        vgPanel.addActor(lTEspera);
        lTRetorno = new Label("Tiempo de retorno\npromedio:  0 ms", skin);
        vgPanel.addActor(lTRetorno);
        vgPanel.addActor(new Container().size(1, 20));
        lTactual = new Label("Tiempo actual:  0 ms", skin);
        vgPanel.addActor(lTactual);
        HorizontalGroup hgButtons = new HorizontalGroup().expand().center();
        ImageButton ibBack = new ImageButton(skin.getDrawable("icon_back"));
        ibBack.getImage().setFillParent(true);
        ImageButton ibPause = new ImageButton(skin.getDrawable("icon_pause"));
        ibPause.getImage().setFillParent(true);
        ImageButton ibPlay = new ImageButton(skin.getDrawable("icon_play"));
        ibPlay.getImage().setFillParent(true);
        ImageButton ibStep = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("step.png")))));
        ibStep.getImage().setFillParent(true);
        hgButtons.addActor(new Container(ibBack).size(100,100));
        hgButtons.addActor(new Container(ibPause).size(100,100));
        hgButtons.addActor(new Container(ibPlay).size(100,100));
        hgButtons.addActor(new Container(ibStep).size(100,100).padRight(60));
        vgPanel.addActor(new Container(new Image(skin.getDrawable("slider_back_hor"))).width(400).pad(10));
        vgPanel.addActor(hgButtons);
        hg.addActor(vgPanel);
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(1/30f);
        stage.draw();
        
        ibBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                dispose();
                main.setScreen(new CPUMenu(main, skin));
            }
        });
        
        ibPause.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                aux = 0;
            }
        });
        
        ibPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                aux = -1;
                acumulador = 0;
            }
        });
        ibStep.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                aux = 1;
            }
        });
    }
    
    float acumulador = 0.0f;
    
    public void render(float delta){
        acumulador += delta;
        if(acumulador >= 1f && !termino && aux != 0){
            acumulador -= 1f;
            termino= algoCPU.simular();
            
            actualizar();
            aux--;
        }
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(1/30f);
        stage.draw();
    }
    
    public void actualizar() {
        tProcesos.clear();
        llenarProcesos();
        lCContexto.setText("cambios de contexto:  "+algoCPU.getCContexto());
        lTactual.setText("Tiempo actual:  "+ algoCPU.getT() +"ms");
        lTRespuesta.setText("Tiempo de respuesta\npromedio:  "+algoCPU.getPromedioRespuesta()+" ms");
        lTEspera.setText("Tiempo de espera\npromedio:  "+algoCPU.getPromedioEspera()+" ms");
        lTRetorno.setText("Tiempo de retorno\npromedio:  "+algoCPU.getPromedioRetorno()+" ms");
    }
    
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        hg.expand();
    }
    
    public void llenarProcesos() {
        int i = 0;
        for (i = 0; i < algoCPU.getAProcs().size(); i++) {
            tProcesos.add(algoCPU.getAProcs().get(i));
            if(i%2 != 0) tProcesos.row();
        }
        if(i == 1) {
            tProcesos.padLeft(140).padRight(200);
        } else {
            tProcesos.pad(20);
        }
    }
    
    public void dispose() {
        stage.dispose();
    }
}
