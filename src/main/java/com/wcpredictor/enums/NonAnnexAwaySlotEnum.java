package com.wcpredictor.enums;

public enum NonAnnexAwaySlotEnum
{
    //non annex c
    B2("2B"),
    C2("2C"),
    F2("2F"),
    I2("2I"),
    L2("2L"),
    J2("2J"),
    H2("2H"),
    G2("2G");

    private final String value;

    NonAnnexAwaySlotEnum(String value)
    {
        this.value = value;
    }
    
    public String getValue() 
    {
        return value;
    }
    
}
