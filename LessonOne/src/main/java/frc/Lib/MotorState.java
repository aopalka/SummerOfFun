package frc.Lib;

import edu.wpi.first.math.geometry.Rotation2d;

public class MotorState {
  /** Speed of the wheel of the module. */
  public double speedMetersPerSecond;

  /** Angle of the motor. */
  public Rotation2d angle = Rotation2d.fromDegrees(0);
  /**
   * Constructs a SwerveModuleState.
   *
   * @param speedMetersPerSecond The speed of the wheel of the module.
   * @param angle The angle of the module.
   */
  public MotorState(double speedMetersPerSecond, Rotation2d angle) {
    this.speedMetersPerSecond = speedMetersPerSecond;
    this.angle = angle;
  }
}
