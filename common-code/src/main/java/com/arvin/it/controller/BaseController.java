package com.arvin.it.controller;

import com.arvin.it.vo.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? success() : error();
    }

    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }



    public AjaxResult success() {
        return AjaxResult.success();
    }

    public AjaxResult error() {
        return AjaxResult.error();
    }

}
