package com.app.service;

import java.util.List;

import com.app.Promos;
import com.app.StockKeepingUnit;

/**
 * Service class for promotion services.
 * @author sumanth
 *
 */
public interface PromotionService {
  
  /**
   * Applies the eligible promotions and calculates the final price.
   * @param skus List of SKU's in cart.
   * @return Total price
   */
  public Integer applyPromotion(List<StockKeepingUnit> skus);
  
  /**
   * Method to add promo to the list of all promos.
   * @param promo
   */
  public void addPromo(Promos promo);
  
  /**
   * Method to delete the promo.
   * @param sku
   */
  public void deletePromo(Character sku);
  
}
