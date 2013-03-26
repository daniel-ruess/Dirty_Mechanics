package org.dirtymechanics.frc.test.shooter;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import org.dirtymechanics.frc.test.control.ControlScheme;

/**
 * Contains all functions relevant to the shooting mechanism.
 */
public class Shooter {
    private static final long SHOOTING_TIMEOUT = 500;
    private static final long SHOOTING_TIME = 75;
    private static final long COOLDOWN_TIMEOUT = 500;
    private final ControlScheme controls;
    private final Jaguar jaguar;
    private final Solenoid pistonOut;
    private final Solenoid pistonIn;
    private boolean shooting = false;
    private long shootingStart = 0;
    private long cooldownStart = 0;

    /**
     * Creates a new instance of the Shooter class.
     * @param shooterJag The jaguar for the wheel.
     * @param pistonOut The solenoid controlling the pressure to push the piston.
     * @param pistonIn The solenoid controlling the pressure to retract the piston.
     * @param controls The control instance.
     */
    public Shooter(Jaguar shooterJag, Solenoid pistonOut, Solenoid pistonIn, ControlScheme controls) {
        this.jaguar = shooterJag;
        this.pistonOut = pistonOut;
        this.pistonIn = pistonIn;
        this.controls = controls;
    }
    
    /**
     * Called per cycle.
     */
    public void update() {
        if (controls.getShooterWheelState()) {
            setSpeed(1.0D);
        } else {
            setSpeed(0.0D);
        }
        
        if (controls.getShooterPistonState()) {
            if (System.currentTimeMillis() - cooldownStart > COOLDOWN_TIMEOUT) {
                if (!shooting) {
                    shooting = true;
                    shootingStart = System.currentTimeMillis();
                }
                if ((System.currentTimeMillis() - shootingStart) % SHOOTING_TIMEOUT < SHOOTING_TIME) {
                    setPiston(true);
                } else {
                    setPiston(false);
                }
            }
        } else {
            if (shooting) {
                shooting = false;
                cooldownStart = System.currentTimeMillis();
            }
            setPiston(false);
        }
    }
    
    /**
     * Sets the speed of the shooter motor.
     * @param speed The desired speed.
     */
    public void setSpeed(double speed) {
        jaguar.set(speed);
    }
    
    /**
     * Sets the state of the piston; true on, false off.
     * @param state The state.
     */
    public void setPiston(boolean state) {
        pistonOut.set(state);
        pistonIn.set(!state);
    }
}
