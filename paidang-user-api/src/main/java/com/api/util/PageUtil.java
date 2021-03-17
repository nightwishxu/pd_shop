package com.api.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    /**
     * 开始分页
     * @param list
     * @param pageNum 页码
     * @param pageSize 每页多少条数据
     * @return
     */
    public static List startPage(List list, Integer pageNum,
                                 Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }

        Integer count = list.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum != pageCount) {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = fromIndex + pageSize;
        } else {
            fromIndex = (pageNum - 1) * pageSize;
            toIndex = count;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }

    public static List pageBySubList(List list, int pagesize, int currentPage) {
        int totalcount = list.size();
        int pagecount = 0;
        List<String> subList;
        int m = totalcount % pagesize;
        if (m > 0) {
            pagecount = totalcount / pagesize + 1;
        } else {
            pagecount = totalcount / pagesize;
        }
        if (m == 0) {
            subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
        } else {
            if (currentPage == pagecount) {
                subList = list.subList((currentPage - 1) * pagesize, totalcount);
            } else {
                subList = list.subList((currentPage - 1) * pagesize, pagesize * (currentPage));
            }
        }
        return subList;
    }

    public static  <T> List<T> limitList(List<T> resultList,Integer pageNum,
                                 Integer pageSize) {
        try {
            Integer  startRowNo = (pageNum - 1) * pageSize + 1;
            Integer  endRowNo = startRowNo + pageSize - 1;
            List var6 = new ArrayList();
            if (resultList != null && resultList.size() > 0 && startRowNo != null) {
                if (startRowNo > resultList.size()) {
                    return var6;
                }

                if (endRowNo > resultList.size()) {
                    endRowNo = resultList.size();
                }

                resultList = resultList.subList(startRowNo - 1, endRowNo);
            }

            var6 = resultList;
            return var6;
        } finally {
        }
    }

}