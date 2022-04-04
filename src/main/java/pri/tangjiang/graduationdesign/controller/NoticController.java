package pri.tangjiang.graduationdesign.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pri.tangjiang.graduationdesign.bean.Notic;
import pri.tangjiang.graduationdesign.bean.param.PageParm;
import pri.tangjiang.graduationdesign.service.admin.AdminService;
import pri.tangjiang.graduationdesign.service.user.UserService;
import pri.tangjiang.graduationdesign.util.Result;

import javax.annotation.Resource;

@RestController
@RequestMapping("/tj/notic")
public class NoticController {
    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    /**
     * 发布公告
     *
     * @param notic
     * @return
     */
    @RequestMapping("/issue")
    public Result issueNoyic(@RequestBody Notic notic){
        return adminService.issueNotic(notic);
    }

    /**
     * 查询公告列表
     *
     * @return
     */
    @RequestMapping("/list")
    public Result<PageInfo<Notic>> listNotices(@RequestBody PageParm pageParm){
        return userService.listNotices(pageParm);
    }
}
