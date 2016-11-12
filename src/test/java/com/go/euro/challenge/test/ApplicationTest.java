package com.go.euro.challenge.test;

import com.go.euro.challenge.Application;
import com.go.euro.challenge.entity.exception.ExitException;
import com.go.euro.challenge.test.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.HashMap;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
@Slf4j
public class ApplicationTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private String testCommandLineRun(String file, String expected) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder()
                .sources(Application.class)
                .run(new String[]{file});
        assertThat(ctx).isNotNull();
        String output = outputCapture.toString();
        assertThat(output).contains(expected);
        outputCapture.flush();
        ctx.close();
        return output;
    }

    @Test(expected = IllegalStateException.class)
    public void testNoArgs() {
        testCommandLineRun("", "EXIT:");
    }

    @Test(expected = IllegalStateException.class)
    public void errorFile() throws FileNotFoundException {
        testCommandLineRun("file_error", "EXIT:");
    }

    @Test
    public void okFile() throws FileNotFoundException, URISyntaxException {
        String output = testCommandLineRun(TestUtil.resourceToPath("testData"),
                "READY TO GO");
        assertThat(output).contains("Unique routes:\t10");
        assertThat(output).contains("Unique stations:\t24");
    }

    @Test(expected = IllegalStateException.class)
    public void errorSizeFile() throws FileNotFoundException, URISyntaxException {
        log.debug("file: {}", TestUtil.resourceToPath("errorData"));
        testCommandLineRun(TestUtil.resourceToPath("errorData"),
                "EXIT: There is incorrect number N of routes. Expected is 10, and there is 12");
    }
}