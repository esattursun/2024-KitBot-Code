package frc.robot.commands.Autonomous;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstans;
import frc.robot.subsystems.ShooterSubsystem;


public class AutoSICommand extends Command {
  
  private final ShooterSubsystem shooterSubsystem;
 
  private double StartTime;
  private double ChargeTime;
  private double WorkingTimeAfterChargeTime;
  private double KeepTime;

  public AutoSICommand(ShooterSubsystem shooterSubsystem,double ChargeTime,double WorkingTimeAfterChargeTime,double KeepTime) {
    this.shooterSubsystem = shooterSubsystem; 
    this.ChargeTime = ChargeTime;
    this.WorkingTimeAfterChargeTime = WorkingTimeAfterChargeTime;
    this.KeepTime = KeepTime;

   addRequirements(shooterSubsystem);
  }

 
  @Override
  public void initialize() {
    System.out.println("shooting");
    //! Time the command start
    StartTime = Timer.getFPGATimestamp();
    
  
  }
 
  @Override
  public void execute() {
    //! Time from robot activated to now
    double RealTime = Timer.getFPGATimestamp();
    //! Time from command start to now
    double PassingTime = RealTime-StartTime;
    

    if (PassingTime <= ChargeTime) {
      //! Until the passing time equals the Charging time.
        shooterSubsystem.setMotorUpper(ShooterConstans.OtoShootChargePower);

    } else if (PassingTime >= ChargeTime && PassingTime <= ChargeTime+WorkingTimeAfterChargeTime) {
      //! From the end of the charging time to the end time.
        shooterSubsystem.setMotorLower(ShooterConstans.OtoShootDownPower);
        shooterSubsystem.setMotorUpper(ShooterConstans.OtoShootUpPower);
    } else if(PassingTime >= ChargeTime+WorkingTimeAfterChargeTime+0.1 && PassingTime <=  ChargeTime+WorkingTimeAfterChargeTime+KeepTime+0.5) {
      //! After the Shot
      shooterSubsystem.setMotorUpper(ShooterConstans.OtoIntakeUpPower);
      shooterSubsystem.setMotorLower(ShooterConstans.OtoIntakeDownPower);
      //  shooterSubsystem.stopMotors();
    }



    }
  

  @Override
  public void end(boolean interrupted) {
    
  }

  @Override
  public boolean isFinished() {
   return false;
  }
  
}