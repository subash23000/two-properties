package threads;


import data.*;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;

public class RunLego implements Runnable{
	// create motor objects connected to motor ports A and B
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);


	// create ultrasonic sensor object connected to sensor port S1
	EV3UltrasonicSensor sonic = new EV3UltrasonicSensor(SensorPort.S1);

	// get the distance measurement mode
	SampleProvider distance = sonic.getDistanceMode();

	// array to store the sensor readings
	float[] sample = new float[distance.sampleSize()];

	// controls the movement of the robot
	@Override
	public void run() {
		// TO DO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1);

				distance.fetchSample(sample, 0); // read dsitance from ultrasonic sensor
				float cm = sample[0] * 100; // Convert to centimeters

				// this checks if the robot should be running and if there is an obstacle in front of it, if there is an obstacle it will stop, go back and turn, otherwise it will keep moving forward	
				if (Robot.getRun()==1) {
				if (cm < 20) {
					//stops both motors immediately
					motorA.setPower(0);
					motorB.setPower(0);
					Thread.sleep(300); // wait for 300 milliseconds

					//go backward*
					motorA.setPower(-40);
					motorB.setPower(-40);
					Thread.sleep(600);// move backward for 600 milliseconds

					//turn robot
					motorA.setPower(40);
					motorB.setPower(-40);
					Thread.sleep(600);

				} else {
					//normal forward movement
					motorA.setPower(Robot.turnRight());
					motorB.setPower(Robot.turnLeft());
				}
				} else {
					// if robot should not be running, stop the motors
					motorA.setPower(0);
					motorB.setPower(0);
				}
			} catch (Exception e) {
				// safety stop if an error occurs
				motorA.setPower(0);
				motorB.setPower(0);	
				System.out.println("Error");
			}				
		}
	}
}