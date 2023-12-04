package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    private double x;
    private double y;
    private double area;
    private String name;
    private NetworkTable table = NetworkTableInstance.getDefault().getTable("Limelight");
    private NetworkTableEntry tx = table.getEntry("tx");
    private NetworkTableEntry ty = table.getEntry("ty");
    private NetworkTableEntry ta = table.getEntry("ta");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    // how many degrees back is your limelight rotated from perfectly vertical?
    double limelightMountAngleDegrees;
    // distance from the center of the Limelight lens to the floor
    double limelightLensHeightInches;
    // distance from the target to the floor
    double goalHeightInches = 60;   

    public Limelight(String name, double mountAngle, double lensHeight){
        this.name = name;
        limelightMountAngleDegrees = mountAngle;
        limelightLensHeightInches = lensHeight;
    }

    public void updateValues(){
        table = NetworkTableInstance.getDefault().getTable("Limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        x = tx.getDouble(0.0);
        y = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
    }
    
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getArea(){
        return area;
    }

    public void camDashDetails(){
        //post to smart dashboard periodically
        SmartDashboard.putNumber("X", x);
        SmartDashboard.putNumber("Y", y);
        SmartDashboard.putNumber("Area", area);
        SmartDashboard.putNumber("Angle to Goal Degrees", getAngleToGoalDegrees());
        SmartDashboard.putNumber("Angle to Goal Radians", getAngleToGoalRadians());
        SmartDashboard.putNumber("Distance to Goal Inches", getDistanceToGoal());
    } 

    //Gets the angle between the limelight and goal in degrees
    public double getAngleToGoalDegrees(){
        return limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    }

    //Gets the angle between the limelight and the goal in radians
    public double getAngleToGoalRadians(){
        return getAngleToGoalDegrees() * (3.14159 / 180.0);
    }

    //Calculates the distance from the limelight to the goal
    public double getDistanceToGoal(){
        return (goalHeightInches - limelightLensHeightInches) / Math.tan(getAngleToGoalRadians());
    }    
}