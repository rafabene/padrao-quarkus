package com.rafabene.application;

public class PageResponse<T> extends Response<T> {

    private int page;
    private int pageSize;
    private int totalPages;
    private long totalItems;

    public PageResponse(T data, String message, Status status, int page, int pageSize,
            long totalItems) {
        super(data, message, status);
        this.page = page;
        this.totalItems = totalItems;
        this.pageSize = (int) Math.ceil((double) totalItems / pageSize);
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

}
