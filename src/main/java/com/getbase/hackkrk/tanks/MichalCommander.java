package com.getbase.hackkrk.tanks;

import com.getbase.hackkrk.tanks.api.*;
import com.getbase.hackkrk.tanks.util.ShotCalculator;
import com.getbase.hackkrk.tanks.utils.UtilsClass;

import java.util.List;
import java.util.Random;

/**
 * Created by scroot on 17.09.2015.
 */
public class MichalCommander implements TacticalMoves {
    public int power = 50;
    public int angle = 45;
    public Tank nearField = null;
    private Random rand = new Random();
    private UtilsClass utils = new UtilsClass();
    private ShotCalculator calculator = new ShotCalculator();

    public boolean onLeftIsMoreTanks(Tank me, List<Tank> tanksList) {
        int l = 0, r = 0;
        for (Tank i : tanksList) {
            if (me.position.get(0) > i.position.get(0)) {
                l++;
            } else {
                r++;
            }
        }

        return r < l;
    }

    public Tank findNearest(Tank me, List<Tank> tankList) {
        int near = 10000;
        Tank nearTank = null;

        for (Tank t : tankList) {
            if (near > Math.abs(Math.abs(t.position.get(0)) - Math.abs(me.position.get(0)))) {
                nearTank = t;
            }
        }

        return nearTank;
    }

    public Command generateCommand(TurnResult result) {
        try {
            Tank my = utils.getMyTank(result.tanks);
            Outcome myOut = utils.getMyOutcome(result.outcome);

            Tank near = findNearest(my, result.tanks);
            boolean rightOfMe = near.position.get(0) > my.position.get(0);
            if (near != nearField) {
                if (rightOfMe) {
                    angle = 45;
                } else {
                    angle = -45;
                }
            }

            System.out.println("Near = " + near.name);
            System.out.println("Right of me? " + rightOfMe);
            System.out.println("More on left? " + onLeftIsMoreTanks(my, result.tanks));

            if (angle > 80 || angle < -80) {
                angle = 0;
            }

            power *= ShotCalculator.calculateShotPowerCoefficient(my.position, near.position, myOut.hitCoordinates);

            if (myOut.hitCoordinates.get(0) > near.position.get(0)) {
                if (rightOfMe) {
                    //power += 10;
                    angle--;
                } else {
                    //power -= 10;
                    angle++;
                }
            } else {
                if (rightOfMe) {
                    //power -= 10;
                    angle++;
                } else {
                    //power += 10;
                    angle--;
                }
            }


            if (rand.nextDouble() > 0.2) {
                if (onLeftIsMoreTanks(my, result.tanks)) angle -= 90;
                return Command.fire(angle, power);
            } else {
                return Command.move(rand.nextDouble() > 0.5 ? -100 : 100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Command.fire(rand.nextInt(90) - 45, rand.nextInt(100) + 30);
    }

}
