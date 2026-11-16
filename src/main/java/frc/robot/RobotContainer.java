// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

/** Creates the subsystems and controllers, and connects buttons to commands. */
public class RobotContainer {

  // --- Subsystems ---
  // Create each subsystem here, once.
  private final ExampleSubsystem example = new ExampleSubsystem();

  // --- Controllers ---
  private final CommandXboxController driverController =
      new CommandXboxController(Constants.OI.DRIVER_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // Hold A: run ExampleCommand. Letting go interrupts it, and its end() stops the motor.
    driverController
        .a()
        .whileTrue(new ExampleCommand(example, Constants.Example.COMMAND_VELOCITY_RPS));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
