package fctk.server.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize
public class GetStudentsByGroupRequest {
    private long groupId;

    public GetStudentsByGroupRequest(long groupId) {
        this.groupId = groupId;
    }

    public long getGroupId() {
        return groupId;
    }

}
