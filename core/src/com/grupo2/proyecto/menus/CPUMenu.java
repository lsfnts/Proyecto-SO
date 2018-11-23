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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.grupo2.proyecto.Main;
import com.grupo2.proyecto.cpu.algoritmos.Proceso;
import com.grupo2.proyecto.cpu.algoritmos.RoundRobin;
import com.grupo2.proyecto.cpu.algoritmos.TMCaC;
import com.grupo2.proyecto.cpu.CPUScreen;
import com.grupo2.proyecto.cpu.algoritmos.AlgoCPU;
import com.grupo2.proyecto.menus.util.json.JsonInfoCpu;
import com.grupo2.proyecto.menus.util.json.JsonInfoProceso;
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
        stage = new Stage(new FitViewport(1200, 800));
        Gdx.input.setInputProcessor(stage);

        final HorizontalGroup hg = new HorizontalGroup();
        hg.setFillParent(true);
        hg.expand().space(20).pad(20);
        final VerticalGroup vg = new VerticalGroup();
        vg.space(20);
        hg.addActor(vg);
        stage.addActor(hg);

        HorizontalGroup hgProceesNumSel = new HorizontalGroup();

        Label lProcessNumSel = new Label("Numero de procesos:", skin);
        hgProceesNumSel.addActor(lProcessNumSel);
        final SelectBox sbProcessNumSel = new SelectBox(skin);
        sbProcessNumSel.setItems(1, 2, 3, 4);
        vg.addActor(hgProceesNumSel);

        final VerticalGroup vgProcesos = new VerticalGroup().pad(20);

        final VerticalGroup vgProc1 = new VerticalGroup().space(10);
        vgProc1.addActor(new Label("Proceso 1:", skin, "dark"));

        HorizontalGroup hgProc1Listo = new HorizontalGroup();
        hgProc1Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto1 = new TextField("0", skin);
        hgProc1Listo.addActor(new Container(tflisto1).width(100));
        hgProc1Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc1.addActor(hgProc1Listo);

        HorizontalGroup hgProc1RafagaNum = new HorizontalGroup();
        hgProc1RafagaNum.addActor(new Label("rafagas: ", skin, "dark"));
        final SelectBox sbProc1RafagaNum = new SelectBox(skin);
        sbProc1RafagaNum.setItems(1, 2, 3, 4);
        hgProc1RafagaNum.addActor(sbProc1RafagaNum);

        final HorizontalGroup hgProc1Raf1 = new HorizontalGroup();
        hgProc1Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc1Raf1 = new TextField("1", skin);
        hgProc1Raf1.addActor(new Container(tfProc1Raf1).width(100));
        hgProc1Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf2 = new HorizontalGroup();
        hgProc1Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc1Raf2 = new TextField("1", skin);
        hgProc1Raf2.addActor(new Container(tfProc1Raf2).width(100));
        hgProc1Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf3 = new HorizontalGroup();
        hgProc1Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc1Raf3 = new TextField("1", skin);
        hgProc1Raf3.addActor(new Container(tfProc1Raf3).width(100));
        hgProc1Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf4 = new HorizontalGroup();
        hgProc1Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc1Raf4 = new TextField("1", skin);
        hgProc1Raf4.addActor(new Container(tfProc1Raf4).width(100));
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

        final VerticalGroup vgProc2 = new VerticalGroup().space(10);
        vgProc2.addActor(new Container(new Image(skin.getDrawable("slider_back_hor")))
                .width(400).padBottom(30).padTop(30));
        vgProc2.addActor(new Label("Proceso 2:", skin, "dark"));

        HorizontalGroup hgProc2Listo = new HorizontalGroup();
        hgProc2Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto2 = new TextField("0", skin);
        hgProc2Listo.addActor(new Container(tflisto2).width(100));
        hgProc2Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc2.addActor(hgProc2Listo);

        HorizontalGroup hgProc2RafagaNum = new HorizontalGroup();
        hgProc2RafagaNum.addActor(new Label("rafagas: ", skin, "dark"));
        final SelectBox sbProc2RafagaNum = new SelectBox(skin);
        sbProc2RafagaNum.setItems(1, 2, 3, 4);
        hgProc2RafagaNum.addActor(sbProc2RafagaNum);

        final HorizontalGroup hgProc2Raf1 = new HorizontalGroup();
        hgProc2Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc2Raf1 = new TextField("1", skin);
        hgProc2Raf1.addActor(new Container(tfProc2Raf1).width(100));
        hgProc2Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf2 = new HorizontalGroup();
        hgProc2Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc2Raf2 = new TextField("1", skin);
        hgProc2Raf2.addActor(new Container(tfProc2Raf2).width(100));
        hgProc2Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf3 = new HorizontalGroup();
        hgProc2Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc2Raf3 = new TextField("1", skin);
        hgProc2Raf3.addActor(new Container(tfProc2Raf3).width(100));
        hgProc2Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf4 = new HorizontalGroup();
        hgProc2Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc2Raf4 = new TextField("1", skin);
        hgProc2Raf4.addActor(new Container(tfProc2Raf4).width(100));
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

        final VerticalGroup vgProc3 = new VerticalGroup().space(10);
        vgProc3.addActor(new Container(new Image(skin.getDrawable("slider_back_hor")))
                .width(400).padBottom(30).padTop(30));
        vgProc3.addActor(new Label("Proceso 3:", skin, "dark"));

        HorizontalGroup hgProc3Listo = new HorizontalGroup();
        hgProc3Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto3 = new TextField("0", skin);
        hgProc3Listo.addActor(new Container(tflisto3).width(100));
        hgProc3Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc3.addActor(hgProc3Listo);

        HorizontalGroup hgProc3RafagaNum = new HorizontalGroup();
        hgProc3RafagaNum.addActor(new Label("rafagas: ", skin, "dark"));
        final SelectBox sbProc3RafagaNum = new SelectBox(skin);
        sbProc3RafagaNum.setItems(1, 2, 3, 4);
        hgProc3RafagaNum.addActor(sbProc3RafagaNum);

        final HorizontalGroup hgProc3Raf1 = new HorizontalGroup();
        hgProc3Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc3Raf1 = new TextField("1", skin);
        hgProc3Raf1.addActor(new Container(tfProc3Raf1).width(100));
        hgProc3Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf2 = new HorizontalGroup();
        hgProc3Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc3Raf2 = new TextField("1", skin);
        hgProc3Raf2.addActor(new Container(tfProc3Raf2).width(100));
        hgProc3Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf3 = new HorizontalGroup();
        hgProc3Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc3Raf3 = new TextField("1", skin);
        hgProc3Raf3.addActor(new Container(tfProc3Raf3).width(100));
        hgProc3Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf4 = new HorizontalGroup();
        hgProc3Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc3Raf4 = new TextField("1", skin);
        hgProc3Raf4.addActor(new Container(tfProc3Raf4).width(100));
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

        final VerticalGroup vgProc4 = new VerticalGroup().space(10);
        vgProc4.addActor(new Container(new Image(skin.getDrawable("slider_back_hor")))
                .width(400).padBottom(30).padTop(30));
        vgProc4.addActor(new Label("Proceso 4:", skin, "dark"));

        HorizontalGroup hgProc4Listo = new HorizontalGroup();
        hgProc4Listo.addActor(new Label("Listo en", skin, "dark"));
        final TextField tflisto4 = new TextField("0", skin);
        hgProc4Listo.addActor(new Container(tflisto4).width(100));
        hgProc4Listo.addActor(new Label(" ms", skin, "dark"));
        vgProc4.addActor(hgProc4Listo);

        HorizontalGroup hgProc4RafagaNum = new HorizontalGroup();
        hgProc4RafagaNum.addActor(new Label("rafagas: ", skin, "dark"));
        final SelectBox sbProc4RafagaNum = new SelectBox(skin);
        sbProc4RafagaNum.setItems(1, 2, 3, 4);
        hgProc4RafagaNum.addActor(sbProc4RafagaNum);

        final HorizontalGroup hgProc4Raf1 = new HorizontalGroup();
        hgProc4Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        final TextField tfProc4Raf1 = new TextField("1", skin);
        hgProc4Raf1.addActor(new Container(tfProc4Raf1).width(100));
        hgProc4Raf1.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf2 = new HorizontalGroup();
        hgProc4Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        final TextField tfProc4Raf2 = new TextField("1", skin);
        hgProc4Raf2.addActor(new Container(tfProc4Raf2).width(100));
        hgProc4Raf2.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf3 = new HorizontalGroup();
        hgProc4Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        final TextField tfProc4Raf3 = new TextField("1", skin);
        hgProc4Raf3.addActor(new Container(tfProc4Raf3).width(100));
        hgProc4Raf3.addActor(new Label(" ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf4 = new HorizontalGroup();
        hgProc4Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        final TextField tfProc4Raf4 = new TextField("1", skin);
        hgProc4Raf4.addActor(new Container(tfProc4Raf4).width(100));
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

        

        

        final ScrollPane spProcess = new ScrollPane(vgProcesos, skin);

        Container container = new Container(spProcess);
        container.height(300).padBottom(10);
        hg.addActor(container);

        sbProcessNumSel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                vgProcesos.clearChildren();
                switch (sbProcessNumSel.getSelectedIndex()) {
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
        hgConmutacion.addActor(new Label(" ms", skin));
        hgProceesNumSel.addActor(sbProcessNumSel);
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

        HorizontalGroup hgButtons = new HorizontalGroup().space(40);
        final TextButton buttonAccept = new TextButton("Aceptar", skin);
        ImageButton ibBack = new ImageButton(skin.getDrawable("icon_back"));
        ibBack.getImage().setFillParent(true);
        ibBack.setFillParent(true);
        hgButtons.addActor(new Container(ibBack).size(60));
        CheckBox cbLeer = new CheckBox(" Leer de archivo", skin);
        hgButtons.addActor(cbLeer);
        buttonAccept.pad(15);
        hgButtons.addActor(buttonAccept);
        vg.addActor(hgButtons);

        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (selectBox.getSelectedIndex() == 0) {
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
                AlgoCPU algoCPU;
                ArrayList<Proceso> procesos = new ArrayList<>(4);
                if (cbLeer.isChecked()) {
                    Json json = new Json();
                    json.setIgnoreUnknownFields(true);
                    json.addClassTag("proceso", JsonInfoProceso.class);
                    JsonInfoCpu infoCpu = json.fromJson(JsonInfoCpu.class, Gdx.files.local("CPU.json"));
                    for (int i = infoCpu.Procesos.length-1; i >= 0; i--) {
                        procesos.add(new Proceso(i, infoCpu.Procesos[i].Rafagas, infoCpu.Procesos[i].Rafagas.length, infoCpu.Procesos[i].ListoEn));
                   }
                    
                    if(infoCpu.Algoritmo == 0){
                        algoCPU = new RoundRobin(procesos, infoCpu.TiempoConmutacion,
                                skin, infoCpu.Cuanto);
                    } else {
                        algoCPU = new TMCaC(procesos, infoCpu.TiempoConmutacion, skin,
                                infoCpu.a, infoCpu.EstimacionInicial);
                    }
                } else {
                    switch (sbProcessNumSel.getSelectedIndex()) {
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

                    if (selectBox.getSelected().equals("Round Robin")) {
                        algoCPU = new RoundRobin(procesos, Integer.parseInt(tfConmutacion.getText()),
                                skin, Integer.parseInt(tfCuanto.getText()));;
                    } else {
                        algoCPU = new TMCaC(procesos, Integer.parseInt(tfConmutacion.getText()), skin,
                                Float.parseFloat(textA.getText()), Integer.parseInt(textEstimacion.getText()));
                    }
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
