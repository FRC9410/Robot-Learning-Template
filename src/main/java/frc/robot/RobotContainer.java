// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.StateMachine;
import frc.robot.subsystems.StateMachine.RobotState;

/** Creates the state machine and controllers, and connects buttons to commands and states. */
public class RobotContainer {

  private final StateMachine stateMachine = new StateMachine();

  private final CommandXboxController driverController =
      new CommandXboxController(Constants.OI.DRIVER_CONTROLLER_PORT);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    // Hold A: run ExampleCommand. Letting go interrupts it, and its end() stops the motor.
    driverController
        .a()
        .whileTrue(
            new ExampleCommand(stateMachine.example, Constants.Example.COMMAND_VELOCITY_RPS));

    // Hold B: ask the state machine for RUNNING. Let go: go back to IDLE.
    driverController
        .b()
        .onTrue(new InstantCommand(() -> stateMachine.setWantedState(RobotState.RUNNING)))
        .onFalse(new InstantCommand(() -> stateMachine.setWantedState(RobotState.IDLE)));
  }

  /** Puts the robot back in its starting state. Called when teleop starts. */
  public void resetState() {
    stateMachine.setWantedState(RobotState.IDLE);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public StateMachine getStateMachine() {
    return stateMachine;
  }
}
