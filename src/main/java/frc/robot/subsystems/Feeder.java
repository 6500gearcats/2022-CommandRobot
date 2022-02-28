package frc.robot.subsystems;

public class Feeder {
    public void stopFeeder() {
        CANSparkMax m_feederMotor;
        m_feederMotor.set(0);   
}
