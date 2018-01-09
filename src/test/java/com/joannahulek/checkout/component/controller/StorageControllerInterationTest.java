package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.model.StorageCountableProduct;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
public class StorageControllerInterationTest extends IntegrationTestBase {

    @Test
    public void shouldGetStorage(){
        List<StorageCountableProduct> storage = template.getForObject(base + "/storage", List.class);
        Assert.assertNotNull(storage);
    }
}