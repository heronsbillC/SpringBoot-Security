package com.clubfactory.demo.test.service;

import com.clubfactory.demo.test.datasource.DataSource;
import com.clubfactory.demo.test.mapper.ResourceMapper;
import com.clubfactory.demo.test.pojo.Resource;
import com.clubfactory.demo.test.pojo.ResourceExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import static com.clubfactory.demo.test.datasource.DataSourceConstant.TEST;

@Service
@Slf4j
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    /**
     * 根据资源id获取资源
     * @param id
     * @return
     */
    @DataSource(TEST)
    public String getResource(int id){
        ResourceExample example = new ResourceExample();
        example.createCriteria().andIdEqualTo(id);
        String url = resourceMapper.selectByExample(example).get(0).getUrl();
        log.info("ResourceService.getResource->url=" + url);
        return url;
    }

    @DataSource(TEST)
    public Resource gerResourceByUrl(String url){
        ResourceExample example = new ResourceExample();
        example.createCriteria().andUrlEqualTo(url);
        Resource resource = resourceMapper.selectByExample(example).get(0);
        return  resource;
    }
}
