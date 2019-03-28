package com.example.bluetools.events;

import org.greenrobot.eventbus.EventBus;

public class DefaultEventBus implements IDefaultEventBus {



    public void registerWithBus(Object object) {

        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }

    }


    public void unregisterWithBus(Object object) {

        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }

    }




}
