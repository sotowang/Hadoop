package com.soto.hadoop.project;

import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UserAgent测试类
 */
public class UserAgentTest {

    @Test
    public void testReadFile() throws Exception {
        String path = "/home/sotowang/user/aur/hadoop/data/access.log";

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(new File(path)))
        );

        String line = "";
        int i = 0;

        UserAgentParser userAgentParser = new UserAgentParser();

        while (line != null) {
            line = reader.readLine();//一次读一行数据
            i++;
            if (StringUtils.isNotBlank(line)) {
                String source = line.substring(getCharacterPosition(line, "\"", 1)) + 1;
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
        System.out.println("UserAgentTest.restReadFile,recordCount" + i);

    }


    /**
     * 测试自定义方式
     */
    @Test
    public void testGetCharacterPosition(){
        String value = "183.162.52.7 - - [10/Nov/2016:00:01:02  +0800]  \"POST /api3/getadv HTTP";
        int index = getCharacterPosition(value, "\"", 1);
        System.out.println(index);
    }

    /**
     * 获取指定字符串中指定标识的字符串出现的索引位置
     */
    private int getCharacterPosition(String value, String operator, int index) {
        Matcher slashMatcher = Pattern.compile(operator).matcher(value);
        int mIdx = 0;
        while (slashMatcher.find()) {
            mIdx++;
            if (mIdx == index) {
                break;
            }
        }

        return slashMatcher.start();

    }



    /**
     * 单元测试UserAgent工具类的使用
     */
    @Test
    public void testUserAgentParser() {
        String source = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3514.0 Safari/537.36";
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
