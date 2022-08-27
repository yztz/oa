package top.yztz.oa.controller;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import top.yztz.oa.bean.Category;
import top.yztz.oa.bean.News;
import top.yztz.oa.bean.Page;
import top.yztz.oa.service.NewsService;
import top.yztz.oa.utils.PageUtils;
import top.yztz.oa.utils.ResponseUtils;
import top.yztz.oa.utils.ValidateUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import static top.yztz.oa.ErrCode.*;

@RestController
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Resource
    private NewsService newsService;

    @GetMapping("/delete")
    public JSONObject delete(@RequestParam("ids") List<Long> ids) {
        newsService.deleteBatch(ids);
        return ResponseUtils.success("删除成功", null);
    }

    @PostMapping("/update")
    public JSONObject update(@RequestBody News news) {//用户更新

        if (!ValidateUtils.isLegalNews(news)) {
            return ResponseUtils.fail("非法的参数信息", ILLEGAL_ARG);
        } else {
            newsService.update(news);
            return ResponseUtils.success("更新成功", news);
        }
    }

    @PostMapping("/add")
    public JSONObject add(@RequestBody News news) {

        if (!ValidateUtils.isLegalNews(news)) {
            return ResponseUtils.fail("非法参数", ILLEGAL_ARG);
        } else {
            news.setTime(new Date());
            newsService.add(news);
            return ResponseUtils.success("新建成功", null);
        }
    }

    @GetMapping("/query")
    public JSONObject query(HttpServletRequest request, HttpServletResponse response) {//查询信息

        String name = request.getParameter("name");
        String cid = request.getParameter("cid");
        String pageIndex = request.getParameter("pageIndex");
        String pageSize = request.getParameter("pageSize");

        if (StringUtils.isEmpty(pageIndex) || StringUtils.isEmpty(pageSize)) {
            return ResponseUtils.fail("分页信息不能为空!", ILLEGAL_ARG);
        } else {
            int pIndex = Integer.parseInt(pageIndex);
            int pSize = Integer.parseInt(pageSize);

            if (StringUtils.isEmpty(name) && StringUtils.isEmpty(cid)) { //都为空
                List<News> newsList = newsService.getPageRecord(pIndex, pSize);
                int size = newsService.getRecordSize();
                int totalPage = PageUtils.getTotalPage(pSize, size);

                Page page = new Page(pIndex, pSize, totalPage, newsList);
                return ResponseUtils.success("查询成功!", page);
            } else if ((!StringUtils.isEmpty(name)) && StringUtils.isEmpty(cid)) { //name不为空
                List<News> newsList = newsService.queryByName(name, pIndex, pSize);
                int size = newsService.querySizeByName(name);
                int totalPage = PageUtils.getTotalPage(pSize, size);

                Page page = new Page(pIndex, pSize, totalPage, newsList);
                return ResponseUtils.success("查询成功!", page);

            } else if (StringUtils.isEmpty(name) && (!StringUtils.isEmpty(cid))) { //cid不为空
                long c_id = Integer.parseInt(cid);
                List<News> newsList = newsService.queryByCategory(c_id, pIndex, pSize);
                int size = newsService.querySizeByCategory(c_id);
                int totalPage = PageUtils.getTotalPage(pSize, size);

                Page page = new Page(pIndex, pSize, totalPage, newsList);
                return ResponseUtils.success("查询成功!", page);
            } else {//都不为空
                long c_id = Integer.parseInt(cid);
                List<News> newsList = newsService.queryByNameAndCID(name, c_id, pIndex, pSize);
                int size = newsService.querySizeByNameAndCID(name, c_id);
                int totalPage = PageUtils.getTotalPage(pSize, size);

                Page page = new Page(pIndex, pSize, totalPage, newsList);
                return ResponseUtils.success("查询成功!", page);
            }

        }

    }


}
