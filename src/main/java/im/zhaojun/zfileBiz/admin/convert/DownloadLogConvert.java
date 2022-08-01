package im.zhaojun.zfileBiz.admin.convert;

import im.zhaojun.zfileBiz.admin.model.entity.DownloadLog;
import im.zhaojun.zfileBiz.admin.model.entity.StorageSource;
import im.zhaojun.zfileBiz.admin.model.result.link.DownloadLogResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @author zhaojun
 */
@Component
@Mapper(componentModel = "spring")
public interface DownloadLogConvert {

	@Mapping(source = "downloadLog.id", target = "id")
	@Mapping(source = "storageSource.name", target = "storageName")
	@Mapping(source = "storageSource.type", target = "storageType")
	DownloadLogResult entityToResultList(DownloadLog downloadLog, StorageSource storageSource);

}