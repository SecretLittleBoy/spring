package com.qf.xmall.sso.demo.dao;


import com.qf.xmall.sso.demo.common.po.TbMemberPO;

public interface TbMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbMemberPO record);

    int insertSelective(TbMemberPO record);

    TbMemberPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbMemberPO record);

    int updateByPrimaryKey(TbMemberPO record);

    TbMemberPO selectByName(String username);

}