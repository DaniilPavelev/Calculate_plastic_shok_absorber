package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double P;
        double x;
        double dx;
        double a = 150;
        Calculator calculator = new Calculator();
        List<Test> tests = new ArrayList<>();

        calculator.setFeaturesAbsorber();
        calculator.setStartData();
        for(double i=0; i<0.2;i=i+0.002){
            dx = 150*i;
            x = dx*i+a*i*i/2;

            P = calculator.calculate(x,dx);

            tests.add(new Test(x,P));
        }

        int g = 0;


    }
}
