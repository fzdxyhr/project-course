package com.yhr.course.course.config;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author yhr
 * @version latest
 * @date 2019/3/7
 */

@Component
public class AliyunFileHandle {

    public String uploadFile(String fileName, InputStream inputStream) throws Exception {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAIe9UrTlT4zGIF";
        String accessKeySecret = "Yd4CzgTW4iPOiuxFtiZzhDFp0SVdQ0";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        PutObjectResult result = ossClient.putObject("filecourse", fileName, inputStream);
        // 关闭OSSClient。
        inputStream.close();
        ossClient.shutdown();
        return result.getETag();
    }

    public void downloadFile(String fileName, OutputStream outputStream) throws Exception {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = "LTAIe9UrTlT4zGIF";
        String accessKeySecret = "Yd4CzgTW4iPOiuxFtiZzhDFp0SVdQ0";

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject("filecourse", fileName);
        // 读取文件内容。
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = ossObject.getObjectContent().read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        // 关闭OSSClient。
        outputStream.close();
        ossClient.shutdown();
    }
}
