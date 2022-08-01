package im.zhaojun.zfileBiz.admin.annoation;

import im.zhaojun.zfileBiz.admin.model.param.IStorageParam;
import im.zhaojun.zfileBiz.admin.annoation.model.StorageSourceParamDef;

import java.util.List;

/**
 * 存储源参数下拉值接口.
 *
 * @author zhaojun
 */
public interface StorageParamSelect {

	/**
	 * 获取存储源参数下拉选项列表.
	 *
	 * @param   storageParamItem
	 *          存储源下拉参数定义
	 *
	 * @param   targetParam
	 *          存储源参数
	 *
	 * @return  存储源参数下拉选项列表
	 */
	List<StorageSourceParamDef.Options> getOptions(StorageParamItem storageParamItem, IStorageParam targetParam);

}