package com.intelligent.type;

public class Discussions <R>{
    private DiscussionResponse discussionResponse;
    private R reply;

    public void setDiscussionResponse(DiscussionResponse discussionResponse) {
        this.discussionResponse = discussionResponse;
    }
    public DiscussionResponse getDiscussionResponse(){return discussionResponse;}

    public void setReply(R reply){this.reply=reply;}
    public R getReply(){return reply;}
}
