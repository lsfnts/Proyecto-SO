/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.dis.util;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class ActorDisSolList {

    Skin skin;
    HorizontalGroup hg;
    ArrayList<Container> actors;
    int i;

    public ActorDisSolList(Skin skin) {
        this.skin = skin;
        actors = new ArrayList<>();
        hg = new HorizontalGroup().space(5);
    }

    public void addAcces(int n) {
        actors.add(new Container(new TextButton(String.valueOf(n), skin,"caja")));
        hg.addActor(actors.get(actors.size() - 1));
    }

    public void deleteAcces(int pos) {
        if (pos < 0) {
            return;
        }
        actors.remove(pos);
    }

   public void setAcces(ArrayList<Integer> accesos){
       for (Container actor : actors) {
           actor.remove();
       }
       actors = new ArrayList<>();
       for (Integer acceso : accesos) {
           actors.add(new Container(new TextButton(String.valueOf(acceso), skin,"caja").padBottom(20)).height(40).padBottom(15));
           hg.addActor(actors.get(actors.size() - 1));
       }
   }
   
   public void setStrings(ArrayList<String> accesos){
       for (Container actor : actors) {
           actor.remove();
       }
       actors = new ArrayList<>();
       for (String acceso : accesos) {
           actors.add(new Container(new TextButton(acceso.split(":")[0], skin,"caja").padBottom(20)).height(40).padBottom(15));
           actors.add(new Container(new TextButton(acceso.split(":")[1]+"ms", skin,"cajita").padBottom(20)).height(40).padBottom(15).padRight(20));
           hg.addActor(actors.get(actors.size() - 2));
           hg.addActor(actors.get(actors.size() - 1));
       }
   }

    public HorizontalGroup getActor() {
        return this.hg;
    }
}
