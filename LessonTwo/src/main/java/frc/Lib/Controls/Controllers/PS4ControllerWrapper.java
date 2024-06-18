package frc.Lib.Controls.Controllers;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

public class PS4ControllerWrapper extends CommandPS4Controller implements ControllerWrapper {
    public final int leftStickAxis = PS4Controller.Axis.kLeftY.value;
    public final int rightStickAxis = PS4Controller.Axis.kLeftY.value;
    /**
     * Contains Left Joystick , Right Joystick , POV , triangle, circle, cross,
     * square buttons.
     *
     * @param port
     */
    public PS4ControllerWrapper(int port) {
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
