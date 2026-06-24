package com.wcpredictor;

import com.wcpredictor.app.Application;
import com.wcpredictor.app.StateMachine;

public class Main {

    public static void main(String[] args) {
        Application app = new Application(new StateMachine());
        app.predict();
    }

}