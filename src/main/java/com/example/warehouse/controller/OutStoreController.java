package com.example.warehouse.controller;

import com.example.warehouse.dto.CurrentUser;
import com.example.warehouse.entity.OutStore;
import com.example.warehouse.result.Result;
import com.example.warehouse.service.OutStoreService;
import com.example.warehouse.utils.TokenUtils;
import com.example.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("outstore")
public class OutStoreController {
    @Autowired
    private OutStoreService outStoreService;
    @Autowired
    private TokenUtils tokenUtils;

    /**
     * 添加出库单
     */
    @RequestMapping("/outstore-add")
    public Result outstoreAdd(@RequestBody OutStore outStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token) {
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        outStore.setCreateBy(currentUser.getUserId());
        return outStoreService.insert(outStore);
    }
}
