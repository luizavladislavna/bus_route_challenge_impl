package com.go.euro.challenge.test;

import com.go.euro.challenge.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.rule.OutputCapture;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by tymoshenkol on 11-Nov-16.
 */
@Slf4j
public class ApplicationTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private String testCommandLineRun(String file, String expected) {
        SpringApplication.run(Application.class, new String[]{file});
        String output = outputCapture.toString();
        assertThat(output).contains(expected);
        outputCapture.flush();
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
    public void okFile() throws FileNotFoundException {
        String target = getClass().getClassLoader().getResource("testData").getPath();
        assertThat(target).isNotNull();
        String output = testCommandLineRun(new File(target).getPath(), "READY TO GO");
        assertThat(output).contains("Unique routes:\t10");
        assertThat(output).contains("Unique stations:\t24");
    }

}