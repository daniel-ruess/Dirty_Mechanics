package org.dirtymechanics.frc.test.drive;

import edu.wpi.first.wpilibj.Relay;

/**
 * Handles operations relating to the transmission.
 */
public class Transmission {
    private final Relay relay;
    
    public Transmission(int relay) {
        this.relay = new Relay(relay);
    }
    
    /**
     * Sets the state of the transmission.
     * @param state The new state.
     */
    public void set(boolean state) {
        if (state) {
            relay.set(Relay.Value.kOff);
        } else {
            relay.set(Relay.Value.kForward);
        }
    }
}