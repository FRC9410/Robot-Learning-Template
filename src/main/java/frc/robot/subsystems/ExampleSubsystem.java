package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * An example subsystem: one TalonFX motor, like a roller or flywheel.
 *
 * <p>A subsystem is one part of the robot (intake, shooter, arm...). It owns that part's hardware
 * and has simple methods like {@link #setVelocity(double)} and {@link #stop()}. It should NOT decide
 * when to do things. That's the job of commands.
 *
 * <p>To make a new subsystem: copy this file, rename it, add a group for it in {@code Constants},
 * and create it in {@code RobotContainer}.
 */
public class ExampleSubsystem extends SubsystemBase {

  // The motor. Note the second argument: it has to say which CAN bus the motor is on.
  private final TalonFX motor =
      new TalonFX(Constants.Example.MOTOR_CAN_ID, Constants.CanBus.CANIVORE_BUS);

  // Control requests. Make them once and reuse them, instead of making new ones every loop.
  private final VelocityVoltage velocityRequest = new VelocityVoltage(0).withSlot(0);
  private final VoltageOut voltageRequest = new VoltageOut(0);
  private final NeutralOut stopRequest = new NeutralOut();

  private double targetVelocityRps = 0;

  public ExampleSubsystem() {
    TalonFXConfiguration config = new TalonFXConfiguration();

    config.MotorOutput.NeutralMode = Constants.Example.NEUTRAL_MODE;
    config.MotorOutput.Inverted =
        Constants.Example.MOTOR_INVERTED
            ? InvertedValue.Clockwise_Positive
            : InvertedValue.CounterClockwise_Positive;

    config.CurrentLimits.StatorCurrentLimit = Constants.Example.STATOR_CURRENT_LIMIT_AMPS;
    config.CurrentLimits.StatorCurrentLimitEnable = true;

    config.Slot0.kP = Constants.Example.KP;
    config.Slot0.kI = Constants.Example.KI;
    config.Slot0.kD = Constants.Example.KD;
    config.Slot0.kS = Constants.Example.KS;
    config.Slot0.kV = Constants.Example.KV;

    motor.getConfigurator().apply(config);
  }

  /** Spins the motor at a speed, in rotations per second. The TalonFX holds that speed itself. */
  public void setVelocity(double velocityRps) {
    targetVelocityRps = velocityRps;
    motor.setControl(velocityRequest.withVelocity(velocityRps));
  }

  /** Sends a fixed voltage to the motor (-12 to 12). Useful for testing before gains are tuned. */
  public void setVoltage(double volts) {
    targetVelocityRps = 0;
    motor.setControl(voltageRequest.withOutput(volts));
  }

  /** Stops the motor. It brakes or coasts depending on NEUTRAL_MODE. */
  public void stop() {
    targetVelocityRps = 0;
    motor.setControl(stopRequest);
  }

  /** How fast the motor is actually spinning, in rotations per second. */
  public double getVelocityRps() {
    return motor.getVelocity().getValueAsDouble();
  }

  /** true when the motor is within 1 rotation per second of the speed it was asked for. */
  public boolean isAtTargetVelocity() {
    return Math.abs(getVelocityRps() - targetVelocityRps) < 1.0;
  }

  /** Runs every 20 ms. Use it to put values on the dashboard, not to move motors. */
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Example/Velocity RPS", getVelocityRps());
    SmartDashboard.putNumber("Example/Target RPS", targetVelocityRps);
    SmartDashboard.putBoolean("Example/At Target", isAtTargetVelocity());
  }
}
