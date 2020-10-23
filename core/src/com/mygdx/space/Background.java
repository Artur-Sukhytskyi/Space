package com.mygdx.space;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.audio.Music;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.mygdx.space.sprites.Stars;

public class Background extends GameCamera {

    private Texture backTexture;
    private Music themeMusic;
    private Texture starTexture;
    private Stars[] stars;
    private final int STARS_COUNT = 350;

    Background(){
        backTexture = new Texture(Gdx.files.internal("BackGround/bg.png"));
        starTexture = new Texture(Gdx.files.internal("BackGround/star12.tga"));
        themeMusic = Gdx.audio.newMusic(Gdx.files.internal("BackGround/Theme Music.mp3"));
        themeMusic.setLooping(true);
        themeMusic.play();
        stars = new Stars[STARS_COUNT];
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i] = new Stars(starTexture);
        }
    }
    void render(SpriteBatch batch){
        batch.draw(backTexture, 0,0);
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i].draw(batch);
        }
    }
    void update(){
        for (int i = 0; i < STARS_COUNT; i++) {
            stars[i].update();
        }
    }
    @Override
    protected void handleInput(float dt) {}
    void dispose(){
        backTexture.dispose();
        starTexture.dispose();
        themeMusic.dispose();
    }
}