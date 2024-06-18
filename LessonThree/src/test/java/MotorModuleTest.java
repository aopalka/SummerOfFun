package frc.Lib.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.wpi.first.hal.HAL;
import frc.Lib.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MotorModuleTest {
    static final double DELTA = 1e-2; // acceptable deviation range
    public MotorModule module;

    @BeforeEach
    void setup() {
        assert HAL.initialize(500, 0); // initialize the HAL, crash if failed
        module = new MotorModule(MotorTypeEnum.Falcon500, 0, "");
    }

    @SuppressWarnings("PMD.SignatureDeclareThrowsException")
    @AfterEach // this method will run after each test
    void shutdown() throws Exception {
        module.stopMotor();
    }

    /**
     * Tests the duty cycle output of (50%, 75% , and 100%) and maxSpeed
     */
    @Test
    void setDesiredStateTest_DutyCycle() {
        double desiredOutput = 0.5 * Constants.MotorConstants.maxSpeed;
        MotorState ms = new MotorState(desiredOutput);
        module.setDesiredState(ms, MotorControlEnum.DutyCycleOut);
        assertThat(module.getModuleState()).isLessThan(desiredOutput);

        desiredOutput = 0.75 * Constants.MotorConstants.maxSpeed;
        ms = new MotorState(desiredOutput);
        module.setDesiredState(ms, MotorControlEnum.DutyCycleOut);
        assertThat(module.getModuleState()).isLessThan(desiredOutput);

        desiredOutput = 1 * Constants.MotorConstants.maxSpeed;
        ms = new MotorState(1);
        module.setDesiredState(ms, MotorControlEnum.DutyCycleOut);
        assertThat(module.getModuleState()).isLessThan(desiredOutput);
    }

    /**
     * Tests the duty cycle output of (1)
     */
    @Test
    void setDesiredStateTest_VelocityVoltage() {
        MotorState ms = new MotorState(1);
        module.setDesiredState(ms, MotorControlEnum.VelocityVoltage);
        assertThat(module.getModuleState()).isLessThan(1);
    }
}
