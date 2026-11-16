package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * One place to reach every constants file.
 *
 * <p>Each line below points to a file in the {@code constants} folder, so you can write
 * {@code Constants.Example.MOTOR_CAN_ID} from anywhere. When you add a subsystem, make a
 * constants file for it and add a line here.
 */
public final class Constants {

  public static final Mode simMode = Mode.SIM;
  public static final Mode currentMode = RobotBase.isReal() ? Mode.REAL : simMode;

  public static enum Mode {
    REAL,
    SIM,
    REPLAY
  }

  public static final class OI extends frc.robot.constants.OIConstants {}
  public static final class CanBus extends frc.robot.constants.CanBusConstants {}
  public static final class Example extends frc.robot.constants.ExampleConstants {}
}
