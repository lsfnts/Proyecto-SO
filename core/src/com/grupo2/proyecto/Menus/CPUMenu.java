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
        sbProcessNumSel.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        sbProcessNumSel.setMaxListCount(5);

        hgProceesNumSel.addActor(sbProcessNumSel);
        vg.addActor(hgProceesNumSel);

        final VerticalGroup vgProcess = new VerticalGroup();
        vgProcess.pad(20);

        final Label lRafaga1 = new Label("Proceso 1: ", skin, "dark");
        final TextField tfRafaga1 = new TextField("1", skin);
        final Label lms1 = new Label("ms, ", skin, "dark");
        final Label lListoEn1 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn1 = new TextField("0", skin);
        final Label lmsL1 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess1 = new HorizontalGroup().space(5);
        hgProcess1.addActor(lRafaga1);
        hgProcess1.addActor(tfRafaga1);
        hgProcess1.addActor(lms1);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);
        vgProcess.addActor(hgProcess1);

        Label lRafaga2 = new Label("Proceso 2: ", skin, "dark");
        TextField tfRafaga2 = new TextField("1", skin);
        Label lms2 = new Label("ms", skin, "dark");
        final Label lListoEn2 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn2 = new TextField("0", skin);
        final Label lmsL2 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess2 = new HorizontalGroup().space(5);
        hgProcess2.addActor(lRafaga2);
        hgProcess2.addActor(tfRafaga2);
        hgProcess2.addActor(lms2);
        hgProcess1.addActor(lListoEn2);
        hgProcess1.addActor(tfListoEn2);
        hgProcess1.addActor(lmsL2);

        Label lRafaga3 = new Label("Proceso 3: ", skin, "dark");
        TextField tfRafaga3 = new TextField("1", skin);
        Label lms3 = new Label("ms", skin, "dark");
        final Label lListoEn3 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn3 = new TextField("0", skin);
        final Label lmsL3 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess3 = new HorizontalGroup();
        hgProcess3.addActor(lRafaga3);
        hgProcess3.addActor(tfRafaga3);
        hgProcess3.addActor(lms3);
        hgProcess1.addActor(lListoEn3);
        hgProcess1.addActor(tfListoEn3);
        hgProcess1.addActor(lmsL3);

        Label lRafaga4 = new Label("Proceso 4: ", skin, "dark");
        TextField tfRafaga4 = new TextField("1", skin);
        Label lms4 = new Label("ms", skin, "dark");
        final Label lListoEn4 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn4 = new TextField("0", skin);
        final Label lmsL4 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess4 = new HorizontalGroup();
        hgProcess4.addActor(lRafaga4);
        hgProcess4.addActor(tfRafaga4);
        hgProcess4.addActor(lms4);
        hgProcess1.addActor(lListoEn4);
        hgProcess1.addActor(tfListoEn4);
        hgProcess1.addActor(lmsL4);

        Label lRafaga5 = new Label("Proceso 5: ", skin, "dark");
        TextField tfRafaga5 = new TextField("1", skin);
        Label lms5 = new Label("ms", skin, "dark");
        final Label lListoEn5 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn5 = new TextField("0", skin);
        final Label lmsL5 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess5 = new HorizontalGroup();
        hgProcess5.addActor(lRafaga5);
        hgProcess5.addActor(tfRafaga5);
        hgProcess5.addActor(lms5);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga6 = new Label("Proceso 6: ", skin, "dark");
        TextField tfRafaga6 = new TextField("1", skin);
        Label lms6 = new Label("ms", skin, "dark");
        final Label lListoEn6 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn6 = new TextField("0", skin);
        final Label lmsL6 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess6 = new HorizontalGroup();
        hgProcess6.addActor(lRafaga6);
        hgProcess6.addActor(tfRafaga6);
        hgProcess6.addActor(lms6);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga7 = new Label("Proceso 7: ", skin, "dark");
        TextField tfRafaga7 = new TextField("1", skin);
        Label lms7 = new Label("ms", skin, "dark");
        final Label lListoEn7 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn7 = new TextField("0", skin);
        final Label lmsL7 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess7 = new HorizontalGroup();
        hgProcess7.addActor(lRafaga7);
        hgProcess7.addActor(tfRafaga7);
        hgProcess7.addActor(lms7);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga8 = new Label("Proceso 8: ", skin, "dark");
        TextField tfRafaga8 = new TextField("1", skin);
        Label lms8 = new Label("ms", skin, "dark");
        final Label lListoEn8 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn8 = new TextField("0", skin);
        final Label lmsL8 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess8 = new HorizontalGroup();
        hgProcess8.addActor(lRafaga8);
        hgProcess8.addActor(tfRafaga8);
        hgProcess8.addActor(lms8);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga9 = new Label("Proceso 9: ", skin, "dark");
        TextField tfRafaga9 = new TextField("1", skin);
        Label lms9 = new Label("ms", skin, "dark");
        final Label lListoEn9 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn9 = new TextField("0", skin);
        final Label lmsL9 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess9 = new HorizontalGroup();
        hgProcess9.addActor(lRafaga9);
        hgProcess9.addActor(tfRafaga9);
        hgProcess9.addActor(lms9);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga10 = new Label("Proceso 10: ", skin, "dark");
        TextField tfRafaga10 = new TextField("1", skin);
        Label lms10 = new Label("ms", skin, "dark");
        final Label lListoEn10 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn10 = new TextField("0", skin);
        final Label lmsL10 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess10 = new HorizontalGroup();
        hgProcess10.addActor(lRafaga10);
        hgProcess10.addActor(tfRafaga10);
        hgProcess10.addActor(lms10);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga11 = new Label("Proceso 11: ", skin, "dark");
        TextField tfRafaga11 = new TextField("1", skin);
        Label lms11 = new Label("ms", skin, "dark");
        final Label lListoEn11 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn11 = new TextField("0", skin);
        final Label lmsL11 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess11 = new HorizontalGroup();
        hgProcess11.addActor(lRafaga11);
        hgProcess11.addActor(tfRafaga11);
        hgProcess11.addActor(lms11);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga12 = new Label("Proceso 12: ", skin, "dark");
        TextField tfRafaga12 = new TextField("1", skin);
        Label lms12 = new Label("ms", skin, "dark");
        final Label lListoEn12 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn12 = new TextField("0", skin);
        final Label lmsL12 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess12 = new HorizontalGroup();
        hgProcess12.addActor(lRafaga12);
        hgProcess12.addActor(tfRafaga12);
        hgProcess12.addActor(lms12);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga13 = new Label("Proceso 13: ", skin, "dark");
        TextField tfRafaga13 = new TextField("1", skin);
        Label lms13 = new Label("ms", skin, "dark");
        final Label lListoEn13 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn13 = new TextField("0", skin);
        final Label lmsL13 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess13 = new HorizontalGroup();
        hgProcess13.addActor(lRafaga13);
        hgProcess13.addActor(tfRafaga13);
        hgProcess13.addActor(lms13);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga14 = new Label("Proceso 14: ", skin, "dark");
        TextField tfRafaga14 = new TextField("1", skin);
        Label lms14 = new Label("ms", skin, "dark");
        final Label lListoEn14 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn14 = new TextField("0", skin);
        final Label lmsL14 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess14 = new HorizontalGroup();
        hgProcess14.addActor(lRafaga14);
        hgProcess14.addActor(tfRafaga14);
        hgProcess14.addActor(lms14);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        Label lRafaga15 = new Label("Proceso 15: ", skin, "dark");
        TextField tfRafaga15 = new TextField("1", skin);
        Label lms15 = new Label("ms", skin, "dark");
        final Label lListoEn15 = new Label("Listo en: ", skin, "dark");
        final TextField tfListoEn15 = new TextField("0", skin);
        final Label lmsL15 = new Label("ms", skin, "dark");
        final HorizontalGroup hgProcess15 = new HorizontalGroup();
        hgProcess15.addActor(lRafaga15);
        hgProcess15.addActor(tfRafaga15);
        hgProcess15.addActor(lms15);
        hgProcess1.addActor(lListoEn1);
        hgProcess1.addActor(tfListoEn1);
        hgProcess1.addActor(lmsL1);

        final ScrollPane spProcess = new ScrollPane(vgProcess, skin);

        Container container = new Container(spProcess);
        container.height(200).padBottom(40);
        vg.addActor(container);

        sbProcessNumSel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                vgProcess.clearChildren();
                switch (sbProcessNumSel.getSelectedIndex() + 1) {
                    case 15:
                        vgProcess.addActorAt(0, hgProcess15);
                    case 14:
                        vgProcess.addActorAt(0, hgProcess14);
                    case 13:
                        vgProcess.addActorAt(0, hgProcess13);
                    case 12:
                        vgProcess.addActorAt(0, hgProcess12);
                    case 11:
                        vgProcess.addActorAt(0, hgProcess11);
                    case 10:
                        vgProcess.addActorAt(0, hgProcess10);
                    case 9:
                        vgProcess.addActorAt(0, hgProcess9);
                    case 8:
                        vgProcess.addActorAt(0, hgProcess8);
                    case 7:
                        vgProcess.addActorAt(0, hgProcess7);
                    case 6:
                        vgProcess.addActorAt(0, hgProcess6);
                    case 5:
                        vgProcess.addActorAt(0, hgProcess5);
                    case 4:
                        vgProcess.addActorAt(0, hgProcess4);
                    case 3:
                        vgProcess.addActorAt(0, hgProcess3);
                    case 2:
                        vgProcess.addActorAt(0, hgProcess2);
                    case 1:
                        vgProcess.addActorAt(0, hgProcess1);
                }
            }

        });

        final VerticalGroup vgTipoAlgo = new VerticalGroup();
        vgTipoAlgo.space(20);
        vg.addActor(vgTipoAlgo);

        final Label labelQuanto = new Label("Cuanto:", skin);
        final TextField textQuanto = new TextField("1", skin);
        final Label labelmsQ = new Label(" ms", skin);

        final Label labelA = new Label("Valor de a:", skin);
        final TextField textA = new TextField("1", skin);
        final Label labelEstimacion = new Label("Estimacion inicial:", skin);
        final TextField textEstimacion = new TextField("0", skin);
        final Label labelmsE = new Label(" ms", skin);

        final TextButton buttonAccept = new TextButton("Aceptar", skin);

        final SelectBox selectBox = new SelectBox(skin);
        selectBox.setItems("Round Robin", "Tiempo mas corto a continuacion");
        vgTipoAlgo.addActor(selectBox);
        final HorizontalGroup hgCuanto = new HorizontalGroup().pad(5);
        hgCuanto.addActor(labelQuanto);
        hgCuanto.addActor(textQuanto);
        hgCuanto.addActor(labelmsQ);

        final Table tTmcc = new Table().pad(5);
        tTmcc.add(labelA);
        tTmcc.add(textA);
        tTmcc.row().pad(10);
        tTmcc.add(labelEstimacion);
        tTmcc.add(textEstimacion);
        tTmcc.add(labelmsE);
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
