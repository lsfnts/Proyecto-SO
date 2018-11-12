/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.menus.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

/**
 *
 * @author Luis
 */
public class ActorDisAcInfo extends Container<Actor> {

    TextButton tbEliminar;
    VerticalGroup vg;
    //Label lNum;

    public ActorDisAcInfo(Skin skin, int n, TextButton tbEliminar, int i) {
        this.tbEliminar = tbEliminar;
        //lNum = new Label("# "+i, skin, "dark");
        vg = new VerticalGroup().space(10).pad(10);
        HorizontalGroup hg = new HorizontalGroup();
        //hg.addActor(lNum);
        hg.addActor(new Label(" Cil. " + n + " ", skin, "dark"));
        hg.addActor(tbEliminar);
        vg.addActor(hg);
        vg.addActor(new Container(new Image(skin.getDrawable("slider_back_hor"))).size(200, 10));
        this.setActor(vg);
    }

    public TextButton getTbEliminar() {
        return tbEliminar;
    }

}
