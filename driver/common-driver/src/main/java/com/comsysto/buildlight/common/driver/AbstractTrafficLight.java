package com.comsysto.buildlight.common.driver;

/**
 * @author zutherb
 */
public abstract class AbstractTrafficLight<T> implements TrafficLight {

    @Override
    public void switchOnAllLeds() {
        for (Color color : Color.values()) {
            switchOn(color);
        }
    }

    @Override
    public void switchOffAllLeds() {
        for (Color color : Color.values()) {
            switchOff(color);
        }
    }

    protected abstract T map(Color color);
}
