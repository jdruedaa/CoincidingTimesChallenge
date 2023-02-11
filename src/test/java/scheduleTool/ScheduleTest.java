package scheduleTool;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ScheduleUtils;

import java.util.List;

public class ScheduleTest {

    @Test
    public void ExampleCase1()
    {
        ScheduleTestNG scheduleTool = new ScheduleTestNG();
        String caseInput = "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00- 21:00\n" +
                "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00\n" +
                "ANDRES=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00";
        String answer = scheduleTool.answer(caseInput);
        List<String> keysAndCountedTimes = ScheduleUtils.getKeysAndCountedTimes(answer);
        Assert.assertEquals(keysAndCountedTimes.get(0),"RENE-ASTRID");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(1)), 2);
        Assert.assertEquals(keysAndCountedTimes.get(2),"RENE-ANDRES");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(3)), 2);
        Assert.assertEquals(keysAndCountedTimes.get(4),"ASTRID-ANDRES");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(5)), 3);
    }

    @Test
    public void ExampleCase2()
    {
        ScheduleTestNG scheduleTool = new ScheduleTestNG();
        String caseInput = "RENE=MO10:15-12:00,TU10:00-12:00,TH13:00-13:15,SA14:00-18:00,SU20:00-21:00\n" +
                "ASTRID=MO10:00-12:00,TH12:00-14:00,SU20:00-21:00";
        String answer = scheduleTool.answer(caseInput);
        List<String> keysAndCountedTimes = ScheduleUtils.getKeysAndCountedTimes(answer);
        Assert.assertEquals(keysAndCountedTimes.get(0),"RENE-ASTRID");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(1)), 3);
    }

    @Test
    public void MinutesTakenIntoAccount()
    {
        ScheduleTestNG scheduleTool = new ScheduleTestNG();
        String caseInput = "RENE=MO10:10-10:30,TU9:55-12:00,WE9:00-10:00,TH10:00-12:00\n" +
                "ASTRID=MO10:40-10:55,TU9:50-11:00,WE9:30-10:30,TH11:10-11:50";
        String answer = scheduleTool.answer(caseInput);
        List<String> keysAndCountedTimes = ScheduleUtils.getKeysAndCountedTimes(answer);
        Assert.assertEquals(keysAndCountedTimes.get(0),"RENE-ASTRID");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(1)), 3);
    }

    @Test
    public void NoCoincidingOfficeTimes()
    {
        ScheduleTestNG scheduleTool = new ScheduleTestNG();
        String caseInput = "RENE=MO10:00-12:00\n" +
                "ASTRID=MO1:00-2:00";
        Assert.assertEquals(scheduleTool.answer(caseInput),"No coinciding office times were found.");
    }

    @Test
    public void MoreThan3MembersWithCoincidingTimes()
    {
        ScheduleTestNG scheduleTool = new ScheduleTestNG();
        String caseInput = "RENE=MO10:00-12:00\n" +
                "ASTRID=MO10:00-12:00\n" +
                "ANDRES=MO10:00-12:00\n" +
                "DIEGO=MO10:00-12:00";
        String answer = scheduleTool.answer(caseInput);
        List<String> keysAndCountedTimes = ScheduleUtils.getKeysAndCountedTimes(answer);
        Assert.assertEquals(keysAndCountedTimes.get(0),"ANDRES-DIEGO");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(1)), 1);
        Assert.assertEquals(keysAndCountedTimes.get(2),"RENE-DIEGO");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(3)), 1);
        Assert.assertEquals(keysAndCountedTimes.get(4),"RENE-ASTRID");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(5)), 1);
        Assert.assertEquals(keysAndCountedTimes.get(6),"RENE-ANDRES");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(7)), 1);
        Assert.assertEquals(keysAndCountedTimes.get(8),"ASTRID-ANDRES");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(9)), 1);
        Assert.assertEquals(keysAndCountedTimes.get(10),"ASTRID-DIEGO");
        Assert.assertEquals(Integer.parseInt(keysAndCountedTimes.get(11)), 1);
    }
}
