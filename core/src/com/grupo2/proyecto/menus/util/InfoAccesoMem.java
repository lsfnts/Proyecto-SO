/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.menus.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class InfoAccesoMem {

    ArrayList<Integer> nums;
    ArrayList<ActorPageInfo> actors;
    Skin skin;
    VerticalGroup vg;
    int i;

    public InfoAccesoMem(Skin skin) {
        this.skin = skin;
        nums = new ArrayList<Integer>();
        actors = new ArrayList<ActorPageInfo>();
        vg = new VerticalGroup().padRight(20);
    }

    public void addAcceso(int n, boolean escritura) {
        nums.add(n);
        TextButton tb = new TextButton("Eliminar", skin);
        tb.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                int pos = -1;
                for (ActorPageInfo actI : actors) {
                    if(actor.equals(actI.getTbEliminar())) {
                        pos = actors.indexOf(actI);
                    }
                }
                deleteAcceso(pos);
            }
        });
        actors.add(new ActorPageInfo(skin, n, escritura, tb, ++i));
        vg.addActor(actors.get(actors.size()-1));
    }

    public void deleteAcceso(int pos) {
        if(pos < 0) return;
        nums.remove(pos);
        deleteAcceso(actors.get(pos));
    }
    
    public void deleteAcceso(ActorPageInfo pageInfo) {
        pageInfo.vg.remove();
        actors.remove(pageInfo);
        i -=1;
        int j = 0;
        for (ActorPageInfo actor : actors) {
            actor.setNum(++j);
        }
    }
    
    public ArrayList<Integer> getAccesos() {
        return this.nums;
    }
    
    public ArrayList<Integer> getAccesosNfu(int tR) {
        int ii = tR;
        while(ii < nums.size()){
            nums.add(ii, -1);
            ii += tR+1;
        }
        return this.nums;
    }
    
    public VerticalGroup getActor() {
        return this.vg;
    }

}
