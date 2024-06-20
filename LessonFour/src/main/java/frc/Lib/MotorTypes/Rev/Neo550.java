package frc.Lib.MotorTypes.Rev;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.FaultID;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.DriverStation;
import frc.Lib.MotorState;
import frc.Lib.MotorTypes.MotorWrapper;
import frc.robot.Constants;

public class Neo550 implements MotorWrapper {
    private CANSparkMax motor;
    private RelativeEncoder encoder;
    /* Feed Forward Setup */
    private SimpleMotorFeedforward velocityFeedForward;

    /**
     * 
     * @param id
     * @param canivorename
     */
    public Neo550(int id, String canivorename) {
        motor = new CANSparkMax(id, MotorType.kBrushless);
        double driveKS = 0.00;
        double driveKV = 0.00;
        double driveKA = 0.0;
        velocityFeedForward = new SimpleMotorFeedforward(driveKS, driveKV, driveKA);
        encoder = motor.getEncoder();
    }

    /** Configs Velocity Motor */
    public void configVelocityMotor() {
        motor.restoreFactoryDefaults();

        /* Motor Inverts and Neutral Mode */
        motor.setInverted(false);
        motor.setIdleMode(IdleMode.kBrake);
        /* Gear Ratio Config */
        encoder.setVelocityConversionFactor(Constants.MotorConstants.velocityGearRatio);
        encoder.setPositionConversionFactor(Constants.MotorConstants.velocityGearRatio);
        /* Current Limiting */
        motor.setSmartCurrentLimit((int) Constants.MotorConstants.velocityCurrentLimit);
        /* PID Config */
        SparkPIDController controller = motor.getPIDController();
        controller.setP(Constants.MotorConstants.velocityMotorKP);
        controller.setI(Constants.MotorConstants.velocityMotorKI);
        controller.setD(Constants.MotorConstants.velocityMotorKD);
        controller.setFF(0);
        /* Open and Closed Loop Ramping */
        motor.setClosedLoopRampRate(Constants.MotorConstants.closedLoopRamp);
        motor.setOpenLoopRampRate(Constants.MotorConstants.openLoopRamp);
        motor.enableVoltageCompensation(12);
        motor.burnFlash();
    }

    public void configPositionMotor() {
        motor.restoreFactoryDefaults();

        /* Motor Inverts and Neutral Mode */
        motor.setInverted(false);
        motor.setIdleMode(IdleMode.kBrake);
        /* Gear Ratio Config */
        encoder.setVelocityConversionFactor(Constants.MotorConstants.positionGearRatio);
        encoder.setPositionConversionFactor(Constants.MotorConstants.positionGearRatio);
        /* Current Limiting */
        motor.setSmartCurrentLimit((int) Constants.MotorConstants.positionCurrentLimit);
        /* PID Config */
        SparkPIDController controller = motor.getPIDController();
        controller.setP(Constants.MotorConstants.positionMotorKP);
        controller.setI(Constants.MotorConstants.positionMotorKI);
        controller.setD(Constants.MotorConstants.positionMotorKD);
        controller.setFF(0);
        /* Open and Closed Loop Ramping */
        motor.setClosedLoopRampRate(Constants.MotorConstants.closedLoopRamp);
        motor.setOpenLoopRampRate(Constants.MotorConstants.openLoopRamp);
        motor.enableVoltageCompensation(12);
        motor.burnFlash();
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
            double percentOutput = desiredState.speedMetersPerSecond / Constants.MotorConstants.maxSpeed;
            motor.set(percentOutput);

        } else {
            double velocity = desiredState.speedMetersPerSecond;
            double output = velocityFeedForward.calculate(velocity);
            SparkPIDController controller = motor.getPIDController();
            controller.setReference(output, ControlType.kVelocity, 0);
        }
        if (motor.getFault(FaultID.kSensorFault)) {
            DriverStation.reportWarning("Sensor Fault on Intake Motor ID:" + motor.getDeviceId(), false);
        }
    }

    /*
     * sets the position of the motor by setting the internal pid.
     *
     * @param rotations
     */
    public void setAngle(double rotations) {
        SparkPIDController controller = motor.getPIDController();
        controller.setReference(rotations, ControlType.kPosition, 0);
        if (motor.getFault(FaultID.kSensorFault)) {
            DriverStation.reportWarning(
                    "Sensor Fault on Intake Pivot Motor ID:" + motor.getDeviceId(), false);
        }
    }

    /**
     * gets the position of the motor in rotations
     *
     * @return position
     */
    public double getPosition() {
        return encoder.getPosition();
    }

    /**
     * sets the position of the motor given the desired rotations.
     *
     * @param absolutePosition
     */
    public void setPosition(double absolutePosition) {
        encoder.setPosition(absolutePosition);
    }

    /**
     * gets the velocity of the drive motor in rotations per second.
     *
     * @return velocity
     */
    public double getVelocity() {
        return encoder.getVelocity() * 60;
    }
}
