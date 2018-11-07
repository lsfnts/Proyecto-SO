/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.Menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
import com.grupo2.proyecto.cpu.CPUScreen;

/**
 *
 * @author Luis
 */
public class CPUMenu extends ScreenAdapter {

    Skin skin;
    Stage stage;
    final Main main;
    ChangeListener clRafagaNum;

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

        final VerticalGroup vgProcesos = new VerticalGroup();
        vgProcesos.pad(20);

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

        HorizontalGroup hgProc1RafagaNum = new HorizontalGroup();
        hgProc1RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc1RafagaNum = new SelectBox(skin);
        sbProc1RafagaNum.setItems(1, 2, 3, 4);
        hgProc1RafagaNum.addActor(sbProc1RafagaNum);

        final HorizontalGroup hgProc1Raf1 = new HorizontalGroup();
        hgProc1Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        TextField tfProc1Raf1 = new TextField("", skin);
        hgProc1Raf1.addActor(tfProc1Raf1);
        hgProc1Raf1.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf2 = new HorizontalGroup();
        hgProc1Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        TextField tfProc1Raf2 = new TextField("", skin);
        hgProc1Raf2.addActor(tfProc1Raf2);
        hgProc1Raf2.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf3 = new HorizontalGroup();
        hgProc1Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        TextField tfProc1Raf3 = new TextField("", skin);
        hgProc1Raf3.addActor(tfProc1Raf3);
        hgProc1Raf3.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc1Raf4 = new HorizontalGroup();
        hgProc1Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        TextField tfProc1Raf4 = new TextField("", skin);
        hgProc1Raf4.addActor(tfProc1Raf4);
        hgProc1Raf4.addActor(new Label("ms", skin, "dark"));
        sbProc1RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc1Raf1.remove();
                hgProc1Raf2.remove();
                hgProc1Raf3.remove();
                hgProc1Raf4.remove();
                switch (sbProc1RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc1.addActorAt(2, hgProc1Raf4);
                    case 2:
                        vgProc1.addActorAt(2, hgProc1Raf3);
                    case 1:
                        vgProc1.addActorAt(2, hgProc1Raf2);
                    case 0:
                        vgProc1.addActorAt(2, hgProc1Raf1);
                }
            }
        });
        vgProc1.addActor(hgProc1RafagaNum);
        vgProc1.addActor(hgProc1Raf1);
        vgProcesos.addActor(vgProc1);

        final VerticalGroup vgProc2 = new VerticalGroup();
        vgProc2.addActor(cSeparador1);
        vgProc2.addActor(new Label("Proceso 2:", skin, "dark"));

        HorizontalGroup hgProc2RafagaNum = new HorizontalGroup();
        hgProc2RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc2RafagaNum = new SelectBox(skin);
        sbProc2RafagaNum.setItems(1, 2, 3, 4);
        hgProc2RafagaNum.addActor(sbProc2RafagaNum);

        final HorizontalGroup hgProc2Raf1 = new HorizontalGroup();
        hgProc2Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        TextField tfProc2Raf1 = new TextField("", skin);
        hgProc2Raf1.addActor(tfProc2Raf1);
        hgProc2Raf1.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf2 = new HorizontalGroup();
        hgProc2Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        TextField tfProc2Raf2 = new TextField("", skin);
        hgProc2Raf2.addActor(tfProc2Raf2);
        hgProc2Raf2.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf3 = new HorizontalGroup();
        hgProc2Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        TextField tfProc2Raf3 = new TextField("", skin);
        hgProc2Raf3.addActor(tfProc2Raf3);
        hgProc2Raf3.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc2Raf4 = new HorizontalGroup();
        hgProc2Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        TextField tfProc2Raf4 = new TextField("", skin);
        hgProc2Raf4.addActor(tfProc2Raf4);
        hgProc2Raf4.addActor(new Label("ms", skin, "dark"));
        sbProc2RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc2Raf1.remove();
                hgProc2Raf2.remove();
                hgProc2Raf3.remove();
                hgProc2Raf4.remove();
                switch (sbProc1RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc2.addActorAt(3, hgProc2Raf4);
                    case 2:
                        vgProc2.addActorAt(3, hgProc2Raf3);
                    case 1:
                        vgProc2.addActorAt(3, hgProc2Raf2);
                    case 0:
                        vgProc2.addActorAt(3, hgProc2Raf1);
                }
            }
        });
        vgProc2.addActor(hgProc2RafagaNum);
        vgProc2.addActor(hgProc2Raf1);

        final VerticalGroup vgProc3 = new VerticalGroup();
        vgProc3.addActor(cSeparador2);
        vgProc3.addActor(new Label("Proceso 3:", skin, "dark"));

        HorizontalGroup hgProc3RafagaNum = new HorizontalGroup();
        hgProc3RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc3RafagaNum = new SelectBox(skin);
        sbProc3RafagaNum.setItems(1, 2, 3, 4);
        hgProc3RafagaNum.addActor(sbProc3RafagaNum);

        final HorizontalGroup hgProc3Raf1 = new HorizontalGroup();
        hgProc3Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        TextField tfProc3Raf1 = new TextField("", skin);
        hgProc3Raf1.addActor(tfProc3Raf1);
        hgProc3Raf1.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf2 = new HorizontalGroup();
        hgProc3Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        TextField tfProc3Raf2 = new TextField("", skin);
        hgProc3Raf2.addActor(tfProc3Raf2);
        hgProc3Raf2.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf3 = new HorizontalGroup();
        hgProc3Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        TextField tfProc3Raf3 = new TextField("", skin);
        hgProc3Raf3.addActor(tfProc3Raf3);
        hgProc3Raf3.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc3Raf4 = new HorizontalGroup();
        hgProc3Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        TextField tfProc3Raf4 = new TextField("", skin);
        hgProc3Raf4.addActor(tfProc3Raf4);
        hgProc3Raf4.addActor(new Label("ms", skin, "dark"));
        sbProc3RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc3Raf1.remove();
                hgProc3Raf2.remove();
                hgProc3Raf3.remove();
                hgProc3Raf4.remove();
                switch (sbProc1RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc3.addActorAt(3, hgProc3Raf4);
                    case 2:
                        vgProc3.addActorAt(3, hgProc3Raf3);
                    case 1:
                        vgProc3.addActorAt(3, hgProc3Raf2);
                    case 0:
                        vgProc3.addActorAt(3, hgProc3Raf1);
                }
            }
        });
        vgProc3.addActor(hgProc3RafagaNum);
        vgProc3.addActor(hgProc3Raf1);

        final VerticalGroup vgProc4 = new VerticalGroup();
        vgProc4.addActor(cSeparador3);
        vgProc4.addActor(new Label("Proceso 4:", skin, "dark"));

        HorizontalGroup hgProc4RafagaNum = new HorizontalGroup();
        hgProc4RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc4RafagaNum = new SelectBox(skin);
        sbProc4RafagaNum.setItems(1, 2, 3, 4);
        hgProc4RafagaNum.addActor(sbProc4RafagaNum);

        final HorizontalGroup hgProc4Raf1 = new HorizontalGroup();
        hgProc4Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        TextField tfProc4Raf1 = new TextField("", skin);
        hgProc4Raf1.addActor(tfProc4Raf1);
        hgProc4Raf1.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf2 = new HorizontalGroup();
        hgProc4Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        TextField tfProc4Raf2 = new TextField("", skin);
        hgProc4Raf2.addActor(tfProc4Raf2);
        hgProc4Raf2.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf3 = new HorizontalGroup();
        hgProc4Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        TextField tfProc4Raf3 = new TextField("", skin);
        hgProc4Raf3.addActor(tfProc4Raf3);
        hgProc4Raf3.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc4Raf4 = new HorizontalGroup();
        hgProc4Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        TextField tfProc4Raf4 = new TextField("", skin);
        hgProc4Raf4.addActor(tfProc4Raf4);
        hgProc4Raf4.addActor(new Label("ms", skin, "dark"));
        sbProc4RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc4Raf1.remove();
                hgProc4Raf2.remove();
                hgProc4Raf3.remove();
                hgProc4Raf4.remove();
                switch (sbProc1RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc4.addActorAt(3, hgProc4Raf4);
                    case 2:
                        vgProc4.addActorAt(3, hgProc4Raf3);
                    case 1:
                        vgProc4.addActorAt(3, hgProc4Raf2);
                    case 0:
                        vgProc4.addActorAt(3, hgProc4Raf1);
                }
            }
        });
        vgProc4.addActor(hgProc4RafagaNum);
        vgProc4.addActor(hgProc4Raf1);

        final VerticalGroup vgProc5 = new VerticalGroup();
        vgProc5.addActor(cSeparador4);
        vgProc5.addActor(new Label("Proceso 5:", skin, "dark"));

        HorizontalGroup hgProc5RafagaNum = new HorizontalGroup();
        hgProc5RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc5RafagaNum = new SelectBox(skin);
        sbProc5RafagaNum.setItems(1, 2, 3, 4);
        hgProc5RafagaNum.addActor(sbProc5RafagaNum);

        final HorizontalGroup hgProc5Raf1 = new HorizontalGroup();
        hgProc5Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        TextField tfProc5Raf1 = new TextField("", skin);
        hgProc5Raf1.addActor(tfProc5Raf1);
        hgProc5Raf1.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc5Raf2 = new HorizontalGroup();
        hgProc5Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        TextField tfProc5Raf2 = new TextField("", skin);
        hgProc5Raf2.addActor(tfProc5Raf2);
        hgProc5Raf2.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc5Raf3 = new HorizontalGroup();
        hgProc5Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        TextField tfProc5Raf3 = new TextField("", skin);
        hgProc5Raf3.addActor(tfProc5Raf3);
        hgProc5Raf3.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc5Raf4 = new HorizontalGroup();
        hgProc5Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        TextField tfProc5Raf4 = new TextField("", skin);
        hgProc5Raf4.addActor(tfProc5Raf4);
        hgProc5Raf4.addActor(new Label("ms", skin, "dark"));
        sbProc5RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc5Raf1.remove();
                hgProc5Raf2.remove();
                hgProc5Raf3.remove();
                hgProc5Raf4.remove();
                switch (sbProc1RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc5.addActorAt(3, hgProc5Raf4);
                    case 2:
                        vgProc5.addActorAt(3, hgProc5Raf3);
                    case 1:
                        vgProc5.addActorAt(3, hgProc5Raf2);
                    case 0:
                        vgProc5.addActorAt(3, hgProc5Raf1);
                }
            }
        });
        vgProc5.addActor(hgProc5RafagaNum);
        vgProc5.addActor(hgProc5Raf1);

        final VerticalGroup vgProc6 = new VerticalGroup();
        vgProc6.addActor(cSeparador5);
        vgProc6.addActor(new Label("Proceso 6:", skin, "dark"));

        HorizontalGroup hgProc6RafagaNum = new HorizontalGroup();
        hgProc6RafagaNum.addActor(new Label("Cantidad de rafagas: ", skin, "dark"));
        final SelectBox sbProc6RafagaNum = new SelectBox(skin);
        sbProc6RafagaNum.setItems(1, 2, 3, 4);
        hgProc6RafagaNum.addActor(sbProc6RafagaNum);

        final HorizontalGroup hgProc6Raf1 = new HorizontalGroup();
        hgProc6Raf1.addActor(new Label("Rafaga 1 : ", skin, "dark"));
        TextField tfProc6Raf1 = new TextField("", skin);
        hgProc6Raf1.addActor(tfProc6Raf1);
        hgProc6Raf1.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc6Raf2 = new HorizontalGroup();
        hgProc6Raf2.addActor(new Label("Rafaga 2: ", skin, "dark"));
        TextField tfProc6Raf2 = new TextField("", skin);
        hgProc6Raf2.addActor(tfProc6Raf2);
        hgProc6Raf2.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc6Raf3 = new HorizontalGroup();
        hgProc3Raf3.addActor(new Label("Rafaga 3: ", skin, "dark"));
        TextField tfProc6Raf3 = new TextField("", skin);
        hgProc6Raf3.addActor(tfProc6Raf3);
        hgProc6Raf3.addActor(new Label("ms", skin, "dark"));

        final HorizontalGroup hgProc6Raf4 = new HorizontalGroup();
        hgProc6Raf4.addActor(new Label("Rafaga 4: ", skin, "dark"));
        TextField tfProc6Raf4 = new TextField("", skin);
        hgProc6Raf4.addActor(tfProc6Raf4);
        hgProc6Raf4.addActor(new Label("ms", skin, "dark"));
        sbProc6RafagaNum.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                hgProc6Raf1.remove();
                hgProc6Raf2.remove();
                hgProc6Raf3.remove();
                hgProc6Raf4.remove();
                switch (sbProc1RafagaNum.getSelectedIndex()) {
                    case 3:
                        vgProc6.addActorAt(3, hgProc6Raf4);
                    case 2:
                        vgProc6.addActorAt(3, hgProc6Raf3);
                    case 1:
                        vgProc6.addActorAt(3, hgProc6Raf2);
                    case 0:
                        vgProc6.addActorAt(3, hgProc6Raf1);
                }
            }
        });
        vgProc6.addActor(hgProc6RafagaNum);
        vgProc6.addActor(hgProc6Raf1);

        final ScrollPane spProcess = new ScrollPane(vgProcesos, skin);

        Container container = new Container(spProcess);
        container.height(300).padBottom(20);
        vg.addActor(container);

        sbProcessNumSel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                vgProcesos.clearChildren();
                switch (sbProcessNumSel.getSelectedIndex() + 1) {
                    case 6:
                        vgProcesos.addActorAt(0, vgProc6);
                    case 5:
                        vgProcesos.addActorAt(0, vgProc5);
                    case 4:
                        vgProcesos.addActorAt(0, vgProc4);
                    case 3:
                        vgProcesos.addActorAt(0, vgProc3);
                    case 2:
                        vgProcesos.addActorAt(0, vgProc2);
                    case 1:
                        vgProcesos.addActorAt(0, vgProc1);
                }
            }

        });

        final VerticalGroup vgTipoAlgo = new VerticalGroup();
        vgTipoAlgo.space(20);
        vg.addActor(vgTipoAlgo);

        final TextButton buttonAccept = new TextButton("Aceptar", skin);

        vgTipoAlgo.addActor(new Label("Algoritmo", skin));
        final SelectBox selectBox = new SelectBox(skin);
        selectBox.setItems("Round Robin", "Tiempo mas corto a continuacion");
        vgTipoAlgo.addActor(selectBox);
        final HorizontalGroup hgCuanto = new HorizontalGroup().pad(5);
        hgCuanto.addActor(new Label("Cuanto:", skin));
        final TextField textQuanto = new TextField("1", skin);
        hgCuanto.addActor(textQuanto);
        hgCuanto.addActor(new Label(" ms", skin));

        final Table tTmcc = new Table().pad(5);
        tTmcc.add(new Label("Valor de a:", skin));
        final TextField textA = new TextField("1", skin);
        tTmcc.add(textA);
        tTmcc.row().pad(10);
        tTmcc.add(new Label("Estimacion inicial:", skin));
        final TextField textEstimacion = new TextField("0", skin);
        tTmcc.add(textEstimacion);
        tTmcc.add(new Label(" ms", skin));
        vgTipoAlgo.addActor(hgCuanto);

        vg.addActor(buttonAccept);
        selectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (selectBox.getSelected().equals("Round Robin")) {
                    vgTipoAlgo.removeActor(tTmcc);
                    vgTipoAlgo.addActor(hgCuanto);
                } else {
                    vgTipoAlgo.removeActor(hgCuanto);
                    vgTipoAlgo.addActor(tTmcc);
                }
            }
        });

        buttonAccept.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                //sbProcessNumSel.getSelectedIndex() + 1;
                main.setScreen(new CPUScreen(main, skin));
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
