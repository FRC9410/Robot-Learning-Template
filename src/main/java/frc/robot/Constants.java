package frc.robot;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.signals.NeutralModeValue;

/**
 * Every constant for the robot, grouped by what it's for. Use them like
 * {@code Constants.Example.MOTOR_CAN_ID}.
 *
 * <p>Keep numbers here instead of typing them into subsystems and commands, so they're easy to
 * find and change. When you add a subsystem, add a group for it below.
 */
public final class Constants {

  /** Controller ports, as set in Driver Station. */
  public static final class OI {
    public static final int DRIVER_CONTROLLER_PORT = 0;
  }

  /**
   * The CAN buses on the robot.
   *
   * <p>Every CTRE device (TalonFX, CANcoder, Pigeon, CANdle...) has to be told which bus it is on.
   * If the bus name is wrong, the code still builds and deploys, but the device never responds and
   * Driver Station shows CAN errors. Check the name in Phoenix Tuner X.
   */
  public static final class CanBus {
    /** The CANivore. Our 2026 robot names it "canivore". Most motors go here. */
    public static final CANBus CANIVORE_BUS = new CANBus("canivore");

    /** The roboRIO's built-in CAN port. Use this for anything plugged into the RIO directly. */
    public static final CANBus RIO_BUS = new CANBus("rio");
  }

  /** Constants for {@link frc.robot.subsystems.ExampleSubsystem}. */
  public static final class Example {
    // --- Hardware ---
    /** The motor's CAN ID. Set it in Phoenix Tuner X. Every device on a bus needs a different ID. */
    public static final int MOTOR_CAN_ID = 20;

    /** true flips which way "positive" spins the motor. */
    public static final boolean MOTOR_INVERTED = false;

    /** Brake holds the motor still when it's stopped. Coast lets it spin freely. */
    public static final NeutralModeValue NEUTRAL_MODE = NeutralModeValue.Brake;

    /** Caps how much current the motor can draw, which protects the motor and the battery. */
    public static final double STATOR_CURRENT_LIMIT_AMPS = 40;

    // --- Velocity control gains (Slot 0) ---
    // Start with KV, then raise KP until the mechanism holds speed well. SysId can find these.
    public static final double KP = 0.1;
    public static final double KI = 0;
    public static final double KD = 0;
    /** Volts needed to overcome friction and just start moving. */
    public static final double KS = 0;
    /** Volts per rotation per second. About 0.12 for a Kraken or Falcon with no load. */
    public static final double KV = 0.12;

    // --- Setpoints ---
    /** Speed in motor rotations per second while ExampleCommand runs. */
    public static final double COMMAND_VELOCITY_RPS = 15;
  }
}
