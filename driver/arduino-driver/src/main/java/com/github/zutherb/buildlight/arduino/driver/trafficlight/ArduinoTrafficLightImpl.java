package com.github.zutherb.buildlight.arduino.driver.trafficlight;

import com.github.zutherb.buildlight.arduino.driver.core.Arduino;
import com.github.zutherb.buildlight.common.driver.core.AbstractTrafficLight;
import com.github.zutherb.buildlight.common.driver.core.Color;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.github.zutherb.buildlight.arduino.driver.core.Arduino.*;

/**
 * @author zutherb
 */
public class ArduinoTrafficLightImpl extends AbstractTrafficLight<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoTrafficLightImpl.class);

    private static Map<Color, Integer> PIN_MAPPING = ImmutableMap.<Color, Integer>builder()
            .put(Color.GREEN, DIGITAL_PIN2)
            .put(Color.YELLOW, DIGITAL_PIN4)
            .put(Color.RED, DIGITAL_PIN7)
            .build();

    private Arduino arduino;
    private SwitchOnLevel switchOnLevel;


    ArduinoTrafficLightImpl(Arduino arduino, SwitchOnLevel switchOnLevel) {
        this.arduino = arduino;
        this.switchOnLevel = switchOnLevel;
        initArduino();
    }

    private void initArduino() {
        for (Map.Entry<Color, Integer> entry : PIN_MAPPING.entrySet()) {
            arduino.pinMode(entry.getValue(), Arduino.OUTPUT);
            //switch off lamp
            arduino.digitalWrite(entry.getValue(), switchOnLevel.getLevelValue());
        }
    }

    @Override
    public void switchOn(Color color) {
        arduino.digitalWrite(map(color), switchOnLevel.getLevelValue());

    }

    @Override
    public void switchOff(Color color) {
        arduino.digitalWrite(map(color), SwitchOnLevel.neg(switchOnLevel).getLevelValue());

    }

    @Override
    public void close() {
        arduino.dispose();
    }

    @Override
    protected Integer map(Color color) {
        return PIN_MAPPING.get(color);
    }
}
