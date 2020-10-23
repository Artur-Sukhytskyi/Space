package com.mygdx.space.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Asteroids extends Sprite {

    private Vector2 previousAsteroidPosition;
    private float asteroidSpeed;
    private Rectangle rectangle;
    private int asteroidHP;
    private boolean asteroidDestroyed;

    public boolean isAsteroidDestroyed() {
        return asteroidDestroyed;
    }

    public void setAsteroidDestroyed(boolean asteroidDestroyed) {
        this.asteroidDestroyed = asteroidDestroyed;
    }
    public int getAsteroidHP() {
        return asteroidHP;
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public Asteroids(Texture texture){
        super(texture);
        setPosition(820 + (float)Math.random() * 820, -5 + (float)Math.random() * 620);
        //setPosition(200, 200);
        previousAsteroidPosition = new Vector2(getX(), getY());
        asteroidSpeed = 5.0f + (float)Math.random() * 7.0f;
        //asteroidSpeed = 0;
        rectangle = new Rectangle(getX(), getY(), texture.getWidth(), getHeight());
        asteroidHP = 3 + (int)(Math.random() * 3);
        asteroidDestroyed = false;
    }
    public boolean hasMoved(){
        if(previousAsteroidPosition.x != getX() || previousAsteroidPosition.y != getY()){
            previousAsteroidPosition.x = getX();
            previousAsteroidPosition.y = getY();
            return true;
        }
        return false;
    }
    private void asteroidRecreate(){
        setPosition(820 + (float)Math.random() * 820, -5 + (float)Math.random() * 620);
        //setPosition(200, 200);
        asteroidSpeed = 5.0f + (float)Math.random() * 7.0f;
        //asteroidSpeed = 0;
        asteroidHP = 3 + (int)(Math.random() * 3);
    }
    private void asteroidRecreateAfterDestoying(){
        setPosition(820 + (float)Math.random() * 820, -5 + (float)Math.random() * 620);
        //setPosition(200, 200);
        asteroidSpeed = 5.0f + (float)Math.random() * 7.0f;
        //asteroidSpeed = 0;
        asteroidHP = 3 + (int)(Math.random() * 3);
    }
    public void getDamage(int dmg){
        asteroidHP -= dmg;
        if(asteroidHP == 0){
            asteroidDestroyed = true;
            asteroidRecreateAfterDestoying();
        }
    }
    public void render(SpriteBatch batch, Texture texture){
        batch.draw(texture, getX(), getY());
    }
    public void update(){
        setX(getX() - asteroidSpeed);
        rectangle.x = getX();
        rectangle.y = getY();
        if(getX() < -60){
            asteroidRecreate();
        }
    }
}