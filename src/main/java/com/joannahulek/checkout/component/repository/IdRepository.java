package com.joannahulek.checkout.component.repository;

import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class IdRepository {

    private String createId() {
        return UUID.randomUUID().toString();
    }
}