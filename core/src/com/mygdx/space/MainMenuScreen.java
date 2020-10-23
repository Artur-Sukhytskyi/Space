package com.mygdx.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends GameCamera{

    private boolean mainActive;

    boolean isMainActive() {
        return mainActive;
    }

    void setMainActive(boolean mainActive) {
        this.mainActive = mainActive;
    }

    private Stage menuStage;

    Stage getMenuStage() {
        return menuStage;
    }

    private ImageButton newGameButton;
    private ImageButton helpButton;
    private ImageButton exitButton;

    //Для текстур кнопок
    private static Texture texNewGameButton;
    private static Texture texHelpButton;
    private static Texture texExitButton;

    /**Помощь*/
    private boolean helpActive;

    boolean isHelpActive() {
        return helpActive;
    }

    void setHelpActive(boolean helpActive) {
        this.helpActive = helpActive;
    }

    MainMenuScreen(){

        mainActive = true;
        helpActive = false;

        camera.setToOrtho ( false, Space.WIDTH, Space.HEIGHT );

        menuStage = new Stage ( new ScreenViewport() );

        //Загружаем текстуры кнопок
        texNewGameButton = new Texture(Gdx.files.internal ( "ActionButtons/NewGame.png"));
        texHelpButton = new Texture (Gdx.files.internal ( "ActionButtons/Help.png" ));
        texExitButton = new Texture(Gdx.files.internal ( "ActionButtons/Exit.png"));

        TextureRegion regNewGame = new TextureRegion(texNewGameButton);
        TextureRegion regHelp = new TextureRegion(texHelpButton);
        TextureRegion regExit = new TextureRegion(texExitButton);

        TextureRegionDrawable drNewGame = new TextureRegionDrawable(regNewGame);
        TextureRegionDrawable drHelp = new TextureRegionDrawable(regHelp);
        TextureRegionDrawable drExit = new TextureRegionDrawable(regExit);

        Vector2 posNewGameButton = new Vector2(Gdx.graphics.getWidth() / 2 - (texNewGameButton.getWidth() / 2), 350);
        Vector2 posHelpButton = new Vector2(Gdx.graphics.getWidth() / 2 - (texHelpButton.getWidth() / 2), 250);
        Vector2 posExitButton = new Vector2(Gdx.graphics.getWidth() / 2 - (texExitButton.getWidth() / 2), 150);

        newGameButton = new ImageButton (drNewGame);
        helpButton = new ImageButton (drHelp);
        exitButton = new ImageButton (drExit);

        newGameButton.setPosition ( posNewGameButton.x, posNewGameButton.y );
        helpButton.setPosition ( posHelpButton.x, posHelpButton.y );
        exitButton.setPosition ( posExitButton.x, posExitButton.y );

        if(mainActive) {
            menuStage.addActor ( newGameButton );
            menuStage.addActor ( helpButton );
            menuStage.addActor ( exitButton );
        }
        handleInput (Gdx.graphics.getDeltaTime());
    }
    private void handleInputNewGame(){
        newGameButton.addListener ( new ClickListener(  ){
            public void clicked(InputEvent event, float x, float y){
                if(mainActive && !helpActive) {
                    mainActive = false;
                }
            }
        } );
    }
    private void handleInputHelp(){
        helpButton.addListener ( new ClickListener (  ){
            public void clicked(InputEvent event, float x, float y){
                if(mainActive && !helpActive) {
                    helpActive = true;
                }
            }
        } );
    }
    private void handleInputExit(){
        exitButton.addListener ( new ClickListener (  ){
            public void clicked(InputEvent event, float x, float y){
                if(mainActive && !helpActive) {
                    Gdx.app.exit ();
                }
            }
        } );
    }
    @Override
    protected void handleInput(float dt) {
        handleInputNewGame ();
        handleInputHelp ();
        handleInputExit ();
    }
    void render(SpriteBatch batch){
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
    void stage(){
        menuStage.draw();
    }
    void dispose(){
        texNewGameButton.dispose();
        texHelpButton.dispose();
        texExitButton.dispose();
    }
}