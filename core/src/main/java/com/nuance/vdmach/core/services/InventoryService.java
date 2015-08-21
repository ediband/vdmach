package com.nuance.vdmach.core.services;

import java.util.List;

import com.nuance.vdmach.common.vo.ItemDTO;

/**
 * Exposes operations on the machine inventory add, remove, update items etc...
 *
 * @author ediband1
 *         date:   8/18/15 1:50 PM
 */
public interface InventoryService {

    List<ItemDTO> findAllItems();

    void decreaseItemCountInInventory(Long itemId, Integer count);
}
