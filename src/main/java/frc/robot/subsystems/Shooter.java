package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{


  public final MotorController m_ShooterMotor = new CANSparkMax(ShooterConstants.kShooterMotorPort, MotorType.kBrushless);
  private boolean m_bBallFired = false; 
  private boolean m_bShooterAtSpeed = false;
  public Shooter() {}

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }

    public void setShooterSpeedSlow(){
      m_ShooterMotor.set(ShooterConstants.kShooterSpeedSlow);
    }
    
    public void stopShooter() {
      m_ShooterMotor.stopMotor();
    }

    public double shooterSpeed(){
      return m_ShooterMotor.get();
    }

    public boolean isBallFired() {
      double FireSpeed = shooterSpeed();
      m_bBallFired = FireSpeed < ShooterConstants.kBallFiredThreshold;
      return m_bBallFired;
    }

    public boolean shooterSpeedSetSlow(){
      double ShooterSpeed = shooterSpeed();
      m_bShooterAtSpeed = ShooterSpeed == ShooterConstants.kShooterSpeedSlow;
      return m_bShooterAtSpeed;
    }
    public void setShooterSpeedFast(){
      m_ShooterMotor.set(ShooterConstants.kShooterSpeedFast);
    }

    public boolean shooterSpeedSetFast(){
      double ShooterSpeed = shooterSpeed();
      m_bShooterAtSpeed = ShooterSpeed == ShooterConstants.kShooterSpeedFast;
      return m_bShooterAtSpeed;
    }

    public void reverseMotor() {
      m_ShooterMotor.set(-ShooterConstants.kShooterSpeedSlow);
    }

}
