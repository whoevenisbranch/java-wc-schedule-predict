package com.wcpredictor.enums;

public enum HomeSlotEnum 
{
    A2("2A"),
    E1("1E"),
    F1("1F"),
    C1("1C"),
    I1("1I"),
    E2("2E"),
    A1("1A"),
    L1("1L"),
    D1("1D"),
    G1("1G"),
    K2("2K"),
    H1("1H"),
    B1("1B"),
    J1("1J"),
    K1("1K"),
    D2("2D");

    private final String value;

    private HomeSlotEnum(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
}
