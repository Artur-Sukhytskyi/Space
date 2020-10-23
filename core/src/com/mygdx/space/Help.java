package com.mygdx.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;


class Help {

    private boolean helpActive;

    boolean isHelpActive() {
        return helpActive;
    }

    void setHelpActive(boolean helpActive) {
        this.helpActive = helpActive;
    }

    private BitmapFont help_1;
    private BitmapFont help_2;

    private FreeTypeFontGenerator generator;

    Help(){
        helpActive = false;
        boolean exitfromhelp = false;

        //generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/ARIALUNI.TTF"));
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/ARIAL.TTF"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 20;

        help_1 = generator.generateFont(parameter);
        help_1.setColor(Color.RED);
        help_2 = generator.generateFont(parameter);
    }
    void render(SpriteBatch batch){
        help_1.draw ( batch, "Arrows: Left, Right, Up, Down --- move player", 0, Space.HEIGHT - 30 );
        help_2.draw ( batch, "To go to menu just touch !ME! ^_^", 0, Space.HEIGHT - 50 );
    }
    void update(){
        if(Gdx.input.justTouched ()){
            //helpActive = false;
            if(helpActive){
                System.out.println ("true in help");
            }
        }
    }
    void dispose(){
        generator.dispose();
        help_1.dispose ();
        help_2.dispose ();
    }
}