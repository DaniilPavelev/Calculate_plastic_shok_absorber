package org.example;

public class Calculator {
    double l = 19.4/100;
    double mu = 0.25;
    double m = 1/mu;
    double q_m = 0.2783*mu - 0.9038*mu + 1.3784;

    double B = 100;


    double r = 0.9;
    P p1=new P();
    P p2=new P();
    P p3=new P();
    P p4=new P();
    double R0;
    double DD;
    double P;
    double P0;
    double adx;
    double A;
    boolean plast = false;
    double R44;
    double R22;

    public double calculate(double x,double dx){

        adx = Math.abs(dx);

        //определение динамического упрочнения
        if(adx>DD){
            setA(adx);
            DD = adx;
        }
        p2.R1 = A*R22; //flag R22!
        p2.x = p2.x+(p2.R1*p2.R)/p1.C;
        p4.R1 = A*R44; //flag R44!
        p4.x = p4.x + (p4.R1*p4.R)/p1.C ;

        p4.R = p4.R1;

        calcP14(x);

        if(plast) {
            loading_or_unloading_within_the_limits_of_elasticity(x);
            if(P0-p2.P > 0){ positive_loading_and_negative_loading_preparation(x); plTR(x); return P;}
            else if(P0 - p4.P <0){ negative_loading_and_positive_loading_preparation(x); plTR(x); return P;}
            P = P0;
            return P;
        }
        else if(dx*p1.P<0){
            unloading_or_reloading_at_plasticity(x);
            if(P0-p2.P > 0){ positive_loading_and_negative_loading_preparation(x); plTR(x); return P;}
            else if(P0 - p4.P <0){ negative_loading_and_positive_loading_preparation(x); plTR(x); return P;}
            P = P0;
            return P;
        }
        else if(R0*p1.P>0){
            unloading_or_reloading_at_plasticity(x);
            if(P0-p2.P > 0){ positive_loading_and_negative_loading_preparation(x); plTR(x); return P;}
            else if(P0 - p4.P <0){ negative_loading_and_positive_loading_preparation(x); plTR(x); return P;}
            P = P0;
            return P;
        }
        else {
            loading_or_unloading_within_the_limits_of_elasticity(x);
            if(P0-p2.P > 0){ positive_loading_and_negative_loading_preparation(x); plTR(x); return P;}
            else if(P0 - p4.P <0){ negative_loading_and_positive_loading_preparation(x); plTR(x); return P;}
            P = P0;
            return P;
        }
    }


    //8
    private  void plTR(double x){
        plast = true;
        p1.R = P;
        p1.x = x;
        p3.R = P;
        p3.x = x;
        p1.C = p1.C0;
    }

    //отрицательное нагружение и подготовка положительного нагружения
    private void negative_loading_and_positive_loading_preparation(double x){
        //отрицательное нагружение
        P = p4.P;
        p3.C0 = p4.C;
        //подготовка положительного нагружения
        p2.C = p2.C0;
        p2.R = (p2.C0*x-p4.P)/(1-p2.C0/p1.C0);
        R22 = p2.R;
        p2.x = x-p4.P/p3.C +p2.R/p1.C0;
    }
    //положительное нагружение и подготовка отрицательного нагружения
    private void positive_loading_and_negative_loading_preparation(double x){
        //положительное нагружение
        P = p2.P;
        p3.C0 = p2.C;
        //подготовка отрицательного нагружения
        p4.C = p4.C0;
        p4.R = (p4.C0*x-p2.P)/(1-p4.C0/p1.C0);
        R44 = p4.R;
        p4.x = x-p2.P/p3.C+p4.R/p1.C0;
        }
    //нагружение или разгрузка в пределах упругости
    private void loading_or_unloading_within_the_limits_of_elasticity(double x){
        P0 = p1.P;
        p3.C0 = p1.C;
        p3.R = P0;
        p3.x = x;
    }
    //разгрузка или повторное нагружение при пластичности
    private void unloading_or_reloading_at_plasticity(double x){
        P0 = p3.P;
        p3.C0 = p3.C;
        p1.R = P0;
        p1.x=x;
        R0 = p3.R;
        if(P0/R0 > 1) R0 = 0;
    }
    private void calcP14(double x){
        p1.calcP(x);
        p2.calcP(x);
        p3.calcP(x);
        p4.calcP(x);
    }
    // TODO: добавить ввод из консоли
    //метод задающий характеристики пластического амортизатора
    public void setFeaturesAbsorber(){
        p1.C0 = 200000;
        p1.C1 = 170000;
        p2.C0 = 100;
        p2.C1 = 90;
        p2.R0 = 205;
        p3.C = 190000;

        p4.R0 = -p2.R0;
        p4.C1 = p2.C1;
        p4.C0 = p2.C0;
    }
    //метод задающий начальные условия
    public void setStartData(){
        p1.R = 0;
        p1.x = 0;
        p1.C = p1.C1;
        p3.x = 0;
        p2.R = p2.R0;
        p2.C=p2.C1;
        p2.x = p2.R/p1.C;
        p4.R = p4.R0;
        p4.C= p4.C1;
        p4.x = p4.R/p1.C;
        R22 = p2.R;
        R44 = p4.R;
        R0 = 0;
        DD=0;
        P=0;
    }

    private void setA(double dx){
        if(dx>=2) A = 1.5;
        else if(dx<10&&dx>2)
            A = 0.0625*dx+0.875;
    }

    public static double[] getCoordinateandSpeed(double v1, double v2, double t, double actualT){
        double[] c_and_s = new double[2];



        return c_and_s;
    }


    public double calcV(double P){

        return Math.cos( Math.pow(P,m) * Math.pow(l,2*m)/(2*10*0.95*Math.pow(r,2*m+1)));

    }

    public double calcX(double P){
        return Math.pow(P,m) * Math.pow(l,2*m)/(2*B*q_m*Math.pow(r,2*m+1));
    }


}