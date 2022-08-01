package im.zhaojun.zfileBiz.common.controller.callback;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import im.zhaojun.zfileBiz.admin.model.dto.OneDriveToken;
import im.zhaojun.zfileBiz.home.service.impl.OneDriveChinaServiceImpl;
import im.zhaojun.zfileBiz.home.service.impl.OneDriveServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * OneDrive 授权回调
 *
 * @author zhaojun
 */
@Api(tags = "OneDrive 认证回调模块")
@Controller
@RequestMapping(value = {"/onedrive", "/onedirve"})
public class OneDriveCallbackController {

    @Resource
    private OneDriveServiceImpl oneDriveServiceImpl;

    @Resource
    private OneDriveChinaServiceImpl oneDriveChinaServiceImpl;


    @GetMapping("/authorize")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "生成 OAuth2 登陆 URL", notes = "生成 OneDrive OAuth2 登陆 URL，用户国际版，家庭版等非世纪互联运营的 OneDrive.")
    public String authorize() {
        return "redirect:https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=" + oneDriveServiceImpl.getClientId() +
                "&response_type=code&redirect_uri=" + oneDriveServiceImpl.getRedirectUri() +
                "&scope=" + oneDriveServiceImpl.getScope();
    }


    @GetMapping("/callback")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "OAuth2 回调地址", notes = "根据 OAuth2 协议，登录成功后，会返回给网站一个 code，用此 code 去换取 accessToken 和 refreshToken.（oneDrive 会回调此接口）")
    public String oneDriveCallback(String code, Model model) {
        OneDriveToken oneDriveToken = oneDriveServiceImpl.getToken(code);
        model.addAttribute("accessToken", oneDriveToken.getAccessToken());
        model.addAttribute("refreshToken", oneDriveToken.getRefreshToken());
        return "callback";
    }


    @GetMapping("/china-authorize")
    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "生成 OAuth2 登陆 URL(世纪互联)", notes = "生成 OneDrive OAuth2 登陆 URL，用于世纪互联版本.")
    public String authorizeChina() {
        return "redirect:https://login.chinacloudapi.cn/common/oauth2/v2.0/authorize?client_id=" + oneDriveChinaServiceImpl.getClientId() +
                "&response_type=code&redirect_uri=" + oneDriveChinaServiceImpl.getRedirectUri() +
                "&scope=" + oneDriveChinaServiceImpl.getScope();
    }


    @GetMapping("/china-callback")
    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "OAuth2 回调地址(世纪互联)", notes = "根据 OAuth2 协议，登录成功后，会返回给网站一个 code，用此 code 去换取 accessToken 和 refreshToken.（oneDrive 会回调此接口）")
    public String oneDriveChinaCallback(String code, Model model) {
        OneDriveToken oneDriveToken = oneDriveChinaServiceImpl.getToken(code);
        model.addAttribute("accessToken", oneDriveToken.getAccessToken());
        model.addAttribute("refreshToken", oneDriveToken.getRefreshToken());
        return "callback";
    }

}