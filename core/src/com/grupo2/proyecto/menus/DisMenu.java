/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.mem.algoritmos.Fifo;
import com.grupo2.proyecto.mem.algoritmos.Nfu;
import com.grupo2.proyecto.menus.util.InfoAccesoDis;

/**
 *
 * @author Luis
 */
public class DisMenu extends ScreenAdapter {

    Skin skin;
    Stage stage;
    final Main main;

    public DisMenu(final Main main, final Skin skin) {
        this.main = main;
        this.skin = skin;
        stage = new Stage(new FitViewport(1180, 800));
        Gdx.input.setInputProcessor(stage);

        HorizontalGroup hg = new HorizontalGroup();
        hg.setFillParent(true);
        hg.expand().space(20).pad(20);
        stage.addActor(hg);

        VerticalGroup vgPanel = new VerticalGroup().space(10);
        hg.addActor(vgPanel);

        HorizontalGroup hgPosIni = new HorizontalGroup().padBottom(30);
        Label lPosIni = new Label("Numero de marcos:", skin);
        hgPosIni.addActor(lPosIni);
        final SelectBox sbPosIni = new SelectBox(skin);
        sbPosIni.setItems(1, 2, 3, 4, 5, 6);
        sbPosIni.setMaxListCount(5);
        hgPosIni.addActor(sbPosIni);
        vgPanel.addActor(hgPosIni);

        vgPanel.addActor(new Label("velocidad de desplazamiento", skin));
        HorizontalGroup hgTDesp = new HorizontalGroup().padBottom(60);
        final TextField tfTDesp = new TextField("0", skin);
        hgTDesp.addActor(tfTDesp);
        hgTDesp.addActor(new Label(" ms", skin));
        vgPanel.addActor(hgTDesp);

        HorizontalGroup hgAddSolic = new HorizontalGroup().space(20).pad(15);
        HorizontalGroup hgCilNum = new HorizontalGroup();
        hgCilNum.addActor(new Label("Cilindro #", skin, "dark"));
        final TextField tfCilNum = new TextField("0", skin);
        hgCilNum.addActor(tfCilNum);

        hgAddSolic.addActor(hgCilNum);
        TextButton agregar = new TextButton("Agregar", skin);
        hgAddSolic.addActor(agregar);
        Table tAdd = new Table(skin);
        tAdd.setBackground(skin.getDrawable("textbox_01"));
        tAdd.add(hgAddSolic);
        vgPanel.addActor(new Container(tAdd).padBottom(60));

        final VerticalGroup vgTipoAlgo = new VerticalGroup();
        vgTipoAlgo.space(10);

        vgTipoAlgo.addActor(new Label("Algoritmo", skin));
        final SelectBox sbAlgo = new SelectBox(skin);
        sbAlgo.setItems("SSF", "Elevador unidireccional");
        vgTipoAlgo.addActor(sbAlgo);
        vgPanel.addActor(vgTipoAlgo);

        final HorizontalGroup hgDireccion = new HorizontalGroup();
        hgDireccion.space(10);

        hgDireccion.addActor(new Label("Direccion", skin));
        final SelectBox sbDir = new SelectBox(skin);
        sbDir.setItems("Izquierda", "Derecha");
        hgDireccion.addActor(sbDir);

        HorizontalGroup hgButtons = new HorizontalGroup().space(60).padTop(60);
        final TextButton buttonAccept = new TextButton("Aceptar", skin);
        ImageButton ibBack = new ImageButton(skin.getDrawable("icon_back"));
        ibBack.getImage().setFillParent(true);
        ibBack.setFillParent(true);
        hgButtons.addActor(new Container(ibBack).size(60));
        buttonAccept.pad(10);
        buttonAccept.invalidate();
        hgButtons.addActor(buttonAccept);
        vgPanel.addActor(hgButtons);

        final InfoAccesoDis ia = new InfoAccesoDis(skin);
        final ScrollPane spProcess = new ScrollPane(ia.getActor(), skin);
        hg.addActor(new Container(spProcess).size(470, 600).pad(10).center());

        sbAlgo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (sbAlgo.getSelected().equals("Elevador unidireccional")) {
                    vgTipoAlgo.addActor(hgDireccion);
                } else {
                    vgTipoAlgo.removeActor(hgDireccion);
                }
            }
        });

        agregar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ia.addAcceso(Integer.valueOf(tfCilNum.getText()));
            }
        });

        ibBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                dispose();
                main.setScreen(new MainMenu(main, skin));
            }
        });

        buttonAccept.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (sbAlgo.getSelectedIndex() == 0) {
                } else {
                }
                dispose();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(delta, 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
