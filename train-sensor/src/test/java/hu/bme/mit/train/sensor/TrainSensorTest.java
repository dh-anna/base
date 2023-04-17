package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorTest {

    TrainController mocController;
	TrainSensor sensor;
	TrainUser mocUser;

    @Before
    public void before() {
        mocController = mock(TrainController.class);
        mocUser = mock(TrainUser.class);
        sensor = new TrainSensorImpl(mocController, mocUser); 
    }

    @Test
    public void SetSpeedLimitToNegative() {
        mocUser.setSpeedLimit(-1);

        verify(mocUser, times(1)).setAlarmState(true); 
    }

   @Test
   public void SetSpeedLimitMuchLessAsReferenceSpeed()
  {
      when(mocController.getReferenceSpeed()).thenReturn(150);

      mocUser.setSpeedLimit(50);

      verify(mocUser, times(1)).setAlarmState(true);   
  } 
}
