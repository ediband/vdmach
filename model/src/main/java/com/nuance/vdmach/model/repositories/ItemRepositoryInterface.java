package com.nuance.vdmach.model.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nuance.vdmach.model.entities.Item;

/**
 *
 * @author ediband1
 *         date:   8/18/15 2:01 PM
 */
@Transactional(propagation = Propagation.SUPPORTS)
public interface ItemRepositoryInterface extends JpaRepository<Item, Long> {

    Item findOne(Long id);

    List<Item> findAll();
}
