package com.qf.xmall.cart.demo.dao;

import com.qf.xmall.cart.demo.common.po.TbItemPO;

public interface TbItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItemPO record);

    int insertSelective(TbItemPO record);

    TbItemPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItemPO record);

    int updateByPrimaryKey(TbItemPO record);
}