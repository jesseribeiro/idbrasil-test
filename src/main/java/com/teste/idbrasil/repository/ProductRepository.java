package com.teste.idbrasil.repository;

import com.teste.idbrasil.bean.ProductBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<ProductBean, Long>, GenericRepository {

    @Query("Select case when (count(x) > 0)  then true else false end From Product x where x.id =:id ")
    boolean ifExistsProduct(@Param("id") Long id);

}
