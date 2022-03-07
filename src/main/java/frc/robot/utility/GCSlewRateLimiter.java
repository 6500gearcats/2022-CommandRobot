package frc.robot.utility;

import edu.wpi.first.math.filter.SlewRateLimiter;

public class GCSlewRateLimiter extends SlewRateLimiter{

    private double m_slewrate = 0.0;

    public GCSlewRateLimiter(double rateLimit) {
        super(rateLimit);
        m_slewrate = rateLimit;
    }

    public GCSlewRateLimiter(double rateLimit, double initial) {
        super(rateLimit, initial);
        m_slewrate = rateLimit;
    }

    public double getRate() {
        return this.m_slewrate;
    }
    
}
