package top.yztz.oa.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.yztz.oa.bean.Page;
import top.yztz.oa.bean.User;
import top.yztz.oa.service.UserService;
import top.yztz.oa.utils.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static top.yztz.oa.ErrCode.*;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public JSONObject userLogin(@RequestBody User userInfo) {
        String username = userInfo.getUserName();
        String password = userInfo.getPassword();
        System.out.println("用户 " + username + " 登录 " + password);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseUtils.resultBean(false, "用户名或密码不能为空!", null, ILLEGAL_ARG);
        } else {

            User user = userService.getByUserNameAndPassword(username, password);
            if (user == null) {
                return ResponseUtils.resultBean(false, "账号或密码错误!", null, BAD_USER_PWD);
            } else {
//                user.setAppendix(UUIDUtils.getUUID());
                JSONObject obj = new JSONObject();
                obj.put("token", AuthUtils.getToke(user.getId().toString()));
                return ResponseUtils.success("登录成功!", obj);
            }

        }
    }


    @GetMapping("/info")
    public JSONObject userInfo(HttpServletRequest request) {
        Long userID = (Long) request.getAttribute("userID");
        System.out.println("userID is " + userID);
        assert userID != null;
        User user = userService.getById(userID);

        return ResponseUtils.success("成功获取用户信息", user);
    }


    @PostMapping("/add")
    public JSONObject userRegister(@RequestBody User user) {//添加用户信息

        if (!ValidateUtils.isLegalUser(user)) {
            return ResponseUtils.fail("非法参数", ILLEGAL_ARG);
        } else {
            userService.add(user);
            return ResponseUtils.success("新建成功", null);
        }
    }

    @PostMapping("/update")
    public JSONObject update(@RequestBody User user) {//用户更新

        if (!ValidateUtils.isLegalUser(user)) {
            return ResponseUtils.fail("非法的参数信息", ILLEGAL_ARG);
        } else {
            userService.update(user);
            return ResponseUtils.success("更新成功", user);
        }
    }

    @GetMapping("/query")
    public JSONObject query(HttpServletRequest request, HttpServletResponse response) {//查询用户

        String userName = request.getParameter("username");
        String pageIndex = request.getParameter("pageIndex");
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isEmpty(pageIndex) || StringUtils.isEmpty(pageSize)) {
            return ResponseUtils.fail("查询信息不能为空!", ILLEGAL_ARG);
        } else {
            int pIndex = Integer.parseInt(pageIndex);
            int pSzie = Integer.parseInt(pageSize);

            if (StringUtils.isEmpty(userName)) {
                List<User> userList = userService.getPageRecord(pIndex, pSzie);
                String size = userService.getRecordSize();

                int totalPage = PageUtils.getTotalPage(pSzie, Integer.parseInt(size));

                Page page = new Page();

                page.setTotalPage(totalPage);
                page.setPageIndex(pIndex);
                page.setPageSize(pSzie);
                page.setData(userList);

                return ResponseUtils.success("查询成功!", page);
            } else {
                List<User> userList = userService.queryByName(userName, pIndex, pSzie);
                String size = userService.querySizeByName(userName);
                int totalPage = PageUtils.getTotalPage(pSzie, Integer.parseInt(size));

                Page page = new Page();

                page.setTotalPage(totalPage);
                page.setPageIndex(pIndex);
                page.setPageSize(pSzie);
                page.setData(userList);

                return ResponseUtils.success("查询成功!", page);
            }

        }

    }

    @GetMapping("/delete")
    public JSONObject delete(@RequestParam("ids") List<Long> usersID) {//删除用户
        if (usersID == null)
            return ResponseUtils.fail("参数为空", ILLEGAL_ARG);

        userService.deleteBatch(usersID);

        return ResponseUtils.success("删除成功", null);
    }

}
