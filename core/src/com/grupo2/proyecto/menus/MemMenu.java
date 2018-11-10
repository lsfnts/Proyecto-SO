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
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
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
import com.grupo2.proyecto.mem.MemScreen;
import com.grupo2.proyecto.mem.algoritmos.AlgoMem;
import com.grupo2.proyecto.mem.algoritmos.Fifo;
import com.grupo2.proyecto.mem.algoritmos.Nfu;
import com.grupo2.proyecto.menus.util.InfoAcceso;

/**
 *
 * @author Luis
 */
public class MemMenu extends ScreenAdapter {

    Skin skin;
    Stage stage;
    final Main main;

    public MemMenu(final Main main, final Skin skin) {
        this.main = main;
        this.skin = skin;
        stage = new Stage(new FitViewport(1000, 800));
        Gdx.input.setInputProcessor(stage);

        final VerticalGroup vg = new VerticalGroup();
        vg.setFillParent(true);
        vg.expand().space(20).pad(20);
        stage.addActor(vg);

        HorizontalGroup hgMarcosNumSel = new HorizontalGroup();
        Label lMarcosNumSel = new Label("Numero de marcos:", skin);
        hgMarcosNumSel.addActor(lMarcosNumSel);
        final SelectBox sbMarcosNumSel = new SelectBox(skin);
        sbMarcosNumSel.setItems(1, 2, 3, 4, 5, 6);
        sbMarcosNumSel.setMaxListCount(5);
        hgMarcosNumSel.addActor(sbMarcosNumSel);
        vg.addActor(hgMarcosNumSel);
        HorizontalGroup hgAddAcceso = new HorizontalGroup().space(20).pad(15);

        VerticalGroup vgNum = new VerticalGroup().space(5);
        HorizontalGroup hgAccesNum = new HorizontalGroup();
        hgAccesNum.addActor(new Label("Pag. Virtual #", skin, "dark"));
        final TextField tfAccesNum = new TextField("0", skin);
        hgAccesNum.addActor(tfAccesNum);
        vgNum.addActor(hgAccesNum);
        final CheckBox cbEscritura = new CheckBox(" Lectura", skin);
        vgNum.addActor(cbEscritura);

        hgAddAcceso.addActor(vgNum);
        TextButton agregar = new TextButton("Agregar", skin);
        hgAddAcceso.addActor(agregar);
        Table tAdd = new Table(skin);
        tAdd.setBackground(skin.getDrawable("textbox_01"));
        tAdd.add(hgAddAcceso);
        vg.addActor(new Container(tAdd));

        final InfoAcceso ia = new InfoAcceso(skin);

        final ScrollPane spProcess = new ScrollPane(ia.getActor(), skin);
        Container container = new Container(spProcess);
        container.height(240).padBottom(10);
        vg.addActor(container);

        final VerticalGroup vgTipoAlgo = new VerticalGroup();
        vgTipoAlgo.space(10);

        vgTipoAlgo.addActor(new Label("Algoritmo", skin));
        final SelectBox sbAlgo = new SelectBox(skin);
        sbAlgo.setItems("FIFO", "NFU");
        vgTipoAlgo.addActor(sbAlgo);
        vg.addActor(vgTipoAlgo);

        final Table tableNFU = new Table().pad(5);
        tableNFU.add(new Label("rondas para sumar R: ", skin));
        final TextField tR = new TextField("0", skin);
        tableNFU.add(tR);
        tableNFU.row().pad(10);
        tableNFU.add(new Label("Olvidar", skin));
        final CheckBox cbOlvidar = new CheckBox("", skin);
        tableNFU.add(cbOlvidar);

        HorizontalGroup hgButtons = new HorizontalGroup().space(60);
        final TextButton buttonAccept = new TextButton("Aceptar", skin);
        ImageButton ibBack = new ImageButton(skin.getDrawable("icon_back"));
        ibBack.getImage().setFillParent(true);
        ibBack.setFillParent(true);
        hgButtons.addActor(new Container(ibBack).size(60));
        buttonAccept.pad(15);
        hgButtons.addActor(buttonAccept);
        vg.addActor(hgButtons);

        cbEscritura.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (cbEscritura.isChecked()) {
                    cbEscritura.setText(" Escritura");
                } else {
                    cbEscritura.setText(" Lectura");
                }

            }
        });

        agregar.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                ia.addAcceso(Integer.valueOf(tfAccesNum.getText()), cbEscritura.isChecked());
            }
        });

        sbAlgo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (sbAlgo.getSelected().equals("NFU")) {
                    vgTipoAlgo.addActor(tableNFU);
                } else {
                    vgTipoAlgo.removeActor(tableNFU);
                }
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
                AlgoMem algoMem;
                if (sbAlgo.getSelected().equals("FIFO")) {
                    algoMem = new Fifo(skin);
                    algoMem.setAccesos(ia.getAccesos());
                } else {
                    algoMem = new Nfu(skin, Integer.valueOf(tR.getText()), cbOlvidar.isChecked());
                    algoMem.setAccesos(ia.getAccesosNfu(Integer.valueOf(tR.getText())));
                }
                algoMem.setMarcos(sbMarcosNumSel.getSelectedIndex() + 1);
                main.setScreen(new MemScreen(main, skin, algoMem));
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
