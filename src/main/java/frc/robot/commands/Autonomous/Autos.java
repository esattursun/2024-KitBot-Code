package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.HashMap;

public class Autos {


  private final SendableChooser<Command> autonChooser;
  private final HashMap<String, Command> eventMap;

  public Autos() {
    

    eventMap = new HashMap<>();
    setMarkers();

    autonChooser = new SendableChooser<Command>();
    SmartDashboard.putData("Auton Chooser", autonChooser);
  }

  private void setMarkers() {}

  public Command getSelected() {
    return autonChooser.getSelected();
  }
}