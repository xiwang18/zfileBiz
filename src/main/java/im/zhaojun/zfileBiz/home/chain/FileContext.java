package im.zhaojun.zfileBiz.home.chain;

import im.zhaojun.zfileBiz.home.model.request.FileListRequest;
import im.zhaojun.zfileBiz.home.model.result.FileItemResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.chain.impl.ContextBase;

import java.util.List;

/**
 * 文件处理责任链上下文
 *
 * @author zhaojun
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
public class FileContext extends ContextBase {

	/**
	 * 存储源 id
	 */
	private Integer storageId;

	/**
	 * 存储源请求
	 */
	private FileListRequest fileListRequest;

	/**
	 * 根据存储源请求获取到的文件列表
	 */
	private List<FileItemResult> fileItemList;

	/**
	 * 当前目录密码路径表达式
	 */
	private String passwordPattern;

}