package frc.Lib.MotorTypes;

import frc.Lib.MotorState;

public interface MotorWrapper {
    public default void setAngle(double state){};
    public default void setSpeed(MotorState currentState, boolean isOpenLoop){};
    public default void stopMotor(){}
}
