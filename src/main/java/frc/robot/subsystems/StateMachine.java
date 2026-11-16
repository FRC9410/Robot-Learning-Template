package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Holds the robot's state and decides what every subsystem does in that state.
 *
 * <p>Other code asks for a state with {@link #setWantedState(RobotState)}. Every 20 ms the state
 * machine checks whether it can switch to that state, then runs the code for the current state.
 * This is the same pattern as our 2026 robot.
 *
 * <p>To add a state:
 *
 * <ol>
 *   <li>Add it to {@link RobotState}.
 *   <li>Add a case for it in {@link #onEnterState()} and/or {@link #executeState()}.
 *   <li>If it needs a rule, like "only shoot when the flywheel is up to speed", add it to
 *       {@link #canTransitionTo(RobotState)}.
 *   <li>Ask for it from a button in {@code RobotContainer}.
 * </ol>
 */
public class StateMachine extends SubsystemBase {

  public enum RobotState {
    /** Doing nothing. Commands like ExampleCommand are free to run the subsystems. */
    IDLE,
    /** Running the example subsystem at RUNNING_VELOCITY_RPS. */
    RUNNING
  }

  private RobotState wantedState = RobotState.IDLE;
  private RobotState currentState = RobotState.IDLE;
  private RobotState previousState = RobotState.IDLE;

  // --- Subsystems ---
  // Create every subsystem here, once. Other code reaches them through the state machine.
  public final ExampleSubsystem example = new ExampleSubsystem();

  public StateMachine() {}

  @Override
  public void periodic() {
    handleStateTransitions();
    executeState();

    SmartDashboard.putString("Robot State", currentState.name());
    SmartDashboard.putString("Wanted State", wantedState.name());
  }

  /** Asks the robot to switch to a state. It switches on the next loop, if it's allowed to. */
  public void setWantedState(RobotState state) {
    wantedState = state;
  }

  public RobotState getCurrentState() {
    return currentState;
  }

  public RobotState getPreviousState() {
    return previousState;
  }

  private void handleStateTransitions() {
    if (currentState != wantedState && canTransitionTo(wantedState)) {
      previousState = currentState;
      currentState = wantedState;
      onEnterState();
    }
  }

  /** Rules for switching states. Return false to stay in the current state for now. */
  private boolean canTransitionTo(RobotState target) {
    return true;
  }

  /** Runs once, right when the robot switches into a state. */
  private void onEnterState() {
    switch (currentState) {
      case IDLE:
        example.stop();
        break;
      case RUNNING:
        example.setVelocity(Constants.Example.RUNNING_VELOCITY_RPS);
        break;
      default:
        break;
    }
  }

  /** Runs every 20 ms for the current state. Put code here that has to keep checking things. */
  private void executeState() {
    switch (currentState) {
      case IDLE:
        // Nothing to do every loop. Leaving IDLE empty lets commands control the subsystems.
        break;
      case RUNNING:
        // Example: you could check example.isAtTargetVelocity() here and move to another state.
        break;
      default:
        break;
    }
  }
}
