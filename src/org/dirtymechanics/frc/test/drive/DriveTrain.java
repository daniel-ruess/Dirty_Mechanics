package org.dirtymechanics.frc.test.drive;

import edu.wpi.first.wpilibj.Jaguar;
import org.dirtymechanics.frc.test.control.ControlScheme;

/**
 * Controls all drive related operations.
 */
public class DriveTrain {
    /**
     * The value for the artificial scalar for the drive train.
     */
    private static final double DRIVE_SCALAR = 0.5D;
    
    private final Jaguar leftJag;
    private final Jaguar rightJag;
    private final Transmission transmission;
    private final ControlScheme controls;
    
    public DriveTrain(Jaguar leftJag, Jaguar rightJag, Transmission transmission,
            ControlScheme controls) {
        this.leftJag = leftJag;
        this.rightJag = rightJag;
        this.transmission = transmission;
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
        double scalar = controls.getDriveScalarState()? 1 : DRIVE_SCALAR;
        drive(controls.getLeftDrivePower() * scalar, controls.getRightDrivePower() * scalar);
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