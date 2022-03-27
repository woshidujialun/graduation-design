package pri.tangjiang.graduationdesign.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.service.admin.AdminService;
import pri.tangjiang.graduationdesign.service.user.UserService;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tj/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    /**
     * 管理员获取全部普通员工信息
     *
     * @return
     */
    @RequestMapping("/userinfo/list")
    public Result<List<UserVO>> listUserInfo() {
        return adminService.listUserInfo();
    }

    /**
     * 获取本人用户信息
     *
     * @param token 登录返回的签名
     * @return
     */
    @RequestMapping("/myself/info")
    public Result<UserVO> getMyselfInfo(@RequestHeader("token") String token) {
        return userService.getMyselfInfo(token);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/userinfo/update")
    public Result updateUserInfo(@RequestBody User user){
        return userService.updateMyselfInfo(user);
    }

    /**
     * 新增用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/userinfo/insert")
    public Result insertUserInfo(@RequestBody User user){
        return userService.insertMyselfInfo(user);
    }

    /**
     * 删除用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/userinfo/delete")
    public Result deleteUserInfo(@RequestBody User user){
        return userService.deleteMyselfInfo(user.getId());
    }
}
