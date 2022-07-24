package com.example.demo.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class playerPageModel {
    public int count;
    public int count_total;
    public int page;
    public int page_total;
    public int items_per_page;
    public List<playerModel> items;

    public playerPageModel() {
    }

}
