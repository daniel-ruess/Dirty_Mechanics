package org.dirtymechanics.frc.test.drive;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Handles operations relating to the transmission.
 */
public class Transmission {
    /**
     * The two solenoids for the double action setup.
     */
    private final Solenoid a, b;
    
    public Transmission(Solenoid a, Solenoid b) {
        this.a = a;
        this.b = b;
    }
    
    /**
     * Sets the state of the transmission.
     * @param state The new state.
     */
    public void set(boolean state) {
        a.set(state);
        b.set(!state);
    }
}