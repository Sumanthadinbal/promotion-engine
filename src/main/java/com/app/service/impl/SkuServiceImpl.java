package com.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.app.service.SkuService;

/**
 * Implementation class for sku service.
 * @author sumanth
 *
 */
public class SkuServiceImpl implements SkuService {

  private Map<Character, Integer> priceChart;
  
  public SkuServiceImpl() {
    priceChart = new HashMap<Character, Integer>();
    //TODO load the price chart as required from database/file.
    priceChart.put('A', 50);
    priceChart.put('B', 30);
    priceChart.put('C', 20);
    priceChart.put('D', 15);
  }
  
  @Override
  public Map<Character, Integer> getPriceOfAllSku() {
    return priceChart;
  }
  
}
