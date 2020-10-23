package com.mygdx.space;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class Space extends ApplicationAdapter {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Space";

	private float timer;

	private SpriteBatch batch;
	private Socket socket;

	private Background background;
	private MainMenuScreen mainScreen;
	private Help help;
	private SpaceScreen spaceScreen;
	private void booleanSwitch(){
		if(mainScreen.isMainActive()){
			Gdx.input.setInputProcessor(mainScreen.getMenuStage());
		}
		if(!mainScreen.isMainActive()){
			spaceScreen.setStageActive(true);
		}
		if(!spaceScreen.isStageActive()){
			mainScreen.setMainActive(true);
		}
		if(mainScreen.isHelpActive()){
			help.setHelpActive(true);
			if(Gdx.input.isTouched()){
				help.setHelpActive(false);
				mainScreen.setHelpActive(false);
			}
		}
	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Background();
		mainScreen = new MainMenuScreen();
		help = new Help();
		spaceScreen = new SpaceScreen();

		connectSocket();
		configSocketEvents();
	}
	@Override
	public void render () {
		update();
		updateServer(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		background.render(batch);
		if(mainScreen.isMainActive() && !mainScreen.isHelpActive()){
			mainScreen.render(batch);
		}
		if(help.isHelpActive() && mainScreen.isMainActive()){
			help.render(batch);
		}
		if(!mainScreen.isMainActive() && spaceScreen.isStageActive()){
			spaceScreen.render(batch);
		}
		batch.end();
		if(mainScreen.isMainActive() && !spaceScreen.isStageActive() && !mainScreen.isHelpActive()){
			mainScreen.stage();
		}
	}
	private void update(){
		background.update();
		booleanSwitch();
		help.update();
		spaceScreen.update();
	}
	private void updateServer(float dt){
		float UPDATE_TIME = 1 / 60f;
		spaceScreen.updatePlayer(dt, timer, UPDATE_TIME, socket);
		spaceScreen.updateAsteroids_0(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_1(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_2(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_3(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_4(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_5(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_6(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_7(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_8(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateAsteroids_9(dt, timer, UPDATE_TIME, socket);

		spaceScreen.updateBullets_0(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_1(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_2(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_3(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_4(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_5(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_6(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_7(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_8(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_9(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_10(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_11(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_12(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_13(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_14(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_15(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_16(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_17(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_18(dt, timer, UPDATE_TIME, socket);
        spaceScreen.updateBullets_19(dt, timer, UPDATE_TIME, socket);
	}
	private void connectSocket(){
		try{
			socket = IO.socket("http://localhost:8080");
			socket.connect();
		}catch (Exception e){
			System.out.println(e);
		}
	}
	//для событий связанных с сокетами
	private void configSocketEvents(){
		socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				Gdx.app.log("SocketIO", "Connected");
				spaceScreen.firstPlayer();
			}
		}).on("socketID", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					String id = data.getString("id");
					Gdx.app.log("SocketIO", "My ID: " + id);
				}catch (JSONException e){
					Gdx.app.log("SocketIO", "Error getting id");
				}
			}
		}).on("newPlayer", new Emitter.Listener() {
			@Override
			public void call(Object... args) {//отображение для первого игрока
				JSONObject data = (JSONObject) args[0];
				try {
					String playerID = data.getString("id");
					Gdx.app.log("SocketIO", "New Player ID: " + playerID);
					spaceScreen.secondPlayer(playerID);
				}catch (JSONException e){
					Gdx.app.log("SocketIO", "Error getting newPlayer1 id");
				}
			}
		}).on("getPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {// отображение для второго игрока
                JSONArray objects = (JSONArray) args[0];
                spaceScreen.coopPlayers(objects);
            }
        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.playerMoved(data);
            }
        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    spaceScreen.friendlyPlayers.remove(id);

                    spaceScreen.anotherAsteroids_0.remove(id);
					spaceScreen.anotherAsteroids_1.remove(id);
					spaceScreen.anotherAsteroids_2.remove(id);
					spaceScreen.anotherAsteroids_3.remove(id);
					spaceScreen.anotherAsteroids_4.remove(id);
					spaceScreen.anotherAsteroids_5.remove(id);
					spaceScreen.anotherAsteroids_6.remove(id);
					spaceScreen.anotherAsteroids_7.remove(id);
					spaceScreen.anotherAsteroids_8.remove(id);
					spaceScreen.anotherAsteroids_9.remove(id);

                    spaceScreen.friendlyBullets_0.remove(id);
					spaceScreen.friendlyBullets_1.remove(id);
					spaceScreen.friendlyBullets_2.remove(id);
					spaceScreen.friendlyBullets_3.remove(id);
					spaceScreen.friendlyBullets_4.remove(id);
					spaceScreen.friendlyBullets_5.remove(id);
					spaceScreen.friendlyBullets_6.remove(id);
					spaceScreen.friendlyBullets_7.remove(id);
					spaceScreen.friendlyBullets_8.remove(id);
					spaceScreen.friendlyBullets_9.remove(id);
					spaceScreen.friendlyBullets_10.remove(id);
					spaceScreen.friendlyBullets_11.remove(id);
					spaceScreen.friendlyBullets_12.remove(id);
					spaceScreen.friendlyBullets_13.remove(id);
					spaceScreen.friendlyBullets_14.remove(id);
					spaceScreen.friendlyBullets_15.remove(id);
					spaceScreen.friendlyBullets_16.remove(id);
					spaceScreen.friendlyBullets_17.remove(id);
					spaceScreen.friendlyBullets_18.remove(id);
					spaceScreen.friendlyBullets_19.remove(id);
                }catch (JSONException e){
                    Gdx.app.log("SocketIO", "Error getting newPlayer11 id");
                }
            }
        }).on("newAsteroid", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					String playerID = data.getString("id");
					Gdx.app.log("SocketIO", "New Player ID: " + playerID);
					spaceScreen.differentAsteroid(playerID);
				}catch (JSONException e){
					Gdx.app.log("SocketIO", "Error getting newPlayer2 id");
				}
			}
		}).on("asteroidMoved_0", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_0(data);
            }
        }).on("asteroidMoved_1", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_1(data);
            }
        }).on("asteroidMoved_2", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_2(data);
            }
        }).on("asteroidMoved_3", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_3(data);
            }
        }).on("asteroidMoved_4", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_4(data);
            }
        }).on("asteroidMoved_5", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_5(data);
            }
        }).on("asteroidMoved_6", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_6(data);
            }
        }).on("asteroidMoved_7", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_7(data);
            }
        }).on("asteroidMoved_8", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_8(data);
            }
        }).on("asteroidMoved_9", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.asteroidMoved_9(data);
            }
        }).on("getAsteroid_0", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_0(objects);
			}
		}).on("getAsteroid_1", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_1(objects);
			}
		}).on("getAsteroid_2", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_2(objects);
			}
		}).on("getAsteroid_3", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_3(objects);
			}
		}).on("getAsteroid_4", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_4(objects);
			}
		}).on("getAsteroid_5", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_5(objects);
			}
		}).on("getAsteroid_6", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_6(objects);
			}
		}).on("getAsteroid_7", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_7(objects);
			}
		}).on("getAsteroid_8", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_8(objects);
			}
		}).on("getAsteroid_9", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopDiffAsteroid_9(objects);
			}
		}).on("newBullet", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				try {
					String playerID = data.getString("id");
					Gdx.app.log("SocketIO", "New Player ID: " + playerID);
					spaceScreen.secondPlayerBullet(playerID);
				}catch (JSONException e){
					Gdx.app.log("SocketIO", "Error getting newPlayer3 id");
				}
			}
		}).on("bulletMoved_0", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONObject data = (JSONObject) args[0];
				spaceScreen.bulletMoved_0(data);
			}
		}).on("bulletMoved_1", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_1(data);
            }
        }).on("bulletMoved_2", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_2(data);
            }
        }).on("bulletMoved_3", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_3(data);
            }
        }).on("bulletMoved_4", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_4(data);
            }
        }).on("bulletMoved_5", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_5(data);
            }
        }).on("bulletMoved_6", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_6(data);
            }
        }).on("bulletMoved_7", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_7(data);
            }
        }).on("bulletMoved_8", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_8(data);
            }
        }).on("bulletMoved_9", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_9(data);
            }
        }).on("bulletMoved_10", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_10(data);
            }
        }).on("bulletMoved_11", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_11(data);
            }
        }).on("bulletMoved_12", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_12(data);
            }
        }).on("bulletMoved_13", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_13(data);
            }
        }).on("bulletMoved_14", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_14(data);
            }
        }).on("bulletMoved_15", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_15(data);
            }
        }).on("bulletMoved_16", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_16(data);
            }
        }).on("bulletMoved_17", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_17(data);
            }
        }).on("bulletMoved_18", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_18(data);
            }
        }).on("bulletMoved_19", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                spaceScreen.bulletMoved_19(data);
            }
        }).on("getBullet_0", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_0(objects);
			}
		}).on("getBullet_1", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_1(objects);
			}
		}).on("getBullet_2", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_2(objects);
			}
		}).on("getBullet_3", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_3(objects);
			}
		}).on("getBullet_4", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_4(objects);
			}
		}).on("getBullet_5", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_5(objects);
			}
		}).on("getBullet_6", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_6(objects);
			}
		}).on("getBullet_7", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_7(objects);
			}
		}).on("getBullet_8", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_8(objects);
			}
		}).on("getBullet_9", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_9(objects);
			}
		}).on("getBullet_10", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_10(objects);
			}
		}).on("getBullet_11", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_11(objects);
			}
		}).on("getBullet_12", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_12(objects);
			}
		}).on("getBullet_13", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_13(objects);
			}
		}).on("getBullet_14", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_14(objects);
			}
		}).on("getBullet_15", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_15(objects);
			}
		}).on("getBullet_16", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_16(objects);
			}
		}).on("getBullet_17", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_17(objects);
			}
		}).on("getBullet_18", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_18(objects);
			}
		}).on("getBullet_19", new Emitter.Listener() {
			@Override
			public void call(Object... args) {
				JSONArray objects = (JSONArray) args[0];
				spaceScreen.coopPlayersBullet_19(objects);
			}
		});
	}
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		background.dispose();
		help.dispose();
		mainScreen.dispose();
		spaceScreen.dispose();
	}
}