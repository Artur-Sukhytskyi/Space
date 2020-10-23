package com.mygdx.space.sprites;

        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.Sprite;

public class Stars extends Sprite {

    private float starSpeed;

    public Stars(Texture texture){
        super(texture);
        setPosition((float) Math.random() * 800, -5 + (float) Math.random() * 600);
        starSpeed = 2.0f + (float)Math.random() * 8.0f;
    }
    public void update(){
        setX(getX() - starSpeed);
        if(getX() < - 20){
            setPosition(820, -5 + (float) Math.random() * 600);
            starSpeed = 2.0f + (float)Math.random() * 8.0f;
        }
    }
}