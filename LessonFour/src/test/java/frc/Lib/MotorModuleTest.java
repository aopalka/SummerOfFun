package frc.robot;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.Lib.MotorControlEnum;
import frc.Lib.MotorModule;
import frc.Lib.MotorState;
import frc.Lib.MotorTypes.MotorTypeEnum;

public class MotorModuleTest {
    static final double maxSpeed = 4.5;

    @Test
    void setDesiredStateTest_DutyCycle(){
        MotorModule mm = new MotorModule(MotorTypeEnum.Falcon500, 0, "");
        MotorState ms = new MotorState(maxSpeed, null);
        mm.setDesiredState(ms, MotorControlEnum.DutyCycleOut);
        assertTrue(mm.getMotorState().speedMetersPerSecond< 1);
    }
    @Test
    void setDesiredStateTest_VelocityVoltage(){
        MotorModule mm = new MotorModule(MotorTypeEnum.Falcon500, 0, "");
        MotorState ms = new MotorState(maxSpeed, null);
        mm.setDesiredState(ms, MotorControlEnum.VelocityVoltage);
        assertTrue(mm.getMotorState().speedMetersPerSecond< 1);
    }
    @Test
    void setDesiredStateTest_PositionVoltage(){
        MotorModule mm = new MotorModule(MotorTypeEnum.Falcon500, 0, "");
        double rotations = 0.25;
        MotorState ms = new MotorState(0, Rotation2d.fromRotations(rotations));
        mm.setDesiredState(ms, MotorControlEnum.PositionVoltage);
        assertTrue(mm.getMotorState().angle.getRotations() < rotations);
    }

}
