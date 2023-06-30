package org.dromara.page.config.service.impl;

import org.dromara.page.config.controller.dto.AddReq;
import org.dromara.page.config.service.ICrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liguoxian
 */
@Service
public class CrudServiceImpl implements ICrudService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(AddReq addReq) {

        return 0;
    }
}
