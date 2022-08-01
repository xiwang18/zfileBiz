package im.zhaojun.zfileBiz.admin.convert;

import im.zhaojun.zfileBiz.admin.model.entity.ShortLink;
import im.zhaojun.zfileBiz.admin.model.entity.StorageSource;
import im.zhaojun.zfileBiz.admin.model.result.link.ShortLinkResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @author zhaojun
 */
@Component
@Mapper(componentModel = "spring")
public interface ShortLinkConvert {

	@Mapping(source = "shortLink.id", target = "id")
	@Mapping(source = "storageSource.name", target = "storageName")
	@Mapping(source = "storageSource.type", target = "storageType")
	ShortLinkResult entityToResultList(ShortLink shortLink, StorageSource storageSource);

}