package frc.robot.constants;

/** Operator interface: controller ports and joystick settings. */
public class OIConstants {
  public static final int DRIVER_CONTROLLER_PORT = 0;
  public static final int OPERATOR_CONTROLLER_PORT = 1;

  /** Joystick values smaller than this are treated as 0, so a resting stick doesn't creep. */
  public static final double DEADBAND = 0.05;
}
