var app = require('express')();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var players = [];
var asteroids_0 = [];
var asteroids_1 = [];
var asteroids_2 = [];
var asteroids_3 = [];
var asteroids_4 = [];
var asteroids_5 = [];
var asteroids_6 = [];
var asteroids_7 = [];
var asteroids_8 = [];
var asteroids_9 = [];

var bullets_0 = [];
var bullets_1 = [];
var bullets_2 = [];
var bullets_3 = [];
var bullets_4 = [];
var bullets_5 = [];
var bullets_6 = [];
var bullets_7 = [];
var bullets_8 = [];
var bullets_9 = [];
var bullets_10 = [];
var bullets_11 = [];
var bullets_12 = [];
var bullets_13 = [];
var bullets_14 = [];
var bullets_15 = [];
var bullets_16 = [];
var bullets_17 = [];
var bullets_18 = [];
var bullets_19 = [];

server.listen(8080, function(){
	console.log("Server is now running...");
});

io.on('connection', function(socket){
	console.log("Player Connected!");
	socket.emit('socketID', {id: socket.id});
	socket.emit('getPlayers', players);

	socket.emit('getAsteroid_0', asteroids_0);
	socket.emit('getAsteroid_1', asteroids_1);
	socket.emit('getAsteroid_2', asteroids_2);
	socket.emit('getAsteroid_3', asteroids_3);
	socket.emit('getAsteroid_4', asteroids_4);
	socket.emit('getAsteroid_5', asteroids_5);
	socket.emit('getAsteroid_6', asteroids_6);
	socket.emit('getAsteroid_7', asteroids_7);
	socket.emit('getAsteroid_8', asteroids_8);
	socket.emit('getAsteroid_9', asteroids_9);

	socket.emit('getBullet_0', bullets_0);
	socket.emit('getBullet_1', bullets_1);
	socket.emit('getBullet_2', bullets_2);
	socket.emit('getBullet_3', bullets_3);
	socket.emit('getBullet_4', bullets_4);
	socket.emit('getBullet_5', bullets_5);
	socket.emit('getBullet_6', bullets_6);
	socket.emit('getBullet_7', bullets_7);
	socket.emit('getBullet_8', bullets_8);
	socket.emit('getBullet_9', bullets_9);
	socket.emit('getBullet_10', bullets_10);
	socket.emit('getBullet_11', bullets_11);
	socket.emit('getBullet_12', bullets_12);
	socket.emit('getBullet_13', bullets_13);
	socket.emit('getBullet_14', bullets_14);
	socket.emit('getBullet_15', bullets_15);
	socket.emit('getBullet_16', bullets_16);
	socket.emit('getBullet_17', bullets_17);
	socket.emit('getBullet_18', bullets_18);
	socket.emit('getBullet_19', bullets_19);
	socket.broadcast.emit('newPlayer', {id: socket.id});
	socket.broadcast.emit('newAsteroid', {id: socket.id});
	socket.broadcast.emit('newBullet', {id: socket.id});
	socket.on('playerMoved', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('playerMoved', data);

	    for(var i = 0; i < players.length; i++){
	        if(players[i].id == data.id){
	            players[i].x = data.x;
	            players[i].y = data.y;
	        }
	    }
	});
	socket.on('asteroidMoved_0', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_0', data);

	    for(var i = 0; i < asteroids_0.length; i++){
	        if(asteroids_0[i].id == data.id){
	            asteroids_0[i].x = data.x;
	            asteroids_0[i].y = data.y;
	        }
	    }
	});
    socket.on('asteroidMoved_1', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_1', data);

	    for(var i = 0; i < asteroids_1.length; i++){
        	if(asteroids_1[i].id == data.id){
        	    asteroids_1[i].x = data.x;
        	    asteroids_1[i].y = data.y;
        	}
        }
	});
    socket.on('asteroidMoved_2', function(data){
	    data.id = socket.id;
        socket.broadcast.emit('asteroidMoved_2', data);

        for(var i = 0; i < asteroids_2.length; i++){
           	if(asteroids_2[i].id == data.id){
            asteroids_2[i].x = data.x;
        	asteroids_2[i].y = data.y;
           	}
        }
	});
    socket.on('asteroidMoved_3', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_3', data);

        for(var i = 0; i < asteroids_3.length; i++){
            if(asteroids_3[i].id == data.id){
                asteroids_3[i].x = data.x;
                asteroids_3[i].y = data.y;
            }
        }
	});
    socket.on('asteroidMoved_4', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_4', data);

        for(var i = 0; i < asteroids_4.length; i++){
            if(asteroids_4[i].id == data.id){
                asteroids_4[i].x = data.x;
                asteroids_4[i].y = data.y;
            }
        }
	});
    socket.on('asteroidMoved_5', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_5', data);

        for(var i = 0; i < asteroids_5.length; i++){
            if(asteroids_5[i].id == data.id){
                asteroids_5[i].x = data.x;
                asteroids_5[i].y = data.y;
            }
        }
	});
    socket.on('asteroidMoved_6', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_6', data);

        for(var i = 0; i < asteroids_6.length; i++){
            if(asteroids_6[i].id == data.id){
                asteroids_6[i].x = data.x;
                asteroids_6[i].y = data.y;
            }
        }
	});
    socket.on('asteroidMoved_7', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_7', data);

        for(var i = 0; i < asteroids_7.length; i++){
            if(asteroids_7[i].id == data.id){
                asteroids_7[i].x = data.x;
                asteroids_7[i].y = data.y;
            }
        }
	});
    socket.on('asteroidMoved_8', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_8', data);

        for(var i = 0; i < asteroids_8.length; i++){
            if(asteroids_8[i].id == data.id){
                asteroids_8[i].x = data.x;
                asteroids_8[i].y = data.y;
            }
        }
	});
socket.on('asteroidMoved_9', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('asteroidMoved_9', data);

        for(var i = 0; i < asteroids_9.length; i++){
            if(asteroids_9[i].id == data.id){
        	    asteroids_9[i].x = data.x;
        	    asteroids_9[i].y = data.y;
        	}
        }
	});
	socket.on('bulletMoved_0', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_0', data);

        for(var i = 0; i < bullets_0.length; i++){
	        if(bullets_0[i].id == data.id){
	            bullets_0[i].x = data.x;
	            bullets_0[i].y = data.y;
	        }
	    }
	});
    socket.on('bulletMoved_1', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_1', data);

	    for(var i = 0; i < bullets_1.length; i++){
	        if(bullets_1[i].id == data.id){
	            bullets_1[i].x = data.x;
	            bullets_1[i].y = data.y;
	        }
	    }
	});
    socket.on('bulletMoved_2', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_2', data);

	    for(var i = 0; i < bullets_2.length; i++){
            if(bullets_2[i].id == data.id){
                bullets_2[i].x = data.x;
                bullets_2[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_3', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_3', data);

        for(var i = 0; i < bullets_3.length; i++){
            if(bullets_3[i].id == data.id){
                bullets_3[i].x = data.x;
                bullets_3[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_4', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_4', data);

        for(var i = 0; i < bullets_4.length; i++){
            if(bullets_4[i].id == data.id){
                bullets_4[i].x = data.x;
                bullets_4[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_5', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_5', data);

        for(var i = 0; i < bullets_5.length; i++){
            if(bullets_5[i].id == data.id){
                bullets_5[i].x = data.x;
                bullets_5[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_6', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_6', data);

        for(var i = 0; i < bullets_6.length; i++){
        	if(bullets_6[i].id == data.id){
                bullets_6[i].x = data.x;
                bullets_6[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_7', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_7', data);

        for(var i = 0; i < bullets_7.length; i++){
            if(bullets_7[i].id == data.id){
                bullets_7[i].x = data.x;
                bullets_7[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_8', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_8', data);

        for(var i = 0; i < bullets_8.length; i++){
            if(bullets_8[i].id == data.id){
                bullets_8[i].x = data.x;
                bullets_8[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_9', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_9', data);

        for(var i = 0; i < bullets_9.length; i++){
            if(bullets_9[i].id == data.id){
                bullets_9[i].x = data.x;
                bullets_9[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_10', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_10', data);

        for(var i = 0; i < bullets_10.length; i++){
            if(bullets_10[i].id == data.id){
                bullets_10[i].x = data.x;
                bullets_10[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_11', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_11', data);

        for(var i = 0; i < bullets_11.length; i++){
            if(bullets_11[i].id == data.id){
                bullets_11[i].x = data.x;
                bullets_11[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_12', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_12', data);

        for(var i = 0; i < bullets_12.length; i++){
            if(bullets_12[i].id == data.id){
                bullets_12[i].x = data.x;
                bullets_12[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_13', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_13', data);

        for(var i = 0; i < bullets_0.length; i++){
            if(bullets_13[i].id == data.id){
                bullets_13[i].x = data.x;
                bullets_13[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_14', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_14', data);

        for(var i = 0; i < bullets_14.length; i++){
            if(bullets_14[i].id == data.id){
                bullets_14[i].x = data.x;
                bullets_14[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_15', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_15', data);

        for(var i = 0; i < bullets_15.length; i++){
            if(bullets_15[i].id == data.id){
                bullets_15[i].x = data.x;
                bullets_15[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_16', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_16', data);

        for(var i = 0; i < bullets_16.length; i++){
            if(bullets_16[i].id == data.id){
                bullets_16[i].x = data.x;
                bullets_16[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_17', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_17', data);

        for(var i = 0; i < bullets_17.length; i++){
        	if(bullets_17[i].id == data.id){
        	    bullets_17[i].x = data.x;
                bullets_17[i].y = data.y;
            }
        }
	});
    socket.on('bulletMoved_18', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_18', data);

        for(var i = 0; i < bullets_18.length; i++){
            if(bullets_18[i].id == data.id){
                bullets_18[i].x = data.x;
                bullets_18[i].y = data.y;
            }
        }
	});
socket.on('bulletMoved_19', function(data){
	    data.id = socket.id;
	    socket.broadcast.emit('bulletMoved_19', data);

        for(var i = 0; i < bullets_19.length; i++){
            if(bullets_19[i].id == data.id){
        	    bullets_19[i].x = data.x;
        	    bullets_19[i].y = data.y;
            }
        }
	});

	socket.on('disconnect', function(){
		console.log("Player Disconnected");
		socket.broadcast.emit('playerDisconnected', {id: socket.id});
		for (var i = 0; i < players.length; i++){
		    if(players[i].id == socket.id){
		        players.splice(i, 1);
		    }
		}
		for (var i = 0; i < asteroids_0.length; i++){
		    if(asteroids_0[i].id == socket.id){
		        asteroids_0.splice(i,1);
		    }
		}
	    for (var i = 0; i < asteroids_1.length; i++){
    		if(asteroids_1[i].id == socket.id){
    		    asteroids_1.splice(i,1);
    		}
    	}
        for (var i = 0; i < asteroids_2.length; i++){
		    if(asteroids_2[i].id == socket.id){
		        asteroids_2.splice(i,1);
		    }
		}
        for (var i = 0; i < asteroids_3.length; i++){
		    if(asteroids_3[i].id == socket.id){
		        asteroids_3.splice(i,1);
		    }
		}
        for (var i = 0; i < asteroids_4.length; i++){
		    if(asteroids_4[i].id == socket.id){
		        asteroids_4.splice(i,1);
		    }
		}
        for (var i = 0; i < asteroids_5.length; i++){
		    if(asteroids_5[i].id == socket.id){
		        asteroids_5.splice(i,1);
		    }
		}
        for (var i = 0; i < asteroids_6.length; i++){
		    if(asteroids_6[i].id == socket.id){
		        asteroids_6.splice(i,1);
		    }
		}
        for (var i = 0; i < asteroids_7.length; i++){
		    if(asteroids_7[i].id == socket.id){
		        asteroids_7.splice(i,1);
		    }
		}
        for (var i = 0; i < asteroids_8.length; i++){
		    if(asteroids_8[i].id == socket.id){
		        asteroids_8.splice(i,1);
		    }
		}
        for (var i = 0; i < asteroids_9.length; i++){
		    if(asteroids_9[i].id == socket.id){
		        asteroids_9.splice(i,1);
		    }
		}
	});
	players.push(new player(socket.id, 0, 0));

    asteroids_0.push(new asteroid_0(socket.id, 0, 0));
	asteroids_1.push(new asteroid_1(socket.id, 0, 0));
	asteroids_2.push(new asteroid_2(socket.id, 0, 0));
	asteroids_3.push(new asteroid_3(socket.id, 0, 0));
	asteroids_4.push(new asteroid_4(socket.id, 0, 0));
	asteroids_5.push(new asteroid_5(socket.id, 0, 0));
	asteroids_6.push(new asteroid_6(socket.id, 0, 0));
	asteroids_7.push(new asteroid_7(socket.id, 0, 0));
	asteroids_8.push(new asteroid_8(socket.id, 0, 0));
	asteroids_9.push(new asteroid_9(socket.id, 0, 0));

	bullets_0.push(new bullet_0(socket.id, -60, -60));
	bullets_1.push(new bullet_1(socket.id, -60, -60));
	bullets_2.push(new bullet_2(socket.id, -60, -60));
	bullets_3.push(new bullet_3(socket.id, -60, -60));
	bullets_4.push(new bullet_4(socket.id, -60, -60));
	bullets_5.push(new bullet_5(socket.id, -60, -60));
	bullets_6.push(new bullet_6(socket.id, -60, -60));
	bullets_7.push(new bullet_7(socket.id, -60, -60));
	bullets_8.push(new bullet_8(socket.id, -60, -60));
	bullets_9.push(new bullet_9(socket.id, -60, -60));
	bullets_10.push(new bullet_10(socket.id, -60, -60));
	bullets_11.push(new bullet_11(socket.id, -60, -60));
	bullets_12.push(new bullet_12(socket.id, -60, -60));
	bullets_13.push(new bullet_13(socket.id, -60, -60));
	bullets_14.push(new bullet_14(socket.id, -60, -60));
	bullets_15.push(new bullet_15(socket.id, -60, -60));
	bullets_16.push(new bullet_16(socket.id, -60, -60));
	bullets_17.push(new bullet_17(socket.id, -60, -60));
	bullets_18.push(new bullet_18(socket.id, -60, -60));
	bullets_19.push(new bullet_19(socket.id, -60, -60));
});

function player(id, x, y){
    this.id = id;
    this.playerPos_x = x;//отслеживаеться playerPos_x!!!!
    this.playerPos_y = y;//отслеживаеться playerPos_y!!!!
}

function asteroid_0(id, x, y){
    this.id = id;
    this.asteroidPos_x_0 = x;
    this.asteroidPos_y_0 = y;
}
function asteroid_1(id, x, y){
    this.id = id;
    this.asteroidPos_x_1 = x;
    this.asteroidPos_y_1 = y;
}
function asteroid_2(id, x, y){
    this.id = id;
    this.asteroidPos_x_2 = x;
    this.asteroidPos_y_2 = y;
}
function asteroid_3(id, x, y){
    this.id = id;
    this.asteroidPos_x_3 = x;
    this.asteroidPos_y_3 = y;
}
function asteroid_4(id, x, y){
    this.id = id;
    this.asteroidPos_x_4 = x;
    this.asteroidPos_y_4 = y;
}
function asteroid_5(id, x, y){
    this.id = id;
    this.asteroidPos_x_5 = x;
    this.asteroidPos_y_5 = y;
}
function asteroid_6(id, x, y){
    this.id = id;
    this.asteroidPos_x_6 = x;
    this.asteroidPos_y_6 = y;
}
function asteroid_7(id, x, y){
    this.id = id;
    this.asteroidPos_x_7 = x;
    this.asteroidPos_y_7 = y;
}
function asteroid_8(id, x, y){
    this.id = id;
    this.asteroidPos_x_8 = x;
    this.asteroidPos_y_8 = y;
}
function asteroid_9(id, x, y){
    this.id = id;
    this.asteroidPos_x_9 = x;
    this.asteroidPos_y_9 = y;
}
function asteroid_10(id, x, y){
    this.id = id;
    this.asteroidPos_x_10 = x;
    this.asteroidPos_y_10 = y;
}

function bullet_0(id, x, y){
    this.id = id;
    this.bulletPos_x_0 = x;
    this.bulletPos_y_0 = y;
}
function bullet_1(id, x, y){
    this.id = id;
    this.bulletPos_x_1 = x;
    this.bulletPos_y_1 = y;
}
function bullet_2(id, x, y){
    this.id = id;
    this.bulletPos_x_2 = x;
    this.bulletPos_y_2 = y;
}
function bullet_3(id, x, y){
    this.id = id;
    this.bulletPos_x_3 = x;
    this.bulletPos_y_3 = y;
}
function bullet_4(id, x, y){
    this.id = id;
    this.bulletPos_x_4 = x;
    this.bulletPos_y_4 = y;
}
function bullet_5(id, x, y){
    this.id = id;
    this.bulletPos_x_5 = x;
    this.bulletPos_y_5 = y;
}
function bullet_6(id, x, y){
    this.id = id;
    this.bulletPos_x_6 = x;
    this.bulletPos_y_6 = y;
}
function bullet_7(id, x, y){
    this.id = id;
    this.bulletPos_x_7 = x;
    this.bulletPos_y_7 = y;
}
function bullet_8(id, x, y){
    this.id = id;
    this.bulletPos_x_8 = x;
    this.bulletPos_y_8 = y;
}
function bullet_9(id, x, y){
    this.id = id;
    this.bulletPos_x_9 = x;
    this.bulletPos_y_9 = y;
}
function bullet_10(id, x, y){
    this.id = id;
    this.bulletPos_x_10 = x;
    this.bulletPos_y_10 = y;
}
function bullet_11(id, x, y){
    this.id = id;
    this.bulletPos_x_11 = x;
    this.bulletPos_y_11 = y;
}
function bullet_12(id, x, y){
    this.id = id;
    this.bulletPos_x_12 = x;
    this.bulletPos_y_12 = y;
}
function bullet_13(id, x, y){
    this.id = id;
    this.bulletPos_x_13 = x;
    this.bulletPos_y_13 = y;
}
function bullet_14(id, x, y){
    this.id = id;
    this.bulletPos_x_14 = x;
    this.bulletPos_y_14 = y;
}
function bullet_15(id, x, y){
    this.id = id;
    this.bulletPos_x_15 = x;
    this.bulletPos_y_15 = y;
}
function bullet_16(id, x, y){
    this.id = id;
    this.bulletPos_x_16 = x;
    this.bulletPos_y_16 = y;
}
function bullet_17(id, x, y){
    this.id = id;
    this.bulletPos_x_17 = x;
    this.bulletPos_y_17 = y;
}
function bullet_18(id, x, y){
    this.id = id;
    this.bulletPos_x_18 = x;
    this.bulletPos_y_18 = y;
}
function bullet_19(id, x, y){
    this.id = id;
    this.bulletPos_x_19 = x;
    this.bulletPos_y_19 = y;
}