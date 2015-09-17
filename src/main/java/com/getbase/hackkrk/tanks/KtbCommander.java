package com.getbase.hackkrk.tanks;

import com.getbase.hackkrk.tanks.api.Command;
import com.getbase.hackkrk.tanks.api.TacticalMoves;
import com.getbase.hackkrk.tanks.api.TurnResult;

import java.util.Random;

/**
 * Created by scroot on 17.09.2015.
 */
public class KtbCommander implements TacticalMoves {
    private Random rand = new Random();

    public Command generateCommand(TurnResult result) {
        if (rand.nextDouble() > 0.5) {
            return Command.fire(rand.nextInt(90) - 45, 50);
        } else {
            return Command.move(rand.nextDouble() > 0.5 ? -100 : 100);
        }
    }

}
