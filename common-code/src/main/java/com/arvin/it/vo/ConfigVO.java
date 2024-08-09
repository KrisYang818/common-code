package com.arvin.it.vo;

import com.arvin.it.vo.validation.Group;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
public class ConfigVO {

    /**
     * 分组校验
     */
    @NotNull(message = "修改配置时，需要指定ID", groups = Group.Update.class)
    @Null(message = "新增配置时，不需要指定ID", groups = Group.Add.class)
    private Integer configId;


    @NotBlank(message = "配置名称不能为空")
    private String name;

    /**
     * 嵌套校验  需要指定@Valid
     */
    @NotEmpty(message = "配置项目必填")
    private List<ConfigItem> configItemList;
}
