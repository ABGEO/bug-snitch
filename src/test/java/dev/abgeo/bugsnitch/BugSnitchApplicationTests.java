package dev.abgeo.bugsnitch;

import dev.abgeo.bugsnitch.controller.BugController;
import dev.abgeo.bugsnitch.controller.CommentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BugSnitchApplicationTests {

    @Autowired
    private BugController bugController;

    @Autowired
    private CommentController CommentController;

    @Test
	void contextLoads() {
        assertThat(bugController).isNotNull();
        assertThat(CommentController).isNotNull();
	}

}
