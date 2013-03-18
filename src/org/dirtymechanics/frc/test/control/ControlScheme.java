package org.dirtymechanics.frc.test.control;

public interface ControlScheme {
    
    /**
     * Gets the power for the left drive train.
     * @return The power value.
     */
    public double getLeftDrivePower();
    
    /**
     * Gets the power for the right drive train.
     * @return The power value.
     */
    public double getRightDrivePower(); 

    /**
     * Gets the state of the transmission.
     * @return The state.
     */
    public boolean getTransmissionState();

    public boolean isShooting();
}