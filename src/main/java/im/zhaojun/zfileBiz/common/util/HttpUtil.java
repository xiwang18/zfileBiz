package im.zhaojun.zfileBiz.common.util;

import cn.hutool.core.io.FileUtil;
import im.zhaojun.zfileBiz.common.constant.ZFileConstant;
import im.zhaojun.zfileBiz.common.exception.PreviewException;
import im.zhaojun.zfileBiz.common.exception.TextParseException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 网络相关工具
 *
 * @author zhaojun
 */
@Slf4j
public class HttpUtil {


    /**
     * 获取 URL 对应的文件内容
     *
     * @param   url
     *          文件 URL
     *
     * @return  文件内容
     */
    public static String getTextContent(String url) {
        long maxFileSize = 1024 * ZFileConstant.TEXT_MAX_FILE_SIZE_KB;

        if (getRemoteFileSize(url) > maxFileSize) {
            throw new PreviewException("预览文件超出大小, 最大支持 " + FileUtil.readableFileSize(maxFileSize));
        }

        String result;
        try {
            result = cn.hutool.http.HttpUtil.get(url);
        } catch (Exception e) {
            throw new TextParseException("文件解析异常, 请求 url = " + url + ", 异常信息为 = " + e.getMessage());
        }

        return result == null ? "" : result;
    }


    /**
     * 获取远程文件大小
     *
     * @param   url
     *          文件 URL
     *
     * @return  文件大小
     */
    public static Long getRemoteFileSize(String url) {
        long size = 0;
        URL urlObject;
        try {
            urlObject = new URL(url);
            URLConnection conn = urlObject.openConnection();
            size = conn.getContentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return size;
    }

}