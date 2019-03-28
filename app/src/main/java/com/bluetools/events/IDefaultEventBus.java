package com.bluetools.events;

public interface IDefaultEventBus {
    void registerWithBus(Object object);

    void unregisterWithBus(Object object);
}
