package com.app.service;

import java.util.Map;

/**
 * SKUServie for sku operations.
 * @author sumanth
 *
 */
public interface SkuService {
  
  /**
   * Method to get all SKU and price mapping.
   * @return Map of sku and it's unit price.
   */
  public Map<Character, Integer> getPriceOfAllSku();

}
