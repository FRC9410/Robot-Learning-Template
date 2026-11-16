package frc.robot.constants;

import com.ctre.phoenix6.signals.NeutralModeValue;

/**
 * Constants for {@link frc.robot.subsystems.ExampleSubsystem}.
 *
 * <p>Give each subsystem its own constants file, then add it to {@link frc.robot.Constants}. Keep
 * numbers here instead of typing them into the subsystem, so they're easy to find and change.
 */
public class ExampleConstants {
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
