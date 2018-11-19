/**
 * Date: 18/11/2018
 * Author: inhyuck | https://github.com/inhyuck
 */

package io.inhyuck.webservice.domain.member;

import lombok.Builder;
import lombok.Data;
import javax.persistence.Id;

@Data
@Builder
public class Member {
    @Id
    private String username;
    private String fullname;
    private String password;

}
