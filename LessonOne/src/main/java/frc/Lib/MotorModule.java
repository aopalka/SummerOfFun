package frc.Lib;

import frc.Lib.MotorTypes.CTRE.Falcon500;

public class MotorModule {
    public Falcon500 motor;

    /**
     * Initializes a motor module that can be reused and creates hot-swappable
     * "motors"
     * 
     * @param type
     * @param id
     * @param canbus
     */
    public MotorModule(int id, String canbus) {
        motor = new Falcon500(id, canbus);
    }

    /**
     * sets the desired state of control the motor.
     *
     * @param currentState
     * @param controlType
     */
    public void setDesiredState(MotorState currentState, MotorControlEnum controlType) {
        switch (controlType) {
            case PositionVoltage:
                setPosition(currentState);
                break;
            case DutyCycleOut:
                setSpeed(currentState, true);
                break;
            default:
                setSpeed(currentState, false);
                break;
        }
    }

    /**
     * sets the motor's speed given the desired state's velocity in
     * rotations/second
     *
     * @param state
     * @param isOpenLoop
     */
    private void setSpeed(MotorState currentState, boolean isOpenLoop) {
        motor.setSpeed(currentState, isOpenLoop);
    }

    /**
     * sets the angle motor's position from desired rotations
     *
     * @param state
     */
    private void setPosition(MotorState state) {
        motor.setAngle(state.angle.getRotations());
    }

    /** Stops the motors properly. */
    public void stopMotor() {
        motor.stopMotor();
    }
}
