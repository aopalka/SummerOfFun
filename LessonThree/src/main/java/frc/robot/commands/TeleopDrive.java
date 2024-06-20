// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopDrive extends Command {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveSubsystem m_drive;
  public DoubleSupplier leftSideInput, rightSideInput;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TeleopDrive(DriveSubsystem drive, DoubleSupplier leftSideInput, DoubleSupplier rightSideInput) {
    m_drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);

    this.leftSideInput = leftSideInput;
    this.rightSideInput = rightSideInput;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftDeadbandedControl = MathUtil.applyDeadband(leftSideInput.getAsDouble(), 0.1);
    double rightDeadbandedControl = MathUtil.applyDeadband(rightSideInput.getAsDouble(), 0.1);
    m_drive.drive(leftDeadbandedControl, rightDeadbandedControl);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
