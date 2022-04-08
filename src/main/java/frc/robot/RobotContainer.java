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
import frc.robot.subsystems.LEDSetter;
import frc.robot.subsystems.HubVision;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.AutoCommand;
// import frc.robot.commands.AutoPickup;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.KillClimber;
import frc.robot.commands.LiftBall;
import frc.robot.commands.PickupBall;
import frc.robot.commands.ReverseLift;
import frc.robot.commands.Shoot2BallsSlow;
import frc.robot.commands.ShootBallFast;
import frc.robot.commands.ShootBallSlow;
import frc.robot.commands.AlignToHub;
// import frc.robot.commands.VisionSteer;
import frc.robot.commands.VomitBall;
import frc.robot.commands.climb.groups.SetupForClimb;
import frc.robot.commands.climb.groups.TraversalClimb;
// import frc.robot.commands.climb.groups.Climb2Bars;
import frc.robot.commands.climb.groups.Climb3Bars;
// import frc.robot.commands.climb.groups.ClimbBar;
import frc.robot.commands.climb.individual.ParkArm;
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
  private final HubVision m_hubVision = new HubVision();
  private final LEDSetter m_ledStrip = new LEDSetter();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  XboxController m_gunnerController = new XboxController(OIConstants.kClimberControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    //Set LED strips to teal
    m_ledStrip.setEntireStripColor(Constants.LEDConstants.teal);

   // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new DefaultDrive(
            m_robotDrive, m_driverController::getLeftY, m_driverController::getRightX));

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    // XBox 1 - driver bindings

    new JoystickButton(m_driverController, Button.kA.value).whenPressed(new PickupBall(m_robotIntake));
    
    // new JoystickButton(m_driverController, Button.kRightBumper.value)
    // .whenPressed(new AutoPickup( m_robotIntake, m_robotDrive, m_driverController::getLeftY ));
    new JoystickButton(m_driverController, Button.kRightBumper.value)
    .whenHeld(new AlignToHub(m_robotDrive, m_hubVision));


    new JoystickButton(m_driverController, Button.kY.value).whenPressed(new VomitBall(m_robotIntake));
    new Trigger(() -> (m_driverController.getLeftTriggerAxis() > 0.5))
      .whenActive(new DefaultDrive(m_robotDrive, m_driverController::getLeftY, m_driverController::getRightX, DriveConstants.kMaxSpeed));
    
    new Trigger(() -> (m_driverController.getRightTriggerAxis() > 0.5))
      .whenActive(new DefaultDrive(m_robotDrive, m_driverController::getLeftY, m_driverController::getRightX, DriveConstants.kSlowSpeed));

    // XBox 2 - gunner bindings
    new JoystickButton(m_gunnerController, Button.kA.value).whenPressed(new SetupForClimb(m_robotClimber));
    new JoystickButton(m_gunnerController, Button.kY.value).whenPressed(new Climb3Bars(m_robotClimber));
    new JoystickButton(m_gunnerController, Button.kX.value).whenPressed(new TraversalClimb(m_robotClimber));

    new JoystickButton(m_gunnerController, Button.kB.value).whenPressed(new KillClimber(m_robotClimber));
    new JoystickButton(m_gunnerController, Button.kStart.value).whenPressed(new RetractArm(m_robotClimber));
    new JoystickButton(m_gunnerController, Button.kBack.value).whenPressed(new StowClimber(m_robotClimber));
    
    new Trigger(() -> (m_gunnerController.getLeftTriggerAxis() > 0.5))
     // .whenActive(new ShootBallSlow(m_robotShooter, m_robotElevator).withTimeout(0.8));
      .whileActiveOnce(new Shoot2BallsSlow(m_robotShooter, m_robotElevator));
       
    new Trigger(() -> (m_gunnerController.getRightTriggerAxis() > 0.5))
      .whileActiveOnce(new ShootBallFast(m_robotShooter, m_robotElevator));

    new Trigger(() -> m_gunnerController.getLeftY() < -0.5)
      .whenActive(new LiftBall(m_robotElevator, m_robotIntake));
      
    new Trigger(() -> m_gunnerController.getLeftY() > 0.5)
      .whileActiveContinuous(new ReverseLift(m_robotElevator, m_robotIntake, m_robotShooter));
    

    new Trigger(() -> m_gunnerController.getRightY() < -0.5)
    .whenActive((new VomitBall(m_robotIntake)));
      
    new Trigger(() -> m_gunnerController.getRightY() > 0.5)
    .whenActive((new PickupBall(m_robotIntake)));

    // new JoystickButton(m_driverController, OIConstants.kSlowModeTrigger)
    //   .whenPressed(() -> m_robotDrive.setMaxOutput(ClimberConstants.kMaxDriveSpeed))
    //   .whenReleased(() -> m_robotDrive.setMaxOutput(DriveConstants.kMaxSpeed));

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

  public Command getAutonomousCommand() {
    return new AutoCommand(m_robotDrive, m_robotShooter, m_robotElevator, m_robotClimber);
    }

  public Command AutoParkArm() {
    return new ParkArm(m_robotClimber);
  }
  }
