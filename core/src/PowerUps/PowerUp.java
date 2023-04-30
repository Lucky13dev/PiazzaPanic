package PowerUps;

import com.team13.piazzapanic.GameState;

public class PowerUp {
    public enum EFFECT {FREE_RECIPE, MONEY_BOOST, REPUTATION_BOOST, SPEED_BOOST, TIME_SAVER}

    //apply effect to the given gameState
    public static void apply(GameState gameState, EFFECT effect){
        switch (effect){
            case FREE_RECIPE:
                //later
                gameState.getHud().addMessage("Recipe Skipped");
                return;
            case REPUTATION_BOOST:
                gameState.giveReputation(100);
                gameState.getHud().addMessage("+100 Reputation");
                return;
            case MONEY_BOOST:
                gameState.giveMoney(100);
                gameState.getHud().addMessage("+100 Money");
                return;
            case SPEED_BOOST:
                gameState.multiplyChefSpeed(1.5f);
                gameState.getHud().addMessage("+50% Chef Speed");
                return;
            case TIME_SAVER:
                if (gameState.getTime() > 10){
                    gameState.decrementTime(10);
                    gameState.getHud().addMessage("-10s Game Time");
                }
                return;
        }
    };
}
