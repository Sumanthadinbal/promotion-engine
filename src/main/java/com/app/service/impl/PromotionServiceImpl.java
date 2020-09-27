package com.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.app.Promos;
import com.app.StockKeepingUnit;
import com.app.service.PromotionService;
import com.app.service.SkuService;

/**
 * Implementation for promotion service.
 * @author sumanth
 *
 */
public class PromotionServiceImpl implements PromotionService{

  private List<Promos> promos;
  
  private Map<Character, List<Promos>> promoLookup;
  
  private SkuService skuService;
  
  public PromotionServiceImpl() {
    this.promos = new ArrayList<Promos>();
    this.promoLookup = new HashMap<Character, List<Promos>>();
    this.skuService = new SkuServiceImpl();
  }
  
  @Override
  public Integer applyPromotion(List<StockKeepingUnit> skus) {
    
    Map<Character, Integer> count = new HashMap<Character, Integer>();
    skus.forEach(sku -> {
      Integer val = count.get(sku.getId()) == null ? 0: count.get(sku.getId());
      count.put(sku.getId(), val + 1);
    });
    
    Integer price = 0;
    
    Iterator<StockKeepingUnit> iterator = skus.iterator();
    List<Promos> applied = new ArrayList<Promos>();
    
    while (iterator.hasNext()) {
      StockKeepingUnit sku = iterator.next();
      List<Promos> promos = getPromosForSku(sku.getId());
      promos.forEach(p -> {
        apply(count, applied, p);
      });
    }
    price = calculatePromoPrice(applied);
    for (Entry<Character, Integer> skuCount: count.entrySet()) {
      price += skuCount.getValue() * skuService.getPriceOfAllSku().get(skuCount.getKey());
    }
    return price;
    
  }

  private void apply(Map<Character, Integer> count, List<Promos> applied, Promos p) {
    for (Entry<Character, Integer> entry: p.getDetails().entrySet()) {
      if (count.get(entry.getKey()) == null || count.get(entry.getKey()) < entry.getValue()) {
        return;
      }
    }
    for (Entry<Character, Integer> entry: p.getDetails().entrySet()) {
      count.put(entry.getKey(), count.get(entry.getKey())-entry.getValue());
    }
    applied.add(p);
  }

  private Integer calculatePromoPrice(List<Promos> applied) {
    return applied.stream().map(a -> a.getPrice()).reduce(0, Integer::sum);
    
  }

  public List<Promos> getPromosForSku(Character sku) {
    return promoLookup.get(sku);
  }

  public void addPromo(Promos promo) {
    promos.add(promo);
    for (Character sku : promo.getDetails().keySet()) {
      List<Promos> promosForSku = promoLookup.get(sku);
      if (promosForSku == null) {
        promosForSku = new ArrayList<Promos>();
      }
      promosForSku.add(promo);
      promoLookup.put(sku, promosForSku);
    }
    
  }

  @Override
  public void deletePromo(Character sku) {
    // TODO Auto-generated method stub
    
  }

}
