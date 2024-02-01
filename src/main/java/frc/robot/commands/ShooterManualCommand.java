package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;


public class ShooterManualCommand extends Command {
  

  private final ShooterSubsystem shooterSubsystem;
  private final double ShooterUpSpeed;
  private final double ShooterDownSpeed;

  public ShooterManualCommand(ShooterSubsystem shooterSubsystem, double ShooterUpSpeed,double ShooterDownSpeed) {
    this.shooterSubsystem = shooterSubsystem; 
    this.ShooterUpSpeed = ShooterUpSpeed;
    this.ShooterDownSpeed=ShooterDownSpeed;

   addRequirements(shooterSubsystem);
  }

 
  @Override
  public void initialize() { 

    System.out.println("shooting");
  }

  
  @Override//
  public void execute() {
   shooterSubsystem.setMotorUpper(ShooterUpSpeed);
   shooterSubsystem.setMotorLower(ShooterDownSpeed);
  }

  
  @Override
  public void end(boolean interrupted) {
    shooterSubsystem.stopMotors();
  }

  
  @Override
  public boolean isFinished() {
   return false;
  }
  
}
 