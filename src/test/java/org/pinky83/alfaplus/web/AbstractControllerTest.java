package org.pinky83.alfaplus.web;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.pinky83.alfaplus.repository.JpaUtil;
import org.pinky83.alfaplus.service.ImageService;
import org.pinky83.alfaplus.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.PostConstruct;

/**
 * Created by Дмитрий on 20.01.2017.
 *
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml"
})
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public abstract class AbstractControllerTest {

    private static final CharacterEncodingFilter ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        ENCODING_FILTER.setEncoding("UTF-8");
        ENCODING_FILTER.setForceEncoding(true);
    }

    @Autowired
    private JpaUtil jpaUtil;

    protected MockMvc mockMvc;

    @Autowired
    protected ImageService imageService;

    @Autowired
    protected PatientService patientService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(ENCODING_FILTER)
                .build();
    }

    @Before
    public void setUp() throws Exception {
        patientService.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}
