package com.wcpredictor.enums;

public enum GroupEnum 
{
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    I("I"),
    J("J"),
    K("K"),
    L("L");

    private final String value;

    private GroupEnum(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
}
