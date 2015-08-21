package com.nuance.vdmach.model.repositories;

import com.nuance.vdmach.common.vo.ItemDTO;
import com.nuance.vdmach.model.entities.Item;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ItemRepositoryTest{

    @Mock
    ItemRepositoryInterface itemRepositoryInterface;

    @InjectMocks
    ItemRepository itemRepository;



    @Test
    public void itemConversionToVO() throws Exception {
        Item item = new Item(123L, "drink", "Cola", 2.0f, 20);
        Mockito.when(itemRepositoryInterface.findOne(123L)).thenReturn(item);

        ItemDTO itemDTO = itemRepository.findItemById(123L);

        assertThat(itemDTO.getId(), equalTo(item.getId()));
        assertThat(itemDTO.getType(), equalTo(item.getType()));
        assertThat(itemDTO.getName(), equalTo(item.getName()));
        assertThat(itemDTO.getPrice(), equalTo(item.getPrice()));
        assertThat(itemDTO.getQuantity(), equalTo(item.getQuantity()));
    }

    @Test
    public void itemQtyNotUpdatedToNegativeValue() throws Exception {
        Mockito.when(itemRepositoryInterface.findOne(1L)).thenReturn(new Item(1L, null, null, null, 1));
        ItemDTO itemDTO = itemRepository.decreaseItemCount(1L, 2);
        assertThat(itemDTO.getQuantity(), equalTo(0));

    }
}