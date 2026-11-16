package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * An example command: spins the example subsystem at a speed until the command ends.
 *
 * <p>A command is one action that runs over time. The scheduler calls its methods in this order:
 *
 * <ol>
 *   <li>{@link #initialize()} once when it starts.
 *   <li>{@link #execute()} every 20 ms while it runs.
 *   <li>{@link #isFinished()} every 20 ms. Returning true ends the command.
 *   <li>{@link #end(boolean)} once when it stops, either because it finished or because it was
 *       interrupted (button released, or another command took the subsystem).
 * </ol>
 */
public class ExampleCommand extends Command {

  private final ExampleSubsystem example;
  private final double velocityRps;

  /**
   * @param example the subsystem this command controls
   * @param velocityRps how fast to spin, in rotations per second
   */
  public ExampleCommand(ExampleSubsystem example, double velocityRps) {
    this.example = example;
    this.velocityRps = velocityRps;

    // Tells the scheduler this command uses the subsystem, so two commands can't
    // control the same motor at once. Always add every subsystem your command uses.
    addRequirements(example);
  }

  @Override
  public void initialize() {
    example.setVelocity(velocityRps);
  }

  @Override
  public void execute() {
    // The TalonFX keeps holding the speed on its own, so there's nothing to do every loop here.
    // A command that steers toward a target would update the motor here instead.
  }

  @Override
  public boolean isFinished() {
    // false = keep running until something stops it (like letting go of the button).
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    example.stop();
  }
}
