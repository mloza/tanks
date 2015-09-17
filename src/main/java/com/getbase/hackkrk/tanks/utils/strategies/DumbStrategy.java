package com.getbase.hackkrk.tanks.utils.strategies;

import com.getbase.hackkrk.tanks.api.Command;
import com.getbase.hackkrk.tanks.api.TacticalMoves;
import com.getbase.hackkrk.tanks.api.Tank;
import com.getbase.hackkrk.tanks.api.TurnResult;
import com.getbase.hackkrk.tanks.utils.UtilsClass;
import com.getbase.hackkrk.tanks.utils.exceptions.TankNotFoundException;

/**
 * Created by Jacek on 2015-09-17.
 */
public class DumbStrategy implements TacticalMoves {

    Boolean shouldIMove = true;
    TurnResult prevResult;

    @Override
    public Command generateCommand(TurnResult result) {

        UtilsClass utilsClass = new UtilsClass();
        Tank brickKiller;

        shouldIMove = !shouldIMove;
        try {
            if (shouldIMove) {

                brickKiller = utilsClass.getMyTank(result.tanks);
                if (utilsClass.getMyTank(prevResult.tanks).position.compareTo(brickKiller.position) < 0) {
                    prevResult = result;
                    return brickKiller.position.getX() > 400 ? Command.move(20) : Command.move(-20);
                } else if (utilsClass.getMyTank(prevResult.tanks).position.compareTo(brickKiller.position) > 0) {
                    prevResult = result;
                    return brickKiller.position.getX() < -400 ? Command.move(-20) : Command.move(20);
                } else {
                    return Command.move(20);
                }
            } else {
                brickKiller = utilsClass.getMyTank(result.tanks);
                if (utilsClass.getMyTank(prevResult.tanks).position.compareTo(brickKiller.position) > 0) {
                    prevResult = result;
                    return Command.fire(-80,98);
                } else if (utilsClass.getMyTank(prevResult.tanks).position.compareTo(brickKiller.position) < 0) {
                    prevResult = result;
                    return Command.fire(80,98);
                } else {
                    return Command.fire(45,45);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
