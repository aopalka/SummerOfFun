package frc.Lib.Controls;

import edu.wpi.first.wpilibj.Joystick;
import frc.Lib.Controls.Controllers.ControllerWrapper;
import frc.Lib.Controls.Controllers.PS4ControllerWrapper;
import frc.Lib.Controls.Controllers.XboxControllerWrapper;
import frc.robot.Constants.OperatorConstants;

/** Represents the Operator Interface (OI) for controlling the robot. */
public class OI {
  /** The driver joystick. */
  public final Joystick driver;
  public ControllerWrapper driver_controller;

  /** Constructs the Operator Interface (OI) with the specified driver port. */
  public OI() {
    /* USB Xbox Controllers */
    driver = new Joystick(OperatorConstants.kDriverControllerPort);
    /* Driver Buttons */
    init("xbox", 0);
  }
  public void init(String type, int driverPort) {
    switch (type) {
      case "xbox":
        driver_controller = new XboxControllerWrapper(driverPort);
        break;
      case "ps4":
        driver_controller = new PS4ControllerWrapper(driverPort);
      default:
        driver_controller = new XboxControllerWrapper(driverPort);
    }
  }
  public double getLeftValue() {
    return driver_controller.getLeftSide();
  }

  public double getRightValue() {
    return driver_controller.getRightSide();
  }

}

