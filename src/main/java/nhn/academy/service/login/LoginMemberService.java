package nhn.academy.service.login;

import nhn.academy.model.Member;
import nhn.academy.model.login.LoginMemberRequest;

public interface LoginMemberService {
    Member login(LoginMemberRequest loginMemberRequest);
}
