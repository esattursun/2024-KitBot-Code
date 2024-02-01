package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ShooterSubsystem;


public class ColorIntakeCommand extends Command {
  private final ColorSensor colorsensor = new ColorSensor();
  private final ShooterSubsystem shooterSubsystem;
  private double KeepingTime;
  private double ChargingTime;
  private double ShotTime;

  private double NotePosition=0;
 /*
  * note positions ;
  * 0 = nothing in there
  * 1 = it saw
  * 2 = it was intake
  */
  public ColorIntakeCommand(ShooterSubsystem shooterSubsystem,double KeepingTime,double ChargingTime,double ShotTime) {
    this.shooterSubsystem = shooterSubsystem; 
    this.KeepingTime = KeepingTime;
    this.ChargingTime = ChargingTime;
    this.ShotTime = ShotTime;


   addRequirements(shooterSubsystem);
  }

 
  @Override
  public void initialize() {
    colorsensor.m_colorMatcher.addColorMatch(colorsensor.kBlueTarget);
    colorsensor.m_colorMatcher.addColorMatch(colorsensor.kGreenTarget);
    colorsensor.m_colorMatcher.addColorMatch(colorsensor.kRedTarget);
    colorsensor.m_colorMatcher.addColorMatch(colorsensor.kYellowTarget);   
    colorsensor.m_colorMatcher.addColorMatch(colorsensor.kOrangeTarget);
    
  }

  
  @Override
  public void execute() {

    if(colorsensor.colorString=="unkown"){
      //! While the sensor does not see orange

      NotePosition=0;

    }else if(colorsensor.colorString == "Orange" && NotePosition==0){
      //! when the sensor sees orange

      NotePosition=1;

    }else if(colorsensor.colorString == "Orange" && NotePosition==1){
      //! when the sensor sees orange

      new SequentialCommandGroup(
       new WaitCommand(1),
       new IntakeCommand(shooterSubsystem, KeepingTime)
      );

      NotePosition =2;

    }else if(colorsensor.colorString == "Orange" && NotePosition==2){
      //! after intake
      
      new SequentialCommandGroup(
       new WaitCommand(1),
       new ShootCommand(shooterSubsystem, ChargingTime, ShotTime)
      );
      
      NotePosition=0;
    }

 
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

