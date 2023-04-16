package com.team13.piazzapanic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class HUD implements Disposable {
    public Stage stage;
    private Boolean scenarioComplete;

    public Integer getScore() {
        return score;
    }

    private Integer score;

    public String timeString;

    public Table table;

    Label timeLabelT;
    Label timeLabel;

    Label scoreLabel;
    Label scoreLabelT;
    Label orderNumL;
    Label orderNumLT;

    public HUD(SpriteBatch sb){
        this.scenarioComplete = Boolean.FALSE;
        score = 0;
        this.timeString = "00:00";
        float fontX = 0.5F;
        float fontY = 0.3F;

        BitmapFont font = new BitmapFont();
        font.getData().setScale(fontX, fontY);
        Viewport viewport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        table = new Table();
        table.left().top();
        table.setFillParent(true);

        timeLabel = new Label(String.format(this.timeString), new Label.LabelStyle(font, Color.WHITE));
        timeLabelT = new Label("TIME", new Label.LabelStyle(font, Color.BLACK));
        orderNumLT = new Label("ORDER", new Label.LabelStyle(font, Color.BLACK));
        orderNumL = new Label(String.format("%d", 0), new Label.LabelStyle(font, Color.WHITE));

        scoreLabel = new Label(String.format("%d", score), new Label.LabelStyle(font, Color.WHITE));
        scoreLabelT = new Label("MONEY", new Label.LabelStyle(font, Color.BLACK));


        table.add(timeLabelT).padTop(2).padLeft(2);
        table.add(scoreLabelT).padTop(2).padLeft(2);
        table.add(orderNumLT).padTop(2).padLeft(2);
        table.row();
        table.add(timeLabel).padTop(2).padLeft(2);
        table.add(scoreLabel).padTop(2).padLeft(2);
        table.add(orderNumL).padTop(2).padLeft(2);

        table.left().top();
        stage.addActor(table);
    }

    /**
     * Updates the time label.
     *
     * @param time The current game time in seconds.
     */
    public void updateTime(int time){
        int minutes = time / 60;
        int seconds = time % 60;
        table.left().top();

        this.timeString = String.format("%02d:%02d", minutes, seconds);

        timeLabel.setText(timeString);
        stage.addActor(table);

    }

    public void showScenarioComplete(){
        timeLabel.setColor(Color.GREEN);
        timeLabel.setText(String.format("TIME: " + this.timeString + " MONEY: %d", score));
        timeLabelT.setText("SCENARIO COMPLETE");
        table.center().top();
        stage.addActor(table);
    }

    /**
     * Buy an entity (e.g. Oven, Pan, etc.) and update the score.
     * @param cost The cost of the entity.
     */
    public void buyEntity(int cost){
        score -= cost;

        table.left().top();
        scoreLabel.setText(String.format("%d", score));
        stage.addActor(table);
    }

    /**
     * Calculates the user's score per order and updates the label.
     *
     * @param scenarioComplete Whether the game scenario has been completed.
     * @param expectedTime The expected time an order should be completed in.
     * @param currentTime The current game time.
     */
    public void updateScore(Boolean scenarioComplete, Integer expectedTime, Integer currentTime){
        int addScore;

        if(this.scenarioComplete == Boolean.FALSE){
            // ALWAYS BOOST SCORE BY 100 for now
            if (currentTime <= expectedTime) {
                addScore = 100;
            }
            else{
                /*
                addScore = 100 - (5 * (currentTime -expectedTime));
                if(addScore < 0){
                    addScore = 0;
                }
                */
                addScore = 100;
            }
            score += addScore;
        }


        if(scenarioComplete==Boolean.TRUE){
            scoreLabel.setColor(Color.GREEN);
            scoreLabel.setText("");
            scoreLabelT.setText("");
            scoreLabelT.remove();
            scoreLabel.remove();
            table.center().top();
            stage.addActor(table);
            this.scenarioComplete = Boolean.TRUE;
            return;
        }

        table.left().top();
        scoreLabel.setText(String.format("%d", score));
        stage.addActor(table);

    }

    /**
     * Updates the order label.
     *
     * @param scenarioComplete Whether the game scenario has been completed.
     * @param orderNum The index number of the order.
     */
    public void updateOrder(Boolean scenarioComplete, Integer orderNum){
        if(scenarioComplete==Boolean.TRUE){
            orderNumL.remove();
            orderNumLT.remove();
            table.center().top();
            stage.addActor(table);
            return;
        }

        table.left().top();
        orderNumL.setText(String.format("%d", orderNum));
        orderNumLT.setText("ORDER");
        stage.addActor(table);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
