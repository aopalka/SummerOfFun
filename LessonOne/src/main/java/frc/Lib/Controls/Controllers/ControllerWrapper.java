package frc.Lib.Controls.Controllers;
/** Controller Wrapper */
public interface ControllerWrapper {
    public default double getLeftSide(){ return 0;}
    public default double getRightSide(){ return 0;}
}
