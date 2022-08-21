package com.roy.dto;


import com.roy.pojo.Itemset;
import com.roy.pojo.ItemsetItem;
import lombok.Data;

import java.util.List;

@Data
public class ItemsetDto extends Itemset {

    private List<ItemsetItem> itemsetItems;

    private String categoryName;

}
