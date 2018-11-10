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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
import com.grupo2.proyecto.cpu.algoritmos.Proceso;
import com.grupo2.proyecto.cpu.algoritmos.RoundRobin;
import com.grupo2.proyecto.cpu.algoritmos.TMCaC;
import com.grupo2.proyecto.cpu.CPUScreen;
import com.grupo2.proyecto.cpu.algoritmos.AlgoCPU;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class CPUMenu extends ScreenAdapter {

    Skin skin;
    Stage stage;
    final Main main;

    public CPUMenu(final Main main, final Skin skin) {
        this.main = main;
        this.skin = skin;
        stage = new Stage(new FitViewport(1000, 800));
        Gdx.input.setInputProcessor(stage);

        final VerticalGroup vg = new VerticalGroup();
        vg.setFillParent(true);
        vg.expand().space(20).pad(20);
        stage.addActor(vg);

        HorizontalGroup hgProceesNumSel = new HorizontalGroup();

        Label lProcessNumSel = new Label("Numero de procesos:", skin);
        hgProceesNumSel.addActor(lProcessNumSel);
        final SelectBox sbProcessNumSel = new SelectBox(skin);
        sbProcessNumSel.setItems(1, 2, 3, 4, 5, 6);
        sbProcessNumSel.setMaxListCount(4);

        hgProceesNumSel.addActor(sbProcessNumSel);
        vg.addActor(hgProceesNumSel);

        final VerticalGroup vgProcesos = new VerticalGroup().pad(20);

        Container cSeparador1 = new Container(new Image(skin.getDrawable("slider_back_hor")));
        cSeparador1.width(400).pad(10);
        Container cSeparador2 = new Container(new Image(skin.getDrawable("slider_back_hor")));
        cSeparador2.width(400).pad(10);
        Container cSeparador3 = new Container(new Image(skin.getDrawable("slider_back_hor")));
        cSeparador3.width(400).pad(10);
        Container cSeparador4 = new Container(new Image(skin.getDrawable("slider_back_hor")));
        cSeparador4.width(400).pad(10);
        Container cSeparador5 = new Container(new Image(skin.getDrawable("slider_back_hor")));
        cSeparador5.width(400).pad(10);

        final VerticalGroup vgProc1 = new VerticalGroup();
        vgProc1.addActor(new Label("Proceso 1:", skin, "dark"));

        HorizontalGroup hgProc1Listo = new HorizontalGroup();
        hgProc1Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto1 = new TextField("0", skin);
        hgProc1Listo.addActor(tflisto1);
        hgProc1Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc1.addActor(hgProc1Listo);

        HorizontalGroup hgProc1RafagaNum = new HorizontalGroup();
        hgProc1RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc1RafagaNum = new SelectBox(skin);
        sbProc1RafagaNum.setItems(1, 2, 3, 4);
        hgProc1RafagaNum.addActor(sbProc1RafagaNum);

        final HorizontalGroup hgProc1Raf1 = new HorizontalGroup();
        hgProc1Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc1Raf1 = new TextField("1", skin);
        hgProc1Raf1.addActor(tfProc1Raf1);
        hgProc1Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf2 = new HorizontalGroup();
        hgProc1Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc1Raf2 = new TextField("1", skin);
        hgProc1Raf2.addActor(tfProc1Raf2);
        hgProc1Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf3 = new HorizontalGroup();
        hgProc1Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc1Raf3 = new TextField("1", skin);
        hgProc1Raf3.addActor(tfProc1Raf3);
        hgProc1Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf4 = new HorizontalGroup();
        hgProc1Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc1Raf4 = new TextField("1", skin);
        hgProc1Raf4.addActor(tfProc1Raf4);
        hgProc1Raf4.addActor(new Label(" ms", skin, "dark"));
        sbProc1RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc1Raf1.remove();
                hgProc1Raf2.remove();
                hgProc1Raf3.remove();
                hgProc1Raf4.remove();
                switch (sbProc1RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc1.addActorAt(3, hgProc1Raf4);
                    case 2:
                        vgProc1.addActorAt(3, hgProc1Raf3);
                    case 1:
                        vgProc1.addActorAt(3, hgProc1Raf2);
                    case 0:
                        vgProc1.addActorAt(3, hgProc1Raf1);
                }
            }
        });
        vgProc1.addActor(hgProc1RafagaNum);
        vgProc1.addActor(hgProc1Raf1);
        vgProcesos.addActor(vgProc1);

        final VerticalGroup vgProc2 = new VerticalGroup();
        vgProc2.addActor(cSeparador1);
        vgProc2.addActor(new Label("Proceso 2:", skin, "dark"));

        HorizontalGroup hgProc2Listo = new HorizontalGroup();
        hgProc2Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto2 = new TextField("0", skin);
        hgProc2Listo.addActor(tflisto2);
        hgProc2Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc2.addActor(hgProc2Listo);

        HorizontalGroup hgProc2RafagaNum = new HorizontalGroup();
        hgProc2RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc2RafagaNum = new SelectBox(skin);
        sbProc2RafagaNum.setItems(1, 2, 3, 4);
        hgProc2RafagaNum.addActor(sbProc2RafagaNum);

        final HorizontalGroup hgProc2Raf1 = new HorizontalGroup();
        hgProc2Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc2Raf1 = new TextField("1", skin);
        hgProc2Raf1.addActor(tfProc2Raf1);
        hgProc2Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf2 = new HorizontalGroup();
        hgProc2Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc2Raf2 = new TextField("1", skin);
        hgProc2Raf2.addActor(tfProc2Raf2);
        hgProc2Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf3 = new HorizontalGroup();
        hgProc2Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc2Raf3 = new TextField("1", skin);
        hgProc2Raf3.addActor(tfProc2Raf3);
        hgProc2Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf4 = new HorizontalGroup();
        hgProc2Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc2Raf4 = new TextField("1", skin);
        hgProc2Raf4.addActor(tfProc2Raf4);
        hgProc2Raf4.addActor(new Label(" ms", skin, "dark"));
        sbProc2RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc2Raf1.remove();
                hgProc2Raf2.remove();
                hgProc2Raf3.remove();
                hgProc2Raf4.remove();
                switch (sbProc2RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc2.addActorAt(4, hgProc2Raf4);
                    case 2:
                        vgProc2.addActorAt(4, hgProc2Raf3);
                    case 1:
                        vgProc2.addActorAt(4, hgProc2Raf2);
                    case 0:
                        vgProc2.addActorAt(4, hgProc2Raf1);
                }
            }
        });
        vgProc2.addActor(hgProc2RafagaNum);
        vgProc2.addActor(hgProc2Raf1);

        final VerticalGroup vgProc3 = new VerticalGroup();
        vgProc3.addActor(cSeparador2);
        vgProc3.addActor(new Label("Proceso 3:", skin, "dark"));

        HorizontalGroup hgProc3Listo = new HorizontalGroup();
        hgProc3Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto3 = new TextField("0", skin);
        hgProc3Listo.addActor(tflisto3);
        hgProc3Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc3.addActor(hgProc3Listo);

        HorizontalGroup hgProc3RafagaNum = new HorizontalGroup();
        hgProc3RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc3RafagaNum = new SelectBox(skin);
        sbProc3RafagaNum.setItems(1, 2, 3, 4);
        hgProc3RafagaNum.addActor(sbProc3RafagaNum);

        final HorizontalGroup hgProc3Raf1 = new HorizontalGroup();
        hgProc3Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc3Raf1 = new TextField("1", skin);
        hgProc3Raf1.addActor(tfProc3Raf1);
        hgProc3Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf2 = new HorizontalGroup();
        hgProc3Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc3Raf2 = new TextField("1", skin);
        hgProc3Raf2.addActor(tfProc3Raf2);
        hgProc3Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf3 = new HorizontalGroup();
        hgProc3Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc3Raf3 = new TextField("1", skin);
        hgProc3Raf3.addActor(tfProc3Raf3);
        hgProc3Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf4 = new HorizontalGroup();
        hgProc3Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc3Raf4 = new TextField("1", skin);
        hgProc3Raf4.addActor(tfProc3Raf4);
        hgProc3Raf4.addActor(new Label(" ms", skin, "dark"));
        sbProc3RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc3Raf1.remove();
                hgProc3Raf2.remove();
                hgProc3Raf3.remove();
                hgProc3Raf4.remove();
                switch (sbProc3RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc3.addActorAt(4, hgProc3Raf4);
                    case 2:
                        vgProc3.addActorAt(4, hgProc3Raf3);
                    case 1:
                        vgProc3.addActorAt(4, hgProc3Raf2);
                    case 0:
                        vgProc3.addActorAt(4, hgProc3Raf1);
                }
            }
        });
        vgProc3.addActor(hgProc3RafagaNum);
        vgProc3.addActor(hgProc3Raf1);

        final VerticalGroup vgProc4 = new VerticalGroup();
        vgProc4.addActor(cSeparador3);
        vgProc4.addActor(new Label("Proceso 4:", skin, "dark"));

        HorizontalGroup hgProc4Listo = new HorizontalGroup();
        hgProc4Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto4 = new TextField("0", skin);
        hgProc4Listo.addActor(tflisto4);
        hgProc4Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc4.addActor(hgProc4Listo);

        HorizontalGroup hgProc4RafagaNum = new HorizontalGroup();
        hgProc4RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc4RafagaNum = new SelectBox(skin);
        sbProc4RafagaNum.setItems(1, 2, 3, 4);
        hgProc4RafagaNum.addActor(sbProc4RafagaNum);

        final HorizontalGroup hgProc4Raf1 = new HorizontalGroup();
        hgProc4Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc4Raf1 = new TextField("1", skin);
        hgProc4Raf1.addActor(tfProc4Raf1);
        hgProc4Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf2 = new HorizontalGroup();
        hgProc4Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc4Raf2 = new TextField("1", skin);
        hgProc4Raf2.addActor(tfProc4Raf2);
        hgProc4Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf3 = new HorizontalGroup();
        hgProc4Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc4Raf3 = new TextField("1", skin);
        hgProc4Raf3.addActor(tfProc4Raf3);
        hgProc4Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf4 = new HorizontalGroup();
        hgProc4Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc4Raf4 = new TextField("1", skin);
        hgProc4Raf4.addActor(tfProc4Raf4);
        hgProc4Raf4.addActor(new Label(" ms", skin, "dark"));
        sbProc4RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc4Raf1.remove();
                hgProc4Raf2.remove();
                hgProc4Raf3.remove();
                hgProc4Raf4.remove();
                switch (sbProc4RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc4.addActorAt(4, hgProc4Raf4);
                    case 2:
                        vgProc4.addActorAt(4, hgProc4Raf3);
                    case 1:
                        vgProc4.addActorAt(4, hgProc4Raf2);
                    case 0:
                        vgProc4.addActorAt(4, hgProc4Raf1);
                }
            }
        });
        vgProc4.addActor(hgProc4RafagaNum);
        vgProc4.addActor(hgProc4Raf1);

        final VerticalGroup vgProc5 = new VerticalGroup();
        vgProc5.addActor(cSeparador4);
        vgProc5.addActor(new Label("Proceso 5:", skin, "dark"));

        HorizontalGroup hgProc5Listo = new HorizontalGroup();
        hgProc5Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto5 = new TextField("0", skin);
        hgProc5Listo.addActor(tflisto5);
        hgProc5Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc5.addActor(hgProc5Listo);

        HorizontalGroup hgProc5RafagaNum = new HorizontalGroup();
        hgProc5RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc5RafagaNum = new SelectBox(skin);
        sbProc5RafagaNum.setItems(1, 2, 3, 4);
        hgProc5RafagaNum.addActor(sbProc5RafagaNum);

        final HorizontalGroup hgProc5Raf1 = new HorizontalGroup();
        hgProc5Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc5Raf1 = new TextField("1", skin);
        hgProc5Raf1.addActor(tfProc5Raf1);
        hgProc5Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc5Raf2 = new HorizontalGroup();
        hgProc5Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc5Raf2 = new TextField("1", skin);
        hgProc5Raf2.addActor(tfProc5Raf2);
        hgProc5Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc5Raf3 = new HorizontalGroup();
        hgProc5Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc5Raf3 = new TextField("1", skin);
        hgProc5Raf3.addActor(tfProc5Raf3);
        hgProc5Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc5Raf4 = new HorizontalGroup();
        hgProc5Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc5Raf4 = new TextField("1", skin);
        hgProc5Raf4.addActor(tfProc5Raf4);
        hgProc5Raf4.addActor(new Label(" ms", skin, "dark"));
        sbProc5RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc5Raf1.remove();
                hgProc5Raf2.remove();
                hgProc5Raf3.remove();
                hgProc5Raf4.remove();
                switch (sbProc5RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc5.addActorAt(4, hgProc5Raf4);
                    case 2:
                        vgProc5.addActorAt(4, hgProc5Raf3);
                    case 1:
                        vgProc5.addActorAt(4, hgProc5Raf2);
                    case 0:
                        vgProc5.addActorAt(4, hgProc5Raf1);
                }
            }
        });
        vgProc5.addActor(hgProc5RafagaNum);
        vgProc5.addActor(hgProc5Raf1);

        final VerticalGroup vgProc6 = new VerticalGroup();
        vgProc6.addActor(cSeparador5);
        vgProc6.addActor(new Label("Proceso 6:", skin, "dark"));

        HorizontalGroup hgProc6Listo = new HorizontalGroup();
        hgProc6Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto6 = new TextField("0", skin);
        hgProc6Listo.addActor(tflisto6);
        hgProc6Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc6.addActor(hgProc6Listo);

        HorizontalGroup hgProc6RafagaNum = new HorizontalGroup();
        hgProc6RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc6RafagaNum = new SelectBox(skin);
        sbProc6RafagaNum.setItems(1, 2, 3, 4);
        hgProc6RafagaNum.addActor(sbProc6RafagaNum);

        final HorizontalGroup hgProc6Raf1 = new HorizontalGroup();
        hgProc6Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc6Raf1 = new TextField("1", skin);
        hgProc6Raf1.addActor(tfProc6Raf1);
        hgProc6Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc6Raf2 = new HorizontalGroup();
        hgProc6Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc6Raf2 = new TextField("1", skin);
        hgProc6Raf2.addActor(tfProc6Raf2);
        hgProc6Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc6Raf3 = new HorizontalGroup();
        hgProc3Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc6Raf3 = new TextField("1", skin);
        hgProc6Raf3.addActor(tfProc6Raf3);
        hgProc6Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc6Raf4 = new HorizontalGroup();
        hgProc6Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc6Raf4 = new TextField("1", skin);
        hgProc6Raf4.addActor(tfProc6Raf4);
        hgProc6Raf4.addActor(new Label(" ms", skin, "dark"));
        sbProc6RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc6Raf1.remove();
                hgProc6Raf2.remove();
                hgProc6Raf3.remove();
                hgProc6Raf4.remove();
                switch (sbProc6RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc6.addActorAt(4, hgProc6Raf4);
                    case 2:
                        vgProc6.addActorAt(4, hgProc6Raf3);
                    case 1:
                        vgProc6.addActorAt(4, hgProc6Raf2);
                    case 0:
                        vgProc6.addActorAt(4, hgProc6Raf1);
                }
            }
        });
        vgProc6.addActor(hgProc6RafagaNum);
        vgProc6.addActor(hgProc6Raf1);

        final ScrollPane spProcess = new ScrollPane(vgProcesos, skin);

        Container container = new Container(spProcess);
        container.height(300).padBottom(10);
        vg.addActor(container);

        sbProcessNumSel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                vgProcesos.clearChildren();
                switch (sbProcessNumSel.getSelectedIndex()) {
                    case 5:
                        vgProcesos.addActorAt(0, vgProc6);
                    case 4:
                        vgProcesos.addActorAt(0, vgProc5);
                    case 3:
                        vgProcesos.addActorAt(0, vgProc4);
                    case 2:
                        vgProcesos.addActorAt(0, vgProc3);
                    case 1:
                        vgProcesos.addActorAt(0, vgProc2);
                    case 0:
                        vgProcesos.addActorAt(0, vgProc1);
                }
            }

        });

        HorizontalGroup hgConmutacion = new HorizontalGroup();
        hgConmutacion.addActor(new Label("Tiempo de Conmutacion: ", skin));
        final TextField tfConmutacion = new TextField("1", skin);
        hgConmutacion.addActor(tfConmutacion);
        hgConmutacion.addActor(new Label("ms", skin));
        vg.addActor(hgConmutacion);

        final VerticalGroup vgTipoAlgo = new VerticalGroup();
        vgTipoAlgo.space(20);
        vg.addActor(vgTipoAlgo);

        vgTipoAlgo.addActor(new Label("Algoritmo", skin));
        final SelectBox selectBox = new SelectBox(skin);
        selectBox.setItems("Round Robin", "Tiempo mas corto a continuacion");
        vgTipoAlgo.addActor(selectBox);
        final HorizontalGroup hgCuanto = new HorizontalGroup().pad(5);
        hgCuanto.addActor(new Label("Cuanto:  ", skin));
        final TextField tfCuanto = new TextField("1", skin);
        hgCuanto.addActor(tfCuanto);
        hgCuanto.addActor(new Label(" ms", skin));

        final Table tableTmcc = new Table().pad(5);
        tableTmcc.add(new Label("Valor de a:", skin));
        final TextField textA = new TextField("0", skin);
        tableTmcc.add(textA);
        tableTmcc.row().pad(10);
        tableTmcc.add(new Label("Estimacion inicial:", skin));
        final TextField textEstimacion = new TextField("0", skin);
        tableTmcc.add(textEstimacion);
        tableTmcc.add(new Label(" ms", skin));
        
        vgTipoAlgo.addActor(hgCuanto);

        HorizontalGroup hgButtons = new HorizontalGroup().space(60);
        final TextButton buttonAccept = new TextButton("Aceptar", skin);
        ImageButton ibBack = new ImageButton(skin.getDrawable("icon_back"));
        ibBack.getImage().setFillParent(true);
        ibBack.setFillParent(true);
        hgButtons.addActor(new Container(ibBack).size(60));
        buttonAccept.pad(15);
        hgButtons.addActor(buttonAccept);
        vg.addActor(hgButtons);

        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (selectBox.getSelected().equals("Round Robin")) {
                    vgTipoAlgo.removeActor(tableTmcc);
                    vgTipoAlgo.addActor(hgCuanto);
                } else {
                    vgTipoAlgo.removeActor(hgCuanto);
                    vgTipoAlgo.addActor(tableTmcc);
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
                ArrayList<Proceso> procesos = new ArrayList<Proceso>(4);
                switch (sbProcessNumSel.getSelectedIndex()) {
                    case 5:
                        int[] proc6Rafs = new int[4];
                        proc6Rafs[0] = Integer.parseInt(tfProc6Raf1.getText());
                        proc6Rafs[1] = Integer.parseInt(tfProc6Raf2.getText());
                        proc6Rafs[2] = Integer.parseInt(tfProc6Raf3.getText());
                        proc6Rafs[3] = Integer.parseInt(tfProc6Raf4.getText());
                        procesos.add(new Proceso(5, proc6Rafs, sbProc6RafagaNum.getSelectedIndex() + 1, Integer.parseInt(tflisto6.getText())));
                    case 4:
                        int[] proc5Rafs = new int[4];
                        proc5Rafs[0] = Integer.parseInt(tfProc5Raf1.getText());
                        proc5Rafs[1] = Integer.parseInt(tfProc5Raf2.getText());
                        proc5Rafs[2] = Integer.parseInt(tfProc5Raf3.getText());
                        proc5Rafs[3] = Integer.parseInt(tfProc5Raf4.getText());
                        procesos.add(new Proceso(4, proc5Rafs, sbProc5RafagaNum.getSelectedIndex() + 1, Integer.parseInt(tflisto5.getText())));
                    case 3:
                        int[] proc4Rafs = new int[4];
                        proc4Rafs[0] = Integer.parseInt(tfProc4Raf1.getText());
                        proc4Rafs[1] = Integer.parseInt(tfProc4Raf2.getText());
                        proc4Rafs[2] = Integer.parseInt(tfProc4Raf3.getText());
                        proc4Rafs[3] = Integer.parseInt(tfProc4Raf4.getText());
                        procesos.add(new Proceso(3, proc4Rafs, sbProc4RafagaNum.getSelectedIndex() + 1, Integer.parseInt(tflisto4.getText())));
                    case 2:
                        int[] proc3Rafs = new int[4];
                        proc3Rafs[0] = Integer.parseInt(tfProc3Raf1.getText());
                        proc3Rafs[1] = Integer.parseInt(tfProc3Raf2.getText());
                        proc3Rafs[2] = Integer.parseInt(tfProc3Raf3.getText());
                        proc3Rafs[3] = Integer.parseInt(tfProc3Raf4.getText());
                        procesos.add(new Proceso(2, proc3Rafs, sbProc3RafagaNum.getSelectedIndex() + 1, Integer.parseInt(tflisto3.getText())));
                    case 1:
                        int[] proc2Rafs = new int[4];
                        proc2Rafs[0] = Integer.parseInt(tfProc2Raf1.getText());
                        proc2Rafs[1] = Integer.parseInt(tfProc2Raf2.getText());
                        proc2Rafs[2] = Integer.parseInt(tfProc2Raf3.getText());
                        proc2Rafs[3] = Integer.parseInt(tfProc2Raf4.getText());
                        procesos.add(new Proceso(1, proc2Rafs, sbProc2RafagaNum.getSelectedIndex() + 1, Integer.parseInt(tflisto2.getText())));
                    case 0:
                        int[] proc1Rafs = new int[4];
                        proc1Rafs[0] = Integer.parseInt(tfProc1Raf1.getText());
                        proc1Rafs[1] = Integer.parseInt(tfProc1Raf2.getText());
                        proc1Rafs[2] = Integer.parseInt(tfProc1Raf3.getText());
                        proc1Rafs[3] = Integer.parseInt(tfProc1Raf4.getText());
                        procesos.add(new Proceso(0, proc1Rafs, sbProc1RafagaNum.getSelectedIndex() + 1, Integer.parseInt(tflisto1.getText())));
                }
                AlgoCPU algoCPU;
                if (selectBox.getSelected().equals("Round Robin")) {
                    algoCPU = new RoundRobin(procesos, Integer.parseInt(tfConmutacion.getText()),
                            skin, Integer.parseInt(tfCuanto.getText()));;
                } else {
                    algoCPU = new TMCaC(procesos, Integer.parseInt(tfConmutacion.getText()), skin,
                            Float.parseFloat(textA.getText()), Integer.parseInt(textEstimacion.getText()));
                }
                main.setScreen(new CPUScreen(main, skin, algoCPU));
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
