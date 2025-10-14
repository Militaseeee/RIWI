package controller;

import domain.Member;
import exception.ServiceException;
import service.interfaces.MemberService;
import java.util.List;

public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void createMember(String fullName, String email, String phone) throws ServiceException {
        memberService.createMember(fullName, email, phone);
    }

    public List<Member> findAllMembers() throws ServiceException {
        return memberService.findAllMembers();
    }

    // Delegación para encontrar por email (lo necesitaremos en la vista)
    public Member findMemberByEmail(String email) throws ServiceException {
        // Asumiendo que añades este método en tu MemberService e MemberDao
        return memberService.findMemberByEmail(email);
    }

    public void updateMember(int id, String fullName, String email, String phone, boolean active) throws ServiceException {
        memberService.updateMember(id, fullName, email, phone, active);
    }
}