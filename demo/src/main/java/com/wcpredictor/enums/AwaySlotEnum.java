package com.wcpredictor.enums;

public enum AwaySlotEnum 
{
    //non annex c
    B2("2B"),
    C2("2C"),
    F2("2F"),
    I2("2I"),
    L2("2L"),
    J2("2J"),
    H2("2H"),
    G2("2G"),
    //third place
    A3("3A"),
    B3("3B"),
    C3("3C"),
    D3("3D"),
    E3("3E"),
    F3("3F"),
    G3("3G"),
    H3("3H"),
    I3("3I"),
    J3("3J"),
    K3("3K"),
    L3("3L");

    private final String value;

    private AwaySlotEnum(String value) 
    {
        this.value = value;
    }
    
    public String getValue() 
    {
        return value;
    }
    
}
