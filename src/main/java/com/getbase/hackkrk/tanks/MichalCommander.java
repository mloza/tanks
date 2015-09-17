package com.getbase.hackkrk.tanks;

import com.getbase.hackkrk.tanks.api.*;
import com.getbase.hackkrk.tanks.utils.UtilsClass;
import com.getbase.hackkrk.tanks.utils.exceptions.TankNotFoundException;

import java.util.List;
import java.util.Random;

/**
 * Created by scroot on 17.09.2015.
 */
public class MichalCommander implements TacticalMoves {
    private Random rand = new Random();

    private UtilsClass utils = new UtilsClass();

    public boolean onLeftIsMoreTanks(Tank me, List<Tank> tanksList) {
        int l = 0, r = 0;
        for (Tank i : tanksList) {
            if (me.position.get(0) > i.position.get(0)) {
                r++;
            } else {
                l++;
            }
        }

        return r < l;
    }

    public Command generateCommand(TurnResult result) {
        try {
            Tank my = utils.getMyTank(result.tanks);
            Outcome myOut = utils.getMyOutcome(result.outcome);

            if (rand.nextDouble() > 0.2) {
                int angle = rand.nextInt(90);

                if (onLeftIsMoreTanks(my, result.tanks)) angle -= 90;

                return Command.fire(angle, rand.nextInt(100) + 30);
            } else {
                return Command.move(rand.nextDouble() > 0.5 ? -100 : 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Command.fire(rand.nextInt(90)-45, rand.nextInt(100) + 30);
    }

}
