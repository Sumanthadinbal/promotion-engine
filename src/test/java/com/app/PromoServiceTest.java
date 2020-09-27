package com.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.app.service.PromotionService;
import com.app.service.impl.PromotionServiceImpl;

public class PromoServiceTest {
  
  private PromotionService pService;
  
  @SuppressWarnings("serial")
  @Before
  public void before() {
    pService = new PromotionServiceImpl();
    Promos promo1 = new Promos(new HashMap<Character, Integer>() {
      {
        put('A', 3);
      }
    }, 130);
    Promos promo2 = new Promos(new HashMap<Character, Integer>() {
      {
        put('B', 2);
      }
    }, 45);
    Promos promo3 = new Promos(new HashMap<Character, Integer>() {
      {
        put('C', 1);
        put('D', 1);
      }
    }, 30);
    
    pService.addPromo(promo1);
    pService.addPromo(promo2);
    pService.addPromo(promo3);
  }
  
  
  
  @Test
  public void testWithNoApplicablePromo() {
 
    
    List<StockKeepingUnit> items = new ArrayList<StockKeepingUnit>();
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('C'));
    
    Assert.assertEquals(100, (int) pService.applyPromotion(items));
    
    
  }
  
  @Test
  public void testWithApplicablePromo() {
    
    
    List<StockKeepingUnit> items = new ArrayList<StockKeepingUnit>();
    
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('C'));
    
    Assert.assertEquals(370, (int) pService.applyPromotion(items));
    
    items = new ArrayList<StockKeepingUnit>();
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('A'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('B'));
    items.add(new StockKeepingUnit('C'));
    items.add(new StockKeepingUnit('D'));
    
    Assert.assertEquals(280, (int) pService.applyPromotion(items));
  }

}
