package pres.qianmuna.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pres.qianmuna.spring.mapper.InfoMapper;
import pres.qianmuna.spring.service.InfoService;

import java.util.List;
import java.util.Map;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/6/3  21:04
 * @description :
 */
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoMapper infoMapper;

    public void setInfoMapper(InfoMapper infoMapper) {
        this.infoMapper = infoMapper;
    }

    @Override
    public List<Map<String, Object>> queryAll() {
        return infoMapper.queryAll();
    }
}
