/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.mem;

import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class HistorialMem {
    Skin skin;
    VerticalGroup vg;
    ArrayList<Container> actors;
    int i;

    public HistorialMem(Skin skin) {
        this.skin = skin;
        actors = new ArrayList<>();
        vg = new VerticalGroup().space(5);
        i=1;
    }

    public void addRow(ArrayList<String> accesos){
       HorizontalGroup hg = new HorizontalGroup();
       hg.addActor(new Label(String.valueOf(i++), skin,"small"));
       for (String acceso : accesos) {
           actors.add(new Container(new TextButton(acceso, skin,"caja").padBottom(20)).height(40).padBottom(15));
           hg.addActor(actors.get(actors.size() - 1));
       }
       vg.addActor(hg);
    }
   public void addRow(ArrayList<String> accesos, boolean R){
       HorizontalGroup hg = new HorizontalGroup();
       if(R){
           hg.addActor(new Label("R=0", skin,"small"));
       }else{
           hg.addActor(new Label(String.valueOf(i++), skin,"small"));
       }
       for (String acceso : accesos) {
           actors.add(new Container(new TextButton(acceso, skin,"caja").padBottom(20)).height(70).padBottom(15));
           hg.addActor(actors.get(actors.size() - 1));
       }
       vg.addActor(hg);
   }
   
   public void setStrings(ArrayList<String> accesos){
       for (Container actor : actors) {
           actor.remove();
       }
       actors = new ArrayList<>();
       for (String acceso : accesos) {
           actors.add(new Container(new TextButton(acceso, skin,"caja").padBottom(20)).height(40).padBottom(15));
           vg.addActor(actors.get(actors.size() - 1));
       }
   }

    public VerticalGroup getActor() {
        return this.vg;
    }
}
