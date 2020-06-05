public class CommentControllerTest extends BaseControllerTest{

    @Autowired

    private CommentRepository commentRepository;


    @Test
    public void updateBugCommentTest() throws Exception {
        // Update Existing Comment.
        Comment newComment = new Comment();
        newComment.setBody("New Comment");

        commentRepository.save(newComment);

        String commentJson = "{\"body\":\"New Bug Body Updated\"}\n";

        mockMvc.perform(asyncDispatch(mockMvc.perform(patch("/comment/" + newComment.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentJson))
                .andReturn()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", is("New Bug comment Updated")));



    }

    @Test
    public void deleteCommentTest() throws Exception{
        //Delete Comment
        mockMvc.perform(asyncDispatch(mockMvc.perform(
                delete("/comment/" + bugRepository.findAll().get(0).getId())
        ).andReturn()))
                .andExpect(status().isOk());

    }

    @Test
    public void createCommentTest() throws Exception{
        //Create Comment
        String commentJson = "{\"body\":\"Test Bug Body\"}\n";

        MvcResult mvcResult = mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(commentJson))
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.body", is("Test Comment Body")));
    }
}