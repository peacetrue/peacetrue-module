package com.github.peacetrue.module.modules.module;

import com.github.peacetrue.metadata.service.support.PropertyNameValueImpl;
import com.github.peacetrue.metadata.service.support.RecordInfoImpl;
import com.github.peacetrue.module.service.ModuleService;
import com.github.peacetrue.module.service.support.ModifyOptionsImpl;
import com.github.peacetrue.template.modules.demo.DemoAdd;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiayx
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestMybatisModuleAutoConfiguration.class)
@Transactional
@ActiveProfiles("module-service-test")
public class ModuleServiceImplTest {


    public static final DemoAdd<Long> DEMO_ADD = new DemoAdd<>();

    static {
        DEMO_ADD.setCode("1");
        DEMO_ADD.setName("1");
        DEMO_ADD.setOperatorId(1L);
    }

    @Autowired
    private ModuleService moduleService;

//    @Test
//    public void add() {
//        moduleService.add(DEMO_ADD);
//    }

//    @Test
//    public void get() {
//        DemoVO vo = moduleService.getRequiredById(new RecordIndex<>("Demo", 1L));
//        Assert.assertEquals(vo.getId(), 1L);
//    }

//    @Test
//    public void exists() {
//        boolean exists = moduleService.exists(new DemoExists("1"));
//        Assert.assertTrue(exists);
//    }

    @Test
    public void exists() {
        RecordInfoImpl recordInfo = new RecordInfoImpl("Demo", new PropertyNameValueImpl<>("code", "1"));
        boolean exists = moduleService.exists(recordInfo);
        Assert.assertTrue(exists);
    }

    @Test
    public void modify() {
        RecordInfoImpl recordInfo = new RecordInfoImpl("Demo",
                new PropertyNameValueImpl<>("id", 1),
                new PropertyNameValueImpl<>("code", "2")
        );
        int count = moduleService.modify(recordInfo, new ModifyOptionsImpl());
        Assert.assertEquals(count, 1);
    }
}