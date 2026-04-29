package threads;


import data.*;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.port.SensorPort;
import lejos.robotics.SampleProvider;

public class RunLego implements Runnable{
    UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
    UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);

	EV3UltrasonicSensor sonic = new EV3UltrasonicSensor(SensorPort.S1);
	SampleProvider distance = sonic.getDistanceMode();
	float[] sample = new float[distance.sampleSize()];

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1);

				distance.fetchSample(sample, 0);
				float cm = sample[0] * 100; // Convert to centimeters

				if (Robot.getRun()==1) {
				if (cm < 20) {
					//obstacle detected
					motorA.setPower(0);
					motorB.setPower(0);
					Thread.sleep(300);

					//go backward*
					motorA.setPower(-40);
					motorB.setPower(-40);
					Thread.sleep(600);

					//turn
					motorA.setPower(40);
					motorB.setPower(-40);
					Thread.sleep(600);

				} else {
					//normal movement
					motorA.setPower(Robot.turnRight());
					motorB.setPower(Robot.turnLeft());
				}
				} else {
					motorA.setPower(0);
					motorB.setPower(0);
				}
			} catch (Exception e) {
				motorA.setPower(0);
				motorB.setPower(0);	
				System.out.println("Error");
			}				
	}
	}
}
