package com.mygdx.space.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends Sprite {
    private Vector2 previousBulletPosition;
    private float speed;
    private boolean active;
    public boolean isActive() {
        return active;
    }
    public Bullet(Texture texture){
        super(texture);
        setPosition(-60.0f, -60.0f);
        previousBulletPosition = new Vector2(getX(), getY());
        speed = 6.0f;
        active = false;
    }
    public boolean hasMoved(){
        if(previousBulletPosition.x != getX() || previousBulletPosition.y != getY()){
            previousBulletPosition.x = getX();
            previousBulletPosition.y = getY();
            return true;
        }
        return false;
    }
    public void destroy(){
        active = false;
    }
    void setup(float x, float y, float speed){
        setX(x);
        setY(y);
        this.speed = speed;
        active = true;
    }
    public void update(){
        setX(getX() + speed);
        if(getX() > 820){
            destroy();
        }
    }
}