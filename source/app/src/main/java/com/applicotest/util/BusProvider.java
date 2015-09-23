package com.applicotest.util;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Nirajan on 9/22/2015.
 */
public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    private BusProvider(){

    }
}
