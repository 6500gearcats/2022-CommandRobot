package frc.robot.commands.climb.individual;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ParkArm extends CommandBase {
    private final Climber m_climber;

    /**
   * Creates a new SetupForClimb
   * 
   * @param climber The climber subsystem this command will run on
   */
public ParkArm(Climber climber) {
    m_climber = climber;

    addRequirements(climber);
    }
    @Override
    public void execute(){
        m_climber.ParkArm();
    }
}
