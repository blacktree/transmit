package com.hebaibai.ctrt;


import com.hebaibai.ctrt.transmit.config.FileTypeConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;


/**
 * 程序入口
 *
 * @author hjx
 */
@Slf4j
public class Main {


    /**
     * 启动入口
     * 需要传入 -c 配置文件路径 参数
     * 例如 -c /home/config.json
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //获取配置文件内容
        FileTypeConfig conf = getConf(args);
        //启动
        CtrtLancher ctrtLancher = new CtrtLancher();
        ctrtLancher.start(conf);
    }


    /**
     * 获取配置文件
     *
     * @param args
     * @return
     * @throws ParseException
     */
    private static FileTypeConfig getConf(String[] args) throws Exception {
        Options options = new Options();
        options.addOption("c", "conf", true, "config file path");
        CommandLine parse = new BasicParser().parse(options, args);
        if (!parse.hasOption("conf")) {
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("Options", options);
            System.exit(0);
        }
        String conf = parse.getOptionValue("conf");
        FileTypeConfig fileTypeConfig = new FileTypeConfig(conf);
        return fileTypeConfig;
    }

}
