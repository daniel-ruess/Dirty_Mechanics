package org.dirtymechanics.frc.test;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import org.dirtymechanics.frc.test.control.ControlScheme;
import org.dirtymechanics.frc.test.control.SmartJoystick;
import org.dirtymechanics.frc.test.control.impl.StandardControlScheme;
import org.dirtymechanics.frc.test.drive.DriveTrain;
import org.dirtymechanics.frc.test.drive.Transmission;
import org.dirtymechanics.frc.test.shooter.Shooter;

public class Robot extends IterativeRobot {
    
    private static final int DRIVE_JAGUAR_LEFT = 1;
    private static final int DRIVE_JAGUAR_RIGHT = 2;
    private static final int SHOOTER_JAGUAR = 3;
    private static final int SHOOTER_SOLENOID_OUT = 1;
    private static final int SHOOTER_SOLENOID_IN = 2;
    private static final int COMPRESSOR_RELAY = 1;
    private static final int COMPRESSOR_SWITCH = 1;
    private static final int TRANSMISSION_SOL_A = 3;
    private static final int TRANSMISSION_SOL_B = 4;
    private static final int LED = 5;
    
    private final Jaguar leftJag = new Jaguar(DRIVE_JAGUAR_LEFT);
    private final Jaguar rightJag = new Jaguar(DRIVE_JAGUAR_RIGHT);
    private final Jaguar shooterJag = new Jaguar(SHOOTER_JAGUAR);
    
    private final Solenoid shooterPistonOut = new Solenoid(SHOOTER_SOLENOID_OUT);
    private final Solenoid shooterPistonIn = new Solenoid(SHOOTER_SOLENOID_IN);
    private final Solenoid transmissionA = new Solenoid(TRANSMISSION_SOL_A);
    private final Solenoid transmissionB = new Solenoid(TRANSMISSION_SOL_B);
    private final Solenoid led = new Solenoid(LED);
    
    private final Compressor compressor;
    private final SmartJoystick leftStick, rightStick, gamepad;
    private final ControlScheme controls;
    private final DriveTrain driveTrain;
    private final Shooter shooter;
    private final Transmission transmission;
    int a = 0;
        
    public Robot() {
        compressor = new Compressor(COMPRESSOR_SWITCH, COMPRESSOR_RELAY);
        leftStick = new SmartJoystick(1);
        rightStick = new SmartJoystick(2);
        gamepad = new SmartJoystick(3);
        controls = new StandardControlScheme(leftStick, rightStick, gamepad);
        transmission = new Transmission(transmissionB, transmissionA);
        driveTrain = new DriveTrain(leftJag, rightJag, transmission, controls);
        shooter = new Shooter(shooterJag, shooterPistonOut, shooterPistonIn, controls);
        compressor.start();
    }
    
    public void robotInit() {
        //driveTrain.shiftDown();
    }
    
    public void autonomousInit() {
    }
    
    /*public void autonomousPeriodic() {
        System.out.println("a" + a++);
        long time = System.currentTimeMillis();
        driveTrain.shiftDown();
        if (autonomousStart - time < 3000) {
            driveTrain.drive(0.4D, 0.4D);
        } else {
            driveTrain.drive(0D, 0D);
        }
        
        if (autonomousStart - time > 7000 && (autonomousStart - time) % 2000 < 250) {
            //TODO shoot
        } else {
            //TODO stop shooting
        }
    }*/
    
    public void teleopPeriodic() {
        led.set(true);
        driveTrain.drive();
        shooter.update();
    }
    
    public void disabledPeriodic() {
        led.set(true);
    }
}