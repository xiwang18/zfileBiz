package im.zhaojun.zfileBiz.home.service.impl;

import cn.hutool.core.util.StrUtil;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import im.zhaojun.zfileBiz.home.model.enums.StorageTypeEnum;
import im.zhaojun.zfileBiz.admin.model.param.S3Param;
import im.zhaojun.zfileBiz.home.service.base.AbstractS3BaseFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author zhaojun
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class S3ServiceImpl extends AbstractS3BaseFileService<S3Param> {

    @Override
    public void init() {
        boolean isPathStyle = "path-style".equals(param.getPathStyle());
        String region = param.getRegion();
        if (StrUtil.isNotEmpty(param.getEndPoint())) {
            region = param.getEndPoint().split("\\.")[1];
        }
        BasicAWSCredentials credentials = new BasicAWSCredentials(param.getAccessKey(), param.getSecretKey());
        s3Client = AmazonS3ClientBuilder.standard()
                .withPathStyleAccessEnabled(isPathStyle)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(param.getEndPoint(), region)).build();

        if (param.isAutoConfigCors()) {
            setUploadCors();
        }
    }

    @Override
    public StorageTypeEnum getStorageTypeEnum() {
        return StorageTypeEnum.S3;
    }

}