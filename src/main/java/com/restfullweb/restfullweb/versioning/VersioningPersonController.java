package com.restfullweb.restfullweb.versioning;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    Person getPersonV1() {
        return new Person("Ali veli");
    }

    @GetMapping("/v2/person")
    PersonV2 getPersonV2() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }


    //request param versioning
    @GetMapping(path = "/person", params = "version=1")
    public Person getFirstVersionOfPersonRequestParameter() {
        return new Person("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }


    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public Person getFirstVersionOfPersonRequestHeader() {
        return new Person("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public Person getFirstVersionOfPersonAcceptHeader() {
        return new Person("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
