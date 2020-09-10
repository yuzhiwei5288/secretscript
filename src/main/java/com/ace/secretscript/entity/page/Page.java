package com.ace.secretscript.entity.page;


import com.ace.secretscript.common.util.Const;

import java.io.Serializable;

/**
 * @author ace
 * @version V1.0
 * @title Page.java
 * @package com.xiye.common.entity.page
 * @description 分页类
 * @date 2020-03-27
 */
public class Page implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 每页显示记录数
     */
    private int showCount;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总记录数
     */
    private int totalResult;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 当前记录起始索引
     */
    private int currentResult;

    /**
     * true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
     */
    private boolean entityOrField;
    private PageData PageData = new PageData();

    public Page() {
        try {
            this.showCount = Integer.parseInt(Const.PAGE_COUNT);
        } catch (NumberFormatException e) {
            this.showCount = 15;
        }
    }

    public int getTotalPage() {
        if (totalResult % showCount == 0) {
            totalPage = totalResult / showCount;
        } else {
            totalPage = totalResult / showCount + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (currentPage > getTotalPage()) {
            currentPage = getTotalPage();
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {

        this.showCount = showCount;
    }

    public int getCurrentResult() {
        currentResult = (getCurrentPage() - 1) * getShowCount();
        if (currentResult < 0) {
            currentResult = 0;
        }
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public boolean isEntityOrField() {
        return entityOrField;
    }

    public void setEntityOrField(boolean entityOrField) {
        this.entityOrField = entityOrField;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public PageData getPageData() {
        return PageData;
    }

    public void setPageData(PageData pageData) {
        PageData = pageData;
    }
}
