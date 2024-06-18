// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.Lib.MotorModule;
import frc.Lib.MotorState;
import frc.Lib.MotorTypes.MotorTypeEnum;
import frc.Lib.MotorTypes.CTRE.KrakenX60;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  MotorModule leftMotor;
  MotorModule rightMotor;
  public DriveTrainSubsystem() {
    leftMotor = new MotorModule(MotorTypeEnum.KrakenX60, 0, "rio");
    rightMotor = new MotorModule(MotorTypeEnum.KrakenX60, 1, "rio");

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

  public void Drive(double left, double right){
    leftMotor.setDesiredState(new MotorState(left*Constants.MotorConstants.maxSpeed, null), null);
    rightMotor.setDesiredState(new MotorState(right*Constants.MotorConstants.maxSpeed, null), null);
  }

}
