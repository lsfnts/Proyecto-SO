/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.cpu.util;

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
public class ActorCPU extends Container<Actor>{
    Stack stack;
    Skin skin;
    VerticalGroup vg;
    Image back = new Image(new NinePatch(new Texture(Gdx.files.internal("metalPanel_green.png")),12,12,12,12));
    Image active = new Image(new NinePatch(new Texture(Gdx.files.internal("glassPanel_corners.png")),10,10,10,10));

    public ActorCPU(Skin skin) {
        this.skin = skin;
        stack = new Stack();
        stack.add(new Container(back).size(240, 200));
        vg = new VerticalGroup();
        vg.setFillParent(true);
        vg.pad(20);
        stack.add(vg.top());
        stack.add(active);
        
        vg.addActor(new Label("1", skin,"dark"));
        HorizontalGroup hgTRespuesta = new HorizontalGroup();
        hgTRespuesta.addActor(new Label("T. de respuesta: ", skin, "small"));
        vg.addActor(hgTRespuesta);
        
        this.width(260);
        this.height(220);
        this.setActor(stack);
    }
    
}
