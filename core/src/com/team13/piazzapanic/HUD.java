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
import java.util.HashMap;
import java.util.Map;


public class HUD implements Disposable, Serializable {
    public Stage stage;
    private Boolean scenarioComplete;

    public String timeString;

    public Table gameStatsTable;
    public Table playerInfoTable;

    Map<String, Float> screenMessages;

    Label timeLabelT;
    Label timeLabel;
    Label reputationLabel;
    Label reputationLabelT;
    Label moneyLabel;
    Label moneyLabelT;
    Label orderNumL;
    Label orderNumLT;
    BitmapFont font;

    public HUD(SpriteBatch sb){
        this.scenarioComplete = Boolean.FALSE;
        this.timeString = "00:00";
        float fontX = 0.5F;
        float fontY = 0.3F;

        this.font = new BitmapFont();
        this.font.getData().setScale(fontX, fontY);
        Viewport viewport = new FitViewport(MainGame.V_WIDTH, MainGame.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        gameStatsTable = new Table();
        gameStatsTable.left().top();
        gameStatsTable.setFillParent(true);

        timeLabel = new Label(String.format(this.timeString), new Label.LabelStyle(font, Color.WHITE));
        timeLabelT = new Label("TIME", new Label.LabelStyle(this.font, Color.BLACK));
        orderNumLT = new Label("ORDER", new Label.LabelStyle(this.font, Color.BLACK));
        orderNumL = new Label(String.format("%d", 0), new Label.LabelStyle(this.font, Color.WHITE));
        moneyLabel = new Label(String.format("%d", 0), new Label.LabelStyle(this.font, Color.WHITE));
        moneyLabelT = new Label("MONEY", new Label.LabelStyle(this.font, Color.BLACK));

        reputationLabel = new Label(String.format("%d", 30), new Label.LabelStyle(this.font, Color.WHITE));
        reputationLabelT = new Label("REP", new Label.LabelStyle(this.font, Color.BLACK));


        gameStatsTable.add(timeLabelT).padTop(2).padLeft(2);
        gameStatsTable.add(moneyLabelT).padTop(2).padLeft(2);
        gameStatsTable.add(orderNumLT).padTop(2).padLeft(2);
        gameStatsTable.row();
        gameStatsTable.add(timeLabel).padTop(2).padLeft(2);
        gameStatsTable.add(moneyLabel).padTop(2).padLeft(2);
        gameStatsTable.add(orderNumL).padTop(2).padLeft(2);

        gameStatsTable.row();
        gameStatsTable.add(reputationLabelT).padTop(2).padLeft(2);
        gameStatsTable.row();
        gameStatsTable.add(reputationLabel).padTop(2).padLeft(2);

        gameStatsTable.left().top();
        stage.addActor(gameStatsTable);

        screenMessages = new HashMap<>();
        screenMessages.put("Game Started", 5f);
        playerInfoTable = new Table();
        playerInfoTable.setFillParent(true);
        for (String message : screenMessages.keySet()){
            playerInfoTable.add(new Label(message, new Label.LabelStyle(this.font, Color.RED))).padTop(2).padLeft(2);
            playerInfoTable.row();
        }
        playerInfoTable.center().bottom();
        stage.addActor(playerInfoTable);
    }

    /**
     * Updates the time label.
     *
     * @param time The current game time in seconds.
     * @param dt Delta time since last call in seconds.
     */
    public void update(int time, float dt){
        //update time label
        int minutes = time / 60;
        int seconds = time % 60;
        gameStatsTable.left().top();

        this.timeString = String.format("%02d:%02d", minutes, seconds);

        this.timeLabel.setText(timeString);
        this.stage.addActor(gameStatsTable);

        //update player messages
        this.playerInfoTable.clear();
        for (Map.Entry<String, Float> message : screenMessages.entrySet()){
            if (message.getValue() > 0) {
                message.setValue(message.getValue()-dt);
                String messageLine = message.getKey()+String.format(" (%.0f)", message.getValue()+1);
                this.playerInfoTable.add(new Label(messageLine, new Label.LabelStyle(this.font, Color.RED)));
                this.playerInfoTable.row();
            }
        }
    }

    //adds a new message to the hud for the display time specified
    public void addMessage(String message, Float displayTime){
        this.screenMessages.put(message, displayTime);
    }
    public void addMessage(String message){
        this.addMessage(message, 5f);
    }
    public void clearMessages(){
        this.screenMessages = new HashMap<>();
    }

    public void showScenarioComplete(float reputation){
        gameStatsTable.clear();
        gameStatsTable.add(timeLabelT).padTop(2).padLeft(2);
        gameStatsTable.row();
        gameStatsTable.add(timeLabel).padTop(2).padLeft(2);
        timeLabel.setColor(Color.GREEN);
        timeLabel.setText(String.format("TIME: " + this.timeString + " Reputation: %d", (int) reputation));
        timeLabelT.setText("SCENARIO COMPLETE");
        gameStatsTable.center().top();
        stage.addActor(gameStatsTable);
    }
    public void showScenarioFailed(){
        gameStatsTable.clear();
        gameStatsTable.add(timeLabelT).padTop(2).padLeft(2);
        gameStatsTable.row();
        gameStatsTable.add(timeLabel).padTop(2).padLeft(2);
        timeLabel.setColor(Color.GREEN);
        timeLabel.setText(String.format("You lost all your reputation."));
        timeLabelT.setText("SCENARIO FAILED");
        gameStatsTable.center().top();
        stage.addActor(gameStatsTable);
    }

    /**
     * Update the money value displayed on the hud.
     * @param newBalance The new value to show in the money field.
     */
    public void updateMoney(int newBalance){
        gameStatsTable.left().top();
        moneyLabel.setText(String.format("%d",newBalance));
        stage.addActor(gameStatsTable);
    }

    /**
     * Update the money value displayed on the hud.
     * @param newReputation The new value to show in the money field.
     */
    public void updateReputation(int newReputation){
        gameStatsTable.left().top();
        reputationLabel.setText(String.format("%d",newReputation));
        stage.addActor(gameStatsTable);
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
            gameStatsTable.center().top();
            stage.addActor(gameStatsTable);
            return;
        }

        gameStatsTable.left().top();
        orderNumL.setText(String.format("%d", orderNum));
        orderNumLT.setText("ORDER");
        stage.addActor(gameStatsTable);

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
