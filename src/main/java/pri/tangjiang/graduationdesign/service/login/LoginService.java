package pri.tangjiang.graduationdesign.service.login;

import pri.tangjiang.graduationdesign.util.Result;

public interface LoginService {
    /**
     * 登录接口
     *
     * @param username
     * @param password
     * @return
     */
    Result login(String username, String password);
}
