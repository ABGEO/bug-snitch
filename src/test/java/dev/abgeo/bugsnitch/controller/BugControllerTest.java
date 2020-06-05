package dev.abgeo.bugsnitch.controller;

import dev.abgeo.bugsnitch.BaseControllerTest;
import dev.abgeo.bugsnitch.model.Bug;
import dev.abgeo.bugsnitch.repository.BugRepository;
import dev.abgeo.bugsnitch.type.Priority;
import dev.abgeo.bugsnitch.type.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BugControllerTest extends BaseControllerTest {

    @Autowired
    private BugRepository bugRepository;

    @Test
    public void testCreate_invalidPriority() throws Exception {
        String bugJson = "{\"title\":\"Test Bug Title\", \"body\":\"Test Bug Body\", \"priority\":\"invalid\"," +
                "\"status\":\"OPEN\"}\n";

        mockMvc.perform(post("/bug")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bugJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreate_invalidStatus() throws Exception {
        String bugJson = "{\"title\":\"Test Bug Title\", \"body\":\"Test Bug Body\", \"priority\":\"CRITICAL\"," +
                "\"status\":\"invalid\"}\n";

        mockMvc.perform(post("/bug")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bugJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreate_success() throws Exception {
        String bugJson = "{\"title\":\"Test Bug Title\", \"body\":\"Test Bug Body\", \"priority\":\"CRITICAL\"," +
                "\"status\":\"OPEN\"}\n";

        MvcResult mvcResult = mockMvc.perform(post("/bug")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bugJson))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("Test Bug Title")))
                .andExpect(jsonPath("$.body", is("Test Bug Body")))
                .andExpect(jsonPath("$.priority", is("CRITICAL")))
                .andExpect(jsonPath("$.status", is("OPEN")));
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(asyncDispatch(mockMvc.perform(get("/bug")).andReturn()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(bugRepository.findAll().size())));
    }

    @Test
    public void getSingle_notFound() throws Exception {
        mockMvc.perform(asyncDispatch(mockMvc.perform(get("/bug/90000000")).andReturn()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSingle_success() throws Exception {
        // Create new test bug.
        Bug newBug = new Bug();
        newBug.setTitle("New Bug");
        newBug.setBody("New Bug Body");
        newBug.setPriority(Priority.MEDIUM);
        newBug.setStatus(Status.OPEN);

        bugRepository.save(newBug);

        mockMvc.perform(asyncDispatch(mockMvc.perform(get("/bug/" + newBug.getId())).andReturn()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is(newBug.getTitle())))
                .andExpect(jsonPath("$.body", is(newBug.getBody())))
                .andExpect(jsonPath("$.priority", is("MEDIUM")))
                .andExpect(jsonPath("$.status", is("OPEN")));
    }

    @Test
    public void update() throws Exception {
        // Create new test bug.
        Bug newBug = new Bug();
        newBug.setTitle("New Bug");
        newBug.setBody("New Bug Body");
        newBug.setPriority(Priority.MEDIUM);
        newBug.setStatus(Status.OPEN);

        bugRepository.save(newBug);

        String bugJson = "{\"title\":\"New Bug Updated\", \"body\":\"New Bug Body Updated\", \"priority\":\"CRITICAL\"," +
                "\"status\":\"FIXED\"}\n";

        mockMvc.perform(asyncDispatch(mockMvc.perform(patch("/bug/" + newBug.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(bugJson))
                    .andReturn()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("New Bug Updated")))
                .andExpect(jsonPath("$.body", is("New Bug Body Updated")))
                .andExpect(jsonPath("$.priority", is("CRITICAL")))
                .andExpect(jsonPath("$.status", is("FIXED")));
    }

    @Test
    public void delete_notFound() throws Exception {
        mockMvc.perform(asyncDispatch(mockMvc.perform(delete("/bug/90000000")).andReturn()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void delete_success() throws Exception {
        mockMvc.perform(asyncDispatch(mockMvc.perform(
                delete("/bug/" + bugRepository.findAll().get(0).getId())
        ).andReturn()))
                .andExpect(status().isOk());
    }

}
