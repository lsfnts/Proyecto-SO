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
import com.grupo2.proyecto.cpu.Algoritmos.Proceso;

/**
 *
 * @author Luis
 */
public class ActorProc extends Container<Actor> {

    Stack stack;
    Skin skin;
    VerticalGroup vg;
    Image active = new Image(new NinePatch(new Texture(Gdx.files.internal("glassPanel_corners.png")), 10, 10, 10, 10));
    Label lRespuesta;
    Label lEspera;
    Label lRetorno;
    Label lRafagas;
    Label lTiempo;

    Proceso proceso;

    public ActorProc(Skin skin,Proceso proceso) {
        this.skin = skin;
        this.proceso = proceso;
        stack = new Stack();
        stack.add(new Container(new Image(skin.getDrawable("window_02"))).size(300, 250));
        vg = new VerticalGroup();
        vg.setFillParent(true);
        vg.padTop(12);
        stack.add(vg.top());

        vg.addActor(new Label("1", skin, "dark"));
        lRespuesta = new Label("T. de respuesta:  999 ms", skin, "small");
        vg.addActor(lRespuesta);
        lEspera = new Label("T. de espera:  999 ms", skin, "small");
        vg.addActor(lEspera);
        lRetorno = new Label("T. de retorno:  999 ms", skin, "small");
        vg.addActor(lRetorno);
        lRafagas = new Label("Rafaga:  1", skin, "small");
        vg.addActor(lRafagas);
        lTiempo = new Label("Tiempo para Terminar\nRafaga:  20 ms", skin, "small");
        vg.addActor(lTiempo);

        this.width(320);
        this.height(270);
        this.setActor(stack);
    }

    public void actualizar() {
        lRespuesta.setText("T. de respuesta:  " + proceso.tRespuesta + " ms");
        lEspera.setText("T. de espera:  " + proceso.tEspera + " ms");
        lRetorno.setText("T. de retorno:  " + proceso.tRetorno + " ms");
        if(proceso.rafagaActual < 0) lRafagas.setText("terminado");
        else lRafagas.setText("Rafaga:  " + (proceso.rafagaActual + 1));
        lTiempo.setText("Restante Rafaga\nactual:  " + proceso.t + " ms");
    }
    
    boolean flag;
    public void activar() {
        if(!flag) {
            stack.add(active);
            flag = true;
        }
    }

    public void desactivar() {
        active.remove();
        flag = false;
    }
}
