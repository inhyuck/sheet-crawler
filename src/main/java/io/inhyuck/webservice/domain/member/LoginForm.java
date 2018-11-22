/**
 * Date: 18/11/2018
 * Author: inhyuck | https://github.com/inhyuck
 */

package io.inhyuck.webservice.domain.member;

import lombok.Data;

@Data
public class LoginForm {
    private String username;
    private String password;
}
