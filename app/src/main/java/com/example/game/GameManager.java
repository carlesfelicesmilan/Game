package com.example.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game.sprites.Background.Background;
import com.example.game.sprites.Battle.BattleMenu;
import com.example.game.sprites.Battle.NewBattleMenu;
import com.example.game.sprites.Character.Hero;
import com.example.game.sprites.Character.newHero;
import com.example.game.sprites.Enemies.Dragon;
import com.example.game.sprites.Enemies.Orc;
import com.example.game.sprites.Obstacle;
import com.example.game.sprites.buttons.BackOptionsButton;
import com.example.game.sprites.buttons.ExitButton;
import com.example.game.sprites.buttons.OptionsButton;
import com.example.game.sprites.buttons.StartButton;
import com.example.game.sprites.GameOver;
import com.example.game.sprites.Score;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class GameManager extends SurfaceView implements SurfaceHolder.Callback, GameManagerCallback {

    private static final String APP_NAME = "Rpg Game";
    public MainThread thread;
    private GameState gameState = GameState.INITIAL;

    //private Bird bird;
    private Background background;
    // Used to get the screen size dimensions
    private DisplayMetrics dm;
    //private ObstacleManager obstacleManager;
    private GameOver gameOver;
    private Score scoreSprite;
    // Actual score
    private int score;
    //private Rect birdPosition;
    // Map of obstacle and each obstacle have two rectangles
    //private HashMap<Obstacle, List<Rect>> obstaclePositions = new HashMap<>();

    private OptionsButton optionsButton;
    private BackOptionsButton backOptionsButton;
    private StartButton startButton;
    private ExitButton exitButton;

    private NewBattleMenu battleMenu2;

    private Dragon dragon;
    private newHero hero;

    //private Enemy coin;

    //private Hero hero;
    private Orc orc;

    private BattleMenu battle;

    //private Rect coinPosition;

    // mpPint: Sound for the point (obstacle removed)
    // mpSwoosh: Sound for the beggining of the game
    private MediaPlayer mpPoint;
    private MediaPlayer mpSwoosh;
    private MediaPlayer mpDie;
    private MediaPlayer mpHit;
    private MediaPlayer mpWing;
    private MediaPlayer mpOptionOn;
    private MediaPlayer mpOptionOff;
    private MediaPlayer start;
    private MediaPlayer orcDamaged;
    private MediaPlayer battleMusic;
    private MediaPlayer clinc;

    public GameManager(Context context, AttributeSet attributeSet) {
        super(context);
        initSounds();
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        dm = new DisplayMetrics();
        // This context comes from main activity. Main activity initiates the view and the view opens the game manager
        // We need to convert the context into an Activity so we can get the window manager
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);

        initGame();
    }

    // Initiate the game and create the bird
    // We need to restart bird position and obstacle position everytime that we start a new game
    private void initGame() {
        score = 0;
        //coinPosition = new Rect();
        background = new Background(getResources(), dm.heightPixels, dm.widthPixels);
        gameOver = new GameOver(getResources(), dm.heightPixels, dm.widthPixels);
        startButton = new StartButton(getResources(), dm.heightPixels, dm.widthPixels);
        exitButton = new ExitButton(getResources(), dm.heightPixels, dm.widthPixels);
        optionsButton = new OptionsButton(getResources(), getContext(), dm.heightPixels, dm.widthPixels);
        backOptionsButton = new BackOptionsButton(getResources(), getContext(), dm.heightPixels, dm.widthPixels);
        scoreSprite = new Score(getResources(), dm.heightPixels, dm.widthPixels);
        //coin = new Enemy(getResources(), dm.heightPixels, dm.widthPixels, this);
        orc = new Orc(getResources(), dm.heightPixels, dm.widthPixels);
        battle = new BattleMenu(getResources(), getContext(), dm.heightPixels, dm.widthPixels);
        dragon = new Dragon("Dragon", 150, 20, 5);
        hero = new newHero("Steyn", 200, 10, 7);
        battleMenu2 = new NewBattleMenu(getResources(), getContext(), dm.heightPixels, dm.widthPixels);
    }

    private void initSounds() {
        //mpPoint = MediaPlayer.create(getContext(), R.raw.coin);
        //mpSwoosh = MediaPlayer.create(getContext(), R.raw.swoosh);
        //mpDie = MediaPlayer.create(getContext(), R.raw.die);
        //mpHit = MediaPlayer.create(getContext(), R.raw.hit);
        //mpWing = MediaPlayer.create(getContext(), R.raw.wing);
        orcDamaged = MediaPlayer.create(getContext(), R.raw.sword);
        //battleMusic = MediaPlayer.create(getContext(), R.raw.battle2);
        clinc = MediaPlayer.create(getContext(), R.raw.clinc);
        //mpOptionOn = MediaPlayer.create(getContext(), R.raw.optionson);
        //mpOptionOff = MediaPlayer.create(getContext(), R.raw.optionsoff);
        //start = MediaPlayer.create(getContext(), R.raw.start);
    }

    //Start the thread
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(thread.getState() == Thread.State.TERMINATED) {
            thread = new MainThread(holder, this);
        }
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    //We can't longer update our UI we stop the background thread from running
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update(){
        switch (gameState) {
            case PLAYING:
                //orc.update();
                //hero.update();
                //battle.update();
                battleMenu2.update(hero, dragon);
                break;
            case GAME_OVER:
                break;
        }
    }

    //Draw the bird inside the canvas
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawRGB(211,211,211);
            switch (gameState) {
                case INITIAL:
                    optionsButton.draw(canvas);
                    startButton.draw(canvas);
                    exitButton.draw(canvas);
                    break;
                case OPTIONS:
                    backOptionsButton.draw(canvas);
                    break;
                case PLAYING:
                    //battle.draw(canvas);
                    battleMenu2.draw(canvas);
                    break;
                case BATTLE:
                    backOptionsButton.draw(canvas);
                    break;
                case GAME_OVER:
                    gameOver.draw(canvas);
                    break;
                case EXIT:
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    break;
            }
        }
    }

    // In initial event we want the bird start flying when user clicks on the screen so we call onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = event.getAction();
        final int evX = (int) event.getX();
        final int evY = (int) event.getY();

        switch (gameState) {
            case INITIAL:
                if (optionsButton.isClicked(evX, evY)) {
                    gameState = GameState.OPTIONS;
                }
                else if (startButton.isClicked(evX, evY)){
                    gameState = GameState.PLAYING;
                }
                else if (exitButton.isClicked(evX, evY)) {
                    gameState = GameState.EXIT;
                }
                break;
            case OPTIONS:
                if (backOptionsButton.isClicked(evX, evY)) {
                    gameState = GameState.INITIAL;
                }
                break;
            case PLAYING:
                //battle.buttonClicked(evX,evY);
                battleMenu2.isButtonPressed(evX,evY);
                break;

            case BATTLE:
                if (backOptionsButton.isClicked(evX, evY)) {
                    gameState = GameState.INITIAL;
                }
                break;
            case GAME_OVER:
                initGame();
                gameState = GameState.INITIAL;
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void updatePosition(Rect birdPosition) {

    }

    @Override
    public void updatePosition(Obstacle obstacle, ArrayList<Rect> positions) {

    }

    @Override
    public void removeObstacle(Obstacle obstacle) {

    }

  /*  @Override
    public void updateCoinPOisition(Rect coinPosition) {
        this.coinPosition = coinPosition;
    }
*/
}
