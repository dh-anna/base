package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import java.util.Timer;
import java.util.TimerTask;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private Timer timer;
	private TimerTask task;
	private long period = 1000L;
	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
		task = new TimerTask() {
        	public void run() {
            	controller.followSpeed();
        	}
    	};
		timer = new Timer();
		timer.schedule(task, 0, period);
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		if((speedLimit < 0) || (speedLimit > 500) || (controller.getReferenceSpeed() * 0.5 > speedLimit))
		{
			user.setAlarmState(true);
		}
		else
		{
			user.setAlarmState(false);
		}		
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}

}
