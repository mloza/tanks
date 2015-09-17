package com.getbase.hackkrk.tanks;

import java.util.Random;

import com.getbase.hackkrk.tanks.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NaiveBot {
    private static final Logger log = LoggerFactory.getLogger(NaiveBot.class);
    private Random rand = new Random();
    private TacticalMoves stratey = new MichalCommander();

    public static void main(String... args) throws Exception {
        new NaiveBot().run();
    }

    public String[] getConfiguration() {
        switch(System.getProperty("player")) {
            case "Master":
                return new String[] {"http://10.12.202.141:9999", "master", "SensibleIvoryPeafowlBat"};
            case "Michal":
                return new String[] {"http://10.12.202.144:9999", "more1", "ReasonableFloralWhiteSwallowDotterel"};
            case "Bartosz":
                return null;
            case "Jacek":
                return null;
        }

        return new String[] {"http://10.12.202.141:9999", "master", "SensibleIvoryPeafowlBat"};
    }

    public void run() throws Exception {
        String data[] = getConfiguration();
        TanksClient client = new TanksClient(data[0], data[1], data[2]);

        while (true) {
            log.info("Waiting for the next game...");
            try {
                GameSetup gameSetup = client.getMyGameSetup();
                log.info("Playing {}", gameSetup);

                playGame(client);
            } catch (Exception e) {
                System.out.println("Error! " + e.getCause());
            }
        }
    }

    private void playGame(TanksClient client) {
        boolean gameFinished = false;
        TurnResult result = null;
        while (!gameFinished) {
            result = client.submitMove(stratey.generateCommand(result));
            gameFinished = result.last;
        }
    }

    public Command generateCommand() {
        if (rand.nextDouble() > 0.2) {
            return Command.fire(rand.nextInt(90) - 45, rand.nextInt(100) + 30);
        } else {
            return Command.move(rand.nextDouble() > 0.5 ? -100 : 100);
        }
    }
}
