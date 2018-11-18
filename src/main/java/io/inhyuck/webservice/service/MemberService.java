/**
 * Date: 18/11/2018
 * Author: inhyuck | https://github.com/inhyuck
 */

package io.inhyuck.webservice.service;

import io.inhyuck.webservice.config.MySheetProperties;
import io.inhyuck.webservice.domain.member.Member;
import io.inhyuck.webservice.service.sheet.SheetAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MySheetProperties sheetProperties;
    @Autowired
    SheetAPI sheetAPI;

    public Member authenticate(String username, String password) throws IOException {
        System.out.println("=============" + "authenticate 호출!" );
        List<List<Object>> values = sheetAPI.findAll(sheetProperties.getMemberInfo());
        Member foundMember = null;
        for(List<Object> member : values) {
            if (member.get(0).equals(username) && member.get(1).equals(password)) {
                foundMember = Member.builder()
                        .username(member.get(0).toString())
                        .password(member.get(1).toString())
                        .fullname(member.get(2).toString())
                        .build();
                break;
            }
        }
        return foundMember;
    }
}
