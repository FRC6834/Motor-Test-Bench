// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import required for XBox Controller
import edu.wpi.first.wpilibj.XboxController;

//imports required to use SPARK MAX Motor Controllers
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();


  //Motor Controller Object
  //CAN ID remains the same regardless of the motor being tested
  //If a BRUSHED motor is being test, motor type must be changed to kBrushed
  //Follow URL for more info: https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax
  private CANSparkMax conveyorMotor = new CANSparkMax(4, MotorType.kBrushless);
  private CANSparkMax intakeMotor = new CANSparkMax(5, MotorType.kBrushless);
  private CANSparkMax flyer1 = new CANSparkMax(8, MotorType.kBrushless);
  private CANSparkMax flyer2 = new CANSparkMax(7, MotorType.kBrushless);

  //XBox Controller Object
  //Constructor takes int parameter specifying the USB port that controller is plugged into on the laptop (left side is port 0)
  //Follow URL for more info: https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/XboxController.html
  private XboxController controller = new XboxController(0);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
    //When A is held, motor spins in + direction
    //When B is held, motor spins in - direction
    boolean A = controller.getAButton();
    boolean B = controller.getBButton();
    boolean Y = controller.getYButton();

    if (A){
      intakeMotor.set(-.5);
    }
    else{
      intakeMotor.set(0);
    }
    
    if(B){
      conveyorMotor.set(-0.3);
    }
    else{
      conveyorMotor.set(0);
    }

    if(Y){
      flyer1.set(-0.9);
      flyer2.set(0.9);
    }
    else{
      flyer1.set(0);
      flyer2.set(0);
    }
    
    /*if(spinForward){
      testMotor.set(.3);
      testMotor2.set(-.3);
    }
    else if(spinBackward){
      testMotor.set(-.3);
      testMotor2.set(.3);
    }
    else{
      testMotor.set(0);
      testMotor2.set(0);
    }  */
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}