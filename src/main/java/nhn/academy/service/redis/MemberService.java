package nhn.academy.service.redis;

import nhn.academy.model.redis.MemberCreateCommand;
import nhn.academy.model.redis.MemberEntity;

import java.util.List;

public interface MemberService {

    public void createMember(MemberCreateCommand memberCreateCommand);
    public MemberEntity findById(String id);
    public List<MemberEntity> getMembers();
    public void deleteMember(String id);

}
