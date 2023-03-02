package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Coord> getCoord() {
        int P;
        double F=0;
        double x;
        double dx;
        double a = 150;
        int j = 0;
        double caunt = 0.2;
        double K = 0.002;
        Calculator calculator = new Calculator();
        List<Coord> tests = new ArrayList<>();

        calculator.setFeaturesAbsorber();
        calculator.setStartData();
        for(double i=0; i<caunt;i=i+K){
            F= 1000000/(caunt/K)*i;

            dx = calculator.calcV(F)*1000;
            x = calculator.calcX(F)*100;


            tests.add(new Coord((int)x,(int)F));
        }

        return tests;


    }



}
