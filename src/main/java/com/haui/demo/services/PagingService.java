package com.haui.demo.services;

import com.haui.demo.models.requests.AbsFilter;
import com.haui.demo.models.responses.Paging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class PagingService {

    @Value("${app.response.page.size}")
    private int pageSizeDefault;

    @PersistenceContext
    private EntityManager entityManager;

    public <F extends AbsFilter, O> Paging<List<O>> getListPaging(F filter, CriteriaBuilder criteriaBuilder,
                                                                  CriteriaQuery<O> criteriaQuery, CriteriaQuery<Long> criteriaQuery1,
                                                                  Root<O> root, List<Predicate> predicates) {
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));

        return getPaging(filter, criteriaQuery, criteriaQuery1, predicates);
//        return exportExcel(criteriaQuery);
    }

    public <T, F extends AbsFilter> Paging<List<T>> getPaging(F filter, CriteriaQuery<T> cqSelect,
                                                              CriteriaQuery<Long> cqCount, List<Predicate> predicates) {
        int startIndex = (filter.getPage() - 1) * pageSizeDefault;

        List<T> building = entityManager.createQuery(cqSelect)
                .setFirstResult(startIndex)
                .setMaxResults(pageSizeDefault)
                .getResultList();

        cqCount.where(predicates.toArray(new Predicate[0]));
        long totalElement = entityManager.createQuery(cqCount).getSingleResult();
        int totalPage = (int) Math.ceil((double) totalElement / pageSizeDefault);

        return mapPaging(building, filter.getPage(), pageSizeDefault,
                building.size(), totalElement, totalPage);
    }

    public <T> Paging<List<T>> mapPaging(List<T> accounts, int page, int pageSizeDf, int size,
                                         long totalElement, int totalPage) {
        Paging<List<T>> paging = new Paging<>();
        paging.setPage(page);
        paging.setSize(pageSizeDf);
        paging.setTotalInPage(size);
        paging.setTotalRecord(totalElement);
        paging.setTotalPage(totalPage);
        paging.setData(accounts);

        return paging;
    }

    public <I, O> Paging<List<O>> mapPagingDTO(List<O> accountDTOS,
                                             Paging<List<I>> paging) {
        return mapPaging
                (accountDTOS, paging.getPage(),
                        paging.getSize(), paging.getTotalInPage(),
                        paging.getTotalRecord(), paging.getTotalPage());
    }

    private <O> Paging<List<O>> exportExcel(CriteriaQuery<O> cq) {

        List<O> values = entityManager.createQuery(cq)
                .getResultList();

        return mapPaging(values, 0, 0, 0, 0, 0);
    }

    public <T> Paging<List<T>> getPaging(List<T> data, int page, CriteriaQuery<T> criteriaQuery) {
        int totalRecord = data.size();
        int totalPage = (int) Math.ceil((double) totalRecord / pageSizeDefault);
        int startIndex = (page - 1) * pageSizeDefault;

        List<T> totalInPage = entityManager.createQuery(criteriaQuery)
                .setFirstResult(startIndex)
                .setMaxResults(pageSizeDefault)
                .getResultList();

        return mapPaging(totalInPage, page, pageSizeDefault, data.size(), totalRecord, totalPage);
    }

    public <T> Paging<List<T>> getPaging(List<T> data, int page) {
        int totalRecord = data.size();
        int totalPage = (int) Math.ceil((double) totalRecord / pageSizeDefault);
        int startIndex = (page - 1) * pageSizeDefault;

        int endIndex = startIndex + pageSizeDefault <= totalRecord ? startIndex + pageSizeDefault : totalRecord - startIndex;
        data = data.subList(startIndex, endIndex);
        return mapPaging(data, page, pageSizeDefault, data.size(), totalRecord, totalPage);
    }

}
