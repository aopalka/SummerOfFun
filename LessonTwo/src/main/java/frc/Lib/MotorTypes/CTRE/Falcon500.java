package frc.Lib.MotorTypes.CTRE;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import frc.Lib.Conversions;
import frc.Lib.MotorState;
import frc.Lib.MotorTypes.MotorWrapper;
import frc.robot.Constants;

public class Falcon500 implements MotorWrapper {
    private TalonFX motor;
    private TalonFXConfiguration config = new TalonFXConfiguration();
    /* drive motor control requests */
    private DutyCycleOut velocityDutyCycle = new DutyCycleOut(0);
    private VelocityVoltage velocityVoltage = new VelocityVoltage(0);
    /* angle motor control requests */
    private PositionVoltage positionVoltage = new PositionVoltage(0);
    /* Feed Forward Setup */
    private SimpleMotorFeedforward velocityFeedForward;
    /**
     * 
     * @param id
     * @param canivorename
     */
    public Falcon500(int id, String canivorename) {
        motor = new TalonFX(id, canivorename);
        double driveKS = 0.00;
        double driveKV = 0.00;
        double driveKA = 0.0;
        velocityFeedForward = new SimpleMotorFeedforward(driveKS, driveKV, driveKA);
    }

    /** Configs Velocity Motor */
    public void configVelocityMotor() {
        /* Motor Inverts and Neutral Mode */
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        /* Gear Ratio Config */
        config.Feedback.SensorToMechanismRatio = Constants.MotorConstants.velocityGearRatio;
        /* Current Limiting */
        config.CurrentLimits.SupplyCurrentLimitEnable = Constants.MotorConstants.velocityEnableCurrentLimit;
        config.CurrentLimits.SupplyCurrentLimit = Constants.MotorConstants.velocityCurrentLimit;
        config.CurrentLimits.SupplyCurrentThreshold = Constants.MotorConstants.velocityCurrentThreshold;
        config.CurrentLimits.SupplyTimeThreshold = Constants.MotorConstants.velocityCurrentThresholdTime;
        /* PID Config */
        config.Slot0.kP = Constants.MotorConstants.velocityMotorKP;
        config.Slot0.kI = Constants.MotorConstants.velocityMotorKI;
        config.Slot0.kD = Constants.MotorConstants.velocityMotorKD;
        /* Open and Closed Loop Ramping */
        config.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = Constants.MotorConstants.openLoopRamp;
        config.OpenLoopRamps.VoltageOpenLoopRampPeriod = Constants.MotorConstants.openLoopRamp;
        config.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = Constants.MotorConstants.closedLoopRamp;
        config.ClosedLoopRamps.VoltageClosedLoopRampPeriod = Constants.MotorConstants.closedLoopRamp;
        motor.getConfigurator().apply(config);
        motor.getConfigurator().setPosition(0.0);
    }

    public void configPositionMotor() {
        /** Swerve Position Motor Configurations */
        /* Motor Inverts and Neutral Mode */
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        /* Gear Ratio and Wrapping Config */
        config.Feedback.SensorToMechanismRatio = Constants.MotorConstants.positionGearRatio;
        config.ClosedLoopGeneral.ContinuousWrap = true;
        /* Current Limiting */
        config.CurrentLimits.SupplyCurrentLimitEnable = Constants.MotorConstants.positionEnableCurrentLimit;
        config.CurrentLimits.SupplyCurrentLimit = Constants.MotorConstants.positionCurrentLimit;
        config.CurrentLimits.SupplyCurrentThreshold = Constants.MotorConstants.positionCurrentThreshold;
        config.CurrentLimits.SupplyTimeThreshold = Constants.MotorConstants.positionCurrentThresholdTime;
        /* PID Config */
        config.Slot0.kP = Constants.MotorConstants.positionMotorKP;
        config.Slot0.kI = Constants.MotorConstants.positionMotorKI;
        config.Slot0.kD = Constants.MotorConstants.positionMotorKD;
        /* Open and Closed Loop Ramping */
        config.ClosedLoopRamps.withVoltageClosedLoopRampPeriod(Constants.MotorConstants.closedLoopRamp);
        config.OpenLoopRamps.withVoltageOpenLoopRampPeriod(Constants.MotorConstants.openLoopRamp);
        motor.getConfigurator().apply(config);
        motor.getConfigurator().setPosition(0.0);
    }

    /**
     * gets the velocity of the drive motor in rotations per second either using
     * percentage output (
     * dutycycleout ) or velocity control triggered by the isOpenLoop parameter
     *
     * @param desiredState current State
     * @param isOpenLoop   openLoop
     */
    public void setSpeed(MotorState desiredState, boolean isOpenLoop) {
        if (isOpenLoop) {
            velocityDutyCycle.Output = desiredState.speedMetersPerSecond / Constants.MotorConstants.maxSpeed;
            motor.setControl(velocityDutyCycle);
        } else {
            velocityVoltage.Velocity = Conversions.MPSToRPS(desiredState.speedMetersPerSecond, Constants.MotorConstants.circumference);
            velocityVoltage.FeedForward = velocityFeedForward.calculate(desiredState.speedMetersPerSecond);
            motor.setControl(velocityVoltage);
        }
    }

    /*
     * sets the position of the motor by setting the internal pid.
     *
     * @param rotations
     */
    public void setAngle(double rotations) {
        motor.setControl(positionVoltage.withPosition(rotations));
    }

    /**
     * gets the position of the motor in rotations
     *
     * @return position
     */
    public double getPosition() {
        return motor.getPosition().getValue();
    }

    /**
     * sets the position of the motor given the desired rotations.
     *
     * @param absolutePosition
     */
    public void setPosition(double absolutePosition) {
        motor.setPosition(absolutePosition);
    }

    /**
     * gets the velocity of the drive motor in rotations per second.
     *
     * @return velocity
     */
    public double getVelocity() {
        return motor.getVelocity().getValue();
    }
}
