package com.getbase.hackkrk.tanks.utils.strategies;

import com.getbase.hackkrk.tanks.api.Command;
import com.getbase.hackkrk.tanks.api.Tank;
import com.getbase.hackkrk.tanks.api.TurnResult;
import com.getbase.hackkrk.tanks.utils.UtilsClass;
import com.getbase.hackkrk.tanks.utils.exceptions.TankNotFoundException;
import mikera.vectorz.Vector2;

import java.util.*;

/**
 * Created by Jacek on 2015-09-17.
 */
public class CoupDaGraceUtil {

    Map<Tank,Integer> memory;


    public CoupDaGraceUtil() {
        memory = new LinkedHashMap<>();
    }

    public void reset(){
        memory.clear();
    }
    public Command[] checkPossilbeCoupDaGrace(TurnResult result){
        Command[] commands = new Command[2];
        if(memory.size() == 0){
            for(Tank tank : result.tanks){
                memory.put(tank,1);
            }
            return null;
        }

        for(Tank tank : result.tanks){
            for(Tank keyTank: memory.keySet()){
                if (tank.name.equals(keyTank.name) && !tank.name.equals(UtilsClass.TANK_NAME)){
                    if(tank.position.equals(keyTank.position)){
                    memory.replace(keyTank,memory.get(keyTank) + 1);
                    } else {
                        memory.replace(keyTank, 1);
                    }
                }
            }
        }

        for(Tank candidate : memory.keySet()){
            if(memory.get(candidate) > 4){
                 commands[0] = Command.move(countDistance(result.tanks, candidate) +
                                 countDistance(result.tanks, candidate) > 0 ? -31 : 31
                 );

                if(countDistance(result.tanks,candidate) < 0){
                    commands[1] = Command.fire(-85,100);
                } else {
                    commands[1] = Command.fire(85,100);
                }
            }
        }
        return commands;
    }

    private double countDistance(List<Tank> tanks, Tank candidate) {
        UtilsClass utilsClass = new UtilsClass();

        try {
            return (utilsClass.getMyTank(tanks).position.getX()+500)-(candidate.position.getX()+500);
        } catch (TankNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
