package com.comsysto.buildlight.cleware.driver;

import com.comsysto.buildlight.common.driver.Color;
import com.comsysto.buildlight.common.driver.TrafficLight;

/**
 * @author zutherb
 */
public class TrafficLightFactoryTest {
    public static void main(String[] args) {
        TrafficLight light = TrafficLightFactory.createNewInstance();
        light.switchOn(Color.RED);
        light.switchOffAllLeds();
        light.close();
    }
}
