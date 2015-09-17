package com.getbase.hackkrk.tanks.util;

import mikera.vectorz.Vector2;

public class ShotCalculator {


    public static void main(String[] args) {
        Vector2 myPos = new Vector2(1, 2);
        Vector2 targetPos = new Vector2(7,3);
        Vector2 hitPos = new Vector2(3,3);

        double coeff = calculateShotPowerCoefficient(myPos, targetPos, hitPos);
    }

    public static double calculateShotPowerCoefficient(Vector2 myPos, Vector2 targetPos, Vector2 hitPos) {
        double desiredDistance = targetPos.getX() - myPos.getX();
        double currentDistance = hitPos.getX() - myPos.getX();

        return currentDistance ==0 ? 0 : desiredDistance / currentDistance;
    }
}
