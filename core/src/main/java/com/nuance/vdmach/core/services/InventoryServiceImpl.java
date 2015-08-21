package com.nuance.vdmach.core.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.nuance.vdmach.common.vo.ItemDTO;
import com.nuance.vdmach.model.repositories.ItemRepository;

/**
 *
 * @author ediband1
 *         date:   8/18/15 3:31 PM
 */
@Named
public class InventoryServiceImpl implements InventoryService {

    @Inject
    ItemRepository itemRepository;

    public List<ItemDTO> findAllItems() {
        return itemRepository.findAllItems();
    }

    @Override
    public void decreaseItemCountInInventory(Long itemId, Integer count) {
        itemRepository.decreaseItemCount(itemId, count);
    }
}
