package edu.university_connect.model.contract.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface RequestUtils {
    static Pageable extractPagination(Pageable pageableReq) {
        return PageRequest.of(
                pageableReq.getPageNumber() > 0 ? pageableReq.getPageNumber() - 1 : 0,
                pageableReq.getPageSize() ,
                pageableReq.getSort()
        );
    }
}
