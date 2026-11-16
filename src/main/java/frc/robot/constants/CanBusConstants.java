package frc.robot.constants;

import com.ctre.phoenix6.CANBus;

/**
 * The CAN buses on the robot.
 *
 * <p>Every CTRE device (TalonFX, CANcoder, Pigeon, CANdle...) has to be told which bus it is on.
 * If the bus name is wrong, the code still builds and deploys, but the device never responds and
 * Driver Station shows CAN errors. Check the name in Phoenix Tuner X.
 */
public class CanBusConstants {
  /** The CANivore. Our 2026 robot names it "canivore". Most motors go here. */
  public static final CANBus CANIVORE_BUS = new CANBus("canivore");

  /** The roboRIO's built-in CAN port. Use this for anything plugged into the RIO directly. */
  public static final CANBus RIO_BUS = new CANBus("rio");
}
