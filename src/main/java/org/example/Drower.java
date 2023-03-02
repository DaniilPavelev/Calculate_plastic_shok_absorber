package org.example;

import javafx.scene.chart.AreaChart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Drower extends JPanel {
        int xg[] =  Up7.x;
        int yg[] =  Up7.y;
        int ng = Up7.n;



        @Override
        protected void paintComponent(Graphics gh) {
            Graphics2D drp = (Graphics2D)gh;
            drp.drawLine(20, 440, 20, 20);
            drp.drawLine(20, 440, 460, 440);

            drp.drawPolyline(xg, yg, ng);
        }
    }

    class Up7 extends JFrame{


        static List<Coord> coordList = Main.getCoord();

        public static int[] getX(List<Coord> list){
            int[] x = new int[100];
            for(int i=0;i<100;i++){
                x[i] =20+ list.get(i).x;
            }
            return x;
        }
        public static int[] getP(List<Coord> list){
            int[] P = new int[100];
            for(int i=0;i<100;i++){
                P[i] =440 - list.get(i).P;
            }
            return P;
        }
        public  static int x[] =  getX(coordList);

        public  static int y[] =  getP(coordList);
        public static int n = 100;

        public Up7 () {
            super("График по точкам");
            JPanel jcp = new JPanel(new BorderLayout());
            setContentPane(jcp);
            jcp.add(new Drower(), BorderLayout.CENTER);
            jcp.setBackground(Color.gray);
            setSize(600, 500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

        public static void main(String[] args)   {
            new Up7().setVisible(true);
        }

}
