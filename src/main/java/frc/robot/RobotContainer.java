// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.ClimberConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.PIDDrive;
import frc.robot.commands.KillClimber;
import frc.robot.commands.LiftBall;
import frc.robot.commands.PickupBall;
import frc.robot.commands.ShootBall;
import frc.robot.commands.climb.groups.SetupForClimb;
import frc.robot.commands.climb.groups.ClimbBar;
import frc.robot.commands.climb.individual.RetractArm;
import frc.robot.commands.climb.individual.StowClimber;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_robotDrive = new DriveTrain();
  private final Shooter m_robotShooter = new Shooter();
  private final Climber m_robotClimber = new Climber();
  private final Elevator m_robotElevator = new Elevator();
  private final Intake m_robotIntake = new Intake();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  XboxController m_climberController = new XboxController(OIConstants.kClimberControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

   // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new PIDDrive(
            m_robotDrive, m_driverController::getLeftY, m_driverController::getRightX));

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // XBox 0 - driver bindings
    new JoystickButton(m_driverController, OIConstants.kPickUpBallBinding).whenPressed(new PickupBall(m_robotIntake));
    new JoystickButton(m_driverController, OIConstants.kLiftBallBinding).whenPressed(new LiftBall(m_robotElevator, m_robotIntake));
    new JoystickButton(m_driverController, OIConstants.kFireShooter).whenPressed(new ShootBall(m_robotShooter, m_robotElevator));

    // XBox 1 - gunner bindings
    new JoystickButton(m_climberController, Button.kA.value).whenPressed(new SetupForClimb(m_robotClimber));
    new JoystickButton(m_climberController, Button.kY.value).whenPressed(new ClimbBar(m_robotClimber));
    new JoystickButton(m_climberController, Button.kB.value).whenPressed(new KillClimber(m_robotClimber));
    new JoystickButton(m_climberController, Button.kStart.value).whenPressed(new RetractArm(m_robotClimber));
    new JoystickButton(m_climberController, Button.kBack.value).whenPressed(new StowClimber(m_robotClimber));



    new JoystickButton(m_driverController, OIConstants.kSlowModeTrigger)
      .whenPressed(() -> m_robotDrive.setMaxOutput(ClimberConstants.kMaxDriveSpeed))
      .whenReleased(() -> m_robotDrive.setMaxOutput(DriveConstants.kMaxSpeed));

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
