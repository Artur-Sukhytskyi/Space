package com.mygdx.space;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public abstract class GameCamera {
    OrthographicCamera camera;
    private Vector3 mouse;

    GameCamera(){
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput(float dt);
}
