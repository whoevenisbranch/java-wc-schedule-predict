package com.wcpredictor.app;

public class StateMachine 
{

    public enum StateEnum 
    {
        R32, R16, QF, SF, F, DONE
    }

    private StateEnum currentState = StateEnum.R32;

    public void next() 
    {
        switch (currentState) 
        {
            case R32:
                this.currentState = StateEnum.R16;
                break;
            case R16:
                this.currentState = StateEnum.QF;
                break;
            case QF:
                this.currentState = StateEnum.SF;
                break;
            case SF:
                this.currentState = StateEnum.F;
                break;
            case F:
                this.currentState = StateEnum.DONE;
                break;
            default:
                break;
        }
    }

    public StateEnum getCurrentState() 
    {
        return currentState;
    }

}
