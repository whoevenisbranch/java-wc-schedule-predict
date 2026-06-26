package com.wcpredictor;

import com.wcpredictor.app.Application;
import com.wcpredictor.app.StateMachine;
import com.wcpredictor.exceptions.UserExitedException;

public class Main
{

    public static void main(String[] args)
    {
        Application app = new Application(new StateMachine());

        try
        {
            app.predict();
        }
        catch (UserExitedException e)
        {
            System.out.println("user exited application");
            System.exit(0);
        }
        catch (Exception e) 
        {
            System.err.println("Application failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

}