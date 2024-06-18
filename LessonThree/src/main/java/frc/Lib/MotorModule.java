package frc.Lib;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.Lib.MotorTypes.MotorTypeEnum;
import frc.Lib.MotorTypes.MotorWrapper;
import frc.Lib.MotorTypes.CTRE.Falcon500;
import frc.Lib.MotorTypes.CTRE.KrakenX60;
import frc.Lib.MotorTypes.Rev.Neo;
import frc.Lib.MotorTypes.Rev.Neo550;
import frc.Lib.MotorTypes.Rev.Vortex;

public class MotorModule {
    public MotorWrapper motor;
    /**
     * Initializes a motor module that can be reused and creates hot-swappable "motors"
     * @param type
     * @param id
     * @param canbus
     */
    public MotorModule(MotorTypeEnum type, int id, String canbus) {
        switch (type) {
            case Falcon500:
                motor = new Falcon500(id,canbus);
                break;       
            case KrakenX60:
                motor = new KrakenX60(id,canbus);
                break;       
            case Vortex:
                motor = new Vortex(id,canbus);
                break;  
            case Neo550:
                motor = new Neo550(id,canbus);
                break;  
            case Neo:
                motor = new Neo(id,canbus);
                break;  
            default:
                motor = new Falcon500(id,canbus);
                break;       
        }
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
     * sets the  motor's speed given the desired state's velocity in
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

    /**
     * Gets the motor's properties / state ( velocity and position )
     * @return MotorState
     */
    public MotorState getMotorState(){
        double vel = motor.getVelocity();
        Rotation2d pos = Rotation2d.fromRotations(motor.getPosition());
        return new MotorState(vel,pos);
    }
}
