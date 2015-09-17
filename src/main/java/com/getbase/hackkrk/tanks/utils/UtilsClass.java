package com.getbase.hackkrk.tanks.utils;

import com.getbase.hackkrk.tanks.api.Outcome;
import com.getbase.hackkrk.tanks.api.Tank;
import com.getbase.hackkrk.tanks.utils.exceptions.TankNotFoundException;

import java.util.List;

/**
 * Created by scroot on 17.09.2015.
 */
public class UtilsClass {

    public static String TANK_NAME = "brickKiller";

    public Tank getMyTank(List<Tank> tanks) throws TankNotFoundException {
        for(Tank tank: tanks) {
            if(tank.name.equals(TANK_NAME)) {
                return tank;
            }
        }

        throw new TankNotFoundException();
    }

    public Outcome getMyOutcome(List<Outcome> out) throws TankNotFoundException {
        for(Outcome o: out) {
            if(o.name.equals(TANK_NAME)) {
                return o;
            }
        }

        throw new TankNotFoundException();
    }

}
