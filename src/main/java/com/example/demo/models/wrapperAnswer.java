package com.example.demo.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class wrapperAnswer<R> {
    public int count;
    public int count_total;
    public int page;
    public int page_total;
    public int items_per_page;
    public List<R> items;

    public wrapperAnswer() {
    }

}
