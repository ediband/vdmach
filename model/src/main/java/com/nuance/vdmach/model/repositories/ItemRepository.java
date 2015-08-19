package com.nuance.vdmach.model.repositories;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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


    public List<ItemDTO> findAllItems() {
        List<ItemDTO> itemDTOs = null;

        List<Item> items = itemRepositoryInterface.findAll();
        if (items != null) {
            for (Item item : items) {
                itemDTOs.add(VOUtil.convertToVO(item, ItemDTO.class));
            }
        }

        return itemDTOs;
    }


}
