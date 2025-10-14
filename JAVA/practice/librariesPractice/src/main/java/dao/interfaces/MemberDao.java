package dao.interfaces;

import domain.Member;
import exception.DataAccessException;

import java.util.List;
import java.util.Optional;

public interface MemberDao extends Crud<Member, Integer> {

    Optional<Member> findByEmail(String email) throws DataAccessException;
}
