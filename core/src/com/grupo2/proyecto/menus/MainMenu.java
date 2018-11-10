/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grupo2.proyecto.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.grupo2.proyecto.Main;

/**
 *
 * @author Luis
 */
public class MainMenu extends ScreenAdapter {

    Skin skin;
    Stage stage;
    final Main main;

    public MainMenu(final Main main, final Skin skin) {
        this.main = main;
        this.skin = skin;
        stage = new Stage(new FitViewport(800, 600));
        Gdx.input.setInputProcessor(stage);
        

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        final TextButton buttonCPU = new TextButton("CPU", skin);
        table.add(buttonCPU).pad(20).width(220);
        /*
        Stack stackcpu = new Stack();
        stackcpu.add(new Image(skin.newDrawable("window_03")));
        Image imageCPU = new Image(new Texture(Gdx.files.internal("cpu.png")));
        imageCPU.setSize(98, 98);
        stackcpu.add(imageCPU);
        table.add(stackcpu).size(128);
        */
        table.row();
        final TextButton buttonMEM = new TextButton("Memoria", skin);
        table.add(buttonMEM).pad(20).width(220);
        table.row();
        
        final TextButton buttonDIS = new TextButton("Disco", skin);
        table.add(buttonDIS).pad(20).width(220);
        
        buttonCPU.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                
                main.setScreen(new CPUMenu(main, skin));
                dispose();
            }
        });
        
        buttonMEM.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                
                main.setScreen(new MemMenu(main, skin));
                dispose();
            }
        });
        
        buttonDIS.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                
                main.setScreen(new DisMenu(main, skin));
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
