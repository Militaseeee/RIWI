package service.interfaces;

import domain.Member;
import exception.ServiceException;
import java.util.List;

public interface MemberService {

    void createMember(String fullName, String email, String phone) throws ServiceException;

    void updateMember(int id, String fullName, String email, String phone, boolean active) throws ServiceException;

    void deleteMember(int id) throws ServiceException;

    Member findMemberById(int id) throws ServiceException;

    List<Member> findAllMembers() throws ServiceException;

    Member findMemberByEmail(String email) throws ServiceException;
}
