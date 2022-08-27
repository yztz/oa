package top.yztz.oa.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.yztz.oa.bean.Category;
import top.yztz.oa.bean.Page;
import top.yztz.oa.bean.User;
import top.yztz.oa.service.CategoryService;
import top.yztz.oa.utils.PageUtils;
import top.yztz.oa.utils.ResponseUtils;
import top.yztz.oa.utils.ValidateUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static top.yztz.oa.ErrCode.ILLEGAL_ARG;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/add")
    public JSONObject add(@RequestBody Category category) {//添加用户信息

        if (!ValidateUtils.isLegalCategory(category)) {
            return ResponseUtils.fail("非法参数", ILLEGAL_ARG);
        } else {
            categoryService.add(category);
            return ResponseUtils.success("新建成功", null);
        }
    }

    @PostMapping("/update")
    public JSONObject update(@RequestBody Category category) {//用户更新

        if (!ValidateUtils.isLegalCategory(category)) {
            return ResponseUtils.fail("非法的参数信息", ILLEGAL_ARG);
        } else {
            categoryService.update(category);
            return ResponseUtils.success("更新成功", category);
        }
    }


    @GetMapping("/queryall")
    public JSONObject queryAll() {
        return ResponseUtils.success("查询成功", categoryService.getAll());
    }


    @GetMapping("/query")
    public JSONObject query(HttpServletRequest request, HttpServletResponse response) {
        String categoryName = request.getParameter("categoryName");
        String pageIndex = request.getParameter("pageIndex");
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isEmpty(pageIndex) || StringUtils.isEmpty(pageSize)) {
            return ResponseUtils.fail("查询信息不能为空!", ILLEGAL_ARG);
        } else {
            int pIndex = Integer.parseInt(pageIndex);
            int pSzie = Integer.parseInt(pageSize);
            int size = (int) categoryService.getRecordSize();
            int totalPage = PageUtils.getTotalPage(pSzie, size);

            if (StringUtils.isEmpty(categoryName)) {
                List<User> userList = categoryService.getPageRecord(pIndex, pSzie);

                Page page = new Page();

                page.setTotalPage(totalPage);
                page.setPageIndex(pIndex);
                page.setPageSize(pSzie);
                page.setData(userList);

                return ResponseUtils.success("查询成功!", page);
            } else {
                List<Category> categoryList = categoryService.getByName(categoryName, pIndex, pSzie);

                Page page = new Page();

                page.setTotalPage(totalPage);
                page.setPageIndex(pIndex);
                page.setPageSize(pSzie);
                page.setData(categoryList);

                return ResponseUtils.success("查询成功!", page);
            }

        }

    }


    @GetMapping("/delete")
    public JSONObject delete(@RequestParam("ids") List<Long> categoryID) {//删除用户
        if (categoryID == null)
            return ResponseUtils.fail("参数为空", ILLEGAL_ARG);

        categoryService.deleteBatch(categoryID);
        return ResponseUtils.success("删除成功", null);
    }

}
