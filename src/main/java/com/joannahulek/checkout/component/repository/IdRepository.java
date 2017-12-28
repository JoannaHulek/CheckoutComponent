package com.joannahulek.checkout.component.repository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class IdRepository {

    public String createId() {
        return UUID.randomUUID().toString();
    }
}
