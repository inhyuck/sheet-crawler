/**
 * Date: 18/11/2018
 * Author: inhyuck | https://github.com/inhyuck
 */

package io.inhyuck.webservice.service;

import io.inhyuck.webservice.dao.MemberDAO;
import io.inhyuck.webservice.entity.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MemberService {
    @Autowired
    MemberDAO memberDAO;

    public Member authenticate(String username, String password) throws IOException {
        return memberDAO.findByUsernameAndPassword(username, password);
    }
}
