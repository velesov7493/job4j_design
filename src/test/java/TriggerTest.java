import org.junit.Test;

import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void logicSuccess() {
        assertEquals(1, Trigger.logic());
    }

}