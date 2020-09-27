package com.app;

import java.util.Map;
import java.util.Set;

/**
 * POJO for Promotion
 * @author sumanth
 *
 */
public class Promos {
  
  /**
   * Details of different sku and quantity for the promotion.
   */
  private Map<Character, Integer> details;
  
  /**
   * Price for this promotion.
   */
  private Integer price;
  
  public Promos(Map<Character, Integer> details, Integer price) {
    this.details = details;
    this.price = price;
  }
  
  public Map<Character, Integer> getDetails() {
    return details;
  }
  
  public Set<Character> getSkus() {
    return details.keySet();
  }
  
  public Integer getPrice() {
    return price;
  }

}
