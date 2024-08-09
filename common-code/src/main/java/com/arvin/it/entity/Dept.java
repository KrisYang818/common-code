package com.arvin.it.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class Dept extends Model<Dept> {

    @TableId(value = "id", type = IdType.AUTO)
    public Integer id;

    private String name;

    private String code;
}
