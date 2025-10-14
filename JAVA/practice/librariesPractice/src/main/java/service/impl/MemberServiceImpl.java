package service.impl;

import dao.interfaces.MemberDao;
import domain.Member;
import exception.DataAccessException;
import exception.NotFoundException;
import exception.ServiceException;
import service.interfaces.MemberService;
import util.ValidateInput;

import java.util.List;

public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    public MemberServiceImpl(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public void createMember(String fullName, String email, String phone) throws ServiceException {
        try {
            ValidateInput.validateString(fullName);
            ValidateInput.validateEmail(email);
            ValidateInput.validateString(phone);

            Member member = new Member();
            member.setFullName(fullName);
            member.setEmail(email);
            member.setPhone(phone);
            member.setActive(true);

            memberDao.create(member);

        } catch (DataAccessException | IllegalArgumentException e) {
            throw new ServiceException("Error creating member: " + e.getMessage(), e);
        }
    }

    @Override
    public void updateMember(int id, String fullName, String email, String phone, boolean active) throws ServiceException {
        try {
            Member member = findMemberById(id); // We reuse to verify if it exists

            ValidateInput.validateString(fullName);
            ValidateInput.validateEmail(email);
            ValidateInput.validateString(phone);

            member.setFullName(fullName);
            member.setEmail(email);
            member.setPhone(phone);
            member.setActive(active);

            memberDao.update(member);
        } catch (DataAccessException | IllegalArgumentException | NotFoundException e) {
            throw new ServiceException("Error updating member: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteMember(int id) throws ServiceException {
        try {
            findMemberById(id); // Check if it exists
            memberDao.delete(id);
        } catch (DataAccessException | NotFoundException e) {
            throw new ServiceException("Error deleting member: " + e.getMessage(), e);
        }
    }

    @Override
    public Member findMemberById(int id) throws ServiceException {
        try {
            return memberDao.findById(id)
                    .orElseThrow(() -> new NotFoundException("Member with ID " + id + " not found"));
        } catch (DataAccessException | NotFoundException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Member> findAllMembers() throws ServiceException {
        try {
            return memberDao.findAll();
        } catch (DataAccessException e) {
            throw new ServiceException("Error retrieving all members.", e);
        }
    }

    @Override
    public Member findMemberByEmail(String email) throws ServiceException {
        try {
            // Llamamos al nuevo método del DAO
            return memberDao.findByEmail(email)
                    // Si el Optional está vacío, lanzamos una excepción de negocio clara.
                    .orElseThrow(() -> new NotFoundException("Member with email " + email + " not found"));
        } catch (DataAccessException | NotFoundException e) {
            // Envolvemos cualquier excepción en una ServiceException para la capa superior
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
