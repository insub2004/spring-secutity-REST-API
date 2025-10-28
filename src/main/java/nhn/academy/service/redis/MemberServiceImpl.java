package nhn.academy.service.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import nhn.academy.model.redis.MemberCreateCommand;
import nhn.academy.model.redis.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    private String HASH_NAME = "Member";

    @Override
    public void createMember(MemberCreateCommand memberCreateCommand) {
        //TODO
    /*
        1. MemberEntity 생성 (memberCreateCommand를 이용)
        2. redisTemplate를 이용하여 레디에서 저장. `Hash` 자료 구조를 사용
    */

        MemberEntity memberEntity = new MemberEntity(memberCreateCommand);

        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(HASH_NAME, memberEntity.getId(), memberEntity);
    }

    @Override
    public MemberEntity findById(String id) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        //이제 자동 cast가 안된다.
        //MemberEntity memberEntity = (MemberEntity)hash.get(HASH_NAME, id);

        Object o = hash.get(HASH_NAME, id);
        return objectMapper.convertValue(o, MemberEntity.class);
    }

    @Override
    public List<MemberEntity> getMembers() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        List<Object> values = hash.values(HASH_NAME);

        List<MemberEntity> memberEntityList = new ArrayList<>();
        for (Object o : values) {
            //이제 명시적 cast이 안됨
            //memberEntityList.add((MemberEntity) o);
            MemberEntity memberEntity = objectMapper.convertValue(o, MemberEntity.class);
            memberEntityList.add(memberEntity);
        }

        return memberEntityList;
    }

    @Override
    public void deleteMember(String id) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.delete(HASH_NAME, id);
    }

}