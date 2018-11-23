/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.mem;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.mem.algoritmos.AlgoMem;
import com.grupo2.proyecto.mem.algoritmos.Fifo;
import com.grupo2.proyecto.menus.MemMenu;

/**
 *
 * @author Luis
 */
public class MemView {
    
    Skin skin;
    Stage stage;
    final Main main;
    AlgoMem algoMem;
    Table tMarcos;
    Stack[] marcos;
    
    Label lTaciertos;
    Label lTfallos;
    Label lRonda;
    boolean termino;
    private int aux;
   HistorialMem historialMem; 

    public MemView(Main main, Skin skin, AlgoMem algoMem) {
        this.main = main;
        this.skin = skin;
        this.algoMem = algoMem;
        stage = new Stage(new FitViewport(1400, 900));
        
        HorizontalGroup hg = new HorizontalGroup();
        hg.setFillParent(true);
        hg.expand().space(20).top().pad(80);
        stage.addActor(hg);
        Gdx.input.setInputProcessor(stage);
        
        tMarcos = new Table();
        hg.addActor(tMarcos);
        marcos = new Stack[algoMem.getMarcosMax()];
        for (int i = 0; i < algoMem.getMarcosMax(); i++) {
            marcos[i] = new Stack(new Window("\n"+String.valueOf(i), skin, "caja"));
            tMarcos.add(marcos[i]).width(285).height(225).pad(15).center();
            if(i%2 != 0) tMarcos.row();
        }
        
        VerticalGroup vgPanel = new VerticalGroup().space(20);
        if(algoMem instanceof Fifo) {
            vgPanel.addActor(new Label("FIFO", skin));
        } else {
            vgPanel.addActor(new Label("NFU", skin));
        }
        
        
        vgPanel.addActor(new Container(new Image(skin.getDrawable("slider_back_hor"))).width(400).pad(10));
        lTaciertos = new Label("aciertos:  0", skin);
        vgPanel.addActor(lTaciertos);
        lTfallos = new Label("fallos:  0", skin);
        vgPanel.addActor(lTfallos);
        vgPanel.addActor(new Container().size(1, 20));
        
        lRonda = new Label("ronda:  0 ", skin);
        vgPanel.addActor(lRonda);
        vgPanel.addActor(new Container(new Image(skin.getDrawable("slider_back_hor"))).width(400).pad(10));
        
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
        
        vgPanel.addActor(hgButtons);
        hg.addActor(vgPanel);
        
        historialMem = new HistorialMem(skin);
        final ScrollPane spAccesos = new ScrollPane(historialMem.getActor(), skin);
        
        vgPanel.addActor(new Container(spAccesos).size(600, 240).pad(10).center());
        
        ibBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                main.setScreen(new MemMenu(main, skin));
                dispose();
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
                acumulador = 0.6f;
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
    
    public void render(float delta) {
        acumulador += delta;
        if(acumulador >= 1f && !termino && aux != 0){
            acumulador -= 1f;
            termino= algoMem.simular();
            
            actualizar();
            aux--;
        } if (termino && aux != 0){
            actualizar();
            aux = 0;
        }
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }
    
    public void actualizar() {
        lTaciertos.setText("aciertos:  "+algoMem.getTAciertos());
        lTfallos.setText("fallos:  "+algoMem.getTFallos());
        lRonda.setText(algoMem.getRondaText());
        
        for (int i = 0; i < marcos.length; i++) {
            marcos[i].add(algoMem.getPagInMarco(i));
        }
        if(algoMem instanceof Fifo){
            historialMem.addRow(algoMem.getRondaAnterior());
        } else {
            historialMem.addRow(algoMem.getRondaAnterior(),algoMem.isR());
        }
        
    }
    
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    public void dispose() {
        stage.dispose();
    }
}
