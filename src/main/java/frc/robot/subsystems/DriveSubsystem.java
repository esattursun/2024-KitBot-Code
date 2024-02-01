package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstans;
import frc.robot.commands.Autonomous.Autos;



public class DriveSubsystem extends SubsystemBase {
    private PWMVictorSPX leftfront;
    private PWMVictorSPX leftback;
    private PWMVictorSPX rightfront;
    private PWMVictorSPX rightback;
    private final Autos autos=new Autos();
    
    public DriveSubsystem() {
        leftfront = new PWMVictorSPX(DriveConstans.leftfrontPwmId);
        leftback = new PWMVictorSPX(DriveConstans.leftbackPwmId);
        rightfront = new PWMVictorSPX(DriveConstans.rightfrontPwmId);
        rightback = new PWMVictorSPX(DriveConstans.rightbackPwmId);
    }

    @Override
    public void periodic() {
       RealTime();
    }
    
    public void setMotors(double leftSpeed, double rightSpeed) {
        leftfront.set(leftSpeed);
        leftback.set(leftSpeed);
        rightfront.set(-rightSpeed);
        rightback.set(-rightSpeed);
    }
    public void stop(){
        setMotors(0,0);
    }

    public double RealTime(){
    double x= Timer.getFPGATimestamp();
    return x;
    }
       public Command getAuton() {
        return autos.getSelected();
      }
}