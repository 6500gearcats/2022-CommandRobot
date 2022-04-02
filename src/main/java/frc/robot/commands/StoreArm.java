package frc.robot.commands;

import frc.robot.Constants.ClimberConstants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class StoreArm extends CommandBase {

      private final Climber m_climber;
    
      /**
       * Tilts the arm back so that it does not block the shooter
       * 
       * @param climber The climber subsystem this command will run on
       */
      public StoreArm(Climber climber) {
        m_climber = climber;
      
        addRequirements(m_climber);
      }
    
      @Override
      public void initialize() {
        m_climber.tiltRobot(ClimberConstants.kStoreArmSpeed);
      }
    
      
      @Override
      public void execute() {
    
      }
    
      // Called once after isFinished returns true
      @Override
      public void end(boolean interrupted) {
        m_climber.stopTilt();
      }
    

}
