package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstans;



public class ShooterSubsystem extends SubsystemBase {
  private final PWMVictorSPX UpperShooterMotor;
  private final PWMVictorSPX LowerShooterMotor;

  
  public ShooterSubsystem() {
    UpperShooterMotor = new PWMVictorSPX(ShooterConstans.ShooterUpPwmId);
    LowerShooterMotor = new PWMVictorSPX(ShooterConstans.ShooterDownPwmId);
  }
 
  //! This function  set the power of the upper shooter motor
  public void setMotorUpper(double speed) {
   UpperShooterMotor.set(speed*ShooterConstans.ShootPower);
  }
  //! This function  set the power of the lower shooter motor
  public void setMotorLower(double speed) {
   LowerShooterMotor.set(speed*ShooterConstans.ShootPower);
  }
  //! This funtion stop the shooter motors
  public void stopMotors() {
   UpperShooterMotor.set(0);
   LowerShooterMotor.set(0);
  }

@Override
public void periodic() {
  RealTime();
  SmartDashboard.putNumber("Time", Timer.getFPGATimestamp());

}
public double RealTime(){
  double x= Timer.getFPGATimestamp();
  return x;
  }
}
