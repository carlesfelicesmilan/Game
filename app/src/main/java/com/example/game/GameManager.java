package com.example.game;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game.sprites.Battle.Battle;
import com.example.game.sprites.Living.Hero.Hero;
import com.example.game.sprites.Screens.MainScreen;
import com.example.game.sprites.Obstacle;
import com.example.game.sprites.GameOver;
import com.example.game.sprites.Screens.StatusScreen;

import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class GameManager extends SurfaceView implements SurfaceHolder.Callback, GameManagerCallback {

    private static final String APP_NAME = "Rpg Game";
    public MainThread thread;
    private GameState gameState = GameState.INITIAL;

    //private Bird bird;
    // Used to get the screen size dimensions
    private DisplayMetrics dm;
    //private ObstacleManager obstacleManager;
    private GameOver gameOver;
    // Actual score
    private int score;
    // Map of obstacle and each obstacle have two rectangles
    //private HashMap<Obstacle, List<Rect>> obstaclePositions = new HashMap<>();

    private MainScreen mainScreen;
    private StatusScreen statusScreen;

    private Battle battle;

    //private Dragon dragon;
    private Hero hero;

    public GameManager(Context context, AttributeSet attributeSet) {
        super(context);
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
        //score = 0;
        gameOver = new GameOver(getResources(), dm.heightPixels, dm.widthPixels);
        //dragon = new Dragon("Dragon", 150, 20, 5, 30, 20);
        hero = new Hero("Steyn", 200, 50, 7, 50, 0);
        mainScreen = new MainScreen(getResources(), getContext(), dm.heightPixels, dm.widthPixels);
        statusScreen = new StatusScreen(getResources(), getContext(), dm.heightPixels, dm.widthPixels);
        //battle = new Battle(getResources(), getContext(), dm.heightPixels, dm.widthPixels, hero);
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
            case GAME_OVER:
                break;
            case BATTLE:
                battle.update();
                if(battle.getBattleState() == "WIN") {
                    hero.receiveMoney(battle.getMoney());
                    hero.receiveExp(battle.getExp());
                    gameState = GameState.INITIAL;
                    break;
                }
                break;
        }
    }

    //Draw the bird inside the canvas
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawRGB(0,0,0);
            switch (gameState) {
                case INITIAL:
                    mainScreen.draw(canvas);
                    hero.updateMoney(getContext().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE));
                    hero.updateExp(getContext().getSharedPreferences(APP_NAME, Context.MODE_PRIVATE));
                    break;
                case STATUS:
                    statusScreen.updateStatus(hero.attack, hero.defense, hero.experience, hero.money);
                    statusScreen.draw(canvas);
                    break;
                case PLAYING:
                    break;
                case BATTLE:
                    battle.draw(canvas);
                    break;
                /*case WIN:
                    victory.draw();
                    break;

                 */
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
                if (mainScreen.isOptionsClicked(evX, evY)){
                    gameState = GameState.STATUS;
                    break;
                }
                else if(mainScreen.isnewGameClicked(evX,evY)) {
                    battle = new Battle(getResources(), getContext(), dm.heightPixels, dm.widthPixels, hero);
                    gameState = GameState.BATTLE;
                    break;
                }

                else if (mainScreen.isExitClicked(evX, evY)) {
                    gameState = GameState.EXIT;
                }
                break;
            case STATUS:
                if(statusScreen.isBackClicked(evX, evY)) {
                    gameState = GameState.INITIAL;
                    break;
                }
            case PLAYING:
                break;
            case BATTLE:
                battle.isButtonPressed(evX,evY);
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
