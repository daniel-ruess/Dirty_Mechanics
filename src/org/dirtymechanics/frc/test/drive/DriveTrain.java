package org.dirtymechanics.frc.test.drive;

import edu.wpi.first.wpilibj.Jaguar;
import org.dirtymechanics.frc.test.control.ControlScheme;

/**
 * Controls all drive related operations.
 */
public class DriveTrain {
    private final Jaguar leftJag;
    private final Jaguar rightJag;
    private final Transmission transmission;
    private final ControlScheme controls;
    
    public DriveTrain(Jaguar leftJag, Jaguar rightJag,
            int transmission, ControlScheme controls) {
        this.leftJag = leftJag;
        this.rightJag = rightJag;
        this.transmission = new Transmission(transmission);
        this.controls = controls;
    }
    
    /**
     * Puts the transmission in high gear.
     */
    public void shiftUp() {
        transmission.set(true);
    }
    
    /**
     * Puts the transmission in low gear.
     */
    public void shiftDown() {
        transmission.set(false);
    }
    
    /**
     * Default drive; passes the controller values to the drive method.
     */
    public void drive() {
        drive(controls.getLeftDrivePower(), controls.getRightDrivePower());
    }
    
    /**
     * Drives the robot with the provided power values.
     * @param leftPow The power for the left drive.
     * @param rightPow The power for the right drive. 
     */
    public void drive(double leftPow, double rightPow) {
        transmission.set(controls.getTransmissionState());
        leftJag.set(-leftPow);
        rightJag.set(rightPow);
    }
}