package com.cleware.commandline;

import com.cleware.daemon.ClewareDaemon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author zutherb
 */
public final class ClewareLuncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClewareLuncher.class);

    private ClewareLuncher() { /*NOOP*/ }

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/cleware/spring-cmd-context.xml");
        ClewareDaemon bean = applicationContext.getBean(ClewareDaemon.class);
        bean.parseCommandLineArgs(new String[]{"red", "off"});
        applicationContext.close();
    }
}
