package im.zhaojun.zfileBiz.common.exception.file.operator;

import im.zhaojun.zfileBiz.common.exception.file.StorageSourceException;
import lombok.Getter;
import lombok.Setter;

/**
 * 代理文件下载异常
 *
 * @author zhaojun
 */
@Getter
@Setter
public class ProxyDownloadFileException extends StorageSourceException {

	// 下载文件路径
	private String pathAndName;

	public ProxyDownloadFileException(Integer storageId, String pathAndName, Throwable cause) {
		super(storageId, cause);
		this.pathAndName = pathAndName;
	}

}