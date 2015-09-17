package com.getbase.hackkrk.tanks.strategies;

import com.getbase.hackkrk.tanks.api.Command;
import com.getbase.hackkrk.tanks.api.TacticalMoves;
import com.getbase.hackkrk.tanks.api.TurnResult;

/**
 * Created by Jacek on 2015-09-17.
 */
public class DumbStrategy implements TacticalMoves {

    @Override
    public Command generateCommand(TurnResult result) {
        return null;
    }
}
