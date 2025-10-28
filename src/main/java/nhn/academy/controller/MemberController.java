package nhn.academy.controller;

import nhn.academy.model.ClassType;
import nhn.academy.model.Member;
import nhn.academy.model.MemberCreateCommand;
import nhn.academy.model.Requester;

import nhn.academy.model.redis.MemberEntity;
import nhn.academy.service.redis.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;
    final List<Member> members = new ArrayList<>();

    {
        members.add(new Member("신건영", 20, ClassType.A));
        members.add(new Member("김철수", 30, ClassType.B));
        members.add(new Member("이영희", 25, ClassType.C));
    }

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // day02
    @PostMapping("/members")
    public void createMember(@RequestBody nhn.academy.model.redis.MemberCreateCommand memberCreateCommand) {
        memberService.createMember(memberCreateCommand);
    }

    @GetMapping("/members")
    public List<MemberEntity> findAllMembers() {
        return memberService.getMembers();
    }

    @GetMapping("/member")
    public MemberEntity getMember(@RequestParam("id") String id) {
        return memberService.findById(id);
    }

    @DeleteMapping("/member")
    public void deleteMember(@RequestParam("id") String id) {
        memberService.deleteMember(id);
    }

    //


    // 이 밑은 day01
    @GetMapping("/name")
    public String getName() {
        return "신건영";
    }

    @GetMapping("/me")
    public Member getMe(Requester requester) {
        System.out.println(requester.getIp());
        System.out.println(requester.getLang());
        return new Member("신건영", 20, ClassType.A);
    }

//    @PostMapping("/members")
//    public ResponseEntity addMember(@RequestBody MemberCreateCommand memberCreateCommand) {
//        // TODO
//        System.out.println(memberCreateCommand);
//        members.add(new Member(memberCreateCommand.getName(), memberCreateCommand.getAge(), memberCreateCommand.getClazz()));
//
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/members")
//    public List<Member> getMembers() {
//        return members;
//    }

    @GetMapping("/req-param")
    public void getParam(int a, int b) {
        System.out.println(a);
        System.out.println(b);
    }

    @GetMapping("/page")
    public void getPage(Pageable pageable) {
        System.out.println("controller : " + pageable.getPageNumber());
        System.out.println("controller : " + pageable.getPageSize());
    }

}