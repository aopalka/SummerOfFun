package frc.Lib;

public class Conversions {
      /**
   * @param wheelMPS Wheel Velocity: (in Meters per Second)
   * @param circumference Wheel Circumference: (in Meters)
   * @return Wheel Velocity: (in Rotations per Second)
   */
  public static double MPSToRPS(double wheelMPS, double circumference) {
    double wheelRPS = wheelMPS / circumference;
    return wheelRPS;
  }

}
