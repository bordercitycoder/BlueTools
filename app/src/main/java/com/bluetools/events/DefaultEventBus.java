package com.bluetools.events;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

public class DefaultEventBus implements IDefaultEventBus {

    @Inject
    public DefaultEventBus() {
    }

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
