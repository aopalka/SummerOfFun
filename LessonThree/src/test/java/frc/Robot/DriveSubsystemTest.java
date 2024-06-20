package frc.robot;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import frc.robot.subsystems.DriveSubsystem;

public class DriveSubsystemTest {
    static final double EPSILON = 1e-6;
    @Test
    void driveTest_Forward(){
        DriveSubsystem drive = new DriveSubsystem();
        drive.drive(1, 1);
        assertTrue(drive.getDriveVelocity() < 1);
    }
    @Test
    void driveTest_Back(){
        DriveSubsystem drive = new DriveSubsystem();
        drive.drive(-1, -1);
        assertTrue(drive.getDriveVelocity() < 1);
    }
    @Test
    void driveTest_TurnRight(){
        DriveSubsystem drive = new DriveSubsystem();
        drive.drive(1, -1);
        assertTrue(drive.getDriveVelocity() < 1);
    }
    @Test
    void driveTest_TurnLeft(){
        DriveSubsystem drive = new DriveSubsystem();
        drive.drive(-1, 1);
        assertTrue(drive.getDriveVelocity() < 1);
    }
}
