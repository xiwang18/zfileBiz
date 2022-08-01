package im.zhaojun.zfileBiz.home.chain.command;

import im.zhaojun.zfileBiz.home.chain.FileContext;
import im.zhaojun.zfileBiz.common.exception.PasswordVerifyException;
import im.zhaojun.zfileBiz.home.model.request.FileListRequest;
import im.zhaojun.zfileBiz.admin.model.verify.VerifyResult;
import im.zhaojun.zfileBiz.admin.service.PasswordConfigService;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 校验文件夹密码责任链 command 命令
 *      校验当前请求的文件夹是否需要密码校验，如果需求则校验密码，密码不正确则抛出异常
 *
 * @author zhaojun
 */
@Service
public class FolderPasswordVerifyCommand implements Command {

	@Resource
	private PasswordConfigService passwordConfigService;

	/**
	 * 校验当前文件是否需要密码.
	 *
	 * @param   context
	 *          文件处理责任链上下文
	 *
	 * @return  是否停止执行责任链, true: 停止执行责任链, false: 继续执行责任链
	 */
	@Override
	public boolean execute(Context context) throws Exception {
		FileContext fileContext = (FileContext) context;
		FileListRequest fileListRequest = fileContext.getFileListRequest();
		Integer storageId = fileContext.getStorageId();

		String path = fileListRequest.getPath();
		String password = fileListRequest.getPassword();

		// 校验密码, 如果校验不通过, 则返回错误消息
		VerifyResult verifyResult = passwordConfigService.verifyPassword(storageId, path, password);
		if (!verifyResult.isPassed()) {
			throw new PasswordVerifyException(verifyResult.getCode(), verifyResult.getMsg());
		}

		// 设置当前文件夹所对应的文件夹路径表达式.
		fileContext.setPasswordPattern(verifyResult.getPattern());;
		return false;
	}

}