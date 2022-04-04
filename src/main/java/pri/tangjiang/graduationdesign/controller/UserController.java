package pri.tangjiang.graduationdesign.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.tangjiang.graduationdesign.bean.Department;
import pri.tangjiang.graduationdesign.bean.User;
import pri.tangjiang.graduationdesign.bean.param.PageParm;
import pri.tangjiang.graduationdesign.bean.vo.SalaryVO;
import pri.tangjiang.graduationdesign.bean.vo.UserVO;
import pri.tangjiang.graduationdesign.dao.DepartmentMapper;
import pri.tangjiang.graduationdesign.dao.SalaryMapper;
import pri.tangjiang.graduationdesign.service.admin.AdminService;
import pri.tangjiang.graduationdesign.service.user.UserService;
import pri.tangjiang.graduationdesign.util.JwtManage;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tj/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private SalaryMapper salaryMapper;

    /**
     * 管理员获取全部员工信息
     *
     * @return
     */
    @RequestMapping("/userinfo/list")
    public Result<PageInfo<UserVO>> listUserInfo(@RequestBody PageParm pageParm) {
        return adminService.listUserInfo(pageParm);
    }

    /**
     * 员工薪资统计
     *
     * @return
     */
    @RequestMapping("/salary/statistics")
    public Result<List<SalaryVO>> salaryStatistics() {
        return Result.ok(salaryMapper.list());
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
     * 保存用户信息
     *
     * @param user
     * @returnuser.getIfmyself()
     */
    @RequestMapping("/userinfo/save")
    public Result updateUserInfo(@RequestBody User user, @RequestHeader("token") String token) throws Exception {
        if (user.getIfmyself() != null) {
            user.setId(JwtManage.verifyToken(token));
        }
        if (user.getId() == null) {
            return userService.insertMyselfInfo(user);
        }
        return userService.updateMyselfInfo(user);
    }

    /**
     * 删除用户信息
     *
     * @param param
     * @return
     */
    @RequestMapping("/userinfo/delete")
    public Result deleteUserInfo(@RequestBody Map<String,Object> param) {
        List<Integer> ids = (List<Integer>) param.get("ids");
        return userService.deleteMyselfInfo(ids);
    }

    /**
     * 获取部门名称及id
     *
     * @return
     */
    @RequestMapping("/department/list")
    public Result<List<Department>> listDepartment() {
        return Result.ok(departmentMapper.selectNameAndId());
    }
}
