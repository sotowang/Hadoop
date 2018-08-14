package com.soto.hadoop.project;

import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;

/**
 * UserAgent测试类
 */
public class UserAgentTest {
    public static void main(String[] args) {
        String source = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3514.0 Safari/537.36\n";
        UserAgentParser userAgentParser = new UserAgentParser();
        UserAgent agent = userAgentParser.parse(source);


        String browser = agent.getBrowser();
        String engine = agent.getEngine();
        String engineVersion = agent.getEngineVersion();
        String os = agent.getOs();
        String platform = agent.getPlatform();
        boolean isMobile = agent.isMobile();

        System.out.println(browser + "   " + engine + "   " + engineVersion + "    " + os + "    " + platform + "    " + isMobile);

    }



}
