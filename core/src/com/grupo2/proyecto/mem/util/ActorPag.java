/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.mem.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

/**
 *
 * @author Luis
 */
public class ActorPag extends Container<Actor> {

    Stack stack;
    Skin skin;
    VerticalGroup vg;
    Container insertando = new Container(new Image(new NinePatch(new Texture(Gdx.files.internal("overlayMem.png")), 17, 17, 17, 17))).size(260, 160).padTop(42);
    Container leyendo = new Container(new Image(new NinePatch(new Texture(Gdx.files.internal("overlayMem.png")), 17, 17, 17, 17))).size(260, 44).padBottom(74);
    Container cambioR = new Container(new Image(new NinePatch(new Texture(Gdx.files.internal("overlayMem.png")), 17, 17, 17, 17))).size(260, 54).padTop(148);
    Label laciertos;
    Label lfallos;
    Label lR;
    Label lC;

    //info de pagina
    public int num;
    int aciertos;
    int fallos;
    public boolean r;
    public int c;

    boolean nfu;

    public ActorPag(Skin skin, int num) {
        this.skin = skin;
        this.num = num;

        stack = new Stack();
        stack.add(new Container(new Image(skin.getDrawable("button_06"))).size(260, 160).padTop(42));
        vg = new VerticalGroup();
        vg.setFillParent(true);
        vg.padTop(30);

        vg.addActor(new Label(String.valueOf(num), skin, "dark"));
        laciertos = new Label("aciertos:  0", skin, "small");
        vg.addActor(laciertos);
        lfallos = new Label("fallos:  0", skin, "small");
        vg.addActor(lfallos);
        stack.add(vg);

        this.width(260);
        this.height(160);
        this.setActor(stack);
    }

    public ActorPag(Skin skin, int num, boolean olvidar) {
        this.skin = skin;
        this.num = num;
        this.nfu = true;

        stack = new Stack();
        stack.add(new Container(new Image(skin.getDrawable("button_06"))).size(260, 160).padTop(42));
        vg = new VerticalGroup();
        vg.setFillParent(true);
        vg.padTop(30);

        vg.addActor(new Label(String.valueOf(num), skin, "dark"));
        laciertos = new Label("aciertos:  0", skin, "small");
        vg.addActor(laciertos);
        lfallos = new Label("fallos:  0", skin, "small");
        vg.addActor(lfallos);
        HorizontalGroup hg = new HorizontalGroup();

        lR = new Label("R:1", skin, "small");
        if (olvidar) {
            lC = new Label("C= 0", skin, "small");
        } else {
            lC = new Label("C=  0", skin, "small");
        }
        hg.addActor(lR);
        hg.addActor(lC);
        vg.addActor(hg);

        stack.add(vg);

        this.width(260);
        this.height(160);
        this.setActor(stack);
    }

    public ActorPag() {
        this.setActor(new Container());
    }

    public void acierto() {
        r = true;
        aciertos += 1;
        laciertos.setText("aciertos:  " + aciertos);
        if (nfu) {
            lR.setText("R:1");
        }
        stack.add(leyendo);
    }

    public void fallo() {
        r = true;
        fallos += 1;
        lfallos.setText("fallos:  " + fallos);
        if (nfu) {
            lR.setText("R:1");
        }
        stack.add(insertando);
    }

    public void sumarC(boolean olvidar) {
        stack.add(cambioR);
        if (olvidar) {
            c = 128 + (c / 2);
            lC.setText("C= " + Integer.toBinaryString(c));
            r = false;
            lR.setText("R:0");
        } else {
            c += 1;
            lC.setText("C= " + c);
            r = false;
            lR.setText("R:0");
        }
    }
    
    public void apagar() {
        insertando.remove();
        leyendo.remove();
        cambioR.remove();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null && obj instanceof ActorPag) {
            ActorPag ap = (ActorPag) obj;
            return this.num == ap.num;
        } else if ((int) obj == num) {
            return true;
        } else {
            return false;
        }
    }

}
