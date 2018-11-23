/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.grupo2.proyecto.cpu.algoritmos.Proceso;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Luis
 */
public class HistorialCpu {

    Skin skin;
    VerticalGroup vg;
    ArrayList<Container> actors;
    //int i;

    public HistorialCpu(Skin skin) {
        this.skin = skin;
        actors = new ArrayList<>();
        vg = new VerticalGroup().space(5);
        //i=1;
    }

    public void addRow(ArrayList<Proceso> procesos, int tiempo) {
        HorizontalGroup hg = new HorizontalGroup();
        hg.addActor(new Label(String.valueOf(tiempo)+" ms  ", skin, "small"));
        for (Proceso proceso : procesos) {
            actors.add(new Container(new TextButton("p"+(proceso.id+1)+":  "+Arrays.toString(proceso.getRafagas()), skin, "cajita").padBottom(20)).height(60).padBottom(15));
            hg.addActor(actors.get(actors.size() - 1));
        }
        vg.addActor(hg);
    }

    public void addRow(ArrayList<String> accesos, boolean R) {
        HorizontalGroup hg = new HorizontalGroup();
        for (String acceso : accesos) {
            actors.add(new Container(new TextButton(acceso, skin, "caja").padBottom(20)).height(60).padBottom(15));
            hg.addActor(actors.get(actors.size() - 1));
        }
        vg.addActor(hg);
    }

    public VerticalGroup getActor() {
        return this.vg;
    }
}
