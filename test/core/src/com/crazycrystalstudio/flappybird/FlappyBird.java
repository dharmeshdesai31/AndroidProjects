package com.crazycrystalstudio.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Preferences;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
    //ShapeRenderer shapeRender;
    BitmapFont font;

    Texture gameOver;

    Texture[] birds;
    //Bird stuff
    int flapState = 0;
    int birdY = 0;
    float velocity = 0;
    Circle birdCircle;
    int score = 0;
    int highScore = 0;
    Preferences prefs;
    int scoringTube=0;

    int gameState = 0;

    Texture topTube;
    Texture bottomTube;

    float gap = 400;
    float maxTubeOffset = 0;

    Random randomGenerator;


    float tubeVelocity = 4;


    int numberOfTubes = 4;
    float tubeX[] = new float[numberOfTubes];
    float tubeOffset[] = new float[numberOfTubes];
    float distanceBetweenTubes;

    Rectangle topTubeRectangles[];
    Rectangle bottomTubeRectangles[];

	@Override
	public void create () {
		batch = new SpriteBatch();
        //shapeRender = new ShapeRenderer();
        birdCircle =  new Circle();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().scale(10);

        //game over
        gameOver = new Texture("gameover2.png");
        prefs = Gdx.app.getPreferences("FlappyBirdMain");

        background = new Texture("bg.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");


        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        maxTubeOffset = Gdx.graphics.getHeight()/2 - gap/2 - 100;
        randomGenerator = new Random();
        distanceBetweenTubes = Gdx.graphics.getWidth() * 3/4;
        topTubeRectangles = new Rectangle[numberOfTubes];
        bottomTubeRectangles = new Rectangle[numberOfTubes];

        startGame();
	}

	public void startGame(){
        birdY = Gdx.graphics.getHeight()/2 - birds[flapState].getHeight()/2;

        for(int i=0; i<numberOfTubes; i++){

            tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 800);

            tubeX[i] = Gdx.graphics.getWidth()/2 - topTube.getWidth()/2 + (i+1)*distanceBetweenTubes;

            topTubeRectangles[i] = new Rectangle();
            bottomTubeRectangles[i] = new Rectangle();
        }
        //tubeX = Gdx.graphics.getWidth()/2 - topTube.getWidth()/2;
    }

    public void saveHighScore(){
        prefs.putInteger("HighScore", score);
        prefs.flush();
    }

	@Override
	public void render () {

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if(gameState == 1) {

            if(tubeX[scoringTube] < Gdx.graphics.getWidth()/2){
                score++;

                Gdx.app.log("Score", String.valueOf(score));

                if(scoringTube < numberOfTubes-1)
                    scoringTube++;
                else
                    scoringTube = 0;
            }

            if(Gdx.input.justTouched()){
                velocity -=30;

            }

            for(int i=0; i<numberOfTubes; i++) {
                if(tubeX[i] < -topTube.getWidth()){
                    tubeX[i] += numberOfTubes * distanceBetweenTubes;
                    tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 800);
                }
                else
                tubeX[i] = tubeX[i] - tubeVelocity;

                batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
                batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);

                topTubeRectangles[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i], topTube.getWidth(), topTube.getHeight());
                bottomTubeRectangles[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());
            }

            if(birdY > 0) {
                velocity++;
                birdY -= velocity;
            }else{
                gameState = 2;

                highScore = prefs.getInteger("HighScore", 0);
                if(highScore < score)
                    saveHighScore();

            }

        }else if (gameState == 0){

            if(Gdx.input.justTouched()){
                gameState = 1;
            }
        }else if (gameState == 2){
            batch.draw(gameOver, Gdx.graphics.getWidth()/2 - gameOver.getWidth()/2, Gdx.graphics.getHeight()/2 - gameOver.getHeight()/2);
            highScore = prefs.getInteger("HighScore", 0);
            font.draw(batch, "High Score: "+String.valueOf(highScore), 100, 500);
            if(Gdx.input.justTouched()){
                gameState = 1;
                startGame();
                score = 0;
                scoringTube = 0;
                velocity =0;
            }
        }

        if (flapState == 0)
            flapState = 1;
        else
            flapState = 0;


        batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);

        font.draw(batch, String.valueOf(score), 100, 200);
        batch.end();

        birdCircle.set(Gdx.graphics.getWidth()/2, birdY + birds[flapState].getWidth()/2, birds[flapState].getWidth()/2);

        /*shapeRender.begin(ShapeRenderer.ShapeType.Filled);
        shapeRender.setColor(Color.RED);
        shapeRender.circle(birdCircle.x, birdCircle.y, birdCircle.radius);
*/
        for(int i=0; i<numberOfTubes; i++){
            //shapeRender.rect(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i], topTube.getWidth(), topTube.getHeight());
            //shapeRender.rect(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());

            if(Intersector.overlaps(birdCircle, topTubeRectangles[i]) || (Intersector.overlaps(birdCircle, bottomTubeRectangles[i]))){
                Gdx.app.log("Collision", "Yes!");
                highScore = prefs.getInteger("HighScore");
                if(highScore < score)
                    saveHighScore();
                gameState = 2;

            }
        }
        //shapeRender.end();
    }

}
