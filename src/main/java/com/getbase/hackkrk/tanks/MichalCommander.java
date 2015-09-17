package com.getbase.hackkrk.tanks;

import com.getbase.hackkrk.tanks.api.Command;
import com.getbase.hackkrk.tanks.api.TacticalMoves;
import com.getbase.hackkrk.tanks.api.TurnResult;

import java.util.Random;

/**
 * Created by scroot on 17.09.2015.
 */
public class MichalCommander implements TacticalMoves {
    private Random rand = new Random();

    public Command generateCommand(TurnResult result) {
        if (rand.nextDouble() > 0.2) {
            return Command.fire(rand.nextInt(90) - 45, rand.nextInt(100) + 30);
        } else {
            return Command.move(rand.nextDouble() > 0.5 ? -100 : 100);
        }
    }

}
