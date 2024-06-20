package frc.Lib.Controls.Controllers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class XboxControllerWrapper extends XboxController implements ControllerWrapper {
    public final int leftStickAxis = XboxController.Axis.kLeftY.value;
    public final int rightStickAxis = XboxController.Axis.kLeftY.value;
    /**
     * Contains Left Joystick , Right Joystick , POV , triangle, circle, cross,
     * square buttons.
     *
     * @param port
     */
    public XboxControllerWrapper(int port) {
        super(port);
    }

    public double getLeftSide(){
        double leftStick = super.getRawAxis(leftStickAxis);
        return leftStick;
    }
    public double getRightSide(){
        double rightStick = super.getRawAxis(rightStickAxis);
        return rightStick;
    }
}
