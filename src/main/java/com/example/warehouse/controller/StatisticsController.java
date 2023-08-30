package com.example.warehouse.controller;

import com.example.warehouse.result.Result;
import com.example.warehouse.service.StoreService;
import com.example.warehouse.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StoreService storeService;

    @RequestMapping("/store-invent")
    public Result storeInvent() {
        List<StoreCountVo> storeCountVos = storeService.selectStoreCount();
        return Result.ok(storeCountVos);
    }
}
