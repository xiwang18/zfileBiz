package im.zhaojun.zfileBiz.admin.model.param;

import im.zhaojun.zfileBiz.admin.annoation.StorageParamItem;
import lombok.Getter;

/**
 * 本地存储初始化参数
 *
 * @author zhaojun
 */
@Getter
public class LocalParam extends ProxyDownloadParam {

	@StorageParamItem(name = "文件路径", description = "只支持绝对路径<br>Docker 部署需提前映射宿主机路径！ " +
			"(<a class='link' target='_blank' href='https://docs.docker.com/engine/reference/run/#volume-shared-filesystems'>配置文档</a>)")
	private String filePath;

}