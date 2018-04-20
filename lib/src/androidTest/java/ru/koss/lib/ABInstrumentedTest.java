package ru.koss.lib;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.koss.lib.config.ABConfig;
import ru.koss.lib.config.ABConfigImpl;
import ru.koss.lib.tests.ButtonTextExperiment;

import static org.junit.Assert.assertEquals;

/**
 * powered by koss
 */
@RunWith(AndroidJUnit4.class)
public class ABInstrumentedTest {

    Context appContext;
    ABRepository repository;
    ABConfig config;

    @Before
    public void setup() {
        appContext = InstrumentationRegistry.getTargetContext();
        config = new ABConfigImpl();
        config.init(appContext);
        config.clear();
    }

    @Test(expected = IllegalStateException.class)
    public void checkConfigVersion() throws Exception {
        repository = new ABRepositoryImpl("2.0", config);
    }


    @Test
    public void selectExperimentButtonDef() throws Exception {
        repository = new ABRepositoryImpl("1.0", config);
        assertEquals("def button", repository.getButtonTextExperiment().getButtonText());
    }

    @Test
    public void selectExperimentButtonA() throws Exception {
        config.setValue(ButtonTextExperiment.DEF_EXPERIMENT.getExperimentKey(),"A");
        repository = new ABRepositoryImpl("1.0", config);
        assertEquals("blue button", repository.getButtonTextExperiment().getButtonText());
    }
}
