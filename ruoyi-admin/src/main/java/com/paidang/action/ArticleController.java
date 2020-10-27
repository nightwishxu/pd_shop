//package com.paidang.action;
//
//import java.util.List;
//
//import com.paidang.dao.model.Article;
//import com.paidang.service.ArticleService;
//import com.ruoyi.common.core.domain.Ret;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.ruoyi.common.annotation.Log;
//import com.ruoyi.common.core.controller.BaseController;
//import com.ruoyi.common.enums.BusinessType;
//
//import com.ruoyi.common.utils.poi.ExcelUtil;
//import com.ruoyi.common.core.page.TableDataInfo;
//
///**
// * 动态Controller
// *
// * @author ruoyi
// * @date 2020-10-18
// */
//@RestController
//@RequestMapping("/community/article")
//public class ArticleController extends BaseController
//{
//    @Autowired
//    private ArticleService articleService;
//
//    /**
//     * 查询动态列表
//     */
//    @PreAuthorize("@ss.hasPermi('community:article:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(Article article)
//    {
//        startPage();
//        List<Article> list = articleService.selectArticleList(article);
//        return getDataTable(list);
//    }
//
//    /**
//     * 导出动态列表
//     */
//    @PreAuthorize("@ss.hasPermi('community:article:export')")
//    @Log(title = "动态", businessType = BusinessType.EXPORT)
//    @GetMapping("/export")
//    public Ret export(Article article)
//    {
//        List<Article> list = articleService.selectArticleList(article);
//        ExcelUtil<Article> util = new ExcelUtil<Article>(Article.class);
//        return util.exportExcel(list, "article");
//    }
//
//    /**
//     * 获取动态详细信息
//     */
//    @PreAuthorize("@ss.hasPermi('community:article:query')")
//    @GetMapping(value = "/{id}")
//    public Ret getInfo(@PathVariable("id") Integer id)
//    {
//        return Ret.success(articleService.selectArticleById(id));
//    }
//
//    /**
//     * 新增动态
//     */
//    @PreAuthorize("@ss.hasPermi('community:article:add')")
//    @Log(title = "动态", businessType = BusinessType.INSERT)
//    @PostMapping
//    public Ret add(@RequestBody Article article)
//    {
//        return toAjax(articleService.insertArticle(article));
//    }
//
//    /**
//     * 修改动态
//     */
//    @PreAuthorize("@ss.hasPermi('community:article:edit')")
//    @Log(title = "动态", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public Ret edit(@RequestBody Article article)
//    {
//        return toAjax(articleService.updateArticle(article));
//    }
//
//    /**
//     * 删除动态
//     */
//    @PreAuthorize("@ss.hasPermi('community:article:remove')")
//    @Log(title = "动态", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ids}")
//    public Ret remove(@PathVariable Integer[] ids)
//    {
//        return toAjax(articleService.deleteArticleByIds(ids));
//    }
//}
