package com.roy.dto;

import com.roy.pojo.Item;
import com.roy.pojo.ItemNeed;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemDto extends Item {

    private List<ItemNeed> needs = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
