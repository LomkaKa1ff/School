package org.example.website;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    // http://localhost:8080/test
    @GetMapping("/test")
    public String name(
            @RequestParam("name") String name) {
        return nameRetard(name);
    }

    public String nameRetard(String text) {
        String result = "";
        boolean upper = false;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                result += upper ? Character.toUpperCase(c) : Character.toLowerCase(c);
                upper = !upper;
            } else {
                result += c;
            }
        }

        return result;
    }
}
