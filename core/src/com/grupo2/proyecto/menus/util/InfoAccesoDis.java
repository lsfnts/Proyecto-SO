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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class InfoAccesoDis {

    ArrayList<Integer> nums;
    ArrayList<ActorDisAcInfo> actors;
    Skin skin;
    VerticalGroup vg;
    int i;

    public InfoAccesoDis(Skin skin) {
        this.skin = skin;
        nums = new ArrayList<Integer>();
        actors = new ArrayList<ActorDisAcInfo>();
        vg = new VerticalGroup().padRight(20);
    }

    public void addAcceso(int n) {
        nums.add(n);
        TextButton tb = new TextButton("Eliminar", skin);
        //lNum = new Label("# "+i, skin, "dark");vg = new VerticalGroup().space(10).pad(10);
        //hg.addActor(lNum);
        tb.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                int pos = -1;
                for (ActorDisAcInfo actI : actors) {
                    if (actor.equals(actI.getTbEliminar())) {
                        pos = actors.indexOf(actI);
                    }
                }
                deleteAcceso(pos);
            }
        });
        actors.add(new ActorDisAcInfo(skin, n, tb, ++i));
        vg.addActor(actors.get(actors.size() - 1));
    }

    public void deleteAcceso(int pos) {
        if (pos < 0) {
            return;
        }
        nums.remove(pos);
        deleteAcceso(actors.get(pos));
    }

    public void deleteAcceso(ActorDisAcInfo DisAcInfo) {
        DisAcInfo.vg.remove();
        actors.remove(DisAcInfo);
        i -= 1;
        int j = 0;
        for (ActorDisAcInfo actor : actors) {
            actor.setNum(++j);
        }
    }

    public ArrayList<Integer> getAccesos() {
        return this.nums;
    }

    public VerticalGroup getActor() {
        return this.vg;
    }
}
