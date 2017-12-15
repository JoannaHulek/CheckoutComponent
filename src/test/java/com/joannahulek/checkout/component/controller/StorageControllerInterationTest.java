package com.joannahulek.checkout.component.controller;

import com.joannahulek.checkout.component.CountableProduct;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StorageControllerInterationTest extends IntegrationTestBase {
   @Test
   public void shouldGetStorage(){
       List<CountableProduct> storage = template.getForObject(base+"/storage",List.class);
       Assert.assertNotNull(storage);
   }
}
