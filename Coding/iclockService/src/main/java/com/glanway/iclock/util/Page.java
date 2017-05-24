package com.glanway.iclock.util;

public class Page {
	/**
     * curPage:当前页
     */
    private int curPage;
    /**
     * pageSize:每页行数
     */
    private int pageSize;
    /**
     * totalPage:共多少页
     */
    @SuppressWarnings("unused")
    private int totalPage;

    /**
	 * totalCount:总记录数
	 */
	private int totalCount;

    /**
     * prepose：前置
     */
    private int prepose;

    /**
     * 起始下标
     */
    private int onSet;

    /**
     * 终止下标
     */
    private int offSet;

    private int topPage;

    private int endPage;

    public int getEndPage() {
        return endPage;
    }

    private int bigenPage;

    public int getBigenPage() {
        return bigenPage;
    }

    public void setBigenPage() {
        if(curPage-2>0&&curPage+2<=totalPage)
            this.bigenPage = curPage-2;
        else if (curPage-2<=0)
            this.bigenPage = 1;
        else if (curPage-2>0&&curPage+2>totalPage){
            if(totalPage-4>1){
                this.bigenPage=totalPage-4;
            }else{
                this.bigenPage=1;
            }
        }
    }

    public void setEndPage() {
        if(bigenPage+4<=totalPage)
            this.endPage =bigenPage+4;
        else if (bigenPage+4>totalPage)
            this.endPage =totalPage;
    }

    public int getTopPage() {
        if((curPage-1)>1)
            return curPage-1;
        return 1;
    }

    public void setTopPage(int topPage) {
        this.topPage = topPage;
    }

    public int getNextPage() {
        if((curPage+1)<totalPage)
            return curPage+1;
        return totalPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    private int nextPage;
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

    public int getPrepose() {
        return prepose;
    }

    public void setPrepose(int prepose) {
        this.prepose = prepose;
    }

    public int getOnSet() {
                return onSet;
    }

    public void setOnSet(int onSet) {
        this.onSet = onSet;
    }

    public int getOffSet() {
       return offSet;

    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }
}

