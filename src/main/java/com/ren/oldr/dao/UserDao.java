package com.ren.oldr.dao;

import com.ren.oldr.common.datasource.BaseDao;
import com.ren.oldr.models.entity.User;
import com.ren.oldr.models.request.copilot.CopilotReq;
import com.ren.oldr.models.response.copilot.CopilotResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: target
 * @date: 2020/5/9 10:17
 * @description:
 */
@Repository
public interface UserDao extends BaseDao<User> {

    User selectByOperId(@Param("openid") String openid);

    List<CopilotResp> getCopilotList(CopilotReq req);

}
