/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.dis;

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
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.dis.algoritmos.AlgoDis;
import com.grupo2.proyecto.dis.algoritmos.Ssf;
import com.grupo2.proyecto.dis.util.ActorDisSolList;
import com.grupo2.proyecto.menus.DisMenu;

/**
 *
 * @author Luis
 */
public class DisView {

    Skin skin;
    Stage stage;
    final Main main;
    AlgoDis algoDis;
    
    Slider sBrazo;
    Label lCilDes;
    Label lTiempo;
    ActorDisSolList disSolList;
    ActorDisSolList histList;
    
    boolean termino;
    int aux;

    public DisView(Main main, Skin skin,AlgoDis algoDis) {
        this.main = main;
        this.skin = skin;
        this.algoDis = algoDis;
        
        stage = new Stage(new FitViewport(1530, 900));
        
        HorizontalGroup hg = new HorizontalGroup();
        hg.setFillParent(true);
        hg.expand().space(20).top().pad(80);
        stage.addActor(hg);
        Gdx.input.setInputProcessor(stage);
        
        VerticalGroup vgCils = new VerticalGroup();
        
        HorizontalGroup hgAddSolic = new HorizontalGroup().space(20).pad(15);
        hgAddSolic.addActor(new Label("Cilindro #", skin, "dark"));
        final SelectBox sbCilNum = new SelectBox(skin);
        sbCilNum.setItems(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
        sbCilNum.setMaxListCount(5);
        hgAddSolic.addActor(sbCilNum);

        TextButton agregar = new TextButton("Agregar", skin);
        hgAddSolic.addActor(agregar);
        Table tAdd = new Table(skin);
        tAdd.setBackground(skin.getDrawable("textbox_01"));
        tAdd.add(hgAddSolic);
        vgCils.addActor(new Container(tAdd).padBottom(60));
        
        sBrazo  = new Slider(0, 19, 1, false, skin,"brazo");
        sBrazo.setValue(algoDis.getPos());
        vgCils.addActor(new Container(sBrazo).width(790).height(80));
        HorizontalGroup hgCils = new HorizontalGroup().padBottom(80);
        for (int i = 0; i < 10; i++) {
            hgCils.addActor(new Container(new Window("\n   "+String.valueOf(i), skin, "cilindro")).width(40).height(225).center());
        }
        hgCils.addActor(new Container(new Window("\n  10", skin, "cilindro")).width(40).height(225).center());
        hgCils.addActor(new Container(new Window("\n   11", skin, "cilindro")).width(40).height(225).center());
        for (int i = 12; i < 20; i++) {
            hgCils.addActor(new Container(new Window("\n  "+String.valueOf(i), skin, "cilindro")).width(40).height(225).center());
        }
        vgCils.addActor(hgCils);
        
        vgCils.addActor(new Label("Solicitudes restantes", skin));
        disSolList = new ActorDisSolList(skin);
        disSolList.setAcces(algoDis.getAccesosRestantes());
        ScrollPane spSolic = new ScrollPane(disSolList.getActor(), skin);
        vgCils.addActor(new Container(spSolic).size(650, 80).pad(10).top().padTop(10));
        
        
        vgCils.addActor(new Label("Solicitudes anteriores", skin));
        histList = new ActorDisSolList(skin);
        ScrollPane spHist = new ScrollPane(histList.getActor(), skin);
        vgCils.addActor(new Container(spHist).size(650, 80).pad(10).top().padTop(10));
        
        hg.addActor(vgCils);
        
        VerticalGroup vgPanel = new VerticalGroup().space(20);
        if(algoDis instanceof Ssf) {
            vgPanel.addActor(new Label("SSF", skin));
        } else {
            vgPanel.addActor(new Label("Elevador unidireccional", skin));
        }
        
        vgPanel.addActor(new Container(new Image(skin.getDrawable("slider_back_hor"))).width(400).pad(10));
        lCilDes = new Label("cilindros desplazados:  0", skin);
        vgPanel.addActor(lCilDes);
        lTiempo = new Label("tiempo:  0 ms", skin);
        vgPanel.addActor(lTiempo);
        vgPanel.addActor(new Container().size(1, 20));
        vgPanel.addActor(new Container(new Image(skin.getDrawable("slider_back_hor"))).width(400).pad(10));
        
        HorizontalGroup hgButtons = new HorizontalGroup().expand().center().padTop(300);
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
        
        agregar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                algoDis.addAcceso(sbCilNum.getSelectedIndex());
                disSolList.setAcces(algoDis.getAccesosRestantes());
                termino = false;
            }
        });
        
        ibBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                dispose();
                main.setScreen(new DisMenu(main, skin));
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
            termino= algoDis.simular();
            
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
    
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    public void dispose() {
        stage.dispose();
    }
    
    public void actualizar() {
        sBrazo.setValue(algoDis.getPos());
        lCilDes.setText("cilindros desplazados:  "+algoDis.getDesp());
        lTiempo.setText("tiempo:  "+algoDis.getTiempo()+" ms");
        disSolList.setAcces(algoDis.getAccesosRestantes());
        histList.setStrings(algoDis.getAccesosPasados());
    }
    
}
