package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstans;
import frc.robot.subsystems.ShooterSubsystem;


public class IntakeCommand extends Command {
  
  private final ShooterSubsystem shooterSubsystem;
 
  private double StartTime;
  private double KeepTime;


  public IntakeCommand(ShooterSubsystem shooterSubsystem,double KeepTime) {
    this.shooterSubsystem = shooterSubsystem; 
    this.KeepTime = KeepTime;

   addRequirements(shooterSubsystem);
  }

 
  @Override
  public void initialize() {
    System.out.println("Intake started");
    //! Time the command start
    StartTime=Timer.getFPGATimestamp();
  }

  
  @Override
  public void execute() {
    
    if (PassingTime() <= KeepTime){
      //! Until the passing time equals the keep time.
        shooterSubsystem.setMotorUpper(ShooterConstans.OtoIntakeUpPower);
        shooterSubsystem.setMotorLower(ShooterConstans.OtoIntakeDownPower);
    }

    PassingTime();
  }
  public double PassingTime(){
    double PassingTime=shooterSubsystem.RealTime()-StartTime;
    return PassingTime;
    }
  @Override
  public void end(boolean interrupted) {
   shooterSubsystem.stopMotors();
   System.out.println("intake finished");
  }

  
  @Override
  public boolean isFinished() {
    if(PassingTime() > KeepTime+0.1){
      return true;
    }else{
      return false;
    }
  }
}

