package com.nuance.vdmach.model.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nuance.vdmach.common.util.VOUtil;
import com.nuance.vdmach.common.vo.ItemDTO;
import com.nuance.vdmach.model.entities.Item;

/**
 * Interacts with the DB via the RepositoryInterface and returns VO to the upper layer
 *
 * @author ediband1
 *         date:   8/18/15 2:06 PM
 */
@Named
public class ItemRepository {

    @Inject
    ItemRepositoryInterface itemRepositoryInterface;

    public ItemDTO findItemById(Long id) {
        Item item = itemRepositoryInterface.findOne(id);
        return item != null ? VOUtil.convertToVO(item, ItemDTO.class) : null;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<ItemDTO> findAllItems() {

        Item test = new Item();
        test.setName("testName");
        itemRepositoryInterface.save(test);

        List<ItemDTO> itemDTOs = null;

        List<Item> items = itemRepositoryInterface.findAll();
        if (items != null) {
            itemDTOs = new ArrayList<ItemDTO>();
            for (Item item : items) {
                itemDTOs.add(VOUtil.convertToVO(item, ItemDTO.class));
            }
        }

        return itemDTOs;
    }


}
