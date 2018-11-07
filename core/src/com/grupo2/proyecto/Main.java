package com.grupo2.proyecto;

import com.grupo2.proyecto.Menus.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.grupo2.proyecto.cpu.CPUScreen;

public class Main extends Game {

    Skin skin;
    TextureAtlas atlas;
    
    @Override
    public void create() {
        skin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("ui-blue.atlas"));
        skin.addRegions(atlas);

        // La fuente
        skin.add("font", new BitmapFont(Gdx.files.internal("KenneyFuture.fnt")));

        // Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("button_01");
        textButtonStyle.over = skin.newDrawable("button_02");
        textButtonStyle.font = skin.getFont("font");
        skin.add("default", textButtonStyle);
        
        SelectBox.SelectBoxStyle selectBoxStyle = new SelectBox.SelectBoxStyle();
        selectBoxStyle.background = skin.getDrawable("selectbox_01");
        selectBoxStyle.font = skin.getFont("font");
        selectBoxStyle.fontColor = Color.DARK_GRAY;
        List.ListStyle ls = new List.ListStyle(skin.getFont("font"),
                Color.SLATE, Color.LIGHT_GRAY, skin.getDrawable("textbox_01"));
        ls.background = skin.getDrawable("textbox_02");
        selectBoxStyle.listStyle = ls;
        selectBoxStyle.scrollStyle = new ScrollPane.ScrollPaneStyle();
        skin.add("default", selectBoxStyle);

        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle(
                skin.getFont("font"), Color.DARK_GRAY, skin.getDrawable("textbox_cursor_02"),
                skin.getDrawable("textbox_02"), skin.getDrawable("textbox_02"));
        skin.add("default", textFieldStyle);
        
        ScrollPane.ScrollPaneStyle scrollPaneStyle = new ScrollPane.ScrollPaneStyle(
                skin.getDrawable("textbox_02"), skin.getDrawable("scroll_back_hor"),
                skin.getDrawable("knob_06"), skin.getDrawable("scroll_back_ver"),
                skin.getDrawable("knob_05"));
        skin.add("default", scrollPaneStyle);
        
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = skin.getDrawable("slider_back_hor");
        sliderStyle.knob = skin.getDrawable("knob_05");
        skin.add("default-horizontal", sliderStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle(skin.getFont("font"), Color.LIGHT_GRAY);
        skin.add("default", labelStyle);
        setScreen(new MainMenu(this, skin));
        
        Label.LabelStyle labelStyleDA = new Label.LabelStyle(skin.getFont("font"), Color.DARK_GRAY);
        skin.add("dark", labelStyleDA);
        setScreen(new MainMenu(this, skin));
    }

    @Override
    public void dispose() {
    }
}
