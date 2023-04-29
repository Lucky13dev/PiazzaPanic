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

import java.io.Serializable;


public class HUD implements Disposable, Serializable {
    public Stage stage;
    private Boolean scenarioComplete;

    public String timeString;

    public Table table;

    Label timeLabelT;
    Label timeLabel;
    Label reputationLabel;
    Label reputationLabelT;
    Label moneyLabel;
    Label moneyLabelT;
    Label orderNumL;
    Label orderNumLT;

    public HUD(SpriteBatch sb){
        this.scenarioComplete = Boolean.FALSE;
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
        moneyLabel = new Label(String.format("%d", 0), new Label.LabelStyle(font, Color.WHITE));
        moneyLabelT = new Label("MONEY", new Label.LabelStyle(font, Color.BLACK));

        reputationLabel = new Label(String.format("%d", 30), new Label.LabelStyle(font, Color.WHITE));
        reputationLabelT = new Label("REP", new Label.LabelStyle(font, Color.BLACK));


        table.add(timeLabelT).padTop(2).padLeft(2);
        table.add(moneyLabelT).padTop(2).padLeft(2);
        table.add(orderNumLT).padTop(2).padLeft(2);
        table.row();
        table.add(timeLabel).padTop(2).padLeft(2);
        table.add(moneyLabel).padTop(2).padLeft(2);
        table.add(orderNumL).padTop(2).padLeft(2);

        table.row();
        table.add(reputationLabelT).padTop(2).padLeft(2);
        table.row();
        table.add(reputationLabel).padTop(2).padLeft(2);


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

    public void showScenarioComplete(float reputation){
        table.clear();
        table.add(timeLabelT).padTop(2).padLeft(2);
        table.row();
        table.add(timeLabel).padTop(2).padLeft(2);
        timeLabel.setColor(Color.GREEN);
        timeLabel.setText(String.format("TIME: " + this.timeString + " Reputation: %d", (int) reputation));
        timeLabelT.setText("SCENARIO COMPLETE");
        table.center().top();
        stage.addActor(table);
    }
    public void showScenarioFailed(){
        table.clear();
        table.add(timeLabelT).padTop(2).padLeft(2);
        table.row();
        table.add(timeLabel).padTop(2).padLeft(2);
        timeLabel.setColor(Color.GREEN);
        timeLabel.setText(String.format("You lost all your reputation."));
        timeLabelT.setText("SCENARIO FAILED");
        table.center().top();
        stage.addActor(table);
    }

    /**
     * Update the money value displayed on the hud.
     * @param newBalance The new value to show in the money field.
     */
    public void updateMoney(int newBalance){
        table.left().top();
        moneyLabel.setText(String.format("%d",newBalance));
        stage.addActor(table);
    }

    /**
     * Update the money value displayed on the hud.
     * @param newReputation The new value to show in the money field.
     */
    public void updateReputation(int newReputation){
        table.left().top();
        reputationLabel.setText(String.format("%d",newReputation));
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
