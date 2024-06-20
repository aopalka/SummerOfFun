// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Lib.MotorState;
import frc.Lib.MotorTypes.CTRE.Falcon500;

public class DriveSubsystem extends SubsystemBase {
  private final Falcon500 leftSideMotor;
  private final Falcon500 rightSideMotor;
  private final String canbusname = "";
  private final int LeftMotorCanID = 0;
  private final int RightMotorCanID = 1;
  /** Creates a new ExampleSubsystem. */
  public DriveSubsystem() {
      leftSideMotor = new Falcon500(LeftMotorCanID,canbusname);
      rightSideMotor = new Falcon500(RightMotorCanID, canbusname);
  }


  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  /**
   * Assigns the motor states you want the motors to be in
   * Left - Speed in mps
   * Right - Speed in mps
   * Where 1 is maxSpeed.
   * @param lSpeed
   * @param rSpeed
   */
  public void drive(double lSpeed, double rSpeed) {
    MotorState leftState = new MotorState(lSpeed, null);
    MotorState rightState = new MotorState(lSpeed, null);
    leftSideMotor.setSpeed(leftState, true);
    rightSideMotor.setSpeed(rightState, true);
  }

  /**
   * Stops the motors properly.
   */
  public void stopMotor(){
    leftSideMotor.stopMotor();
    rightSideMotor.stopMotor();
  }
}
