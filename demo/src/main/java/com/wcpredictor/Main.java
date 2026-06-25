package com.wcpredictor;

import com.wcpredictor.app.Application;
import com.wcpredictor.app.StateMachine;

public class Main {

    public static void main(String[] args) {
        Application app = new Application(new StateMachine());

        try 
        {
            app.predict();
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}