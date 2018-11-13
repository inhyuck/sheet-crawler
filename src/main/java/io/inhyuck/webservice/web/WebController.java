/**
 * Date: 13/11/2018
 */

package io.inhyuck.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @GetMapping("/hello")
    public String hello() {
        return "hello inhyuck";
    }
}
