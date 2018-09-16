package com.pet.planet.api.publication.response;


import java.util.List;

public class PageResponse<DTO>{

    private List<DTO> content;
    private int page;
    private int totalPages;
    private boolean isLast;


    public List<DTO> getContent() {
        return content;
    }
    public void setContent(List<DTO> content) {

        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
