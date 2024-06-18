// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public static class MotorConstants{
    public static double positionCurrentLimit = 35;
    public static double positionCurrentThreshold = 60;
    public static double positionCurrentThresholdTime = 0.1;
    public static boolean positionEnableCurrentLimit = true;
    public static double velocityCurrentLimit = 35;
    public static double velocityCurrentThreshold = 60;
    public static double velocityCurrentThresholdTime = 0.1;
    public static boolean velocityEnableCurrentLimit = true;
    public static double openLoopRamp = 0.25;
    public static double closedLoopRamp = 0.0;
    public static double velocityMotorKP = 0.05;
    public static double velocityMotorKI = 0.00;
    public static double velocityMotorKD = 0.00;
    public static double positionMotorKP = 0.05;
    public static double positionMotorKI = 0.00;
    public static double positionMotorKD = 0.00;
    public static double positionGearRatio = 1;
    public static double velocityGearRatio = 1;
    public static double maxSpeed = 4.5;
    public static double circumference = Units.inchesToMeters(4);

  }
}
