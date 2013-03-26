package org.dirtymechanics.frc.test.control.impl;

import org.dirtymechanics.frc.test.control.ControlScheme;
import org.dirtymechanics.frc.test.control.SmartJoystick;

public class StandardControlScheme implements ControlScheme {
    private static final int TRANSMISSION_SWITCH = 1;
    private static final int SHOOTER_WHEEL = 3;
    private static final int SHOOTER_PISTON = 6;
    private static final int DRIVE_SCALAR = 10;
    
    private final SmartJoystick left;
    private final SmartJoystick right;
    private final SmartJoystick pad;

    public StandardControlScheme(SmartJoystick left, SmartJoystick right, SmartJoystick pad) {
        this.left = left;
        this.right = right;
        this.pad = pad;
        right.registerToggleButton(TRANSMISSION_SWITCH);
        right.registerToggleButton(SHOOTER_WHEEL);
        right.registerToggleButton(DRIVE_SCALAR);
    }
    

    public double getLeftDrivePower() {
        return -left.getY();
    }

    public double getRightDrivePower() {
        return -right.getY();
    }
    
    public boolean getTransmissionState() {
        return left.getButtonState(TRANSMISSION_SWITCH);
    }

    public boolean getShooterWheelState() {
        return pad.getButtonState(SHOOTER_WHEEL);
    }

    public boolean getShooterPistonState() {
        return pad.getButtonState(SHOOTER_PISTON);
    }

    public boolean getDriveScalarState() {
        return right.getButtonState(DRIVE_SCALAR);
    }
}