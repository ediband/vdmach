package com.nuance.vdmach.core.services;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.nuance.vdmach.common.vo.ItemDTO;
import com.nuance.vdmach.model.repositories.ItemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ediband1
 *         date:   8/18/15 3:31 PM
 */
@Named
public class InventoryServiceImpl implements InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);

    @Inject
    ItemRepository itemRepository;

    @Override
    public List<ItemDTO> findAllItems() {
        logger.info("Fetching all items in inventory");

        return itemRepository.findAllItems();
    }

    @Override
    public void decreaseItemCountInInventory(Long itemId, Integer count) {
        logger.info("Decreasing item count of item with id [" + itemId + "] by [" + count + "]");

        itemRepository.decreaseItemCount(itemId, count);
    }
}
