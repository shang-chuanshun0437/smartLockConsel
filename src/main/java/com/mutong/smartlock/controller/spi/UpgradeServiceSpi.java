package com.mutong.smartlock.controller.spi;

import com.mutong.smartlock.common.ErrorCode;
import com.mutong.smartlock.common.LockException;
import com.mutong.smartlock.common.Result;
import com.mutong.smartlock.common.Upgrade;
import com.mutong.smartlock.controller.AddUserService;
import com.mutong.smartlock.controller.UpgradeService;
import com.mutong.smartlock.controller.request.AddUserRequest;
import com.mutong.smartlock.controller.response.AddUserResponse;
import com.mutong.smartlock.controller.response.UpgradeResponse;
import com.mutong.smartlock.service.spi.UserManageSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;

@RestController
@RequestMapping("/v1")
public class UpgradeServiceSpi implements UpgradeService
{
    private Logger logger = LoggerFactory.getLogger(UpgradeServiceSpi.class);

    @RequestMapping(value = "/upgrade",method = RequestMethod.POST)
    @Override
    public UpgradeResponse upgrade()
    {
        if (logger.isDebugEnabled())
        {
            logger.debug("inter upgrade");
        }

        UpgradeResponse response = new UpgradeResponse();

        Result result = new Result();
        Upgrade upgrade = new Upgrade();

        //设置版本信息
        upgrade.setVersionCode(2);
        upgrade.setVersionName("2.0.1");
        upgrade.setVersionDesc("测试版");
        upgrade.setDownloadUrl("http://47.94.86.112:8080/smartlock/v1/downLoad/apk");

        response.setResult(result);
        response.setUpgrade(upgrade);

       return response;
    }

    @RequestMapping(value = "/downLoad/apk",method = RequestMethod.GET)
    @Override
    public String downLoadApk(HttpServletRequest request, HttpServletResponse response)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("inter download apk");
        }

        String fileName = "mutong.apk";
        if (fileName != null)
        {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            String realPath = "/home/apk";
            File file = new File(realPath, fileName);
            if (file.exists())
            {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" +  fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1)
                    {
                        os.write(buffer, 0, i);
                        if(logger.isDebugEnabled())
                        {
                            logger.debug("file exists,data:{}",buffer);
                        }
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        if(logger.isDebugEnabled())
        {
            logger.debug("exit download apk");
        }
        return null;
    }

}
