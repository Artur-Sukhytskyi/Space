package com.mygdx.space.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.space.SpaceScreen;

public class Starship extends Sprite {

    private Vector2 previousSteamshipPosition;
    private float starshipSpeed;
    private int fireCounter;
    private int fireRate;
    private Sound fire;
    public Starship (Texture texture){
        super(texture);
        previousSteamshipPosition = new Vector2(getX(), getY());
        starshipSpeed = 200;
        fireRate = 10;
        fire = Gdx.audio.newSound(Gdx.files.internal("Players/Fire.ogg"));
    }
    private void bulletActivate(float dx, float dy, float speed){
        for (int i = 0; i < SpaceScreen.bullets.length; i++) {
            if(!SpaceScreen.bullets[i].isActive()){
                SpaceScreen.bullets[i].setup(getX() + dx, getY() + dy, speed);
                fire.play();
                break;
            }
        }
    }
    public void playerControl(float dt, Texture texture){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            setPosition(getX() + (-starshipSpeed * dt), getY());
            if(getX() < 0f){
                setX(0f);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            setPosition(getX() + (+starshipSpeed * dt), getY());
            if(getX() > Gdx.graphics.getWidth() - texture.getWidth()){
                setX(Gdx.graphics.getWidth() - texture.getWidth());
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            setPosition(getX(), getY() + (-starshipSpeed * dt));
            if(getY() < 0f){
                setY(0f);
            }
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            setPosition(getX(), getY() + (+starshipSpeed * dt));
            if(getY() > Gdx.graphics.getHeight() - texture.getHeight()){
                setY(Gdx.graphics.getHeight() - texture.getHeight());
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            //System.out.println("space is tap");
            fireCounter++;
            if(fireCounter > fireRate){
                fireCounter = 0;
                bulletActivate(50, 46, 12);
                /*bulletActivate(20, 0, 12);
                bulletActivate(20, 95, 12);*/
            }
        }
    }
    public boolean hasMoved(){
        if(previousSteamshipPosition.x != getX() || previousSteamshipPosition.y != getY()){
            previousSteamshipPosition.x = getX();
            previousSteamshipPosition.y = getY();
            return true;
        }
        return false;
    }
    public void dispose(){
        fire.dispose();
    }
}