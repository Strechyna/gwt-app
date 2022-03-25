package com.example.gwtapp.server.service.helper;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class PlaceCsvHeader {

    public static final String ID = "id";
    public static final String CITY_NAME = "city_name";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";
    public static final String DESCRIPTION = "description";
    public static final String[] ALL = {ID, CITY_NAME, NAME, ADDRESS, DESCRIPTION};

}
