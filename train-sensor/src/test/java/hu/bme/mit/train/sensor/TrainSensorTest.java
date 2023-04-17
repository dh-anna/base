package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mocController;
	TrainSensor sensor;
	TrainUser mocUser;

    @Before
    public void before() {
        mocController = mock(TrainControllerImpl.class);
        mocUser = mock(TrainUserImpl.class);
        sensor = new TrainSensorImpl(mocController, mocUser); 
    }

    @Test
    public void SetSpeedLimitToNegative() {
        mocUser.setSpeedLimit(-1);

        verify(mocUser, times(1)).setAlarmState(true); 
    }
}
