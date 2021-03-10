package ut.com.team.absurdum.bukshev.bitbucket.swiser;

import org.junit.Test;
import com.team.absurdum.bukshev.bitbucket.swiser.api.MyPluginComponent;
import com.team.absurdum.bukshev.bitbucket.swiser.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}