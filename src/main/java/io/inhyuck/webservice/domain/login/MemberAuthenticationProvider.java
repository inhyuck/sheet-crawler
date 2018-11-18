/**
 * Date: 18/11/2018
 * Author: inhyuck | https://github.com/inhyuck
 */

package io.inhyuck.webservice.domain.login;

import io.inhyuck.webservice.domain.member.Member;
import io.inhyuck.webservice.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class MemberAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    MemberService memberService;

    @Override
    public Authentication authenticate (Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        Member member = null;
        try {
            member = memberService.authenticate(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (member == null) {
            throw new BadCredentialsException("Login Error!");
        }
        member.setPassword(null);

        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
        System.out.println("1111111" + authorities.toString());
        return new UsernamePasswordAuthenticationToken(member, null, authorities);
    }

    @Override
    public boolean supports(Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
