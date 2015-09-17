package com.getbase.hackkrk.tanks;

import com.getbase.hackkrk.tanks.api.Command;
import com.getbase.hackkrk.tanks.api.TacticalMoves;
import com.getbase.hackkrk.tanks.api.Tank;
import com.getbase.hackkrk.tanks.api.TurnResult;
import com.getbase.hackkrk.tanks.utils.UtilsClass;
import com.getbase.hackkrk.tanks.utils.exceptions.TankNotFoundException;

import java.util.Random;

/**
 * Created by scroot on 17.09.2015.
 */
public class MichalCommander implements TacticalMoves {
    private Random rand = new Random();

    private UtilsClass utils = new UtilsClass();

    public Command generateCommand(TurnResult result) {
        try {
            Tank my = utils.getMyTank(result.tanks);
        } catch (TankNotFoundException e) {
            e.printStackTrace();
        }

        if (rand.nextDouble() > 0.2) {
            return Command.fire(rand.nextInt(90) - 45, rand.nextInt(100) + 30);
        } else {
            return Command.move(rand.nextDouble() > 0.5 ? -100 : 100);
        }
    }

}
