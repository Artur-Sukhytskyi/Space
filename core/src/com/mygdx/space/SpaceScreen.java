package com.mygdx.space;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.space.sprites.Asteroids;
import com.mygdx.space.sprites.Bullet;
import com.mygdx.space.sprites.Starship;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.socket.client.Socket;

public class SpaceScreen extends GameCamera{

    private boolean stageActive;/**Для управления активностью Сцены*/

    boolean isStageActive() {
        return stageActive;
    }

    void setStageActive(boolean stageActive) {
        this.stageActive = stageActive;
    }

    private Starship player;
    private Texture playerShip;
    private Texture friendlyShip;

    HashMap<String, Starship> friendlyPlayers;
    HashMap<String, Asteroids> anotherAsteroids_0;
    HashMap<String, Asteroids> anotherAsteroids_1;
    HashMap<String, Asteroids> anotherAsteroids_2;
    HashMap<String, Asteroids> anotherAsteroids_3;
    HashMap<String, Asteroids> anotherAsteroids_4;
    HashMap<String, Asteroids> anotherAsteroids_5;
    HashMap<String, Asteroids> anotherAsteroids_6;
    HashMap<String, Asteroids> anotherAsteroids_7;
    HashMap<String, Asteroids> anotherAsteroids_8;
    HashMap<String, Asteroids> anotherAsteroids_9;

    HashMap<String, Bullet> friendlyBullets_0;
    HashMap<String, Bullet> friendlyBullets_1;
    HashMap<String, Bullet> friendlyBullets_2;
    HashMap<String, Bullet> friendlyBullets_3;
    HashMap<String, Bullet> friendlyBullets_4;
    HashMap<String, Bullet> friendlyBullets_5;
    HashMap<String, Bullet> friendlyBullets_6;
    HashMap<String, Bullet> friendlyBullets_7;
    HashMap<String, Bullet> friendlyBullets_8;
    HashMap<String, Bullet> friendlyBullets_9;
    HashMap<String, Bullet> friendlyBullets_10;
    HashMap<String, Bullet> friendlyBullets_11;
    HashMap<String, Bullet> friendlyBullets_12;
    HashMap<String, Bullet> friendlyBullets_13;
    HashMap<String, Bullet> friendlyBullets_14;
    HashMap<String, Bullet> friendlyBullets_15;
    HashMap<String, Bullet> friendlyBullets_16;
    HashMap<String, Bullet> friendlyBullets_17;
    HashMap<String, Bullet> friendlyBullets_18;
    HashMap<String, Bullet> friendlyBullets_19;

    private final int ASTEROID_COUNT = 10;
    private Asteroids[] asteroids = new Asteroids[ASTEROID_COUNT];
    private Texture asteroidTextura;

    private final int BULLETS_COUNT = 20;
    public static Bullet[] bullets;
    private Texture bulletTexture;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;
    private BitmapFont asteroidDestroyed;
    private int count;

    SpaceScreen(){
        stageActive = false;
        camera.setToOrtho( false, Space.WIDTH, Space.HEIGHT );

        playerShip = new Texture(Gdx.files.internal("Players/playerShip1.png"));
        friendlyShip = new Texture(Gdx.files.internal("Players/playerShip2.png"));
        friendlyPlayers = new HashMap<String, Starship>();

        asteroidTextura = new Texture(Gdx.files.internal("asteroid.tga"));
        for (int i = 0; i < ASTEROID_COUNT; i++) {
            asteroids[i] = new Asteroids(asteroidTextura);
        }
        anotherAsteroids_0 = new HashMap<String, Asteroids>();
        anotherAsteroids_1 = new HashMap<String, Asteroids>();
        anotherAsteroids_2 = new HashMap<String, Asteroids>();
        anotherAsteroids_3 = new HashMap<String, Asteroids>();
        anotherAsteroids_4 = new HashMap<String, Asteroids>();
        anotherAsteroids_5 = new HashMap<String, Asteroids>();
        anotherAsteroids_6 = new HashMap<String, Asteroids>();
        anotherAsteroids_7 = new HashMap<String, Asteroids>();
        anotherAsteroids_8 = new HashMap<String, Asteroids>();
        anotherAsteroids_9 = new HashMap<String, Asteroids>();

        bulletTexture = new Texture(Gdx.files.internal("Players/bullet.tga"));
        bullets = new Bullet[BULLETS_COUNT];
        for (int i = 0; i < BULLETS_COUNT; i++) {
            bullets[i] = new Bullet(bulletTexture);
        }
        friendlyBullets_0 = new HashMap<String, Bullet>();
        friendlyBullets_1 = new HashMap<String, Bullet>();
        friendlyBullets_2 = new HashMap<String, Bullet>();
        friendlyBullets_3 = new HashMap<String, Bullet>();
        friendlyBullets_4 = new HashMap<String, Bullet>();
        friendlyBullets_5 = new HashMap<String, Bullet>();
        friendlyBullets_6 = new HashMap<String, Bullet>();
        friendlyBullets_7 = new HashMap<String, Bullet>();
        friendlyBullets_8 = new HashMap<String, Bullet>();
        friendlyBullets_9 = new HashMap<String, Bullet>();
        friendlyBullets_10 = new HashMap<String, Bullet>();
        friendlyBullets_11 = new HashMap<String, Bullet>();
        friendlyBullets_12 = new HashMap<String, Bullet>();
        friendlyBullets_13 = new HashMap<String, Bullet>();
        friendlyBullets_14 = new HashMap<String, Bullet>();
        friendlyBullets_15 = new HashMap<String, Bullet>();
        friendlyBullets_16 = new HashMap<String, Bullet>();
        friendlyBullets_17 = new HashMap<String, Bullet>();
        friendlyBullets_18 = new HashMap<String, Bullet>();
        friendlyBullets_19 = new HashMap<String, Bullet>();

        //generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/ARIALUNI.TTF"));
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/ARIAL.TTF"));
        parameter = new FreeTypeFontParameter();
        parameter.size = 20;
        asteroidDestroyed = generator.generateFont(parameter);
        count = 0;
    }

    @Override
    protected void handleInput(float dt) {
        if(player != null){
            player.playerControl(dt, playerShip);
        }
    }

    void render(SpriteBatch batch){
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        if(player != null && isStageActive()){
            player.draw(batch);
        }
        for(HashMap.Entry<String, Starship> entry : friendlyPlayers.entrySet()){
            entry.getValue().draw(batch);
        }

        for (int i = 0; i < ASTEROID_COUNT; i++) {
            asteroids[i].render(batch, asteroidTextura);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_0.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_1.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_2.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_3.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_4.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_5.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_6.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_7.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_8.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Asteroids> entry : anotherAsteroids_9.entrySet()){
            entry.getValue().draw(batch);
        }

        for (int i = 0; i < BULLETS_COUNT; i++) {
            if(bullets[i].isActive()){
                batch.draw(bulletTexture, bullets[i].getX(), bullets[i].getY());
            }
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_0.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_1.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_2.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_3.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_4.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_5.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_6.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_7.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_8.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_9.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_10.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_11.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_12.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_13.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_14.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_15.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_16.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_17.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_18.entrySet()){
            entry.getValue().draw(batch);
        }
        for(HashMap.Entry<String, Bullet> entry : friendlyBullets_19.entrySet()){
            entry.getValue().draw(batch);
        }

        asteroidDestroyed.draw(batch, "Asteroids destroyed: " + count, 0,Gdx.graphics.getHeight() - parameter.size);
    }

    void firstPlayer(){
        player = new Starship(playerShip);
    }
    void secondPlayer(String id){
        friendlyPlayers.put(id, new Starship(friendlyShip));
    }
    void coopPlayers(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Starship coopPlayer = new Starship(friendlyShip);
                Vector2 coopPosition = new Vector2();
                coopPosition.x = ((Double) objects.getJSONObject(i).getDouble("playerPos_x")).floatValue();
                coopPosition.y = ((Double) objects.getJSONObject(i).getDouble("playerPos_y")).floatValue();
                coopPlayer.setPosition(coopPosition.x, coopPosition.y);

                friendlyPlayers.put(objects.getJSONObject(i).getString("id"), coopPlayer);
            }
        }catch (JSONException e) {        }
    }
    void playerMoved(JSONObject data){
        try {
            String playerID = data.getString("id");
            Double x = data.getDouble("playerPos_x");
            Double y = data.getDouble("playerPos_y");
            if(friendlyPlayers.get(playerID) != null){
                friendlyPlayers.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }

    void differentAsteroid(String id){
        anotherAsteroids_0.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_1.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_2.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_3.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_4.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_5.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_6.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_7.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_8.put(id, new Asteroids(asteroidTextura));
        anotherAsteroids_9.put(id, new Asteroids(asteroidTextura));
    }
    void coopDiffAsteroid_0(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_0")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_0")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_0.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_1(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_1")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_1")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_1.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_2(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_2")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_2")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_2.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_3(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_3")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_3")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_3.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_4(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_4")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_4")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_4.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_5(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_5")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_5")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_5.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_6(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_6")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_6")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_6.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_7(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_7")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_7")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_7.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_8(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_8")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_8")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_8.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }
    void coopDiffAsteroid_9(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Asteroids asteroid = new Asteroids(asteroidTextura);
                Vector2 asteroidPosition = new Vector2();

                asteroidPosition.x = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_x_9")).floatValue();
                asteroidPosition.y = ((Double) objects.getJSONObject(i).getDouble("asteroidPos_y_9")).floatValue();
                asteroid.setPosition(asteroidPosition.x, asteroidPosition.y);
                anotherAsteroids_9.put(objects.getJSONObject(i).getString("id"), asteroid);
            }
        }catch (JSONException e) {        }
    }

    void asteroidMoved_0(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_0");
            Double y = data.getDouble("asteroidPos_y_0");
            if(anotherAsteroids_0.get(playerID) != null){
                anotherAsteroids_0.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_1(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_1");
            Double y = data.getDouble("asteroidPos_y_1");
            if(anotherAsteroids_1.get(playerID) != null){
                anotherAsteroids_1.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_2(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_2");
            Double y = data.getDouble("asteroidPos_y_2");
            if(anotherAsteroids_2.get(playerID) != null){
                anotherAsteroids_2.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_3(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_3");
            Double y = data.getDouble("asteroidPos_y_3");
            if(anotherAsteroids_3.get(playerID) != null){
                anotherAsteroids_3.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_4(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_4");
            Double y = data.getDouble("asteroidPos_y_4");
            if(anotherAsteroids_4.get(playerID) != null){
                anotherAsteroids_4.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_5(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_5");
            Double y = data.getDouble("asteroidPos_y_5");
            if(anotherAsteroids_5.get(playerID) != null){
                anotherAsteroids_5.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_6(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_6");
            Double y = data.getDouble("asteroidPos_y_6");
            if(anotherAsteroids_6.get(playerID) != null){
                anotherAsteroids_6.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_7(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_7");
            Double y = data.getDouble("asteroidPos_y_7");
            if(anotherAsteroids_7.get(playerID) != null){
                anotherAsteroids_7.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_8(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_8");
            Double y = data.getDouble("asteroidPos_y_8");
            if(anotherAsteroids_8.get(playerID) != null){
                anotherAsteroids_8.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void asteroidMoved_9(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("asteroidPos_x_9");
            Double y = data.getDouble("asteroidPos_y_9");
            if(anotherAsteroids_9.get(playerID) != null){
                anotherAsteroids_9.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }

    void secondPlayerBullet(String id){
        friendlyBullets_0.put(id, new Bullet(bulletTexture));
        friendlyBullets_1.put(id, new Bullet(bulletTexture));
        friendlyBullets_2.put(id, new Bullet(bulletTexture));
        friendlyBullets_3.put(id, new Bullet(bulletTexture));
        friendlyBullets_4.put(id, new Bullet(bulletTexture));
        friendlyBullets_5.put(id, new Bullet(bulletTexture));
        friendlyBullets_6.put(id, new Bullet(bulletTexture));
        friendlyBullets_7.put(id, new Bullet(bulletTexture));
        friendlyBullets_8.put(id, new Bullet(bulletTexture));
        friendlyBullets_9.put(id, new Bullet(bulletTexture));
        friendlyBullets_10.put(id, new Bullet(bulletTexture));
        friendlyBullets_11.put(id, new Bullet(bulletTexture));
        friendlyBullets_12.put(id, new Bullet(bulletTexture));
        friendlyBullets_13.put(id, new Bullet(bulletTexture));
        friendlyBullets_14.put(id, new Bullet(bulletTexture));
        friendlyBullets_15.put(id, new Bullet(bulletTexture));
        friendlyBullets_16.put(id, new Bullet(bulletTexture));
        friendlyBullets_17.put(id, new Bullet(bulletTexture));
        friendlyBullets_18.put(id, new Bullet(bulletTexture));
        friendlyBullets_19.put(id, new Bullet(bulletTexture));
    }
    void coopPlayersBullet_0(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_0")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_0")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_0.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_1(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_1")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_1")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_1.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_2(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_2")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_2")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_2.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_3(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_3")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_3")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_3.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_4(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_4")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_4")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_4.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_5(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_5")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_5")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_5.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_6(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_6")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_6")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_6.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_7(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_7")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_7")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_7.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_8(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_8")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_8")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_8.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_9(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_9")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_9")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_9.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_10(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_10")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_10")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_10.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_11(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_11")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_11")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_11.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_12(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_12")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_12")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_12.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_13(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_13")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_13")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_13.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_14(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_14")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_14")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_14.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_15(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_15")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_15")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_15.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_16(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_16")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_16")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_16.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_17(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_17")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_17")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_17.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_18(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_18")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_18")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_18.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void coopPlayersBullet_19(JSONArray objects){
        try{
            for (int i = 0; i < objects.length(); i++) {
                Bullet bullet = new Bullet(bulletTexture);
                Vector2 bulletPosition = new Vector2();

                bulletPosition.x = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_19")).floatValue();
                bulletPosition.y = ((Double) objects.getJSONObject(i).getDouble("bulletPos_x_19")).floatValue();
                bullet.setPosition(bulletPosition.x, bulletPosition.y);
                friendlyBullets_19.put(objects.getJSONObject(i).getString("id"), bullet);
            }
        }catch (JSONException e) {        }
    }
    void bulletMoved_0(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_0");
            Double y = data.getDouble("bulletPos_y_0");
            if(friendlyBullets_0.get(playerID) != null){
                friendlyBullets_0.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_1(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_1");
            Double y = data.getDouble("bulletPos_y_1");
            if(friendlyBullets_1.get(playerID) != null){
                friendlyBullets_1.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_2(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_2");
            Double y = data.getDouble("bulletPos_y_2");
            if(friendlyBullets_2.get(playerID) != null){
                friendlyBullets_2.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_3(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_3");
            Double y = data.getDouble("bulletPos_y_3");
            if(friendlyBullets_3.get(playerID) != null){
                friendlyBullets_3.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_4(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_4");
            Double y = data.getDouble("bulletPos_y_4");
            if(friendlyBullets_4.get(playerID) != null){
                friendlyBullets_4.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_5(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_5");
            Double y = data.getDouble("bulletPos_y_5");
            if(friendlyBullets_5.get(playerID) != null){
                friendlyBullets_5.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_6(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_6");
            Double y = data.getDouble("bulletPos_y_6");
            if(friendlyBullets_6.get(playerID) != null){
                friendlyBullets_6.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_7(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_7");
            Double y = data.getDouble("bulletPos_y_7");
            if(friendlyBullets_7.get(playerID) != null){
                friendlyBullets_7.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_8(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_8");
            Double y = data.getDouble("bulletPos_y_8");
            if(friendlyBullets_8.get(playerID) != null){
                friendlyBullets_8.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_9(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_9");
            Double y = data.getDouble("bulletPos_y_9");
            if(friendlyBullets_9.get(playerID) != null){
                friendlyBullets_9.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_10(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_10");
            Double y = data.getDouble("bulletPos_y_10");
            if(friendlyBullets_10.get(playerID) != null){
                friendlyBullets_10.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_11(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_11");
            Double y = data.getDouble("bulletPos_y_11");
            if(friendlyBullets_11.get(playerID) != null){
                friendlyBullets_11.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_12(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_12");
            Double y = data.getDouble("bulletPos_y_12");
            if(friendlyBullets_12.get(playerID) != null){
                friendlyBullets_12.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_13(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_13");
            Double y = data.getDouble("bulletPos_y_13");
            if(friendlyBullets_13.get(playerID) != null){
                friendlyBullets_13.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_14(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_14");
            Double y = data.getDouble("bulletPos_y_14");
            if(friendlyBullets_14.get(playerID) != null){
                friendlyBullets_14.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_15(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_15");
            Double y = data.getDouble("bulletPos_y_15");
            if(friendlyBullets_15.get(playerID) != null){
                friendlyBullets_15.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_16(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_16");
            Double y = data.getDouble("bulletPos_y_16");
            if(friendlyBullets_16.get(playerID) != null){
                friendlyBullets_16.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_17(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_17");
            Double y = data.getDouble("bulletPos_y_17");
            if(friendlyBullets_17.get(playerID) != null){
                friendlyBullets_17.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_18(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_18");
            Double y = data.getDouble("bulletPos_y_18");
            if(friendlyBullets_18.get(playerID) != null){
                friendlyBullets_18.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void bulletMoved_19(JSONObject data){
        try {
            String playerID = data.getString("id");

            Double x = data.getDouble("bulletPos_x_19");
            Double y = data.getDouble("bulletPos_y_19");
            if(friendlyBullets_19.get(playerID) != null){
                friendlyBullets_19.get(playerID).setPosition(x.floatValue(), y.floatValue());
            }
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error getting newPlayer id");
        }
    }
    void update() {
        handleInput(Gdx.graphics.getDeltaTime());
        if (stageActive) {
            //if(stageActive && player != null) {
            for (int i = 0; i < ASTEROID_COUNT; i++) {
                asteroids[i].update();
            }
            for (int i = 0; i < BULLETS_COUNT; i++) {
                if(bullets[i].isActive()){
                    bullets[i].update();
                    for (int j = 0; j < ASTEROID_COUNT; j++) {
                        if(asteroids[j].getRectangle().contains(bullets[i].getX(), bullets[i].getY())){
                            asteroids[j].getDamage(1);
                            bullets[i].destroy();
                            if(asteroids[j].isAsteroidDestroyed()){
                                count += 1;
                                asteroids[j].setAsteroidDestroyed(false);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    void updatePlayer(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && player != null && player.hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("playerPos_x", player.getX());
                data.put("playerPos_y", player.getY());
                socket.emit("playerMoved", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for player");
            }
        }
    }
    void updateAsteroids_0(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[0].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_0", asteroids[0].getX());
                data.put("asteroidPos_y_0", asteroids[0].getY());

                socket.emit("asteroidMoved_0", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_1(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[1].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_1", asteroids[1].getX());
                data.put("asteroidPos_y_1", asteroids[1].getY());

                socket.emit("asteroidMoved_1", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_2(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[2].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_2", asteroids[2].getX());
                data.put("asteroidPos_y_2", asteroids[2].getY());

                socket.emit("asteroidMoved_2", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_3(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[3].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_3", asteroids[3].getX());
                data.put("asteroidPos_y_3", asteroids[3].getY());

                socket.emit("asteroidMoved_3", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_4(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[4].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_4", asteroids[4].getX());
                data.put("asteroidPos_y_4", asteroids[4].getY());

                socket.emit("asteroidMoved_4", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_5(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[5].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_5", asteroids[5].getX());
                data.put("asteroidPos_y_5", asteroids[5].getY());

                socket.emit("asteroidMoved_5", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_6(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[6].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_6", asteroids[6].getX());
                data.put("asteroidPos_y_6", asteroids[6].getY());

                socket.emit("asteroidMoved_6", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_7(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[7].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_7", asteroids[7].getX());
                data.put("asteroidPos_y_7", asteroids[7].getY());

                socket.emit("asteroidMoved_7", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_8(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[8].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_8", asteroids[8].getX());
                data.put("asteroidPos_y_8", asteroids[8].getY());

                socket.emit("asteroidMoved_8", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateAsteroids_9(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && asteroids != null && asteroids[9].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("asteroidPos_x_9", asteroids[9].getX());
                data.put("asteroidPos_y_9", asteroids[9].getY());

                socket.emit("asteroidMoved_9", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for asteroids");
            }
        }
    }
    void updateBullets_0(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[0].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_0", bullets[0].getX());
                data.put("bulletPos_y_0", bullets[0].getY());

                socket.emit("bulletMoved_0", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_1(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[1].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_1", bullets[1].getX());
                data.put("bulletPos_y_1", bullets[1].getY());

                socket.emit("bulletMoved_1", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_2(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[2].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_2", bullets[2].getX());
                data.put("bulletPos_y_2", bullets[2].getY());

                socket.emit("bulletMoved_2", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_3(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[3].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_3", bullets[3].getX());
                data.put("bulletPos_y_3", bullets[3].getY());

                socket.emit("bulletMoved_3", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_4(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[4].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_4", bullets[4].getX());
                data.put("bulletPos_y_4", bullets[4].getY());

                socket.emit("bulletMoved_4", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_5(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[5].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_5", bullets[5].getX());
                data.put("bulletPos_y_5", bullets[5].getY());

                socket.emit("bulletMoved_5", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_6(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[6].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_6", bullets[6].getX());
                data.put("bulletPos_y_6", bullets[6].getY());

                socket.emit("bulletMoved_6", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_7(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[7].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_7", bullets[7].getX());
                data.put("bulletPos_y_7", bullets[7].getY());

                socket.emit("bulletMoved_7", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_8(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[8].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_8", bullets[8].getX());
                data.put("bulletPos_y_8", bullets[8].getY());

                socket.emit("bulletMoved_8", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_9(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[9].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_9", bullets[9].getX());
                data.put("bulletPos_y_9", bullets[9].getY());

                socket.emit("bulletMoved_9", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_10(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[10].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_10", bullets[10].getX());
                data.put("bulletPos_y_10", bullets[10].getY());

                socket.emit("bulletMoved_10", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_11(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[11].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_11", bullets[11].getX());
                data.put("bulletPos_y_11", bullets[11].getY());

                socket.emit("bulletMoved_11", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_12(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[12].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_12", bullets[12].getX());
                data.put("bulletPos_y_12", bullets[12].getY());

                socket.emit("bulletMoved_12", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_13(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[13].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_13", bullets[13].getX());
                data.put("bulletPos_y_13", bullets[13].getY());

                socket.emit("bulletMoved_13", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_14(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[14].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_14", bullets[14].getX());
                data.put("bulletPos_y_14", bullets[14].getY());

                socket.emit("bulletMoved_14", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_15(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[15].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_15", bullets[15].getX());
                data.put("bulletPos_y_15", bullets[15].getY());

                socket.emit("bulletMoved_15", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_16(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[16].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_16", bullets[16].getX());
                data.put("bulletPos_y_16", bullets[16].getY());

                socket.emit("bulletMoved_16", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_17(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[17].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_17", bullets[17].getX());
                data.put("bulletPos_y_17", bullets[17].getY());

                socket.emit("bulletMoved_17", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_18(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[18].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_18", bullets[18].getX());
                data.put("bulletPos_y_18", bullets[18].getY());

                socket.emit("bulletMoved_18", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }
    void updateBullets_19(float dt, float timer, float UPDATE_TIME, Socket socket){
        timer += dt;
        if(timer >= UPDATE_TIME && bullets != null && bullets[19].hasMoved()){
            JSONObject data = new JSONObject();
            try{
                data.put("bulletPos_x_19", bullets[19].getX());
                data.put("bulletPos_y_19", bullets[19].getY());

                socket.emit("bulletMoved_19", data);
            }catch (JSONException e){
                Gdx.app.log("SocketIO", "Error sending update data for bullets");
            }
        }
    }

    void dispose(){
        playerShip.dispose();
        friendlyShip.dispose();
        player.dispose();
        asteroidTextura.dispose();
        bulletTexture.dispose();
        generator.dispose();
        asteroidDestroyed.dispose();
    }
}