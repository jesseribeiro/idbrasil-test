package com.teste.idbrasil.repository;

import com.teste.idbrasil.bean.ProductBean;
import com.teste.idbrasil.bean.VendaBean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface VendaRepository extends CrudRepository<VendaBean, Long>, GenericRepository {

}
