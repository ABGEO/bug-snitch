package dev.abgeo.bugsnitch.controller;

import dev.abgeo.bugsnitch.BaseControllerTest;
import dev.abgeo.bugsnitch.model.Comment;
import dev.abgeo.bugsnitch.repository.BugRepository;
import dev.abgeo.bugsnitch.repository.CommentRepository;
import dev.abgeo.bugsnitch.service.WebSocketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CommentControllerTest extends BaseControllerTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BugRepository bugRepository;

    @MockBean
    private WebSocketService webSocketService;

    @Test
    public void updateBugCommentTest() throws Exception {
        Comment newComment = new Comment();
        newComment.setBody("New Comment");
        commentRepository.save(newComment);

        mockMvc.perform(asyncDispatch(mockMvc.perform(patch("/comment/" + newComment.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"body\":\"New Comment Updated\"}"))
                .andReturn()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", is("New Comment Updated")));
    }

    @Test
    public void deleteCommentTest() throws Exception {
        mockMvc.perform(asyncDispatch(mockMvc.perform(
                delete("/comment/" + commentRepository.findAll().get(0).getId())
        ).andReturn()))
                .andExpect(status().isOk());
    }

    @Test
    public void createCommentTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/bug/" + bugRepository.findAll().get(0).getId() + "/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"body\":\"Test Comment Body\"}\n"))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.body", is("Test Comment Body")));
    }

}
